package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.NewPageOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.SiteEntity;
import io.philoyui.qmier.qmiermanager.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SitePageService extends PageService<SiteEntity,Long>{

    @Autowired
    private SiteService siteService;

    @Override
    public PageObject<SiteEntity> paged(SearchFilter searchFilter) {
        return siteService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("site")
                .withDomainClass(SiteEntity.class)
                .withDomainChineseName("主站")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名称").required(),
                        new StringFieldDefinition("mainUrl", "网址").required()
                )
                .withTableColumnDefinitions(
                        "id_10",
                        "name_20",
                        "mainUrl_40",
                        "#operation_30"
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new NewPageOperation("板块设置","/admin/site_category/page?siteId=#id#","板块设置","id"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "mainUrl_rw"
                );
    }

    @Override
    public SiteEntity get(String id) {
        return siteService.get(Long.parseLong(id));
    }

    @Override
    public SiteEntity get(SearchFilter searchFilter) {
        return siteService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(SiteEntity siteEntity) {
        siteService.insert(siteEntity);
    }

    @Override
    public void delete(SiteEntity siteEntity) {
        siteService.delete(siteEntity.getId());
    }
}
