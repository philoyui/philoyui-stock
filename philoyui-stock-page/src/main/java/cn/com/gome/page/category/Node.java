package cn.com.gome.page.category;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangyu-ds on 2018/5/9.
 */
public class Node {

    private String id;

    private String name;

    private String parentId;

    private List<Node> childrenNodes = new LinkedList<>();

    public Node(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public List<Node> listNodes() {
        return childrenNodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<Node> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public void addChildNode(Node node) {
        childrenNodes.add(node);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
