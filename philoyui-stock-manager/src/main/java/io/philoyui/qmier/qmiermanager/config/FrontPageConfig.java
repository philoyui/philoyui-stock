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
                .addRootItem("股票管理","stock",
                        new LeafItem("日线数据", "/admin/data_day/page"),
                        new LeafItem("周线数据", "/admin/data_week/page"),
                        new LeafItem("月线数据", "/admin/data_month/page"),
                        new LeafItem("15min数据", "/admin/data_15min/page"),
                        new LeafItem("30min数据", "/admin/data_30min/page"),
                        new LeafItem("60min数据", "/admin/data_hour/page")
                )
                .addRootItem("财报管理","financial_report",
                        new LeafItem("财报管理", "/admin/financial_report/page")
                )
                .addRootItem("系统管理","system",
                        new LeafItem("市场管理", "/admin/financial_market/page"),
                        new LeafItem("任务管理", "/admin/timer_task/page"),
                        new LeafItem("任务日志管理", "/admin/timer_task_log/page")
                )
                .addRootItem("选股管理","filter",
                        new LeafItem("打分管理","/admin/my_stock/page"),
                        new LeafItem("打标管理","/admin/tag_stock/page"),
                        new LeafItem("股票指标", "/admin/stock_indicator/page"),
                        new LeafItem("标签管理", "/admin/tag/page"),
                        new LeafItem("股票管理", "/admin/stock/page"),
                        new LeafItem("TradingView数据","/admin/trading_view/page")
                )
                .addRootItem("关注管理","focus",
                        new LeafItem("关注股票", "/admin/focus_stock/page")
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
