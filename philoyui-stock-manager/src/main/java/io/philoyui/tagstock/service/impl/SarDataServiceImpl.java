package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.SarDataDao;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.tagstock.entity.SarDataEntity;
import io.philoyui.tagstock.service.SarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class SarDataServiceImpl extends GenericServiceImpl<SarDataEntity,Long> implements SarDataService {

    @Autowired
    private SarDataDao sarDataDao;

    @Override
    protected GenericDao<SarDataEntity, Long> getDao() {
        return sarDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        sarDataDao.deleteData("Day");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        sarDataDao.deleteData("Week");
    }

    @Transactional
    @Override
    public void deleteMonthData() {
        sarDataDao.deleteData("Month");
    }

    @Override
    public String findCurrent(String symbol) {
        SearchFilter searchFilter = SearchFilter.getPagedSearchFilter(0,1);
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",symbol));
        searchFilter.add(Order.desc("day"));
        List<SarDataEntity> sarDataEntities = this.paged(searchFilter).getContent();
        if(sarDataEntities.size()>0){
            SarDataEntity sarDataEntity = sarDataEntities.get(0);
            return (sarDataEntity.getSarType().getDescription() + "第" + sarDataEntity.getLastIndex() + "天").replaceAll("-","");
        }
        return "";
    }
}
