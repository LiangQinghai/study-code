package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysRole
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 17:56
 */
@TableName("SYS_ROLE")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 5968677679255088542L;

    @TableId
    private Long id;

    @TableField
    private String roleName;

    @TableField
    private String remark;

    @TableField
    private Long createTime;

    @TableField(exist = false)
    private List<Long> menuList;

    public Long getId() {
        return id;
    }

    public SysRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SysRole setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public SysRole setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<Long> getMenuList() {
        return menuList;
    }

    public SysRole setMenuList(List<Long> menuList) {
        this.menuList = menuList;
        return this;
    }
}
