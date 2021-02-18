package io.philoyui.weixin.service;

import io.philoyui.weixin.entity.ArticleType;
import io.philoyui.weixin.entity.WeChatMsgEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;


public class WeChatMsgServiceTest {

    @Autowired
    private WeChatMsgService weChatMsgService;

    @Test
    public void test_add_text_message(){
        WeChatMsgEntity weChatMsgEntity = new WeChatMsgEntity();
        weChatMsgEntity.setContent("这是文本消息");
        weChatMsgEntity.setCreatedTime(new Date());
        weChatMsgEntity.setArticleType(ArticleType.Text);
        weChatMsgService.insert(weChatMsgEntity);
    }

}