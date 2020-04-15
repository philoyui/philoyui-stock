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
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagPageService extends PageService<TagEntity,Long> {

    @Autowired
    private TagService tagService;

    @Override
    public PageObject<TagEntity> paged(SearchFilter searchFilter) {
        return tagService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("tag")
                .withDomainClass(TagEntity.class)
                .withDomainChineseName("标签")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("tagName", "标签名称"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间")
                )
                .withTableColumnDefinitions(
                        "tagName_30",
                        "lastExecuteTime_30",
                        "#operation_40"
                )
                .withColumnAction(
                        new NewPageOperation("标记股票","/admin/tag_stock/page?tagName=#tagName#","标记股票","tagName"),
                        new EditOperation(),
                        new DeleteOperation()
                );
        return pageConfig;
    }

    @Override
    public TagEntity get(String id) {
        return tagService.get(Long.parseLong(id));
    }

    @Override
    public TagEntity get(SearchFilter searchFilter) {
        return tagService.get(searchFilter);
    }
}
