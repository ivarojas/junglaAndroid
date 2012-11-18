package com.taller.jandroid;

import java.util.List;

import persistance.Jungle;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import animal.AnimalSet;

public class SoundActivity extends MyActivity{

	private AlertDialog alertDialog;
	MediaPlayer media_player;
	private AnimalSet animal_set;
	private List<String> random_animals;
	private String correct_answer;
	private static ImageButton sound;
	private AnimationDrawable sound_animation;
	private int destiny;
	private MediaPlayer media_player2;
	private Jungle app;
	private static Context context;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.activity_sound);
        
        app = (Jungle)getApplicationContext();
        destiny = app.getDestiny();        
		
		sound = (ImageButton)findViewById(R.id.soundChallenge);
		
		setSoundAnimal();
		setSoundButton();
		
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });
        
		// create alert dialog
		alertDialog = builder.create();
		
		context = this.getApplicationContext();
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
    
    public void verifyAnswer(View view){
    	Jungle app = (Jungle)getApplicationContext();
    	Button button = (Button) findViewById(view.getId());
    	if(button.getText().equals(app.getSpanishName(destiny,this.correct_answer))){
    		if(media_player!=null && media_player.isPlaying())
    			media_player.stop();
    		if(media_player2!=null && media_player2.isPlaying())
    			media_player2.stop();
    		media_player = MediaPlayer.create(SoundActivity.this, R.raw.tada);
    		media_player.start();
    		Intent intent = new Intent(this, ChooseAnimalsJungleActivity.class);
            startActivity(intent);
            finish();
    	}else{
    		if(media_player!=null && media_player.isPlaying())
    			media_player.stop();
    		media_player = MediaPlayer.create(SoundActivity.this, R.raw.failbeep);
    		media_player.start();
    		alertDialog.show();
    	}
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	if (app.getState() != app.CHOOSE_CHALLENGE){
        	if(media_player!=null && media_player.isPlaying())
    			media_player.stop();
        	if(media_player2!=null && media_player2.isPlaying())
    			media_player2.stop();
            Intent i = new Intent(this,SplitAnimalActivity.class);
            startActivity(i);    
            finish();
		}else{
			this.goMenuChallenges();
		}
    }

	public void setSoundAnimal(){
		//setting sound animal and choices to show
        animal_set = new AnimalSet(this);
        
        Jungle app = (Jungle)getApplicationContext();
        
        if(destiny == app.CONGO){
        	random_animals = animal_set.getAnimalsRandom("congo", 3, true);
        }
        else{
        	random_animals = animal_set.getAnimalsRandom("borneo", 3, true);
        }
        
        int rand_n = 0;
        do{
        	rand_n = (int)(Math.random() * 3);
        	correct_answer = random_animals.get(rand_n);
        }while(correct_answer.equals("okapi") || correct_answer.equals("proboscis"));
                
        //Setting sound
		media_player2 = MediaPlayer.create(SoundActivity.this, this.animal_set.getSoundAnimalId(this.correct_answer));
		//media_player.setLooping(true);
		media_player2.start();
		SoundActivity.sound.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(media_player!=null && media_player.isPlaying())
            		media_player.stop();
            	
            	if(!media_player2.isPlaying()){
            		sound.setBackgroundResource(R.drawable.sound_speaker);
            		sound_animation = (AnimationDrawable) sound.getBackground();
            		sound_animation.start();
            		media_player2.start();
            	}
            }
            
        });
        
        //set choices
        int[] button_ids = {R.id.first_option, R.id.second_option, R.id.third_option};
        for(int i = 0; i < 3; i++){
        	Button choice = (Button) findViewById(button_ids[i]);
        	choice.setText(app.getSpanishName(destiny,this.random_animals.get(i)));
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf"); 
        	choice.setTypeface(font);
        }
	}

	public void setSoundButton(){
		sound.setBackgroundResource(R.drawable.sound_speaker);
		sound_animation = (AnimationDrawable) sound.getBackground();
		
		this.media_player2.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer arg0) {
				SoundActivity.sound.setBackgroundResource(SoundActivity.context.getResources().getIdentifier("play", "drawable", SoundActivity.context.getPackageName()));
			}
        });
	}
	
	@Override
	public void verifyChoice(View v) {
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
		this.sound_animation.start();
	}
}
