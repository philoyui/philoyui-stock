package ${basePackage}.dubbo;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import ${basePackage}.vo.${EntityName};

public interface ${EntityName}DubboService {

    /**
     * 列表查询${EntityChineseName}
     * @param searchFilter
     * @return
     */
    PageObject<${EntityName}> paged(SearchFilter searchFilter);

    /**
     * 获取单个${EntityChineseName}
     * @param id
     * @return
     */
    ${EntityName} get(Long id);

    /**
     * 条件查询${EntityChineseName}
     * @param searchFilter
     * @return
     */
    ${EntityName} get(SearchFilter searchFilter);

    /**
     * 保存或者编辑${EntityChineseName}
     * @param ${entityName}
     */
    void saveOrUpdate(${EntityName} ${entityName});

    /**
     * 删除${EntityChineseName}
     * @param id
     */
    void delete(Long id);

}
