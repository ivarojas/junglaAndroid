package com.taller.jandroid;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	BroadcastReceiver broadcastReceiver = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDragController = new DragController(this);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feeding);
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        broadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
        	  finish();
        	  
          }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        
        this.setDrop_background(R.drawable.open_mouth_monkey);
        setupViews();
        
        ImageButton next = (ImageButton)findViewById(R.id.next);
	    next.setVisibility(View.INVISIBLE);
	    
	    next.setOnClickListener(this);
	    dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_choose_dialog);
		
		float d = this.getResources().getDisplayMetrics().density;
		((TextView)dialog.findViewById(R.id.textView1)).setLayoutParams(new LinearLayout.LayoutParams((int)(100*d),LayoutParams.WRAP_CONTENT));
		((TextView)dialog.findViewById(R.id.textView1)).setText("");
		((ImageView)dialog.findViewById(R.id.image)).setBackgroundResource(R.drawable.drag_here_feed_instructions);
		dialog.setTitle("Dale 3 alimentos al animal");
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

		success = 0;
		
		dialogButton.setOnClickListener(this);
		dialog.show();
		        
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
//        case ENABLE_S2_MENU_ID:
//            if (mSpot2 != null) mSpot2.setDragLayer (mDragLayer);
//            return true;
        }

        
        Intent intent = new Intent("CLOSE_ALL");
        this.sendBroadcast(intent);
//        android.os.Process.killProcess(android.os.Process.myPid()); 
        finish();
        return super.onOptionsItemSelected(item);
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
	    drop_center.setup (mDragLayer, dragController, R.drawable.sad_monkey, this);
	    drop1.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop2.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop3.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop4.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop5.setup (null, dragController, R.color.drop_target_disabled, this);
	    drop6.setup (null, dragController, R.color.drop_target_disabled, this);
	}

	public void onClick(View v) 
	{
	    if (mLongClickStartsDrag) {
	       // Tell the user that it takes a long click to start dragging and do some shit.
	    }
	    
	    if(v.getId() == R.id.next){
	    	Intent i = new Intent(this, SoundActivity.class);
	    	startActivity(i);
	    	finish();
	    }
	    if(v.getId() == R.id.dialogButtonOK)
	    	dialog.dismiss();
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
		
		
		if(v.getId() == R.id.image_view4 || v.getId() == R.id.image_view2 || v.getId() == R.id.image_view6){
			center.setBackgroundResource(R.drawable.monkey);
			success += 1;
			((ViewManager)v.getParent()).removeView(v);
			if(success ==3){
				ImageButton next = (ImageButton)mDragLayer.findViewById(R.id.next);
				next.setVisibility(View.VISIBLE);
			}
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tada);
	    	mediaPlayer.start();
		}
		else{
			center.setBackgroundResource(R.drawable.sad_monkey);
			MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.failbeep);
	    	mediaPlayer.start();
		}

	}
	
	@Override
    public void onBackPressed(){
		super.onBackPressed();
        Intent i = new Intent(this,AnimalInformationActivity.class);
        i.putExtra("animal", "elephant");
        startActivity(i);    	
    }

	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
	
}
