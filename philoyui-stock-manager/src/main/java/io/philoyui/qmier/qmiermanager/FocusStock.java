package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "关注股票", domainName = "focus_stock")
public class FocusStock {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "股票代码")
    private String symbol;

    @Desc(name = "股票名称")
    private String stockName;

    @Desc(name = "加入时间")
    private Date addTime;

    @Desc(name = "分析时间")
    private Date analysisTime;

    @Desc(name = "分析结果")
    private String analysisResult;

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public Date getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(Date analysisTime) {
        this.analysisTime = analysisTime;
    }
}
