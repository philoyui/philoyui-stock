package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.DataDayDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import io.philoyui.qmier.qmiermanager.to.ProductData;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataDayServiceImpl extends GenericServiceImpl<DataDayEntity,Long> implements DataDayService {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private DataDayDao dataDayDao;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<DataDayEntity, Long> getDao() {
        return dataDayDao;
    }

    @Override
    public void insertAll(List<DataDayEntity> dataDayEntityList) {
        dataDayDao.saveAll(dataDayEntityList);
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataDayDao.deleteBySymbol(symbol);
    }

    @Override
    public void downloadHistory() {
        dataDayDao.deleteAll();
        taskTracerImpl.trace(TaskType.Day, taskCounter -> {
            dataDownloaderImpl.download(TaskType.Day, (financialProductEntity, kLineDataArray) -> {
                List<DataDayEntity> dataDayEntityList = new ArrayList<>();
                for (KLineData KLineData : kLineDataArray) {
                    DataDayEntity dataDayEntity = new DataDayEntity();
                    dataDayEntity.setSymbol(financialProductEntity.getSymbol());
                    dataDayEntity.setName(financialProductEntity.getName());
                    dataDayEntity.setDay(KLineData.getDay());
                    dataDayEntity.setDateString(DateFormatUtils.format(KLineData.getDay(), "yyyy-MM-dd HH:mm:ss"));
                    dataDayEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                    dataDayEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                    dataDayEntity.setLow(Double.parseDouble(KLineData.getLow()));
                    dataDayEntity.setClose(Double.parseDouble(KLineData.getClose()));
                    dataDayEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                    dataDayEntity.setRecordTime(new Date());
                    dataDayEntityList.add(dataDayEntity);
                }
                insertAll(dataDayEntityList);
                taskCounter.increase();
            });
        });
    }

    @Override
    public void processEstimateDayData() {

        Date date = null;

        String dataString = DateFormatUtils.format(new Date(),"yyyy-MM-dd 00:00:00");
        try {
            date = DateUtils.parseDate(dataString, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(dataDayDao.countByDateString(dataString)>0){
            return;
        }

        int pageNo = 1;

        while(true){
            ProductData[] productDataArray = fetchCurrentProductData(pageNo);
            if(productDataArray==null||productDataArray.length==0){
                break;
            }

            List<DataDayEntity> dataDayEntities = new ArrayList<>();

            for (ProductData productData : productDataArray) {

                if(NumberUtils.isDigits(productData.getOpen())&&NumberUtils.isDigits(productData.getHigh())&&NumberUtils.isDigits(productData.getLow())&&NumberUtils.isDigits(productData.getSell())){
                    return;
                }

                DataDayEntity dataDayEntity = new DataDayEntity();
                dataDayEntity.setSymbol(productData.getSymbol());
                dataDayEntity.setDay(date);
                dataDayEntity.setDateString(dataString);
                dataDayEntity.setOpen(Double.parseDouble(productData.getOpen()));
                dataDayEntity.setHigh(Double.parseDouble(productData.getHigh()));
                dataDayEntity.setLow(Double.parseDouble(productData.getLow()));
                dataDayEntity.setClose(Double.parseDouble(productData.getSell()));
                dataDayEntity.setVolume(productData.getVolume());
                dataDayEntity.setRecordTime(new Date());
                dataDayEntity.setName(productData.getName());
                dataDayEntities.add(dataDayEntity);
            }

            dataDayDao.saveAll(dataDayEntities);

            pageNo++;
        }

    }

    @Override
    public double[] findCloseData(StockEntity stockEntity) {
        SearchFilter pagedSearchFilter = SearchFilter.getPagedSearchFilter(0, 160);
        pagedSearchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        pagedSearchFilter.add(Order.desc("day"));
        PageObject<DataDayEntity> pageObjects = this.paged(pagedSearchFilter);
        return pageObjects.getContent().stream().mapToDouble(DataDayEntity::getClose).toArray();
    }

    private ProductData[] fetchCurrentProductData(int pageNo) {

        String fetchUrl = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page="+pageNo+"&num=100&sort=symbol&asc=1&node=sh_a&symbol=&_s_r_a=page";
        Connection.Response response = null;
        try {
            response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = response.body().replaceAll("symbol","\"symbol\"")
                .replaceAll("code","\"code\"")
                .replaceAll("name","\"name\"")
                .replaceAll("trade","\"trade\"")
                .replaceAll("pricechange","\"pricechange\"")
                .replaceAll("changepercent","\"changepercent\"")
                .replaceAll("sell","\"sell\"")
                .replaceAll("settlement","\"settlement\"")
                .replaceAll("open","\"open\"")
                .replaceAll("high","\"high\"")
                .replaceAll("low","\"low\"")
                .replaceAll("volume","\"volume\"")
                .replaceAll("amount","\"amount\"")
                .replaceAll("ticktime","\"ticktime\"")
                .replaceAll("per:","\"per\":")
                .replaceAll("pb","\"pb\"")
                .replaceAll("mktcap","\"mktcap\"")
                .replaceAll("nmc","\"nmc\"")
                .replaceAll("buy","\"buy\"")
                .replaceAll("turnoverratio","\"turnoverratio\"")
                ;

        return gson.fromJson(result, ProductData[].class);

    }
}