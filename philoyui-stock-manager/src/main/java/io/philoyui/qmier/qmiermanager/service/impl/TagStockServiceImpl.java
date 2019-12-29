package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TagStockDao;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TagStockServiceImpl extends GenericServiceImpl<TagStockEntity,Long> implements TagStockService {

    @Autowired
    private TagStockDao tagStockDao;

    @Override
    protected GenericDao<TagStockEntity, Long> getDao() {
        return tagStockDao;
    }

    @Override
    public void batchInsert(List<TagStockEntity> tagStockEntities) {
        tagStockDao.saveAll(tagStockEntities);
    }

    @Transactional
    @Override
    public void deleteByTagName(String tagName) {
        tagStockDao.deleteByTagName(tagName);
    }
}
