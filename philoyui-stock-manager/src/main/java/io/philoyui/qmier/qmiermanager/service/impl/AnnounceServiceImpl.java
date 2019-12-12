package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import cn.com.gome.page.excp.GmosException;
import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClient;
import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClientImpl;
import io.philoyui.qmier.qmiermanager.client.east.data.AnnounceGetData;
import io.philoyui.qmier.qmiermanager.client.east.request.AnnounceGetRequest;
import io.philoyui.qmier.qmiermanager.client.east.response.AnnounceGetResponse;
import io.philoyui.qmier.qmiermanager.dao.AnnounceDao;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;
import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class AnnounceServiceImpl extends GenericServiceImpl<AnnounceEntity,Long> implements AnnounceService {

    private static final Logger LOG = LoggerFactory.getLogger(AnnounceServiceImpl.class);

    @Autowired
    private AnnounceDao announceDao;

    @Override
    protected GenericDao<AnnounceEntity, Long> getDao() {
        return announceDao;
    }

    @Override
    public boolean existsByDetailUrl(String detailUrl) {
        return announceDao.existsByDetailUrl(detailUrl);
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    @Override
    public void downloadAnnounce(int pageNo) {
        EastMoneyClient client = new EastMoneyClientImpl();
        AnnounceGetRequest request = new AnnounceGetRequest();
        request.setPageNo(pageNo);
        request.setPageSize(100);
        AnnounceGetResponse response = client.execute(request);
        for (AnnounceGetData data : response.getData()) {
            if (!this.existsByDetailUrl(data.getUrl())) { //未录入的公告

                //创业板不记录
                String code = data.getAnnounceStockInfoList().get(0).getStockCode();
                if (code.startsWith("300")) {
                    continue;
                }

                AnnounceEntity announceEntity = new AnnounceEntity();
                announceEntity.setTitle(data.getNoticeTitle());
                announceEntity.setSymbol(buildSymbol(code));
                announceEntity.setPublishTime(data.getPublishTime());
                announceEntity.setDetailUrl(data.getUrl());
                announceEntity.setAnnounceType(data.getAnnounceTypeInfoList().get(0).getAnnounceType());
                this.insert(announceEntity);
            }
        }
        LOG.info("下载公告数据成功，当前页数为：" + pageNo);
    }

    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else {
            return code;
        }
    }

    @Recover
    public void recover(GmosException e) {
        LOG.error(e.getMessage());
    }

}