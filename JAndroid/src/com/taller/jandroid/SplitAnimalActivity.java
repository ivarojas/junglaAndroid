package com.taller.jandroid;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import persistance.Jungle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;
import animal.Animal;


public class SplitAnimalActivity extends MyActivity implements ViewFactory, View.OnClickListener {

    private List<Integer> animals_ids_up;
    private List<Integer> animals_ids_down;

    ImageSwitcher iSwitcherUp;
	ImageSwitcher iSwitcherDown;
	Button btnNextUp;
	Button btnPrevUp;
	Button btnNextDown;
	Button btnPrevDown;
	Button mix;
	MediaPlayer mediaPlayer;
	ImageButton next;
	int positionUp = 0;
	int positionDown = 0;
	Activity activity = this;
	List<Integer> right_animals = new ArrayList<Integer>();

	private Dialog dialog; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_split_animal);
        
       // imageSwitcher1
		iSwitcherUp = (ImageSwitcher) findViewById(R.id.imageSwitcherUp);
		iSwitcherUp.setFactory(this);
		
		iSwitcherDown = (ImageSwitcher) findViewById(R.id.imageSwitcherDown);
		iSwitcherDown.setFactory(this);
		
		iSwitcherUp.setImageResource(animals_ids_up.get(0));
		iSwitcherDown.setImageResource(animals_ids_down.get(0));

		next = (ImageButton)findViewById(R.id.nextButton_split);
		next.setVisibility(View.INVISIBLE);
		
    	setButtons();
    	
	    dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_manual_split_animal);
		dialog.setTitle("Instrucciones:");

		Button dialogButton = (Button) dialog.findViewById(R.id.button1);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(this);
		dialog.show();
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
    
    public void setPositionUpNext()
    {
    	positionUp++;
    	if(positionUp > animals_ids_up.size() -1)
    	{
    		positionUp = 0;
    	}
    }
    
    public void setPositionUpPrev()
    {
    	positionUp--;
    	if(positionUp < 0)
    	{
    		positionUp = animals_ids_up.size() - 1;
    	} 	
    }
    
    public void setPositionDownNext()
    {
    	positionDown++;
    	if(positionDown > animals_ids_down.size() -1)
    	{
    		positionDown = 0;
    	}
    }
    
    public void setPositionDownPrev()
    {
    	positionDown--;
    	if(positionDown < 0)
    	{
    		positionDown = animals_ids_down.size() - 1;
    	} 	
    }
    
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		/*iView.setLayoutParams(new 
				ImageSwitcher.LayoutParams(
						LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));*/
		//iView.setBackgroundColor(0xFF000000);
		return iView;
	}
    
    void setButtons(){
    	btnNextUp = (Button) findViewById(R.id.buttonNextUp);
    	btnNextUp.setOnClickListener(new View.OnClickListener() {
    		   public void onClick(View v) {
    			   iSwitcherUp.setInAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
    						android.R.anim.slide_in_left));
    			   iSwitcherUp.setOutAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
    						android.R.anim.slide_out_right));
    			   setPositionUpNext();
    			   iSwitcherUp.setImageResource(animals_ids_up.get(positionUp));
    			   //Toast.makeText(SplitedAnimalActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
    			}    
    	});  
        
    	btnPrevUp = (Button) findViewById(R.id.buttonPrevUp);
    	btnPrevUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	iSwitcherUp.setInAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
        				R.anim.slide_in_right));
        		iSwitcherUp.setOutAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
        				R.anim.slide_out_left));
            	
            	setPositionUpPrev();
            	iSwitcherUp.setImageResource(animals_ids_up.get(positionUp));
            	//Toast.makeText(SplitedAnimalActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
            }
        });
    	
    	btnNextDown = (Button) findViewById(R.id.buttonNextDown);
    	btnNextDown.setOnClickListener(new View.OnClickListener() {
    		   public void onClick(View v) {
    			   iSwitcherDown.setInAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
    						android.R.anim.slide_in_left));
    			   iSwitcherDown.setOutAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
    						android.R.anim.slide_out_right));
    			   setPositionDownNext();
    			   iSwitcherDown.setImageResource(animals_ids_down.get(positionDown)); 
    			   //Toast.makeText(SplitedAnimalActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
    		   }    
    	});  
        
    	btnPrevDown = (Button) findViewById(R.id.buttonPrevDown);
    	btnPrevDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	iSwitcherDown.setInAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
        				R.anim.slide_in_right));
        		iSwitcherDown.setOutAnimation(AnimationUtils.loadAnimation(SplitAnimalActivity.this,
        				R.anim.slide_out_left));
            	
            	setPositionDownPrev();
            	iSwitcherDown.setImageResource(animals_ids_down.get(positionDown));
            	//Toast.makeText(SplitedAnimalActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
            }
        });
    	
    	next = (ImageButton) findViewById(R.id.nextButton_split);
    	next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent nextIntent = new Intent(SplitAnimalActivity.this, ChooseAnimalsJungleActivity.class);
            	startActivity(nextIntent);
            	finish();
            }
        });
    	
    	mix = (Button) findViewById(R.id.buttonMix);
    	mix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mixAnimalParts();
            }
        });
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,SoundActivity.class);
        startActivity(i);    	
    }
    
    void mixAnimalParts(){
    	/*if(positionUp == positionDown){
    		if(!right_animals.contains(new Integer(positionUp))){
    			right_animals.add(new Integer(positionUp));
    			addRightImage();
    			Toast.makeText(SplitAnimalActivity.this, "Bien hecho !!!", Toast.LENGTH_SHORT).show();
    		}
    	}*/
    	if(positionUp == 0 && positionDown == 2){
    		next = (ImageButton)findViewById(R.id.nextButton_split);
    		next.setVisibility(View.VISIBLE);
    		mediaPlayer = MediaPlayer.create(this, R.raw.tada);
    		mediaPlayer.start();
    		Toast.makeText(SplitAnimalActivity.this, "Bien hecho !!!", Toast.LENGTH_SHORT).show();
    	}
    	else{
    		mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
    		mediaPlayer.start();
    		Toast.makeText(SplitAnimalActivity.this, "Mal hecho !!!", Toast.LENGTH_SHORT).show();
    	}	
    }
    
    void addRightImage(){
    	HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
    	LinearLayout topLinearLayout = (LinearLayout) scrollView.getChildAt(0);
    	final ImageView imageView = new ImageView (this);
        imageView.setImageResource(animals_ids_up.get(positionUp));
        topLinearLayout.addView(imageView);
    }

	public void onClick(View v) {
		dialog.dismiss();
	}


	@Override
	public void verifyChoice(View v) {

	}
	
	public void setSplitAnimals(){
		Jungle jungle = (Jungle) getApplication();
		
		List<Animal> random_animals = jungle.getAnimalsRandom(jungle.CONGO, 5);
		List<Integer> positions_up = jungle.getRandomRange(0, 4, 5);
		List<Integer> positions_down = jungle.getRandomRange(0, 4, 5);
		
		List<Integer> animals_ids_up = new ArrayList<Integer>(); 
		List<Integer> animals_ids_down = new ArrayList<Integer>();
		Hashtable<Integer,String> hash_ids_names = new Hashtable<Integer,String>();
		
		int k, image_id;
		String animal_name;
		int size = positions_up.size();
		for(int i = 0; i < size; i++){
			k = positions_up.get(i);
			animal_name = random_animals.get(k).getName();
			image_id = jungle.getImageId("split_", animal_name, "_up");
			animals_ids_up.add(image_id);
			hash_ids_names.put(image_id, animal_name);
		}
		
		for(int i = 0; i < size; i++){
			k = positions_down.get(i);
			animal_name = random_animals.get(k).getName();
			image_id = jungle.getImageId("split_", animal_name, "_down");
			animals_ids_down.add(image_id);
			hash_ids_names.put(image_id, animal_name);
		}
	}
}
