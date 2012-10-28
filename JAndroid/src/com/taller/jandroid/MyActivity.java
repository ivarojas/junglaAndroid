package com.taller.jandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

abstract public class MyActivity extends Activity {
	
	public int drop_background;
	BroadcastReceiver broadcastReceiver = null;

	public int getDrop_background() {
		return drop_background;
	}

	public void setDrop_background(int drop_background) {
		this.drop_background = drop_background;
	}

	public abstract void verifyChoice(View v);
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}

}
