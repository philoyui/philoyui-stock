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

    @RequestMapping("/obtainEveryDay")
    public ResponseEntity<String> obtainEveryDay(){
        myStockService.obtainEveryDay();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        myStockService.deleteAll();
        return ResponseEntity.ok("success");
    }


    @RequestMapping("/addFocus")
    public ResponseEntity<String> addFocus(Long id){
        myStockService.addFocus(id);
        return ResponseEntity.ok("success");
    }

}
