package io.philoyui.stock.client.east;

public interface EastMoneyClient {

    <T extends EastMoneyResponse> T execute(EastMoneyRequest<T> request);


}
