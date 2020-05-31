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
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagStockPageService extends PageService<TagStockEntity,Long> {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private StockPageService stockPageService;

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
                        new StringFieldDefinition("tagName", "标签名称"),
                        new DateFieldDefinition("createdTime", "创建时间"),
                        new StringFieldDefinition("dayString", "时间标识"),
                        new DomainStringFieldDefinition("symbol", "股票名称", stockPageService).aliasName("stockName"),
                        new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif")
                )
                .withTableColumnDefinitions(
                        "symbol_10",
                        "tagName_20",
                        "dayImage_20",
                        "weekImage_20",
                        "createdTime_15",
                        "dayString_10",
                        "#operation_15"
                )
                .withFilterDefinitions(
                    "tagName",
                    "createdTime"
                )
                .withSortDefinitions(
                    "createdTime_desc","createdTime_asc"
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "symbol_rw",
                        "tagName_rw",
                        "createdTime_rw",
                        "dayString_rw"
                );
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

