package io.philoyui.stock.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DealDateUtils {

    public static String getLastDealDayString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w==0){
            Date date = DateUtils.addDays(new Date(), -2);
            return DateFormatUtils.format(date,"yyyy-MM-dd");
        }else if(w==6){
            Date date = DateUtils.addDays(new Date(), -1);
            return DateFormatUtils.format(date,"yyyy-MM-dd");
        }else{
            Date date = DateUtils.addHours(new Date(), -20);
            return DateFormatUtils.format(date,"yyyy-MM-dd");
        }
    }
}
