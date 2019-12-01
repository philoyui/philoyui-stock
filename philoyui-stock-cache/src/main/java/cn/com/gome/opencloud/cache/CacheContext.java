package cn.com.gome.opencloud.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 缓存组件上下文服务
 *
 * Created by yangyu-ds on 2018/7/23.
 */
public class CacheContext {

    /**
     * 缓存业务key -> 缓存实例 CacheInstance
     */
    private Map<String,CacheInstance> cacheInstanceMap = new ConcurrentHashMap<>();

    /**
     * 注册的缓存组件，默认提供Guava本地缓存
     */
    private Map<String,CacheAdaptor> cacheAdaptorMap = new ConcurrentHashMap<>();

    /**
     * 注册缓存提供者服务
     * @param cacheObjectName
     * @param cacheInstance
     */
    public void registerCacheInstance(String cacheObjectName, CacheInstance cacheInstance) {
        cacheInstanceMap.put(cacheObjectName, cacheInstance);
    }

    /**
     * 根据指定组件ID获取缓存组件
     * @param identifier
     * @return
     */
    public CacheAdaptor getCacheComponent(String identifier) {
        return cacheAdaptorMap.get(identifier);
    }

    /**
     * 获取缓存组件
     * @param cacheObjectName
     * @return
     */
    public CacheInstance getCacheProvider(String cacheObjectName) {
        return cacheInstanceMap.get(cacheObjectName);
    }

    /**
     * 注册外部缓存组件
     * @param key
     * @param cacheAdaptor
     */
    public void registerCacheComponent(String key, CacheAdaptor cacheAdaptor) {
        cacheAdaptorMap.put(key, cacheAdaptor);
    }
}
