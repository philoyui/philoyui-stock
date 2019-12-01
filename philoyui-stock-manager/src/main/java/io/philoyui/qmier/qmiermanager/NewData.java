package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "当前股票价格", domainName = "data_15min")
public class NewData {
    
    @Desc(name = "股票代码",filter = true,order = true)
    private String code;

    @Desc(name = "股票名称",filter = true)
    private String stockName;

    @Desc(name = "当前价")
    private Double current;
    
    @Desc(name = "时间点")
    private String timeString;

    @Desc(name = "当前时间",order = true)
    private Date currentTime;

    @Desc(name ="开盘价")
    private Double open;

    @Desc(name = "最高价")
    private Double high;

    @Desc(name="最低价")
    private Double low;

    @Desc(name="收盘价")
    private Double close;

    @Desc(name="成交量")
    private Long volume;

    @Desc(name="成交额")
    private Double amount;

    @Desc(name = "买1数量")
    private Long buyCount1;

    @Desc(name = "买1价格")
    private Double buyPrice1;

    @Desc(name = "买2数量")
    private Long buyCount2;

    @Desc(name = "买2价格")
    private Double buyPrice2;

    @Desc(name = "买3数量")
    private Long buyCount3;

    @Desc(name = "买3价格")
    private Double buyPrice3;

    @Desc(name = "买4数量")
    private Long buyCount4;

    @Desc(name = "买4价格")
    private Double buyPrice4;

    @Desc(name = "买5数量")
    private Long buyCount5;

    @Desc(name = "买5价格")
    private Double buyPrice5;

    @Desc(name = "卖1数量")
    private Long sellCount1;

    @Desc(name = "卖1价格")
    private Double sellPrice1;

    @Desc(name = "卖2数量")
    private Long sellCount2;

    @Desc(name = "卖2价格")
    private Double sellPrice2;

    @Desc(name = "卖3数量")
    private Long sellCount3;

    @Desc(name = "卖3价格")
    private Double sellPrice3;

    @Desc(name = "卖4数量")
    private Long sellCount4;

    @Desc(name = "卖4价格")
    private Double sellPrice4;

    @Desc(name = "卖5数量")
    private Long sellCount5;

    @Desc(name = "卖5价格")
    private Double sellPrice5;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getBuyCount1() {
        return buyCount1;
    }

    public void setBuyCount1(Long buyCount1) {
        this.buyCount1 = buyCount1;
    }

    public Double getBuyPrice1() {
        return buyPrice1;
    }

    public void setBuyPrice1(Double buyPrice1) {
        this.buyPrice1 = buyPrice1;
    }

    public Long getBuyCount2() {
        return buyCount2;
    }

    public void setBuyCount2(Long buyCount2) {
        this.buyCount2 = buyCount2;
    }

    public Double getBuyPrice2() {
        return buyPrice2;
    }

    public void setBuyPrice2(Double buyPrice2) {
        this.buyPrice2 = buyPrice2;
    }

    public Long getBuyCount3() {
        return buyCount3;
    }

    public void setBuyCount3(Long buyCount3) {
        this.buyCount3 = buyCount3;
    }

    public Double getBuyPrice3() {
        return buyPrice3;
    }

    public void setBuyPrice3(Double buyPrice3) {
        this.buyPrice3 = buyPrice3;
    }

    public Long getBuyCount4() {
        return buyCount4;
    }

    public void setBuyCount4(Long buyCount4) {
        this.buyCount4 = buyCount4;
    }

    public Double getBuyPrice4() {
        return buyPrice4;
    }

    public void setBuyPrice4(Double buyPrice4) {
        this.buyPrice4 = buyPrice4;
    }

    public Long getBuyCount5() {
        return buyCount5;
    }

    public void setBuyCount5(Long buyCount5) {
        this.buyCount5 = buyCount5;
    }

    public Double getBuyPrice5() {
        return buyPrice5;
    }

    public void setBuyPrice5(Double buyPrice5) {
        this.buyPrice5 = buyPrice5;
    }

    public Long getSellCount1() {
        return sellCount1;
    }

    public void setSellCount1(Long sellCount1) {
        this.sellCount1 = sellCount1;
    }

    public Double getSellPrice1() {
        return sellPrice1;
    }

    public void setSellPrice1(Double sellPrice1) {
        this.sellPrice1 = sellPrice1;
    }

    public Long getSellCount2() {
        return sellCount2;
    }

    public void setSellCount2(Long sellCount2) {
        this.sellCount2 = sellCount2;
    }

    public Double getSellPrice2() {
        return sellPrice2;
    }

    public void setSellPrice2(Double sellPrice2) {
        this.sellPrice2 = sellPrice2;
    }

    public Long getSellCount3() {
        return sellCount3;
    }

    public void setSellCount3(Long sellCount3) {
        this.sellCount3 = sellCount3;
    }

    public Double getSellPrice3() {
        return sellPrice3;
    }

    public void setSellPrice3(Double sellPrice3) {
        this.sellPrice3 = sellPrice3;
    }

    public Long getSellCount4() {
        return sellCount4;
    }

    public void setSellCount4(Long sellCount4) {
        this.sellCount4 = sellCount4;
    }

    public Double getSellPrice4() {
        return sellPrice4;
    }

    public void setSellPrice4(Double sellPrice4) {
        this.sellPrice4 = sellPrice4;
    }

    public Long getSellCount5() {
        return sellCount5;
    }

    public void setSellCount5(Long sellCount5) {
        this.sellCount5 = sellCount5;
    }

    public Double getSellPrice5() {
        return sellPrice5;
    }

    public void setSellPrice5(Double sellPrice5) {
        this.sellPrice5 = sellPrice5;
    }
}
