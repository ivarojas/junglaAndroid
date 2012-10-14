package com.taller.jandroid;

import com.taller.jandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PresentationActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);
        ImageButton mbutton=(ImageButton)findViewById(R.id.monkeybutton);
        ImageButton rbutton=(ImageButton)findViewById(R.id.rhinobutton);
        ImageButton lbutton=(ImageButton)findViewById(R.id.lionbutton);
        ImageButton gbutton=(ImageButton)findViewById(R.id.giraffebutton);
        ImageButton next= (ImageButton)findViewById(R.id.continuebutton);

        mbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,MonkeyActivity.class);
                startActivity(i);
                finish();
               }
             });
        
        rbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,RhinoActivity.class);
                startActivity(i);
                finish();
               }
             });
        
        lbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,LionActivity.class);
                startActivity(i);
                finish();
               }
             });
        
        gbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(PresentationActivity.this,GiraffeActivity.class);
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
    public void onBackPressed(){
    	super.onBackPressed();
        Intent translate = new Intent(this,ArrivingJungle.class);
        startActivity(translate);    	
    }

}
