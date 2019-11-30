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
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("stock")
                .withDomainClass(StockEntity.class)
                .withDomainChineseName("股票数据")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "id"),
                        new StringFieldDefinition("name", "股票名称"),
                        new StringFieldDefinition("code", "股票代码"),
                        new DoubleFieldDefinition("percent", "涨跌幅"),
                        new LongFieldDefinition("floatShares", "流通股"),
                        new DoubleFieldDefinition("currentPrice", "当前价"),
                        new DoubleFieldDefinition("amplitude", "振幅"),
                        new LongFieldDefinition("marketCapital", "市值"),
                        new DoubleFieldDefinition("dividendYield", "股息率"),
                        new DoubleFieldDefinition("amount", "成交额"),
                        new DoubleFieldDefinition("chg", "涨跌额"),
                        new LongFieldDefinition("volume", "成交量"),
                        new DoubleFieldDefinition("volumeRatio", "量比"),
                        new DoubleFieldDefinition("pb", "市净率"),
                        new DoubleFieldDefinition("turnoverRate", "换手率"),
                        new DoubleFieldDefinition("peTtm", "市盈率"),
                        new LongFieldDefinition("totalShares", "总股本")
                )
                .withTableColumnDefinitions(
                        "name_6",
                        "code_6",
                        "percent_6",
                        "floatShares_6",
                        "currentPrice_6",
                        "marketCapital_6",
                        "dividendYield_6",
                        "amount_6",
                        "chg_6",
                        "volume_6",
                        "volumeRatio_6",
                        "pb_6",
                        "turnoverRate_6",
                        "peTtm_6",
                        "#operation_16"
                )
                .withFilterDefinitions(
                    "name",
                    "code",
                    "percent"
                )
                .withSortDefinitions(
                    "code_desc","code_asc",
                    "percent_desc","percent_asc",
                    "floatShares_desc","floatShares_asc",
                    "marketCapital_desc","marketCapital_asc",
                    "amount_desc","amount_asc",
                    "volume_desc","volume_asc",
                    "volumeRatio_desc","volumeRatio_asc",
                    "turnoverRate_desc","turnoverRate_asc",
                    "peTtm_desc","peTtm_asc"
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
                        "name_rw",
                        "code_rw",
                        "percent_rw",
                        "floatShares_rw",
                        "currentPrice_rw",
                        "amplitude_rw",
                        "marketCapital_rw",
                        "dividendYield_rw",
                        "amount_rw",
                        "chg_rw",
                        "volume_rw",
                        "volumeRatio_rw",
                        "pb_rw",
                        "turnoverRate_rw",
                        "peTtm_rw",
                        "totalShares_rw"
                );
        return pageConfig;
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
    public void saveOrUpdate(StockEntity stock) {
        stockService.insert(stock);
    }

    @Override
    public void delete(StockEntity stock) {
        stockService.delete(stock.getId());
    }
}

