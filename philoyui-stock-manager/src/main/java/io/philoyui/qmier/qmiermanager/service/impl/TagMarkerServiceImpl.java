package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.service.tag.TagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class TagMarkerServiceImpl implements TagMarkerService {

    @Autowired
    private ApplicationContext applicationContext;

    private List<TagMarker> dayTagMarkers = new ArrayList<>();

    private List<TagMarker> weekTagMarkers = new ArrayList<>();

    private List<TagMarker> monthTagMarkers = new ArrayList<>();

    private Map<String, TagMarker> nameTagMarkerMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void start(){

        Map<String, TagMarker> tagMarkerBeanMap = applicationContext.getBeansOfType(TagMarker.class);

        for (String tagName : tagMarkerBeanMap.keySet()) {
            nameTagMarkerMap.put(tagName, tagMarkerBeanMap.get(tagName));
        }

        for (TagMarker tagMarker : tagMarkerBeanMap.values()) {
            if(tagMarker.supportDate()){
                dayTagMarkers.add(tagMarker);
            }
            if(tagMarker.supportWeek()){
                weekTagMarkers.add(tagMarker);
            }
            if(tagMarker.supportMonth()){
                monthTagMarkers.add(tagMarker);
            }
        }

    }

    @Override
    public TagMarker findByName(String identifier) {
        return nameTagMarkerMap.get(identifier);
    }

    @Override
    public List<TagMarker> findDayGlobalMarker() {
        return dayTagMarkers.stream().filter(TagMarker::isGlobal).collect(Collectors.toList());
    }

    @Override
    public List<TagMarker> findDayEachProcessors() {
        return dayTagMarkers.stream().filter(tagMarker -> !tagMarker.isGlobal()).collect(Collectors.toList());
    }

    @Override
    public List<TagMarker> findWeekGlobalMarkers() {
        return weekTagMarkers.stream().filter(TagMarker::isGlobal).collect(Collectors.toList());
    }

    @Override
    public List<TagMarker> findMonthEachMarkers() {
        return monthTagMarkers.stream().filter(tagMarker -> !tagMarker.isGlobal()).collect(Collectors.toList());
    }

    @Override
    public List<TagMarker> findWeekEachMarkers() {
        return weekTagMarkers.stream().filter(tagMarker -> !tagMarker.isGlobal()).collect(Collectors.toList());
    }

    @Override
    public List<TagMarker> findMonthGlobalMarkers() {
        return monthTagMarkers.stream().filter(TagMarker::isGlobal).collect(Collectors.toList());
    }

}
