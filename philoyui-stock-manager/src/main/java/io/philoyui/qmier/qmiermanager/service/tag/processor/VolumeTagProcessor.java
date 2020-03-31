package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import org.springframework.stereotype.Component;

@Component
public class VolumeTagProcessor extends TagProcessor {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity) {
        double[] volumeArray = processorContext.getVolumeDataArray();
        double[] closeArray = processorContext.getCloseDataArray();
        double[] openArray = processorContext.getOpenDataArray();
        if ( volumeArray[0] > volumeArray[1] * 3 && ((closeArray[0] - openArray[0])/openArray[0]<0.8 || (closeArray[0] - openArray[0])/openArray[0]> -0.8)){
            this.tagStocks(stockEntity.getSymbol(),"三倍量");
        }
    }

}
