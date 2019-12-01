package cn.com.gome.opencloud.cache;

import java.io.Serializable;

/**
 * Created by yangyu-ds on 2018/1/17.
 */
public class CacheObject<T> implements Serializable {

    private T obj;

    private Class clazz;

    public CacheObject() {
        //
    }

    public CacheObject(T obj) {
        this.obj = obj;
        if(obj!=null){
            this.clazz = obj.getClass();
        }
    }

    public T getObj() {
        return this.obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

}
