package com.example.mtn_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtn_app.MOMO.BeyonicGetPayment;
import com.example.mtn_app.MOMO.CheckReequestToPay;
import com.example.mtn_app.MOMO.GetToken;
import com.example.mtn_app.MOMO.RequestToPay;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String amount;
    public static String phone;
    EditText money;
    Button submit;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        submit = findViewById(R.id.submit);
        status = findViewById(R.id.status);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = findViewById(R.id.money);
                amount = money.getText().toString();
                phone = "+256771238781";
               if(!amount.isEmpty()){
                   MTNMOMO(amount);
               }else{
                   Toast.makeText(MainActivity.this, "Enter Amount Please", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void MTNMOMO(String amount) {
//        GetToken getToken = new GetToken();
//        RequestToPay requestToPay = new RequestToPay();
        CheckReequestToPay checkReequestToPay = new CheckReequestToPay();
//        BeyonicGetPayment beyonicGetPayment = new BeyonicGetPayment();

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
//                    String message = checkReequestToPay.checkPayment();
//                    status.setText(message);
                    JSONObject object = checkReequestToPay.checkPayment();
//                    JSONObject object = beyonicGetPayment.checkBeyonicPayment();
                    String moneyy = object.getString("amount");
                    String message = object.getString("status");
                    status.setText(message + "--" + moneyy);
                    money.getText().clear();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}