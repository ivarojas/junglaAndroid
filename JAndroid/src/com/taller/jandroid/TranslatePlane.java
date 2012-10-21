package com.taller.jandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TranslatePlane extends Activity implements OnClickListener, AnimationListener{

	
	private int destiny = 1;
	BroadcastReceiver broadcastReceiver = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_translate_plane);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        ImageButton destiny1 = (ImageButton)findViewById(R.id.destiny1);
        ImageButton destiny2 = (ImageButton)findViewById(R.id.destiny2);
        
        destiny1.setOnClickListener(this);
        destiny2.setOnClickListener(this);
        
    }
    
    public void onClick(View v) {
    	
    	
    	MediaPlayer mp = MediaPlayer.create(this, R.raw.biplane);
    	mp.start();
    	
    	ImageView iv = (ImageView)findViewById(R.id.plane); 
    	
    	Animation translate = null;
    	
    	switch(v.getId()){
    	case R.id.destiny1:{
    		translate = AnimationUtils.loadAnimation(this, R.anim.translate_destiny1);
    		break;
    	}
    	case R.id.destiny2:{
//    		translate = AnimationUtils.loadAnimation(this, R.anim.translate_destiny2);
//    		break;
    		return;
    	}
    	}
    			 
    	
    	ImageButton dest1 = (ImageButton)findViewById(R.id.destiny1);
    	ImageButton dest2 = (ImageButton)findViewById(R.id.destiny2);
    	dest1.setVisibility(View.INVISIBLE);
    	dest2.setVisibility(View.INVISIBLE);
    	
		translate.setDuration(3000);
		translate.reset();
		translate.setFillAfter(true);		
		translate.setAnimationListener(this);
		
		iv.clearAnimation();
		iv.startAnimation(translate);
	}

    public void onAnimationStart(Animation arg0) {
    }           
    public void onAnimationRepeat(Animation arg0) {
    }           
    public void onAnimationEnd(Animation arg0) {
    	Intent nextintent = null;
    	if(destiny == 1){
    		nextintent = new Intent(this,ArrivingJungle.class);
    		this.startActivity(nextintent);
    		this.finish();
    	}
    	else{
    		
    	}
    		
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) 
    {
        switch (item.getItemId()) {
//        case ENABLE_S2_MENU_ID:
//            if (mSpot2 != null) mSpot2.setDragLayer (mDragLayer);
//            return true;
        }

        
        Intent intent = new Intent("CLOSE_ALL");
        this.sendBroadcast(intent);
//        android.os.Process.killProcess(android.os.Process.myPid()); 
        finish();
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent intro = new Intent(this,IndieIntroducing.class);
    	this.startActivity(intro);
    }
    
	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
}
