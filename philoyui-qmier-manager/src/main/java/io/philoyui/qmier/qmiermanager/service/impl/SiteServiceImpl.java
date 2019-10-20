package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.SiteDao;
import io.philoyui.qmier.qmiermanager.entity.SiteEntity;
import io.philoyui.qmier.qmiermanager.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteServiceImpl extends GenericServiceImpl<SiteEntity,Long> implements SiteService {

    @Autowired
    private SiteDao siteDao;


    @Override
    protected GenericDao<SiteEntity, Long> getDao() {
        return siteDao;
    }

    @Override
    public Object findByReferId(String referId) {
        return siteDao.getOne(Long.parseLong(referId));
    }

    @Override
    public String getDomainName() {
        return "site_category";
    }

    @Override
    public String getDomainChineseName() {
        return "版区";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<SiteEntity> findAll() {
        return siteDao.findAll();
    }
}
