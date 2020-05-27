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
 * @Title SysRole
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 17:56
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
