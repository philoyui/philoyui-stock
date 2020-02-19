package io.philoyui.qmier.qmiermanager.entity.enu;

public enum IntervalType {
    Day("日"),Week("周"),Month("月");

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
