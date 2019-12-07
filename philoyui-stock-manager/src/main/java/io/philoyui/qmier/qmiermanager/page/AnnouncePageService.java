package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.NewPageOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;
import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncePageService extends PageService<AnnounceEntity,Long> {

    @Autowired
    private AnnounceService announceService;

    @Override
    public PageObject<AnnounceEntity> paged(SearchFilter searchFilter) {
        return announceService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("announce")
                .withDomainClass(AnnounceEntity.class)
                .withDomainChineseName("公告")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("title", "标题"),
                        new StringFieldDefinition("symbol", "股票编码"),
                        new DateFieldDefinition("publishTime", "发布时间"),
                        new StringFieldDefinition("detailUrl", "详细页链接"),
                        new StringFieldDefinition("announceType", "公告类型")
                )
                .withTableColumnDefinitions(
                        "symbol_10",
                        "title_40",
                        "announceType_15",
                        "publishTime_15",
                        "#operation_30"
                )
                .withFilterDefinitions(
                    "title_like",
                    "symbol",
                    "publishTime",
                    "announceType"
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("下载历史数据","downloadHistory", ButtonStyle.Green)
                )
                .withColumnAction(
                        new NewPageOperation("详情","#detailUrl#","详情","detailUrl"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "title_rw",
                        "symbol_rw",
                        "publishTime_rw",
                        "announceType_rw"
                );
        return pageConfig;
    }

    @Override
    public AnnounceEntity get(String id) {
        return announceService.get(Long.parseLong(id));
    }

    @Override
    public AnnounceEntity get(SearchFilter searchFilter) {
        return announceService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(AnnounceEntity announce) {
        announceService.insert(announce);
    }

    @Override
    public void delete(AnnounceEntity announce) {
        announceService.delete(announce.getId());
    }
}

