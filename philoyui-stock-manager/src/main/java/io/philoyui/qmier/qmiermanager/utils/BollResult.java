package io.philoyui.qmier.qmiermanager.utils;

import java.io.Serializable;

public class BollResult implements Serializable {

    private double[] upperResult;

    private double[] middleResult;

    private double[] lowerResult;

    public BollResult(double[] upperResult, double[] middleResult, double[] lowerResult) {
        this.upperResult = upperResult;
        this.middleResult = middleResult;
        this.lowerResult = lowerResult;
    }

    public double[] getUpperResult() {
        return upperResult;
    }

    public void setUpperResult(double[] upperResult) {
        this.upperResult = upperResult;
    }

    public double[] getMiddleResult() {
        return middleResult;
    }

    public void setMiddleResult(double[] middleResult) {
        this.middleResult = middleResult;
    }

    public double[] getLowerResult() {
        return lowerResult;
    }

    public void setLowerResult(double[] lowerResult) {
        this.lowerResult = lowerResult;
    }
}
