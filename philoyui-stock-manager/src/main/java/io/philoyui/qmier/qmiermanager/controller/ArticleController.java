package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.client.tts.SpeechSynthesizerClient;
import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequestMapping("/admin/article")
public class ArticleController {

    @Value("${application.voice.base-url}")
    private String invoiceBaseUrl;

    @Value("${application.voice.store-path}")
    private String invoiceBasePath;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/generateVoice")
    public ResponseEntity<String> generateVoice(@RequestParam Long id){
        ArticleEntity articleEntity = articleService.get(id);
        String text = Jsoup.parse(articleEntity.getContent()).text();
        String appKey = "mKtuCAr9hlmbahml";
        String accessKeyId = "LTAI4Fx36HdmB3g4KyR8dWxJ";
        String secret = "HobPTtHrgCgx0E1BlhQvIJYxWIGIWs";
        String url = ""; // 默认即可，默认值：wss://nls-gateway.cn-shanghai.aliyuncs.com/ws/v1
        SpeechSynthesizerClient client = new SpeechSynthesizerClient(appKey, accessKeyId, secret, url);
        client.process(text, buildInvoicePath(articleEntity));
        return ResponseEntity.ok("success");
    }


    @RequestMapping("/detailContent")
    public String detailContent(Model model, @RequestParam Long id){
        ArticleEntity articleEntity = articleService.get(id);
        model.addAttribute("article",articleEntity);
        model.addAttribute("voicePath",invoiceBaseUrl + "/article_" + articleEntity.getId() + ".wav");
        return "article/detailContent";
    }

    /**
     * 构建名字 article_109
     * @param articleEntity
     * @return
     */
    private String buildInvoicePath(ArticleEntity articleEntity) {
        return invoiceBasePath + "article_" + articleEntity.getId() + ".wav";
    }

}
