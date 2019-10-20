package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主框架页面
 */
@Controller
public class IndexController {

    @Autowired
    private PageManager pageManager;

    @GetMapping("/")
    public String frame(Model model){
        model.addAttribute("username",pageManager.getLoginUsername());
        model.addAttribute("rootItems", pageManager.getAdminItems().getRootItems());
        model.addAttribute("title", pageManager.getTitle());
        return "home";
    }

}
