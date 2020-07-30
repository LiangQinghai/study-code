package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @Title SysRoleMenu
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:18
 */
@TableName("SYS_ROLE_MENU")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -1168118158696786006L;

    @TableId
    private Long id;

    @TableField
    private Long roleId;

    @TableField
    private Long menuId;

    public Long getId() {
        return id;
    }

    public SysRoleMenu setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public SysRoleMenu setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getMenuId() {
        return menuId;
    }

    public SysRoleMenu setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }
}
