package io.philoyui.qmier.qmiermanager.entity.enu;

public enum NoticeType{

    Researched("投资者关系活动记录表");

    private String description;

    NoticeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
