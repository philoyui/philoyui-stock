package io.philoyui.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.stock.client.east.EastMoneyClient;
import io.philoyui.stock.client.east.EastMoneyClientImpl;
import io.philoyui.stock.client.east.request.ConceptGetRequest;
import io.philoyui.stock.client.east.request.IndustryGetRequest;
import io.philoyui.stock.client.east.response.ConceptGetResponse;
import io.philoyui.stock.client.east.response.IndustryGetResponse;
import org.junit.Test;

public class IndustryTest {


    private Gson gson = new GsonBuilder().create();

    @Test
    public void test_industry(){

        EastMoneyClient client = new EastMoneyClientImpl();

        IndustryGetRequest request = new IndustryGetRequest();
        request.setPageNo(1);
        request.setPageSize(10);

        IndustryGetResponse response = client.execute(request);

        System.out.println(gson.toJson(response));


        ConceptGetRequest crequest = new ConceptGetRequest();
        crequest.setPageNo(1);
        crequest.setPageSize(10);

        ConceptGetResponse execute = client.execute(crequest);

        System.out.println(gson.toJson(execute));
    }

}
