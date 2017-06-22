package com.example.android.localbroadcastmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INTENT_FILTER_NAME = "com.example.t.localbroadcast.TEST";

    private LocalBroadcastManager mLocalBroadcastManager;

    // 1) This is your broadcast receiver, because we are registering this at runtime, no need to put it in the
    // AndroidManifest.xml file
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Hey I received something, let's put it on some toast
            Toast.makeText(context, intent.getStringExtra(Intent.EXTRA_TEXT),
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2) get a reference to the LocalBroadcastManager instance
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        findViewById(R.id.broadcast).setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 5) when the activity if paused, unregister the receiver
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3) register our broadcast receiver with the local broadcast manager in onresume
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver,
                new IntentFilter(INTENT_FILTER_NAME));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.broadcast) {
            // 4) broadcast button was clicked, let's send the broadcast
            Intent intent = new Intent(INTENT_FILTER_NAME);
            intent.putExtra(Intent.EXTRA_TEXT, "I THINK I GOT IT");
            mLocalBroadcastManager.sendBroadcast(intent);
        }
    }
}