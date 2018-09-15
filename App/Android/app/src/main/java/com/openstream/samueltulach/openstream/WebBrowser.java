package com.openstream.samueltulach.openstream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
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
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // RIP HTTPS

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
            webView.loadUrl("javascript:%20(function(){var%20e={eval:'\"function%20play(e){window.JSInterface.playVideo(e)}for(var%20sources=document.getElementsByTagName(\\\\\"source\\\\\"),final=\\\\\"<p>Please%20click%20on%20video%20you%20want%20to%20play:</p><br>\\\\\",i=0;i<sources.length;i++)final+=\\\\\"<a%20href=\\'#\\'%20onclick=\\'play(this.innerHTML);\\'>\\\\\"+sources[i].src+\\\\\"</a><br>\\\\\";document.body.innerHTML=final;\"'},t=!0;if(\"object\"==typeof%20this.artoo&&(artoo.settings.reload||(artoo.log.verbose(\"artoo%20already%20exists%20within%20this%20page.%20No%20need%20to%20inject%20him%20again.\"),artoo.loadSettings(e),artoo.exec(),t=!1)),t){var%20i=document.getElementsByTagName(\"body\")[0];i||(i=document.createElement(\"body\"),document.firstChild.appendChild(i));var%20o=document.createElement(\"script\");console.log(\"artoo.js%20is%20loading...\"),o.src=\"//medialab.github.io/artoo/public/dist/artoo-latest.min.js\",o.type=\"text/javascript\",o.id=\"artoo_injected_script\",o.setAttribute(\"settings\",JSON.stringify(e)),i.appendChild(o)}}).call(this);");
        }
        return super.onOptionsItemSelected(item);
    }
}
