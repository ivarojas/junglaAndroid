package com.taller.jandroid;

import persistance.Jungle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TranslatePlane extends MyActivity implements OnClickListener, AnimationListener{

	
	private int destiny = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_translate_plane);
               
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
    		destiny = 0;
    		translate = AnimationUtils.loadAnimation(this, R.anim.translate_destiny1);
    		break;
    	}
    	case R.id.destiny2:{
    		destiny = 1;
    		translate = AnimationUtils.loadAnimation(this, R.anim.translate_destiny2);
    		break;
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
    	
    	Jungle app = (Jungle)getApplicationContext();
    	app.setDestiny(destiny);
    	Intent nextintent = new Intent(this,ArrivingJungle.class);
    	nextintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		this.startActivity(nextintent);
		this.finish();
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent intro = new Intent(this,IndieIntroducing.class);
    	intro.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
    	this.startActivity(intro);
    	finish();
    }

	@Override
	public void verifyChoice(View v) {

	}

}
