package io.philoyui.stock.client.xueqiu;

public interface XueQiuClient {

    <T extends XueQiuResponse> T execute(XueQiuRequest<T> request);

}
