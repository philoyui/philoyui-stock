package io.philoyui.stockdetail.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockDetailPageService extends PageService<StockDetailEntity,Long> {

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
                        new StringFieldDefinition("cashInfo","现金详情"),
                        new StringFieldDefinition("epsInfo","市盈率信息"),
                        new StringFieldDefinition("turnOverInfo","换手率"),
                        new DateFieldDefinition("createdTime","创建时间"),
                        new DateFieldDefinition("modifyTime","修改时间")

                )
                .withTableColumnDefinitions(
                        "symbol_10",
                        "cashInfo_10",
                        "epsInfo_10",
                        "turnOverInfo_10",
                        "createdTime_10",
                        "modifyTime_10",
                        "#operation_40"
                )
                .withFilterDefinitions(
                        "symbol","cashInfo","epsInfo","turnOverInfo"
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
