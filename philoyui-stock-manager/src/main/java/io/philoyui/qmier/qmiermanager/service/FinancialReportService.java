package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;

import java.util.List;

public interface FinancialReportService extends GenericService<FinancialReportEntity,Long> {

    void insertAll(List<FinancialReportEntity> financialReports);

    void downloadHistory(StockEntity stockEntity);

}
