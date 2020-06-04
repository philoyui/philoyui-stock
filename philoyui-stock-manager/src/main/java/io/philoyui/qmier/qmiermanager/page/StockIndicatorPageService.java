package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.StockIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockIndicatorPageService extends PageService<StockIndicatorEntity,Long> {

    @Autowired
    private StockIndicatorService stockIndicatorService;

    @Override
    public PageObject<StockIndicatorEntity> paged(SearchFilter searchFilter) {
        return stockIndicatorService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("stock_indicator")
                .withDomainClass(StockIndicatorEntity.class)
                .withDomainChineseName("股票指标")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("pythonName", "Python文件名称"),
                        new StringFieldDefinition("identifier", "唯一标识"),
                        new EnableFieldDefinition("enable", "是否启用"),
                        new EnumFieldDefinition("intervalType","数据类型", IntervalType.class)
                )
                .withTableColumnDefinitions(
                        "identifier_15",
                        "pythonName_20",
                        "enable_10",
                        "intervalType_10",
                        "#operation_25"
                )
                .withFilterDefinitions(
                        "intervalType"
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("日标签任务","dayTask", ButtonStyle.Green),
                        new TableOperation("周标签任务","weekTask",ButtonStyle.Blue),
                        new TableOperation("月标签任务","monthTask",ButtonStyle.Orange)
                )
                .withColumnAction(
                        new ConfirmOperation("executeGlobal","执行"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "identifier_rw",
                        "pythonName_rw",
                        "enable_rw",
                        "intervalType_rw"
                );
        return pageConfig;
    }

    @Override
    public StockIndicatorEntity get(String id) {
        return stockIndicatorService.get(Long.parseLong(id));
    }

    @Override
    public StockIndicatorEntity get(SearchFilter searchFilter) {
        return stockIndicatorService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(StockIndicatorEntity stockIndicatorEntity) {
        stockIndicatorService.insert(stockIndicatorEntity);
    }

    @Override
    public void delete(StockIndicatorEntity stockIndicatorEntity) {
        stockIndicatorService.delete(stockIndicatorEntity.getId());
    }
}
