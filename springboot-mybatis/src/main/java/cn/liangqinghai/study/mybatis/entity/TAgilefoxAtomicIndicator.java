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
@TableName("T_AGILEFOX_ATOMIC_INDICATOR")
@ApiModel(value="TAgilefoxAtomicIndicator对象", description="")
public class TAgilefoxAtomicIndicator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "原子指标主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据域ID")
    @TableField("DATA_DOMAIN_ID")
    private Long dataDomainId;

    @ApiModelProperty(value = "原子指标英文名")
    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "原子指标中文名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "数据类型")
    @TableField("DATA_TYPE")
    private String dataType;

    @ApiModelProperty(value = "统计周期标识")
    @TableField("STATISTICCYCLE_ID")
    private Long statisticcycleId;

    @ApiModelProperty(value = "格式类型")
    @TableField("FORMAT_TYPE")
    private String formatType;

    @ApiModelProperty(value = "是否累加指标")
    @TableField("ADDITIVE")
    private Boolean additive;

    @ApiModelProperty(value = "计算逻辑")
    @TableField("FORMULA")
    private String formula;

    @ApiModelProperty(value = "状态")
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

    @ApiModelProperty(value = "应用ID")
    @TableField("APP_ID")
    private Long appId;

    @ApiModelProperty(value = "业务过程ID")
    @TableField("BPM_ID")
    private Long bpmId;

    @ApiModelProperty(value = "业务板块")
    @TableField("AREA_ID")
    private Long areaId;

    @ApiModelProperty(value = "所属行业")
    @TableField("INDUSTRY_ID")
    private Long industryId;


}
