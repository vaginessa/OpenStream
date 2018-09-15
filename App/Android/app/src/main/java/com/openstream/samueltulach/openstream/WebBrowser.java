package com.openstream.samueltulach.openstream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebBrowser extends AppCompatActivity {

    WebView webView;
    String urltext = "https://github.com/SamuelTulach/OpenStream/blob/master/Other/welcome.md";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);

        JSInterface j = new JSInterface(webView);
        webView.addJavascriptInterface(j, "JSInterface");
        webView.loadUrl(urltext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browsermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.changeurl) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Type in URL:");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setText("https://");
            builder.setView(input);
            builder.setPositiveButton("Do it", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    urltext = input.getText().toString();
                    webView.loadUrl(urltext);
                }
            });
            builder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } else if (id == R.id.refresh) {
            webView.reload();
        } else if (id == R.id.search) {
            webView.loadUrl("javascript:var sources = document.getElementsByTagName(\"source\");var final = \"<p>Please click on video you want to play:</p><br>\";var script = \"<script>function play(link) {window.JSInterface.playVideo(link);}</script>\"for (var i = 0;i<sources.length;i++) {    final += \"<a href='#' onclick='play(this.innerHTML);'>\" + sources[i].src + \"</a><br>\";}document.write(script + final);");
        }
        return super.onOptionsItemSelected(item);
    }
}
