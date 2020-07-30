package cn.liangqinghai.study.mbp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Mr.Liang
 * @date 2020/5/19
 */
@ConfigurationProperties(prefix = MbpProperties.MBP)
public class MbpProperties {

    public static final String MBP = "mbp";

    @NestedConfigurationProperty
    private Demo demo;

    @NestedConfigurationProperty
    private Path path;

    @NestedConfigurationProperty
    private Password password;

    @NestedConfigurationProperty
    private Generator generator;

    @NestedConfigurationProperty
    private Xss xss;

    @NestedConfigurationProperty
    private Email email;

    public static String getMBP() {
        return MBP;
    }

    public Demo getDemo() {
        return demo;
    }

    public MbpProperties setDemo(Demo demo) {
        this.demo = demo;
        return this;
    }

    public Path getPath() {
        return path;
    }

    public MbpProperties setPath(Path path) {
        this.path = path;
        return this;
    }

    public Password getPassword() {
        return password;
    }

    public MbpProperties setPassword(Password password) {
        this.password = password;
        return this;
    }

    public Generator getGenerator() {
        return generator;
    }

    public MbpProperties setGenerator(Generator generator) {
        this.generator = generator;
        return this;
    }

    public Xss getXss() {
        return xss;
    }

    public MbpProperties setXss(Xss xss) {
        this.xss = xss;
        return this;
    }

    public Email getEmail() {
        return email;
    }

    public MbpProperties setEmail(Email email) {
        this.email = email;
        return this;
    }
}
