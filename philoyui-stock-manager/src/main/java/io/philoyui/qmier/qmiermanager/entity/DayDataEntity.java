package io.philoyui.qmier.qmiermanager.entity;

import io.philoyui.qmier.qmiermanager.Desc;

import java.io.Serializable;

/**
 * 日线数据
 */
@DescEntity(name="日线数据",domainName="day_data")
public class DayDataEntity implements Serializable {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "代码", require=true)
    private String code;

    @Desc(name="时间戳",order = true)
    private String dayString;

    @Desc(name="当前价",writable=false)
    private Double currentPrice;

    public DayDataEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
