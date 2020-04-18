package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class SarTagProcess extends EachTagMarker {
    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] highArray = processorContext.getHighDataArray();
        double[] lowArray = processorContext.getLowDataArray();
        double[] cciResult = TalibUtils.sar(highArray,lowArray,0.02,0.2);
        if ( closeArray[0] > cciResult[0] && closeArray[1] < cciResult[1] ) {
            this.tagStocks(stockEntity.getSymbol(),prefix + "SAR多头开始");
        }
        if ( closeArray[0] < cciResult[0] && closeArray[1] > cciResult[1] ) {
            this.tagStocks(stockEntity.getSymbol(),prefix + "SAR空头开始");
        }
    }

    @Override
    public void cleanTags(String prefix) {
        this.deleteStocks(prefix + "SAR多头开始");
        this.deleteStocks(prefix + "SAR空头开始");
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
