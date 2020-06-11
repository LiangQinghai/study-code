package cn.liangqinghai.study.jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LiangQinghai
 * @Title User
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 10:45
 */
@Entity
@Data
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UERR_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SEX")
    private Integer sex;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LAST_LOGIN_TIME")
    private Long lastLoginTime;

    @Column(name = "LAST_LOGIN_IP")
    private String lastLoginIp;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Column(name = "CREATE_TIME")
    private Long createTime;

}
