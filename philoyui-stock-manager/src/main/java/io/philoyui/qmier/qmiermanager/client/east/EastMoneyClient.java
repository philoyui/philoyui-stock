package io.philoyui.qmier.qmiermanager.client.east;

public interface EastMoneyClient {

    <T extends EastMoneyResponse> T execute(EastMoneyRequest<T> request);


}
