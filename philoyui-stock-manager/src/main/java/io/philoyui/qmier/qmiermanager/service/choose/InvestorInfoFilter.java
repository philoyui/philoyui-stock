package io.philoyui.qmier.qmiermanager.service.choose;

import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 投资者活动关系表
 */
@Component
public class InvestorInfoFilter implements StockFilter {

    @Override
    public Set<String> filterSymbol(String param1, String param2, String param3) {
        String endData = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startData = DateFormatUtils.format(DateUtils.addDays(new Date(),-2), "yyyy-MM-dd");
        Set<String> stockSet = new HashSet<>();
        String fetchUrl = "http://ircs.p5w.net/ircs/interaction/moreIrmInfoList.do?pageNo=1&irmpagesize=40&beginDate=" + startData + "&endDate=" + endData;
        Connection connect = Jsoup.connect(fetchUrl);
        try {
            Document document = connect.get();
            Elements articleElements = document.select("body > div > dl");
            for (Element articleElement : articleElements) {
                String symbol = articleElement.select("dt.wz_text > a:nth-child(1)").text().replace("· ","");
                stockSet.add(buildSymbol(symbol));
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
