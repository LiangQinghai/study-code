package cn.liangqinghai.study.mbp.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Title SysUser
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 17:46
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

}
