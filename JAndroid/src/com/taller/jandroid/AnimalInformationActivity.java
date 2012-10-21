package com.taller.jandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalInformationActivity extends Activity implements OnCompletionListener, OnClickListener {

    private static int animal_sound = R.raw.elephant;
	private String animal;
	MediaPlayer mediaPlayer;
	BroadcastReceiver broadcastReceiver = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
               
        setContentView(R.layout.activity_animal_information);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        animal = getIntent().getStringExtra("animal");
        setAnimalInfoAndImg();
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_info);
        next.setOnClickListener(this);
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

	public void setAnimalInfoAndImg() {
		ImageView animal_img = (ImageView)findViewById(R.id.animal_img);
        TextView animal_txt = (TextView)findViewById(R.id.animal_txt);
        
        if(animal.equals("elephant")){
        	animal_sound = R.raw.elephant;
        	
        }else if(animal.equals("gorilla")){
        	animal_txt.setText("Hola soy un gorila. Paso todo el día en busca de hojas, tallos y brotes de las plantas.");
        	animal_img.setImageResource(R.drawable.gorilla);
        	animal_sound = R.raw.gorilla;
        }
	}
        
    public void playSound(View view){
    	mediaPlayer = MediaPlayer.create(this, animal_sound);
    	mediaPlayer.start();
    	mediaPlayer.setOnCompletionListener(this);
    }

	public void onCompletion(MediaPlayer mp) {
		mp.stop();
	}

	public void onClick(View v) {
		if(v.getId() == R.id.nextButton_info){
			Intent next = null;
			if(animal.equals("elephant")){
				next = new Intent(this, FeedingActivity.class);
			}else if(animal.equals("gorilla")){
				next = new Intent(this, SplitAnimalActivity.class);
			}
			if(mediaPlayer != null)
				mediaPlayer.stop();
			this.startActivity(next);
			finish();
		}
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent i = null;
    	if(animal.equals("elephant"))
    		i = new Intent(this,ShadowActivity.class);
    	else if(animal.equals("gorilla"))
    		i = new Intent(this,SoundActivity.class);
		if(mediaPlayer != null)
			mediaPlayer.stop();
        startActivity(i);
    }
    
	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
	
}
