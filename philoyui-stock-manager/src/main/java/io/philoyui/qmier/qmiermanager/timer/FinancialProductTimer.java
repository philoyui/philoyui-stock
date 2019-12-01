package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.page.field.DoubleFieldDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialMarketService;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import io.philoyui.qmier.qmiermanager.to.ProductData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * 金融商品定时器，主要获取新的交易产品，如每天的新股
 */
@Component
public class FinancialProductTimer {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleFieldDefinition.class);

    @Autowired
    private FinancialProductService financialProductService;

    @Autowired
    private FinancialMarketService financialMarketService;

    private static Gson gson = new GsonBuilder().create();

    /**
     * 每天读取股票列表（分为上证，深证，创业板，科创板，港股，美股，期货，外汇，贵金属），发现新股存入，每天执行一次
     *
     * http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?
     * page=3&num=40&sort=symbol&asc=1&node=sh_a&symbol=&_s_r_a=page
     *
     */
    @Scheduled(cron="* * 8 * * ? ") //早晨八点
    public void fetch(){
        fetchProductDataArray("sh_a",80);
        fetchProductDataArray("sz_a",80);
        fetchProductDataArray("kcb",80);
//        fetchProductDataArray("cyb",80);
        fetchProductDataArray("sgt_hk",80);
        fetchProductDataArray("hgt_hk",80);
    }

    private void fetchProductDataArray(String identifier,int pageSize) {
        int pageNo = 1;
        while(true){
            FinancialMarketEntity financialMarket = financialMarketService.findByIdentifier(identifier);
            String fetchUrl = new StringBuilder().append("http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=").append(pageNo).append("&num=").append(pageSize).append("&sort=symbol&asc=1&node=").append(identifier).append("&symbol=&_s_r_a=page").toString();
            try {
                Connection.Response response = Jsoup.connect(fetchUrl)
                        .header("Content-Type", "application/json")
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute();
                String result = response.body().replaceAll("symbol","\"symbol\"")
                        .replaceAll("code","\"code\"")
                        .replaceAll("name","\"name\"")
                        .replaceAll("trade","\"trade\"")
                        .replaceAll("pricechange","\"pricechange\"")
                        .replaceAll("changepercent","\"changepercent\"")
                        .replaceAll("sell","\"sell\"")
                        .replaceAll("settlement","\"settlement\"")
                        .replaceAll("open","\"open\"")
                        .replaceAll("high","\"high\"")
                        .replaceAll("low","\"low\"")
                        .replaceAll("volume","\"volume\"")
                        .replaceAll("amount","\"amount\"")
                        .replaceAll("ticktime","\"ticktime\"")
                        .replaceAll("per:","\"per\":")
                        .replaceAll("pb","\"pb\"")
                        .replaceAll("mktcap","\"mktcap\"")
                        .replaceAll("nmc","\"nmc\"")
                        .replaceAll("buy","\"buy\"")
                        .replaceAll("turnoverratio","\"turnoverratio\"")
                        ;
                ProductData[] productDataArray = gson.fromJson(result, ProductData[].class);
                if(productDataArray==null){
                    break;
                }
                for (ProductData productData : productDataArray) {
                    FinancialProductEntity financialProductEntity = financialProductService.findBySymbol(productData.getSymbol());
                    if(financialProductEntity==null){
                        financialProductEntity = new FinancialProductEntity();
                        financialProductEntity.setSymbol(productData.getSymbol());
                        financialProductEntity.setCode(productData.getCode());
                        financialProductEntity.setName(productData.getName());
                        financialProductEntity.setMarketId(financialMarket.getId());
                        financialProductEntity.setModifyTime(new Date());
                        financialProductEntity.setEnable(true);
                        financialProductService.insert(financialProductEntity);
                    }else if(!financialProductEntity.getName().equalsIgnoreCase(productData.getName())){
                        financialProductEntity.setName(productData.getName());
                        financialProductEntity.setModifyTime(new Date());
                        financialProductService.update(financialProductEntity);
                    }
                }
                LOG.info("抓取数据成功，地址为：" + fetchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pageNo++;
        }
        LOG.info("抓取金融产品数据完成....");
    }

}
