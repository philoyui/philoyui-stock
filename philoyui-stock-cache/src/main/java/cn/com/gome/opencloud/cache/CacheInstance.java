package cn.com.gome.opencloud.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yangyu-ds on 2018/7/23.
 */
public class CacheInstance {

    private String cacheObjectName;

    private CacheAdaptor cacheAdaptor;

    public CacheInstance(CacheAdaptor cacheAdaptor, String cacheObjectName) {
        this.cacheAdaptor = cacheAdaptor;
        this.cacheObjectName = cacheObjectName;
    }


    public void initialize(CacheProperties cacheProperties) {
        cacheAdaptor.createCacheInstance(cacheProperties, cacheObjectName);
    }

    public <T> T cacheGet(String cacheKey, ExpendOperation<T> expendOperation){

        CacheObject<T> cacheObject = null;
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        cacheObject = cacheAdaptor.get(this.cacheObjectName, cacheKey);

        try {
            if(cacheObject == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                cacheObject = cacheAdaptor.get(this.cacheObjectName,cacheKey);

                try {
                    if(cacheObject == null) {
                        T obj = expendOperation.doOperate();
                        cacheObject = new CacheObject<>(obj);
                        cacheAdaptor.set(this.cacheObjectName,cacheKey,cacheObject);
                    }
                } finally {
                    readWriteLock.readLock().lock();
                    readWriteLock.writeLock().unlock();
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        return cacheObject.getObj();
    }

    public void setCacheObjectName(String cacheObjectName) {
        this.cacheObjectName = cacheObjectName;
    }
}
