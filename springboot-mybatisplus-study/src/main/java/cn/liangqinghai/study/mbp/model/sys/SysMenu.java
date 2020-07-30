package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysMenu
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:14
 */
@TableName("SYS_MENU")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -2396975235287001183L;

    @TableId
    private Long id;

    @TableField
    private Long parentId;

    @TableField
    private String parentName;

    @TableField
    private String name;

    @TableField
    private String url;

    @TableField
    private String perms;

    /**
     * 0: 目录
     * 1: 菜单
     * 2: 按钮
     */
    @TableField
    private Integer type;

    @TableField
    private Integer orderNum;

    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<?> list;

    public Long getId() {
        return id;
    }

    public SysMenu setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public SysMenu setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getParentName() {
        return parentName;
    }

    public SysMenu setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysMenu setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SysMenu setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPerms() {
        return perms;
    }

    public SysMenu setPerms(String perms) {
        this.perms = perms;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SysMenu setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public SysMenu setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public Boolean getOpen() {
        return open;
    }

    public SysMenu setOpen(Boolean open) {
        this.open = open;
        return this;
    }

    public List<?> getList() {
        return list;
    }

    public SysMenu setList(List<?> list) {
        this.list = list;
        return this;
    }
}
