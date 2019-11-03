package cn.com.gome.page.field;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.domain.PageDomainProvider;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.utils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class DomainStringFieldDefinition extends StringFieldDefinition{

    private PageDomainProvider pageDomainProvider;

    public DomainStringFieldDefinition(String fieldName, String description, PageDomainProvider pageDomainProvider) {
        super(fieldName, description);
        this.pageDomainProvider = pageDomainProvider;
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {

        String id = (String)value;

        Object obj = pageDomainProvider.findByReferId(id);

        if(obj==null){
            return "已被删除";
        }

        String domainLinkName = (String)BeanUtils.getPropertyValue(obj,pageDomainProvider.getDisplayFieldName());

        String domainName = pageDomainProvider.getDomainName();
        String domainChineseName = pageDomainProvider.getDomainChineseName();

        return String.format("<a style=\"text-decoration:none\" class=\"ml-5 c-primary\" onclick=\"layer_show('%s详细信息','/admin/%s/lay_detail?referId=%s')\" href=\"javascript:;\" title=\"%s\">%s</a>", domainChineseName, domainName, id, id, domainLinkName);

    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {

        String domainName = pageDomainProvider.getDomainName();
        String domainChineseName = pageDomainProvider.getDomainChineseName();
        String id = (String) BeanUtils.getPropertyValue(entity, "id");

        Object obj = pageDomainProvider.findByReferId(id);

        if(obj==null){
            return "已删除";
        }

        String domainLinkName = (String)BeanUtils.getPropertyValue(obj,pageDomainProvider.getDisplayFieldName());

        return String.format("<a style=\"text-decoration:none\" class=\"ml-5 c-primary\" onclick=\"layer_show('%s详细信息','/admin/%s/lay_detail?referId=%s')\" href=\"javascript:;\" title=\"%s\">%s</a>", domainChineseName, domainName, id, id, domainLinkName);
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


        String selectValue = "";

        if (entity != null) {
            Enum<?> requestValue = (Enum<?>)BeanUtils.getPropertyValue(entity, fieldName);
            if(requestValue!=null){
                selectValue = requestValue.name();
            }
        }

        return  pageContext.getStylePlugin().buildSelectBoxFilterHtml(optionHashMap,fieldName,description,selectValue);

    }
}
