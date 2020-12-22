package io.philoyui.stock.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.ImageFieldDefinition;
import cn.com.gome.page.field.IntegerFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockPageService extends PageService<StockEntity,Long> {

    @Autowired
    private StockService stockService;

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
                        new StringFieldDefinition("symbol", "股票编码"),
                        new IntegerFieldDefinition("name","股票名称"),
                        new ImageFieldDefinition("symbol", "周线图", 200, 150).aliasName("weekImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/weekly/n/" + symbol + ".gif"),
                        new ImageFieldDefinition("symbol", "日线图", 200, 150).aliasName("dayImage").beforeView(symbol -> "http://image.sinajs.cn/newchart/daily/n/" + symbol + ".gif")
                )
                .withTableColumnDefinitions(
                        "symbol_15",
                        "name_15",
                        "weekImage_25",
                        "dayImage_25",
                        "#operation_20"
                )
                .withFilterDefinitions(
                        "symbol","name"
                )
                .withColumnAction(
                        new ConfirmOperation("addMyStock","加入自选")
                )
                .withDefaultPageSize("100")
                ;

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
}
