package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.page.field.DoubleFieldDefinition;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 金融商品定时器，主要获取新的交易产品，如每天的新股
 */
@Component
public class StockTimer {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleFieldDefinition.class);

    @Autowired
    private StockService stockService;

    /**
     * 每天读取股票列表（分为上证，深证，创业板，科创板，港股，美股，期货，外汇，贵金属），发现新股存入，每天执行一次
     *
     * http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=3&num=40&sort=symbol&asc=1&node=sh_a&symbol=&_s_r_a=page
     *
     */
    @Scheduled(cron="* * 8 * * ? ") //早晨八点
    public void fetch(){
        stockService.downloadHistoryData();
    }

}
