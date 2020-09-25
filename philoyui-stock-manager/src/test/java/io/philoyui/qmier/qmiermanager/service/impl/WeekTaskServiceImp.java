package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.tagstock.timer.WeekTagTimer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekTaskServiceImp {

    @Autowired
    private WeekTagTimer weekTagTimer;

    @Test
    public void test_(){
        weekTagTimer.execute();
    }

}