package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class CciTagMarker extends EachTagMarker {
    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] highData = processorContext.getHighDataArray();
        double[] lowData = processorContext.getLowDataArray();
        double[] cciResult = TalibUtils.cci(highData,lowData,closeArray,14);
        if ( cciResult[0] > 100 ) {
            this.tagStocks(stockEntity.getSymbol(),"CCI强势股");
        }
    }

    @Override
    public void cleanTags() {
        this.deleteStocks("月CCI强势股");
        this.deleteStocks("周CCI强势股");
        this.deleteStocks("日CCI强势股");
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
