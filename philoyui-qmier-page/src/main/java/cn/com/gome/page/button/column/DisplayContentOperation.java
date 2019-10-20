package cn.com.gome.page.button.column;


import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class DisplayContentOperation extends ColumnAction {

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();
        String link = String.format("/admin/%s/doc_content?id=%s", domainName, id);
        return buildLayerButtonHtml(pageConfig.getDomainChineseName() + "文档内容", link,  "文档内容");

    }
}
