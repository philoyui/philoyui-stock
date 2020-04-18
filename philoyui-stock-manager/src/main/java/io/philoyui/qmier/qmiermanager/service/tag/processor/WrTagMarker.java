package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class WrTagMarker extends EachTagMarker {
    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] highData = processorContext.getHighDataArray();
        double[] lowData = processorContext.getLowDataArray();
        double[] cciResult = TalibUtils.wr(highData,lowData,closeArray,14);
        if ( cciResult[0] > 85 ) {
            this.tagStocks(stockEntity.getSymbol(),"WR超卖");
        }
        if ( cciResult[0] < 15 ) {
            this.tagStocks(stockEntity.getSymbol(),"WR超买");
        }
    }

    @Override
    public void cleanTags(String prefix) {
        this.deleteStocks(prefix + "WR超买");
        this.deleteStocks(prefix + "WR超卖");
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
