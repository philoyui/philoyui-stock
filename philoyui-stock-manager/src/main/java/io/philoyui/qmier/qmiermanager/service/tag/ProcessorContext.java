package io.philoyui.qmier.qmiermanager.service.tag;

import java.io.Serializable;

public class ProcessorContext implements Serializable {

    private double[] lowDataArray;

    private double[] highDataArray;

    private double[] closeDataArray;

    private double[] openDataArray;

    private double[] volumeDataArray;

    public double[] getLowDataArray() {
        return lowDataArray;
    }

    public void setLowDataArray(double[] lowDataArray) {
        this.lowDataArray = lowDataArray;
    }

    public double[] getHighDataArray() {
        return highDataArray;
    }

    public void setHighDataArray(double[] highDataArray) {
        this.highDataArray = highDataArray;
    }

    public double[] getCloseDataArray() {
        return closeDataArray;
    }

    public void setCloseDataArray(double[] closeDataArray) {
        this.closeDataArray = closeDataArray;
    }

    public double[] getOpenDataArray() {
        return openDataArray;
    }

    public void setOpenDataArray(double[] openDataArray) {
        this.openDataArray = openDataArray;
    }

    public double[] getVolumeDataArray() {
        return volumeDataArray;
    }

    public void setVolumeDataArray(double[] volumeDataArray) {
        this.volumeDataArray = volumeDataArray;
    }
}
