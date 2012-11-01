package com.taller.jandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends MyActivity implements OnClickListener{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        Button start = (Button)findViewById(R.id.start);
        Button exit = (Button)findViewById(R.id.exit);
        
        start.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    
	public void onClick(View arg0) {
		
		switch(arg0.getId()){
		case R.id.start: 
			Intent start_game = new Intent(this,IndieIntroducing.class);
	    	this.startActivity(start_game);
	    	break;
		case R.id.exit: 
			android.os.Process.killProcess(android.os.Process.myPid()); 
			break;
		}
		
	}

	@Override
	public void verifyChoice(View v) {
		
	}
}
