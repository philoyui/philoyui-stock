package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.philoyui.qmier.qmiermanager.dao.TagDao;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

    }

    @Override
    public TagEntity findByTagName(String tagName) {
        try {
            return cache.get("tagName_" + tagName, () -> tagDao.findByTagName(tagName));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TagEntity> findAdd() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("strategyType", StrategyType.ADD));
        return this.list(searchFilter);
    }

    @Override
    public List<TagEntity> findReduce() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("strategyType", StrategyType.REDUCE));
        return this.list(searchFilter);
    }
}
