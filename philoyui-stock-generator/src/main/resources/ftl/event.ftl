package ${basePackage}.event;

import cn.com.gome.cloud.openplatform.common.interfaze.DomainEvent;
import cn.com.pop.domain.event.EventEntity;

@EventEntity(domainName = "${EntityChineseName}", topicName = "${appName}_${entityName}", groupAlias = "${entityName}")
public interface ${EntityName}Event extends DomainEvent {

}