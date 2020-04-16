package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.StockDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialMarketService;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.to.ProductData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class StockServiceImpl extends GenericServiceImpl<StockEntity,Long> implements StockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockDao stockDao;

    @Autowired
    private FinancialReportService financialReportService;

    @Autowired
    private FinancialMarketService financialMarketService;

    private static Gson gson = new GsonBuilder().create();

    @Override
    protected GenericDao<StockEntity, Long> getDao() {
        return stockDao;
    }

    @Override
    public StockEntity findBySymbol(String symbol) {
        List<StockEntity> stockEntities = stockDao.findBySymbol(symbol);
        return stockEntities.size()>0?stockEntities.get(0):null;
    }

    @Override
    public void enable(Long id) {
        StockEntity stockEntity = this.get(id);
        stockEntity.setEnable(true);
        stockDao.saveAndFlush(stockEntity);
    }

    @Override
    public void disable(Long id) {
        StockEntity stockEntity = this.get(id);
        stockEntity.setEnable(false);
        stockDao.saveAndFlush(stockEntity);
    }

    @Override
    public List<StockEntity> findEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        return list(searchFilter);
    }

    @Override
    public void downloadHistoryData() {
        stockDao.deleteAll();
        fetchProductDataArray("sh_a",80);
//        fetchProductDataArray("sz_a",80);
//        fetchProductDataArray("kcb",80);
        fetchProductDataArray("kcb_root",80);
//        fetchProductDataArray("sgt_hk",80);
//        fetchProductDataArray("hgt_hk",80);
    }

    @Override
    public void updateAll(List<StockEntity> stockEntityList) {
        stockDao.saveAll(stockEntityList);
    }

    @Transactional
    @Override
    public void allEnable() {
        stockDao.enableAll();
    }

    @Override
    public List<StockEntity> findAll() {
        return stockDao.findAll();
    }

    private void fetchProductDataArray(String identifier,int pageSize) {
        int pageNo = 1;
        while(true){
            FinancialMarketEntity financialMarket = financialMarketService.findByIdentifier(identifier);
            String fetchUrl = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=" + pageNo + "&num=" + pageSize + "&sort=symbol&asc=1&node=" + identifier + "&symbol=&_s_r_a=page";
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

                List<StockEntity> stockEntities = Lists.newArrayList();

                for (ProductData productData : productDataArray) {
                    if(!productData.getSymbol().startsWith("sz3")){
                        StockEntity stockEntity = new StockEntity();
                        stockEntity.setSymbol(productData.getSymbol());
                        stockEntity.setCode(productData.getCode());
                        stockEntity.setName(productData.getName());
                        stockEntity.setMarketId(financialMarket.getId());
                        stockEntity.setModifyTime(new Date());
                        stockEntity.setEnable(computeEnable(productData));
                        stockEntity.setPb(productData.getPb());
                        stockEntity.setPer(productData.getPer());
                        stockEntity.setTurnoverRatio(productData.getTurnOverRatio());
                        stockEntity.setTotalPrice(productData.getMktCap());
                        stockEntities.add(stockEntity);
                    }
                }

                stockDao.saveAll(stockEntities);

                LOG.info("抓取数据成功，地址为：" + fetchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pageNo++;
        }
        LOG.info("抓取金融产品数据完成....");
    }

    /**
     * 计算是否需要启用每天的抓取
     * @param productData
     * @return
     */
    private Boolean computeEnable(ProductData productData) {
        if(productData.getName().startsWith("*ST")
                ||productData.getName().startsWith("S")
                ||productData.getName().startsWith("PT")
                ||productData.getName().startsWith("ST")
                ||productData.getName().startsWith("S*ST")){
            return false;
        }
        return true;
    }
}