package com.taller.jandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class PresentationActivity extends MyActivity {
	
	int destiny;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
       
      	setContentView(R.layout.activity_presentation);
        
      	TextView tx = (TextView)findViewById(R.id.title_congo);
       	Typeface font = Typeface.createFromAsset(getAssets(), "fonts/HOLLY.ttf");
       	tx.setTypeface(font);
      	
        ImageButton bbutton=(ImageButton)findViewById(R.id.bonobobutton);
        ImageButton hbutton=(ImageButton)findViewById(R.id.hippobutton);
        ImageButton ebutton=(ImageButton)findViewById(R.id.elephantbutton);
        ImageButton gbutton=(ImageButton)findViewById(R.id.gorillabutton);
        ImageButton obutton=(ImageButton)findViewById(R.id.okapibutton);
        ImageButton next=(ImageButton)findViewById(R.id.continuebutton);

        bbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","bonobo");
                startActivity(i);
                finish();
               }
             });
        
        hbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","hippo");
                startActivity(i);
                finish();
               }
             });
        
        ebutton.setOnClickListener(new OnClickListener() {
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
        finish();
    }


	@Override
	public void verifyChoice(View v) {

	}
}
