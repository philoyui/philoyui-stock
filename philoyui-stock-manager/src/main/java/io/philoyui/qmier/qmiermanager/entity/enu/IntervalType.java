package io.philoyui.qmier.qmiermanager.entity.enu;

public enum IntervalType {

    Day("日"),Week("周"),Month("月"),Min30("30分钟"),Min60("60分钟"),Min15("15分钟");

    IntervalType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

}
