package cn.com.gome.page.domain;

import java.io.Serializable;

public interface CategoryEntity extends Serializable {

    boolean isRootElement();

    String getNodeId();

    String getParentNodeId();

    String getNodeName();

}
