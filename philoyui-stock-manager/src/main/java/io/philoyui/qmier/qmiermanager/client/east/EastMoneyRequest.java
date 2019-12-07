package io.philoyui.qmier.qmiermanager.client.east;

import java.io.Serializable;
import java.util.Map;

public interface EastMoneyRequest<T extends EastMoneyResponse> extends Serializable {

    Map<String, String> getMapParameters();

    String getMethodUrl();

    Class<T> getResponseClass();

    String formatContent(String body);
}
