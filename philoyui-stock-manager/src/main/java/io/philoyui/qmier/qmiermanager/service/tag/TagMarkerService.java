package io.philoyui.qmier.qmiermanager.service.tag;

import java.util.List;

public interface TagMarkerService {

    TagMarker findByName(String identifier);

    List<TagMarker> findDayGlobalMarker();

    List<TagMarker> findDayEachProcessors();

    List<TagMarker> findWeekGlobalMarkers();

    List<TagMarker> findMonthEachMarkers();

    List<TagMarker> findWeekEachMarkers();

    List<TagMarker> findMonthGlobalMarkers();
}
