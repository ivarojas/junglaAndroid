package com.taller.jandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ShadowActivity extends Activity {

    private AlertDialog alertDialog;
	BroadcastReceiver broadcastReceiver = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		alertDialog = builder.create();
       
        setContentView(R.layout.activity_shadow);
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
    	if(button.getText().equals("Elefante")){
    		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tada);
    		mediaPlayer.start();
    		Intent intent = new Intent(this, AnimalInformationActivity.class);
    		intent.putExtra("animal", "elephant");
            startActivity(intent);
            finish();
    	}else{
    		// show it
    		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
    		mediaPlayer.start();
    		alertDialog.show();
    	}
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i=new Intent(this,PresentationActivity.class);
        startActivity(i);
    }

	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
    
}
