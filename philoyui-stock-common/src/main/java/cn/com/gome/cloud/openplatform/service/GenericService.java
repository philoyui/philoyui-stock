package cn.com.gome.cloud.openplatform.service;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/21.
 */
public interface GenericService<T extends Serializable, D extends Serializable> {

    /**
     * 查询总数
     * @param searchFilter
     * @return
     */
    long count(SearchFilter searchFilter);

    /**
     * 查询列表
     * @param searchFilter
     * @return
     */
    List<T> list(SearchFilter searchFilter);

    /**
     * 分页查询
     * @param searchFilter
     * @return
     */
    PageObject<T> paged(SearchFilter searchFilter);

    /**
     * 新增
     * @param t
     */
    T insert(T t);

    /**
     * 更新
     * @param t
     */
    T update(T t);

    /**
     * 批量删除
     */
    void delete(Iterable<? extends T> entities);

    /**
     * 删除
     */
    void delete(D id);

    /**
     * 获取单个对象
     * @param searchFilter
     * @return
     */
    T get(SearchFilter searchFilter);

    /**
     * 获取单个对象
     * @param id
     * @return
     */
    T get(D id);

}
