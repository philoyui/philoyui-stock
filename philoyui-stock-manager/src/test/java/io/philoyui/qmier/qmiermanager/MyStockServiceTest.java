package io.philoyui.qmier.qmiermanager;

import io.philoyui.qmier.qmiermanager.service.StockService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStockServiceTest {

    @Autowired
    private StockService stockService;


    /**
     * 用例：投资者活动关系表每天定时器抓取，添加到自选股中
     */

}
