package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class MacdTagMarker extends EachTagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        MacdResult macdResult = TalibUtils.macd(closeArray, 12, 26, 9);
        if ( macdResult.getMacdResult()[0] > macdResult.getSignalResult()[0] && macdResult.getMacdResult()[1] < macdResult.getSignalResult()[1] && macdResult.getMacdResult()[0] > -0.25 && macdResult.getMacdResult()[0] < 0.25) {
            this.tagStocks(stockEntity.getSymbol(),prefix + "MACD零轴金叉");
        }
    }

    @Override
    public void cleanTags() {
        this.deleteStocks("月MACD零轴金叉");
        this.deleteStocks("周MACD零轴金叉");
        this.deleteStocks("日MACD零轴金叉");
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
