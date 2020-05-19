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
@TableName("T_AGILEFOX_DATA_RELATION")
@ApiModel(value="TAgilefoxDataRelation对象", description="")
public class TAgilefoxDataRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据依赖关系对应")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "依赖的表名")
    @TableField("DEPEND_TABLE")
    private String dependTable;

    @ApiModelProperty(value = "依赖的ID")
    @TableField("DEPEND_ID")
    private Long dependId;

    @ApiModelProperty(value = "被依赖的表名")
    @TableField("DEPENDED_ON_TABLE")
    private String dependedOnTable;

    @ApiModelProperty(value = "被依赖的ID")
    @TableField("DEPENDED_ON_ID")
    private Long dependedOnId;

    @ApiModelProperty(value = "创建者")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField("LAST_MODIFIED_USER")
    private String lastModifiedUser;

    @ApiModelProperty(value = "最后修改日期")
    @TableField("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty(value = "应用ID")
    @TableField("APP_ID")
    private Long appId;


}
