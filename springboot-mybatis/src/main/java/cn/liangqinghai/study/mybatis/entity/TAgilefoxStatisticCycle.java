package cn.liangqinghai.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Liang
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_AGILEFOX_STATISTIC_CYCLE")
@ApiModel(value="TAgilefoxStatisticCycle对象", description="")
public class TAgilefoxStatisticCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("NAME")
    private String name;

    @TableField("DESC")
    private String desc;

    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "开始时间类型")
    @TableField("START_TIME_TYPE")
    private String startTimeType;

    @ApiModelProperty(value = "表达式，两种类型公用")
    @TableField("START_EXP")
    private String startExp;

    @ApiModelProperty(value = "T_AGILEFOX_STATISTIC_FUNCTION_EXPRESSION'ID")
    @TableField("START_FUNCTION_EXP_ID")
    private Long startFunctionExpId;

    @ApiModelProperty(value = "结束时间类型")
    @TableField("STOP_TIME_TYPE")
    private String stopTimeType;

    @ApiModelProperty(value = "结束时间表达式，类型公用")
    @TableField("STOP_EXP")
    private String stopExp;

    @ApiModelProperty(value = "T_AGILEFOX_STATISTIC_FUNCTION_EXPRESSION'ID")
    @TableField("STOP_FUNCTION_EXP_ID")
    private Long stopFunctionExpId;

    @ApiModelProperty(value = "租户id")
    @TableField("APP_ID")
    private Long appId;

    @ApiModelProperty(value = "创建用户")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改用户")
    @TableField("LAST_MODIFIED_USER")
    private String lastModifiedUser;

    @ApiModelProperty(value = "修改时间")
    @TableField("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty(value = "所属行业")
    @TableField("INDUSTRY_ID")
    private Long industryId;

    @ApiModelProperty(value = "所属项目")
    @TableField("PROJECT_ID")
    private Long projectId;


}
