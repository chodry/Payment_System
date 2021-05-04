package com.example.mtn_app.MOMO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BeyonicPayment {

    public static String makePayment() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"phonenumber\": \"+256771238781\",\n\t\"amount\": \"500\",\n\t\"currency\": \"UGX\",\n\t\"description\": \"Am testing\",\n\t\"callback_url\": \"https://my.website/payments/callback\",\n\t\"metadata\": {\"my_id\": \"123ASDAsd123\"},\n\t\"send_instructions\": \"True\"\n}");
        Request request = new Request.Builder()
                .url("https://api.beyonic.com/api/collectionrequests")
                .method("POST", body)
                .addHeader("Authorization", "Token  f34e84646fcf944a36bfcbe0b0006de64e2059f4 ")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "kisumu=c7goppqnwpjbqkn46780ctcl04gscu2x")
                .build();

        Response response = client.newCall(request).execute();

        String res = response.body().string();

        JSONObject object = new JSONObject(res);
        String collectionsID = object.getString("id");

        return collectionsID;

    }
}
