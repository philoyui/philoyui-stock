package io.philoyui.focus.controller;

import io.philoyui.focus.service.FocusStockService;
import io.philoyui.focus.timer.FocusStockTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/focus_stock")
public class FocusStockController {

    @Autowired
    private FocusStockTimer focusStockTimer;

    @Autowired
    private FocusStockService focusStockService;

    @RequestMapping("/focusTask")
    public ResponseEntity<String> focusTask(){
        focusStockTimer.execute();
        return ResponseEntity.ok("success");
    }


    @RequestMapping("/addMyStock")
    public ResponseEntity<String> addMyStock(Long id){
        focusStockService.addToMyStock(id);
        return ResponseEntity.ok("success");
    }

}
