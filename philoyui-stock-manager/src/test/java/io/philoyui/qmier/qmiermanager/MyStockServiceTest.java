package io.philoyui.qmier.qmiermanager;

import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.DayDataService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStockServiceTest {

    @Autowired
    private DayDataService dayDataService;

    @Autowired
    private StockService stockService;

    /**
     * 用例：自选来源：1. KD周线金叉 2. 大宗交易 3. 投资者关系 4. 长期盘整，5. 八仙战法
     */
    @Test
    public void test_(){

        StockEntity stockEntity = stockService.findBySymbol("sz002975");

        StockHistoryData stockHistoryData = dayDataService.findStockHistoryData(stockEntity);

        double[] closeArray = stockHistoryData.getCloseArray();

        ArrayUtils.reverse(closeArray);

        MacdResult macdResult = TalibUtils.macd(closeArray, 12, 26, 9);

        System.out.println(Arrays.toString(closeArray));

        System.out.println(Arrays.toString(macdResult.getMacdResult()));

        System.out.println(Arrays.toString(macdResult.getSignalResult()));

        System.out.println(Arrays.toString(macdResult.getHistResult()));

    }

    /**
     * 用例：投资者活动关系表每天定时器抓取，添加到自选股中
     */

}
