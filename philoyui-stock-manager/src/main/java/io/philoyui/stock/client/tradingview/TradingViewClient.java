package io.philoyui.stock.client.tradingview;

public interface TradingViewClient {

    <T extends TradingViewResponse> T execute(TradingViewRequest<T> request);


}
