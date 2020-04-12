package io.philoyui.qmier.qmiermanager.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.service.WeekDataService;
import io.philoyui.qmier.qmiermanager.service.impl.KLineDataDownloaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_week")
public class DataWeekController {

    @Autowired
    private WeekDataService weekDataService;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        weekDataService.downloadHistory();
        return ResponseEntity.ok("success");
    }

}
