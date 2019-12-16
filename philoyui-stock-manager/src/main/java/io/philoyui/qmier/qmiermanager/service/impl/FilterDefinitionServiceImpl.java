package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.FilterDefinitionDao;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.service.FilterDefinitionService;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private FinancialProductService financialProductService;

    @Override
    protected GenericDao<FilterDefinitionEntity, Long> getDao() {
        return filterDefinitionDao;
    }

    @Override
    public void filterStock() {

        financialProductService.markAllDisable();

        List<FinancialProductEntity> financialProductEntities = financialProductService.list(SearchFilter.getDefault());
        Set<String> resultSet = financialProductEntities.stream().map(FinancialProductEntity::getSymbol).collect(Collectors.toSet());

        List<FilterDefinitionEntity> filterDefinitions = filterDefinitionDao.findByEnable(true);
        for (FilterDefinitionEntity filterDefinition : filterDefinitions) {
            Set<String> symbolSet = stockFilters.select(filterDefinition.getIdentifier()).filterSymbol(filterDefinition.getParam1(),filterDefinition.getParam2() ,filterDefinition.getParam3());
            resultSet = Sets.intersection(resultSet,symbolSet);
        }

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.in("symbol",resultSet.toArray(new String[0])));
        List<FinancialProductEntity> financialProductEntityList = financialProductService.list(searchFilter);
        financialProductEntityList.forEach(financialProductEntity -> financialProductEntity.setEnable(true));
        financialProductService.updateAll(financialProductEntityList);

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
}