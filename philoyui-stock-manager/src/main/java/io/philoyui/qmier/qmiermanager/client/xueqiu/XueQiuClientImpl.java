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
                    .cookie("xq_r_token", "5d9da1b897618a378ec339215420c06ac7674a3a")
                    .cookie("xq_a_token", "63de0d2b2c9432d0675782fcd02ec6182112bffd")
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
        String fetchUrl = "https://xueqiu.com" + request.getMethodUrl();
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
