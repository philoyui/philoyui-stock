package io.philoyui.tagstock.indicator.minute;

import io.philoyui.stock.entity.StockEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Macd30IndicatorProviderTest {

    @Autowired
    private Macd30IndicatorProvider macd30IndicatorProvider;

    @Test
    public void processTags() {

        StockEntity stockEntity = new StockEntity();
        stockEntity.setSymbol("sz002312");
        macd30IndicatorProvider.processTags(stockEntity);

    }
}