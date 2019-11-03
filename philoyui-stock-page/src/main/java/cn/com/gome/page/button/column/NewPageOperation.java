package cn.com.gome.page.button.column;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class NewPageOperation extends ColumnAction{

    private String name;

    private String title;

    private String link;

    private String[] fields;


    public NewPageOperation(String name, String link,String title,String... fieldIds) {
        this.name = name;
        this.title = title;
        this.link = link;
        this.fields = fieldIds;
    }

    /**
     * <a data-href="放要打开的链接" data-title="弹出窗口标题" onClick="Hui_admin_tab(this)">
     *
     * @param request
     * @param pageConfig
     * @param entity
     * @return
     */
    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String linkHtml = link;


        for (String field : fields) {
            linkHtml = linkHtml.replaceAll("#" + field + "#", this.getIdStringValue(entity, field));
        }

        return buildTabButtonHtml(title,linkHtml,name);
    }

}
