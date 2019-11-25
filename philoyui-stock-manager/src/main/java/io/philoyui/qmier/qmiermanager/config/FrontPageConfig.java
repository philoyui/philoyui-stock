package io.philoyui.qmier.qmiermanager.config;

import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageSettings;
import cn.com.gome.page.plugins.style.hui.HuiStylePlugin;
import cn.com.gome.page.plugins.upload.FileUploadPlugin;
import cn.com.gome.page.setting.AdminItems;
import cn.com.gome.page.setting.AdminItemsBuilder;
import cn.com.gome.page.setting.LeafItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrontPageConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Qualifier("aliYunUploadPlugin")
    @Autowired
    private FileUploadPlugin fileUploadPlugin; //GFS上传插件

    @Bean
    public PageManager pageManager(){

        AdminItems adminItems = new AdminItemsBuilder()
                .addRootItem("文章管理","column",
                        new LeafItem("文章管理", "/admin/article/page"),
                        new LeafItem("分类管理", "/admin/article_category/page")
                )
                .addRootItem("股票管理","stock",
                        new LeafItem("股票管理", "/admin/stock/page")
                )
                .build();

        PageSettings pageSettings = new PageSettings();
        pageSettings.setStylePlugin(new HuiStylePlugin()); //使用的页面风格
        pageSettings.setAdminItems(adminItems);
        pageSettings.setTitle("股票分析"); //后台标题
        pageSettings.setFileUploadPlugin(fileUploadPlugin); //使用的上传插件 默认不支持上传
        pageSettings.setLoginUserPlugin(new SecurityLoginUserPlugin()); //用户会话管理插件
        return new PageManager(applicationContext,pageSettings);

    }

}
