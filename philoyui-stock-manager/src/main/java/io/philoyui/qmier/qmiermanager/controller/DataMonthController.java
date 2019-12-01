package io.philoyui.qmier.qmiermanager.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
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
@RequestMapping("/admin/data_month")
public class DataMonthController {

    @Autowired
    private DataMonthService dataMonthService;

    @Autowired
    private DataDownloader dataDownloader;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataDownloader.process(DataType.Month, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                dataMonthService.deleteBySymbol(financialProductEntity.getSymbol());
                List<DataMonthEntity> dataMonthEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    DataMonthEntity dataMonthEntity = new DataMonthEntity();
                    dataMonthEntity.setSymbol(financialProductEntity.getSymbol());
                    dataMonthEntity.setDay(historyData.getDay());
                    dataMonthEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    dataMonthEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    dataMonthEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    dataMonthEntity.setLow(Double.parseDouble(historyData.getLow()));
                    dataMonthEntity.setClose(Double.parseDouble(historyData.getClose()));
                    dataMonthEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    dataMonthEntityList.add(dataMonthEntity);
                }
                dataMonthService.insertAll(dataMonthEntityList);
            }
        });

        return ResponseEntity.ok("success");
    }
}
