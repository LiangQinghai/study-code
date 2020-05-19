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
@TableName("T_AGILEFOX_FACT_TABLE_FIELD_MAPPING")
@ApiModel(value="TAgilefoxFactTableFieldMapping对象", description="")
public class TAgilefoxFactTableFieldMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "事实表id")
    @TableField("FACT_TABLE_ID")
    private Long factTableId;

    @ApiModelProperty(value = "属性或者度量id")
    @TableField("ATTR_ID")
    private Long attrId;

    @ApiModelProperty(value = "来源类型")
    @TableField("SOURCE_TYPE")
    private String sourceType;

    @ApiModelProperty(value = "来源字段")
    @TableField("SOURCE_FIELD")
    private String sourceField;

    @ApiModelProperty(value = "来源字段类型")
    @TableField("SOURCE_FIELD_TYPE")
    private String sourceFieldType;

    @ApiModelProperty(value = "来源字段名称")
    @TableField("SOURCE_FIELD_NAME")
    private String sourceFieldName;

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


}
