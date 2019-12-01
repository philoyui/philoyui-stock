package cn.com.gome.opencloud.cache;

/**
 *
 * 缓存组件适配器
 *
 * Created by yangyu-ds on 2018/7/23.
 */
public interface CacheAdaptor {

    /**
     *
     * 注册申请缓存服务
     *
     * @param cacheProperties
     * @param cacheObjectName
     */
    void createCacheInstance(CacheProperties cacheProperties, String cacheObjectName);

    /**
     * 获取缓存中数据
     *
     * @param cacheObjectName
     * @param cacheKey
     * @return
     */
    CacheObject get(String cacheObjectName, String cacheKey);

    /**
     * 设置缓存数据
     * @param cacheObjectName
     * @param cacheKey
     * @param cacheObject
     */
    void set(String cacheObjectName, String cacheKey, CacheObject cacheObject);

}
