package com.example.flyme.eventbusdemo;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_MESSAGE = "0x1";
    private static final String TOAST_MESSAGE = "0x2";
    private static final String APIX_KEY = "33f7668931ea4d18783d5f86f5a36322";
    private static final String PHONE_URL = "http://a.apix.cn/apixlife/phone/phone";
    private TextView txt;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        txt = (TextView) findViewById(R.id.spot);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("phone", "13187073347");
                client.addHeader("apix-key", APIX_KEY);
                client.get(PHONE_URL, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.d("RESPONSE", "SUCCESS");
                        String content = new String(responseBody);
                        EventBusMsg msg = new EventBusMsg(TOAST_MESSAGE, content);
                        EventBus.getDefault().post(msg);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });

    }

    public void onEventMainThread(EventBusMsg msg){
        switch (msg.getMethod()){
            case LOG_MESSAGE:
                Log.d("RESPONSE",msg.getContent());
                break;
            case TOAST_MESSAGE:
                txt.setText(msg.getContent());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
