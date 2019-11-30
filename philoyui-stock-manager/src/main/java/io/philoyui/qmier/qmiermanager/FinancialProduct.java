package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "金融产品", domainName = "financial_product")
public class FinancialProduct {

    @Desc(name = "ID")
    private Long id;

    /**
     * 标识码
     */
    @Desc(name = "标识码",filter = true)
    private String symbol;

    /**
     * 代码
     */
    @Desc(name = "代码",filter = true)
    private String code;

    /**
     * 名称
     */
    @Desc(name = "名称",filter = true)
    private String name;

    /**
     * 交易所ID
     */
    @Desc(name="交易所",filter = true)
    private Long marketId;

    /**
     * 修改时间
     */
    @Desc(name="修改时间",order = true)
    private Date modifyTime;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
