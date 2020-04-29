package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.utils.BeanUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFieldDefinition extends FieldDefinition{

    private static final Logger LOG = LoggerFactory.getLogger(DateFieldDefinition.class);

    public DateFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String startTime = request.getParameter(fieldName + "Start");
        String endTime = request.getParameter(fieldName + "End");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (startTime != null && !"".equals(startTime.trim())) {
            try {
                searchFilter.add(Restrictions.gte(fieldName, simpleDateFormat.parse(startTime)));
            } catch (ParseException e) {
                LOG.error("构造日期查询条件时解析日期出错："+fieldName, e);
            }
        }
        if (!Strings.isNullOrEmpty(endTime)) {
            try {
                searchFilter.add(Restrictions.lte(fieldName, simpleDateFormat.parse(request.getParameter(fieldName + "End"))));
            } catch (ParseException e) {
                LOG.error("构造日期查询条件时解析日期出错："+fieldName, e);
            }

        }
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {

        String startName = fieldName + "Start";
        String endName = fieldName + "End";
        String startValue = request.getParameter(startName);
        String endValue = request.getParameter(endName);
        return String.format("<input name=%s type=\"text\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" id=%s class=\"input-text Wdate\" style=\"width:120px;\" value=\"%s\" placeholder=\"%s开始\"/>-<input name=%s type=\"text\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" id=%s class=\"input-text Wdate\" style=\"width:120px;\" value=\"%s\"  placeholder=\"%s结束\"/>", startName, startName, startValue == null ? "" : startValue,description, endName, endName, endValue == null ? "" : endValue,description);

    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {

        String result = null;

        if (value instanceof Date) {
            Date date = (Date) value;
            result = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        } else if (value instanceof String) {
            result = (String) value;
        }

        return result;
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {
        StylePlugin currentStyle = pageContext.getCurrentStyle();

        Object requestValue = "";

        if (entity != null) {
            requestValue = BeanUtils.getPropertyValue(entity, fieldName);
        }

        String requestValueString = "";
        if(requestValue!=null){
            Date requestValueTime = (Date)requestValue;
            requestValueString = DateFormatUtils.format(requestValueTime,"yyyy-HH-dd HH:mm:ss");
        }
        return currentStyle.getFormViewDateInputHtml().replaceAll("#fieldName#",fieldName).replaceAll("#fieldValue#",requestValueString);

    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        Date date = (Date) entity;
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Date;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[]{};
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        try {
            return DateUtils.parseDate(parameterValue,"yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new GmosException("日期数据无法解析：" + parameterValue);
    }

}
