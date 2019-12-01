package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataHourService;
import io.philoyui.qmier.qmiermanager.service.impl.DataDownloader;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/data_hour")
public class DataHourController {

    @Autowired
    private DataHourService dataHourService;

    @Autowired
    private DataDownloader dataDownloader;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataDownloader.process(DataType.Hour, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                dataHourService.deleteBySymbol(financialProductEntity.getSymbol());
                List<DataHourEntity> dataHourEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    DataHourEntity dataHourEntity = new DataHourEntity();
                    dataHourEntity.setSymbol(financialProductEntity.getSymbol());
                    dataHourEntity.setDay(historyData.getDay());
                    dataHourEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    dataHourEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    dataHourEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    dataHourEntity.setLow(Double.parseDouble(historyData.getLow()));
                    dataHourEntity.setClose(Double.parseDouble(historyData.getClose()));
                    dataHourEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    dataHourEntityList.add(dataHourEntity);
                }
                dataHourService.insertAll(dataHourEntityList);
            }
        });

        return ResponseEntity.ok("success");
    }

}
