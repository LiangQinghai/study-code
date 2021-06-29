package cn.liangqinghai.study.dynamic.datasource.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_tenant")
public class Tenant {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private String jdbcUrl;

    private String driver;

    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public Tenant setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Tenant setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tenant setName(String name) {
        this.name = name;
        return this;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public Tenant setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public String getDriver() {
        return driver;
    }

    public Tenant setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Tenant setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Tenant setPassword(String password) {
        this.password = password;
        return this;
    }
}
