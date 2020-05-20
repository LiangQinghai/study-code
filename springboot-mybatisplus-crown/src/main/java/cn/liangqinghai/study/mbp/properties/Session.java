package cn.liangqinghai.study.mbp.properties;

import lombok.Data;

/**
 * @author LiangQinghai
 * @Title Session
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 18:00
 */
@Data
public class Session {

    private int expireTime;

    private int dbSyncPeriod;

    private int validationInterval;

    private int maxSession;

    private boolean kickoutAfter;

}
