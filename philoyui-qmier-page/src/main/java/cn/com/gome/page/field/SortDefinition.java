package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.utils.PageConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SortDefinition {

    private Map<String,String> sortConfigMap = new ConcurrentHashMap<>();

    private PageConfig pageConfig;

    public static SortDefinition parse(PageConfig pageConfig, Map<String, FieldDefinition> fieldNameDefinitionMap, String[] sortDefinitions) {

        SortDefinition sortDefinition = new SortDefinition();
        sortDefinition.setPageConfig(pageConfig);

        Map<String,String> sortConfigMap = new HashMap<>();

        for (String definition : sortDefinitions) {

            String[] sortDefinitionParts = definition.split("_");

            if(sortDefinitionParts.length!=2){
                throw new GmosException("排序条件设置出错，格式出错，有且必须有一个_：" + definition + " , 领域对象为" + pageConfig.getDomainName());
            }

            String fieldName = sortDefinitionParts[0];

            FieldDefinition fieldDefinition = fieldNameDefinitionMap.get(fieldName);

            if(fieldDefinition==null){
                throw new GmosException("排序条件指定的属性字段不存在，当前值为：" +  definition + " , 领域对象为" + pageConfig.getDomainName());
            }

            String sortPattern = sortDefinitionParts[1];

            switch (sortPattern){
                case "desc":
                    sortConfigMap.put(definition,fieldDefinition.getDescription()+"降序");
                    break;
                case "asc":
                    sortConfigMap.put(definition,fieldDefinition.getDescription()+"升序");
                    break;
                default:
                    throw new GmosException("不存在的排序方式：当前值为：" + sortPattern);

            }

        }

        sortDefinition.setSortConfigMap(sortConfigMap);

        return sortDefinition;
    }



    public void buildSearchFilterOrder(SearchFilter searchFilter, String orderParameterValue) {

        String[] valueParts = orderParameterValue.split("_");

        if(valueParts.length!=2){
            throw new GmosException("传入的排序方式的值不符合要求："+orderParameterValue);
        }

        if(valueParts[1].equalsIgnoreCase("desc")){
            searchFilter.add(Order.desc(valueParts[0]));
        }else{
            searchFilter.add(Order.asc(valueParts[0]));
        }

    }

    public String buildOrderFilterHtml(HttpServletRequest request){
        StylePlugin stylePlugin = pageConfig.getPageContext().getCurrentStyle();
        String requestOrderFieldValue = request.getParameter(PageConstant.REQUEST_ORDER_FIELD);
        return stylePlugin.getListViewOrderFilterHtml(sortConfigMap,requestOrderFieldValue);
    }

    private void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public void setSortConfigMap(Map<String, String> sortConfigMap) {
        this.sortConfigMap = sortConfigMap;
    }
}
