package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.BatchActionOperation;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.button.column.EnableOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialProductPageService extends PageService<FinancialProductEntity,Long> implements PageDomainProvider<FinancialProductEntity> {

    @Autowired
    private FinancialProductService financialProductService;

    @Autowired
    private FinancialMarketPageService financialMarketPageService;

    @Override
    public PageObject<FinancialProductEntity> paged(SearchFilter searchFilter) {
        return financialProductService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("financial_product")
                .withDomainClass(FinancialProductEntity.class)
                .withDomainChineseName("金融产品")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "标识码"),
                        new DomainLongFieldDefinition("marketId", "交易所",financialMarketPageService),
                        new StringFieldDefinition("code", "代码"),
                        new StringFieldDefinition("name", "名称"),
                        new DateFieldDefinition("modifyTime", "修改时间"),
                        new EnableFieldDefinition("enable", "是否启用")
                )
                .withTableColumnDefinitions(
                        "#checkbox_3",
                        "symbol_14",
                        "code_10",
                        "name_15",
                        "marketId_10",
                        "modifyTime_15",
                        "enable_8",
                        "#operation_25"
                )
                .withFilterDefinitions(
                    "symbol",
                    "enable",
                    "name_like",
                    "marketId"
                )
                .withSortDefinitions(
                    "modifyTime_desc","modifyTime_asc"
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("下载历史数据","downloadHistory", ButtonStyle.Orange)
                )
                .withColumnAction(
                        new EnableOperation("enable"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "symbol_rw",
                        "code_rw",
                        "name_rw",
                        "marketId_rw",
                        "modifyTime_rw"
                );
        return pageConfig;
    }

    @Override
    public FinancialProductEntity get(String id) {
        return financialProductService.get(Long.parseLong(id));
    }

    @Override
    public FinancialProductEntity get(SearchFilter searchFilter) {
        return financialProductService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(FinancialProductEntity financialProduct) {
        financialProductService.insert(financialProduct);
    }

    @Override
    public void delete(FinancialProductEntity financialProduct) {
        financialProductService.delete(financialProduct.getId());
    }

    @Override
    public void enable(String id) {
        financialProductService.enable(Long.parseLong(id));
    }

    @Override
    public void disable(String id) {
        financialProductService.disable(Long.parseLong(id));
    }

    @Override
    public Object findByReferId(String referId) {
        return financialProductService.findBySymbol(referId);
    }

    @Override
    public String getDomainName() {
        return "data_day";
    }

    @Override
    public String getDomainChineseName() {
        return "日数据";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List<FinancialProductEntity> findAll() {
        return financialProductService.list(SearchFilter.getDefault());
    }
}

