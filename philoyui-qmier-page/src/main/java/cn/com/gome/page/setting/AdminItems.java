package cn.com.gome.page.setting;

import java.util.List;

public class AdminItems {

    private List<RootItem> rootItems;

    public AdminItems(List<RootItem> rootItems) {
        this.rootItems = rootItems;
    }

    public List<RootItem> getRootItems() {
        return rootItems;
    }

    public void setRootItems(List<RootItem> rootItems) {
        this.rootItems = rootItems;
    }
}
