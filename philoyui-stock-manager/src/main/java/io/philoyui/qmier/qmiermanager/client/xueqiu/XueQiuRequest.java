package io.philoyui.qmier.qmiermanager.client.xueqiu;

import java.io.Serializable;
import java.util.Map;

public interface XueQiuRequest<T extends XueQiuResponse> extends Serializable {

    Map<String, String> getMapParameters();

    String getMethodUrl();

    Class<T> getResponseClass();

}