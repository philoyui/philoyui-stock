package cn.com.gome.page.core;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;

import java.io.Serializable;
import java.util.List;

public abstract class PageService<T extends Serializable,ID extends Serializable> {

    protected PageConfig pageConfig;

    public abstract PageObject<T> paged(SearchFilter searchFilter);

    public PageConfig getPageConfig(){
        return pageConfig;
    }

    public PageConfig initPageConfig(PageContext pageContext) {
        this.pageConfig = initializePageConfig(pageContext);
        return pageConfig;
    }

    protected abstract PageConfig initializePageConfig(PageContext pageContext);

    public abstract T get(String id);

    public abstract T get(SearchFilter searchFilter);

    public void saveOrUpdate(T t){

    }

    public void delete(T t){

    }

    public void delete(List<T> entities){

    }

    public void enable(String id){

    }

    public void disable(String id){

    }

}