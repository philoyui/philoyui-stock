package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.FinancialReportDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialReportServiceImpl extends GenericServiceImpl<FinancialReportEntity,Long> implements FinancialReportService {

    @Autowired
    private FinancialReportDao financialReportDao;

    @Override
    protected GenericDao<FinancialReportEntity, Long> getDao() {
        return financialReportDao;
    }

}