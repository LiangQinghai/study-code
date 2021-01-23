package cn.liangqinghai.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@TableName("T_DW_ACCESS_LOG")
public class AccessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    @TableField("DATE")
    private LocalDateTime date;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 访问来源IP
     */
    @TableField("IP")
    private String ip;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 访问方法
     */
    @TableField("METHOD")
    private String method;

    /**
     * 参数
     */
    @TableField("PARAMS")
    private String params;

    /**
     * 结果
     */
    @TableField("RESULT")
    private String result;

    /**
     * 状态
     */
    @TableField("STATUS")
    private String status;

    /**
     * 来源
     */
    @TableField("SOURCE")
    private String source;

    /**
     * 租户id
     */
    @TableField("APP_ID")
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
            "id=" + id +
            ", date=" + date +
            ", userName=" + userName +
            ", ip=" + ip +
            ", type=" + type +
            ", method=" + method +
            ", params=" + params +
            ", result=" + result +
            ", status=" + status +
            ", source=" + source +
            ", appId=" + appId +
        "}";
    }
}
