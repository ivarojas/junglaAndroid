package com.taller.jandroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class GoodbyeActivity extends MyActivity implements AnimationListener {
	
	MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goodbye);
        
        ImageView image = (ImageView) findViewById(R.id.ship);
        AnimationSet animSet = new AnimationSet(false);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim1);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim2);
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.goodbye_anim3);
        anim1.setDuration(3000);
        animSet.addAnimation(anim1);
        anim2.setDuration(3000);
        anim2.setStartOffset(3000);
        animSet.addAnimation(anim2);
        anim3.setDuration(3000);
        anim3.setStartOffset(6000);
        animSet.addAnimation(anim3);
        animSet.setAnimationListener(this);
        
        mp = MediaPlayer.create(this, R.raw.nature);
        mp.start();
        image.startAnimation(animSet);
        
        
    }

	public void onAnimationEnd(Animation animation) {
		mp.stop();
		ImageView indie_ship = (ImageView)findViewById(R.id.ship);
		indie_ship.setVisibility(View.INVISIBLE);
		Intent next = new Intent(this,TranslatePlane.class);
		next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(next);
		finish();
		
	}

	public void onAnimationRepeat(Animation animation) {
		
	}

	public void onAnimationStart(Animation animation) {
		
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,SayGoodbyeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        startActivity(i);    	
        finish();
    }

	@Override
	public void verifyChoice(View v) {

	}

}
