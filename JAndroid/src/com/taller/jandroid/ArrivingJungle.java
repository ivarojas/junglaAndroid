package com.taller.jandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ArrivingJungle extends MyActivity implements OnClickListener, AnimationListener{
	
	int destiny;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        destiny = getIntent().getExtras().getInt("destiny");
        
        setContentView(R.layout.activity_arriving_jungle);
        
        if(destiny == 2){
        	findViewById(R.id.relative_arriv).setBackgroundResource(R.drawable.arriving_jungle2);
        }
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_arriv);
        next.setOnClickListener(this);
        next.setVisibility(View.INVISIBLE);
        
        ImageView indie = (ImageView)findViewById(R.id.indie);
    	indie.setVisibility(View.INVISIBLE);
        translate();
        
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
    
    public void onClick(View v) {
    	if(v.getId() == R.id.nextButton_arriv){
    		Intent presentation = new Intent(this, PresentationActivity.class);
    		presentation.putExtra("destiny", destiny);
    		this.startActivity(presentation);
    		finish();
    	}
    		
	}

	public void translate() {
		ImageView iv = (ImageView)findViewById(R.id.indieParachuting); 
    	
		Animation translate =  AnimationUtils.loadAnimation(this, R.anim.translate_arriving);
    	
    	translate.setAnimationListener(this);		 
		translate.setDuration(3000);
		translate.reset();
		translate.setFillAfter(true);		
		
		iv.clearAnimation();
		iv.startAnimation(translate);

	}
    
    public void onAnimationStart(Animation arg0) {
    }           
    public void onAnimationRepeat(Animation arg0) {
    }           
    public void onAnimationEnd(Animation arg0) {
    	ImageView iv = (ImageView)findViewById(R.id.indieParachuting);
    	iv.clearAnimation();
    	iv.setVisibility(View.GONE);
    	indieArrive();
    }

	public void indieArrive() {
		ImageView indie = (ImageView)findViewById(R.id.indie);
    	indie.setVisibility(View.VISIBLE);
    	ImageButton next = (ImageButton)findViewById(R.id.nextButton_arriv);
        next.setVisibility(View.VISIBLE);
    	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	Intent intro = new Intent(this,TranslatePlane.class);
    	this.startActivity(intro);
    }

	@Override
	public void verifyChoice(View v) {
		// TODO Auto-generated method stub
		
	}
    
}
