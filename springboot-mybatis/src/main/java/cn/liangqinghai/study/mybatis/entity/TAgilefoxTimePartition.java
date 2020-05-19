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
@TableName("T_AGILEFOX_TIME_PARTITION")
@ApiModel(value="TAgilefoxTimePartition对象", description="")
public class TAgilefoxTimePartition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "T_AGILEFOX_AREA的主键，所属业务板块")
    @TableField("AREA_ID")
    private Long areaId;

    @ApiModelProperty(value = "中文名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "英文名")
    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "数据类型")
    @TableField("FIELD_TYPE")
    private String fieldType;

    @ApiModelProperty(value = "默认值")
    @TableField("DEFAULT_VALUE")
    private String defaultValue;

    @ApiModelProperty(value = "描述")
    @TableField("REMARK")
    private String remark;

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

    @ApiModelProperty(value = "租户id")
    @TableField("APP_ID")
    private Long appId;

    @ApiModelProperty(value = "所属行业")
    @TableField("INDUSTRY_ID")
    private Long industryId;

    @ApiModelProperty(value = "所属项目")
    @TableField("PROJECT_ID")
    private Long projectId;


}