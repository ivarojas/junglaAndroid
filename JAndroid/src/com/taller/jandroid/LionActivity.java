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

public class LionActivity extends Activity {
	static AlertDialog alertDialog;
	MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lion);
        ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        mp=MediaPlayer.create(LionActivity.this,R.raw.elephant);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Hola soy un elefante.\nAmo comer frutas y plantas.\nSoy el tercer animal terrestre más grande en la Tierra.")
        		.setCancelable(false)
        		.setPositiveButton("Cerrar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		 alertDialog = builder.create();
       
        
        
        arrow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(LionActivity.this,PresentationActivity.class);
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
				//Toast.makeText(LionActivity.this, "Este es un elefante. Eso po. asdasfasdafsafsqweqwrqweqwrqwrqwdctm", Toast.LENGTH_LONG).show();
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
