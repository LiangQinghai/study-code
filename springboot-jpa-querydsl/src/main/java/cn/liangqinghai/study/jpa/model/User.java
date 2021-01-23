package cn.liangqinghai.study.jpa.model;

import javax.persistence.*;

/**
 * @author LiangQinghai
 * @Title User
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 10:45
 */
@Entity
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

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getSex() {
        return this.sex;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getLastLoginTime() {
        return this.lastLoginTime;
    }

    public String getLastLoginIp() {
        return this.lastLoginIp;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$sex = this.getSex();
        final Object other$sex = other.getSex();
        if (this$sex == null ? other$sex != null : !this$sex.equals(other$sex)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$lastLoginTime = this.getLastLoginTime();
        final Object other$lastLoginTime = other.getLastLoginTime();
        if (this$lastLoginTime == null ? other$lastLoginTime != null : !this$lastLoginTime.equals(other$lastLoginTime))
            return false;
        final Object this$lastLoginIp = this.getLastLoginIp();
        final Object other$lastLoginIp = other.getLastLoginIp();
        if (this$lastLoginIp == null ? other$lastLoginIp != null : !this$lastLoginIp.equals(other$lastLoginIp))
            return false;
        final Object this$avatarUrl = this.getAvatarUrl();
        final Object other$avatarUrl = other.getAvatarUrl();
        if (this$avatarUrl == null ? other$avatarUrl != null : !this$avatarUrl.equals(other$avatarUrl)) return false;
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $sex = this.getSex();
        result = result * PRIME + ($sex == null ? 43 : $sex.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $lastLoginTime = this.getLastLoginTime();
        result = result * PRIME + ($lastLoginTime == null ? 43 : $lastLoginTime.hashCode());
        final Object $lastLoginIp = this.getLastLoginIp();
        result = result * PRIME + ($lastLoginIp == null ? 43 : $lastLoginIp.hashCode());
        final Object $avatarUrl = this.getAvatarUrl();
        result = result * PRIME + ($avatarUrl == null ? 43 : $avatarUrl.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * PRIME + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", sex=" + this.getSex() + ", email=" + this.getEmail() + ", lastLoginTime=" + this.getLastLoginTime() + ", lastLoginIp=" + this.getLastLoginIp() + ", avatarUrl=" + this.getAvatarUrl() + ", createTime=" + this.getCreateTime() + ")";
    }
}
