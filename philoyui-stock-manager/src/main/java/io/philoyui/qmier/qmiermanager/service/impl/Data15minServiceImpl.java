package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data15minDao;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;
import io.philoyui.qmier.qmiermanager.service.Data15minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class Data15minServiceImpl extends GenericServiceImpl<Data15minEntity,Long> implements Data15minService {

    @Autowired
    private Data15minDao data15minDao;

    @Override
    protected GenericDao<Data15minEntity, Long> getDao() {
        return data15minDao;
    }

    @Override
    public void insertAll(List<Data15minEntity> data15minEntityList) {
        data15minDao.saveAll(data15minEntityList);
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        data15minDao.deleteBySymbol(symbol);
    }
}