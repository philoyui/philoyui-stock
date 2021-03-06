package io.philoyui.mystock.controller;

import io.philoyui.focus.service.FocusStockService;
import io.philoyui.mystock.service.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/my_stock")
public class MyStockController {

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private FocusStockService focusStockService;

    @RequestMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        myStockService.deleteAll();
        return ResponseEntity.ok("success");
    }

}
