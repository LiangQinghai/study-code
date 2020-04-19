package cn.liangqinghai.study.jwt.module;

import java.io.Serializable;

/**
 * @author Mr.Liang
 * @date 2020/4/19
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8925503669256933507L;

    private Integer id;

    private String name;

    private String password;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
}
