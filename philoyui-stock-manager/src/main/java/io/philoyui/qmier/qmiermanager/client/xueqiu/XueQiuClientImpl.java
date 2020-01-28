package io.philoyui.qmier.qmiermanager.client.xueqiu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class XueQiuClientImpl implements XueQiuClient{

    private Gson gson = new GsonBuilder().create();

    @Override
    public <T extends XueQiuResponse> T execute(XueQiuRequest<T> request) {
        String fetchUrl = buildFetchUrl(request);
        try {
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .cookie("xq_r_token", "59c54cd0ecb1c46e0250f2609705ad960f53f459")
                    .cookie("xq_a_token", "80912439e9511e25f38d7c73d47cddbf429f36d7")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            return gson.fromJson(response.body(),request.getResponseClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T extends XueQiuResponse> String buildFetchUrl(XueQiuRequest<T> request) {
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
