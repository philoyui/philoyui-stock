package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.linkin.LinkInProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class LinkInController {

    @Autowired
    private PageManager pageManager;

    @GetMapping("/{domainName}/linkIn")
    public String page(HttpServletRequest request, @PathVariable String domainName){
        PageService pageService = pageManager.findServiceByDomainName(domainName);
        if(pageService instanceof LinkInProvider){
            LinkInProvider linkInProvider = (LinkInProvider)pageService;
            return linkInProvider.obtainSelectJson();
        }
        return "";
    }

}
