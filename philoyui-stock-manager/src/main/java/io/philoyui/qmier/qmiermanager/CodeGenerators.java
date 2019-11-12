package io.philoyui.qmier.qmiermanager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodeGenerators {

    private static Map<CodeTemplate,CodeGenerator> templateGenerators = new ConcurrentHashMap<>();

    static {
        templateGenerators.put(CodeTemplate.Dao,new DaoCodeGenerator());
        templateGenerators.put(CodeTemplate.Service,new ServiceCodeGenerator());
        templateGenerators.put(CodeTemplate.ServiceImpl,new ServiceImplCodeGenerator());
        templateGenerators.put(CodeTemplate.Controller,new ControllerCodeGenerator());
        templateGenerators.put(CodeTemplate.Dubbo,new DubboCodeGenerator());
        templateGenerators.put(CodeTemplate.DubboImpl,new DubboImplCodeGenerator());
        templateGenerators.put(CodeTemplate.Vo,new VoCodeGenerator());
        templateGenerators.put(CodeTemplate.Event,new EventCodeGenerator());
        templateGenerators.put(CodeTemplate.EventHandler,new EventHandlerCodeGenerator());
        templateGenerators.put(CodeTemplate.Page,new PageServiceCodeGenerator());
    }

    public static CodeGenerator select(CodeTemplate codeTemplate) {
        return templateGenerators.get(codeTemplate);
    }

}
