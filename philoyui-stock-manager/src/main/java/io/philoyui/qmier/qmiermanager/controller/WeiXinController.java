package io.philoyui.qmier.qmiermanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/wx")
public class WeiXinController {

    @RequestMapping("/callback")
    public String callback(){
        return "success";
    }

}
