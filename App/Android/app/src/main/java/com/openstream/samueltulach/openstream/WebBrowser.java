package com.openstream.samueltulach.openstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebBrowser extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://github.com/SamuelTulach/OpenStream");
    }
}
