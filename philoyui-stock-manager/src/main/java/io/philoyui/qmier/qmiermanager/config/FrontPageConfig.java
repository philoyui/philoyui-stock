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
                .addRootItem("抓取记录","goods",
                        new LeafItem("记录管理", "/admin/article_record/page")
                )
                .addRootItem("Qmier管理","order",
                        new LeafItem("Qmier管理", "/admin/qmier/page"),
                        new LeafItem("照片管理", "/admin/qmier_image/page"),
                        new LeafItem("评论管理", "/admin/qmier_comment/page")
                )
                .addRootItem("股票管理","stock",
                        new LeafItem("股票管理", "/admin/stock/page")
                )
                .addRootItem("推送管理","member",
                        new LeafItem("权限管理", "/admin/push_type/page"),
                        new LeafItem("消息管理", "/admin/push_type/page"),
                        new LeafItem("消息推送记录", "/admin/member/page")
                )
                .addRootItem("财务管理","finance",
                        new LeafItem("充值管理", "/admin/recharge/page"),
                        new LeafItem("提现管理", "/admin/withdraw/page"),
                        new LeafItem("佣金管理", "/admin/commission/page"),
                        new LeafItem("收入管理", "/admin/income/page")
                )
                .addRootItem("系统设置","config",
                        new LeafItem("系统设置", "/admin/config/page")
                )
                .build();

        PageSettings pageSettings = new PageSettings();
        pageSettings.setStylePlugin(new HuiStylePlugin()); //使用的页面风格
        pageSettings.setAdminItems(adminItems);
        pageSettings.setTitle("三级分销商城"); //后台标题
        pageSettings.setFileUploadPlugin(fileUploadPlugin); //使用的上传插件 默认不支持上传
        pageSettings.setLoginUserPlugin(new SecurityLoginUserPlugin()); //用户会话管理插件
        return new PageManager(applicationContext,pageSettings);

    }

}
