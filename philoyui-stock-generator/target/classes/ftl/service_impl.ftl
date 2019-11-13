package ${basePackage}.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import ${basePackage}.dao.${EntityName}Dao;
import ${basePackage}.entity.${EntityName}Entity;
import ${basePackage}.service.${EntityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ${EntityName}ServiceImpl extends GenericServiceImpl<${EntityName}Entity,Long> implements ${EntityName}Service {

    @Autowired
    private ${EntityName}Dao ${entityName}Dao;

    @Override
    protected GenericDao<${EntityName}Entity, Long> getDao() {
        return ${entityName}Dao;
    }

}