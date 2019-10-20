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
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockPageService extends PageService<StockEntity,Long> {

    @Autowired
    private StockService stockService;

    @Override
    public PageObject<StockEntity> paged(SearchFilter searchFilter) {
        return stockService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("stock")
                .withDomainClass(StockEntity.class)
                .withDomainChineseName("股票")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名称").required(),
                        new StringFieldDefinition("code", "代码").required(),
                        new DoubleFieldDefinition("roe", "ROE净资产收益率").required(),
                        new DoubleFieldDefinition("volume", "成交量").required(),
                        new DoubleFieldDefinition("currentPrice", "最新价").required(),
                        new DoubleFieldDefinition("changeRange", "涨跌幅").required(),
                        new StringFieldDefinition("industry", "板块").required(),
                        new DoubleFieldDefinition("grossMargin", "毛利率").required(),
                        new DoubleFieldDefinition("debtRatio", "负债率").required(),
                        new DoubleFieldDefinition("salesRevenue", "营业利润率").required(),
                        new DoubleFieldDefinition("mainNetInflowOfFundsRatio", "今日主力净流入占比").required(),
                        new DoubleFieldDefinition("oversizeInflowOfFundsRatio", "超大单净流入占比").required(),
                        new DoubleFieldDefinition("bigSizeInflowOfFundsRatio", "大单净流入占比").required()


                )
                .withTableColumnDefinitions(
                        "name_8",
                        "code_8",
                        "currentPrice_6",
                        "changeRange_6",
                        "roe_6",
                        "grossMargin_8",
                        "salesRevenue_8",
                        "debtRatio_10",
                        "mainNetInflowOfFundsRatio_8",
                        "oversizeInflowOfFundsRatio_8",
                        "bigSizeInflowOfFundsRatio_8",
                        "industry_6",
                        "#operation_10"
                )
                .withFilterDefinitions("name_like","code","industry","roe","grossMargin","salesRevenue","debtRatio","mainNetInflowOfFundsRatio","oversizeInflowOfFundsRatio","bigSizeInflowOfFundsRatio","volume")
                .withSortDefinitions("roe_desc","roe_asc","changeRange_desc","changeRange_asc","grossMargin_asc","grossMargin_desc","salesRevenue_asc","salesRevenue_desc","debtRatio_asc","debtRatio_desc","volume_asc","volume_desc")
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("抓取","fetch", ButtonStyle.Orange)
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "code_rw",
                        "roe_rw",
                        "industry_rw",
                        "changeRange_rw",
                        "currentPrice_rw",
                        "grossMargin_rw",
                        "salesRevenue_rw",
                        "debtRatio_rw",
                        "mainNetInflowOfFundsRatio_rw",
                        "oversizeInflowOfFundsRatio_rw",
                        "bigSizeInflowOfFundsRatio_rw"
                );
    }

    @Override
    public StockEntity get(String id) {
        return stockService.get(Long.parseLong(id));
    }

    @Override
    public StockEntity get(SearchFilter searchFilter) {
        return stockService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(StockEntity stockEntity) {
        stockService.insert(stockEntity);
    }

    @Override
    public void delete(StockEntity stockEntity) {
        stockService.delete(stockEntity.getId());
    }

    @Override
    public void delete(List<StockEntity> entities) {
        stockService.delete(entities);
    }
}
