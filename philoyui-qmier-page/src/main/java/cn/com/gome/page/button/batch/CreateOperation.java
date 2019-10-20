package cn.com.gome.page.button.batch;

import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.plugins.style.StylePlugin;

import javax.servlet.http.HttpServletRequest;

public class CreateOperation extends TableAction {

    private String action = "form";

    public CreateOperation() {
    }

    public CreateOperation(String action) {
        this.action = action;
    }

    @Override
    public String generateBatchButtonHtml(PageConfig pageConfig, HttpServletRequest request) {
        StylePlugin stylePlugin = pageConfig.getStylePlugin();
        String createActionHtml = stylePlugin.getCreateActionHtml(action);
        createActionHtml = createActionHtml.replaceAll("#domain中文名字#",pageConfig.getDomainChineseName());
        createActionHtml = createActionHtml.replaceAll("#domainName#",pageConfig.getDomainName());
        return createActionHtml;
    }
}
