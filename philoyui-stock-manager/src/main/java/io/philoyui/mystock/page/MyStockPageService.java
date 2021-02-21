package io.philoyui.mystock.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.LinkOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.mystock.service.MyStockService;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tradingview.entity.TradingViewEntity;
import io.philoyui.tradingview.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyStockPageService extends PageService<MyStockEntity,String> {

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDetailService stockDetailService;

    @Autowired
    private TradingViewService tradingViewService;

    @Override
    public PageObject<MyStockEntity> paged(SearchFilter searchFilter) {

        PageObject<MyStockEntity> pageObject = myStockService.paged(searchFilter);
        for (MyStockEntity myStockEntity : pageObject.getContent()) {
            StringBuilder reportBuilder = new StringBuilder();
            StringBuilder technicalBuilder = new StringBuilder();

            for (TagStockEntity tagStockEntity : tagStockService.findBySymbol(myStockEntity.getSymbol())) {
                technicalBuilder.append("<p>").append(tagStockEntity.getTagName()).append("</p>");
            }

            StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(myStockEntity.getSymbol());
            if(stockDetailEntity!=null){
                List<String> reportItems = stockDetailEntity.buildReportItems();
                for (String describeItem : reportItems) {
                    reportBuilder.append("<p>").append(describeItem).append("</p>");
                }
                List<String> technicalItems = stockDetailEntity.buildTechnicalItems();
                for (String technicalItem : technicalItems) {
                    technicalBuilder.append("<p>").append(technicalItem).append("</p>");
                }
            }

            TradingViewEntity tradingViewEntity = tradingViewService.findBySymbol(myStockEntity.getSymbol());
            if(tradingViewEntity!=null){
                List<String> tradingViewReports = tradingViewEntity.buildTradingViewItems();
                for (String describeItem : tradingViewReports) {
                    technicalBuilder.append("<p>").append(describeItem).append("</p>");
                }
            }

            myStockEntity.setFinancialReport(reportBuilder.toString());
            myStockEntity.setTechnicalIndex(technicalBuilder.toString());
        }

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
                new StringFieldDefinition("symbol", "股票编码"),
                new StringFieldDefinition("symbol", "股票名").beforeView(symbol -> stockService.findStockName((String)symbol)).aliasName("stockName"),
                new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                new StringFieldDefinition("dateString", "日期"),
                new StringFieldDefinition("financialReport","财报"),
                new StringFieldDefinition("technicalIndex","技术指标"),
                new StringFieldDefinition("reason","原因"),
                new DateFieldDefinition("createdTime", "创建时间")
        );
        pageConfig.withTableColumnDefinitions(
                "symbol_8",
                "stockName_6",
                "dayImage_15",
                "weekImage_15",
                "financialReport_14",
                "technicalIndex_12",
                "reason_10",
                "#operation_6"
        );
        pageConfig.withFilterDefinitions(
                "symbol_like",
                "stockName_like",
                "financialReport_like",
                "technicalIndex_like"
        );
        pageConfig.withTableAction(
                new TableOperation("清空", "deleteAll", ButtonStyle.Blue)
        );
        pageConfig.withColumnAction(
                new LinkOperation("详情","http://quote.eastmoney.com/concept/#symbol#.html","symbol"),
                new EditOperation(),
                new DeleteOperation()
        );
        pageConfig.withFormItemDefinition(
                "reason_rw"
        );
        pageConfig.withDefaultPageSize("100");
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

