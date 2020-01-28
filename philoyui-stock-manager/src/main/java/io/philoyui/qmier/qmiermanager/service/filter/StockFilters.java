package io.philoyui.qmier.qmiermanager.service.filter;

import io.philoyui.qmier.qmiermanager.service.choose.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockFilters {

    @Autowired
    private QuickRadioFilter quickRadioFilter;

    @Autowired
    private QuickRadioCommonFilter quickRadioCommonFilter;

    @Autowired
    private TurnoverFilter turnoverFilter;

    @Autowired
    private PerFilter perFilter;

    @Autowired
    private MiddleDishStockFilter middleDishStockFilter;

    @Autowired
    private LowDishStockFilter lowDishStockFilter;

    @Autowired
    private BigDishStockFilter bigDishStockFilter;

    /**
     * ROE增长性股票
     */
    @Autowired
    private RoeIncreasingFilter roeIncreasingFilter;

    /**
     * 适合存股
     */
    @Autowired
    private RoeStableFilter roeStableFilter;

    /**
     * 投资者活动关系记录表
     */
    @Autowired
    private InvestorInfoFilter investorInfoFilter;

    /**
     * 大宗交易
     */
    @Autowired
    private BigBuyOverflowFilter bigBuyOverflowFilter;

    /**
     * 大宗交易
     */
    @Autowired
    private BigBuyFilter bigBuyFilter;

    /**
     * 大宗交易
     */
    @Autowired
    private BigBuyHighVolumeFilter bigBuyHighVolumeFilter;

    public StockFilter select(String identifier) {
        switch (identifier){
            case "quick_radio":
                return quickRadioFilter;
            case "quick_radio_common":
                return quickRadioCommonFilter;
            case "turn_over":
                return turnoverFilter;
            case "low_price":
                return perFilter;
            case "big_dish_stock":
                return bigDishStockFilter;
            case "middle_dish_stock":
                return middleDishStockFilter;
            case "little_dish_stock":
                return lowDishStockFilter;
            case "roe_increasing":
                return roeIncreasingFilter;
            case "roe_stable":
                return roeStableFilter;
            case "investor_info":
                return investorInfoFilter;
            case "today_big_deal_overflow":
                return bigBuyOverflowFilter;
            case "today_big_deal":
                return bigBuyFilter;
            case "today_big_deal_high_volum":
                return bigBuyHighVolumeFilter;
        }
        return quickRadioFilter;
    }

}
