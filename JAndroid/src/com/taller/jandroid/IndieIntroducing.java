package com.taller.jandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class IndieIntroducing extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_indie_introducing);
        
        ImageButton next = (ImageButton)findViewById(R.id.nextButton_intr);
        next.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		if(arg0.getId() == R.id.nextButton_intr){
			Intent chooseDestiny = new Intent(this, TranslatePlane.class);
			this.startActivity(chooseDestiny);
		}
	}
	
}
