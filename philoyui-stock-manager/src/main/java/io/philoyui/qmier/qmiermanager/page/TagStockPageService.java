package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TagStockPageService extends PageService<TagStockEntity,Long> {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private StockService stockService;

    @Override
    public PageObject<TagStockEntity> paged(SearchFilter searchFilter) {
        return tagStockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("tag_stock")
                .withDomainClass(TagStockEntity.class)
                .withDomainChineseName("标签股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "编码"),
                        new StringFieldDefinition("symbol", "股票名").beforeView(symbol -> stockService.findStockName((String)symbol)).aliasName("stockName"),
                        new MapStringFieldDefinition("tagName", "标签名称",tagStockService.findTagStatisticOptions()),
                        new DateFieldDefinition("createdTime", "创建时间"),
                        new StringFieldDefinition("dayString", "时间标识"),
                        new StringFieldDefinition("lastDayString","时间标识2"),
                        new StringFieldDefinition("symbol", "股票名称"),
                        new ImageFieldDefinition("symbol", "周线图", 300, 200).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 300, 200).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif"),
                        new ListToStringFieldDefinition("symbol","标签", symbol -> tagStockService.findLastBySymbol((String)symbol).stream().map(TagStockEntity::getTagName).collect(Collectors.toList())).aliasName("tagList")
                )
                .withTableColumnDefinitions(
                        "symbol_8",
                        "stockName_8",
                        "tagName_14",
                        "dayString_10",
                        "lastDayString_10",
                        "dayImage_25",
                        "weekImage_25"
                )
                .withFilterDefinitions(
                    "symbol",
                    "tagName_like"
                )
                .withSortDefinitions(
                    "dayString_desc","dayString_asc"
                )
                .withFormItemDefinition(
                        "symbol_rw",
                        "tagName_rw",
                        "createdTime_rw",
                        "dayString_rw"
                ).withDefaultPageSize("100");
        return pageConfig;
    }

    @Override
    public TagStockEntity get(String id) {
        return tagStockService.get(Long.parseLong(id));
    }

    @Override
    public TagStockEntity get(SearchFilter searchFilter) {
        return tagStockService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(TagStockEntity tagStock) {
        tagStockService.insert(tagStock);
    }

    @Override
    public void delete(TagStockEntity tagStock) {
        tagStockService.delete(tagStock.getId());
    }
}

