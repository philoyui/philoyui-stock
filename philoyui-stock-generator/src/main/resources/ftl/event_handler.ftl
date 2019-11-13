package ${basePackage}.eventhandler;

import cn.com.pop.domain.event.EventMethod;
import cn.com.pop.domain.event.EventSubscriber;
import cn.com.pop.domain.event.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

@EventSubscriber(domainChineseName = "${EntityChineseName}", group = "${entityName}")
public class ${EntityName}EventHandler {

    @Autowired
    private DomainEventPublisher publisher;

    @EventMethod
    public void handle(){


    }

}