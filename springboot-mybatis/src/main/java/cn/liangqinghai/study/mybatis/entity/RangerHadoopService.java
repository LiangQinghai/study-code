package cn.liangqinghai.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@TableName("t_permission_ranger_hadoop_service")
public class RangerHadoopService implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String type;

    private String description;

    private String configs;

    private Long rangerServiceId;

    private String rangerServiceName;

    private String guid;

    private Long tenantId;

    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;

    private Integer isEnabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getConfigs() {
        return configs;
    }

    public void setConfigs(String configs) {
        this.configs = configs;
    }
    public Long getRangerServiceId() {
        return rangerServiceId;
    }

    public void setRangerServiceId(Long rangerServiceId) {
        this.rangerServiceId = rangerServiceId;
    }
    public String getRangerServiceName() {
        return rangerServiceName;
    }

    public void setRangerServiceName(String rangerServiceName) {
        this.rangerServiceName = rangerServiceName;
    }
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "RangerHadoopService{" +
            "id=" + id +
            ", name=" + name +
            ", type=" + type +
            ", description=" + description +
            ", configs=" + configs +
            ", rangerServiceId=" + rangerServiceId +
            ", rangerServiceName=" + rangerServiceName +
            ", guid=" + guid +
            ", tenantId=" + tenantId +
            ", createUser=" + createUser +
            ", createTime=" + createTime +
            ", updateUser=" + updateUser +
            ", updateTime=" + updateTime +
            ", isEnabled=" + isEnabled +
        "}";
    }
}
