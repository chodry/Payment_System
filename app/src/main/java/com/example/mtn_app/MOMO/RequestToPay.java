package com.example.mtn_app.MOMO;

import com.example.mtn_app.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestToPay extends GetToken {

    private static GetToken getToken = new GetToken();
    public static String uniqueID = UUID.randomUUID().toString();

    private static String money = MainActivity.amount;
    private static String phone = MainActivity.phone;

    public static String rtp() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"amount\":\"" + money + "\"," +
                " \r\n  \"currency\": \"EUR\",\r\n  \"externalId\": \"65185415\",\r\n" +
                "  \"payer\": {\r\n    \"partyIdType\": \"MSISDN\",\r\n    \"partyId\": \"" + phone + "\"\r\n  }," +
                "\r\n  \"payerMessage\": \"Pay for Services\",\r\n  \"payeeNote\": \"Payer note\"\r\n}");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay")
                .method("POST", body)
                .addHeader("X-Reference-Id", uniqueID)
                .addHeader("X-Target-Environment", "sandbox")
                .addHeader("Ocp-Apim-Subscription-Key", "290472d867d24600b5c4bf1036f14ace")
                .addHeader("Authorization", "Bearer " + getToken.thetoken())
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        String res2 = response.body().string();

//        JSONObject object2 = new JSONObject(res2);
//        String token = object.getString("access_token");
        String status2 = String.valueOf(response.code());
//        System.out.println(token);
        System.out.println("--------Status-----" + status2 + "-------");

        return uniqueID;
    }
}
