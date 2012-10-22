package com.taller.jandroid;

import com.taller.jandroid.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PresentationActivity extends MyActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_presentation);
        
        ImageButton mbutton=(ImageButton)findViewById(R.id.monkeybutton);
        ImageButton rbutton=(ImageButton)findViewById(R.id.rhinobutton);
        ImageButton lbutton=(ImageButton)findViewById(R.id.lionbutton);
        ImageButton gbutton=(ImageButton)findViewById(R.id.giraffebutton);
        ImageButton obutton=(ImageButton)findViewById(R.id.okapibutton);
        ImageButton next=(ImageButton)findViewById(R.id.continuebutton);

        mbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","monkey");
                startActivity(i);
                finish();
               }
             });
        
        rbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","hippo");
                startActivity(i);
                finish();
               }
             });
        
        lbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","elephant");
                startActivity(i);
                finish();
               }
             });
        
        gbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","gorilla");
                startActivity(i);
                finish();
               }
             });
        
        obutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","okapi");
                startActivity(i);
                finish();
               }
             });
        
        next.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,ShadowActivity.class);
                startActivity(i);
                finish();
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
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent translate = new Intent(this,TranslatePlane.class);
        startActivity(translate);    	
    }


	@Override
	public void verifyChoice(View v) {
		// TODO Auto-generated method stub
		
	}
}
