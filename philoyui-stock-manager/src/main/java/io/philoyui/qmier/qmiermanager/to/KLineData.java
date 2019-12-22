package io.philoyui.qmier.qmiermanager.to;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class KLineData implements Serializable {

    private Date day;

    private String open;

    private String high;

    private String low;

    private String close;

    private String volume;

    @SerializedName("ma_price15")
    private Double maPrice15;

    @SerializedName("ma_volume15")
    private Double maVolume15;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Double getMaPrice15() {
        return maPrice15;
    }

    public void setMaPrice15(Double maPrice15) {
        this.maPrice15 = maPrice15;
    }

    public Double getMaVolume15() {
        return maVolume15;
    }

    public void setMaVolume15(Double maVolume15) {
        this.maVolume15 = maVolume15;
    }
}
