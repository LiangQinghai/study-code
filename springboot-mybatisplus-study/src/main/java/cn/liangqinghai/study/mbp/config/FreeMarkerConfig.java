package cn.liangqinghai.study.mbp.config;

import cn.liangqinghai.study.mbp.config.props.CustomConfigProp;
import cn.liangqinghai.study.mbp.utils.freemarker.FormatTimeHelper;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author LiangQinghai
 * @Title FreeMarkerConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 11:06
 */
@Configuration
@EnableConfigurationProperties({CustomConfigProp.class})
public class FreeMarkerConfig {

    @Autowired
    private CustomConfigProp customConfigProp;

    @Resource
    private freemarker.template.Configuration configuration;

    @Resource
    private FreeMarkerViewResolver resolver;

    @Resource
    private InternalResourceViewResolver viewResolver;

    @PostConstruct
    public void setShareVariable() throws TemplateModelException {

        configuration.setSharedVariable("author", customConfigProp.getFreeMaker().getAuthor());
        configuration.setSharedVariable("keyword", customConfigProp.getFreeMaker().getKeyword());
        configuration.setSharedVariable("description", customConfigProp.getFreeMaker().getDescription());

        configuration.setTagSyntax(freemarker.template.Configuration.AUTO_DETECT_TAG_SYNTAX);
        configuration.setSharedVariable("formatTime", new FormatTimeHelper());
        configuration.setSharedVariable("shiro", new ShiroTags());

    }

    @PostConstruct
    public void freeMarkerConfigurer() {}

}
