package com.taller.jandroid;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import persistance.Jungle;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import drag_framework.DragController;
import drag_framework.DragLayer;
import drag_framework.DropSpot;

public class FeedingActivity extends MyActivity
implements View.OnLongClickListener, View.OnClickListener, View.OnTouchListener{
	
	public int drop_background;
	private DragController mDragController;
	private DragLayer mDragLayer;          
	private boolean mLongClickStartsDrag = false;
	public static final boolean Debugging = false;
	private static int success = 0;
	Dialog dialog;
	private List<Integer> right_answers;
	private int[] images_ids = new int[6];
	private List<Integer> right_answers_ids;
	private int destiny;
	private Jungle app;
	private TextView display ;
	private Dialog finalDialog;
	
    @SuppressLint("UseValueOf")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDragController = new DragController(this);

        setContentView(R.layout.activity_feeding);
        
        app = (Jungle)getApplicationContext();
        
		if (app.getState() == app.CHOOSE_CHALLENGE){
			int dest = (int) Math.round(Math.random());
			app.setDestiny(dest);
		}
        
        destiny = app.getDestiny();
        if(destiny == 0)
        	this.setDrop_background(R.drawable.feed_bonobo_open_mouth);
        else
        	this.setDrop_background(R.drawable.feed_orangutan_open_mouth);
        
        setupViews();
        this.setupDialogEnd();
        
        ImageButton next = (ImageButton)findViewById(R.id.next);
	    next.setVisibility(View.INVISIBLE);
	    
	    next.setOnClickListener(this);
	    dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_choose_dialog);
		((ImageView)dialog.findViewById(R.id.image)).setBackgroundDrawable(decodeDrawable(R.drawable.drag_here_feed_instructions,false));
		dialog.setTitle("Dale alimentos al animal");
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf");  
		dialogButton.setTypeface(font);
		
		success = 0;
		
		dialogButton.setOnClickListener(this);
		dialog.show();
		
		
		// Getting random food for the animal
		Jungle jungle = (Jungle) getApplication();
		Hashtable<String, List<Integer>> foods = jungle.getChallengeFoodIds(jungle.CONGO, "bonobo", 6);
		List<Integer> right_foods_ids = foods.get("right_foods");
		List<Integer> wrong_foods_ids = foods.get("wrong_foods");
		
		ImageView answer_image;
		
		images_ids[0] = R.id.image_view1;
		images_ids[1] = R.id.image_view2;
		images_ids[2] = R.id.image_view3;
		images_ids[3] = R.id.image_view4;
		images_ids[4] = R.id.image_view5;
		images_ids[5] = R.id.image_view6;
		
		//right answers
		int size = right_foods_ids.size();
		
		right_answers = app.getRandomRange(0, 5, size);
		right_answers_ids = new ArrayList<Integer>();
		for(int right_answer : right_answers)
			right_answers_ids.add(images_ids[right_answer]);
		
		int i;
		for(i = 0; i < size; i++){
			answer_image = (ImageView)findViewById(images_ids[right_answers.get(i)]);
			answer_image.setImageDrawable(decodeDrawable(right_foods_ids.get(i),false));
		}
		
		//wrong answers
		int k = 0;
		for(i = 0; i < 6; i++){
			if(!right_answers.contains(new Integer(i))){
				answer_image = (ImageView)findViewById(images_ids[i]);
				answer_image.setImageDrawable(decodeDrawable(wrong_foods_ids.get(k),false));
				k++;
			}
		}
    }
    
    private void setupViews() 
	{
	    DragController dragController = mDragController;
	    mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
	    mDragLayer.setDragController(dragController);
	
	    dragController.addDropTarget (mDragLayer);
	    
	    ImageView i1 = (ImageView) findViewById (R.id.image_view1);
	    ImageView i2 = (ImageView) findViewById (R.id.image_view2);
	    ImageView i3 = (ImageView) findViewById (R.id.image_view3);
	    ImageView i4 = (ImageView) findViewById (R.id.image_view4);
	    ImageView i5 = (ImageView) findViewById (R.id.image_view5);
	    ImageView i6 = (ImageView) findViewById (R.id.image_view6);
	
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
    
	    DropSpot drop_center = (DropSpot) mDragLayer.findViewById (R.id.drop_spot0);
	    if(destiny == 0)
	    	drop_center.setup (mDragLayer, dragController, R.drawable.feed_bonobo_sad, this);
	    else{
	    	drop_center.setup (mDragLayer, dragController, R.drawable.feed_orangutan_sad, this);
	    	drop_center.setBackgroundDrawable(decodeDrawable(R.drawable.feed_orangutan_sad,false));
	    }
	    drop1.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop2.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop3.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop4.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop5.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop6.setup (null, dragController, R.color.drop_target_disabled, this);
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

	public void onClick(View v) 
	{
	    if (mLongClickStartsDrag) {
	       // Tell the user that it takes a long click to start dragging and do some shit.
	    }
	    
	    switch(v.getId()){
			case R.id.again:
				finalDialog.dismiss();
				Intent self = new Intent(this, FeedingActivity.class);
				self.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	            startActivity(self);
	            finish();
				break;
			case R.id.nope:
				finalDialog.dismiss();
				//Para cambiar la redireccion, cambiar la clase destino del intent
				if (app.getState() != app.CHOOSE_CHALLENGE){
		    		Intent i = new Intent(this, SplitAnimalActivity.class);
		    		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    		startActivity(i);
		    		recycle();
		    		finish();
		    	}else{
		    		recycle();
		    		goMenuChallenges();
		    	}
				break;
			case R.id.dialogButtonOK:
				dialog.dismiss();
		}
	}
	
	public boolean onLongClick(View v) 
	{
	    if (mLongClickStartsDrag) {
	       
	        if (!v.isInTouchMode()) {
	           return false;
	        }
	        return startDrag (v);
	    }
	
	    return false;
	}
	
	public boolean onTouch (View v, MotionEvent ev) 
	{
	    if (mLongClickStartsDrag) return false;
	
	    boolean handledHere = false;
	
	    final int action = ev.getAction();
	
	    if (action == MotionEvent.ACTION_DOWN) {
	       handledHere = startDrag (v);
	    }
	    
	    return handledHere;
	}
	
	public boolean startDrag (View v)
	{
	    Object dragInfo = v;
	    
	    mDragController.startDrag (v, mDragLayer, dragInfo, DragController.DRAG_ACTION_MOVE);
	    
	    return true;
	}
	
	public void verifyChoice(View v) {
		
		DropSpot center = (DropSpot)mDragLayer.findViewById(R.id.drop_spot0);
		String return_message;
		
		if(right_answers_ids.contains(v.getId())){
			if(destiny == 0)
				center.setBackgroundDrawable(decodeDrawable(R.drawable.feed_bonobo_happy,false));
			else
				center.setBackgroundDrawable(decodeDrawable(R.drawable.feed_orangutan_happy,false));
			success += 1;
			((ViewManager)v.getParent()).removeView(v);
			if(success == right_answers_ids.size()){
				//ImageButton next = (ImageButton)mDragLayer.findViewById(R.id.next);
				//next.setVisibility(View.VISIBLE);
				return_message = "Has ganado";
				display.setText(return_message);
				finalDialog.show();
			}
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tada);
	    	mediaPlayer.start();
		}
		else{
			if(destiny == 0)
				center.setBackgroundDrawable(decodeDrawable(R.drawable.feed_bonobo_sad,false));
			else
				center.setBackgroundDrawable(decodeDrawable(R.drawable.feed_orangutan_sad,false));
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
	    	mediaPlayer.start();
		}
	}
	
	@Override
    public void onBackPressed(){
		super.onBackPressed();
		if (app.getState() != app.CHOOSE_CHALLENGE){
			Intent i = new Intent(this,ShadowActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			startActivity(i);
			recycle();
			finish();
		}else{
			recycle();
			this.goMenuChallenges();
		} 
    }	
	
	public void recycle(){
		for(int i=0; i< 5; i++){
			ImageView image_view = (ImageView) findViewById(images_ids[i]);
			if(image_view != null)
				((ViewManager)image_view.getParent()).removeView(image_view);
		}
	}
}
