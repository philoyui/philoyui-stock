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
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.EnableFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyStockPageService extends PageService<MyStockEntity,String> {

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private FinancialMarketPageService financialMarketPageService;

    @Override
    public PageObject<MyStockEntity> paged(SearchFilter searchFilter) {
        return myStockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("my_stock")
                .withDomainClass(MyStockEntity.class)
                .withDomainChineseName("自选股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "标识码"),
                        new StringFieldDefinition("dateString","日期"),
                        new DateFieldDefinition("createdTime", "创建时间")
                )
                .withTableColumnDefinitions(
                        "#checkbox_3",
                        "symbol_20",
                        "dateString_20",
                        "createdTime_15",
                        "#operation_35"
                )
                .withFilterDefinitions(
                    "symbol_like"
                )
                .withTableAction(
                        new TableOperation("手动执行","obtainEveryDay", ButtonStyle.Orange)
                )
                .withColumnAction(
                        new DeleteOperation()
                );
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

