package cn.liangqinghai.study.webservice;

import cn.liangqinghai.study.webservice.service.DemoImpl;
import cn.liangqinghai.study.webservice.service.DemoService;
import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webservice</p>
 * <p>File name: CxfConfig</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/26 16:33
 */
@Configuration
public class CxfConfig {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Bean
    public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/services/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 发布服务并指定访问URL
     * @return
     */
    @Bean
    public EndpointImpl userEnpoint(DemoService demoService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new DemoImpl());
//        endpoint.getInInterceptors().add(authInterceptor);
        endpoint.getInInterceptors().add(new SAAJInInterceptor());
        Map<String, Object> inProperties = new HashMap<>();
        inProperties.put(ConfigurationConstants.ACTION, ConfigurationConstants.ENCRYPT);
        inProperties.put(ConfigurationConstants.DEC_PROP_FILE, "insecurity.properties");
        inProperties.put(ConfigurationConstants.PW_CALLBACK_REF, new TestPwdCallback());
        endpoint.getInInterceptors().add(new WSS4JInInterceptor(inProperties));
        endpoint.publish("/user");
        return endpoint;
    }

    public static class TestPwdCallback implements CallbackHandler {

        private static Map<String, String> passwords = new HashMap<>();

        static {
            passwords.put("myalias", "myAliasPassword");
            passwords.put("alice", "alicePassword");
            passwords.put("username", "myAliasPassword");
        }

        @Override
        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {
                WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

                String pass = passwords.get(pc.getIdentifier());
                if (pass != null) {
                    pc.setPassword(pass);
                }
            }
        }
    }

}
