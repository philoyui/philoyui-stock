package io.philoyui.qmier.qmiermanager.client.xueqiu;

public interface XueQiuClient {

    <T extends XueQiuResponse> T execute(XueQiuRequest<T> request);

}
