package com.taller.jandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TranslatePlane extends Activity implements OnClickListener, AnimationListener{

	
	private int destiny = 1; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_translate_plane);
        
        ImageButton destiny1 = (ImageButton)findViewById(R.id.destiny1);
        ImageButton destiny2 = (ImageButton)findViewById(R.id.destiny2);
        
        destiny1.setOnClickListener(this);
        destiny2.setOnClickListener(this);
        
    }
    
    public void onClick(View v) {
    	
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
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent intro = new Intent(this,IndieIntroducing.class);
    	this.startActivity(intro);
    }
}
