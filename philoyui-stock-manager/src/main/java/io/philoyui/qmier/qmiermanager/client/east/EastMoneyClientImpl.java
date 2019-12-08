package io.philoyui.qmier.qmiermanager.client.east;

import cn.com.gome.page.excp.GmosException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class EastMoneyClientImpl implements EastMoneyClient {

    private Gson gson = new GsonBuilder().create();

    @Override
    public <T extends EastMoneyResponse> T execute(EastMoneyRequest<T> request) {
        String fetchUrl = buildFetchUrl(request);
        try {
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            return gson.fromJson(request.formatContent(response.body()),request.getResponseClass());
        } catch (IOException e) {
            throw new GmosException("抓取数据失败：" + fetchUrl);
        }
    }

    private <T extends EastMoneyResponse> String buildFetchUrl(EastMoneyRequest<T> request) {
        String fetchUrl = request.getMethodUrl();
        StringBuilder fetchUrlBuilder = new StringBuilder(fetchUrl);
        Map<String, String> mapParameters = request.getMapParameters();
        if(mapParameters.size()>0){
            fetchUrlBuilder.append("?");
        }
        for (Map.Entry<String, String> stringStringEntry : mapParameters.entrySet()) {
            fetchUrlBuilder.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
        }
        fetchUrlBuilder.deleteCharAt(fetchUrlBuilder.lastIndexOf("&"));
        return fetchUrlBuilder.toString();
    }

}
