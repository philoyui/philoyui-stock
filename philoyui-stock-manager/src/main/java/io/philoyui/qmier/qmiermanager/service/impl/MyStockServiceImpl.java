package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.dao.StockDao;
import io.philoyui.qmier.qmiermanager.dao.TagDao;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MyStockServiceImpl extends GenericServiceImpl<MyStockEntity,Long> implements MyStockService {

    @Autowired
    private MyStockDao myStockDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MyStockService myStockService;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

    /**
     * 遍历所有的股票
     * 查看近3天的打标
     *
     */
    public void obtainEveryDay() {
        myStockService.deleteAll();
        for (StockEntity stockEntity : stockDao.findAll()) {
            if(stockEntity.getName().contains("ST")) {
                continue;
            }
            Integer score = 0;
            SearchFilter searchFilter = SearchFilter.getDefault();
            searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
            searchFilter.add(Restrictions.gt("lastIndex",-4));
            List<TagStockEntity> tagStockEntities = tagStockService.findBySymbol(stockEntity.getSymbol());
            ArrayList<String> reasons = Lists.newArrayList();
            for (TagStockEntity tagStockEntity : tagStockEntities) {
                TagEntity tagEntity = tagService.findByTagName(tagStockEntity.getTagName());
                if(tagEntity!=null){
                    if(tagStockEntity.getLastIndex()==-1){
                        score += tagEntity.getLast1Score();
                        reasons.add("<div style=\"color:" + buildColor(tagEntity.getLast1Score()) + "\">" + tagEntity.getTagName() + "(" + tagStockEntity.getDayString() + ") " +  tagEntity.getLast1Score() +"</div>");
                    }else if(tagStockEntity.getLastIndex()==-2){
                        score += tagEntity.getLast2Score();
                        reasons.add("<div style=\"color:" + buildColor(tagEntity.getLast2Score()) + "\">" + tagEntity.getTagName() + "(" + tagStockEntity.getDayString() + ") " + tagEntity.getLast2Score() + "</div>");
                    }else if(tagStockEntity.getLastIndex()==-3){
                        score += tagEntity.getLast3Score();
                        reasons.add("<div style=\"color:" + buildColor(tagEntity.getLast3Score()) + "\">" + tagEntity.getTagName() + "(" + tagStockEntity.getDayString() + ") " +tagEntity.getLast3Score() + "</div>");
                    }
                }
            }

            MyStockEntity myStockEntity = new MyStockEntity();
            myStockEntity.setSymbol(stockEntity.getSymbol());
            myStockEntity.setCreatedTime(new Date());
            myStockEntity.setDateString(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
            myStockEntity.setScore(score);
            myStockEntity.setReason(StringUtils.join(reasons,""));
            myStockEntity.setStockName(stockEntity.getName());
            myStockService.insert(myStockEntity);

        }
    }

    private String buildColor(Integer last1Score) {
        if(last1Score>0){
            return "red";
        }
        return "green";
    }


    @Transactional
    @Override
    public void deleteAll() {
        myStockDao.deleteAll();
    }


}
