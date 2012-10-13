package com.taller.jandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ArrivingJungle extends Activity implements OnClickListener, AnimationListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_arriving_jungle);
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_arriv);
        next.setOnClickListener(this);
        next.setVisibility(View.INVISIBLE);
        
        ImageView indie = (ImageView)findViewById(R.id.indie);
    	indie.setVisibility(View.INVISIBLE);
        translate();
        
    }
    
    @Override
    public void onClick(View v) {
    	if(v.getId() == R.id.nextButton_arriv){
    		Intent shadow = new Intent(this, PresentationActivity.class);
    		this.startActivity(shadow);
    	}
    		
	}

	public void translate() {
		ImageView iv = (ImageView)findViewById(R.id.indieParachuting); 
    	
		Animation translate =  AnimationUtils.loadAnimation(this, R.anim.translate_arriving);
    	
    	translate.setAnimationListener(this);		 
		translate.setDuration(3000);
		translate.reset();
		translate.setFillAfter(true);		
		
		iv.clearAnimation();
		iv.startAnimation(translate);

	}
    
    @Override
    public void onAnimationStart(Animation arg0) {
    }           
    @Override
    public void onAnimationRepeat(Animation arg0) {
    }           
    @Override
    public void onAnimationEnd(Animation arg0) {
    	ImageView iv = (ImageView)findViewById(R.id.indieParachuting);
    	iv.clearAnimation();
    	iv.setVisibility(View.GONE);
    	indieArrive();
    }

	public void indieArrive() {
		ImageView indie = (ImageView)findViewById(R.id.indie);
    	indie.setVisibility(View.VISIBLE);
    	ImageButton next = (ImageButton)findViewById(R.id.nextButton_arriv);
        next.setVisibility(View.VISIBLE);
    	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent intro = new Intent(this,TranslatePlane.class);
    	this.startActivity(intro);
    }

}
