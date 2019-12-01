package io.philoyui.qmier.qmiermanager.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import io.philoyui.qmier.qmiermanager.service.impl.DataDownloader;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/data_week")
public class DataWeekController extends DataController{

    @Autowired
    private DataWeekService dataWeekService;

    @Autowired
    private DataDownloader dataDownloader;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataWeekService.downloadHistory();
        return ResponseEntity.ok("success");
    }

}
