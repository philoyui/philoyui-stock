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
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.QmierEntity;
import io.philoyui.qmier.qmiermanager.service.QmierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QmierPageService extends PageService<QmierEntity,Long> implements PageDomainProvider<QmierEntity> {

    @Autowired
    private QmierService qmierService;

    @Override
    public PageObject<QmierEntity> paged(SearchFilter searchFilter) {
        return qmierService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("qmier")
                .withDomainClass(QmierEntity.class)
                .withDomainChineseName("Qmier")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名称").required(),
                        new StringFieldDefinition("city", "城市").required(),
                        new StringFieldDefinition("height","身高"),
                        new StringFieldDefinition("region","区域"),
                        new StringFieldDefinition("color","肤色"),
                        new StringFieldDefinition("address","地址"),
                        new StringFieldDefinition("price","价格"),
                        new StringFieldDefinition("services","服务"),
                        new StringFieldDefinition("contact","联系方式"),
                        new StringFieldDefinition("size","罩杯")
                )
                .withTableColumnDefinitions(
                        "name_10",
                        "city_10",
                        "region_10",
                        "price_15",
                        "address_15",
                        "height_10",
                        "contact_15",
                        "#operation_20"
                )
                .withFilterDefinitions("name_like","city","address_like")
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new NewPageOperation("图片","/admin/qmier_image/page?qmierId=#id#","图片","id"),
                        new NewPageOperation("评论","/admin/qmier_comment/page?qmierId=#id#","评论","id"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "city_rw",
                        "region_rw",
                        "height_rw",
                        "color_rw",
                        "size_rw",
                        "contact_rw",
                        "services_rw",
                        "price_rw",
                        "address_rw"
                );
    }

    @Override
    public QmierEntity get(String id) {
        return qmierService.get(Long.parseLong(id));
    }

    @Override
    public QmierEntity get(SearchFilter searchFilter) {
        return qmierService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(QmierEntity qmierEntity) {
        qmierService.insert(qmierEntity);
    }

    @Override
    public void delete(QmierEntity qmierEntity) {
        qmierService.delete(qmierEntity.getId());
    }

    @Override
    public Object findByReferId(String referId) {
        return qmierService.get(Long.parseLong(referId));
    }

    @Override
    public String getDomainName() {
        return "qmier";
    }

    @Override
    public String getDomainChineseName() {
        return "Qmier";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<QmierEntity> findAll() {
        return qmierService.list(SearchFilter.getDefault());
    }
}
