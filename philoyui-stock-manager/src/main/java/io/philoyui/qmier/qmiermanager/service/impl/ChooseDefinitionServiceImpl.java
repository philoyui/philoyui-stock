package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.dao.ChooseDefinitionDao;
import io.philoyui.qmier.qmiermanager.entity.ChooseDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.ChooseDefinitionService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ChooseDefinitionServiceImpl extends GenericServiceImpl<ChooseDefinitionEntity,Long> implements ChooseDefinitionService {

    @Autowired
    private ChooseDefinitionDao chooseDefinitionDao;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockFilters stockFilters;

    @Override
    protected GenericDao<ChooseDefinitionEntity, Long> getDao() {
        return chooseDefinitionDao;
    }

    @Override
    public List<ChooseDefinitionEntity> findByEnable(boolean enable) {
        return chooseDefinitionDao.findByEnable(enable);
    }

    @Override
    public void tagStock(ChooseDefinitionEntity chooseDefinitionEntity) {
        tagStockService.deleteByTagName(chooseDefinitionEntity.getName());

        StockFilter stockFilter = stockFilters.select(chooseDefinitionEntity.getIdentifier());
        Set<String> codeSet = stockFilter.filterSymbol(chooseDefinitionEntity.getParam1(), chooseDefinitionEntity.getParam2(), chooseDefinitionEntity.getParam3());

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.in("symbol",codeSet.toArray(new String[0])));
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = Lists.newArrayList();
        for (StockEntity stockEntity : stockEntities) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(stockEntity.getSymbol());
            tagStockEntity.setTagName(chooseDefinitionEntity.getName());
            tagStockEntity.setCreatedTime(new Date());
            tagStockEntities.add(tagStockEntity);
        }
        tagStockService.batchInsert(tagStockEntities);

        chooseDefinitionEntity.setLastExecuteTime(new Date());
        chooseDefinitionEntity.setChooseCount(codeSet.size());
        chooseDefinitionDao.save(chooseDefinitionEntity);
    }
}