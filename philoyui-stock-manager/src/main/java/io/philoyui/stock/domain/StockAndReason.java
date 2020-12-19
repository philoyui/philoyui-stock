package io.philoyui.stock.domain;

import java.io.Serializable;

public class StockAndReason implements Serializable {

    private String symbol;

    private String dayString;

    private String reason;

    public StockAndReason(String symbol, String dayString, String reason) {
        this.symbol = symbol;
        this.dayString = dayString;
        this.reason = reason;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }
}
