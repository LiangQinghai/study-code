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
 * @Title SysUserLoginLog
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

}
