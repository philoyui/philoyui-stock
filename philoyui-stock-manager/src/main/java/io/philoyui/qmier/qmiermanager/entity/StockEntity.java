package io.philoyui.qmier.qmiermanager.entity;

import cn.com.gome.cloud.openplatform.open.OpenConvertible;
import io.philoyui.qmier.qmiermanager.Stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockEntity implements OpenConvertible<Stock> {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 涨跌幅
     */
    private Double percent;

    /**
     * 流通股
     */
    private Long floatShares;

    /**
     * 当前价
     */
    private Double currentPrice;

    /**
     * 振幅
     */
    private Double amplitude;

    /**
     * 市值
     */
    private Long marketCapital;

    /**
     * 股息率
     */
    private Double dividendYield;

    /**
     * 成交额
     */
    private Double amount;

    /**
     * 涨跌额
     */
    private Double chg;

    /**
     * 成交量
     */
    private Long volume;

    /**
     * 量比
     */
    private Double volumeRatio;

    /**
     * 市净率
     */
    private Double pb;

    /**
     * 换手率
     */
    private Double turnoverRate;

    /**
     * 市盈率
     */
    private Double peTtm;

    /**
     * 总股本
     */
    private Long totalShares;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getFloatShares() {
        return floatShares;
    }

    public void setFloatShares(Long floatShares) {
        this.floatShares = floatShares;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    public Long getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(Long marketCapital) {
        this.marketCapital = marketCapital;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(Double volumeRatio) {
        this.volumeRatio = volumeRatio;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Double getPeTtm() {
        return peTtm;
    }

    public void setPeTtm(Double peTtm) {
        this.peTtm = peTtm;
    }

    public Long getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(Long totalShares) {
        this.totalShares = totalShares;
    }


    @Override
    public Stock converter() {
        Stock stock = new Stock();
        stock.setId(id);
        stock.setName(name);
        stock.setCode(code);
        stock.setPercent(percent);
        stock.setFloatShares(floatShares);
        stock.setCurrentPrice(currentPrice);
        stock.setAmplitude(amplitude);
        stock.setMarketCapital(marketCapital);
        stock.setDividendYield(dividendYield);
        stock.setAmount(amount);
        stock.setChg(chg);
        stock.setVolume(volume);
        stock.setVolumeRatio(volumeRatio);
        stock.setPb(pb);
        stock.setTurnoverRate(turnoverRate);
        stock.setPeTtm(peTtm);
        stock.setTotalShares(totalShares);
        return stock;
    }

    public static StockEntity constructFrom(Stock stock) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setName(stock.getName());
        stockEntity.setCode(stock.getCode());
        stockEntity.setPercent(stock.getPercent());
        stockEntity.setFloatShares(stock.getFloatShares());
        stockEntity.setCurrentPrice(stock.getCurrentPrice());
        stockEntity.setAmplitude(stock.getAmplitude());
        stockEntity.setMarketCapital(stock.getMarketCapital());
        stockEntity.setDividendYield(stock.getDividendYield());
        stockEntity.setAmount(stock.getAmount());
        stockEntity.setChg(stock.getChg());
        stockEntity.setVolume(stock.getVolume());
        stockEntity.setVolumeRatio(stock.getVolumeRatio());
        stockEntity.setPb(stock.getPb());
        stockEntity.setTurnoverRate(stock.getTurnoverRate());
        stockEntity.setPeTtm(stock.getPeTtm());
        stockEntity.setTotalShares(stock.getTotalShares());
        return stockEntity;
    }
}
