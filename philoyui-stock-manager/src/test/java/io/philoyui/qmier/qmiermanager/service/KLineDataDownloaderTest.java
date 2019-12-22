package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.service.impl.KLineDataDownloaderImpl;
import org.junit.Before;
import org.junit.Test;

public class KLineDataDownloaderTest {

    private KLineDataDownloader KLineDataDownloader = new KLineDataDownloaderImpl();

    @Before
    public void setUp() throws Exception {

    }


    /**
     *
     * 链接List -》 遍历抓取 -》 抓取内容转换类型 -》 转换后保存到数据库 -》 停留时间
     *
     * 数据来源-》遍历数据-》构造链接-》抓取数据-》转换对象 -》 数据保存 -》停留时间
     */
    @Test
    public void process() {


    }
}