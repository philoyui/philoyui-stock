package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.form.FormField;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class DoubleFieldDefinition extends StringFieldDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleFieldDefinition.class);

    public DoubleFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Double;
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return Double.parseDouble(parameterValue);
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String startValue = request.getParameter(fieldName + "Start");
        String endValue = request.getParameter(fieldName + "End");

        if (startValue != null && !"".equals(endValue.trim())) {
            searchFilter.add(Restrictions.gte(fieldName, Double.parseDouble(startValue)));
        }
        if (!Strings.isNullOrEmpty(endValue)) {
            searchFilter.add(Restrictions.lte(fieldName,Double.parseDouble(endValue)));
        }
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        String startName = fieldName + "Start";
        String endName = fieldName + "End";
        String startValue = request.getParameter(startName);
        String endValue = request.getParameter(endName);
        return String.format("<input name=%s type=\"text\" id=%s class=\"input-text\" style=\"width:120px;\" value=\"%s\" placeholder=\"%s开始\"/>-<input name=%s type=\"text\" id=%s class=\"input-text\" style=\"width:120px;\" value=\"%s\"  placeholder=\"%s结束\"/>", startName, startName, startValue == null ? "" : startValue,description, endName, endName, endValue == null ? "" : endValue,description);
    }

}
