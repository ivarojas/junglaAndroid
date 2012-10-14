package com.taller.jandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class GoodbyeActivity extends Activity implements AnimationListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_goodbye);
        
        ImageView image = (ImageView) findViewById(R.id.ship);
        AnimationSet animSet = new AnimationSet(false);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim1);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim2);
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim3);
        anim1.setDuration(5000);
        animSet.addAnimation(anim1);
        anim2.setDuration(5000);
        anim2.setStartOffset(5000);
        animSet.addAnimation(anim2);
        anim3.setDuration(5000);
        anim3.setStartOffset(10000);
        animSet.addAnimation(anim3);
        animSet.setAnimationListener(this);
        
        image.startAnimation(animSet);
        
    }

	public void onAnimationEnd(Animation animation) {
		ImageView indie_ship = (ImageView)findViewById(R.id.ship);
		indie_ship.setVisibility(View.INVISIBLE);
		Intent next = new Intent(this,TranslatePlane.class);
		startActivity(next);
		
	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,SayGoodbyeActivity.class);
        startActivity(i);    	
    }
}
