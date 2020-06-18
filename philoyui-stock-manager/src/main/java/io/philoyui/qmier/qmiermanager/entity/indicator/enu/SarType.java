package io.philoyui.qmier.qmiermanager.entity.indicator.enu;

public enum SarType {

    Buy("多头"),
    Sell("空头");

    private String description;

    SarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
