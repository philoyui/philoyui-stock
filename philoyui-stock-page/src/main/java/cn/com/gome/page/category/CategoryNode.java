package cn.com.gome.page.category;


import cn.com.gome.page.domain.CategoryEntity;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryNode {

    private String v;

    private String n;

    private CategoryNode[] s = new CategoryNode[]{};

    public CategoryNode(String id, String name) {
        v = id;
        n = name;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public static CategoryNode[] generateCategoryTree(List<CategoryEntity> baseCategoryEntities) {

        Map<String,CategoryNode> nodeMetadataMap = new HashMap<>();

        List<CategoryNode> trees = new ArrayList<>();

        for (CategoryEntity categoryEntity : baseCategoryEntities) {

            CategoryNode categoryNode = new CategoryNode(categoryEntity.getNodeId(), categoryEntity.getNodeName());

            if (categoryEntity.isRootElement()) {
                trees.add(categoryNode);
            }

            nodeMetadataMap.put(categoryEntity.getNodeId(),categoryNode);

        }

        for (CategoryEntity categoryEntity : baseCategoryEntities) {

            if(!categoryEntity.isRootElement()){
                CategoryNode parentNode = nodeMetadataMap.get(categoryEntity.getParentNodeId());
                if(parentNode!=null){
                    parentNode.addChildNode(new CategoryNode(categoryEntity.getNodeId(), categoryEntity.getNodeName()));
                }
            }
        }

        return trees.toArray(new CategoryNode[trees.size()]);
    }

    private void addChildNode(CategoryNode categoryNode) {
        this.s = (CategoryNode[]) ArrayUtils.add(s, categoryNode);
    }
}
