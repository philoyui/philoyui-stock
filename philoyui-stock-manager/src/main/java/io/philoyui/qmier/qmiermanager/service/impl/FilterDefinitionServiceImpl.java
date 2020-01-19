package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.FilterDefinitionDao;
import io.philoyui.qmier.qmiermanager.dao.TagStockDao;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.FilterDefinitionService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilterDefinitionServiceImpl extends GenericServiceImpl<FilterDefinitionEntity,Long> implements FilterDefinitionService {

    @Autowired
    private FilterDefinitionDao filterDefinitionDao;

    @Autowired
    private StockFilters stockFilters;

    @Autowired
    private StockService stockService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    protected GenericDao<FilterDefinitionEntity, Long> getDao() {
        return filterDefinitionDao;
    }

    @Override
    public void filterStock() {

        stockService.markAllDisable();

        List<StockEntity> stockEntities = stockService.list(SearchFilter.getDefault());
        Set<String> resultSet = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toSet());

        List<FilterDefinitionEntity> filterDefinitions = filterDefinitionDao.findByEnable(true);
        for (FilterDefinitionEntity filterDefinition : filterDefinitions) {
            SearchFilter searchFilter = SearchFilter.getDefault();
            searchFilter.add(Restrictions.eq("tagName",filterDefinition.getName()));
            List<TagStockEntity> tagStockEntities = tagStockService.list(searchFilter);
            Set<String> symbolSet = tagStockEntities.stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());
            resultSet = Sets.intersection(resultSet,symbolSet);
        }

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.in("symbol",resultSet.toArray(new String[0])));
        List<StockEntity> stockEntityList = stockService.list(searchFilter);
        stockEntityList.forEach(financialProductEntity -> financialProductEntity.setEnable(true));
        stockService.updateAll(stockEntityList);

    }

    @Override
    public void enable(Long id) {
        FilterDefinitionEntity filterDefinitionEntity = this.get(id);
        filterDefinitionEntity.setEnable(true);
        this.update(filterDefinitionEntity);
    }

    @Override
    public void disable(Long id) {
        FilterDefinitionEntity filterDefinitionEntity = this.get(id);
        filterDefinitionEntity.setEnable(false);
        this.update(filterDefinitionEntity);
    }

    @Override
    public void tagStock(FilterDefinitionEntity filterDefinitionEntity) {

        tagStockService.deleteByTagName(filterDefinitionEntity.getName());

        StockFilter stockFilter = stockFilters.select(filterDefinitionEntity.getIdentifier());
        Set<String> codeSet = stockFilter.filterSymbol(filterDefinitionEntity.getParam1(), filterDefinitionEntity.getParam2(), filterDefinitionEntity.getParam3());

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.in("symbol",codeSet.toArray(new String[0])));
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = Lists.newArrayList();
        for (StockEntity stockEntity : stockEntities) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(stockEntity.getSymbol());
            tagStockEntity.setTagName(filterDefinitionEntity.getName());
            tagStockEntity.setCreatedTime(new Date());
            tagStockEntities.add(tagStockEntity);
        }
        tagStockService.batchInsert(tagStockEntities);

        filterDefinitionEntity.setLastExecuteTime(new Date());
        filterDefinitionEntity.setFilterCount(codeSet.size());
        filterDefinitionDao.save(filterDefinitionEntity);
    }

}