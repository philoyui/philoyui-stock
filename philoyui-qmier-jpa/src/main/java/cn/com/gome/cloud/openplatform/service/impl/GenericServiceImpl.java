package cn.com.gome.cloud.openplatform.service.impl;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.domain.CustomSpecification;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by yangyu-ds on 2016/9/21.
 */
public abstract class GenericServiceImpl<T extends Serializable, D extends Serializable> implements GenericService<T, D> {

    protected abstract GenericDao<T,D> getDao();

    @Override
    public long count(SearchFilter searchFilter) {

        CustomSpecification customSpecification = new CustomSpecification(searchFilter);

        return getDao().count(customSpecification);
    }

    @Override
    public List<T> list(SearchFilter searchFilter) {

        List<Sort.Order> sortOrders = new ArrayList<>();

        for (Order order : searchFilter.getOrders()) {
            if(order.isDesc()){
                sortOrders.add(new Sort.Order(Sort.Direction.DESC,order.getAttr()));
            }else{
                sortOrders.add(new Sort.Order(Sort.Direction.ASC,order.getAttr()));
            }
        }

        CustomSpecification customSpecification = new CustomSpecification(searchFilter);

        if(sortOrders.isEmpty()){
            return getDao().findAll(customSpecification);
        }else{
            return getDao().findAll(customSpecification,new Sort(sortOrders));
        }

    }

    @Override
    public PageObject<T> paged(SearchFilter searchFilter) {

        List<Sort.Order> sortOrders = new ArrayList<>();

        for (Order order : searchFilter.getOrders()) {
            if(order.isDesc()){
                sortOrders.add(new Sort.Order(Sort.Direction.DESC,order.getAttr()));
            }else{
                sortOrders.add(new Sort.Order(Sort.Direction.ASC,order.getAttr()));
            }
        }

        PageRequest pageRequest;

        if(sortOrders.isEmpty()){
            pageRequest = new PageRequest(searchFilter.getPageNo(), searchFilter.getPageSize());
        }else{
            pageRequest = new PageRequest(searchFilter.getPageNo(), searchFilter.getPageSize(),new Sort(sortOrders));
        }

        CustomSpecification<T> customSpecification = new CustomSpecification<>(searchFilter);
        Page page = getDao().findAll(customSpecification, pageRequest);

        PageObject<T> pageObject = new PageObject<>();
        pageObject.setFirst(page.isFirst());
        pageObject.setLast(page.isLast());
        pageObject.setContent(page.getContent());
        pageObject.setNumber(page.getNumber());
        pageObject.setNumberOfElements(page.getNumberOfElements());
        pageObject.setSize(page.getSize());
        pageObject.setTotalPages(page.getTotalPages());
        pageObject.setHasNext(page.hasNext());
        pageObject.setHasPrevious(page.hasPrevious());
        pageObject.setTotalElements(page.getTotalElements());
        return pageObject;

    }

    @Override
    public T get(SearchFilter searchFilter) {
        T entity = null;
        CustomSpecification customSpecification = new CustomSpecification(searchFilter);
        List<T> all = getDao().findAll(customSpecification);
        if(!all.isEmpty()){
            entity = all.get(0);
        }
        return entity;
    }

    @Override
    public T insert(T t) {
        return getDao().save(t);
    }

    @Override
    public T update(T t) {
        return getDao().save(t);
    }

    @Override
    public void delete(D id) {
        getDao().deleteById(id);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        getDao().deleteAll(entities);
    }

    @Override
    public T get(D id) {
        Optional<T> option = getDao().findById(id);
        return option.orElse(null);
    }
}
