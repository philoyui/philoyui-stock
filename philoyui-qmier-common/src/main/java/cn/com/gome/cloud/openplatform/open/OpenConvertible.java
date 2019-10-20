package cn.com.gome.cloud.openplatform.open;

import java.io.Serializable;

/**
 *
 * 数据库对象转换成开放对象
 *
 * Created by yangyu-ds on 2018/9/27.
 */
public interface OpenConvertible<T> extends Serializable{

    T converter();

}
