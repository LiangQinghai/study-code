package cn.liangqinghai.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("T_AGILEFOX_WHITELISTS")
@ApiModel(value="TAgilefoxWhitelists对象", description="")
public class TAgilefoxWhitelists implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "IP地址")
    @TableField("IP_STR")
    private String ipStr;

    @ApiModelProperty(value = "端口号")
    @TableField("PORT")
    private Integer port;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private String createTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField("LAST_MODIFIED_USER")
    private String lastModifiedUser;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("LAST_MODIFIED_TIME")
    private String lastModifiedTime;

    @ApiModelProperty(value = "app_id")
    @TableField("APP_ID")
    private Long appId;


}
