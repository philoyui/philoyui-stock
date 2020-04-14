package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.EachTagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.utils.BollResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class BollTagMarker extends EachTagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix) {
        double[] closeArray = processorContext.getCloseDataArray();
        double[] openDataArray = processorContext.getOpenDataArray();
        BollResult bollResult = TalibUtils.boll(closeArray, 20, 2, 2);

        double middleHigh = (bollResult.getUpperResult()[1] - bollResult.getMiddleResult()[1])/2 + bollResult.getMiddleResult()[1];
        double middleLow = bollResult.getMiddleResult()[1] - (bollResult.getMiddleResult()[1] - bollResult.getLowerResult()[1])/2;
        double co2 = (openDataArray[1] - closeArray[1])/2;

        if(closeArray[0]>closeArray[1] && closeArray[1] < closeArray[2] && bollResult.getMiddleResult()[0] > bollResult.getMiddleResult()[1] && closeArray[1] > middleLow && closeArray[1] < middleHigh && closeArray[0] > co2){
            this.tagStocks(stockEntity.getSymbol(),prefix + "BOLL回踩");
        }

    }

    @Override
    public void cleanTags() {
        this.deleteStocks("月BOLL回踩");
        this.deleteStocks("周BOLL回踩");
        this.deleteStocks("日BOLL回踩");
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
