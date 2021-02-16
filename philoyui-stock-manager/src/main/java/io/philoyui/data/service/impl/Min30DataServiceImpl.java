package io.philoyui.data.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.data.dao.Min30DataDao;
import io.philoyui.data.entity.Min30DataEntity;
import io.philoyui.data.service.Min30DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DELL
 */
@Component
public class Min30DataServiceImpl extends GenericServiceImpl<Min30DataEntity,Long> implements Min30DataService {

    @Autowired
    private Min30DataDao min30DataDao;

    @Override
    protected GenericDao<Min30DataEntity, Long> getDao() {
        return min30DataDao;
    }

    @Override
    public void insertAll(List<Min30DataEntity> min30DataEntityList) {
        min30DataDao.saveAll(min30DataEntityList);
    }

    @Override
    public void deleteAll() {
        min30DataDao.deleteAll();
    }
}
