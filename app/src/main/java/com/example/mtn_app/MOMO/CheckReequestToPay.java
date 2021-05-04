package com.example.mtn_app.MOMO;

import com.example.mtn_app.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckReequestToPay extends RequestToPay {

    private static GetToken getToken = new GetToken();
    private static RequestToPay requestToPay = new RequestToPay();

    public static JSONObject checkPayment() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/" + requestToPay.rtp())
                .method("GET", null)
                .addHeader("X-Target-Environment", "sandbox")
                .addHeader("Ocp-Apim-Subscription-Key", "290472d867d24600b5c4bf1036f14ace")
                .addHeader("Authorization", "Bearer " + getToken.thetoken())
                .build();

        Response response = client.newCall(request).execute();

        String res2 = response.body().string();

        JSONObject object3 = new JSONObject(res2);
//        String token = object.getString("access_token");
        String status3 = String.valueOf(response.code());
        String amount = object3.getString("amount");
        String status4 = object3.getString("status");
//        System.out.println(token);
        System.out.println("--------Status 2-----" + status3 + "-------");
        System.out.println("--------status 4-----" + status4 + "-------");
        System.out.println("--------Amount-----" + amount + "-------");
        System.out.println("--------Object-----" + object3 + "-------");

        return object3;
    }
}
