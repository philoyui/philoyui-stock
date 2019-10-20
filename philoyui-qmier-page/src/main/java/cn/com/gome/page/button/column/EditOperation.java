package cn.com.gome.page.button.column;


import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class EditOperation extends ColumnAction {

    private String action = "form";

    public EditOperation() {
    }

    public EditOperation(String action) {
        this.action = action;
    }

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {
        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();
        String link = String.format("/admin/%s/" + action + "?id=%s", domainName, id);
        return buildLayerButtonHtml("编辑" + pageConfig.getDomainChineseName(),link, "编辑");
    }
}
