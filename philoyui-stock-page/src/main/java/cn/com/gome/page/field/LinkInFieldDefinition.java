package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.linkin.LinkInDomain;
import cn.com.gome.page.field.linkin.LinkInProvider;
import cn.com.gome.page.utils.BeanUtils;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LinkInFieldDefinition  extends DomainLongFieldDefinition{

    private LinkInProvider linkInProvider;

    public LinkInFieldDefinition(String fieldName, String description,LinkInProvider linkInProvider) {
        super(fieldName, description,linkInProvider);
        this.linkInProvider = linkInProvider;
    }

    public LinkInProvider getLinkInProvider() {
        return linkInProvider;
    }


    @Override
    public FieldType getFieldType() {
        return FieldType.LinkIn;
    }

    /**
     * <div id="element_id">
     *   <select class="province"></select>
     *   <select class="city"></select>
     *   <select class="area"></select>
     * </div>
     *
     * @param pageService
     * @param request
     * @return
     */
    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        List<LinkInDomain> linkInDomains = linkInProvider.buildLinkInConfigs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<span class=\"select-box inline\" id=\"link_in_" + fieldName + "\">");
        for (int i = 0; i < linkInDomains.size(); i++) {
            String fieldValue = request.getParameter(linkInDomains.get(i).getFieldId());
            stringBuilder.append(String.format("<select name=\"%s\" data-first-title=\"选择 - %s\" class=\"select %s\" data-value=\"%s\"> </select>", linkInDomains.get(i).getFieldId(), linkInDomains.get(i).getDomainChineseName(), fieldName + i, fieldValue));
        }
        stringBuilder.append("</span>");
        return stringBuilder.toString();
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {
        List<LinkInDomain> linkInDomains = linkInProvider.buildLinkInConfigs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<span class=\"select-box inline\" id=\"link_in_" + fieldName + "\">");
        for (int i = 0; i < linkInDomains.size(); i++) {

            String selectValue = String.valueOf(BeanUtils.getPropertyValue(entity, linkInDomains.get(i).getFieldId()));

            String requestValue = request.getParameter(linkInDomains.get(i).getFieldId());
            if (!Strings.isNullOrEmpty(requestValue)){
                selectValue = requestValue;
            }

            stringBuilder.append(String.format("<select name=\"%s\" data-first-title=\"选择 - %s\" class=\"select %s\" data-value=\"%s\"> </select>", linkInDomains.get(i).getFieldId(), linkInDomains.get(i).getDomainChineseName(), fieldName + i,selectValue ));
        }
        stringBuilder.append("</span>");
        return stringBuilder.toString();
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[]{"/resources/js/jquery.cxselect.js","/resources/js/form_linkin.js"};
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {

        List<LinkInDomain> linkInDomains = linkInProvider.buildLinkInConfigs();

        for (LinkInDomain linkInDomain : linkInDomains) {
            String fieldValue = request.getParameter(linkInDomain.getFieldId());
            if (!Strings.isNullOrEmpty(fieldValue)) {
                searchFilter.add(Restrictions.eq(linkInDomain.getFieldId(), Long.parseLong(fieldValue)));
            }
        }

    }



}
