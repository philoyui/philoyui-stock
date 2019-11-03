package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

public class FilterField implements Serializable {

    private String fieldName;

    private FieldDefinition fieldDefinition;

    private String filterPattern;

    public static FilterField resolveFilterField(Map<String, FieldDefinition> fieldNameDefinitionMap, String filterDefinition) {

        if(Strings.isNullOrEmpty(filterDefinition)){
            throw new GmosException("筛选条件设置withFilterDefinitions出错，存在空定义" + filterDefinition);
        }

        String[] filterDefinitionParts = filterDefinition.split("_");

        if(filterDefinitionParts.length>=3){
            throw new GmosException("筛选条件设置withFilterDefinitions出错，格式出错，只能有一个_：" + filterDefinition);
        }

        String fieldName = filterDefinitionParts[0];

        FieldDefinition fieldDefinition = fieldNameDefinitionMap.get(fieldName);
        if(fieldDefinition==null){
            throw new GmosException("筛选条件设置withFilterDefinitions出错，不存在的fieldName:" + filterDefinition);
        }

        String supplyPart = "";

        if(filterDefinitionParts.length==2){
            supplyPart =  filterDefinitionParts[1];
        }

        FilterField filterField = new FilterField();
        filterField.setFieldName(fieldName);
        filterField.setFieldDefinition(fieldDefinition);
        filterField.setFilterPattern(supplyPart);
        return filterField;

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

    public String getFilterPattern() {
        return filterPattern;
    }

    public void setFilterPattern(String filterPattern) {
        this.filterPattern = filterPattern;
    }

    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter) {
        fieldDefinition.constructSearchFilter(request,searchFilter,this.filterPattern);
    }

    public String buildSearchFieldHtml(PageService pageService, HttpServletRequest request) {
        return fieldDefinition.buildSearchFilterHtml(pageService, request);
    }
}
