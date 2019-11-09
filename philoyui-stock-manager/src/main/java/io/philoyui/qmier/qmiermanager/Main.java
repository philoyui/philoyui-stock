package io.philoyui.qmier.qmiermanager;

import com.google.common.collect.Lists;

public class Main {


    /**
     * Dao,Service,
     * @param args
     */
    public static void main(String[] args){

        PageCodeRequest request = new PageCodeRequest();
        request.setBasePath("F:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\java\\io\\philoyui\\qmier\\qmiermanager");
        request.setCodeTemplates(CodeTemplate.Dao,CodeTemplate.Service,CodeTemplate.Page);
        request.setEntityClasses(Lists.newArrayList());
        new PageCodeGenerator().generateCode(request);

    }

}
