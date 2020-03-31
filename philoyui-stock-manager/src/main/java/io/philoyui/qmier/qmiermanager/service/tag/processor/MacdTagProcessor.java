package io.philoyui.qmier.qmiermanager.service.tag.processor;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import io.philoyui.qmier.qmiermanager.utils.MacdResult;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.stereotype.Component;

@Component
public class MacdTagProcessor extends TagProcessor {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity) {
        double[] closeArray = processorContext.getCloseDataArray();
        MacdResult macdResult = TalibUtils.macd(closeArray, 12, 26, 9);
        if ( macdResult.getMacdResult()[0] > macdResult.getSignalResult()[0] && macdResult.getMacdResult()[1] < macdResult.getSignalResult()[1] && macdResult.getMacdResult()[0] > -0.25 && macdResult.getMacdResult()[0] < 0.25) {
            this.tagStocks(stockEntity.getSymbol(),"MACD零轴金叉");
        }
    }

}
