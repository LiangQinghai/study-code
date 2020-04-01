package cn.liangqinghai.study.flux.entities;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @Title HelloWorld
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 3:48 PM
 */
public class HelloWorld implements Serializable {

    private static final long serialVersionUID = -730057586726756685L;

    private Long id;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
