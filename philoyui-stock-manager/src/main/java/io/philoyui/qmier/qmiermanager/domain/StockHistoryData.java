package io.philoyui.qmier.qmiermanager.domain;

import io.philoyui.qmier.qmiermanager.service.StockData;

import java.io.Serializable;

public class StockHistoryData implements Serializable {

    private double[] lowArray;

    private double[] openArray;

    private double[] closeArray;

    private double[] highArray;

    private double[] volumeArray;

    private StockData[] stockData;

    public double[] getLowArray() {
        return lowArray;
    }

    public void setLowArray(double[] lowArray) {
        this.lowArray = lowArray;
    }

    public double[] getOpenArray() {
        return openArray;
    }

    public void setOpenArray(double[] openArray) {
        this.openArray = openArray;
    }

    public double[] getCloseArray() {
        return closeArray;
    }

    public void setCloseArray(double[] closeArray) {
        this.closeArray = closeArray;
    }

    public double[] getHighArray() {
        return highArray;
    }

    public void setHighArray(double[] highArray) {
        this.highArray = highArray;
    }

    public double[] getVolumeArray() {
        return volumeArray;
    }

    public void setVolumeArray(double[] volumeArray) {
        this.volumeArray = volumeArray;
    }

    public StockData[] getStockData() {
        return stockData;
    }

    public void setStockData(StockData[] stockData) {
        this.stockData = stockData;
    }
}
