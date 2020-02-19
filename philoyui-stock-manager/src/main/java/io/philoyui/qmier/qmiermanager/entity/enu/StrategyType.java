package io.philoyui.qmier.qmiermanager.entity.enu;

public enum StrategyType {

    ADD("选取"),REDUCE("排除"),COMMON("普通");

    StrategyType(String desc) {
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
