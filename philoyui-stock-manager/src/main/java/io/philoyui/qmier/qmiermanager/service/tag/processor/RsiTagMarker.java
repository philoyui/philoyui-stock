package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class RsiTagMarker extends EachTagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] cciResult = TalibUtils.rsi(closeArray,14);
        if ( cciResult[0] > 70 ) {
            this.tagStocks(stockEntity.getSymbol(),prefix + "RSI超买");
        }
        if ( cciResult[0] < 70 ) {
            this.tagStocks(stockEntity.getSymbol(),prefix + "RSI超卖");
        }
    }

    @Override
    public void cleanTags(String prefix) {
        this.deleteStocks(prefix + "RSI超买");
        this.deleteStocks(prefix + "RSI超卖");
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
