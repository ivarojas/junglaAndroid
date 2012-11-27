package com.taller.jandroid;

import persistance.Jungle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ArrivingJungle extends MyActivity implements OnClickListener, AnimationListener{
	
	int destiny;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arriving_jungle);

        Jungle app = (Jungle)getApplicationContext();
        destiny = app.getDestiny();
        
        if(destiny == app.BORNEO){
        	findViewById(R.id.relative_arriv).setBackgroundResource(R.drawable.arriving_jungle2);
        }
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_arriv);
        next.setOnClickListener(this);
        next.setVisibility(View.INVISIBLE);
        
        ImageView indie = (ImageView)findViewById(R.id.indie);
    	indie.setVisibility(View.INVISIBLE);
        translate();
        
    }
    
    public void onClick(View v) {
    	if(v.getId() == R.id.nextButton_arriv){
    		Intent presentation = null;
    		if(destiny == 0){
    			presentation = new Intent(this, PresentationActivity.class);
    		}
    		else{
    			presentation = new Intent(this, PresentationBorneoActivity.class);
    		}
    		presentation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
    		this.startActivity(presentation);
    		finish();
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
    
    public void onAnimationStart(Animation arg0) {
    }           
    public void onAnimationRepeat(Animation arg0) {
    }           
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
    	intro.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
    	this.startActivity(intro);
    	finish();
    }

	@Override
	public void verifyChoice(View v) {
		
	}
    
}
