package cn.liangqinghai.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@TableName("t_permission_tenant_resource")
public class TenantResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * hdfs path
     */
    private String hdfsPath;

    /**
     * hive数据库列表
     */
    private String hiveDbs;

    /**
     * yarn队列
     */
    private String yarnQueue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public String getHdfsPath() {
        return hdfsPath;
    }

    public void setHdfsPath(String hdfsPath) {
        this.hdfsPath = hdfsPath;
    }
    public String getHiveDbs() {
        return hiveDbs;
    }

    public void setHiveDbs(String hiveDbs) {
        this.hiveDbs = hiveDbs;
    }
    public String getYarnQueue() {
        return yarnQueue;
    }

    public void setYarnQueue(String yarnQueue) {
        this.yarnQueue = yarnQueue;
    }

    @Override
    public String toString() {
        return "TenantResource{" +
            "id=" + id +
            ", tenantId=" + tenantId +
            ", hdfsPath=" + hdfsPath +
            ", hiveDbs=" + hiveDbs +
            ", yarnQueue=" + yarnQueue +
        "}";
    }
}
