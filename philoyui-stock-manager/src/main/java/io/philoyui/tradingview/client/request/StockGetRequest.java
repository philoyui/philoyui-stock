package io.philoyui.tradingview.client.request;

import io.philoyui.tradingview.client.TradingViewRequest;
import io.philoyui.tradingview.client.TradingViewResponse;

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
