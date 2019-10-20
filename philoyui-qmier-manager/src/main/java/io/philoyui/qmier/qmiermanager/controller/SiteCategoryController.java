package io.philoyui.qmier.qmiermanager.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.ArticleRecordEntity;
import io.philoyui.qmier.qmiermanager.entity.SiteCategoryEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.ArticleStatus;
import io.philoyui.qmier.qmiermanager.service.ArticleRecordService;
import io.philoyui.qmier.qmiermanager.service.SiteCategoryService;
import io.philoyui.qmier.qmiermanager.service.SiteService;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/admin/site_category")
public class SiteCategoryController {

    @Autowired
    private ArticleRecordService articleRecordService;

    @Autowired
    private SiteCategoryService siteCategoryService;

    @Autowired
    private SiteService siteService;

    private Gson gson = new GsonBuilder().create();

    @PutMapping("/fetch")
    public ResponseEntity<String> fetch(@RequestParam Long id) throws IOException, ParseException, InterruptedException {
        SiteCategoryEntity siteCategoryEntity = siteCategoryService.get(id);
        for (Integer i = 1; i <= siteCategoryEntity.getTotalPageNum(); i++) {
            String fetchUrl = siteCategoryEntity.getSiteListUrl().replaceAll("#pageNo#",String.valueOf(i));

            System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
            System.getProperties().setProperty("http.proxyPort", "9999");
            Document doc = Jsoup.connect(fetchUrl).get();

            Elements threadDivs = doc.select("[id^=normalthread_]");
            for (Element threadDiv : threadDivs) {

                String title = threadDiv.select("tr > th > a.s.xst").text();
                String author = threadDiv.select("tr > td:nth-child(3) > cite > a").text();
                String region = threadDiv.select("tr > th > em > a").text();
                String detailId = threadDiv.attr("id");
                String detailUrl = threadDiv.select("tr > th > a.s.xst").attr("href");
                String replyCount = threadDiv.select("tr > td.num > a").text();
                String readCount = threadDiv.select("tr > td.num > em").text();

                String createdTimeString = threadDiv.select("tr > td:nth-child(3) > em > span > span").attr("title");
                if(Strings.isNullOrEmpty(createdTimeString)){
                    createdTimeString = threadDiv.select("tr > td:nth-child(3) > em > span").text();
                }

                if(Strings.isNullOrEmpty(createdTimeString)) {
                    System.err.println(threadDiv.text() + author + "--" + region + "--" + createdTimeString);
                }else{
                    boolean existRecord = articleRecordService.existsBySiteCategoryIdAndDetailId(siteCategoryEntity.getId(),detailId);
                    if(!existRecord) {
                        Date createdTime = DateUtils.parseDate(createdTimeString, "yyyy-MM-dd");
                        ArticleRecordEntity articleRecordEntity = new ArticleRecordEntity();
                        articleRecordEntity.setSiteId(siteCategoryEntity.getSiteId());
                        articleRecordEntity.setDetailUrl(detailUrl);
                        articleRecordEntity.setStatus(ArticleStatus.WAITING);
                        articleRecordEntity.setCreatedTime(createdTime);
                        articleRecordEntity.setSiteCategoryId(siteCategoryEntity.getId());
                        articleRecordEntity.setDetailId(detailId);
                        articleRecordEntity.setRegion(region);
                        articleRecordEntity.setAuthor(author);
                        articleRecordEntity.setTitle(title);
                        articleRecordEntity.setReadCount(Integer.parseInt(readCount));
                        articleRecordEntity.setReplyCount(Integer.parseInt(replyCount));
                        articleRecordService.insert(articleRecordEntity);
                    }
                }
            }

            Thread.sleep(3000);

        }
        return ResponseEntity.ok("success");
    }

}
