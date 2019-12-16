package io.philoyui.qmier.qmiermanager.service.filter;

import java.util.Set;

public interface StockFilter {

    Set<String> filterSymbol(String param1, String param2, String param3);

}
