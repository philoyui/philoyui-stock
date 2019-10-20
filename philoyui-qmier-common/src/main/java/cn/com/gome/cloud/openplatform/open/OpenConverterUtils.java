package cn.com.gome.cloud.openplatform.open;

import cn.com.gome.cloud.openplatform.common.PageObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu-ds on 2018/9/27.
 */
public class OpenConverterUtils {


    /**
     *
     * 数据库PageObject -> 开放对象PageObject
     *
     * @param pageObject
     * @param <K>
     * @param <V>
     * @return
     */
    public  static <K extends OpenConvertible<V>,V extends Serializable> PageObject<V> convertPageObject(PageObject<K> pageObject) {
        List<V> vList = new ArrayList<>();
        for (K k : pageObject.getContent()) {
            vList.add(k.converter());
        }
        PageObject<V> vPageObject = new PageObject<>();
        vPageObject.setContent(vList);
        vPageObject.setTotalElements(pageObject.getTotalElements());
        vPageObject.setTotalPages(pageObject.getTotalPages());
        vPageObject.setHasPrevious(pageObject.hasPrevious());
        vPageObject.setHasNext(pageObject.isHasNext());
        vPageObject.setSize(pageObject.getSize());
        vPageObject.setNumberOfElements(pageObject.getNumberOfElements());
        vPageObject.setLast(pageObject.isLast());
        vPageObject.setFirst(pageObject.isFirst());
        vPageObject.setNumber(pageObject.getNumber());
        return vPageObject;
    }

    /**
     * 数据库对象改成开放对象
     * @param k
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K extends OpenConvertible<V>,V extends Serializable> V convertObject(K k) {
        if(k==null){
            return null;
        }
        return k.converter();
    }

    public  static <K extends OpenConvertible<V>,V extends Serializable> List<V> convertList(List<K> entities) {
        List<V> vList = new ArrayList<>();
        for (K k : entities) {
            vList.add(k.converter());
        }
        return vList;
    }
}
