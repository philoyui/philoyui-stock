package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.philoyui.stock.service.TagService;
import io.philoyui.tagstock.dao.TagDao;
import io.philoyui.tagstock.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class TagServiceImpl extends GenericServiceImpl<TagEntity,Long> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    protected GenericDao<TagEntity, Long> getDao() {
        return tagDao;
    }

    private Cache<String, TagEntity> cache;

    @PostConstruct
    public void start(){
        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(300)
                .build();
    }

    @Override
    public TagEntity findByTagName(String tagName) {
        return cache.get("tagName_" + tagName, k -> tagDao.findByTagName(tagName));
    }

}
