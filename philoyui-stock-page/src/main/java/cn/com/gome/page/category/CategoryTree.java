package cn.com.gome.page.category;


import cn.com.gome.page.domain.CategoryEntity;

import java.util.*;

/**
 * Created by yangyu-ds on 2018/5/9.
 */
public class CategoryTree {

    private List<CategoryEntity> categoryEntities;

    /**
     * 存储节点数据
     */
    private Map<String,Node> nodeMetadataMap = new HashMap<>();

    private List<Node> treeNodes = new LinkedList<>();

    private Map<String, String> optionHashMap = new LinkedHashMap<>();

    public CategoryTree() {
        //
    }

    public CategoryTree(CategoryEntity[] categoryEntities) {

        for (CategoryEntity categoryEntity : categoryEntities) {
            nodeMetadataMap.put(categoryEntity.getNodeId(),new Node(categoryEntity.getNodeId(),categoryEntity.getNodeName(),categoryEntity.getParentNodeId()));
        }

        for (CategoryEntity categoryEntity : categoryEntities) {
            this.addElement(categoryEntity);
        }
        optionHashMap = transformToOptionMap();

    }

    public void addElement(CategoryEntity categoryEntity) {
        Node node = nodeMetadataMap.get(categoryEntity.getNodeId());
        if(categoryEntity.isRootElement()){
            treeNodes.add(node);
        }else{
            Node parentNode = nodeMetadataMap.get(categoryEntity.getParentNodeId());
            if(parentNode!=null){
                parentNode.addChildNode(node);
            }
        }
    }

    public Map<String,String> transformToOptionMap() {
        LinkedHashMap<String,String> optionMap = new LinkedHashMap<>();
        String preLine = "";
        optionMap.put("0","根节点");
        deepLoop(optionMap,treeNodes,preLine);
        return optionMap;
    }

    private void deepLoop(Map<String, String> optionMap, List<Node> nodes, String preLine){
        String preNewLine = preLine + "---";
        for (Node node : nodes) {
            optionMap.put(node.getId().toString(),preNewLine + " " + node.getName());
            if(!node.getChildrenNodes().isEmpty()){
                deepLoop(optionMap,node.getChildrenNodes(), preNewLine);
            }
        }
    }

    public Map<String, String> getOptionHashMap() {
        return optionHashMap;
    }
}
