package io.philoyui.stock.service;

import io.philoyui.stock.entity.enu.TaskType;

/**
 * K线数据下载工具
 */
public interface KLineDataDownloader {

    /**
     * 下载K线数据
     * @param taskType                  K线数据的类型
     * @param downloadDataCallback      下载数据如何处理回调
     */
    void download(TaskType taskType, DownloadDataCallback downloadDataCallback);

}
