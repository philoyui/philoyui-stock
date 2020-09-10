package io.philoyui.qmier.qmiermanager.macd;

import io.philoyui.qmier.qmiermanager.service.impl.StockIndicatorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayTaskTest {

    @Autowired
    private StockIndicatorServiceImpl stockIndicatorService;

    @Test
    public void test_execute(){
        stockIndicatorService.executeDayTask();
    }


}
