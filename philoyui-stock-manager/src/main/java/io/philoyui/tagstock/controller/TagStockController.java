package io.philoyui.tagstock.controller;

import io.philoyui.tagstock.timer.DayTagTimer;
import io.philoyui.tagstock.timer.FocusStockTimer;
import io.philoyui.tagstock.timer.MinTagTimer;
import io.philoyui.tagstock.timer.WeekTagTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/tag_stock")
public class TagStockController {

    @Autowired
    private DayTagTimer dayTagTimer;

    @Autowired
    private MinTagTimer minTagTimer;

    @Autowired
    private WeekTagTimer weekTagTimer;

    @RequestMapping("/dayTask")
    public void dayTask(){
        dayTagTimer.execute();
    }

    @RequestMapping("/minTask")
    public void minTask(){
        minTagTimer.execute();
    }

    @RequestMapping("/weekTagTimer")
    public void weekTagTimer(){
        weekTagTimer.execute();
    }

}
