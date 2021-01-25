package cn.liangqinghai.study.jpa.model;

import cn.liangqinghai.study.jpa.hibernate.PoListener;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author LiangQinghai
 * @Title BasePo
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 10:51
 */
@FilterDef(name = "TENANT_FILTER", parameters = @ParamDef(name = "tenantCode", type = "long"))
@Filters(
        @Filter(name = "TENANT_FILTER", condition = BasePo.TENANT_CODE + " = :tenantCode")
)
@EntityListeners(PoListener.class)
@MappedSuperclass
public abstract class BasePo implements Serializable {

    private static final long serialVersionUID = 5136929727278311346L;

    public static final String TENANT_CODE = "TENANT_CODE";

    @Column(name = TENANT_CODE, columnDefinition = "BIGINT", length = 20)
    private Long tenantCode;

    public Long getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(Long tenantCode) {
        this.tenantCode = tenantCode;
    }
}
