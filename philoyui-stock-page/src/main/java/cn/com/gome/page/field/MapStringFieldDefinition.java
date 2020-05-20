package cn.com.gome.page.field;

import cn.com.gome.page.core.PageService;
import cn.com.gome.page.plugins.style.StylePlugin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MapStringFieldDefinition extends StringFieldDefinition {

    private Map<String,String> mapOption = new HashMap<>();

    public MapStringFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    public MapStringFieldDefinition(String fieldName, String description, Map<String,String> mapOption) {
        super(fieldName, description);
        this.mapOption = mapOption;
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        String selectValue = request.getParameter(fieldName);
        StylePlugin stylePlugin = pageContext.getStylePlugin();
        return stylePlugin.buildSelectBoxFilterHtml(mapOption,fieldName,description,selectValue);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.MapStringOption;
    }

}
