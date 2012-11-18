package com.taller.jandroid;

import java.util.ArrayList;
import java.util.List;

import persistance.Jungle;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import animal.AnimalSet;
import drag_framework.DragController;
import drag_framework.DragLayer;
import drag_framework.DropSpot;

public class ChooseAnimalsJungleActivity extends MyActivity 
implements View.OnLongClickListener, View.OnClickListener, View.OnTouchListener
{

	private DragController mDragController;   // Object that sends out drag-drop events while a view is being moved.
	private DragLayer mDragLayer;             // The ViewGroup that supports drag-drop.
	private boolean mLongClickStartsDrag = false;    // If true, it takes a long click to start the drag operation.
	// Otherwise, any touch event starts a drag.

	public static final boolean Debugging = false;
	private static int success = 0;
	Dialog dialog;
	private int[] images_ids = new int[6];
	private int destiny;
	private List<Integer> right_answers;
	private List<Integer> right_answers_ids;
	private Jungle app;


	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mDragController = new DragController(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_choose_animals_jungle);

		this.setDrop_background(R.drawable.drag_here);
		
		app =  (Jungle)getApplicationContext();
		destiny = app.getDestiny();
		
		setupViews();
		setAnimalsOptions();

		ImageButton next = (ImageButton)findViewById(R.id.nextButton_choose);
		next.setVisibility(View.INVISIBLE);

		next.setOnClickListener(this);

		dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_choose_dialog);
		dialog.setTitle("Instrucciones:");

		success = 0;
		
		TextView tx = (TextView)findViewById(R.id.text_question);
		TextView txd = (TextView)dialog.findViewById(R.id.text_dialog);
		
		if(destiny == app.CONGO){
			tx.setText("¿Cuáles de estos animales viven en la jungla del Congo?");
			txd.setText("Encuentra los animales que viven en la jungla del Congo");
			txd.setTextSize(30);
		}
		else{
			tx.setText("¿Cuáles de estos animales viven en la jungla de Borneo?");
			txd.setText("Encuentra los animales que viven en la jungla de Borneo");
			txd.setTextSize(30);
		}

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(this);
		dialog.show();	    
	}

	@SuppressLint("UseValueOf")
	public void setAnimalsOptions() {
		AnimalSet animSet = new AnimalSet(this);
		right_answers = new ArrayList<Integer>();
		right_answers_ids = new ArrayList<Integer>();
		
		images_ids[0] = R.id.animal1;
		images_ids[1] = R.id.animal2;
		images_ids[2] = R.id.animal3;
		images_ids[3] = R.id.animal4;
		images_ids[4] = R.id.animal5;
		images_ids[5] = R.id.animal6;
		
		// random amount of right_answers
		int k = 0;
		k = (2 + (int)(Math.random() * ((5 - 2) + 1)));
		
		Jungle app = (Jungle)getApplicationContext();
		right_answers = app.getRandomRange(0, 5, k);
		
		
		List<String> random_right_animals = new ArrayList<String>();
		List<String> random_wrong_animals = new ArrayList<String>();
		List<String> random_wrong_animals1 = new ArrayList<String>();
		List<String> random_wrong_animals2 = new ArrayList<String>();
		
		
		int wrong_animals = 6 - k;
		int wrong_animals_1 = (1 + (int)(Math.random() * ((wrong_animals - 1) + 1)));
		int wrong_animals_2 = wrong_animals - wrong_animals_1; 
			
		
		if(destiny == 0){
			random_right_animals  = animSet.getAnimalsRandom("congo", k, false);
			random_wrong_animals  = animSet.getAnimalsRandom("borneo", wrong_animals_1, false);
		}
		else{
			random_right_animals = animSet.getAnimalsRandom("borneo", k, false);
			random_wrong_animals1  = animSet.getAnimalsRandom("congo", wrong_animals_1, false);
		}
		
		random_wrong_animals2 = animSet.getAnimalsRandom("extra", wrong_animals_2, false);
		
		random_wrong_animals.addAll(random_wrong_animals1);
		random_wrong_animals.addAll(random_wrong_animals2);
		
		ImageView img = null;
		int img_id = 0;
		int n = 0;
		for(Integer i: right_answers){
			img = (ImageView)findViewById(images_ids[i]);
			img_id = animSet.getDrawableAnimalId(random_right_animals.get(n++));
			img.setImageDrawable(decodeDrawable(img_id));
			right_answers_ids.add(new Integer(images_ids[i]));
		}

		n = 0;
		for(int i = 0; i < 6; i++){
			if(!right_answers.contains(new Integer(i))){
				img = (ImageView)findViewById(images_ids[i]);
				img_id = animSet.getDrawableAnimalId(random_wrong_animals.get(n++));
				img.setImageDrawable(decodeDrawable(img_id));
			}
		}
		
		
		
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
		//	        case ENABLE_S2_MENU_ID:
		//	            if (mSpot2 != null) mSpot2.setDragLayer (mDragLayer);
		//	            return true;
		}


		Intent intent = new Intent("CLOSE_ALL");
		this.sendBroadcast(intent);
		//	        android.os.Process.killProcess(android.os.Process.myPid()); 
		finish();
		return super.onOptionsItemSelected(item);
	}



	/**
	 * Handle a click on a view.
	 *
	 */    

	public void onClick(View v) 
	{
		if (mLongClickStartsDrag) {
			// Tell the user that it takes a long click to start dragging.
		}

		if(v.getId() == R.id.nextButton_choose){
			Intent i = new Intent(this, SayGoodbyeActivity.class);
			startActivity(i);
			finish();
		}
		if(v.getId() == R.id.dialogButtonOK)
			dialog.dismiss();
	}

	/**
	 * Handle a long click.
	 *
	 * @param v View
	 * @return boolean - true indicates that the event was handled
	 */    

	public boolean onLongClick(View v) 
	{
		if (mLongClickStartsDrag) {

			//trace ("onLongClick in view: " + v + " touchMode: " + v.isInTouchMode ());

			// Make sure the drag was started by a long press as opposed to a long click.
			// (Note: I got this from the Workspace object in the Android Launcher code. 
			//  I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
			if (!v.isInTouchMode()) {
				return false;
			}
			return startDrag (v);
		}

		// If we get here, return false to indicate that we have not taken care of the event.
		return false;
	}


	/**
	 * This is the starting point for a drag operation if mLongClickStartsDrag is false.
	 * It looks for the down event that gets generated when a user touches the screen.
	 * Only that initiates the drag-drop sequence.
	 *
	 */    

	public boolean onTouch (View v, MotionEvent ev) 
	{
		// If we are configured to start only on a long click, we are not going to handle any events here.
		if (mLongClickStartsDrag) return false;

		boolean handledHere = false;

		final int action = ev.getAction();

		// In the situation where a long click is not needed to initiate a drag, simply start on the down event.
		if (action == MotionEvent.ACTION_DOWN) {
			handledHere = startDrag (v);
		}

		return handledHere;
	}

	/**
	 * Start dragging a view.
	 *
	 */    

	public boolean startDrag (View v)
	{
		Object dragInfo = v;

		mDragController.startDrag (v, mDragLayer, dragInfo, DragController.DRAG_ACTION_MOVE);

		return true;
	}

	/**
	 * Finds all the views we need and configure them to send click events to the activity.
	 *
	 */
	private void setupViews() 
	{
		DragController dragController = mDragController;
		mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
		mDragLayer.setDragController(dragController);

		dragController.addDropTarget (mDragLayer);

		ImageView i1 = (ImageView) findViewById (R.id.animal1);
		ImageView i2 = (ImageView) findViewById (R.id.animal2);
		ImageView i3 = (ImageView) findViewById (R.id.animal3);
		ImageView i4 = (ImageView) findViewById (R.id.animal4);
		ImageView i5 = (ImageView) findViewById (R.id.animal5);
		ImageView i6 = (ImageView) findViewById (R.id.animal6);

		i1.setOnClickListener(this);
		i1.setOnLongClickListener(this);
		i1.setOnTouchListener(this);

		i2.setOnClickListener(this);
		i2.setOnLongClickListener(this);
		i2.setOnTouchListener(this);

		i3.setOnClickListener(this);
		i3.setOnLongClickListener(this);
		i3.setOnTouchListener(this);

		i4.setOnClickListener(this);
		i4.setOnLongClickListener(this);
		i4.setOnTouchListener(this);

		i5.setOnClickListener(this);
		i5.setOnLongClickListener(this);
		i5.setOnTouchListener(this);

		i6.setOnClickListener(this);
		i6.setOnLongClickListener(this);
		i6.setOnTouchListener(this);

		DropSpot drop1 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot1);

		DropSpot drop2 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot2);

		DropSpot drop3 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot3);

		DropSpot drop4 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot4);

		DropSpot drop5 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot5);

		DropSpot drop6 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot6);

		DropSpot drop_center = (DropSpot) mDragLayer.findViewById (R.id.drop_spot_center);
		drop_center.setup (mDragLayer, dragController, R.drawable.drag_here,this);	    

		drop1.setup (null, dragController, R.color.drop_target_disabled,this);
		drop2.setup (null, dragController, R.color.drop_target_disabled,this);
		drop3.setup (null, dragController, R.color.drop_target_disabled,this);
		drop4.setup (null, dragController, R.color.drop_target_disabled,this);
		drop5.setup (null, dragController, R.color.drop_target_disabled,this);
		drop6.setup (null, dragController, R.color.drop_target_disabled,this);	
	}

	@Override
	public void verifyChoice(View v) {
		DropSpot center =  (DropSpot)mDragLayer.findViewById(R.id.drop_spot_center);

		if(right_answers_ids.contains(v.getId())){
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tada);
			mediaPlayer.start();

			((ViewManager)v.getParent()).removeView(v);
			center.setBackgroundDrawable(decodeDrawable(R.drawable.smiley_happy));
			success += 1;
			if(success == right_answers_ids.size()){
				ImageButton next = (ImageButton)mDragLayer.findViewById(R.id.nextButton_choose);
				next.setVisibility(View.VISIBLE);
			}
		}
		else{
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
			mediaPlayer.start();
			center.setBackgroundDrawable(decodeDrawable(R.drawable.smiley_sad));
		}
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		if (app.getState() != app.CHOOSE_CHALLENGE){
			Intent i = new Intent(this,SoundActivity.class);
			startActivity(i);    	
			finish();
		}else{
			this.goMenuChallenges();
		}
	}

} // end class
