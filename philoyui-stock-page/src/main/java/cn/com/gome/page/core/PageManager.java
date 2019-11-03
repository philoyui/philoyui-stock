package cn.com.gome.page.core;

import cn.com.gome.page.plugins.upload.FileUploadPlugin;
import cn.com.gome.page.setting.AdminItems;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class PageManager {

    private ApplicationContext applicationContext;

    /**
     * 全局配置
     */
    private PageSettings pageSettings;

    /**
     * 上下文信息
     */
    private PageContext pageContext;


    private FileUploadPlugin fileUploadPlugin;

    public PageManager(ApplicationContext applicationContext, PageSettings pageSettings) {
        this.fileUploadPlugin = pageSettings.getFileUploadPlugin();
        this.applicationContext = applicationContext;
        this.pageSettings = pageSettings;
        initComponents();
    }

    /**
     * 初始化各个模块
     */
    private void initComponents() {
        PageContext pageContext = new PageContext(pageSettings);
        Map<String, PageService> pageServiceBeanMap = applicationContext.getBeansOfType(PageService.class);
        for (PageService pageService : pageServiceBeanMap.values()) {
            PageConfig pageConfig = pageService.initPageConfig(pageContext);

            pageContext.addPageService(pageConfig.getDomainName(),pageService);
        }
        this.pageContext = pageContext;
    }

    /**
     * 根据领域对象获取PageService
     * @param domainName
     * @return
     */
    public PageService findServiceByDomainName(String domainName) {
        return pageContext.findServiceByDomainName(domainName);
    }

    /**
     * 获取菜单
     * @return
     */
    public AdminItems getAdminItems() {
        return pageSettings.getAdminItems();
    }

    /**
     * 获取控制台标题
     * @return
     */
    public String getTitle() {
        return pageSettings.getTitle();
    }

    public String getLoginUsername() {
        return pageContext.getLoginUserPlugin().getLoginUsername();
    }

    public FileUploadPlugin getFileUploadPlugin() {
        return fileUploadPlugin;
    }
}
