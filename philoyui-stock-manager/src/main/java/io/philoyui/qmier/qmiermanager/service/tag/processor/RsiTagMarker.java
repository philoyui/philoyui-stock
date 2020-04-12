package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class RsiTagMarker extends EachTagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] cciResult = TalibUtils.rsi(closeArray,14);
        if ( cciResult[0] > 70 ) {
            this.tagStocks(stockEntity.getSymbol(),"RSI超买");
        }
        if ( cciResult[0] < 70 ) {
            this.tagStocks(stockEntity.getSymbol(),"RSI超卖");
        }
    }

}
