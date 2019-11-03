package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class EnableController extends BaseController{

    @Autowired
    private PageManager pageManager;

    @PutMapping("/{domainName}/enable")
    public ResponseEntity<String> enable(HttpServletRequest request, @PathVariable String domainName, Model model) {

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        String id = checkCanOperate(request, pageService,pageManager.getLoginUsername());

        try{
            pageService.enable(id);
            return ResponseEntity.ok("success");
        }catch (GmosException e){
            return ResponseEntity.status(503).body(e.getMessage());
        }

    }

    @PutMapping("/{domainName}/disable")
    public ResponseEntity<String> disable(HttpServletRequest request, @PathVariable String domainName, Model model) {

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        String id = checkCanOperate(request, pageService,pageManager.getLoginUsername());

        pageService.disable(id);

        try{
            pageService.disable(id);
            return ResponseEntity.ok("success");
        }catch (GmosException e){
            return ResponseEntity.status(503).body(e.getMessage());
        }

    }


}
