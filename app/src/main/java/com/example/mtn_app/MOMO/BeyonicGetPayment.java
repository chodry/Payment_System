package com.example.mtn_app.MOMO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BeyonicGetPayment {

    private static BeyonicPayment beyonicPayment = new BeyonicPayment();

    public static JSONObject checkBeyonicPayment() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.beyonic.com/api/collectionrequests/" + beyonicPayment.makePayment())
                .method("GET", null)
                .addHeader("Authorization", "Token f34e84646fcf944a36bfcbe0b0006de64e2059f4 ")
                .addHeader("Cookie", "kisumu=c7goppqnwpjbqkn46780ctcl04gscu2x")
                .build();
        Response response = client.newCall(request).execute();

        String res2 = response.body().string();

        JSONObject object3 = new JSONObject(res2);
        String status4 = object3.getString("status");
        System.out.println("--------status 4-----" + status4 + "-------");

        return object3;

    }
}
