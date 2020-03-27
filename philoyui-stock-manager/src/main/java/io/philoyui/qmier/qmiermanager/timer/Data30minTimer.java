package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.Data30minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Data30minTimer implements TimeScheduler{

    @Autowired
    private Data30minService data30minService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="0 0 23 * * 1-5") //下午6点
    @Override
    public void schedule(){
        data30minService.downloadHistory();
    }

}
