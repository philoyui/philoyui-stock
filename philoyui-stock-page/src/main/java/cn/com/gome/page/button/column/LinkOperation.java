package cn.com.gome.page.button.column;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class LinkOperation extends ColumnAction {

    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String link;

    /**
     * 字段列表
     */
    private String[] fields;

    public LinkOperation(String title, String link,String... fields) {
        this.title = title;
        this.link = link;
        this.fields = fields;
    }

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {
        String linkHtml = link;
        for (String field : fields) {
            linkHtml = linkHtml.replaceAll("#" + field + "#", this.getIdStringValue(entity, field));
        }
        return "<a href=\"" + linkHtml + "\" target=\"_blank\">" + title + "</a>";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
