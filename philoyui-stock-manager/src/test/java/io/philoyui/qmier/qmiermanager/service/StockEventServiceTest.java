package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.StockEventEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.EventType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class StockEventServiceTest {

    @Autowired
    private StockEventService stockEventService;


    /**
     * 发现macd底背离
     */
    public void test_record(){
        StockEventEntity stockEventEntity = new StockEventEntity();
        stockEventEntity.setSymbol("");
        stockEventEntity.setDayString("");
        stockEventEntity.setRecordTime(new Date());
        stockEventEntity.setEventType(EventType.MACD_CROSS);
        stockEventEntity.setCloseValue(1.1);
        stockEventEntity.setRemark("各种值为");
        stockEventService.recordEvent(stockEventEntity);
    }

    /**
     * 今日底背离股票
     */

}