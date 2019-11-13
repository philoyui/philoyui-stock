package ${basePackage}.dubbo.impl;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.open.OpenConverterUtils;
import com.alibaba.dubbo.config.annotation.Service;
import ${basePackage}.${entityFolder}.${EntityName}Entity;
import ${basePackage}.service.${EntityName}Service;
import ${basePackage}.vo.${EntityName};
import ${basePackage}.dubbo.${EntityName}DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(group="${appName}")
public class ${EntityName}DubboServiceImpl implements ${EntityName}DubboService {

    @Autowired
    private ${EntityName}Service ${entityName}Service;

    @Override
    public PageObject<${EntityName}> paged(SearchFilter searchFilter) {
        return OpenConverterUtils.convertPageObject(${entityName}Service.paged(searchFilter));
    }

    @Override
    public ${EntityName} get(Long id) {
        return OpenConverterUtils.convertObject(${entityName}Service.get(id));
    }

    @Override
    public ${EntityName} get(SearchFilter searchFilter) {
        return OpenConverterUtils.convertObject(${entityName}Service.get(searchFilter));
    }

    @Override
    public void saveOrUpdate(${EntityName} ${entityName}) {
        ${entityName}Service.insert(${EntityName}Entity.constructFrom(${entityName}));
    }

    @Override
    public void delete(Long id) {
        ${entityName}Service.delete(id);
    }

}
