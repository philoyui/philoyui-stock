package io.philoyui.stock.service.impl;

import io.philoyui.stock.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {

    @Autowired
    private StockService stockService;

    @Test
    public void test_fetch_product_data_array(){
        stockService.fetchProductDataArray("hgt_hk",80);
    }

}