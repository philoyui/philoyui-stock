package io.philoyui.qmier.qmiermanager.service;

import java.io.Serializable;
import java.util.Date;

public interface StockData extends Serializable {

    Date getDay();

    Double getClose();

    String getSymbol();

}
