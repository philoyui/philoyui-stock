package ${basePackage}.entity;

import java.util.Date;

import cn.com.gome.cloud.openplatform.open.OpenConvertible;
import ${basePackage}.vo.${EntityName};

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ${EntityName}Entity implements OpenConvertible<${EntityName}> {

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

    @Override
    public ${EntityName} converter() {
        ${EntityName} ${entityName} = new ${EntityName}();
    <#list fieldConfigs as fieldConfig>
        ${entityName}.set${fieldConfig.name?cap_first}(${fieldConfig.name});
    </#list>
        return ${entityName};
    }

    public static ${EntityName}Entity constructFrom(${EntityName} ${entityName}) {
        ${EntityName}Entity ${entityName}Entity = new ${EntityName}Entity();
    <#list fieldConfigs as fieldConfig>
        ${entityName}Entity.set${fieldConfig.name?cap_first}(${entityName}.get${fieldConfig.name?cap_first}());
    </#list>
        return ${entityName}Entity;
    }
}
