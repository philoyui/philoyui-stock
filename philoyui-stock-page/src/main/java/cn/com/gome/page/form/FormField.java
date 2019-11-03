package cn.com.gome.page.form;

import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.field.FieldDefinition;
import com.google.common.base.Strings;

import java.util.Map;

public class FormField {

    private PageConfig pageConfig;

    private String fieldName;

    private FieldDefinition fieldDefinition;

    private boolean formReadable;

    private boolean formEditable;

    /**
     * 多级下拉，不可读，也不可编辑，但是保存时需要读取request保存
     */
    private boolean formHide;

    private String detailHtml = "";

    private String formHtml = "";

    public static FormField resolve(PageConfig pageConfig, Map<String, FieldDefinition> fieldNameDefinitionMap, String formFieldDefinition) {

        if(Strings.isNullOrEmpty(formFieldDefinition)){
            throw new GmosException("表单字段设置formField出错，存在空定义" + formFieldDefinition + " , 领域对象为 " + pageConfig.getDomainName());
        }

        String[] filterDefinitionParts = formFieldDefinition.split("_");

        if(filterDefinitionParts.length!=2){
            throw new GmosException("表单字段设置formField出错，格式出错，有且只能有一个_：" + formFieldDefinition + " , 领域对象为 " + pageConfig.getDomainName());
        }

        String fieldName = filterDefinitionParts[0];

        FieldDefinition fieldDefinition = fieldNameDefinitionMap.get(fieldName);
        if(fieldDefinition==null){
            throw new GmosException("表单字段设置formField出错，不存在的fieldName:" + formFieldDefinition + " , 领域对象为 " + pageConfig.getDomainName());
        }

        String supplyPart =  filterDefinitionParts[1];

        FormField formField = new FormField();

        switch (supplyPart){
            case "r" :
                formField.setFormReadable(true);
                break;
            case "h":
                formField.setFormHide(true);
                break;
            case "rw":
                formField.setFormEditable(true);
                formField.setFormReadable(true);
                break;
        }

        formField.setFieldName(fieldName);
        formField.setFieldDefinition(fieldDefinition);
        formField.setPageConfig(pageConfig);
        return formField;

    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldDefinition getFieldDefinition() {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public boolean isFormReadable() {
        return formReadable;
    }

    public void setFormReadable(boolean formReadable) {
        this.formReadable = formReadable;
    }

    public boolean isFormEditable() {
        return formEditable;
    }

    public void setFormEditable(boolean formEditable) {
        this.formEditable = formEditable;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getFormHtml() {
        return formHtml;
    }

    public void setFormHtml(String formHtml) {
        this.formHtml = formHtml;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public boolean isFormHide() {
        return formHide;
    }

    public void setFormHide(boolean formHide) {
        this.formHide = formHide;
    }
}
