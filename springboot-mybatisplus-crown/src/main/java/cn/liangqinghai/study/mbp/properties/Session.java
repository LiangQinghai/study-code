package cn.liangqinghai.study.mbp.properties;


/**
 * @author LiangQinghai
 * @Title Session
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 18:00
 */
public class Session {

    private int expireTime;

    private int dbSyncPeriod;

    private int validationInterval;

    private int maxSession;

    private boolean kickoutAfter;

    public int getExpireTime() {
        return expireTime;
    }

    public Session setExpireTime(int expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public int getDbSyncPeriod() {
        return dbSyncPeriod;
    }

    public Session setDbSyncPeriod(int dbSyncPeriod) {
        this.dbSyncPeriod = dbSyncPeriod;
        return this;
    }

    public int getValidationInterval() {
        return validationInterval;
    }

    public Session setValidationInterval(int validationInterval) {
        this.validationInterval = validationInterval;
        return this;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public Session setMaxSession(int maxSession) {
        this.maxSession = maxSession;
        return this;
    }

    public boolean isKickoutAfter() {
        return kickoutAfter;
    }

    public Session setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
        return this;
    }
}
