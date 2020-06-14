package io.philoyui.qmier.qmiermanager.service.indicator.day;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.east.data.BigBuyData;
import io.philoyui.qmier.qmiermanager.client.east.response.BigBuyResponse;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class BigBuyIndicatorProvider implements IndicatorProvider {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private TagStockService tagStockService;

    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else if(code.startsWith("3")){
            return "sz" + code;
        } else {
            return code;
        }
    }

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public String identifier() {
        return "big_buy";
    }

    @Override
    public void cleanOldData() {
        tagStockService.deleteByTagName("大宗交易");
        tagStockService.deleteByTagName("大容量大宗交易");
        tagStockService.deleteByTagName("溢价大宗交易");
    }

    @Override
    public void processGlobal() {
        String endData1 = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startData1 = DateFormatUtils.format(DateUtils.addDays(new Date(),-1), "yyyy-MM-dd");
        handleBigData(endData1, startData1,-1);

        String endData2 = DateFormatUtils.format(DateUtils.addDays(new Date(),-1), "yyyy-MM-dd");
        String startData2 = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");
        handleBigData(endData2, startData2,-2);


        String endData3 = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");
        String startData3 = DateFormatUtils.format(DateUtils.addDays(new Date(),-3), "yyyy-MM-dd");
        handleBigData(endData3, startData3,-3);
    }

    private void handleBigData(String endData, String startData, int lastIndex) {
        String fetchUrl = "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=DZJYGGTJ&token=70f12f2f4f091e459a279469fe49eca5&cmd=&st=Cjeltszb&sr=-1&p=1&ps=500&js={pages:(tp),data:(x)}&filter=((TDATE%3E=^" + startData + "^%20and%20TDATE%3C=^" + endData + "^))&rt=52607034";
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
                tagStockService.tagStock(buildSymbol(bigBuyData.getSymbol()),"大宗交易", DateUtils.parseDate(bigBuyData.getCreatedTime(),"yyyy-MM-dd'T'HH:mm:ss"), IntervalType.Day,lastIndex);
                if(bigBuyData.getDealAmount()>1000) {
                    tagStockService.tagStock(buildSymbol(bigBuyData.getSymbol()),"大容量大宗交易", DateUtils.parseDate(bigBuyData.getCreatedTime(),"yyyy-MM-dd'T'HH:mm:ss"),IntervalType.Day,lastIndex);
                }
                if(bigBuyData.getPremiumDiscount()>0) {
                    tagStockService.tagStock(buildSymbol(bigBuyData.getSymbol()),"溢价大宗交易", DateUtils.parseDate(bigBuyData.getCreatedTime(),"yyyy-MM-dd'T'HH:mm:ss"),IntervalType.Day,lastIndex);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
