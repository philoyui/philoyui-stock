package io.philoyui.qmier.qmiermanager.service.tag;

public abstract class EachTagMarker extends TagMarker {

    @Override
    public void processGlobal() {

    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    protected boolean checkCanProcess(ProcessorContext processorContext) {
        return processorContext.getCloseDataArray().length > 10;
    }

}
