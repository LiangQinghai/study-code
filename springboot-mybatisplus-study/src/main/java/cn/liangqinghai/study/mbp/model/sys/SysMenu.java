package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysMenu
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:14
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
