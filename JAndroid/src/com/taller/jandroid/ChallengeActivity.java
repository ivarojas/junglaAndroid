package com.taller.jandroid;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ChallengeActivity extends MyActivity implements OnClickListener{

	protected Dialog finalDialog;
	protected TextView display;

	@Override
	public void verifyChoice(View v) {
		
	}
	
	public void setupDialogEnd(){
    	finalDialog = new Dialog(this);
    	finalDialog.setContentView(R.layout.dialog_retry);
    	
    	Button again = (Button)finalDialog.findViewById(R.id.again);
    	Button nope = (Button)finalDialog.findViewById(R.id.nope);
    	
    	again.setOnClickListener(this);
    	nope.setOnClickListener(this);

    	display = (TextView)finalDialog.findViewById(R.id.msg_displayed);    	
    }

	public void onClick(View v) {
		
	}
}
