package com.taller.jandroid;

import java.io.InputStream;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

	//decodes image and scales it to reduce memory consumption
	protected BitmapDrawable decodeDrawable(int drawable_id, boolean better){
		
		InputStream in = getResources().openRawResource(drawable_id);
		
	    //Decode image size
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		o.inDither = false;
		o.inDensity = 240;
		BitmapFactory.decodeStream(in,null,o);

		//The new size we want to scale to
		final int REQUIRED_SIZE;
		if(better)
			REQUIRED_SIZE=240;
		else
			REQUIRED_SIZE=128;

		//Find the correct scale value. It should be the power of 2.
		int scale=1;
		while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
		    scale*=2;

		//Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize=scale;
		o2.inDither = true;
		o2.inDensity = 240;
		o2.inPreferredConfig = Bitmap.Config.ARGB_8888;
		return new BitmapDrawable(BitmapFactory.decodeStream(in, null, o2));
	}
	
	public void goMenuChallenges(){
		Intent i = new Intent(this,ChallengesActivity.class);
        startActivity(i); 
        finish();
	}
	
}
