package cn.com.gome.opencloud.cache.guava;

import cn.com.gome.opencloud.cache.CacheAdaptor;
import cn.com.gome.opencloud.cache.CacheObject;
import cn.com.gome.opencloud.cache.CacheProperties;
import cn.com.gome.opencloud.cache.exp.CacheException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangyu-ds on 2018/7/23.
 */
public class GuavaCacheAdaptor implements CacheAdaptor {

    private Map<String,Cache<String,CacheObject>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void createCacheInstance(CacheProperties cacheProperties, String cacheObjectName) {
        Cache<String,CacheObject> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(cacheProperties.getExpireSecond(), TimeUnit.SECONDS)
                .initialCapacity(cacheProperties.getInitSize())
                .maximumSize(cacheProperties.getMaxSize())
                .build();

        cacheMap.put(cacheObjectName,loadingCache);
    }

    @Override
    public CacheObject get(String cacheObjectName, String cacheKey) {

        Cache<String, CacheObject> loadingCache = cacheMap.get(cacheObjectName);

        if(loadingCache==null){
            throw new CacheException("为创建当前业务Key的缓存：" + cacheObjectName);
        }

        return loadingCache.getIfPresent(cacheKey);
    }

    @Override
    public void set(String cacheObjectName, String cacheKey, CacheObject cacheObject) {

        Cache<String, CacheObject> loadingCache = cacheMap.get(cacheObjectName);

        if(loadingCache==null){
            throw new CacheException("为创建当前业务Key的缓存：" + cacheObjectName);
        }

        loadingCache.put(cacheKey,cacheObject);
    }


}
