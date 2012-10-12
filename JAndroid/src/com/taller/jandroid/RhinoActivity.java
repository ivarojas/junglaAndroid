package com.taller.jandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class RhinoActivity extends Activity {
	static AlertDialog alertDialog;
	MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rhino);
        ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        mp=MediaPlayer.create(RhinoActivity.this,R.raw.hippo);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Hola soy un hipopótamo.\nPaso la mayor parte del día sumergido en el agua.\n"
	+ "Me alimento de plantas.")
        		.setCancelable(false)
        		.setPositiveButton("Cerrar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		 alertDialog = builder.create();
        
        arrow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(RhinoActivity.this,PresentationActivity.class);
                mp.stop();
                startActivity(i);
                }
            });
        
		sound.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				mp.start();
			}
		});
		
		info.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				alertDialog.show();
			}
		});
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i=new Intent(this,PresentationActivity.class);
        mp.stop();
        startActivity(i);
    }
}
