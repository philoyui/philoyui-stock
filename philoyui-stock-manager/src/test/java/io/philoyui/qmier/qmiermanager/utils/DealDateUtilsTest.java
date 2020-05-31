package io.philoyui.qmier.qmiermanager.utils;

import org.junit.Test;

public class DealDateUtilsTest {

    @Test
    public void test_get_current_week(){
        String dayString = DealDateUtils.getLastDealDayString();
        System.out.println(dayString);
    }

}