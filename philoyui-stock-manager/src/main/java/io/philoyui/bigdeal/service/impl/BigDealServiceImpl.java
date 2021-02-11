package io.philoyui.bigdeal.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.bigdeal.dao.BigDealDao;
import io.philoyui.bigdeal.entity.BigDealEntity;
import io.philoyui.bigdeal.service.BigDealService;
import io.philoyui.stock.client.east.data.BigBuyData;
import io.philoyui.stock.client.east.response.BigBuyResponse;
import io.philoyui.stock.entity.enu.IntervalType;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BigDealServiceImpl extends GenericServiceImpl<BigDealEntity,Long> implements BigDealService {

    @Autowired
    private BigDealDao bigDealDao;

    private Gson gson = new GsonBuilder().create();

    @Override
    protected GenericDao<BigDealEntity, Long> getDao() {
        return bigDealDao;
    }

    @Override
    public void fetchLastData() {

        bigDealDao.deleteAll();

        String dayString1 = DateFormatUtils.format(DateUtils.addHours(new Date(),-16), "yyyy-MM-dd");
        String dayString2 = DateFormatUtils.format(DateUtils.addDays(new Date(),-31), "yyyy-MM-dd");

        String fetchUrl = "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=DZJYXQ&token=70f12f2f4f091e459a279469fe49eca5&cmd=&st=Cjeltszb&sr=-1&p=1&ps=1000&js={pages:(tp),data:(x)}&filter=((TDATE%3E=^" + dayString2 + "^%20and%20TDATE%3C=^" + dayString1 + "^))&rt=52607034";
        try {
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            String result = response.body().replaceAll("pages", "\"pages\"")
                    .replaceAll("data", "\"data\"");

            BigBuyResponse bigBuyResponse = gson.fromJson(result, BigBuyResponse.class);

            for (BigBuyData bigBuyData : bigBuyResponse.getData()) {
                bigDealDao.save(BigDealEntity.constructFrom(bigBuyData));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
