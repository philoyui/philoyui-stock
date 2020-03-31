package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class WrTagProcessor extends TagProcessor {
    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity) {
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
}
