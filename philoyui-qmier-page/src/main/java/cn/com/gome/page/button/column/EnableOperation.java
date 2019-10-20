package cn.com.gome.page.button.column;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.utils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class EnableOperation extends ColumnAction {

    private String enable;

    public EnableOperation(String enable) {
        super();
        this.enable = enable;
    }

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String id = getIdStringValue(entity);
        String domainName = pageConfig.getDomainName();

        boolean enabled = (Boolean) BeanUtils.getPropertyValue(entity, enable);

        if(!enabled){
            String enableLink = String.format("/admin/%s/enable?id=%s", domainName, id);
            return buildLinkButtonHtml("确认启用啊？","put",enableLink, "启用");
        }else{
            String disableLink = String.format("/admin/%s/disable?id=%s", domainName, id);
            return buildLinkButtonHtml("确认停用吗？","put",disableLink, "停用");
        }
    }
}
