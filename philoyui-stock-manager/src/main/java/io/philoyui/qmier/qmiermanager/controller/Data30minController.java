package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
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
@RequestMapping("/admin/data_30min")
public class Data30minController {

    @Autowired
    private Data30minService data30minService;

    @Autowired
    private DataDownloader dataDownloader;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataDownloader.process(DataType.Min_30, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                data30minService.deleteBySymbol(financialProductEntity.getSymbol());
                List<Data30minEntity> data30minEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    Data30minEntity data30minEntity = new Data30minEntity();
                    data30minEntity.setSymbol(financialProductEntity.getSymbol());
                    data30minEntity.setDay(historyData.getDay());
                    data30minEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    data30minEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    data30minEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    data30minEntity.setLow(Double.parseDouble(historyData.getLow()));
                    data30minEntity.setClose(Double.parseDouble(historyData.getClose()));
                    data30minEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    data30minEntityList.add(data30minEntity);
                }
                data30minService.insertAll(data30minEntityList);
            }
        });

        return ResponseEntity.ok("success");
    }

}
