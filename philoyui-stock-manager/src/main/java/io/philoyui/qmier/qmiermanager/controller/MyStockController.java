package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/my_stock")
public class MyStockController {

    @Autowired
    private MyStockService myStockService;

    @RequestMapping("/obtainEveryDay")
    public ResponseEntity<String> obtainEveryDay(){
        myStockService.obtainEveryDay();
        return ResponseEntity.ok("success");
    }

}
