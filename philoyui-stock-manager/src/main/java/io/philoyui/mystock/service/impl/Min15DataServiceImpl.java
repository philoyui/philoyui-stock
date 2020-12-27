package io.philoyui.mystock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.mystock.dao.Min15DataDao;
import io.philoyui.mystock.entity.Min15DataEntity;
import io.philoyui.mystock.service.Min15DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DELL
 */
@Component
public class Min15DataServiceImpl extends GenericServiceImpl<Min15DataEntity,Long> implements Min15DataService {

    @Autowired
    private Min15DataDao min15DataDao;

    @Override
    protected GenericDao<Min15DataEntity, Long> getDao() {
        return min15DataDao;
    }

    @Override
    public void insertAll(List<Min15DataEntity> min15DataEntityList) {
        min15DataDao.saveAll(min15DataEntityList);
    }

    @Override
    public void deleteAll() {
        min15DataDao.deleteAll();
    }
}
