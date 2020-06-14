package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.Data30minDao;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Data30minServiceImpl extends GenericServiceImpl<Data30minEntity,Long> implements Data30minService {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private Data30minDao data30minDao;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<Data30minEntity, Long> getDao() {
        return data30minDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        data30minDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<Data30minEntity> data30minEntityList) {
        data30minDao.saveAll(data30minEntityList);
    }

    @Override
    public void downloadHistory(FocusStockEntity focusStockEntity) {

        String fetchUrl = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+focusStockEntity.getSymbol()+"&scale="+ TaskType.Min_30.getMinute()+"&ma=no&datalen=80";
        try {
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            KLineData[] KLineDataArray = gson.fromJson(response.body(), KLineData[].class);

            List<Data30minEntity> dataDayEntityList = new ArrayList<>();
            for (KLineData KLineData : KLineDataArray) {
                Data30minEntity data30minEntity = new Data30minEntity();
                data30minEntity.setSymbol(focusStockEntity.getSymbol());
                data30minEntity.setName(focusStockEntity.getStockName());
                data30minEntity.setDay(KLineData.getDay());
                data30minEntity.setDateString(DateFormatUtils.format(KLineData.getDay(), "yyyy-MM-dd HH:mm:ss"));
                data30minEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                data30minEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                data30minEntity.setLow(Double.parseDouble(KLineData.getLow()));
                data30minEntity.setClose(Double.parseDouble(KLineData.getClose()));
                data30minEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                data30minEntity.setRecordTime(new Date());
                dataDayEntityList.add(data30minEntity);
            }
            insertAll(dataDayEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}