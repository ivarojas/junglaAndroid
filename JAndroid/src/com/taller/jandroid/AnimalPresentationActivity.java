package com.taller.jandroid;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AnimalPresentationActivity extends MyActivity {
	private static AlertDialog alertDialog;
	private MediaPlayer mp;
	private int animal_sound,animal_img;
	private String animal_name, animal_info;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animal_presentation);      
        
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
        ImageButton arrow=(ImageButton)findViewById(R.id.back);
    	ImageButton sound=(ImageButton)findViewById(R.id.speaker);
        ImageButton info=(ImageButton)findViewById(R.id.bubble);
        ImageView imv=(ImageView)findViewById(R.id.imageView1);
        
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
                Intent i=new Intent(AnimalPresentationActivity.this,PresentationActivity.class);
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
		if(animal_name.equals("monkey")){
			animal_info="Hola soy un mono Bonobo.\nMe gusta comer frutas, pero también como nueces, semillas, hojas e insectos.";
			animal_img=R.drawable.monkey;
			animal_sound=R.raw.chimp;	
		}
		if(animal_name.equals("hippo")){
			animal_info="Hola soy un hipopótamo.\nPaso la mayor parte del día sumergido en el agua.\n";
			animal_img=R.drawable.hippo;
			animal_sound=R.raw.hippo;
		}
		if(animal_name.equals("elephant")){
			animal_info="Hola soy un elefante del Congo.\nAmo comer frutas y plantas.\nSoy el tercer animal terrestre más grande en la Tierra.";
			animal_img=R.drawable.elephant2;
			animal_sound=R.raw.elephant;
		}
		if(animal_name.equals("gorilla")){
			animal_info="Hola soy un gorila.\nPaso todo el día en busca de hojas, tallos y brotes de las plantas.";
			animal_img=R.drawable.gorilla;
			animal_sound=R.raw.gorilla;
		}
		if(animal_name.equals("okapi")){
			animal_info="Hola soy un okapi.\nSoy pariente de las jirafas.\nSoy vegetariano, me alimento de frutas y hojas";
			animal_img=R.drawable.okapi;
			animal_sound=R.raw.gorilla;
			findViewById(R.id.speaker).setVisibility(View.INVISIBLE);
		}
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i=new Intent(this,PresentationActivity.class);
        mp.stop();
        startActivity(i);
    }

	@Override
	public void verifyChoice(View v) {

	}
    
}
