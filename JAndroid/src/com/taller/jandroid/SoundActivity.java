package com.taller.jandroid;

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

public class SoundActivity extends MyActivity{

	private AlertDialog alertDialog;
	MediaPlayer mediaPlayer;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_sound);
		
		ImageButton sound1 = (ImageButton)findViewById(R.id.soundChallenge);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		alertDialog = builder.create();
		
		mediaPlayer = MediaPlayer.create(SoundActivity.this, R.raw.gorilla);
		mediaPlayer.start();
		
		sound1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(!mediaPlayer.isPlaying())
            		mediaPlayer.start();
            }
            
        });
		
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
    	if(button.getText().equals("Gorila")){
    		mediaPlayer.stop();
    		mediaPlayer = MediaPlayer.create(SoundActivity.this, R.raw.tada);
    		mediaPlayer.start();
    		Intent intent = new Intent(this, AnimalInformationActivity.class);
    		intent.putExtra("animal", "gorilla");
    		mediaPlayer.stop();
            startActivity(intent);
            finish();
    	}else{
    		// show it
    		mediaPlayer = MediaPlayer.create(SoundActivity.this, R.raw.failbeep);
    		mediaPlayer.start();
    		alertDialog.show();
    	}
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	mediaPlayer.stop();
        Intent i = new Intent(this,FeedingActivity.class);
        startActivity(i);    	
    }

	@Override
	public void verifyChoice(View v) {
		// TODO Auto-generated method stub
		
	}

}
