package cn.com.gome.page.core;


import cn.com.gome.page.column.TableColumnFactory;
import cn.com.gome.page.plugins.login.LoginUserPlugin;
import cn.com.gome.page.plugins.style.StylePlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageContext {

    /**
     * 全局配置
     */
    private PageSettings pageSettings;

    /**
     * 全局样式
     */
    private StylePlugin stylePlugin;


    /**
     * 登录用户插件
     */
    private LoginUserPlugin loginUserPlugin;

    /**
     * 表格字段定义，如#operation,#checkbox
     */
    private TableColumnFactory tableColumnFactory = new TableColumnFactory();

    /**
     * 页面服务，唯一标志 -> 用户自定义的PageService
     */
    private Map<String,PageService> pageServiceMap = new ConcurrentHashMap<>();


    public PageContext(PageSettings pageSettings) {
        this.pageSettings = pageSettings;
        this.stylePlugin = pageSettings.getStylePlugin();
        this.loginUserPlugin = pageSettings.getLoginUserPlugin();
    }

    public StylePlugin getStylePlugin() {
        return stylePlugin;
    }

    public void setStylePlugin(StylePlugin stylePlugin) {
        this.stylePlugin = stylePlugin;
    }

    public StylePlugin getCurrentStyle() {
        return stylePlugin;
    }


    public TableColumnFactory getTableColumnFactory() {
        return tableColumnFactory;
    }

    public void addPageService(String domainName, PageService pageService) {
        pageServiceMap.put(domainName,pageService);
    }

    public PageService findServiceByDomainName(String domainName) {
        return pageServiceMap.get(domainName);
    }

    public LoginUserPlugin getLoginUserPlugin() {
        return loginUserPlugin;
    }

    public void setLoginUserPlugin(LoginUserPlugin loginUserPlugin) {
        this.loginUserPlugin = loginUserPlugin;
    }
}
