package com.taller.jandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class PresentationBorneoActivity extends MyActivity {
	
	int destiny;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

       	setContentView(R.layout.activity_presentation_borneo);
        
       	TextView tx = (TextView)findViewById(R.id.title_borneo);
       	Typeface font = Typeface.createFromAsset(getAssets(), "fonts/HOLLY.ttf");
       	tx.setTypeface(font);
       	
        ImageButton obutton=(ImageButton)findViewById(R.id.orangutanbutton);
        ImageButton fbutton=(ImageButton)findViewById(R.id.frogbutton);
        ImageButton rbutton=(ImageButton)findViewById(R.id.rhinobutton);
        ImageButton pbutton=(ImageButton)findViewById(R.id.proboscisbutton);
        ImageButton hbutton=(ImageButton)findViewById(R.id.hornbillbutton);
        ImageButton lbutton=(ImageButton)findViewById(R.id.leopardbutton);
        ImageButton bbutton=(ImageButton)findViewById(R.id.bearbutton);
        ImageButton sbutton=(ImageButton)findViewById(R.id.snakebutton);
        ImageButton next=(ImageButton)findViewById(R.id.continuebutton);

        obutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","orangutan");
                startActivity(i);
                finish();
               }
             });
        
        fbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","frog");
                startActivity(i);
                finish();
               }
             });
        
        rbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","rhino");                
                startActivity(i);
                finish();
               }
             });
        
        pbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","proboscis");
                startActivity(i);
                finish();
               }
             });
        
        hbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","hornbill");
                startActivity(i);
                finish();
               }
             });
        lbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","leopard");
                startActivity(i);
                finish();
               }
             });
        bbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","bear");
                startActivity(i);
                finish();
               }
             });
        sbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationBorneoActivity.this,AnimalPresentationActivity.class);
                i.putExtra("animal","snake");
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
    public void onBackPressed(){
    	super.onBackPressed();
        Intent translate = new Intent(this,TranslatePlane.class);
        startActivity(translate);  
        finish();
    }


	@Override
	public void verifyChoice(View v) {
		// TODO Auto-generated method stub
		
	}
}
