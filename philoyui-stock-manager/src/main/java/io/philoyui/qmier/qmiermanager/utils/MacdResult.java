package io.philoyui.qmier.qmiermanager.utils;

import cn.com.gome.cloud.openplatform.common.SearchFilter;

import java.io.Serializable;

public class MacdResult implements Serializable {

    private double[] macdResult;

    private double[] signalResult;

    private double[] histResult;

    public MacdResult(double[] macdResult, double[] signalResult, double[] histResult) {
        this.macdResult = macdResult;
        this.signalResult = signalResult;
        this.histResult = histResult;
    }

    public double[] getMacdResult() {
        return macdResult;
    }

    public void setMacdResult(double[] macdResult) {
        this.macdResult = macdResult;
    }

    public double[] getSignalResult() {
        return signalResult;
    }

    public void setSignalResult(double[] signalResult) {
        this.signalResult = signalResult;
    }

    public double[] getHistResult() {
        return histResult;
    }

    public void setHistResult(double[] histResult) {
        this.histResult = histResult;
    }
}
