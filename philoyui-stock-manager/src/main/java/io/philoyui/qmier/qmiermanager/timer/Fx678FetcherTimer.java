package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Component
public class Fx678FetcherTimer {


    @Autowired
    private ArticleService articleService;

//    @Scheduled(cron="0 0/30 * * * ?") //每30分钟执行一次
    public void fetcher(){

        //读取文章列表
        String[] categoryIds = new String[]{"102","103","105","107","106","109","111","112","113"};
        for (String categoryId : categoryIds) {
            String fetchUrl = "https://pinglun.fx678.com/category/" + categoryId;
            Connection connect = Jsoup.connect(fetchUrl);
            try {
                Document document = connect.get();
                Elements articleElements = document.select("#showArticle > .category-list__item");
                for (Element articleElement : articleElements) {
                    String title = articleElement.select(".category-list__content > h3").text(); //title
                    String detailUrl = articleElement.select(".category-list__content").attr("href"); //链接
                    String analyst = articleElement.select(".category-list__author span").text(); //分析师
                    String createDateStr = articleElement.select(".category-list__time").text(); //创建时间
                    Date date = DateUtils.parseDateStrictly(createDateStr, "MM月dd日 HH:mm");
                    Date createDate = DateUtils.setYears(date, Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy")));

                    if(!articleService.existsBySiteIdentifierAndDetailUrl("fx678",detailUrl)){

                        connect = Jsoup.connect("https://pinglun.fx678.com" + detailUrl);
                        String content = connect.get().select("#article-cont").html();

                        ArticleEntity articleEntity = new ArticleEntity();
                        articleEntity.setTitle(title);
                        articleEntity.setContent(content);
                        articleEntity.setCreatedTime(createDate);
                        articleEntity.setAnalyst(analyst);
                        articleEntity.setDetailUrl(detailUrl);
                        articleEntity.setSiteIdentifier("fx678");
                        articleService.insert(articleEntity);

                        System.out.println("抓取文章成功：" + title);
                    }

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }

    }

}
