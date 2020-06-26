package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.TradingViewDao;
import io.philoyui.qmier.qmiermanager.entity.TradingViewEntity;
import io.philoyui.qmier.qmiermanager.service.TradingViewService;
import io.philoyui.qmier.qmiermanager.to.TradingViewFilter;
import io.philoyui.qmier.qmiermanager.to.TradingViewResult;
import io.philoyui.qmier.qmiermanager.to.TradingViewVo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TradingViewServiceImpl extends GenericServiceImpl<TradingViewEntity,Long> implements TradingViewService {

    @Autowired
    private TradingViewDao tradingViewDao;

    @Override
    protected GenericDao<TradingViewEntity, Long> getDao() {
        return tradingViewDao;
    }

    @Override
    public void batchInsert(List<TradingViewEntity> tradingViewEntities) {
        tradingViewDao.saveAll(tradingViewEntities);
    }

    @Override
    public void fetchCurrent() {

        this.deleteAll();

        int index = 0;

        while (index<100000) {

            TradingViewFilter tradingViewFilter = new TradingViewFilter(index, index+150);
            tradingViewFilter.addFilter("Recommend.All", "nempty");
            tradingViewFilter.addFilter("type", "in_range", new String[]{"stock", "dr", "fund"});
            tradingViewFilter.addFilter("subtype", "in_range", new String[]{"common", "", "etf", "unit", "mutual", "money", "reit", "trust"});
            tradingViewFilter.addColumns(
                    "name",
                    "close",//收盘价
                    "change",//涨幅%
                    "change_abs",//涨幅
                    "Recommend.All",//推荐指数
                    "volume",//成交量
                    "market_cap_basic",//总市值
                    "price_earnings_ttm",//市盈率
                    "earnings_per_share_basic_ttm",
                    "number_of_employees",//雇员
                    "sector",//板块
                    "industry",//行业
                    "description",//中文名
                    "name",//symbol
                    "quick_ratio",//速动比率
                    "number_of_shareholders",//股东数
                    "Candle.3BlackCrows",//三只乌鸦
                    "Candle.HangingMan",//上吊线
                    "Candle.InvertedHammer",//倒锤子线
                    "Candle.Marubozu.White",//光头阳线
                    "Candle.Marubozu.Black",//光头阴线
                    "Candle.Doji",//十字星
                    "Candle.MorningStar",//启明星
                    "Candle.Doji.Gravestone",//墓碑线
                    "Candle.ShootingStar",//流星线
                    "Candle.3WhiteSoldiers",//白三兵
                    "Candle.SpinningTop.White",//白色旋转陀螺
                    "Candle.TriStar.Bullish",//看涨三星
                    "Candle.Kicking.Bullish",//看涨反冲形态
                    "Candle.Engulfing.Bullish",//看涨吞没
                    "Candle.Harami.Bullish",//看涨孕线
                    "Candle.AbandonedBaby.Bullish",//看涨弃婴
                    "Candle.TriStar.Bearish",//看跌三星
                    "Candle.Kicking.Bearish",//看跌反冲形态
                    "Candle.Engulfing.Bearish",//看跌吞没
                    "Candle.Harami.Bearish",//看跌孕线
                    "Candle.AbandonedBaby.Bearish",//看跌弃婴
                    "Candle.Doji.Dragonfly",//蜻蜓线
                    "Candle.Hammer",//锤子线
                    "Candle.LongShadow.Upper",//长上影线
                    "Candle.LongShadow.Lower",//长下影线
                    "Candle.EveningStar",//黄昏星
                    "Candle.SpinningTop.Black"//黑丝旋转陀螺
            );
            TradingViewVo tradingViewVo = tradingViewFilter.build();
            String postUrl = "https://scanner.tradingview.com/china/scan";
            try {
                String response = Jsoup.connect(postUrl).timeout(60000).ignoreContentType(true)
                        .header("access-control-allow-credentials", "true")
                        .header("access-control-allow-headers", "X-UserId,X-UserExchanges,X-CSRFToken")
                        .header("access-control-allow-methods", "GET, POST, OPTIONS")
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .header("content-encoding", "gzip")
                        .header("access-control-allow-origin", "https://cn.tradingview.com")
                        .method(Connection.Method.POST).ignoreHttpErrors(true)
                        .requestBody(new GsonBuilder().create().toJson(tradingViewVo))
                        .execute()
                        .body();

                TradingViewResult tradingViewResult = new GsonBuilder().create().fromJson(response, TradingViewResult.class);

                if (tradingViewResult.getData() == null || tradingViewResult.getData().size() == 0) {
                    break;
                }

                List<TradingViewEntity> tradingViewEntities = new ArrayList<>();

                for (TradingViewResult.DataBean dataBean : tradingViewResult.getData()) {

                    List<String> dList = dataBean.getD();

                    String symbol = buildSymbol(dList.get(0));

                    TradingViewEntity tradingViewEntity = tradingViewDao.findBySymbol(symbol);

                    if(tradingViewEntity==null){
                        tradingViewEntity = new TradingViewEntity();
                        tradingViewEntity.setSymbol(buildSymbol(dList.get(0)));
                        tradingViewEntity.setStockName(dList.get(12));
                        tradingViewEntity.setClose(Double.parseDouble(dList.get(1)));
                        tradingViewEntity.setTodayChange(Double.parseDouble(dList.get(2)));
                        tradingViewEntity.setChangeAbs(Double.parseDouble(dList.get(3)));
                        tradingViewEntity.setRecommendScore(Double.parseDouble(dList.get(4)));
                        tradingViewEntity.setVolume(Long.parseLong(dList.get(5)));
                        tradingViewEntity.setMarketCap(dList.get(6)==null?null:Double.parseDouble(dList.get(6)));
                        tradingViewEntity.setPriceEarningsTtm(dList.get(7) == null ? null : Double.parseDouble(dList.get(7)));
                        tradingViewEntity.setNumberOfEmployees(dList.get(9) == null ? 0 : Long.parseLong(dList.get(9)));
                        tradingViewEntity.setSector(dList.get(10));
                        tradingViewEntity.setIndustry(dList.get(11));
                        tradingViewEntity.setQuickRatio(dList.get(14) == null ? 0 : Double.parseDouble(dList.get(14)));
                        tradingViewEntity.setNumberOfShareholders(dList.get(15) == null ? 0 : Double.parseDouble(dList.get(15)));
                        tradingViewEntity.setBlackCrows3(!"0".equalsIgnoreCase(dList.get(16)));
                        tradingViewEntity.setHangingMan(!"0".equalsIgnoreCase(dList.get(17)));
                        tradingViewEntity.setInvertedHammer(!"0".equalsIgnoreCase(dList.get(18)));
                        tradingViewEntity.setMarubozuWhite(!"0".equalsIgnoreCase(dList.get(19)));
                        tradingViewEntity.setMarubozuBlack(!"0".equalsIgnoreCase(dList.get(20)));
                        tradingViewEntity.setDoji(!"0".equalsIgnoreCase(dList.get(21)));
                        tradingViewEntity.setMorningStar(!"0".equalsIgnoreCase(dList.get(22)));
                        tradingViewEntity.setGravestone(!"0".equalsIgnoreCase(dList.get(23)));
                        tradingViewEntity.setShootingStar(!"0".equalsIgnoreCase(dList.get(24)));
                        tradingViewEntity.setWhiteSoldiers3(!"0".equalsIgnoreCase(dList.get(25)));
                        tradingViewEntity.setSpinningTopWhite(!"0".equalsIgnoreCase(dList.get(26)));
                        tradingViewEntity.setTriStarBullish(!"0".equalsIgnoreCase(dList.get(27)));
                        tradingViewEntity.setKickingBullish(!"0".equalsIgnoreCase(dList.get(28)));
                        tradingViewEntity.setEngulfingBullish(!"0".equalsIgnoreCase(dList.get(29)));
                        tradingViewEntity.setHaramiBullish(!"0".equalsIgnoreCase(dList.get(30)));
                        tradingViewEntity.setAbandonedBabyBullish(!"0".equalsIgnoreCase(dList.get(31)));
                        tradingViewEntity.setTriStarBearish(!"0".equalsIgnoreCase(dList.get(32)));
                        tradingViewEntity.setKickingBearish(!"0".equalsIgnoreCase(dList.get(33)));
                        tradingViewEntity.setEngulfingBearish(!"0".equalsIgnoreCase(dList.get(34)));
                        tradingViewEntity.setHaramiBearish(!"0".equalsIgnoreCase(dList.get(35)));
                        tradingViewEntity.setAbandonedBabyBearish(!"0".equalsIgnoreCase(dList.get(36)));
                        tradingViewEntity.setDojiDragonfly(!"0".equalsIgnoreCase(dList.get(37)));
                        tradingViewEntity.setHammer(!"0".equalsIgnoreCase(dList.get(38)));
                        tradingViewEntity.setLongShadowUpper(!"0".equalsIgnoreCase(dList.get(39)));
                        tradingViewEntity.setSpinningTopBlack(!"0".equalsIgnoreCase(dList.get(40)));
                        tradingViewEntity.setLongShadowLower(!"0".equalsIgnoreCase(dList.get(41)));
                        tradingViewEntity.setEveningStar(!"0".equalsIgnoreCase(dList.get(42)));
                        tradingViewEntities.add(tradingViewEntity);
                    }
                }
                this.batchInsert(tradingViewEntities);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("第" + index + "页下载完成");

            index+=150;

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    private void deleteAll() {
        tradingViewDao.deleteAll();
    }

    private String buildSymbol(String code) {
        if(code.startsWith("6")){
            return "sh" + code;
        }else if(code.startsWith("0")){
            return "sz" + code;
        }else if(code.startsWith("3")){
            return "sz" + code;
        } else {
            return code;
        }
    }

}