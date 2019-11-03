package cn.com.gome.page.setting;

import java.util.ArrayList;
import java.util.List;

public class AdminItemsBuilder {

    private List<RootItem> rootItems = new ArrayList<>();

    public AdminItemsBuilder addRootItem(String name, String identifier, LeafItem... leafItems) {
        rootItems.add(new RootItem(name,identifier,leafItems));
        return this;
    }

    public List<RootItem> getRootItems() {
        return rootItems;
    }

    public void setRootItems(List<RootItem> rootItems) {
        this.rootItems = rootItems;
    }

    public AdminItems build() {
        return new AdminItems(rootItems);
    }
}
