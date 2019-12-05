package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;
import io.philoyui.qmier.qmiermanager.service.Data15minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Data15minPageService extends PageService<Data15minEntity,Long> {

    @Autowired
    private Data15minService data15minService;

    @Autowired
    private FinancialProductPageService financialProductPageService;

    @Override
    public PageObject<Data15minEntity> paged(SearchFilter searchFilter) {
        return data15minService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("data_15min")
                .withDomainClass(Data15minEntity.class)
                .withDomainChineseName("15分钟数据")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new DomainStringFieldDefinition("symbol", "代码",financialProductPageService),
                        new DateFieldDefinition("day", "时间"),
                        new StringFieldDefinition("dateString", "时间格式"),
                        new DoubleFieldDefinition("open", "开盘价"),
                        new DoubleFieldDefinition("high", "最高价"),
                        new DoubleFieldDefinition("low", "最低价"),
                        new DoubleFieldDefinition("close", "收盘价"),
                        new LongFieldDefinition("volume", "成交量"),
                        new DateFieldDefinition("recordTime","记录时间")
                )
                .withTableColumnDefinitions(
                        "symbol_10",
                        "day_10",
                        "open_10",
                        "high_10",
                        "low_10",
                        "close_10",
                        "volume_10",
                        "recordTime_10",
                        "#operation_10"
                )
                .withFilterDefinitions(
                    "symbol"
                )
                .withSortDefinitions(
                    "volume_desc","volume_asc",
                    "recordTime_desc","recordTime_asc",
                    "day_desc","day_asc"
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("下载历史数据","download_history", ButtonStyle.Green)
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "symbol_rw",
                        "day_rw",
                        "dateString_rw",
                        "open_rw",
                        "high_rw",
                        "low_rw",
                        "close_rw",
                        "volume_rw"
                );
        return pageConfig;
    }

    @Override
    public Data15minEntity get(String id) {
        return data15minService.get(Long.parseLong(id));
    }

    @Override
    public Data15minEntity get(SearchFilter searchFilter) {
        return data15minService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(Data15minEntity data15min) {
        data15minService.insert(data15min);
    }

    @Override
    public void delete(Data15minEntity data15min) {
        data15minService.delete(data15min.getId());
    }
}

