package cn.com.gome.page.setting;

public class RootItem {

    private final String name;

    private final String identifier;

    private final LeafItem[] leafItems;

    public RootItem(String name,String identifier, LeafItem... leafItems) {
        this.name = name;
        this.identifier = identifier;
        this.leafItems = leafItems;
    }

    public String getName() {
        return name;
    }

    public LeafItem[] getLeafItems() {
        return leafItems;
    }

    public String getIdentifier() {
        return identifier;
    }
}
