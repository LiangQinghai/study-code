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
@TableName("t_permission_data_ranger_operation")
public class DataRangerOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作权限id
     */
    private Long operationId;

    /**
     * 操作权限hash
     */
    private String operationHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }
    public String getOperationHash() {
        return operationHash;
    }

    public void setOperationHash(String operationHash) {
        this.operationHash = operationHash;
    }

    @Override
    public String toString() {
        return "DataRangerOperation{" +
            "id=" + id +
            ", operationId=" + operationId +
            ", operationHash=" + operationHash +
        "}";
    }
}
