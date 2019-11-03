package cn.com.gome.page.controller;

import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.FieldDefinition;
import cn.com.gome.page.field.domain.PageDomainProvider;
import cn.com.gome.page.form.FormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LayerController {

    @Autowired
    private PageManager pageManager;

    @GetMapping("/{domainName}/lay_detail")
    public String page(HttpServletRequest request, @PathVariable String domainName, Model model){

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        if(pageService instanceof PageDomainProvider){

            PageDomainProvider pageDomainProvider = (PageDomainProvider)pageService;

            Object entity = pageDomainProvider.findByReferId(request.getParameter("referId"));

            PageConfig pageConfig = pageService.getPageConfig();

            List<FormField> formFields = pageConfig.getFormFields();

            for (FormField detailField : formFields) {

                FieldDefinition fieldDefinition = detailField.getFieldDefinition();
                Object propertyValue = BeanUtils.getPropertyValue(entity, detailField.getFieldName());

                if(propertyValue!=null){
                    detailField.setDetailHtml(fieldDefinition.formatColumnValue(pageConfig,propertyValue));
                }

            }

            model.addAttribute("detailFields",formFields);

        }

        return "admin/detail";
    }

}
