package cn.com.gome.page.field;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.domain.PageDomainProvider;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.utils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class DomainLongFieldDefinition extends LongFieldDefinition{

    private PageDomainProvider pageDomainProvider;

    public DomainLongFieldDefinition(String fieldName, String description, PageDomainProvider pageDomainProvider) {
        super(fieldName, description);
        this.pageDomainProvider = pageDomainProvider;
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        Long id = (Long)value;

        Object obj = pageDomainProvider.findByReferId(String.valueOf(id));

        if(obj==null){
            return "已删除";
        }

        String domainLinkName = (String)BeanUtils.getPropertyValue(obj,pageDomainProvider.getDisplayFieldName());

        String domainName = pageDomainProvider.getDomainName();
        String domainChineseName = pageDomainProvider.getDomainChineseName();

        return String.format("<a style=\"text-decoration:none\" class=\"ml-5 c-primary\" onclick=\"layer_show('%s详细信息','/admin/%s/lay_detail?referId=%s')\" href=\"javascript:;\" title=\"%s\">%s</a>", domainChineseName, domainName, id, id, domainLinkName);
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        Long id = (Long)entity;

        Object obj = pageDomainProvider.findByReferId(String.valueOf(id));

        if(obj==null){
            return "已删除";
        }

        String domainLinkName = (String)BeanUtils.getPropertyValue(obj,pageDomainProvider.getDisplayFieldName());

        return domainLinkName + "(" + id + ")";
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.DomainLong;
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {

        HashMap<String, String> optionHashMap = new HashMap<>();
        for (Object object : pageDomainProvider.findAll()) {
            optionHashMap.put(String.valueOf(BeanUtils.getPropertyValue(object,"id")),String.valueOf(BeanUtils.getPropertyValue(object,pageDomainProvider.getDisplayFieldName())));
        }
        StylePlugin stylePlugin = pageContext.getStylePlugin();
        String selectValue = request.getParameter(fieldName);
        return stylePlugin.buildSelectBoxFilterHtml(optionHashMap,fieldName,description,selectValue);

    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        HashMap<String, String> optionHashMap = new HashMap<>();

        for (Object object : pageDomainProvider.findAll()) {
            optionHashMap.put(String.valueOf(BeanUtils.getPropertyValue(object,"id")),String.valueOf(BeanUtils.getPropertyValue(object,pageDomainProvider.getDisplayFieldName())));
        }

        Long selectValue = 0L;

        if (request.getParameter(fieldName) == null) {
            Long requestValue = (Long) BeanUtils.getPropertyValue(entity, fieldName);
            if(requestValue!=null){
                selectValue = requestValue;
            }
            return pageContext.getStylePlugin().buildSelectBoxFilterHtml(optionHashMap,fieldName,description,String.valueOf(selectValue));
        }else{
            selectValue = Long.parseLong(request.getParameter(fieldName));
            return "<input type=\"hidden\" name=\"" + fieldName + "\" value=\""+selectValue+"\"/>\n" + optionHashMap.get(String.valueOf(selectValue));
        }

    }
}
