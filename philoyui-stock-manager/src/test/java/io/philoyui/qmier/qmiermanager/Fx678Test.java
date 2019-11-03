package io.philoyui.qmier.qmiermanager;

import io.philoyui.qmier.qmiermanager.client.fx678.Fx678Client;
import io.philoyui.qmier.qmiermanager.client.fx678.Fx678ClientImpl;
import io.philoyui.qmier.qmiermanager.client.fx678.data.ArticleData;
import io.philoyui.qmier.qmiermanager.client.fx678.request.NewArticleGetRequest;
import io.philoyui.qmier.qmiermanager.client.fx678.response.NewArticleGetResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Fx678Test {

    @Test
    public void test_new_article_fetcher(){

        Fx678Client client = new Fx678ClientImpl();

        NewArticleGetRequest request = new NewArticleGetRequest();
        request.setPageNo(1);
        request.setPageSize(5);
        request.setCategoryId("101");
        client.execute(request);

        NewArticleGetResponse response = client.execute(request);

        for (ArticleData articleData : response.getData()) {
            System.out.println( articleData.getAnalRealName() + "-" + articleData.getArticleTitle() +"-" + articleData.getCategoryId() + "-" + articleData.getYdate() + "-" + articleData.getPdate());
        }

//        String fetchUrl = "https://pinglun.fx678.com/Ajax/changeIndexNewsByCategory";
//        Connection connect = Jsoup.connect(fetchUrl);
//        connect.ignoreContentType(true);
//        connect.header("Content-Type", "application/json");
//        connect.data("category_id","102,103");
//        connect.data("pageSize","12");
//        connect.data("page","1");
//        try {
//            Document document = connect.post();
//            System.out.println(document.text());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void test_article_list_fetcher(){

        //读取文章列表

        String[] categoryIds = new String[]{"102"};

        for (String categoryId : categoryIds) {
            for (int pageNo = 1; pageNo <= 5; pageNo++) {
                String fetchUrl = "https://pinglun.fx678.com/category/" + categoryId + "?page=" + pageNo;
                Connection connect = Jsoup.connect(fetchUrl);
                try {
                    Document document = connect.get();
                    Elements articleElements = document.select("#showArticle > .category-list__item");
                    for (Element articleElement : articleElements) {
                        System.out.println(articleElement.select(".category-list__content > h3").text()); //title
                        System.out.println(articleElement.select(".category-list__content").attr("href")); //链接
                        System.out.println(articleElement.select(".category-list__author span").text()); //title
                        String data = articleElement.select(".category-list__time").text(); //title
                        Date date = DateUtils.parseDateStrictly(data, "MM月dd日 HH:mm");
                        System.out.println(DateUtils.setYears(date,Integer.parseInt(DateFormatUtils.format(new Date(),"yyyy"))));
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void test_article_fetcher(){

            String fetchUrl = "https://pinglun.fx678.com/article/201911022146481745.shtml";

            Connection connect = Jsoup.connect(fetchUrl);

            try {
                Document document = connect.get();

                System.out.println(document.select("#article-cont").html()); //title
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
