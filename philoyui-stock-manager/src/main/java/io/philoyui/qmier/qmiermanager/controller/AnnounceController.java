package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/announce")
public class AnnounceController {


    @Autowired
    private AnnounceService announceService;

    /**
     * 抓取所有的公告信息，共500*100条
     * @return
     */
    @RequestMapping("/downloadHistory")
    public ResponseEntity<String> fetch() {
        for (int pageNo = 406; pageNo <= 500; pageNo++) {
            announceService.downloadAnnounce(pageNo);
        }
        return ResponseEntity.ok("success");
    }

}
