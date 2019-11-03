package io.philoyui.qmier.qmiermanager.client.tradingview.request;

import io.philoyui.qmier.qmiermanager.client.tradingview.TradingViewRequest;
import io.philoyui.qmier.qmiermanager.client.tradingview.TradingViewResponse;

import java.util.Map;

public class StockGetRequest implements TradingViewRequest<TradingViewResponse> {

    @Override
    public Map<String, String> getMapParameters() {
        return null;
    }

    @Override
    public String getMethodUrl() {
        return "https://scanner.tradingview.com/china/scan";
    }

    @Override
    public Class<TradingViewResponse> getResponseClass() {
        return TradingViewResponse.class;
    }
}
