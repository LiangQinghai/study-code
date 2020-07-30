package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @Title SysUserLoginLog
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:23
 */
@TableName("SYS_USER_LOGIN_LOG")
public class SysUserLoginLog implements Serializable {

    private static final long serialVersionUID = -6057068502205889558L;

    @TableId
    private String id;

    @TableField
    private Long loginTime;

    @TableField
    private String loginIp;

    @TableField
    private Long userId;

    @TableField
    private String operationSystem;

    @TableField
    private String browser;

    public String getId() {
        return id;
    }

    public SysUserLoginLog setId(String id) {
        this.id = id;
        return this;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public SysUserLoginLog setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public SysUserLoginLog setLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SysUserLoginLog setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public SysUserLoginLog setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public SysUserLoginLog setBrowser(String browser) {
        this.browser = browser;
        return this;
    }
}
