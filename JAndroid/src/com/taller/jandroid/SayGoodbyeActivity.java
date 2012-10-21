package com.taller.jandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SayGoodbyeActivity extends Activity implements OnClickListener {

	BroadcastReceiver broadcastReceiver = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_say_goodbye);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_sayGB);
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
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_sayGB){
			Intent next = new Intent(this, GoodbyeActivity.class);
			this.startActivity(next);
			finish();
		}	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,ChooseAnimalsJungleActivity.class);
        startActivity(i);    	
    }
    
	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
    
}
