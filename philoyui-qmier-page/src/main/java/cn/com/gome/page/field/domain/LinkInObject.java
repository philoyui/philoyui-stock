package cn.com.gome.page.field.domain;

import java.util.ArrayList;
import java.util.List;

public class LinkInObject {

    private String name;

    private String value;

    private List<LinkInObject> sub = new ArrayList<>();

    public LinkInObject(long id, String name) {
        this.name = String.valueOf(id);
        this.value = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<LinkInObject> getSub() {
        return sub;
    }

    public void setSub(List<LinkInObject> sub) {
        this.sub = sub;
    }

    public void addSubObject(long id, String name) {
        sub.add(new LinkInObject(id,name));
    }

    public void addSubObject(LinkInObject linkInObject) {
        sub.add(linkInObject);
    }
}
