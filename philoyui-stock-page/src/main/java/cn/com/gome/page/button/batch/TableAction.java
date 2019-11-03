package cn.com.gome.page.button.batch;


import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public abstract class TableAction {
    public abstract String generateBatchButtonHtml(PageConfig pageConfig, HttpServletRequest request);
}
