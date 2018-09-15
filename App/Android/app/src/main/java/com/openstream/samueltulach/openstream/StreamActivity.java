package com.openstream.samueltulach.openstream;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.util.ServerRunner;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class StreamActivity extends AppCompatActivity {

    TextView textView;
    TextView textViewInfo;
    Server s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        Intent intent = getIntent();
        String file = intent.getExtras().getString("FILE");

        textViewInfo = (TextView) findViewById(R.id.textView3);
        textViewInfo.setText("Stream has started! You should see video playing on your TV or server in few seconds. If not, please check your IP address (" + getIPAddress(true) + ").");

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(file);

        /*new Thread(new Runnable() {
            public void run() {
                Server s= new Server();
                s.runserver();
            }
        }).start();*/

        s = new Server();
        try {
            s.ipadress = getIPAddress(true);
            s.video = intent.getExtras().getString("FILE");
            s.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%');
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) { }
        return "";
    }

    public void stopStream(View v) {
        s.stop();

        // Bacause restart it the way
        Intent mStartActivity = new Intent(this, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
}
