package io.philoyui.stockdetail.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.stock.service.StockService;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockDetailPageService extends PageService<StockDetailEntity,Long> {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDetailService stockDetailService;

    @Override
    public PageObject<StockDetailEntity> paged(SearchFilter searchFilter) {
        return stockDetailService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("stock_detail")
                .withDomainClass(StockDetailEntity.class)
                .withDomainChineseName("股票详情")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "股票编码"),
                        new StringFieldDefinition("symbol", "股票名").beforeView(symbol -> stockService.findStockName((String)symbol)).aliasName("stockName"),
                        new DoubleFieldDefinition("caToAssetValue","现金占比值"),
                        new DoubleFieldDefinition("gpMarginValue","毛利率"),
                        new DoubleFieldDefinition("npMarginValue","净利率"),
                        new DoubleFieldDefinition("roeAvgValue","回报率"),
                        new DoubleFieldDefinition("nrTurnDaysValue","应收账款周转天数"),
                        new DoubleFieldDefinition("assetTurnRatioValue","总资产周转率值"),
                        new DoubleFieldDefinition("invTurnDaysValue","存货周转率值"),
                        new DoubleFieldDefinition("liabilityToAssetValue","资产负债值"),
                        new DoubleFieldDefinition("quickRatioValue","速动比率值")
                )
                .withTableColumnDefinitions(
                        "symbol_8",
                        "stockName_8",
                        "caToAssetValue_8",
                        "gpMarginValue_8",
                        "npMarginValue_8",
                        "roeAvgValue_8",
                        "nrTurnDaysValue_8",
                        "assetTurnRatioValue_8",
                        "invTurnDaysValue_8",
                        "liabilityToAssetValue_8",
                        "quickRatioValue_8",
                        "#operation_12"
                )
                .withFilterDefinitions(
                        "symbol","caToAssetValue","gpMarginValue","npMarginValue","roeAvgValue","nrTurnDaysValue","assetTurnRatioValue","invTurnDaysValue","liabilityToAssetValue","quickRatioValue"
                )
                .withSortDefinitions(
                        "caToAssetValue_desc",
                        "gpMarginValue_desc",
                        "npMarginValue_desc",
                        "roeAvgValue_desc",
                        "nrTurnDaysValue_desc",
                        "assetTurnRatioValue_desc",
                        "invTurnDaysValue_desc",
                        "liabilityToAssetValue_desc",
                        "quickRatioValue_desc"
                )
                .withDefaultPageSize("100")
                ;

        return pageConfig;
    }

    @Override
    public StockDetailEntity get(String id) {
        return stockDetailService.get(Long.parseLong(id));
    }

    @Override
    public StockDetailEntity get(SearchFilter searchFilter) {
        return stockDetailService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(StockDetailEntity stockDetailEntity) {
        stockDetailService.insert(stockDetailEntity);
    }

    @Override
    public void delete(StockDetailEntity stockDetailEntity) {
        stockDetailService.delete(stockDetailEntity.getId());
    }
}
