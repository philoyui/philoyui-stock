package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.TradingView;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.TradingViewEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.SarDataEntity;
import io.philoyui.qmier.qmiermanager.service.FocusStockService;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.SarDataService;
import io.philoyui.qmier.qmiermanager.service.TradingViewService;
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
    private TradingViewService tradingViewService;

    @Override
    public PageObject<FocusStockEntity> paged(SearchFilter searchFilter) {


        PageObject<FocusStockEntity> paged = focusStockService.paged(searchFilter);

        paged.getContent().stream().forEach(stock ->{
            MyStockEntity myStockEntity = myStockService.findBySymbol(stock.getSymbol());
            if(myStockEntity!=null){
                stock.setAnalysisResult(myStockEntity.getReason());
                stock.setScore(myStockEntity.getScore());
            }
            stock.setSarReason(sarDataService.findCurrent(stock.getSymbol()));
        });

        return paged;
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
                        new StringFieldDefinition("stockName", "股票名称"),
                        new DateFieldDefinition("addTime", "加入时间"),
                        new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                        new StringFieldDefinition("sarReason", "多空判断"),
                        new IntFieldDefinition("score", "分数"),
                        new StringFieldDefinition("analysisResult", "分析结果")
                )
                .withTableColumnDefinitions(
                        "symbol_8",
                        "stockName_8",
                        "weekImage_18",
                        "dayImage_18",
                        "analysisResult_20",
                        "score_5",
                        "sarReason_8",
                        "#operation_10"
                )
                .withFilterDefinitions(
                        "symbol_like","stockName_like"
                )
                .withSortDefinitions(
                        "addTime_desc",
                        "score_desc"
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "symbol_rw",
                        "stockName_rw",
                        "addTime_rw",
                        "analysisResult_rw"
                ).withDefaultPageSize("200");
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

