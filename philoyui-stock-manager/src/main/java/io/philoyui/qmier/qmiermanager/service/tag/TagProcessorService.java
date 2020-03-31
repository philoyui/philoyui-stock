package io.philoyui.qmier.qmiermanager.service.tag;

import java.util.List;

public interface TagProcessorService {

    List<TagProcessor> findAllDayTagProcessors();

    List<TagProcessor> findAllWeekTagProcessors();

    List<TagProcessor> findAllMonthTagProcessors();

    TagProcessor findByName(String identifier);

}
