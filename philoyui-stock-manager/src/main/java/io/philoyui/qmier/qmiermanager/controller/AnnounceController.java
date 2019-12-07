package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClient;
import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClientImpl;
import io.philoyui.qmier.qmiermanager.client.east.data.AnnounceGetData;
import io.philoyui.qmier.qmiermanager.client.east.request.AnnounceGetRequest;
import io.philoyui.qmier.qmiermanager.client.east.response.AnnounceGetResponse;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;
import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import io.philoyui.qmier.qmiermanager.service.impl.DataDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/announce")
public class AnnounceController {

    private static final Logger LOG = LoggerFactory.getLogger(AnnounceController.class);

    @Autowired
    private AnnounceService announceService;

    /**
     * 抓取所有的公告信息，共500*100条
     * @return
     */
    @RequestMapping("/downloadHistory")
    public ResponseEntity<String> fetch() {
        EastMoneyClient client = new EastMoneyClientImpl();
        for (int i = 299; i <= 500; i++) {

            try {
                AnnounceGetRequest request = new AnnounceGetRequest();
                request.setPageNo(i);
                request.setPageSize(100);
                AnnounceGetResponse response = client.execute(request);
                for (AnnounceGetData data : response.getData()) {
                    if (!announceService.existsByDetailUrl(data.getUrl())) { //未录入的公告

                        //创业板不记录
                        String code = data.getAnnounceStockInfoList().get(0).getStockCode();
                        if (code.startsWith("300")) {
                            continue;
                        }

                        AnnounceEntity announceEntity = new AnnounceEntity();
                        announceEntity.setTitle(data.getNoticeTitle());
                        announceEntity.setSymbol(buildSymbol(code));
                        announceEntity.setPublishTime(data.getPublishTime());
                        announceEntity.setDetailUrl(data.getUrl());
                        announceEntity.setAnnounceType(data.getAnnounceTypeInfoList().get(0).getAnnounceType());
                        announceService.insert(announceEntity);
                    }
                }
                LOG.info("下载公告数据成功，当前页数为：" + i);
            }catch (Exception e){
                e.printStackTrace();
                LOG.error("下载公告数据失败，当前页数为：" + i);
            }
        }
        return ResponseEntity.ok("success");
    }


    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else {
            return code;
        }
    }
}
