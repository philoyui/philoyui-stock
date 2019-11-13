package io.philoyui.qmier.qmiermanager;

import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.entity.DayDataEntity;

public class Main {


    /**
     * Dao,Service,
     * @param args
     */
    public static void main(String[] args){

        PageCodeRequest request = new PageCodeRequest();
        request.setBasePackage("io.philoyui.qmier.qmiermanager");
        request.setCodeTemplates(CodeTemplate.Dao,CodeTemplate.Service,CodeTemplate.ServiceImpl);
        request.setEntityClasses(Lists.newArrayList(DayDataEntity.class));
        request.setBasePath("C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\java\\io\\philoyui\\qmier\\qmiermanager");
        new PageProjectInitializer().generateCode(request);

    }

}
