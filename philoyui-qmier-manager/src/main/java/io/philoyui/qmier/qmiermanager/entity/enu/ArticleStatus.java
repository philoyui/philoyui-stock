package io.philoyui.qmier.qmiermanager.entity.enu;

public enum ArticleStatus {

    EMPTY(""),WAITING("待抓取"),COMPLETED("已抓取"),ANALYSIS("已分析");

    private String desc;

    ArticleStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public String toString() {
        return desc;
    }
}
