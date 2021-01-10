package io.philoyui.tradingview.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.EnableFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.tradingview.entity.TradingViewEntity;
import io.philoyui.tradingview.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradingViewPageService extends PageService<TradingViewEntity,Long> {

    @Autowired
    private TradingViewService tradingViewService;

    @Override
    public PageObject<TradingViewEntity> paged(SearchFilter searchFilter) {
        return tradingViewService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("trading_view")
                .withDomainClass(TradingViewEntity.class)
                .withDomainChineseName("tradingView数据")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "Id"),
                        new StringFieldDefinition("symbol", "编码"),
                        new StringFieldDefinition("stockName", "股票名称"),
                        new DoubleFieldDefinition("close", "收盘价"),
                        new DoubleFieldDefinition("todayChange", "涨跌幅百分比"),
                        new DoubleFieldDefinition("changeAbs", "涨跌幅百分比"),
                        new DoubleFieldDefinition("recommendScore", ""),
                        new LongFieldDefinition("volume", "成交量"),
                        new LongFieldDefinition("marketCap", "市值"),
                        new DoubleFieldDefinition("priceEarningsTtm", "市盈率"),
                        new LongFieldDefinition("numberOfEmployees", "雇员数"),
                        new StringFieldDefinition("sector", "板块"),
                        new StringFieldDefinition("industry", "行业"),
                        new DoubleFieldDefinition("quickRatio", "速动比率"),
                        new DoubleFieldDefinition("numberOfShareholders", "股东数"),
                        new EnableFieldDefinition("blackCrows3", "三只乌鸦"),
                        new EnableFieldDefinition("hangingMan", "上吊线"),
                        new EnableFieldDefinition("invertedHammer", "倒锤子线"),
                        new EnableFieldDefinition("marubozuWhite", "光头阳线"),
                        new EnableFieldDefinition("marubozuBlack", "光头阴线"),
                        new EnableFieldDefinition("doji", "十字星"),
                        new EnableFieldDefinition("morningStar", "启明星"),
                        new EnableFieldDefinition("gravestone", "墓碑线"),
                        new EnableFieldDefinition("shootingStar", "流星线"),
                        new EnableFieldDefinition("whiteSoldiers3", "白三兵"),
                        new EnableFieldDefinition("spinningTopWhite", "白色旋转陀螺"),
                        new EnableFieldDefinition("triStarBullish", "看涨三星"),
                        new EnableFieldDefinition("kickingBullish", "看涨反冲形态"),
                        new EnableFieldDefinition("engulfingBullish", "看涨吞没"),
                        new EnableFieldDefinition("haramiBullish", "看涨孕线"),
                        new EnableFieldDefinition("abandonedBabyBullish", "看涨弃婴"),
                        new EnableFieldDefinition("triStarBearish", "看跌三星"),
                        new EnableFieldDefinition("kickingBearish", "看跌反冲形态"),
                        new EnableFieldDefinition("engulfingBearish", "看跌吞没"),
                        new EnableFieldDefinition("haramiBearish", "看跌孕线"),
                        new EnableFieldDefinition("abandonedBabyBearish", "看跌弃婴"),
                        new EnableFieldDefinition("dojiDragonfly", "蜻蜓线"),
                        new EnableFieldDefinition("hammer", "锤子线"),
                        new EnableFieldDefinition("LongShadowUpper", "长上影线"),
                        new EnableFieldDefinition("SpinningTopBlack", "黑丝旋转陀螺"),
                        new EnableFieldDefinition("LongShadowLower", "长下影线"),
                        new EnableFieldDefinition("eveningStar", "黄昏星")
                )
                .withTableColumnDefinitions(
                        "symbol_8",
                        "stockName_8",
                        "close_8",
                        "todayChange_8",
                        "recommendScore_8",
                        "volume_8",
                        "sector_12",
                        "industry_12",
                        "numberOfShareholders_8",
                        "#operation_10"
                )
                .withFilterDefinitions(
                        "symbol","stockName","industry","sector"
                )
                .withSortDefinitions(
                        "recommendScore_desc"
                )
                .withTableAction(
                        new TableOperation("抓取","fetch", ButtonStyle.Green)
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "symbol_rw",
                        "stockName_rw",
                        "close_rw",
                        "todayChange_rw",
                        "changeAbs_rw",
                        "recommendScore_rw",
                        "volume_rw",
                        "marketCap_rw",
                        "priceEarningsTtm_rw",
                        "numberOfEmployees_rw",
                        "sector_rw",
                        "industry_rw",
                        "quickRatio_rw",
                        "numberOfShareholders_rw",
                        "blackCrows3_rw",
                        "hangingMan_rw",
                        "invertedHammer_rw",
                        "marubozuWhite_rw",
                        "marubozuBlack_rw",
                        "doji_rw",
                        "morningStar_rw",
                        "gravestone_rw",
                        "shootingStar_rw",
                        "whiteSoldiers3_rw",
                        "spinningTopWhite_rw",
                        "triStarBullish_rw",
                        "kickingBullish_rw",
                        "engulfingBullish_rw",
                        "haramiBullish_rw",
                        "abandonedBabyBullish_rw",
                        "triStarBearish_rw",
                        "kickingBearish_rw",
                        "engulfingBearish_rw",
                        "haramiBearish_rw",
                        "abandonedBabyBearish_rw",
                        "dojiDragonfly_rw",
                        "hammer_rw",
                        "LongShadowUpper_rw",
                        "SpinningTopBlack_rw",
                        "LongShadowLower_rw",
                        "eveningStar_rw"
                );
        return pageConfig;
    }

    @Override
    public TradingViewEntity get(String id) {
        return tradingViewService.get(Long.parseLong(id));
    }

    @Override
    public TradingViewEntity get(SearchFilter searchFilter) {
        return tradingViewService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(TradingViewEntity tradingView) {
        tradingViewService.insert(tradingView);
    }

    @Override
    public void delete(TradingViewEntity tradingView) {
        tradingViewService.delete(tradingView.getId());
    }
}

