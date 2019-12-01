package cn.com.gome.opencloud.cache;

/**
 * Created by yangyu-ds on 2018/7/23.
 */
public enum CacheType {

    GUAVA("guava"),GCACHE("gcache");

    String identifier;

    CacheType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

}
