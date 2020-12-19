package io.philoyui.stock.client.fx678;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class Fx678ClientImpl implements Fx678Client {

    private Gson gson = new GsonBuilder().create();

    @Override
    public <T extends Fx678Response> T execute(Fx678Request<T> request) {
        try {
            Connection connection = Jsoup.connect(request.getMethodUrl())
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true);

            for (Map.Entry<String, String> stringStringEntry : request.getMapParameters().entrySet()) {
                connection.data(stringStringEntry.getKey(),stringStringEntry.getValue());
            }

            Document document = connection.post();

            return gson.fromJson(document.text(),request.getResponseClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
