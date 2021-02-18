package io.philoyui.weixin.page;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.weixin.entity.ArticleType;
import io.philoyui.weixin.entity.WeChatMsgEntity;
import io.philoyui.weixin.service.WeChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeChatMsgPageService extends PageService<WeChatMsgEntity,Long> {

    @Autowired
    private WeChatMsgService weChatMsgService;

    @Override
    public PageObject<WeChatMsgEntity> paged(SearchFilter searchFilter) {
        searchFilter.add(Order.desc("createdTime"));
        return weChatMsgService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("we_chat_msg")
                .withDomainClass(WeChatMsgEntity.class)
                .withDomainChineseName("微信消息")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("title", "标题"),
                        new StringFieldDefinition("content", "消息正文"),
                        new ImageFieldDefinition("imageUrl", "标签名称",100,100),
                        new DateFieldDefinition("createdTime", "创建时间"),
                        new StringFieldDefinition("articleLink", "文章链接"),
                        new StringFieldDefinition("mediaId","媒体ID"),
                        new EnumFieldDefinition("articleType","文章类型", ArticleType.class)
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withTableColumnDefinitions(
                        "title_30",
                        "imageUrl_20",
                        "createdTime_15",
                        "articleType_15",
                        "#operation_20"
                )
                .withFilterDefinitions(
                        "articleType"
                )
                .withSortDefinitions(
                        "createdTime_asc","createdTime_desc"
                )
                .withColumnAction(
                        new ConfirmOperation("addMyStock","加入自选")
                )
                .withFormItemDefinition(
                        "title_rw",
                        "content_rw",
                        "imageUrl_rw",
                        "articleLink_rw",
                        "mediaId_rw",
                        "articleType_rw"
                ).withDefaultPageSize("100");
        return pageConfig;
    }

    @Override
    public WeChatMsgEntity get(String id) {
        return weChatMsgService.get(Long.parseLong(id));
    }

    @Override
    public WeChatMsgEntity get(SearchFilter searchFilter) {
        return weChatMsgService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(WeChatMsgEntity weChatMsgEntity) {
        weChatMsgService.insert(weChatMsgEntity);
    }

    @Override
    public void delete(WeChatMsgEntity weChatMsgEntity) {
        weChatMsgService.delete(weChatMsgEntity.getId());
    }
}
