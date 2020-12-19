package io.philoyui.focus.controller;

import io.philoyui.tagstock.timer.FocusStockTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/focus_stock")
public class FocusStockController {

    @Autowired
    private FocusStockTimer focusStockTimer;

    @RequestMapping("/focusTask")
    public void focusTask(){
        focusStockTimer.execute();
    }

}
