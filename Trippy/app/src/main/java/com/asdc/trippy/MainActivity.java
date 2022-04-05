package com.asdc.trippy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWebsiteView();
    }

    private void setupWebsiteView(){
        WebView myWebView = (WebView) findViewById(R.id.trippy_web_view);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://tripmanagement.herokuapp.com/");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
    }

}