package io.philoyui.qmier.qmiermanager.client.east.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnnounceTypeInfo implements Serializable {

    /**
     * 报告类型
     */
    @SerializedName("COLUMNNAME")
    private String announceType;

    public String getAnnounceType() {
        return announceType;
    }

    public void setAnnounceType(String announceType) {
        this.announceType = announceType;
    }
}
