package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.SiteCategoryDao;
import io.philoyui.qmier.qmiermanager.entity.SiteCategoryEntity;
import io.philoyui.qmier.qmiermanager.service.SiteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteCategoryServiceImpl extends GenericServiceImpl<SiteCategoryEntity,Long> implements SiteCategoryService {

    @Autowired
    private SiteCategoryDao siteCategoryDao;

    @Override
    protected GenericDao<SiteCategoryEntity, Long> getDao() {
        return siteCategoryDao;
    }

    @Override
    public List<SiteCategoryEntity> findAllEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        return this.list(searchFilter);
    }

    @Override
    public Object findByReferId(String referId) {
        return siteCategoryDao.getOne(Long.parseLong(referId));
    }

    @Override
    public String getDomainName() {
        return "site_category";
    }

    @Override
    public String getDomainChineseName() {
        return "板块";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<SiteCategoryEntity> findAll() {
        return siteCategoryDao.findAll();
    }
}
