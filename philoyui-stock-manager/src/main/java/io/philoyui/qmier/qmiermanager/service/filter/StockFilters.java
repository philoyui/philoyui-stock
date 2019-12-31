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
    private TurnoverFilter turnoverFilter;

    @Autowired
    private PerFilter perFilter;

    public StockFilter select(String identifier) {
        switch (identifier){
            case "roe_increasing":
                return roeIncreasingFilter;
            case "roe_stable":
                return roeStableFilter;
            case "quick_radio":
                return quickRadioFilter;
            case "turn_over":
                return turnoverFilter;
            case "low_price":
                return perFilter;
        }
        return roeIncreasingFilter;
    }

}
