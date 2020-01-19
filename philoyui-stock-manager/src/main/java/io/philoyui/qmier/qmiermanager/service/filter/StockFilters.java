package io.philoyui.qmier.qmiermanager.service.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockFilters {

    @Autowired
    private RoeIncreasingFilter roeIncreasingFilter;

    @Autowired
    private RoeStableFilter roeStableFilter;

    @Autowired
    private QuickRadioFilter quickRadioFilter;

    @Autowired
    private QuickRadioCommonFilter quickRadioCommonFilter;

    @Autowired
    private TurnoverFilter turnoverFilter;

    @Autowired
    private PerFilter perFilter;

    @Autowired
    private InvestorInfoFilter investorInfoFilter;

    @Autowired
    private BigBuyOverflowFilter bigBuyOverflowFilter;

    @Autowired
    private BigBuyFilter bigBuyFilter;

    @Autowired
    private BigBuyHighVolumeFilter bigBuyHighVolumeFilter;

    @Autowired
    private MiddleDishStockFilter middleDishStockFilter;

    @Autowired
    private LowDishStockFilter lowDishStockFilter;

    @Autowired
    private BigDishStockFilter bigDishStockFilter;

    public StockFilter select(String identifier) {
        switch (identifier){
            case "roe_increasing":
                return roeIncreasingFilter;
            case "roe_stable":
                return roeStableFilter;
            case "quick_radio":
                return quickRadioFilter;
            case "quick_radio_common":
                return quickRadioCommonFilter;
            case "turn_over":
                return turnoverFilter;
            case "low_price":
                return perFilter;
            case "investor_info":
                return investorInfoFilter;
            case "today_big_deal_overflow":
                return bigBuyOverflowFilter;
            case "today_big_deal":
                return bigBuyFilter;
            case "today_big_deal_high_volum":
                return bigBuyHighVolumeFilter;
            case "big_dish_stock":
                return bigDishStockFilter;
            case "middle_dish_stock":
                return middleDishStockFilter;
            case "little_dish_stock":
                return lowDishStockFilter;
        }
        return roeIncreasingFilter;
    }

}
