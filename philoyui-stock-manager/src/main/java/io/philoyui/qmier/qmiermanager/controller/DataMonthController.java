package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.MonthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_month")
public class DataMonthController {

    @Autowired
    private MonthDataService monthDataService;

}
