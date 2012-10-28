package com.taller.jandroid;

import java.util.List;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import animal.AnimalSet;

public class SoundActivity extends MyActivity{

	private AlertDialog alertDialog;
	MediaPlayer media_player;
	private AnimalSet animal_set;
	private List<String> random_animals;
	private String correct_answer;
	private ImageButton sound;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_sound);
		
		sound = (ImageButton)findViewById(R.id.soundChallenge);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });
        
        setSoundAnimal();
        
		// create alert dialog
		alertDialog = builder.create();
		
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
    	Button button = (Button) findViewById(view.getId());
    	if(button.getText().equals(this.correct_answer)){
    		if(media_player!=null && media_player.isPlaying())
    			media_player.stop();
    		media_player = MediaPlayer.create(SoundActivity.this, R.raw.tada);
    		media_player.start();
    		Intent intent = new Intent(this, AnimalInformationActivity.class);
    		intent.putExtra("animal", "gorilla");
    		media_player.stop();
            startActivity(intent);
            finish();
    	}else{
    		// show it
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
    	media_player.stop();
        Intent i = new Intent(this,FeedingActivity.class);
        startActivity(i);    	
    }

	public void setSoundAnimal(){
		//setting sound animal and choices to show
        animal_set = new AnimalSet(this);
        random_animals = animal_set.getAnimalsRandom("congo", 3);
        int rand_n = (int)(Math.random() * 3);
        correct_answer = random_animals.get(rand_n);
        int sound_animal_id = animal_set.getSoundAnimalId(random_animals.get(rand_n));
        
        //Setting sound
		media_player = MediaPlayer.create(SoundActivity.this, R.raw.sound_gorilla);
		media_player.setLooping(true);
		media_player.start();
		this.sound.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	/*if(!mediaPlayer.isPlaying())
            		mediaPlayer.start();*/
            }
            
        });
        
        //set choices
        int[] button_ids = {R.id.first_option, R.id.second_option, R.id.third_option};
        for(int i = 0; i < 3; i++){
        	Button choice = (Button) findViewById(button_ids[i]);
        	choice.setText(this.random_animals.get(i));
        }
	}

	@Override
	public void verifyChoice(View v) {
		// TODO Auto-generated method stub
		
	}
}
