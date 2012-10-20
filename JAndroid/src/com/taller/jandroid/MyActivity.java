package com.taller.jandroid;

import android.app.Activity;
import android.view.View;

abstract public class MyActivity extends Activity {
	
	public int drop_background;

	public int getDrop_background() {
		return drop_background;
	}

	public void setDrop_background(int drop_background) {
		this.drop_background = drop_background;
	}

	public abstract void verifyChoice(View v);

}
