package io.philoyui.stock.client.east.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnnounceStockInfo implements Serializable {

    /**
     * 股票代码
     */
    @SerializedName("SECURITYCODE")
    private String stockCode;

    /**
     * 股票名称
     */
    @SerializedName("SECURITYFULLNAME")
    private String stockName;

    /**
     * 股票简称
     */
    @SerializedName("SECURITYSHORTNAME")
    private String stockShortName;

    /**
     * 交易所
     */
    @SerializedName("TRADEMARKET")
    private String tradeMarket;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockShortName() {
        return stockShortName;
    }

    public void setStockShortName(String stockShortName) {
        this.stockShortName = stockShortName;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }
}
