package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IndicatorProviders {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, IndicatorProvider> stockIndicatorMap = new ConcurrentHashMap<>();

    public void start(){
        Map<String, IndicatorProvider> stockIndicatorProcessorMap = applicationContext.getBeansOfType(IndicatorProvider.class);
        for (IndicatorProvider indicatorProvider : stockIndicatorProcessorMap.values()) {
            stockIndicatorMap.put(indicatorProvider.identifier(), indicatorProvider);
        }
    }

    public IndicatorProvider findByIdentifier(String identifier) {
        return stockIndicatorMap.get(identifier);
    }

}
