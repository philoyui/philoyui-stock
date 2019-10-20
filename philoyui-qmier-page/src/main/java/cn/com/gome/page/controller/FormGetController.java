package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.FieldDefinition;
import cn.com.gome.page.field.FieldType;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class FormGetController {

    @Autowired
    private PageManager pageManager;

    @GetMapping("/{domainName}/form")
    public String formGet(HttpServletRequest request, @PathVariable String domainName, Model model){

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        PageConfig pageConfig = pageService.getPageConfig();

        String idString = request.getParameter("id");

        Object entity = null;

        List<FormField> responseList = new ArrayList<>();

        List<FormField> formFields = pageConfig.getFormFields();

        if(idString==null){

            entity = BeanUtils.newObject(pageConfig.getDomainClass());

            for (FormField formField : formFields) {
                FieldDefinition fieldDefinition = formField.getFieldDefinition();
                if(formField.isFormEditable()){
                    formField.setFormHtml(fieldDefinition.generateFormItemHtml(pageService,request,entity));
                    responseList.add(formField);
                }
            }

        }else{

            entity = pageService.get(request.getParameter("id"));

            for (FormField formField : formFields) {
                FieldDefinition fieldDefinition = formField.getFieldDefinition();
                if(formField.isFormEditable()){
                    formField.setFormHtml(fieldDefinition.generateFormItemHtml(pageService, request,entity));
                }else if(entity!=null){
                    Object fieldValue = BeanUtils.getPropertyValue(entity, fieldDefinition.getFieldName());
                    if(fieldValue!=null){
                        formField.setFormHtml(fieldDefinition.generateFormItemReadHtml(pageConfig,request, fieldValue));
                    }
                }
                if(!formField.isFormHide()){
                    responseList.add(formField);
                }
            }

        }

        model.addAttribute("domainName",pageConfig.getDomainName());
        model.addAttribute("formFields",responseList);
        model.addAttribute("id",idString);
        model.addAttribute("validateJsResource",buildJsResourceContent(responseList));
        model.addAttribute("validateJsContent",buildValidateJsContent(responseList));
        model.addAttribute("editorFormIds", buildEditorFormIds(responseList));
        model.addAttribute("imageFormIds", buildImageFormIds(responseList));
        model.addAttribute("linkInFieldIds", StringUtils.join(pageConfig.getLinkInFieldIds(),","));
        model.addAttribute("linkInDomains", StringUtils.join(pageConfig.getLinkInFieldDomains(),","));

        return "admin/form";

    }

    private String buildImageFormIds(List<FormField> fieldDefinitions) {

        StringBuilder stringBuilder = new StringBuilder();

        for (FormField formField : fieldDefinitions) {
            FieldDefinition fieldDefinition = formField.getFieldDefinition();
            if(fieldDefinition.getFieldType()== FieldType.Image){
                stringBuilder.append(formField.getFieldName()).append(",");
            }
        }

        if(stringBuilder.length()!=0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }

        return stringBuilder.toString();

    }

    private String buildEditorFormIds(List<FormField> formFields) {

        StringBuilder stringBuilder = new StringBuilder();

        for (FormField formField : formFields) {
            FieldDefinition fieldDefinition = formField.getFieldDefinition();
            if(fieldDefinition.getFieldType() == FieldType.Editor){
                stringBuilder.append(formField.getFieldName()).append(",");
            }
        }

        if(stringBuilder.length()!=0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }

        return stringBuilder.toString();
    }

    private String buildLinkInFieldIds(List<FormField> formFields) {

        StringBuilder stringBuilder = new StringBuilder();

        for (FormField formField : formFields) {
            FieldDefinition fieldDefinition = formField.getFieldDefinition();
            if(fieldDefinition.getFieldType() == FieldType.LinkIn){
                stringBuilder.append(formField.getFieldName()).append(",");
            }
        }

        if(stringBuilder.length()!=0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }

        return stringBuilder.toString();
    }


    /**
     *
     * 表单页构造JS文件引入
     *
     * @param formFields
     * @return
     */
    public String buildJsResourceContent(List<FormField> formFields) {

        Set<String> jsFileStringSet = new HashSet<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (FormField formField : formFields) {
            FieldDefinition fieldDefinition = formField.getFieldDefinition();
            jsFileStringSet.addAll(Arrays.asList(fieldDefinition.getNeedJsFiles()));
        }

        for (String jsFileString : jsFileStringSet) {
            stringBuilder.append("<script type=\"text/javascript\" src=\"").append(jsFileString).append("\"></script>");
        }

        return stringBuilder.toString();
    }


    /**
     *
     * 构建表单页校验JS的配置
     *
     * @param formFields
     * @return
     */
    private String buildValidateJsContent(List<FormField> formFields) {

        StringBuilder stringBuilder = new StringBuilder();

        for (FormField formField : formFields) {
            FieldDefinition fieldDefinition = formField.getFieldDefinition();
            stringBuilder.append(formField.getFieldName()).append(":{");
            if(fieldDefinition.isRequired()){
                stringBuilder.append("required:true,");
            }
            if(fieldDefinition.getFieldType() == FieldType.Email){
                stringBuilder.append("email:true,");
            }
            if(fieldDefinition.getFieldType() == FieldType.Mobile){
                stringBuilder.append("isMobile:true,");
            }
            stringBuilder.append("},");
        }

        return stringBuilder.toString();
    }

}
