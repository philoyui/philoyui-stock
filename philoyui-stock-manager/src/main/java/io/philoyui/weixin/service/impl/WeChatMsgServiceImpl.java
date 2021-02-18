package io.philoyui.weixin.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.weixin.dao.WeChatMsgDao;
import io.philoyui.weixin.entity.WeChatMsgEntity;
import io.philoyui.weixin.service.WeChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeChatMsgServiceImpl extends GenericServiceImpl<WeChatMsgEntity,Long> implements WeChatMsgService {

    @Autowired
    private WeChatMsgDao weChatMsgDao;

    @Override
    protected GenericDao<WeChatMsgEntity, Long> getDao() {
        return weChatMsgDao;
    }
}
