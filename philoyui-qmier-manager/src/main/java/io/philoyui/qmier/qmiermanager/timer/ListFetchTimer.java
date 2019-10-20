package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.entity.SiteCategoryEntity;
import io.philoyui.qmier.qmiermanager.service.SiteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListFetchTimer {

    @Autowired
    private SiteCategoryService siteCategoryService;

    public void process(){

        List<SiteCategoryEntity> siteCategoryEntities = siteCategoryService.findAllEnable();

        for (SiteCategoryEntity siteCategoryEntity : siteCategoryEntities) {



        }
    }

}
