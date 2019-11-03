package io.philoyui.qmier.qmiermanager.client.tradingview;

import java.io.Serializable;
import java.util.Map;

public interface TradingViewRequest<T extends TradingViewResponse> extends Serializable {

    Map<String, String> getMapParameters();

    String getMethodUrl();

    Class<T> getResponseClass();

}
