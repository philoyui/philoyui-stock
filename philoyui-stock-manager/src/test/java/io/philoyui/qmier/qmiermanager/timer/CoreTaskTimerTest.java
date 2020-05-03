package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.QmierManagerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CoreTaskTimerTest {

    @Autowired
    private CoreTaskTimer coreTaskTimer;

    @Test
    public void test_core(){
        coreTaskTimer.executeDayTask();
    }

}