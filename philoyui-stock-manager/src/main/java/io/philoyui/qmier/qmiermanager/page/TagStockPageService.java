package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.DomainStringFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
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
                .withDomainChineseName("被标记股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "标识码"),
                        new DomainStringFieldDefinition("symbol", "股票名称", stockPageService).aliasName("stockName"),
                        new StringFieldDefinition("tagName", "标签"),
                        new DateFieldDefinition("createdTime", "修改时间")
                )
                .withTableColumnDefinitions(
                        "symbol_20",
                        "stockName_30",
                        "tagName_30",
                        "createdTime_20"
                )
                .withFilterDefinitions(
                        "symbol_like",
                        "tagName"
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
}
