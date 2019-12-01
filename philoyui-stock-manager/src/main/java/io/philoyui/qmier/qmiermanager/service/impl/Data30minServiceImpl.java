package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data30minDao;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class Data30minServiceImpl extends GenericServiceImpl<Data30minEntity,Long> implements Data30minService {

    @Autowired
    private Data30minDao data30minDao;

    @Override
    protected GenericDao<Data30minEntity, Long> getDao() {
        return data30minDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        data30minDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<Data30minEntity> data30minEntityList) {
        data30minDao.saveAll(data30minEntityList);
    }
}