package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.LinkOperation;
import cn.com.gome.page.button.column.NewPageOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MyStockPageService extends PageService<MyStockEntity,String> {

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private StockPageService stockPageService;

    @Autowired
    private FinancialMarketPageService financialMarketPageService;

    @Override
    public PageObject<MyStockEntity> paged(SearchFilter searchFilter) {
        return myStockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext);
        pageConfig.withDomainName("my_stock");
        pageConfig.withDomainClass(MyStockEntity.class);
        pageConfig.withDomainChineseName("自选股票");
        pageConfig.withFieldDefinitions(
                new LongFieldDefinition("id", "ID"),
                new StringFieldDefinition("symbol", "标识码"),
                new DomainStringFieldDefinition("symbol", "股票名称", stockPageService).aliasName("stockName"),
                new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                new StringFieldDefinition("dateString", "日期"),
                new IntFieldDefinition("score","得分"),
                new IntFieldDefinition("reason","原因"),
                new DateFieldDefinition("createdTime", "创建时间")
        );
        pageConfig.withTableColumnDefinitions(
                "#checkbox_4",
                "symbol_8",
                "stockName_8",
                "dayImage_20",
                "weekImage_20",
                "reason_20",
                "score_10",
                "#operation_10"
        );
        pageConfig.withFilterDefinitions(
                "symbol_like","stockName_like","reason_like"
        );
        pageConfig.withSortDefinitions("score_desc");
        pageConfig.withTableAction(
                new TableOperation("手动执行", "obtainEveryDay", ButtonStyle.Orange),
                new TableOperation("清空", "deleteAll", ButtonStyle.Blue)
        );
        pageConfig.withColumnAction(
                new LinkOperation("详情","http://quote.eastmoney.com/concept/#symbol#.html","symbol"),
                new DeleteOperation()
        );
        return pageConfig;
    }

    @Override
    public MyStockEntity get(String id) {
        return myStockService.get(Long.parseLong(id));
    }

    @Override
    public MyStockEntity get(SearchFilter searchFilter) {
        return myStockService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(MyStockEntity myStockEntity) {
        myStockService.insert(myStockEntity);
    }

    @Override
    public void delete(MyStockEntity myStockEntity) {
        myStockService.delete(myStockEntity.getId());
    }

}

