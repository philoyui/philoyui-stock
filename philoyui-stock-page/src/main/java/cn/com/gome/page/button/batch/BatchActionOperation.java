package cn.com.gome.page.button.batch;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.plugins.style.StylePlugin;

import javax.servlet.http.HttpServletRequest;

public class BatchActionOperation extends TableAction {

    private final String name;

    private final String path;

    private final ButtonStyle buttonStyle;

    public BatchActionOperation(String name, String path, ButtonStyle buttonStyle) {
        this.name = name;
        this.path = path;
        this.buttonStyle = buttonStyle;
    }

    @Override
    public String generateBatchButtonHtml(PageConfig pageConfig, HttpServletRequest request) {
        StylePlugin stylePlugin = pageConfig.getStylePlugin();
        String htmlContent = stylePlugin.getBatchActionHtml();
        htmlContent = htmlContent.replaceAll("#名字#",name);
        htmlContent = htmlContent.replaceAll("#操作链接#",path);
        htmlContent = htmlContent.replaceAll("#Class样式#",buttonStyle.getButtonStyleClass());
        return htmlContent;
    }
}
