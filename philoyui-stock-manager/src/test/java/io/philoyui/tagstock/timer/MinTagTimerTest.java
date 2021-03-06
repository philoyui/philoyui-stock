package io.philoyui.tagstock.timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinTagTimerTest {

    @Autowired
    private MinTagTimer minTagTimer;

    @Test
    public void execute() {
        minTagTimer.execute();
    }

}