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
@TableName("T_AGILEFOX_PROJECT")
@ApiModel(value="TAgilefoxProject对象", description="")
public class TAgilefoxProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目英文名")
    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "项目中文名")
    @TableField("CH_NAME")
    private String chName;

    @ApiModelProperty(value = "描述")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "业务板块")
    @TableField("BUSINESS_AREA_ID")
    private String businessAreaId;

    @ApiModelProperty(value = "空间类型")
    @TableField("SPACE_TYPE")
    private String spaceType;

    @ApiModelProperty(value = "所属行业")
    @TableField("INDUSTRY_ID")
    private Long industryId;

    @ApiModelProperty(value = "当前状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField("LAST_MODIFIED_USER")
    private String lastModifiedUser;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty(value = "appID")
    @TableField("APP_ID")
    private Long appId;

    @ApiModelProperty(value = "数据源ID")
    @TableField("DATASOURCE_ID")
    private Long datasourceId;


}
