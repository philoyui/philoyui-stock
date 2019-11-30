package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialMarketPageService extends PageService<FinancialMarketEntity,Long> implements PageDomainProvider<FinancialMarketEntity> {

    @Autowired
    private FinancialMarketService financialMarketService;

    @Override
    public PageObject<FinancialMarketEntity> paged(SearchFilter searchFilter) {
        return financialMarketService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("financial_market")
                .withDomainClass(FinancialMarketEntity.class)
                .withDomainChineseName("金融市场")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名字"),
                        new StringFieldDefinition("identifier", "唯一标识")
                )
                .withTableColumnDefinitions(
                        "name_30",
                        "identifier_30",
                        "#operation_30"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "identifier_rw"
                );
        return pageConfig;
    }

    @Override
    public FinancialMarketEntity get(String id) {
        return financialMarketService.get(Long.parseLong(id));
    }

    @Override
    public FinancialMarketEntity get(SearchFilter searchFilter) {
        return financialMarketService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(FinancialMarketEntity financialMarket) {
        financialMarketService.insert(financialMarket);
    }

    @Override
    public void delete(FinancialMarketEntity financialMarket) {
        financialMarketService.delete(financialMarket.getId());
    }

    @Override
    public Object findByReferId(String referId) {
        return financialMarketService.get(Long.parseLong(referId));
    }

    @Override
    public String getDomainName() {
        return "financial_market";
    }

    @Override
    public String getDomainChineseName() {
        return "金融市场";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<FinancialMarketEntity> findAll() {
        return financialMarketService.list(SearchFilter.getDefault());
    }
}

