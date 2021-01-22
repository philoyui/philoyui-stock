package io.philoyui.stockdetail.controller;

import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author DELL
 */
@Controller
@RequestMapping("/api/stock_detail")
public class StockDetailController {

    private static Map<String,String> nameValues = new ConcurrentHashMap<>();

    static {
        nameValues.put("1","大白马");
        nameValues.put("2","三年业绩上升");
        nameValues.put("3","生意很难做");
        nameValues.put("4","生意很艰辛");
        nameValues.put("5","毛利还可以");
        nameValues.put("6","毛利还不错");
        nameValues.put("7","毛利很高");
        nameValues.put("8","毛利超高");
        nameValues.put("9","毛利堪比卖白粉");
        nameValues.put("10","生意赚不到钱");
        nameValues.put("11","税后利润一般");
        nameValues.put("12","税后利润不错");
        nameValues.put("13","税后利润优异");
        nameValues.put("14","税后也非常赚钱");
        nameValues.put("15","股东在亏损");
        nameValues.put("16","收益率不高");
        nameValues.put("17","还可以的收益");
        nameValues.put("18","不错的回报率");
        nameValues.put("19","打败巴菲特回报率");
        nameValues.put("20","很牛逼的回报率");

        nameValues.put("21","气很短");
        nameValues.put("22","气一般");
        nameValues.put("23","现金充足");
        nameValues.put("24","气很长");

        nameValues.put("25","天天收现金");
        nameValues.put("26","收款很快");
        nameValues.put("27","收款速度一般");
        nameValues.put("28","收款速度很慢");
        nameValues.put("29","收款速度太慢了吧");

        nameValues.put("30","重资产周转慢,关注现金");
        nameValues.put("31","经营稳健,还不错");
        nameValues.put("32","经营效率优异");
        nameValues.put("33","团队运营超一流");

        nameValues.put("34","基本无存货,产品火爆");
        nameValues.put("35","货卖的很快,口碑好");
        nameValues.put("36","货卖的不错");
        nameValues.put("37","货卖的一般");
        nameValues.put("38","卖货慢,低频或原物料");
        nameValues.put("39","产品不好卖,酒地产除外");

        nameValues.put("40","基本没什么杆杠");
        nameValues.put("41","不用举债就能存活很好");
        nameValues.put("42","杆杠稳健");
        nameValues.put("43","杆杠偏高");
        nameValues.put("44","杆杠过大,风险偏高");

        nameValues.put("45","债务纠纷,缺乏清偿能力");
        nameValues.put("46","债务纠纷,清偿问题不大");
        nameValues.put("47","债务纠纷,也能立即清偿");

    }

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
            stockDetailEntity.setQuickRatioInfo(nameValues.get(info));
            stockDetailEntity.setQuickRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setQuickRatioInfo(nameValues.get(info));
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
            stockDetailEntity.setLiabilityToAssetInfo(nameValues.get(info));
            stockDetailEntity.setLiabilityToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setLiabilityToAssetInfo(nameValues.get(info));
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
            stockDetailEntity.setInvTurnDaysInfo(nameValues.get(info));
            stockDetailEntity.setInvTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setInvTurnDaysInfo(nameValues.get(info));
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
            stockDetailEntity.setAssetTurnRatioInfo(nameValues.get(info));
            stockDetailEntity.setAssetTurnRatioValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setAssetTurnRatioInfo(nameValues.get(info));
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
            stockDetailEntity.setNrTurnDaysInfo(nameValues.get(info));
            stockDetailEntity.setNrTurnDaysValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setNrTurnDaysInfo(nameValues.get(info));
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
            stockDetailEntity.setCaToAssetInfo(nameValues.get(info));
            stockDetailEntity.setCaToAssetValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setCaToAssetInfo(nameValues.get(info));
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
            stockDetailEntity.setRoeAvgInfo(nameValues.get(info));
            stockDetailEntity.setRoeAvgValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setRoeAvgInfo(nameValues.get(info));
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
            stockDetailEntity.setNpMarginInfo(nameValues.get(info));
            stockDetailEntity.setNpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setNpMarginInfo(nameValues.get(info));
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
            stockDetailEntity.setGpMarginInfo(nameValues.get(info));
            stockDetailEntity.setGpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailService.update(stockDetailEntity);
        }else {
            stockDetailEntity.setGpMarginInfo(nameValues.get(info));
            stockDetailEntity.setGpMarginValue(NumberUtils.toDouble(value,0.0));
            stockDetailEntity.setModifyTime(new Date());
            stockDetailService.update(stockDetailEntity);
        }
    }

}
