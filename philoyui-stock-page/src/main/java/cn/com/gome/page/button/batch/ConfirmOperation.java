package cn.com.gome.page.button.batch;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class ConfirmOperation extends TableAction{

    private String actionName;

    private String actionChineseName;

    public ConfirmOperation(String actionName, String actionChineseName) {
        this.actionName = actionName;
        this.actionChineseName = actionChineseName;
    }

    @Override
    public String generateBatchButtonHtml(PageConfig pageConfig, HttpServletRequest request) {
        return null;
    }
}
