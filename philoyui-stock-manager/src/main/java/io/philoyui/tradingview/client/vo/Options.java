/**
 * Copyright 2019 bejson.com
 */
package io.philoyui.tradingview.client.vo;

/**
 * Auto-generated: 2019-10-29 22:5:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Options {

    private String lang;

    private String data_restrictions;

    private boolean active_symbols_only;

    public Options() {

    }

    public Options(String lang, String data_restrictions, boolean active_symbols_only) {
        this.lang = lang;
        this.data_restrictions = data_restrictions;
        this.active_symbols_only = active_symbols_only;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setData_restrictions(String data_restrictions) {
        this.data_restrictions = data_restrictions;
    }

    public String getData_restrictions() {
        return data_restrictions;
    }

    public void setActive_symbols_only(boolean active_symbols_only) {
        this.active_symbols_only = active_symbols_only;
    }

    public boolean getActive_symbols_only() {
        return active_symbols_only;
    }

}