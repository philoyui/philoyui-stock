package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.service.tag.GlobalTagMarker;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InvestorInfoTagMarker extends GlobalTagMarker {

    @Override
    public void processGlobal() {
        String endData = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startData = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");
        List<String> stockList = new ArrayList<>();
        String fetchUrl = "http://ircs.p5w.net/ircs/interaction/moreIrmInfoList.do?pageNo=1&irmpagesize=40&beginDate=" + startData + "&endDate=" + endData;
        Connection connect = Jsoup.connect(fetchUrl);
        try {
            Document document = connect.get();
            Elements articleElements = document.select("body > div > dl");
            for (Element articleElement : articleElements) {
                String symbol = articleElement.select("dt.wz_text > a:nth-child(1)").text().replace("· ","");
                stockList.add(buildSymbol(symbol));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        this.tagStocks(stockList,"投资者关系活动记录表");
    }

    @Override
    public boolean supportDate() {
        return true;
    }

    @Override
    public boolean supportWeek() {
        return false;
    }

    @Override
    public boolean supportMonth() {
        return false;
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
