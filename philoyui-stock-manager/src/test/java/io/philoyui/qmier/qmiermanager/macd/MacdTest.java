package io.philoyui.qmier.qmiermanager.macd;

import io.philoyui.qmier.qmiermanager.analysis.service.MACDAnalysisService;
import io.philoyui.qmier.qmiermanager.analysis.domain.TimeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MacdTest {

    @Autowired
    private MACDAnalysisService macdAnalysisService;

    @Test
    public void test_macd_0(){
        String symbol = "sz002391";
        List<TimeData> dataTimes = macdAnalysisService.findLastAxis0Cross(symbol);
    }


    @Test
    public void test_macd_deviate(){
        String symbol = "sh601100";
        List<TimeData> dataTimes = macdAnalysisService.findDeviate(symbol);
    }
}
