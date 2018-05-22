package com.example.divyansh.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        WebView webView = (WebView)findViewById(R.id.webviewid);

        String WebData = intent.getStringExtra("WebData");

        webView.loadData(WebData,"text/html",null);

    }
}
