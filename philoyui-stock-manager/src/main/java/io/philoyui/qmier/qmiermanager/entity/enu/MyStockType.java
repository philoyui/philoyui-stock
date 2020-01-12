package io.philoyui.qmier.qmiermanager.entity.enu;

public enum MyStockType {

    Research("投资者关系活动记录表"),BigBuyHighPremium("大宗交易高溢价"),BigBuyHighVolume("大宗交易大量"),BigBuy("大宗交易");

    private String desc;

    MyStockType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
