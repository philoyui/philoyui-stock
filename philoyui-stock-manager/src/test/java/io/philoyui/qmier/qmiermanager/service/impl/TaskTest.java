package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.tagstock.timer.DayTagTimer;
import io.philoyui.qmier.qmiermanager.tagstock.timer.MinTagTimer;
import io.philoyui.qmier.qmiermanager.tagstock.timer.MonthTagTimer;
import io.philoyui.qmier.qmiermanager.tagstock.timer.WeekTagTimer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

    @Autowired
    private DayTagTimer dayTagTimer;

    @Autowired
    private WeekTagTimer weekTagTimer;

    @Autowired
    private MinTagTimer minTagTimer;

    @Autowired
    private MonthTagTimer monthTagTimer;

    @Test
    public void test_week(){
        weekTagTimer.execute();
    }

    @Test
    public void test_day(){
        dayTagTimer.execute();
    }

    @Test
    public void test_min(){
        minTagTimer.execute();
    }

    @Test
    public void test_month(){
        monthTagTimer.execute();
    }

}