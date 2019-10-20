package cn.com.gome.page.button.column;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.utils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class ColumnAction {

    public abstract String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity);

    protected String getIdStringValue(Object entity,String fieldId) {
        return BeanUtils.getPropertyValue(entity,fieldId).toString();
    }

    protected String getIdStringValue(Object entity) {
        return BeanUtils.getPropertyValue(entity,"id").toString();
    }

    protected String buildLayerButtonHtml(String title, String link, String actionText) {
        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"layer_show('" + title + "','" + link + "')\" href=\"javascript:;\" title=\"" +
                actionText + "\"><i class=\"Hui-iconfont\">" + actionText + "</i></a>";
    }

    protected String buildLinkButtonHtml(String confirmMsg,String method, String link, String actionText) {
        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"confirm_link('" + confirmMsg + "','"+method+"','" + link + "')\" href=\"javascript:;\" title=\"" +
                actionText + "\"><i class=\"Hui-iconfont\">" + actionText + "</i></a>";
    }

    protected String buildConfirmButtonHtml(String confirmMsg,String method, String link,  String actionText) {
        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"confirm_link('" + confirmMsg + "','"+method+"','" + link + "')\" href=\"javascript:;\" title=\"" +
                actionText + "\"><i class=\"Hui-iconfont\">" + actionText + "</i></a>";
    }

    protected static String buildFormButtonHtml(String title, String link, String iconFontCode, String actionText) {
        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"layer_show('" + title + "','" + link + "')\" href=\"javascript:;\" title=\"" +
                actionText + "\"><i class=\"Hui-iconfont\">" + iconFontCode + "</i></a>";
    }

    protected static String buildTabButtonHtml(String title, String link, String actionText) {
        return "<a style=\"text-decoration:none\" class=\"ml-5\" data-href=\"" + link + "\" data-title=\"" + title + "\" onClick=\"Hui_admin_tab(this)\">"+actionText+"</a>";
    }
}
