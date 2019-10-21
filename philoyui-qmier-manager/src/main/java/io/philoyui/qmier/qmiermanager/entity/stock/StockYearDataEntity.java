package io.philoyui.qmier.qmiermanager.entity.stock;

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
}
