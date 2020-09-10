package io.philoyui.qmier.qmiermanager.analysis.domain;

import java.io.Serializable;
import java.util.Date;

public class TimeData implements Serializable {

    /**
     * 发生日期
     */
    private Date currentDate;

    /**
     * 时间点数据
     */
    private Double data;

    /**
     * 数据类型
     */
    private DataType dataType;

    /**
     * 股票标识
     */
    private String symbol;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
