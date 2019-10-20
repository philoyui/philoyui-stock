package cn.com.gome.page.button.column;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class DetailOperation extends ColumnAction{
    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();
        String link = String.format("/admin/%s/detail?id=%s", domainName, id);
        return buildLayerButtonHtml(pageConfig.getDomainChineseName() + "详情", link, "详细");

    }
}
