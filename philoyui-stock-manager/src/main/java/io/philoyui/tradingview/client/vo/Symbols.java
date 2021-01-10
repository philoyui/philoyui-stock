/**
  * Copyright 2019 bejson.com 
  */
package io.philoyui.tradingview.client.vo;
import java.util.List;

/**
 * Auto-generated: 2019-10-29 22:5:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Symbols {

    private Query query;
    private List<String> tickers;
    public void setQuery(Query query) {
         this.query = query;
     }
     public Query getQuery() {
         return query;
     }

    public void setTickers(List<String> tickers) {
         this.tickers = tickers;
     }
     public List<String> getTickers() {
         return tickers;
     }

}