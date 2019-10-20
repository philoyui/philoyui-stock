package io.philoyui.qmier.qmiermanager;

import io.philoyui.qmier.qmiermanager.service.QmierService;
import io.philoyui.qmier.qmiermanager.service.impl.QmierServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QmierServiceTest {

    private QmierService qmierService = new QmierServiceImpl();

    /**
     * 用例一：展示所有的Qmier，可以按照城市筛选，按照最新更新事件筛选
     */


    /**
     * 用例二：详细展示Qmier，名字，价格记录(来源文章)，文章历史，联系方式（QQ微信）,正文
     */

    /**
     * 用例三：每小时执行定时器，扫描所有的Site，获取列表页按时间排序地址，通过HTTP抓取所有的详细页地址，比对是否有抓取，未抓取则加入队列
     */

    /**
     * 用例四：每小时执行定时器，每个SiteType一个线程，获取该site下的待抓取详情页，解析详情页信息放入Article表中，解析出的页面信息方式Article，隔半分钟再抓取
     */


    /**
     * 用例五：管理员阅读Article，完善各部分信息（补充，评论，图片），，标记审阅完成
     */

    @Test
    public void test_jsoup() throws IOException {

        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "9999");

        Document doc = Jsoup.connect("http://www.aishxs.biz/forum.php?mod=forumdisplay&fid=38&filter=author&orderby=dateline&page=2").get();
        Elements threadDivs = doc.select("[id^=normalthread_]");
        for (Element threadDiv : threadDivs) {
            System.out.println(threadDiv.select("tr > th > a.s.xst").text()); //标题
            System.out.println(threadDiv.select("tr > th > a.s.xst").attr("href")); //详细页链接
            System.out.println(threadDiv.select("tr > th > em > a").text()); //地区
            System.out.println(threadDiv.select("tr > td:nth-child(3) > cite > a").text()); //作者名
            System.out.println(threadDiv.select("tr > td:nth-child(3) > em > span > span").attr("title")); //发帖日期
        }

    }


    @Test
    public void test_jsoup_detail() throws IOException {

        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "9999");


        /**
         * nzrE_2132_viewid
         */
        Document doc = Jsoup.connect("http://www.aishxs.biz/forum.php?mod=viewthread&tid=501162&extra=page%3D3%26filter%3Dauthor%26orderby%3Ddateline")
                .cookie("nzrE_2132_auth", "c297E9MPv29TwRMkPK58EKIJKGEg34axRbKOPEo0j%2BZa2kebwIDdUF9kkupVeb8Bz0fkD7DiomaKYZi5cPBR%2FqhzVw")
                .cookie("nzrE_2132_saltkey","eRBn59ve")
                .get();

        Map<String,String> idImagePathMap = new HashMap<>();
        Elements threadDivs = doc.select(" img[zoomfile^=data]");
        for (Element threadDiv : threadDivs) {
            idImagePathMap.put(threadDiv.attr("zoomfile"),threadDiv.attr("id"));
        }

        Elements titleElement = doc.select("#thread_subject");
        Elements contentElements = doc.select("[id^=postmessage_]");

        System.out.println(contentElements.eachText().get(0));

        String title = titleElement.text();
        String content = contentElements.eachText().get(0);


    }

}
