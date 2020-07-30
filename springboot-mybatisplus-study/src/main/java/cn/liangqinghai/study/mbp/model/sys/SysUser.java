package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysUser
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 17:46
 */
@TableName("SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -5611728402094320418L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField()
    private String userName;

    @TableField()
    private transient String password;

    @TableField
    private Integer sex;

    @TableField
    private String email;

    @TableField
    private Long lastLoginTime;

    @TableField
    private String lastLoginIp;

    @TableField
    private String avatarUrl;

    @TableField
    private Long createTime;

    @TableField(exist = false)
    private List<Long> roleIdList;

    public Long getId() {
        return id;
    }

    public SysUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public SysUser setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public SysUser setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public SysUser setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public SysUser setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public SysUser setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public SysUser setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
        return this;
    }
}
