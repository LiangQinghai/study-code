package cn.liangqinghai.study.jpa.hibernate;

import cn.liangqinghai.study.jpa.common.ContextHolder;
import cn.liangqinghai.study.jpa.model.BasePo;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * @author LiangQinghai
 * @Title PoListener
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 11:01
 */
public class PoListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void setTenantId(Object object) {

        if (object instanceof BasePo) {

            ((BasePo) object).setTenantCode(ContextHolder.tenantCode);

        }

    }

}
