package io.philoyui.mystock.timer;

import io.philoyui.data.timer.Min15Timer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Min15TimerTest {

    @Autowired
    private Min15Timer min15Timer;

    @Test
    public void execute() {

        min15Timer.execute();

    }

}