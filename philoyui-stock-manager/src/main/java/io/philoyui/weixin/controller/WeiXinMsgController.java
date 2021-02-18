package io.philoyui.weixin.controller;

import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import net.dreamlu.weixin.annotation.WxMsgController;
import net.dreamlu.weixin.spring.DreamMsgControllerAdapter;

@WxMsgController("/weixin/wx")
public class WeiXinMsgController extends DreamMsgControllerAdapter {

    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg defaultMsg = new OutTextMsg(inFollowEvent);
        // 取消关注
        if(InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent())){
            // 此处可以将取消关注的用户更新db
        }
    }

    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {

    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {

    }

}
