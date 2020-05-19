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
@TableName("T_AGILEFOX_DIMENSION_TABLE_ATTR_RELATION")
@ApiModel(value="TAgilefoxDimensionTableAttrRelation对象", description="")
public class TAgilefoxDimensionTableAttrRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字段来源id，当来源类型为TABLEFIELD的时候，该字段不能为空")
    @TableField("FIELD_MAPPING_ID")
    private Long fieldMappingId;

    @ApiModelProperty(value = "所属维表Id")
    @TableField("DIMENSION_TABLE_ID")
    private Long dimensionTableId;

    @ApiModelProperty(value = "字段引入来源类型，目前有sql引入和字段引入")
    @TableField("SOURCE_TYPE")
    private String sourceType;

    @ApiModelProperty(value = "字段类型")
    @TableField("FIELD_TYPE")
    private String fieldType;

    @ApiModelProperty(value = "字段名称")
    @TableField("FIELD_NAME")
    private String fieldName;

    @ApiModelProperty(value = "字段描述（对应界面字段名称）")
    @TableField("FIELD_DESC")
    private String fieldDesc;

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

    @ApiModelProperty(value = "所属项目")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "所属行业")
    @TableField("INDUSTRY_ID")
    private Long industryId;

    @ApiModelProperty(value = "所属业务板块")
    @TableField("AREA_ID")
    private Long areaId;


}
