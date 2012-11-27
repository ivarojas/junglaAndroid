package com.taller.jandroid;

import persistance.Jungle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class IndieIntroducing extends MyActivity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_indie_introducing);
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_intr);
        next.setOnClickListener(this);
        
        Jungle jungle = (Jungle)this.getApplication();
        jungle.setState(jungle.ADVENTURE);
    }
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_intr){
			Intent chooseDestiny = new Intent(this, TranslatePlane.class);
			this.startActivity(chooseDestiny);
		}
	}

	@Override
	public void verifyChoice(View v) {
	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
