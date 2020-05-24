package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockAnalysisTimerTest {

    @Autowired
    private StockAnalysisTimer stockAnalysisTimer;

    @Autowired
    private TagStockService tagStockService;

    @Test
    public void test_core(){
        stockAnalysisTimer.executeDayTask();
    }

    @Test
    public void test_(){

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("tagName","DIFF底背离(日)"));
        searchFilter.add(Restrictions.eq("dayString","2020-05-20"));
        for (TagStockEntity tagStockEntity : tagStockService.list(searchFilter)) {
            System.out.println(tagStockEntity.getSymbol() + "DIFF底背离(日)");
        }

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("tagName","MACD底背离(日)"));
        searchFilter.add(Restrictions.eq("dayString","2020-05-20"));
        for (TagStockEntity tagStockEntity : tagStockService.list(searchFilter)) {
            System.out.println(tagStockEntity.getSymbol() + "MACD底背离(日)");
        }
    }

}