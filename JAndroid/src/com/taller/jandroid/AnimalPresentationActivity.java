package com.taller.jandroid;


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
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import animal.AnimalSet;

public class AnimalPresentationActivity extends MyActivity {
	private static AlertDialog alertDialog;
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
        
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
    	ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        ImageView imv=(ImageView)findViewById(R.id.imageView1);

        dialog1 = new Dialog(this);
		dialog1.setContentView(R.layout.dialog_info_animal);
		dialog1.setTitle("Información:");
		
		Button dialogButton1 = (Button) dialog1.findViewById(R.id.dialogButtonOK);
		
        animal_name=getIntent().getStringExtra("animal");
        
        setAnimalInfo();
        
        mp=MediaPlayer.create(AnimalPresentationActivity.this,animal_sound);
        imv.setBackgroundResource(animal_img);
        
    	builder.setMessage(animal_info)
		.setCancelable(false)
		.setPositiveButton("Cerrar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {}
		});
    	
    	alertDialog = builder.create();
        
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
//				alertDialog.show();
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
			animal_img = animal_set.getDrawableAnimalId(animal_name);
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
