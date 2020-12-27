package io.philoyui.tagstock.controller;

import io.philoyui.tagstock.timer.DayTagTimer;
import io.philoyui.tagstock.timer.MinTagTimer;
import io.philoyui.tagstock.timer.MonthTagTimer;
import io.philoyui.tagstock.timer.WeekTagTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private MonthTagTimer monthTagTimer;

    @RequestMapping("/dayTask")
    public ResponseEntity<String> dayTask(){
        dayTagTimer.execute();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/minTask")
    public ResponseEntity<String> minTask(){
        minTagTimer.execute();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/weekTagTimer")
    public ResponseEntity<String> weekTagTimer(){
        weekTagTimer.execute();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/monthTagTimer")
    public ResponseEntity<String> monthTagTimer(){
        monthTagTimer.execute();
        return ResponseEntity.ok("success");
    }
}
