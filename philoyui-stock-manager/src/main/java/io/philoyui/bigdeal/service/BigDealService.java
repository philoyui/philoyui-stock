package io.philoyui.bigdeal.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.bigdeal.entity.BigDealEntity;

public interface BigDealService extends GenericService<BigDealEntity,Long> {

    void fetchLastData();

}
