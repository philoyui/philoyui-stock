package io.philoyui.tradingview.client;

public interface TradingViewClient {

    <T extends TradingViewResponse> T execute(TradingViewRequest<T> request);


}
