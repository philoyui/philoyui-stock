package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.field.FieldDefinition;
import cn.com.gome.page.field.validator.FieldValidator;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.utils.BeanUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class FormPostController {

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

    @Autowired
    private PageManager pageManager;

    @PostMapping("/{domainName}/form")
    public ResponseEntity<String> formPost(HttpServletRequest request, @PathVariable String domainName, Model model) {

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        PageConfig pageConfig = pageService.getPageConfig();

        String idString = request.getParameter("id");

        Serializable entity;

        if(!Strings.isNullOrEmpty(idString)){
            entity = pageService.get(request.getParameter("id"));
        }else{
            entity = BeanUtils.newObject(pageConfig.getDomainClass());
        }

        List<FormField> formFields = pageConfig.getFormFields();
        for (FormField formField : formFields) {
            if(formField.isFormEditable()||formField.isFormHide()){
                fillObjectFieldValue(request,entity,formField);
            }
        }

        String ownerFieldName = pageConfig.getLoginUserFieldName();
        if(!Strings.isNullOrEmpty(ownerFieldName)){
            BeanUtils.setProperty(entity,ownerFieldName,pageManager.getLoginUsername());
        }
        try {
            pageService.saveOrUpdate(entity);
            return ResponseEntity.ok("success");
        }catch (GmosException e){
            return ResponseEntity.status(503).body(e.getMessage());
        }

    }

    private void fillObjectFieldValue(HttpServletRequest request, Serializable entity, FormField formField) {

        String parameterValue = request.getParameter(formField.getFieldName());

        FieldDefinition fieldDefinition = formField.getFieldDefinition();

        List<FieldValidator> fieldValidators = fieldDefinition.getFieldValidators();
        for (FieldValidator fieldValidator : fieldValidators) {
            fieldValidator.validate(formField.getFieldName(),parameterValue);
        }

        Object parameterValueObject = fieldDefinition.transferType(entity,formField,parameterValue);
        try {
            BeanUtils.setProperty(entity, formField.getFieldName(), parameterValueObject);
        }catch (Exception e){
            LOG.error("填充属性值出错，出错的属性为：" + formField.getFieldName(),e);
        }
    }

}
