package cn.com.gome.cloud.openplatform.common;

import java.io.Serializable;

/**
 * Created by yangyu-ds on 2016/9/22.
 */
public class Order implements Serializable{

    private String attr;

    private boolean desc;

    public Order() {
        //
    }

    public Order(String attr, boolean desc) {
        this.attr = attr;
        this.desc = desc;
    }

    public static Order desc(String attr) {
        return new Order(attr,true);
    }


    public static Order asc(String attr) {
        return new Order(attr,false);
    }


    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
