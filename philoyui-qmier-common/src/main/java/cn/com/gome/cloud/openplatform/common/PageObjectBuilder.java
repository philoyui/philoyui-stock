package cn.com.gome.cloud.openplatform.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyu-ds on 2018/9/24.
 */
public class PageObjectBuilder {

    private PageObjectBuilder() {
        //
    }

    public static <K extends Serializable,V extends Serializable> PageObject<V> convert(PageObject<K> pageObject, List<V> entities) {
        PageObject<V> vPageObject = new PageObject<>();
        vPageObject.setContent(entities);
        vPageObject.setTotalElements(pageObject.getTotalElements());
        vPageObject.setTotalPages(pageObject.getTotalPages());
        vPageObject.setHasPrevious(pageObject.hasPrevious());
        vPageObject.setHasNext(pageObject.isHasNext());
        vPageObject.setSize(pageObject.getSize());
        vPageObject.setNumberOfElements(pageObject.getNumberOfElements());
        vPageObject.setLast(pageObject.isLast());
        vPageObject.setFirst(pageObject.isFirst());
        return vPageObject;
    }


    public static <K extends Serializable,V extends Serializable> PageObject<V> convert(List<V> entities, int totalCount, int pageNo) {

        int pageSize = entities.size();
        int pageCount = pageSize>0?(totalCount+pageSize-1)/pageSize:1;

        PageObject<V> vPageObject = new PageObject<>();
        vPageObject.setContent(entities);
        vPageObject.setTotalElements(totalCount);
        vPageObject.setTotalPages(pageCount);
        vPageObject.setHasPrevious(pageSize!=1);
        vPageObject.setHasNext(pageNo >= pageCount);
        vPageObject.setSize(pageSize);
        vPageObject.setNumberOfElements(pageSize);
        vPageObject.setLast(pageNo >= pageCount);
        vPageObject.setFirst(pageSize!=1);

        return vPageObject;
    }
}
