package cn.com.gome.opencloud.cache;

/**
 * Created by yangyu-ds on 2018/7/23.
 */
public class CacheProperties {

    /**
     * 缓存类型
     */
    private CacheType cacheType;

    /**
     * 缓存对象名称
     */
    private String cacheObjectName;

    /**
     * 过期秒数
     */
    private int expireSecond;

    /**
     * 初始个数
     */
    private int initSize;

    /**
     * 最大个数
     */
    private int maxSize;

    public static CacheProperties newCacheProperties() {
        return new CacheProperties();
    }

    public CacheProperties withCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
        return this;
    }

    public CacheProperties withExpireSecond(int expireSecond) {
        this.expireSecond = expireSecond;
        return this;
    }

    public CacheProperties withInitSize(int initSize) {
        this.initSize = initSize;
        return this;
    }

    public CacheProperties withMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public CacheProperties withCacheObjectName(String cacheObjectName) {
        this.cacheObjectName = cacheObjectName;
        return this;
    }

    public int getExpireSecond() {
        return expireSecond;
    }

    public int getInitSize() {
        return initSize;
    }


    public int getMaxSize() {
        return maxSize;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public String getCacheObjectName() {
        return cacheObjectName;
    }

    public void setCacheObjectName(String cacheObjectName) {
        this.cacheObjectName = cacheObjectName;
    }
}
