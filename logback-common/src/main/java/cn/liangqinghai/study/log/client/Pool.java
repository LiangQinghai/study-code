package cn.liangqinghai.study.log.client;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author LiangQinghai
 * @Title Pool
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 11:01
 */
public abstract class Pool<T> implements Closeable {

    protected GenericObjectPool<T> pool;
    
    public Pool(){}
    
    public Pool(GenericObjectPoolConfig<T> config, PooledObjectFactory<T> factory) {
        
        config.setMinIdle(0);
        config.setMaxIdle(0);
        config.setMaxTotal(30);
        config.setMaxWaitMillis(1000);
        
        initPool(config, factory);
        
    }

    public void initPool(GenericObjectPoolConfig<T> config, PooledObjectFactory<T> factory) {

        if (this.pool != null) {
            closePool();
        }

        this.pool = new GenericObjectPool<T>(factory, config);

    }

    public void closePool() {
        pool.close();
    }

    public T getResource() {

        try {
            return pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    protected void returnResourceObject(T resource) {

        if (resource == null) {
            return;
        }

        pool.returnObject(resource);

    }

    protected void returnBrokenResourceObject(T res) {

        returnResourceObject(res);

    }

    public void returnResource(T res) {
        returnResourceObject(res);
    }

    public void destroy() {
        closePool();
    }

    @Override
    public void close() throws IOException {
        destroy();
    }
}
