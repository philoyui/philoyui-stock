package cn.com.gome.page.button.column;


import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class DeleteOperation extends ColumnAction {

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {
        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();
        String link = String.format("/admin/%s/delete?id=%s", domainName, id);
        return buildConfirmButtonHtml("确定删除吗？","delete", link,  "删除");
    }
}
