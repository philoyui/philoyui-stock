package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.plugins.style.StylePlugin;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapIntegerFieldDefinition extends IntegerFieldDefinition{

    private Map<Integer,String> mapOption = new HashMap<>();

    public MapIntegerFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    public MapIntegerFieldDefinition(String fieldName, String description, Map<Integer,String> mapOption) {
        super(fieldName, description);
        this.mapOption = mapOption;
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        Map<String,String> nameDescMap = new ConcurrentHashMap<>();
        for (Map.Entry<Integer, String> integerStringEntry : mapOption.entrySet()) {
            nameDescMap.put(String.valueOf(integerStringEntry.getKey()),integerStringEntry.getValue());
        }
        String selectValue = request.getParameter(fieldName);
        StylePlugin stylePlugin = pageContext.getStylePlugin();
        return stylePlugin.buildSelectBoxFilterHtml(nameDescMap,fieldName,description,selectValue);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.MapIntegerOption;
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String parameterValue = request.getParameter(fieldName);
        if(!Strings.isNullOrEmpty(parameterValue)){
            searchFilter.add(Restrictions.eq(fieldName,Integer.parseInt(parameterValue)));
        }
    }
}
