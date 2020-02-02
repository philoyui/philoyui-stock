package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.CodeTemplate;
import cn.com.gome.cloud.openplatform.generator.PageProjectInitializer;
import cn.com.gome.cloud.openplatform.generator.request.PageCodeRequest;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /**
     * Dao,Service,
     * @param args
     */
    public static void main(String[] args){
//        PageCodeRequest request = new PageCodeRequest();
//        request.setAppName("gmos");
//        request.setBasePackage("io.philoyui.qmier.qmiermanager");
//        request.setCodeTemplates(CodeTemplate.Page,CodeTemplate.Dao,CodeTemplate.Entity,CodeTemplate.Service,CodeTemplate.ServiceImpl,CodeTemplate.Controller);
////        request.setEntityClasses(Lists.newArrayList(ChooseDefinition.class));
//        request.setBasePath("C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\java\\io\\philoyui\\qmier\\qmiermanager");
//        new PageProjectInitializer().generateCode(request);


        int a = 18;
        int b = 23;
        try {
            String[] args1 = new String[] { "python", "C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\resources\\python\\a.py", String.valueOf(a), String.valueOf(b) };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
