package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.domain.MacdParseResult;
import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockData;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class MacdDivergeTagMarker extends EachTagMarker {

    /**
     * 1. MACD金叉或死叉
     * @param stockHistoryData
     * @param stockEntity
     * @param prefix
     */
    @Override
    public void processEachStock(StockHistoryData stockHistoryData, StockEntity stockEntity, String prefix) {

        double[] closeArray = stockHistoryData.getCloseArray();
        StockData[] stockDataArray = stockHistoryData.getStockData();
        MacdResult macdResult = TalibUtils.macd(closeArray, 12, 26, 9);

        MacdParseResult macdParseResult = new MacdParseResult();

        for (int index = 0; index < macdResult.getMacdResult().length - 1; index++) {

            double currentMacd = macdResult.getMacdResult()[index];
            double currentSignal = macdResult.getSignalResult()[index];
            StockData stockData = stockDataArray[index];

            double yesterdayMacd = macdResult.getMacdResult()[index+1];
            double yesterdaySignal = macdResult.getSignalResult()[index+1];

            if (currentMacd > currentSignal && yesterdayMacd < yesterdaySignal) {
                macdParseResult.markGoldenCross(stockData,macdResult,index);
            }

            if (currentMacd < currentSignal && yesterdayMacd > yesterdaySignal) {
                macdParseResult.markDeathCross(stockData,macdResult,index);
            }

        }

        if(macdParseResult.isBottomDivergenceCross()){
            this.tagStocks(stockEntity.getSymbol(),prefix + "底背离金叉");
            macdParseResult.printCrossData();
        }

        if(macdParseResult.isTopDivergenceCross()){
            this.tagStocks(stockEntity.getSymbol(),prefix + "顶背离金叉");
            macdParseResult.printCrossData();
        }

    }

    @Override
    public void cleanTags(String prefix) {
        this.deleteStocks(prefix + "MACD底背离");
    }

    @Override
    public boolean supportDate() {
        return true;
    }

    @Override
    public boolean supportWeek() {
        return true;
    }

    @Override
    public boolean supportMonth() {
        return true;
    }
}
