package com.openstream.samueltulach.openstream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenWebbrowser(View v) {
        Intent intent = new Intent(this, WebBrowser.class);
        startActivity(intent);
    }

    public void OpenFilebrowser(View v) {
        new ChooserDialog().with(this)
                .withStartFile("/sdcard")
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(MainActivity.this, "FILE: " + path, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, StreamActivity.class);
                        i.putExtra("FILE", path);
                        startActivity(i);
                    }
                })
                .build()
                .show();
    }
}
