package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.ChooseDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.service.ChooseDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/choose_definition")
public class ChooseDefinitionController {

    @Autowired
    private ChooseDefinitionService chooseDefinitionService;

    @RequestMapping("/tagStock")
    public ResponseEntity<String> filter(Long id) {

        ChooseDefinitionEntity chooseDefinitionEntity = chooseDefinitionService.get(id);

        chooseDefinitionService.tagStock(chooseDefinitionEntity);

        return ResponseEntity.ok("success");
    }

}
