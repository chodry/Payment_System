package com.example.mtn_app.MOMO;

import com.example.mtn_app.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetToken extends MainActivity {

    public String thetoken() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/token/")
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", "290472d867d24600b5c4bf1036f14ace")
                .addHeader("Authorization", "Basic Yzk2MDVlZGYtNDdmYi00MDcyLWJjNzAtMDYyY2I3YmQ0Y2U2OjRlY2IxODVlN2I2ZTQ0NWFhZTQ4MDQwYmNjOTlmMjg4")
                .build();
        Response response = client.newCall(request).execute();

        String res = response.body().string();

        JSONObject object = new JSONObject(res);
        String token = object.getString("access_token");
        String status = String.valueOf(response.code());
        System.out.println("Token: " +token);
        System.out.println("Status: -------------" + status + "---------------------");

        return token;

    }

}
