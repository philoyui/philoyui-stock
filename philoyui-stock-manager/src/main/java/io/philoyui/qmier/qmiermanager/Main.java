package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.CodeTemplate;
import cn.com.gome.cloud.openplatform.generator.PageProjectInitializer;
import cn.com.gome.cloud.openplatform.generator.request.PageCodeRequest;
import com.google.common.collect.Lists;

public class Main {

    /**
     * Dao,Service,
     * @param args
     */
    public static void main(String[] args){
        PageCodeRequest request = new PageCodeRequest();
        request.setAppName("gmos");
        request.setBasePackage("io.philoyui.qmier.qmiermanager");
        request.setCodeTemplates(CodeTemplate.Page,CodeTemplate.Dao,CodeTemplate.Entity,CodeTemplate.Service,CodeTemplate.ServiceImpl,CodeTemplate.Controller);
        request.setEntityClasses(Lists.newArrayList(TimerTask.class));
        request.setBasePath("C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\java\\io\\philoyui\\qmier\\qmiermanager");
        new PageProjectInitializer().generateCode(request);

    }

}
