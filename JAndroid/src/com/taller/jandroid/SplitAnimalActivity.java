package com.taller.jandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;
//import android.app.ActionBar.LayoutParams;
//import android.widget.Toast;


public class SplitAnimalActivity extends Activity implements ViewFactory {

    private Integer[] mImageUpIds = {
            R.drawable.split_gorilla_up,
            R.drawable.split_elephant_up,
            R.drawable.split_hippo_up,
    };
    
    private Integer[] mImageDownIds = {
            R.drawable.split_gorilla_down, 
            R.drawable.split_elephant_down,
            R.drawable.split_hippo_down,
    };
    
	ImageSwitcher iSwitcherUp;
	ImageSwitcher iSwitcherDown;
	Button btnNextUp;
	Button btnPrevUp;
	Button btnNextDown;
	Button btnPrevDown;
	ImageButton next;
	int positionUp = 0;
	int positionDown = 0;
	Activity activity = this;
	
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
		
		iSwitcherUp.setImageResource(mImageUpIds[0]);
		iSwitcherDown.setImageResource(mImageDownIds[0]);

    	setButtons();
    }
    
    public void setPositionUpNext()
    {
    	positionUp++;
    	if(positionUp > mImageUpIds.length -1)
    	{
    		positionUp = 0;
    	}
    }
    
    public void setPositionUpPrev()
    {
    	positionUp--;
    	if(positionUp < 0)
    	{
    		positionUp = mImageUpIds.length - 1;
    	} 	
    }
    
    public void setPositionDownNext()
    {
    	positionDown++;
    	if(positionDown > mImageDownIds.length -1)
    	{
    		positionDown = 0;
    	}
    }
    
    public void setPositionDownPrev()
    {
    	positionDown--;
    	if(positionDown < 0)
    	{
    		positionDown = mImageDownIds.length - 1;
    	} 	
    }
    
	public View makeView() {
		// TODO Auto-generated method stub
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
    			   iSwitcherUp.setImageResource(mImageUpIds[positionUp]);
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
            	iSwitcherUp.setImageResource(mImageUpIds[positionUp]);
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
    			   iSwitcherDown.setImageResource(mImageDownIds[positionDown]); 
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
            	iSwitcherDown.setImageResource(mImageDownIds[positionDown]);
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
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,AnimalInformationActivity.class);
        i.putExtra("animal", "gorilla");
        startActivity(i);    	
    }

}
