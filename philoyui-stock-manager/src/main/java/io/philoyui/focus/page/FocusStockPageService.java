package io.philoyui.focus.page;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.NewPageOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.focus.entity.FocusStockEntity;
import io.philoyui.focus.service.FocusStockService;
import io.philoyui.mystock.service.MyStockService;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TradingViewService;
import io.philoyui.tagstock.service.SarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FocusStockPageService extends PageService<FocusStockEntity,Long> {

    @Autowired
    private FocusStockService focusStockService;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private SarDataService sarDataService;

    @Autowired
    private StockService stockService;

    @Autowired
    private TradingViewService tradingViewService;

    @Override
    public PageObject<FocusStockEntity> paged(SearchFilter searchFilter) {
        searchFilter.add(Order.asc("level"));
        return focusStockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("focus_stock")
                .withDomainClass(FocusStockEntity.class)
                .withDomainChineseName("关注股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "股票代码"),
                        new StringFieldDefinition("symbol", "股票名").beforeView(symbol -> stockService.findStockName((String)symbol)).aliasName("stockName"),
                        new DateFieldDefinition("addTime", "加入时间"),
                        new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                        new StringFieldDefinition("reason", "原因"),
                        new IntegerFieldDefinition("level","等级")
                )
                .withTableAction(
                        new TableOperation("重新生成","focusTask", ButtonStyle.Green)
                )
                .withTableColumnDefinitions(
                        "symbol_10",
                        "stockName_10",
                        "weekImage_20",
                        "dayImage_20",
                        "level_5",
                        "reason_20",
                        "#operation_15"
                )
                .withFilterDefinitions(
                        "symbol","level"
                )
                .withColumnAction(
                        new NewPageOperation("标签","/admin/tag_stock/page?symbol=#symbol#","标签","symbol"),
                        new ConfirmOperation("addMyStock","加入自选")
                )
                .withDefaultPageSize("200");
        return pageConfig;
    }

    @Override
    public FocusStockEntity get(String id) {
        return focusStockService.get(Long.parseLong(id));
    }

    @Override
    public FocusStockEntity get(SearchFilter searchFilter) {
        return focusStockService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(FocusStockEntity focusStock) {
        focusStockService.insert(focusStock);
    }

    @Override
    public void delete(FocusStockEntity focusStock) {
        focusStockService.delete(focusStock.getId());
    }
}

