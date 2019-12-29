package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.service.FilterDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/filter_definition")
public class FilterDefinitionController {

    @Autowired
    private FilterDefinitionService filterDefinitionService;

    @RequestMapping("/filterAll")
    public ResponseEntity<String> filterAll() {

        filterDefinitionService.filterStock();

        return ResponseEntity.ok("success");
    }


    @RequestMapping("/tagStock")
    public ResponseEntity<String> filter(Long id) {

        FilterDefinitionEntity filterDefinitionEntity = filterDefinitionService.get(id);

        filterDefinitionService.tagStock(filterDefinitionEntity);

        return ResponseEntity.ok("success");
    }
}
