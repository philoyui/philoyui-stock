package io.philoyui.qmier.qmiermanager.service.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.east.data.BigBuyData;
import io.philoyui.qmier.qmiermanager.client.east.response.BigBuyResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class BigBuyOverflowFilter implements StockFilter {

    private Gson gson = new GsonBuilder().create();

    @Override
    public Set<String> filterSymbol(String param1, String param2, String param3) {
        Set<String> stockSet = new HashSet<>();

        String endData = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startData = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");

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
                if(bigBuyData.getPremiumDiscount()>0){
                    stockSet.add(buildSymbol(bigBuyData.getSymbol()));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return stockSet;
    }


    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else {
            return code;
        }
    }

}
