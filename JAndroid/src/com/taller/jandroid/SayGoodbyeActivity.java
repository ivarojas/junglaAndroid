package com.taller.jandroid;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SayGoodbyeActivity extends MyActivity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_say_goodbye);
                
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_sayGB);
        next.setOnClickListener(this);
    }
    
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_sayGB){
			Intent next = new Intent(this, GoodbyeActivity.class);
			this.startActivity(next);
			finish();
		}	
	}
	
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,ChooseAnimalsJungleActivity.class);
        startActivity(i);    	
    }

	@Override
	public void verifyChoice(View v) {

	}
    
}
