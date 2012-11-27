package com.taller.jandroid;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SayGoodbyeActivity extends MyActivity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_say_goodbye);
                
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_sayGB);
        next.setOnClickListener(this);
    }
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_sayGB){
			Intent next = new Intent(this, GoodbyeActivity.class);
			next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			this.startActivity(next);
			finish();
		}	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,ChooseAnimalsJungleActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        startActivity(i);    	
        finish();
    }

	@Override
	public void verifyChoice(View v) {

	}
    
}
