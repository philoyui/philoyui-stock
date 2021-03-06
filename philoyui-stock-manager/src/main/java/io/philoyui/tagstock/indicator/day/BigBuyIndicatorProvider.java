package io.philoyui.tagstock.indicator.day;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.stock.client.east.data.BigBuyData;
import io.philoyui.stock.client.east.response.BigBuyResponse;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void processGlobal() {
        String dayString1 = DateFormatUtils.format(DateUtils.addHours(new Date(),-16), "yyyy-MM-dd");
        handleBigData(dayString1,-1);

        String dayString2 = DateFormatUtils.format(DateUtils.addDays(DateUtils.addHours(new Date(),-16),-1), "yyyy-MM-dd");
        handleBigData(dayString2,-2);


        String dayString3 = DateFormatUtils.format(DateUtils.addDays(DateUtils.addHours(new Date(),-16),-2), "yyyy-MM-dd");
        handleBigData(dayString3,-7);
    }

    private void handleBigData(String datString, int lastIndex) {
        String fetchUrl = "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=DZJYGGTJ&token=70f12f2f4f091e459a279469fe49eca5&cmd=&st=Cjeltszb&sr=-1&p=1&ps=500&js={pages:(tp),data:(x)}&filter=((TDATE%3E=^" + datString + "^%20and%20TDATE%3C=^" + datString + "^))&rt=52607034";
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
