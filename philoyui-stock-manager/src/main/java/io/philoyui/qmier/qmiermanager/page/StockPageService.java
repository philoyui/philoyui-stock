package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.EnableOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockPageService extends PageService<StockEntity,String> implements PageDomainProvider<StockEntity> {

    @Autowired
    private StockService stockService;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private FinancialMarketPageService financialMarketPageService;

    @Override
    public PageObject<StockEntity> paged(SearchFilter searchFilter) {
        return stockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("stock")
                .withDomainClass(StockEntity.class)
                .withDomainChineseName("股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "标识码"),
                        new DomainLongFieldDefinition("marketId", "交易所",financialMarketPageService),
                        new StringFieldDefinition("code", "代码"),
                        new StringFieldDefinition("name", "名称"),
                        new DateFieldDefinition("modifyTime", "修改时间"),
                        new EnableFieldDefinition("enable", "是否启用"),
                        new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                        new ListToStringFieldDefinition("symbol","标签", symbol -> tagStockService.findLastBySymbol((String)symbol).stream().map(TagStockEntity::getTagName).collect(Collectors.toList())).aliasName("tagList")
                )
                .withTableColumnDefinitions(
                        "#checkbox_3",
                        "symbol_8",
                        "name_8",
                        "dayImage_20",
                        "weekImage_20",
                        "modifyTime_12",
                        "enable_5",
                        "tagList_14",
                        "#operation_10"
                )
                .withFilterDefinitions(
                    "symbol_like",
                    "enable",
                    "name_like",
                    "marketId"
                )
                .withSortDefinitions(
                    "modifyTime_desc","modifyTime_asc"
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("下载历史数据","downloadHistory", ButtonStyle.Orange),
                        new TableOperation("启用所有","allEnable",ButtonStyle.Green)
                )
                .withColumnAction(
                        new EnableOperation("enable"),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "symbol_rw",
                        "code_rw",
                        "name_rw",
                        "marketId_rw",
                        "modifyTime_rw"
                );
        return pageConfig;
    }

    @Override
    public StockEntity get(String id) {
        return stockService.get(Long.parseLong(id));
    }

    @Override
    public StockEntity get(SearchFilter searchFilter) {
        return stockService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(StockEntity financialProduct) {
        stockService.insert(financialProduct);
    }

    @Override
    public void delete(StockEntity financialProduct) {
        stockService.delete(financialProduct.getId());
    }

    @Override
    public void enable(String id) {
        stockService.enable(Long.parseLong(id));
    }

    @Override
    public void disable(String id) {
        stockService.disable(Long.parseLong(id));
    }

    @Override
    public Object findByReferId(String referId) {
        return stockService.findBySymbol(referId);
    }

    @Override
    public String getDomainName() {
        return "financial_product";
    }

    @Override
    public String getDomainChineseName() {
        return "金融产品";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<StockEntity> findAll() {
        return stockService.list(SearchFilter.getDefault());
    }
}

