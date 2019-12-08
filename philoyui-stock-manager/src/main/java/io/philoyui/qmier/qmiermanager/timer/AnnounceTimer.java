package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClient;
import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClientImpl;
import io.philoyui.qmier.qmiermanager.client.east.data.AnnounceGetData;
import io.philoyui.qmier.qmiermanager.client.east.request.AnnounceGetRequest;
import io.philoyui.qmier.qmiermanager.client.east.response.AnnounceGetResponse;
import io.philoyui.qmier.qmiermanager.controller.AnnounceController;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;
import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnounceTimer {

    private static final Logger LOG = LoggerFactory.getLogger(AnnounceController.class);

    @Autowired
    private AnnounceService announceService;

    @Scheduled(cron="0 0/23 * * * ?") //每20分钟执行一次
    public void fetch(){
        EastMoneyClient client = new EastMoneyClientImpl();
        for (int pageNo = 1; pageNo <= 10; pageNo++) {
            announceService.downloadAnnounce(pageNo);
        }
    }

}
