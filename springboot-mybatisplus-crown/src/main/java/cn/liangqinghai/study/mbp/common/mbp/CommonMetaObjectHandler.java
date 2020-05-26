package cn.liangqinghai.study.mbp.common.mbp;

import cn.liangqinghai.study.mbp.utils.shiro.ShiroUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author LiangQinghai
 * @Title CommonMetaObjectHandler
 * @ProjectName study-code
 * @Description
 * @date 2020/5/26 19:56
 */
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "CREATE_TIME";

    private static final String UPDATE_TIME = "UPDATE_TIME";

    private static final String CREATE_BY = "CREATE_BY";

    private static final String UPDATE_BY = "UPDATE_BY";

    private static final String DELETED = "DELETED";

    @Override
    public void insertFill(MetaObject metaObject) {

        strictInsertFill(metaObject, DELETED, Boolean.class, false);
        strictInsertFill(metaObject, CREATE_TIME, Date.class, new Date());
        strictInsertFill(metaObject, CREATE_BY, String.class, this::currentLoginUserName);
        strictInsertFill(metaObject, UPDATE_TIME, Date.class, new Date());
        strictInsertFill(metaObject, UPDATE_BY, String.class, this::currentLoginUserName);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        strictInsertFill(metaObject, UPDATE_TIME, Date.class, new Date());
        strictInsertFill(metaObject, UPDATE_BY, String.class, this::currentLoginUserName);

    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    private String currentLoginUserName() {

        String user = "";

        try {
            user = ShiroUtil.getLoginName();
        } catch (Exception e) {

            log.error("Get user from shiro error, ignore this error.", e);

        }

        return user;

    }

}
