package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataMonthTimer {

    @Autowired
    private DataMonthService dataMonthService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="0 0 0 1 * ?") //每月1号0点
    public void fetcher(){
        dataMonthService.downloadHistory();
    }

}
