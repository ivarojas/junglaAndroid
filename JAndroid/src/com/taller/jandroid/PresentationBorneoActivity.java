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

public class PresentationBorneoActivity extends MyActivity {
	
	int destiny;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

       	setContentView(R.layout.activity_presentation_borneo);
        
        ImageButton obutton=(ImageButton)findViewById(R.id.orangutanbutton);
        ImageButton fbutton=(ImageButton)findViewById(R.id.frogbutton);
        ImageButton rbutton=(ImageButton)findViewById(R.id.rhinobutton);
        ImageButton pbutton=(ImageButton)findViewById(R.id.proboscisbutton);
        ImageButton hbutton=(ImageButton)findViewById(R.id.hornbillbutton);
        ImageButton lbutton=(ImageButton)findViewById(R.id.leopardbutton);
        ImageButton bbutton=(ImageButton)findViewById(R.id.bearbutton);
        ImageButton next=(ImageButton)findViewById(R.id.continuebutton);

        obutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","orangutan");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        
        fbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","frog");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        
        rbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","rhino");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        
        pbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","proboscis");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        
        hbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","hornbill");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        lbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","leopard");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        bbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","bear");
                i.putExtra("destiny", 2);
                startActivity(i);
                finish();
               }
             });
        next.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,ShadowActivity.class);
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
