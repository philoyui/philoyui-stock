package io.philoyui.qmier.qmiermanager.entity.stock;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 股票的年线数据
 */
public class StockYearDataEntity implements Serializable {

    @Id
    private Long id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 年
     */
    private String year;

    /**
     * 资产收益率
     */
    private Double roe;


    /**
     * 每股收益 f55
     */
    private Double earningsPerShare;

    /**
     * 负债率 f57
     */
    private Double debtRatio;

    /**
     * 毛利率 f49
     */
    private Double grossMargin;

    /**
     * 主营业务收入 f41
     */
    private Double salesRevenue;



}
