package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.tagstock.timer.DayTagTimer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayTaskServiceImplTest {

    @Autowired
    private DayTagTimer dayTagTimer;

    @Test
    public void test_(){
        dayTagTimer.execute();
    }


}