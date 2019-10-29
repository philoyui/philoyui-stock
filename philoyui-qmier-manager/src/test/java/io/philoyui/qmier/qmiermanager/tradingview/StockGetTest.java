package io.philoyui.qmier.qmiermanager.tradingview;

import io.philoyui.qmier.qmiermanager.client.tradingview.TradingViewClientImpl;
import io.philoyui.qmier.qmiermanager.client.tradingview.TradingViewFilter;
import io.philoyui.qmier.qmiermanager.client.tradingview.TradingViewResponse;
import org.junit.Test;

public class StockGetTest {

    @Test
    public void test_get_stock(){

        TradingViewClientImpl tradingViewClient = new TradingViewClientImpl();


        TradingViewFilter filter = new TradingViewFilter(1,10);
        filter.addSort("Recommend.All","desc");
        TradingViewResponse response = tradingViewClient.execute(filter);

    }


}
