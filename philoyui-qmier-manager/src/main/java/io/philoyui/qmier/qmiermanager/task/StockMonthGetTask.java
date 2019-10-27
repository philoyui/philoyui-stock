package io.philoyui.qmier.qmiermanager.task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 股票月线数据获取器
 */
public class StockMonthGetTask {

    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次
    public void statusCheck() {

    }

}
