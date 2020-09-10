package io.philoyui.qmier.qmiermanager.analysis.service;

import io.philoyui.qmier.qmiermanager.analysis.domain.TimeData;

import java.util.List;

public interface MACDAnalysisService {

    List<TimeData> findLastAxis0Cross(String symbol);

    List<TimeData> findDeviate(String symbol);
}
