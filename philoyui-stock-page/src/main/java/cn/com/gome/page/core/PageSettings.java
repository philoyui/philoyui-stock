package cn.com.gome.page.core;

import cn.com.gome.page.category.CategoryServiceProvider;
import cn.com.gome.page.column.TableColumnFactory;
import cn.com.gome.page.setting.AdminItems;
import cn.com.gome.page.plugins.login.EmptyLoginUserPlugin;
import cn.com.gome.page.plugins.login.LoginUserPlugin;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.plugins.style.hui.HuiStylePlugin;
import cn.com.gome.page.plugins.upload.EmptyUploadPlugin;
import cn.com.gome.page.plugins.upload.FileUploadPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageSettings {

    /**
     * 后台菜单
     */
    private AdminItems adminItems;

    /**
     * 当前样式
     */
    private StylePlugin stylePlugin = new HuiStylePlugin();

    /**
     * 文件上传插件
     */
    private FileUploadPlugin fileUploadPlugin = new EmptyUploadPlugin();

    /**
     * 登录用户插件
     */
    private LoginUserPlugin loginUserPlugin = new EmptyLoginUserPlugin();


    private String title;

    private TableColumnFactory tableColumnFactory = new TableColumnFactory();

    private Map<String, CategoryServiceProvider> categoryServiceProviderMap = new ConcurrentHashMap<>();

    public void setAdminItems(AdminItems adminItems) {
        this.adminItems = adminItems;
    }

    public AdminItems getAdminItems() {
        return adminItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStylePlugin(StylePlugin stylePlugin) {
        this.stylePlugin = stylePlugin;
    }

    public StylePlugin getStylePlugin() {
        return stylePlugin;
    }

    public TableColumnFactory getTableColumnFactory() {
        return tableColumnFactory;
    }

    public void setTableColumnFactory(TableColumnFactory tableColumnFactory) {
        this.tableColumnFactory = tableColumnFactory;
    }

    public Map<String, CategoryServiceProvider> getCategoryServiceProviderMap() {
        return categoryServiceProviderMap;
    }

    public void setCategoryServiceProviderMap(Map<String, CategoryServiceProvider> categoryServiceProviderMap) {
        this.categoryServiceProviderMap = categoryServiceProviderMap;
    }

    public FileUploadPlugin getFileUploadPlugin() {
        return fileUploadPlugin;
    }

    public void setFileUploadPlugin(FileUploadPlugin fileUploadPlugin) {
        this.fileUploadPlugin = fileUploadPlugin;
    }

    public LoginUserPlugin getLoginUserPlugin() {
        return loginUserPlugin;
    }

    public void setLoginUserPlugin(LoginUserPlugin loginUserPlugin) {
        this.loginUserPlugin = loginUserPlugin;
    }
}
