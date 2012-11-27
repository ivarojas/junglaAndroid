package com.taller.jandroid;


import java.util.List;

import myviews.MyTextView;
import persistance.Jungle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import animal.Animal;
import animal.AnimalSet;

public class AnimalPresentationActivity extends MyActivity {
	private MediaPlayer mp;
	private int animal_sound,animal_img;
	private String animal_name, animal_info;
	int destiny;
	WebView mWebView;
	private Dialog dialog1;
	private AnimalSet animal_set;
	private String animal_url;
	private Jungle app;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animal_presentation);      
        
        app = (Jungle)getApplicationContext();
        destiny = app.getDestiny();
        
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
    	ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        ImageButton url=(ImageButton)findViewById(R.id.urlButton);
        ImageView imv=(ImageView)findViewById(R.id.imageView1);

        dialog1 = new Dialog(this);
		dialog1.setContentView(R.layout.dialog_info_animal);
		
		Button dialogButton1 = (Button) dialog1.findViewById(R.id.dialogButtonOK);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf");  
		dialogButton1.setTypeface(font);
		
        animal_name=getIntent().getStringExtra("animal");
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Para ver más información del animal debes estar conectado a internet")
           .setCancelable(false)
           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
            	   dialog.cancel();
               }
           })
           //Set your icon here
           .setTitle("No estás conectado!")
           .setIcon(R.drawable.alert);
        final AlertDialog alert = builder.create();
        
        
        setAnimalInfo();
        
        mp=MediaPlayer.create(AnimalPresentationActivity.this,animal_sound);
        imv.setBackgroundDrawable(decodeDrawable(animal_img,true));
           	
        
        arrow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = null;
            	if(destiny == 0){
            		i=new Intent(AnimalPresentationActivity.this,PresentationActivity.class);
            	}
            	else{
            		i=new Intent(AnimalPresentationActivity.this,PresentationBorneoActivity.class);
            	}
            	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
                mp.stop();
                startActivity(i);
                recycle();
                finish();                
            }  
            });
        
		sound.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(!mp.isPlaying())
					mp.start();
			}
		});
		
		info.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Animal anim = null;
				if(destiny == app.CONGO)
					anim = app.getSingleAnimal(app.CONGO, animal_name);
				else
					anim = app.getSingleAnimal(app.BORNEO, animal_name);	
				dialog1.setTitle(anim.getSpanish()+":");
				
				List<String> food = anim.getFood();
				List<String> ambient = anim.getAmbient();
				animal_info = app.getSingleAnimal(destiny, animal_name).getDescription();
				
				TextView tx = (TextView)dialog1.findViewById(R.id.animal_info);
				tx.setText(animal_info);				
				
				View vi = null;
				if(!ambient.contains("air")){
					vi = dialog1.findViewById(R.id.airLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("tree")){
					vi = dialog1.findViewById(R.id.treeLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("ground")){
					vi = dialog1.findViewById(R.id.groundLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("river")){
					vi = dialog1.findViewById(R.id.riverlayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				
				if(!food.contains("fruit")){
					vi = dialog1.findViewById(R.id.fruitLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("insect")){
					vi = dialog1.findViewById(R.id.insectLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("plant")){
					vi = dialog1.findViewById(R.id.plantLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("meat")){
					vi = dialog1.findViewById(R.id.meatLayout);
					if(vi!=null)
						((ViewManager)vi.getParent()).removeView(vi);
				}
				
				
				dialog1.show();
			}
		});
		url.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				if(!isInternetOn()){
					alert.show();
				}else{
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(animal_url));
					startActivity(browserIntent);
				}
			}
		});
		
		dialogButton1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				dialog1.dismiss();
			}
		});
	}
	
	public void setAnimalInfo(){
		
		MyTextView tx = (MyTextView)findViewById(R.id.animalName);
		tx.setText(app.getSpanishName(destiny, animal_name));
				
		if(animal_name.equals("okapi") || animal_name.equals("proboscis")){
			animal_info="Hola soy un okapi.\nSoy pariente de las jirafas.\nSoy vegetariano, me alimento de frutas y hojas";
			if(animal_name.equals("proboscis"))
				animal_img = R.drawable.proboscis;
			else
				animal_img = R.drawable.okapi;
			animal_sound = R.raw.sound_bear;
			findViewById(R.id.speaker).setVisibility(View.INVISIBLE);
		}else{
			animal_set = new AnimalSet(this);
			animal_img = animal_set.getDrawableAnimalId(animal_name);
			animal_sound = animal_set.getSoundAnimalId(animal_name);
			
			if(animal_name.equals("snake")){
				ImageButton url=(ImageButton)findViewById(R.id.urlButton);
				url.setVisibility(View.INVISIBLE);
			}
		}
		
		if(destiny == app.CONGO)
			animal_url = app.getSingleAnimal(app.CONGO, animal_name).getUrl();
		else
			animal_url = app.getSingleAnimal(app.BORNEO, animal_name).getUrl();
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent i = null;
    	if(destiny == 0){
    		i=new Intent(this,PresentationActivity.class);
    	}
    	else{
    		i=new Intent(this,PresentationBorneoActivity.class);
    	}
    	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        mp.stop();
        startActivity(i);
        recycle();
        finish();
    }

	@Override
	public void verifyChoice(View v) {

	}
	public void recycle(){
		ImageView image_view = (ImageView) findViewById(R.id.imageView1);
		((ViewManager)image_view.getParent()).removeView(image_view);
		ImageButton sound=(ImageButton)findViewById(R.id.speaker);
		((ViewManager)sound.getParent()).removeView(sound);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        ((ViewManager)info.getParent()).removeView(info);
        ImageButton url=(ImageButton)findViewById(R.id.urlButton);
        ((ViewManager)url.getParent()).removeView(url);
	}
    
}
 