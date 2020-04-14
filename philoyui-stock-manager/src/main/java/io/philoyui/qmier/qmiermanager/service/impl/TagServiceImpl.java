package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TagDao;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagServiceImpl extends GenericServiceImpl<TagEntity,Long> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    protected GenericDao<TagEntity, Long> getDao() {
        return tagDao;
    }

}
