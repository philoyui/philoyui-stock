package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.*;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockStrategyPageService extends PageService<StockStrategyEntity,Long> {

    @Autowired
    private StockStrategyService stockStrategyService;

    @Override
    public PageObject<StockStrategyEntity> paged(SearchFilter searchFilter) {
        return stockStrategyService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("stock_strategy")
                .withDomainClass(StockStrategyEntity.class)
                .withDomainChineseName("选股定义")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("identifier", "唯一标识"),
                        new StringFieldDefinition("name", "名字"),
                        new StringFieldDefinition("description", "描述"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间"),
                        new EnableFieldDefinition("enable", "是否启用"),
                        new EnumFieldDefinition("strategyType", "类型", StrategyType.class)
                )
                .withTableColumnDefinitions(
                        "identifier_15",
                        "name_15",
                        "strategyType_8",
                        "lastExecuteTime_18",
                        "enable_8",
                        "#operation_25"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("日标签任务","dayTask",ButtonStyle.Green),
                        new TableOperation("周标签任务","weekTask",ButtonStyle.Blue),
                        new TableOperation("月标签任务","monthTask",ButtonStyle.Orange)
                )
                .withColumnAction(
                        new ConfirmOperation("tagStock","打标"),
                        new NewPageOperation("标记股票","/admin/tag_stock/page?tagName=#name#","标记股票","name"),
                        new ConfirmOperation("dropStock","标记不抓取"),
                        new EnableOperation("enable"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "identifier_rw",
                        "name_rw",
                        "description_rw",
                        "enable_rw",
                        "strategyType_rw"
                );
        return pageConfig;
    }

    @Override
    public StockStrategyEntity get(String id) {
        return stockStrategyService.get(Long.parseLong(id));
    }

    @Override
    public StockStrategyEntity get(SearchFilter searchFilter) {
        return stockStrategyService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(StockStrategyEntity chooseDefinition) {
        stockStrategyService.insert(chooseDefinition);
    }

    @Override
    public void delete(StockStrategyEntity chooseDefinition) {
        stockStrategyService.delete(chooseDefinition.getId());
    }

    @Override
    public void enable(String id) {
        stockStrategyService.enable(Long.parseLong(id));
    }

    @Override
    public void disable(String id) {
        stockStrategyService.disable(Long.parseLong(id));
    }
}

