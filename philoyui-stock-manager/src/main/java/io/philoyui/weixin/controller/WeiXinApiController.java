package io.philoyui.weixin.controller;

import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.field.DoubleFieldDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import io.philoyui.weixin.vo.*;
import net.dreamlu.weixin.annotation.WxApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@WxApi("weixin/api")
public class WeiXinApiController {

    private Gson gson = new GsonBuilder().create();

    private static final Logger LOG = LoggerFactory.getLogger(WeiXinApiController.class);

    @GetMapping("menu")
    @ResponseBody
    public String getMenu(){
        ApiResult menu = MenuApi.getMenu();
        return menu.getJson();
    }

    /**
     * 初始化菜单
     * @return
     */
    @GetMapping("/initMenu")
    public ResponseEntity<String> initMenu(){

        ApiResult deleteResult = MenuApi.deleteMenu();

        System.out.println(gson.toJson(deleteResult));

        if(!deleteResult.isSucceed()){
            throw new GmosException("清理微信公众平台菜单失败：" + deleteResult.getErrorMsg());
        }

        Menu menu = new Menu();
        ViewButton button11 = new ViewButton();
        button11.setName("搜索");
        button11.setType("view");
        button11.setUrl("https://www.baidu.com");

        SendPicButton button21 = new SendPicButton();
        button21.setName("发图");
        button21.setType("pic_photo_or_album");
        button21.setKey("pic");

        SendLocalButton button32 = new SendLocalButton();
        button32.setName("发位置");
        button32.setType("location_select");
        button32.setKey("local");

        ClickButton button31 = new ClickButton();
        button31.setName("点赞");
        button31.setType("click");
        button31.setKey("strtest");

        Button button = new Button();
        button.setName("click2");
        button.setSub_button(new Button[]{button31,button32});
        button.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{button11,button21,button});

        ApiResult createResult = MenuApi.createMenu(gson.toJson(menu));

        System.out.println(gson.toJson(createResult));

        if(!createResult.isSucceed()){
            throw new GmosException("创建微信公众平台菜单成功");
        }

        return ResponseEntity.ok("success");
    }

}
