package io.philoyui.stockdetail.controller;

import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


/**
 * @author DELL
 */
@Controller
@RequestMapping("/api/stock_detail")
public class StockDetailController {

    @Autowired
    private StockDetailService stockDetailService;

    @RequestMapping("/editField")
    public ResponseEntity<String> update(String symbol,String field,String info,String value){
        switch (field){
            case "gpMargin":
                editGpMarginField(symbol,info,value);
                break;
            case "npMargin":
                editNpMarginField(symbol,info,value);
                break;
            case "roeAvg":
                editRoeAvgField(symbol,info,value);
                break;
            case "CAToAsset":
                editCAToAssetField(symbol,info,value);
                break;
            case "NRTurnDays":
                editNRTurnDaysField(symbol,info,value);
                break;
            case "AssetTurnRatio":
                editAssetTurnRatioField(symbol,info,value);
                break;
            case "INVTurnDays":
                editINVTurnDaysField(symbol,info,value);
                break;
            case "liabilityToAsset":
                editLiabilityToAssetField(symbol,info,value);
                break;
            case "quickRatio":
                editQuickRatioField(symbol,info,value);
                break;
            default:
        }

        return ResponseEntity.ok("success");
    }

    private void editQuickRatioField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setQuickRatioInfo(info);
            stockDetailEntity.setQuickRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setQuickRatioInfo(info);
            stockDetailEntity.setQuickRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editLiabilityToAssetField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setLiabilityToAssetInfo(info);
            stockDetailEntity.setLiabilityToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setLiabilityToAssetInfo(info);
            stockDetailEntity.setLiabilityToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editINVTurnDaysField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setInvTurnDaysInfo(info);
            stockDetailEntity.setInvTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setInvTurnDaysInfo(info);
            stockDetailEntity.setInvTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editAssetTurnRatioField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setAssetTurnRatioInfo(info);
            stockDetailEntity.setAssetTurnRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setAssetTurnRatioInfo(info);
            stockDetailEntity.setAssetTurnRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editNRTurnDaysField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setNrTurnDaysInfo(info);
            stockDetailEntity.setNrTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setNrTurnDaysInfo(info);
            stockDetailEntity.setNrTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editCAToAssetField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setCaToAssetInfo(info);
            stockDetailEntity.setCaToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setCaToAssetInfo(info);
            stockDetailEntity.setCaToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

    private void editRoeAvgField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setRoeAvgInfo(info);
            stockDetailEntity.setRoeAvgValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setRoeAvgInfo(info);
            stockDetailEntity.setRoeAvgValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }


    private void editNpMarginField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setNpMarginInfo(info);
            stockDetailEntity.setNpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setNpMarginInfo(info);
            stockDetailEntity.setNpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }


    private void editGpMarginField(String symbol, String info, String value) {
        StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
        if(stockDetailEntity==null){
            stockDetailEntity = new StockDetailEntity();
            stockDetailEntity.setSymbol(symbol);
            stockDetailEntity.setGpMarginInfo(info);
            stockDetailEntity.setGpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setGpMarginInfo(info);
            stockDetailEntity.setGpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

}
