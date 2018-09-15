package com.openstream.samueltulach.openstream;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JSInterface {

    private WebView mAppView;

    public JSInterface  (WebView appView) {
        this.mAppView = appView;
    }

    @JavascriptInterface
    public void playVideo(String echo){
        Toast toast = Toast.makeText(mAppView.getContext(), echo, Toast.LENGTH_SHORT);
        toast.show();
    }

}
