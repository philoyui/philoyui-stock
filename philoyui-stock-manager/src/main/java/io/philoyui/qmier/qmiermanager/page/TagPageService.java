package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.NewPageOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagPageService extends PageService<TagEntity,Long> implements PageDomainProvider {

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
                        new IntegerFieldDefinition("last1Score","当前得分"),
                        new IntegerFieldDefinition("last2Score","前一次得分"),
                        new IntegerFieldDefinition("last3Score","前两次得分"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间"),
                        new EnumFieldDefinition("intervalType","时间类型", IntervalType.class)
                )
                .withTableColumnDefinitions(
                        "tagName_15",
                        "intervalType_10",
                        "lastExecuteTime_15",
                        "last1Score_10",
                        "last2Score_10",
                        "last3Score_10",
                        "#operation_20"
                )
                .withFilterDefinitions(
                        "intervalType"
                )
                .withColumnAction(
                        new NewPageOperation("标记股票","/admin/tag_stock/page?tagName=#tagName#","标记股票","tagName"),
                        new EditOperation(),
                        new DeleteOperation()
                ).withFormItemDefinition(
                        "tagName_rw",
                        "last1Score_rw",
                        "last2Score_rw",
                        "last3Score_rw",
                        "intervalType_rw"
                )
                .withDefaultPageSize("100")
                ;
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

    @Override
    public void saveOrUpdate(TagEntity tagEntity) {
        tagService.insert(tagEntity);
    }

    @Override
    public void delete(TagEntity tagEntity) {
        tagService.delete(tagEntity.getId());
    }

    @Override
    public Object findByReferId(String referId) {
        return tagService.findByTagName(referId);
    }

    @Override
    public String getDomainName() {
        return "tag_stock";
    }

    @Override
    public String getDomainChineseName() {
        return "标签股票";
    }

    @Override
    public String getDisplayFieldName() {
        return "tagName";
    }

    @Override
    public List findAll() {
        return tagService.list(SearchFilter.getDefault());
    }
}
