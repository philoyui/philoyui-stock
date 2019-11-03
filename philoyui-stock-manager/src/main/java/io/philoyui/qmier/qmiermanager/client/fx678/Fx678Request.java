package io.philoyui.qmier.qmiermanager.client.fx678;

import java.io.Serializable;
import java.util.Map;

public interface Fx678Request<T extends Fx678Response> extends Serializable {

    Map<String, String> getMapParameters();

    String getMethodUrl();

    Class<T> getResponseClass();

}
