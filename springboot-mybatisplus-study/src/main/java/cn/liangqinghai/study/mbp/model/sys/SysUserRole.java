package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @Title SysUserRole
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 17:59
 */
@TableName("SYS_USER_ROLE")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = -1882998001141014102L;

    @TableId
    private Long id;

    @TableField
    private Long userId;

    @TableField
    private Long roleId;

    public Long getId() {
        return id;
    }

    public SysUserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SysUserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public SysUserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
}
