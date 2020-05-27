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

/**
 * @author LiangQinghai
 * @Title SysRoleMenu
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:18
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("SYS_ROLE_MENU")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -1168118158696786006L;

    @TableId
    private Long id;

    @TableField
    private Long roleId;

    @TableField
    private Long menuId;

}
