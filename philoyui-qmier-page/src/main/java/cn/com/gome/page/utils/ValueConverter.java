package cn.com.gome.page.utils;

import cn.com.gome.cloud.openplatform.common.PageObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * OpenConverterUtils cannot convert received value objects.
 *
 * @author Zhang Junshu @gome.com.cn
 * @since 2018-11-29 3:39 PM
 */
public class ValueConverter {

    public static <R extends Serializable, V extends Serializable> PageObject<R> convertPageObject(PageObject<V> sourcePageObject, Function<V, R> converter) {

        List<R> resultList = new ArrayList<>();

        for (V value : sourcePageObject.getContent()) {
            resultList.add(converter.apply(value));
        }

        PageObject<R> resultPageObject = new PageObject<>();
        resultPageObject.setContent(resultList);
        resultPageObject.setTotalElements(sourcePageObject.getTotalElements());
        resultPageObject.setTotalPages(sourcePageObject.getTotalPages());
        resultPageObject.setHasPrevious(sourcePageObject.hasPrevious());
        resultPageObject.setHasNext(sourcePageObject.isHasNext());
        resultPageObject.setSize(sourcePageObject.getSize());
        resultPageObject.setNumberOfElements(sourcePageObject.getNumberOfElements());
        resultPageObject.setLast(sourcePageObject.isLast());
        resultPageObject.setFirst(sourcePageObject.isFirst());
        resultPageObject.setNumber(sourcePageObject.getNumber());

        return resultPageObject;
    }


    public static <R, V> R convertValue(V value, Function<V, R> converter) {
        return converter.apply(value);
    }
}
