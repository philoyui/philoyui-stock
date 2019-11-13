package ${basePackage}.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ${EntityName}Entity implements Serializable {

    <#list fieldConfigs as fieldConfig>
    /**
     * ${fieldConfig.desc}
     */
    <#if fieldConfig.name='id'>
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private ${fieldConfig.type} ${fieldConfig.name};
    <#else>
    private ${fieldConfig.type} ${fieldConfig.name};
    </#if>

    </#list>

    <#list fieldConfigs as fieldConfig>
    public ${fieldConfig.type} get${fieldConfig.name?cap_first}() {
        return ${fieldConfig.name};
    }

    public void set${fieldConfig.name?cap_first}(${fieldConfig.type} ${fieldConfig.name}) {
        this.${fieldConfig.name} = ${fieldConfig.name};
    }

    </#list>

}
