package com.taller.jandroid;

import persistance.Jungle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class IndieIntroducing extends MyActivity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_indie_introducing);
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_intr);
        next.setOnClickListener(this);
        
        Jungle jungle = (Jungle)this.getApplication();
        jungle.setState(jungle.ADVENTURE);
    }
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_intr){
			Intent chooseDestiny = new Intent(this, TranslatePlane.class);
			chooseDestiny.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			this.startActivity(chooseDestiny);
			finish();
		}
	}

	@Override
	public void verifyChoice(View v) {
	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        startActivity(i);
        finish();
    }
}
