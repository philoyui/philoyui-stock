package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RsiIndicatorProviderTest {

    @Autowired
    private RsiIndicatorProvider rsiIndicatorProvider;

    @Test
    public void processTags() {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSymbol("sh601390");
        List<TagStockEntity> tagStockEntities = rsiIndicatorProvider.processTags(stockEntity);
        System.out.println(new GsonBuilder().create().toJson(tagStockEntities));
    }
}