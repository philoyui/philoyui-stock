package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.Data15minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Data15minTimer {

    @Autowired
    private Data15minService data15minService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="* * 16 * * ? ") //下午4点
    public void fetcher(){
        data15minService.downloadHistory();
    }

}
