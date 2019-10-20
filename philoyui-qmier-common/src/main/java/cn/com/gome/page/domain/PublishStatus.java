package cn.com.gome.page.domain;

public enum PublishStatus {
    CREATED("已创建"),
    PENDING("待审核"),
    PASS("审核通过"),
    REFUSE("审核不通过"),
    DELETE("已删除");

    String description;

    private PublishStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.description;
    }
}
