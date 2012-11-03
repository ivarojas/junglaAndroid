package com.taller.jandroid;


import java.util.List;

import persistance.Jungle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animal_presentation);      
        
        destiny = getIntent().getExtras().getInt("destiny");
        Log.i("destiny", destiny+"");
        
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
    	ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        ImageView imv=(ImageView)findViewById(R.id.imageView1);

        dialog1 = new Dialog(this);
		dialog1.setContentView(R.layout.dialog_info_animal);
		
		Button dialogButton1 = (Button) dialog1.findViewById(R.id.dialogButtonOK);
		
        animal_name=getIntent().getStringExtra("animal");
        
        setAnimalInfo();
        
        mp=MediaPlayer.create(AnimalPresentationActivity.this,animal_sound);
        imv.setBackgroundResource(animal_img);
           	
        
        arrow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = null;
            	if(destiny == 1){
            		i=new Intent(AnimalPresentationActivity.this,PresentationActivity.class);
            	}
            	else{
            		i=new Intent(AnimalPresentationActivity.this,PresentationBorneoActivity.class);
            	}
                i.putExtra("destiny", destiny);
                mp.stop();
                startActivity(i);
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
				Jungle app = (Jungle)getApplicationContext();
				Animal anim = null;
				if(destiny==1)
					anim = app.getSingleAnimal(app.CONGO, animal_name);
				else
					anim = app.getSingleAnimal(app.BORNEO, animal_name);	
				dialog1.setTitle(anim.getSpanish()+":");
				
				if(anim!=null)
					Log.i("animal", anim.getSpanish());
				List<String> food = anim.getFood();
				List<String> ambient = anim.getAmbient();
				View vi = null;
				if(!ambient.contains("air")){
					vi = dialog1.findViewById(R.id.airLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("tree")){
					vi = dialog1.findViewById(R.id.treeLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("ground")){
					vi = dialog1.findViewById(R.id.groundLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!ambient.contains("river")){
					vi = dialog1.findViewById(R.id.riverlayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				
				if(!food.contains("fruit")){
					vi = dialog1.findViewById(R.id.fruitLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("insect")){
					vi = dialog1.findViewById(R.id.insectLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("plant")){
					vi = dialog1.findViewById(R.id.plantLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				if(!food.contains("meat")){
					vi = dialog1.findViewById(R.id.meatLayout);
					((ViewManager)vi.getParent()).removeView(vi);
				}
				
				
				dialog1.show();
			}
		});
		
		dialogButton1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				dialog1.dismiss();
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
	
	public void setAnimalInfo(){
		
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
		}
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent i = null;
    	if(destiny == 1){
    		i=new Intent(this,PresentationActivity.class);
    	}
    	else{
    		i=new Intent(this,PresentationBorneoActivity.class);
    	}
        i.putExtra("destiny", destiny);
        mp.stop();
        startActivity(i);
    }

	@Override
	public void verifyChoice(View v) {

	}
    
}
