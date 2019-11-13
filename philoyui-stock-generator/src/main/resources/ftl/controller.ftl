package ${basePackage}.controller;

import ${basePackage}.service.${EntityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/${domainName}")
public class ${EntityName}Controller {

    @Autowired
    private ${EntityName}Service ${entityName}Service;

    @RequestMapping("/hello")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        return ResponseEntity.ok("success");
    }
}
