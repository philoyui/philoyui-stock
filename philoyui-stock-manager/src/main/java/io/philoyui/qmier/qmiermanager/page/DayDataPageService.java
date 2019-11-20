package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.DayDataEntity;
import io.philoyui.qmier.qmiermanager.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayDataPageService extends PageService<DayDataEntity,Long> {

    @Autowired
    private DayDataService dayDataService;

    @Override
    public PageObject<DayDataEntity> paged(SearchFilter searchFilter) {
        return dayDataService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("dayData")
                .withDomainClass(DayDataEntity.class)
                .withDomainChineseName("日线数据")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("code", "代码"),
                        new StringFieldDefinition("dayString", "时间戳"),
                        new DoubleFieldDefinition("currentPrice", "当前价")
                )
                .withTableColumnDefinitions(
                        "id_10",
                        "code_10",
                        "dayString_10",
                        "currentPrice_10",
                        "#operation_10"
                )
                .withFilterDefinitions(
                    "id",
                    "code",
                    "dayString",
                    "currentPrice"
                )
                .withSortDefinitions(
                    "dayString_desc","dayString_asc"
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "code_rw",
                        "dayString_rw",
                        "currentPrice_r"
                );
        return pageConfig;
    }

    @Override
    public DayDataEntity get(String id) {
        return dayDataService.get(Long.parseLong(id));
    }

    @Override
    public DayDataEntity get(SearchFilter searchFilter) {
        return dayDataService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(DayDataEntity dayData) {
        dayDataService.insert(dayData);
    }

    @Override
    public void delete(DayDataEntity dayData) {
        dayDataService.delete(dayData.getId());
    }
}

