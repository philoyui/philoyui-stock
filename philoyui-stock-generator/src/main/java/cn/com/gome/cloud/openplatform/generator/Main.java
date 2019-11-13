package cn.com.gome.cloud.openplatform.generator;

import cn.com.gome.cloud.openplatform.generator.request.PageCodeRequest;

public class Main {


    /**
     * Dao,Service,
     * @param args
     */
    public static void main(String[] args){
        PageCodeRequest request = new PageCodeRequest();
        request.setAppName("gmos");
        request.setBasePackage("io.philoyui.qmier.qmiermanager");
        request.setCodeTemplates(CodeTemplate.Page);
//        request.setEntityClasses(Lists.newArrayList());
        request.setBasePath("E:\\opencloud\\philoyui-stock\\philoyui-stock-manager\\src\\main\\java\\io\\philoyui\\qmier\\qmiermanager");
        new PageProjectInitializer().generateCode(request);

    }

}
