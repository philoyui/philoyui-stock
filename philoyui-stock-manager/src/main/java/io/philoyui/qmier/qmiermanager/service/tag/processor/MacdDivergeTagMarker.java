package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class MacdDivergeTagMarker extends EachTagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        MacdResult macdResult = TalibUtils.macd(closeArray, 12, 26, 9);

        int crossIndex = -1;
        double crossMacd = 0;
        double crossClose = 0;
        int divergeIndex = -1;

        for (int i = 0; i < macdResult.getMacdResult().length - 1; i++) {

            double currentMacd = macdResult.getMacdResult()[i];
            double currentSignal = macdResult.getSignalResult()[i];
            double currentClose = closeArray[i];

            double yesterdayMacd = macdResult.getMacdResult()[i+1];
            double yesterdaySignal = macdResult.getSignalResult()[i+1];

            if (currentMacd > currentSignal && yesterdayMacd < yesterdaySignal) {
                if(crossMacd==0){
                    crossMacd = currentMacd;
                    crossClose = currentClose;
                    crossIndex = i;
                }else if(crossMacd!=0){
                    if(currentMacd<crossMacd&&currentClose>crossClose){
                        divergeIndex = i;
                    }
                }
            }
        }

        if( crossIndex >=0 && crossIndex<5 && divergeIndex>0){
            this.tagStocks(stockEntity.getSymbol(),prefix + "MACD底背离");
        }

        for (int i = 0; i < macdResult.getMacdResult().length - 1; i++) {

            double currentMacd = macdResult.getMacdResult()[i];
            double currentSignal = macdResult.getSignalResult()[i];
            double currentClose = closeArray[i];

            double yesterdayMacd = macdResult.getMacdResult()[i+1];
            double yesterdaySignal = macdResult.getSignalResult()[i+1];

            if (currentMacd < currentSignal && yesterdayMacd > yesterdaySignal) {
                if(crossMacd==0){
                    crossMacd = currentMacd;
                    crossClose = currentClose;
                    crossIndex = i;
                }else if(crossMacd!=0){
                    if(currentMacd>crossMacd&&currentClose<crossClose){
                        divergeIndex = i;
                        break;
                    }
                }
            }
        }

        if( crossIndex >=0 && crossIndex<5 && divergeIndex>=0){
            this.tagStocks(stockEntity.getSymbol(),prefix + "MACD顶背离");
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
