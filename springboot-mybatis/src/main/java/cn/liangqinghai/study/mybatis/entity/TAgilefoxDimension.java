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
@TableName("T_AGILEFOX_DIMENSION")
@ApiModel(value="TAgilefoxDimension对象", description="")
public class TAgilefoxDimension implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据域id")
    @TableField("DOMAIN_ID")
    private Long domainId;

    @ApiModelProperty(value = "维度名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "维度英文名称")
    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "维度类型")
    @TableField("DIMENSION_TYPE")
    private String dimensionType;

    @ApiModelProperty(value = "主键中文名")
    @TableField("PK_FIELD_NAME")
    private String pkFieldName;

    @ApiModelProperty(value = "主键英文名")
    @TableField("PK_FIELD_EN_NAME")
    private String pkFieldEnName;

    @ApiModelProperty(value = "主键类型")
    @TableField("PK_TYPE")
    private String pkType;

    @ApiModelProperty(value = "描述")
    @TableField("DESC")
    private String desc;

    @ApiModelProperty(value = "当前状态")
    @TableField("STATUS")
    private String status;

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

    @TableField("APP_ID")
    private Long appId;

    @ApiModelProperty(value = "所属项目")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "所属业务板块")
    @TableField("AREA_ID")
    private Long areaId;


}
