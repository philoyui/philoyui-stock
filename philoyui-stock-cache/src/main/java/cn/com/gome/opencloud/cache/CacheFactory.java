package cn.com.gome.opencloud.cache;

import cn.com.gome.opencloud.cache.exp.CacheException;
import cn.com.gome.opencloud.cache.guava.GuavaCacheAdaptor;

import java.util.Map;

/**
 * Created by yangyu-ds on 2018/7/23.
 */
public class CacheFactory {

    private CacheContext cacheContext = new CacheContext();

    /**
     * 启动初始化
     */
    public void start(){

//        cacheContext = new CacheContext();
        cacheContext.registerCacheComponent(CacheType.GUAVA.getIdentifier(),new GuavaCacheAdaptor());

    }

    /**
     *
     * 创建为每个业务键值分配缓存服务
     *
     * @param cacheProperties
     * @return
     */
    public CacheInstance createCacheInstance(CacheProperties cacheProperties) {

        checkExistCacheObject(cacheProperties.getCacheObjectName());

        CacheAdaptor cacheAdaptor = getCacheComponent(cacheProperties.getCacheType());

        CacheInstance cacheInstance = new CacheInstance(cacheAdaptor,cacheProperties.getCacheObjectName());

        cacheContext.registerCacheInstance(cacheProperties.getCacheObjectName(), cacheInstance);

        cacheInstance.initialize(cacheProperties);

        return cacheInstance;

    }

    /**
     * 缓存组价
     * @return
     */
    private CacheAdaptor getCacheComponent(CacheType cacheType) {

        CacheAdaptor cacheAdaptor = cacheContext.getCacheComponent(cacheType.getIdentifier());

        if(cacheAdaptor ==null){
            throw new CacheException("未设置指定类型的缓存组件："+ cacheType.getIdentifier());
        }

        return cacheAdaptor;
    }

    /**
     * 校验是否存在业务KEY
     * @param cacheObjectName
     */
    private void checkExistCacheObject(String cacheObjectName) {

        CacheInstance cacheInstance = cacheContext.getCacheProvider(cacheObjectName);

        if(cacheInstance !=null){
            throw new CacheException("存在重复的缓存业务KEY设置：" + cacheObjectName);
        }

    }

    public void setCustomCacheAdaptor(Map<String,CacheAdaptor> extraAdaptor){
        for (Map.Entry<String, CacheAdaptor> stringCacheComponentEntry : extraAdaptor.entrySet()) {
            cacheContext.registerCacheComponent(stringCacheComponentEntry.getKey(),stringCacheComponentEntry.getValue());
        }
    }

}
