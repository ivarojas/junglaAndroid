package com.taller.jandroid;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import persistance.Jungle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;
import animal.Animal;


public class SplitAnimalActivity extends ChallengeActivity implements ViewFactory, View.OnClickListener {

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
	private View toast_layout;
	private Toast toast;
	private Jungle app;
	private Hashtable<Integer, String> hash_ids_names;
	private int destiny; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_split_animal);
        
        app = (Jungle)getApplicationContext();
        
		if (app.getState() == app.CHOOSE_CHALLENGE){
			int dest = (int) Math.round(Math.random());
			app.setDestiny(dest);
		}
		destiny = app.getDestiny();
        
       // imageSwitcher1
		iSwitcherUp = (ImageSwitcher) findViewById(R.id.imageSwitcherUp);
		iSwitcherUp.setFactory(this);
		
		iSwitcherDown = (ImageSwitcher) findViewById(R.id.imageSwitcherDown);
		iSwitcherDown.setFactory(this);
		
		this.setSplitAnimals();
		this.setToast();
		
    	String animal_up_name = hash_ids_names.get(animals_ids_up.get(0));
    	String animal_down_name = hash_ids_names.get(animals_ids_down.get(0));
    	
    	if(animal_up_name.equals(animal_down_name)){
    		int id = animals_ids_up.get(0);
    		animals_ids_up.set(0, animals_ids_up.get(animals_ids_up.size()-1));
    		animals_ids_up.set(animals_ids_up.size()-1, id);
    	}
		
		iSwitcherUp.setImageResource(animals_ids_up.get(0));
		iSwitcherDown.setImageResource(animals_ids_down.get(0));

		next = (ImageButton)findViewById(R.id.nextButton_split);
		next.setVisibility(View.INVISIBLE);
		
    	setButtons();
    	
    	//setting final dialog
        this.setupDialogEnd();
    	
	    dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_manual_split_animal);
		dialog.setTitle("Instrucciones:");

		Button dialogButton = (Button) dialog.findViewById(R.id.button1);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(this);
		dialog.show();
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
            	toast_layout.setVisibility(View.INVISIBLE);
            	if (app.getState() != app.CHOOSE_CHALLENGE){
            		Intent nextIntent = new Intent(SplitAnimalActivity.this, SoundActivity.class);
            		nextIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
            		startActivity(nextIntent);
            		recycle();
            		finish();
            	}else{
            		recycle();
            		goMenuChallenges();
            	}
            }
        });
    	
    	mix = (Button) findViewById(R.id.buttonMix);
    	Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf");
    	mix.setTypeface(font);
    	mix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mixAnimalParts();
            }
        });
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	if (app.getState() != app.CHOOSE_CHALLENGE){
	        Intent i = new Intent(this,FeedingActivity.class);
	        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	        startActivity(i);
	        recycle();
	        finish();
		}else{
			recycle();
			this.goMenuChallenges();
		}
    }
    
    void mixAnimalParts(){
    	String animal_up_name = hash_ids_names.get(animals_ids_up.get(positionUp));
    	String animal_down_name = hash_ids_names.get(animals_ids_down.get(positionDown));
    	
    	if(animal_up_name.equals(animal_down_name)){
    		//next = (ImageButton)findViewById(R.id.nextButton_split);
    		//next.setVisibility(View.VISIBLE);
    		mediaPlayer = MediaPlayer.create(this, R.raw.tada);
    		mediaPlayer.start();
    		//Toast.makeText(SplitAnimalActivity.this, "Bien hecho !!!", Toast.LENGTH_SHORT).show();
    		
    		//showing animal and name
    		ImageView image = (ImageView) toast_layout.findViewById(R.id.animal_image);
    		String animal_name = hash_ids_names.get(animals_ids_up.get(positionUp));
    		int image_id = app.getImageId("", animal_name, "");
    		image.setBackgroundDrawable(decodeDrawable(image_id,true));
    		TextView text = (TextView) toast_layout.findViewById(R.id.animal_name);
    		
    		Jungle app = (Jungle)getApplicationContext();
    		
    		text.setText(app.getSpanishName(app.getDestiny(),animal_name));
    		toast.setView(toast_layout);
    		//toast.show();
    		
    		String return_message = "Has ganado";
			this.display.setText(return_message);
			this.finalDialog.show();
    	}
    	else{
    		mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
    		mediaPlayer.start();
    		Toast.makeText(SplitAnimalActivity.this, "Mal hecho !!!", Toast.LENGTH_SHORT).show();
    	}	
    }

	public void onClick(View v) {
		//dialog.dismiss();
		switch(v.getId()){
			case R.id.again:
				finalDialog.dismiss();
				Intent self = new Intent(this, SplitAnimalActivity.class);
				self.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	            startActivity(self);
	            finish();
				break;
			case R.id.nope:
				finalDialog.dismiss();
				//Para cambiar la redireccion, cambiar la clase destino del intent
				toast_layout.setVisibility(View.INVISIBLE);
            	if (app.getState() != app.CHOOSE_CHALLENGE){
            		Intent nextIntent = new Intent(SplitAnimalActivity.this, SoundActivity.class);
            		nextIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
            		startActivity(nextIntent);
            		recycle();
            		finish();
            	}else{
            		recycle();
            		goMenuChallenges();
            	}
				break;
			case R.id.button1:
				dialog.dismiss();
		}
	}


	@Override
	public void verifyChoice(View v) {

	}
	
	public void setSplitAnimals(){
		
		List<Animal> random_animals = app.getAnimalsRandom(destiny, 5);
		List<Integer> positions_up = app.getRandomRange(0, 4, 5);
		List<Integer> positions_down = app.getRandomRange(0, 4, 5);
		
		animals_ids_up = new ArrayList<Integer>(); 
		animals_ids_down = new ArrayList<Integer>();
		hash_ids_names = new Hashtable<Integer,String>();
		
		int k, image_id;
		String animal_name;
		int size = positions_up.size();
		for(int i = 0; i < size; i++){
			k = positions_up.get(i);
			animal_name = random_animals.get(k).getName();
			image_id = app.getImageId("split_", animal_name, "_up");
			animals_ids_up.add(image_id);
			hash_ids_names.put(image_id, animal_name);
		}
		
		for(int i = 0; i < size; i++){
			k = positions_down.get(i);
			animal_name = random_animals.get(k).getName();
			image_id = app.getImageId("split_", animal_name, "_down");
			animals_ids_down.add(image_id);
			hash_ids_names.put(image_id, animal_name);
		}
	}
	
	private void setToast(){
		LayoutInflater inflater = getLayoutInflater();
		toast_layout = inflater.inflate(
							R.layout.split_animal_mix,
		                    (ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) toast_layout.findViewById(R.id.animal_name);
		text.setText("This is a custom toast");
		text.setTextColor(Color.BLACK);
		text.setTextSize(20);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf"); 
		text.setTypeface(font);

		toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
	}
	
	public void recycle(){
		((ViewManager)iSwitcherUp.getParent()).removeView(iSwitcherUp);
		((ViewManager)iSwitcherDown.getParent()).removeView(iSwitcherDown);
		((ViewManager)btnNextUp.getParent()).removeView(btnNextUp);
		((ViewManager)btnPrevDown.getParent()).removeView(btnPrevDown);
		((ViewManager)btnNextDown.getParent()).removeView(btnNextDown);
		((ViewManager)btnPrevUp.getParent()).removeView(btnPrevUp);
		((ViewManager)mix.getParent()).removeView(mix);
	}
}
