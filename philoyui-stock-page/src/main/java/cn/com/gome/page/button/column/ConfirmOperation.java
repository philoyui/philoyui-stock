package cn.com.gome.page.button.column;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class ConfirmOperation extends ColumnAction{

    private String actionName;

    private String actionChineseName;

    public ConfirmOperation(String actionName, String actionChineseName) {
        this.actionName = actionName;
        this.actionChineseName = actionChineseName;
    }

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();

        String enableLink = String.format("/admin/%s/%s?id=%s", domainName,actionName, id);
        return buildLinkButtonHtml("确认" + actionChineseName + "啊？","put",enableLink, actionChineseName);
    }

}
