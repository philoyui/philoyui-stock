package io.philoyui.qmier.qmiermanager.timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreTaskTimerTest {

    @Autowired
    private CoreTaskTimer coreTaskTimer;

    @Test
    public void test_core(){
        coreTaskTimer.executeDayTask();
    }

}