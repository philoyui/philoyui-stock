package cn.com.gome.page.button.column;

import cn.com.gome.page.core.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class LinkWithIdOperation extends ColumnAction{

    private final String name;

    private final String styleClass;

    private final String path;

    public LinkWithIdOperation(String name, String styleClass, String path) {
        this.name = name;
        this.styleClass = styleClass;
        this.path = path;
    }

    @Override
    public String displayButtons(HttpServletRequest request, PageConfig pageConfig, Object entity) {

        String id = getIdStringValue(entity);
        String link = String.format("%s%s", path, id);

        return "<a style=\"text-decoration:none\" class=\"ml-5\" href=\"" + link + "\" title=\"" + name + "\"><i class=\"Hui-iconfont\">" + styleClass + "</i></a>";

    }

}
