package io.philoyui.qmier.qmiermanager.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.service.StockData;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MacdParseResult {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    private List<MacdCross> macdCrossList = new ArrayList<>();

    public void markGoldenCross(StockData stockData, MacdResult macdResult, int index) {
        MacdCross macdCross = new MacdCross();
        macdCross.setIndex(index);
        macdCross.setType(1);
        macdCross.setMacdValue(macdResult.getMacdResult()[index]);
        macdCross.setCloseValue(stockData.getClose());
        macdCross.setDay(stockData.getDay());
        macdCross.setSymbol(stockData.getSymbol());
        macdCross.setHistValue(macdResult.getHistResult()[index]);
        macdCross.setSignalValue(macdResult.getSignalResult()[index]);
        macdCrossList.add(macdCross);
    }

    public void markDeathCross(StockData stockData, MacdResult macdResult, int index) {
        MacdCross macdCross = new MacdCross();
        macdCross.setIndex(index);
        macdCross.setType(2);
        macdCross.setMacdValue(macdResult.getMacdResult()[index]);
        macdCross.setCloseValue(stockData.getClose());
        macdCross.setDay(stockData.getDay());
        macdCrossList.add(macdCross);
    }

    /**
     * 是否是底背离金叉
     * @return
     */
    public boolean isBottomDivergenceCross() {

        if(macdCrossList.size() < 3){//至少3次交叉，金叉-死叉-金叉
            return false;
        }

        if(!macdCrossList.get(0).isGoldenCross()){//第一个必须是金叉
            return false;
        }

        if(macdCrossList.get(0).getIndex()>3){//必须是3天内金叉
            return false;
        }

        MacdCross lastGoldenCross = macdCrossList.get(0);
        MacdCross last2GoldenCross = macdCrossList.get(2);

        return lastGoldenCross.getMacdValue() > last2GoldenCross.getMacdValue() && lastGoldenCross.getCloseValue() < last2GoldenCross.getCloseValue();
    }

    /**
     * 是否是顶背离
     * @return
     */
    public boolean isTopDivergenceCross() {

        if(macdCrossList.size() < 3){//至少3次交叉，死叉-金叉-死叉
            return false;
        }

        if(!macdCrossList.get(0).isDeathCross()){//第一个必须是死叉
            return false;
        }

        if(macdCrossList.get(0).getIndex()>3){//必须是3天内死叉
            return false;
        }

        MacdCross lastDeathCross = macdCrossList.get(0);
        MacdCross last2DeathCross = macdCrossList.get(2);

        return lastDeathCross.getMacdValue() < last2DeathCross.getMacdValue() && lastDeathCross.getCloseValue() > last2DeathCross.getCloseValue();

    }

    public void printCrossData() {
        System.out.println(gson.toJson(macdCrossList));
    }

    public static class MacdCross{

        /**
         * 序号
         */
        private Integer index;

        /**
         * 1. 底背离金叉 2.顶背离死叉
         */
        private int type;

        private String symbol;

        /**
         * Macd值
         */
        private double macdValue;

        private double signalValue;

        private double histValue;

        /**
         * 收盘价
         */
        private double closeValue;

        /**
         * 股票数据时间
         */
        private Date day;


        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isGoldenCross() {
            return type == 1;
        }

        public double getMacdValue() {
            return macdValue;
        }

        public void setMacdValue(double macdValue) {
            this.macdValue = macdValue;
        }

        public double getCloseValue() {
            return closeValue;
        }

        public void setCloseValue(double closeValue) {
            this.closeValue = closeValue;
        }

        public boolean isDeathCross() {
            return type == 2;
        }

        public Date getDay() {
            return day;
        }

        public void setDay(Date day) {
            this.day = day;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getSignalValue() {
            return signalValue;
        }

        public void setSignalValue(double signalValue) {
            this.signalValue = signalValue;
        }

        public double getHistValue() {
            return histValue;
        }

        public void setHistValue(double histValue) {
            this.histValue = histValue;
        }
    }

}
