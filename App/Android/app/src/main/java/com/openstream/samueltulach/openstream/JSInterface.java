package com.openstream.samueltulach.openstream;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JSInterface {

    private WebView mAppView;

    public JSInterface  (WebView appView) {
        this.mAppView = appView;
    }

    @JavascriptInterface
    public void playVideo(String value) {
        Intent i = new Intent(mAppView.getContext(), StreamActivity.class);
        i.putExtra("FILE", value);
        mAppView.getContext().startActivity(i);
    }

}
