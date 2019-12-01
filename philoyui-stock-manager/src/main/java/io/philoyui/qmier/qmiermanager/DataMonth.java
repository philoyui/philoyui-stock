package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.io.Serializable;
import java.util.Date;

@DescEntity(name = "月数据", domainName = "data_month")
public class DataMonth implements Serializable {

    @Desc(name="ID")
    private Long id;

    @Desc(name="代码",filter = true)
    private String symbol;

    @Desc(name = "时间")
    private Date day;

    @Desc(name="时间格式")
    private String dateString;

    @Desc(name = "开盘价")
    private Double open;

    @Desc(name="最高价")
    private Double high;

    @Desc(name = "最低价")
    private Double low;

    @Desc(name = "收盘价")
    private Double close;

    @Desc(name="成交量",order = true)
    private Long volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

}
