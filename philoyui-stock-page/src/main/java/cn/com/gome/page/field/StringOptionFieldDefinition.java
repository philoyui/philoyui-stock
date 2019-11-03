package cn.com.gome.page.field;

import cn.com.gome.page.core.PageService;
import cn.com.gome.page.plugins.style.StylePlugin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringOptionFieldDefinition extends StringFieldDefinition {

    private String[] stringOptions;

    public StringOptionFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    public StringOptionFieldDefinition(String fieldName, String description, String[] stringOptions) {
        super(fieldName, description);
        this.stringOptions = stringOptions;
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {

        Map<String,String> nameDescMap = new ConcurrentHashMap<>();

        for (String stringOption : stringOptions) {
            nameDescMap.put(stringOption,stringOption);
        }

        String selectValue = request.getParameter(fieldName);
        StylePlugin stylePlugin = pageContext.getStylePlugin();
        return stylePlugin.buildSelectBoxFilterHtml(nameDescMap,fieldName,description,selectValue);

    }

    @Override
    public FieldType getFieldType() {
        return FieldType.StringOption;
    }

}
