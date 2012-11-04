package com.taller.jandroid;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import animal.AnimalSet;

public class ShadowActivity extends MyActivity {

    private AlertDialog alertDialog;
	private AnimalSet animal_set;
	private List<String> random_animals;
	private String correct_answer;
    
	MediaPlayer mediaPlayer;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
  
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		alertDialog = builder.create();
       
        setContentView(R.layout.activity_shadow);
        
        //setting shadow animal and choices to show
        this.setShadowAnimal();
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
    
    public void verifyAnswer(View view){
    	Button button = (Button) findViewById(view.getId());
    	if(button.getText().equals(this.correct_answer)){
    		if(mediaPlayer!=null && mediaPlayer.isPlaying())
    			mediaPlayer.stop();
    		mediaPlayer = MediaPlayer.create(this, R.raw.tada);
    		mediaPlayer.start();
    		Intent intent = new Intent(this, AnimalInformationActivity.class);
    		intent.putExtra("animal", "elephant");
            startActivity(intent);
            finish();
    	}else{
    		if(mediaPlayer!=null && mediaPlayer.isPlaying())
    			mediaPlayer.stop();
    		mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
    		mediaPlayer.start();
    		alertDialog.show();
    	}
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i=new Intent(this,PresentationActivity.class);
        i.putExtra("destiny", 1);
        startActivity(i);
    }

	@Override
	public void verifyChoice(View v) {
		
	}
	
	public void setShadowAnimal(){
		//setting shadow animal and choices to show
        animal_set = new AnimalSet(this);
        random_animals = animal_set.getAnimalsRandom("congo", 3);
        int rand_n = (int)(Math.random() * 3);
        correct_answer = random_animals.get(rand_n);
        int shadow_animal_id = animal_set.getShadowAnimalId(random_animals.get(rand_n));
        ImageView image_view = (ImageView) findViewById(R.id.shadow);
        
        image_view.setBackgroundDrawable(decodeDrawable(shadow_animal_id));
        
        //set choices
        int[] button_ids = {R.id.first_option, R.id.second_option, R.id.third_option};
        for(int i = 0; i < 3; i++){
        	Button choice = (Button) findViewById(button_ids[i]);
        	choice.setText(this.random_animals.get(i));
        }
	}
}
