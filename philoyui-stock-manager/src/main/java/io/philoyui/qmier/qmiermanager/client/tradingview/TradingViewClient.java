package io.philoyui.qmier.qmiermanager.client.tradingview;

public interface TradingViewClient {

    <T extends TradingViewResponse> T execute(TradingViewRequest<T> request);


}
