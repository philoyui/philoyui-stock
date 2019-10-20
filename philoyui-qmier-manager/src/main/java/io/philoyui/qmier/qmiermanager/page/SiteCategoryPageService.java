package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.*;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.SiteCategoryEntity;
import io.philoyui.qmier.qmiermanager.service.SiteCategoryService;
import io.philoyui.qmier.qmiermanager.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SiteCategoryPageService extends PageService<SiteCategoryEntity,Long> {

    @Autowired
    private SiteCategoryService siteCategoryService;

    @Autowired
    private SiteService siteService;

    @Override
    public PageObject<SiteCategoryEntity> paged(SearchFilter searchFilter) {
        return siteCategoryService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("site_category")
                .withDomainClass(SiteCategoryEntity.class)
                .withDomainChineseName("版区")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名称").required(),
                        new DomainLongFieldDefinition("siteId", "主站",siteService).required(),
                        new StringFieldDefinition("siteListUrl", "列表地址").required(),
                        new DateFieldDefinition("lastProcessTime","上次抓取时间").required(),
                        new EnableFieldDefinition("enable","是否开启").required(),
                        new StringFieldDefinition("city","城市").required(),
                        new IntFieldDefinition("totalPageNum","总页码").required()
                )
                .withTableColumnDefinitions(
                        "id_5",
                        "name_10",
                        "siteId_10",
                        "city_10",
                        "siteListUrl_25",
                        "lastProcessTime_10",
                        "totalPageNum_5",
                        "enable_5",
                        "#operation_20"
                )
                .withFilterDefinitions("siteId","enable")
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new ConfirmOperation("fetch","抓取"),
                        new EditOperation(),
                        new DeleteOperation(),
                        new EnableOperation("enable")
                )
                .withFormItemDefinition(
                        "name_rw",
                        "siteId_rw",
                        "city_rw",
                        "siteListUrl_rw",
                        "totalPageNum_rw",
                        "enable_rw"
                );
    }

    @Override
    public SiteCategoryEntity get(String id) {
        return siteCategoryService.get(Long.parseLong(id));
    }

    @Override
    public SiteCategoryEntity get(SearchFilter searchFilter) {
        return siteCategoryService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(SiteCategoryEntity siteCategoryEntity) {
        siteCategoryService.insert(siteCategoryEntity);
    }

    @Override
    public void delete(SiteCategoryEntity siteCategoryEntity) {
        siteCategoryService.delete(siteCategoryEntity.getId());
    }

    @Override
    public void enable(String id) {
        SiteCategoryEntity siteCategoryEntity = siteCategoryService.get(Long.parseLong(id));
        siteCategoryEntity.setEnable(true);
        siteCategoryService.insert(siteCategoryEntity);
    }

    @Override
    public void disable(String id) {
        SiteCategoryEntity siteCategoryEntity = siteCategoryService.get(Long.parseLong(id));
        siteCategoryEntity.setEnable(false);
        siteCategoryService.insert(siteCategoryEntity);
    }
}
