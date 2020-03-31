package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessorService;
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
public class TagProcessorServiceImpl implements TagProcessorService {

    @Autowired
    private StockService stockService;

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private DataWeekService dataWeekService;

    @Autowired
    private DataMonthService dataMonthService;

    @Autowired
    private ApplicationContext applicationContext;

    private List<TagProcessor> tagProcessors = new ArrayList<>();

    private Map<String,TagProcessor> nameTagProcessors = new ConcurrentHashMap<>();

    @PostConstruct
    public void start(){
        Map<String, TagProcessor> pageServiceBeanMap = applicationContext.getBeansOfType(TagProcessor.class);
        tagProcessors.addAll(pageServiceBeanMap.values());

        for (String tagName : pageServiceBeanMap.keySet()) {
            nameTagProcessors.put(tagName,nameTagProcessors.get(tagName));
        }
    }

    @Override
    public List<TagProcessor> findAllDayTagProcessors() {
        return tagProcessors.stream().filter(TagProcessor::supportDate).collect(Collectors.toList());
    }

    @Override
    public List<TagProcessor> findAllWeekTagProcessors() {
        return tagProcessors.stream().filter(TagProcessor::supportWeek).collect(Collectors.toList());
    }

    @Override
    public List<TagProcessor> findAllMonthTagProcessors() {
        return tagProcessors.stream().filter(TagProcessor::supportMonth).collect(Collectors.toList());
    }

    @Override
    public TagProcessor findByName(String identifier) {
        return nameTagProcessors.get(identifier);
    }

}
