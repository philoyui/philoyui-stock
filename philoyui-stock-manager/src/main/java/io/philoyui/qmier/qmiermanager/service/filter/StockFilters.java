package io.philoyui.qmier.qmiermanager.service.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockFilters {

    @Autowired
    private RoeIncreasingFilter roeIncreasingFilter;

    @Autowired
    private RoeStableFilter roeStableFilter;

    public StockFilter select(String identifier) {
        switch (identifier){
            case "roe_increasing":
                return roeIncreasingFilter;
            case "roe_stable":
                return roeStableFilter;
        }
        return roeIncreasingFilter;
    }

}
