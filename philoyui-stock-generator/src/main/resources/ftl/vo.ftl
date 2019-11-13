package ${basePackage}.vo;

import java.io.Serializable;
import java.util.Date;

public class ${EntityName} implements Serializable {

    <#list fieldConfigs as fieldConfig>
    /**
     * ${fieldConfig.desc}
     */
    private ${fieldConfig.type} ${fieldConfig.name};

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
