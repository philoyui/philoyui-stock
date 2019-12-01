package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
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
@RequestMapping("/admin/data_day")
public class DataDayController {

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private DataDownloader dataDownloader;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataDownloader.process(DataType.Day, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                dataDayService.deleteBySymbol(financialProductEntity.getSymbol());
                List<DataDayEntity> dataDayEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    DataDayEntity dataDayEntity = new DataDayEntity();
                    dataDayEntity.setSymbol(financialProductEntity.getSymbol());
                    dataDayEntity.setDay(historyData.getDay());
                    dataDayEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    dataDayEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    dataDayEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    dataDayEntity.setLow(Double.parseDouble(historyData.getLow()));
                    dataDayEntity.setClose(Double.parseDouble(historyData.getClose()));
                    dataDayEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    dataDayEntityList.add(dataDayEntity);
                }
                dataDayService.insertAll(dataDayEntityList);
            }
        });

        return ResponseEntity.ok("success");
    }
}
