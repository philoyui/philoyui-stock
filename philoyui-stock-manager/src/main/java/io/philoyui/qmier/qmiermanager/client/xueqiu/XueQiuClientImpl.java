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
                    .cookie("xq_r_token", "4e03d73d0034b90d908e39c83ccfc0b1059a0976")
                    .cookie("xq_a_token", "8f7ed73b060e5e26ae1a47406fc61f32604a1989")
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
