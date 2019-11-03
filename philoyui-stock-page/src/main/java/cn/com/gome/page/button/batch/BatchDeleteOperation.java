package cn.com.gome.page.button.batch;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.plugins.style.StylePlugin;

import javax.servlet.http.HttpServletRequest;

public class BatchDeleteOperation extends TableAction {
    @Override
    public String generateBatchButtonHtml(PageConfig pageConfig, HttpServletRequest request) {
        StylePlugin stylePlugin = pageConfig.getStylePlugin();
        return stylePlugin.getBatchDeleteActionHtml();
    }
}
