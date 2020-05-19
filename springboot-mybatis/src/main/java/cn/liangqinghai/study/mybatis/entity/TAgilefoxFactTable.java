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
@TableName("T_AGILEFOX_FACT_TABLE")
@ApiModel(value="TAgilefoxFactTable对象", description="")
public class TAgilefoxFactTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "业务板块id")
    @TableField("INDUSTRY_ID")
    private Long industryId;

    @ApiModelProperty(value = "数据域id")
    @TableField("AREA_ID")
    private Long areaId;

    @ApiModelProperty(value = "业务过程id")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "中文名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "英文名")
    @TableField("EN_NAME")
    private String enName;

    @ApiModelProperty(value = "事实表明细类型")
    @TableField("FACT_TABLE_TYPE")
    private String factTableType;

    @ApiModelProperty(value = "来源表")
    @TableField("SOURCE_TABLE")
    private String sourceTable;

    @ApiModelProperty(value = "事实表过滤条件")
    @TableField("FILTER")
    private String filter;

    @ApiModelProperty(value = "是否自动产生主键")
    @TableField("AUTO_CREATE_PK")
    private Boolean autoCreatePk;

    @ApiModelProperty(value = "事实表描述")
    @TableField("DESC")
    private String desc;

    @ApiModelProperty(value = "状态")
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

    @ApiModelProperty(value = "租户id")
    @TableField("APP_ID")
    private Long appId;


}
