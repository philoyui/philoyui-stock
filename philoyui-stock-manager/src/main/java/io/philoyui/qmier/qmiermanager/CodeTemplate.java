package io.philoyui.qmier.qmiermanager;

public enum CodeTemplate {

    Dao("dao"),Service("service"),ServiceImpl("service/impl"),Dubbo("dubbo"),Vo("vo"),DubboImpl("dubbo/impl"),Page("page"),Event("event"),Controller("controller"), EventHandler("eventhandler");

    private String folder;

    CodeTemplate(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
