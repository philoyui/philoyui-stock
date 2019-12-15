package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import cn.com.gome.page.field.DoubleFieldDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.FinancialProductDao;
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
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class FinancialProductServiceImpl extends GenericServiceImpl<FinancialProductEntity,Long> implements FinancialProductService {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialProductServiceImpl.class);

    @Autowired
    private FinancialProductDao financialProductDao;

    @Autowired
    private FinancialMarketService financialMarketService;

    private static Gson gson = new GsonBuilder().create();

    @Override
    protected GenericDao<FinancialProductEntity, Long> getDao() {
        return financialProductDao;
    }

    @Override
    public boolean existsBySymbol(String symbol) {
        return financialProductDao.existsBySymbol(symbol);
    }

    @Override
    public FinancialProductEntity findBySymbol(String symbol) {
        return financialProductDao.findBySymbol(symbol);
    }

    @Override
    public void enable(Long id) {
        FinancialProductEntity financialProductEntity = this.get(id);
        financialProductEntity.setEnable(true);
        financialProductDao.saveAndFlush(financialProductEntity);
    }

    @Override
    public void disable(Long id) {
        FinancialProductEntity financialProductEntity = this.get(id);
        financialProductEntity.setEnable(false);
        financialProductDao.saveAndFlush(financialProductEntity);
    }

    @Override
    public List<FinancialProductEntity> findEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        return list(searchFilter);
    }

    @Override
    public void downloadHistoryData() {
        fetchProductDataArray("sh_a",80);
        fetchProductDataArray("sz_a",80);
        fetchProductDataArray("kcb",80);
//        fetchProductDataArray("cyb",80);
        fetchProductDataArray("sgt_hk",80);
        fetchProductDataArray("hgt_hk",80);
    }

    @Transactional
    @Override
    public void markAllDisable() {
        financialProductDao.markAllEnable();
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
                for (ProductData productData : productDataArray) {
                    FinancialProductEntity financialProductEntity = this.findBySymbol(productData.getSymbol());
                    if(financialProductEntity==null){
                        financialProductEntity = new FinancialProductEntity();
                        financialProductEntity.setSymbol(productData.getSymbol());
                        financialProductEntity.setCode(productData.getCode());
                        financialProductEntity.setName(productData.getName());
                        financialProductEntity.setMarketId(financialMarket.getId());
                        financialProductEntity.setModifyTime(new Date());
                        financialProductEntity.setEnable(computeEnable(productData));
                        this.insert(financialProductEntity);
                    }else if(!financialProductEntity.getName().equalsIgnoreCase(productData.getName())){
                        financialProductEntity.setName(productData.getName());
                        financialProductEntity.setEnable(computeEnable(productData));
                        financialProductEntity.setModifyTime(new Date());
                        this.update(financialProductEntity);
                    }
                }
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