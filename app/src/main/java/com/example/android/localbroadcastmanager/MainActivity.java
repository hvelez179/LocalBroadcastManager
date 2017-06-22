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

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "HOLY SMOKES!  "+ intent.getStringExtra(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
       // LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(INTENT_FILTER_NAME));
        findViewById(R.id.broadcast).setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mLocalBroadcastManager.registerReceiver(mBroadcastReceiver,
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(INTENT_FILTER_NAME));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.broadcast) {
            Intent intent = new Intent(INTENT_FILTER_NAME);
            intent.putExtra(Intent.EXTRA_TEXT, "I REALLY GOT THIS!!!!");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }
}