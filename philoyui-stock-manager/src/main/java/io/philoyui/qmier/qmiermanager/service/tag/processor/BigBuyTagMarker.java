package io.philoyui.qmier.qmiermanager.service.tag.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.east.data.BigBuyData;
import io.philoyui.qmier.qmiermanager.client.east.response.BigBuyResponse;
import io.philoyui.qmier.qmiermanager.service.tag.GlobalTagMarker;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BigBuyTagMarker extends GlobalTagMarker {

    private Gson gson = new GsonBuilder().create();

    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else {
            return code;
        }
    }

    @Override
    public void processGlobal() {

        String endData = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startData = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");

        List<String> stockSet = new ArrayList<>();
        List<String> highVolumeStockSet = new ArrayList<>();
        List<String> overflowStockSet = new ArrayList<>();

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
                stockSet.add(buildSymbol(bigBuyData.getSymbol()));
                if(bigBuyData.getDealAmount()>1000) {
                    highVolumeStockSet.add(bigBuyData.getSymbol());
                }
                if(bigBuyData.getPremiumDiscount()>0) {
                    overflowStockSet.add(bigBuyData.getSymbol());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        this.tagStocks(stockSet,"大宗交易");
        this.tagStocks(highVolumeStockSet,"大容量大宗交易");
        this.tagStocks(overflowStockSet,"溢价大宗交易");
    }

}
