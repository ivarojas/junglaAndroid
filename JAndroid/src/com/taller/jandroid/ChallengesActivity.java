package com.taller.jandroid;

import persistance.Jungle;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ChallengesActivity extends MyActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_challenges);
		
		ImageButton shadow = (ImageButton)findViewById(R.id.shadow);
		ImageButton feed = (ImageButton)findViewById(R.id.feed);
        ImageButton sound = (ImageButton)findViewById(R.id.sound);
        ImageButton split = (ImageButton)findViewById(R.id.split);
        ImageButton choose = (ImageButton)findViewById(R.id.choose);
        
        shadow.setOnClickListener(this);
        feed.setOnClickListener(this);
        sound.setOnClickListener(this);
        split.setOnClickListener(this);
        choose.setOnClickListener(this);
        
        Jungle jungle = (Jungle)getApplicationContext();
        jungle.setState(jungle.CHOOSE_CHALLENGE);
	}

	public void onClick(View arg0) {
		switch(arg0.getId()){
			case R.id.shadow: 
				Intent shadow_challenge = new Intent(this,ShadowActivity.class);
				shadow_challenge.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    	this.startActivity(shadow_challenge);
		    	break;
			case R.id.feed: 
				Intent feed_challenge = new Intent(this,FeedingActivity.class);
				feed_challenge.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    	this.startActivity(feed_challenge);
		    	break;
			case R.id.sound: 
				Intent sound_challenge = new Intent(this,SoundActivity.class);
				sound_challenge.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    	this.startActivity(sound_challenge); 
				break;
			case R.id.split:
				Intent split_challenge = new Intent(this,SplitAnimalActivity.class);
				split_challenge.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    	this.startActivity(split_challenge);
				break;
			case R.id.choose:
				Intent choose_challenge = new Intent(this,ChooseAnimalsJungleActivity.class);
				choose_challenge.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    	this.startActivity(choose_challenge);
				break;
		}
		
		finish();
	}

	@Override
	public void verifyChoice(View v) {
		
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		Intent i = new Intent(this, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		startActivity(i);
		finish();
	}
	
}
