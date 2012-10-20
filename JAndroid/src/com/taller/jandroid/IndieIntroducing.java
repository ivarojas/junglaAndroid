package com.taller.jandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class IndieIntroducing extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_indie_introducing);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  android.os.Process.killProcess(android.os.Process.myPid()); 
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_intr);
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

        return super.onOptionsItemSelected(item);
    }
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_intr){
			Intent chooseDestiny = new Intent(this, TranslatePlane.class);
			this.startActivity(chooseDestiny);
		}
	}
	
}
