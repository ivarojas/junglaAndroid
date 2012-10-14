package com.taller.jandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import drag_framework.DragController;
import drag_framework.DragLayer;
import drag_framework.DropSpot;

/**
 * This activity presents a screen on which images can be added and moved around.
 * It also defines areas on the screen where the dragged views can be dropped. Feedback is
 * provided to the user as the objects are dragged over these drop zones.
 *
 * <p> Like the DragActivity in the previous version of the DragView example application, the
 * code here is derived from the Android Launcher code.
 * 
 */

	public class ChooseAnimalsJungleActivity extends Activity 
	    implements View.OnLongClickListener, View.OnClickListener, View.OnTouchListener
	{
	
	
	/**
	 */
	// Variables
	
	private DragController mDragController;   // Object that sends out drag-drop events while a view is being moved.
	private DragLayer mDragLayer;             // The ViewGroup that supports drag-drop.
	private DropSpot mSpot2;                  // The DropSpot that can be turned on and off via the menu.
	private boolean mLongClickStartsDrag = false;    // If true, it takes a long click to start the drag operation.
	                                                // Otherwise, any touch event starts a drag.
	
	public static final boolean Debugging = false;
	private static int success = 0;
	Dialog dialog;
	
	/**
	 */
	// Methods
	
	/**
	 * onCreate - called when the activity is first created.
	 * 
	 * Creates a drag controller and sets up three views so click and long click on the views are sent to this activity.
	 * The onLongClick method starts a drag sequence.
	 *
	 */
	
	 protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    mDragController = new DragController(this);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_choose_animals_jungle);
	    setupViews ();
	    
	    ImageButton next = (ImageButton)findViewById(R.id.nextButton_choose);
	    next.setVisibility(View.INVISIBLE);
	    
	    next.setOnClickListener(this);
	    
	    dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_choose_dialog);
		dialog.setTitle("Instrucciones:");
		
		

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(this);
		dialog.show();
	    
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
	    	Intent i = new Intent(this, GoodbyeActivity.class);
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
	    // Let the DragController initiate a drag-drop sequence.
	    // I use the dragInfo to pass along the object being dragged.
	    // I'm not sure how the Launcher designers do this.
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
	
	    // Set up some drop targets and enable them by connecting them to the drag layer
	    // and the drag controller.
	    // Note: If the dragLayer is not set, the drop spot will not accept drops.
	    // That is the initial state of the second drop spot.
	    DropSpot drop1 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot1);
	
	    DropSpot drop2 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot2);
	    
	    DropSpot drop3 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot3);
	    
	    DropSpot drop4 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot4);
	    
	    DropSpot drop5 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot5);
	    
	    DropSpot drop6 = (DropSpot) mDragLayer.findViewById (R.id.drop_spot6);
	    
	    DropSpot drop_center = (DropSpot) mDragLayer.findViewById (R.id.drop_spot_center);
	    drop_center.setup (mDragLayer, dragController, R.drawable.drag_here);
	
	    
	
	    drop1.setup (null, dragController, R.color.drop_target_disabled);
	    drop2.setup (null, dragController, R.color.drop_target_disabled);
	    drop3.setup (null, dragController, R.color.drop_target_disabled);
	    drop4.setup (null, dragController, R.color.drop_target_disabled);
	    drop5.setup (null, dragController, R.color.drop_target_disabled);
	    drop6.setup (null, dragController, R.color.drop_target_disabled);
	
	    // Save the second area so we can enable and disable it via the menu.
	    mSpot2 = drop_center;
	
	    // Note: It might be interesting to allow the drop spots to be movable too.
	    // Unfortunately, in the current implementation, that does not work
	    // because the parent view of the DropTarget objects is not the drag layer.
	    // The current DragLayer.onDrop method makes assumptions about how to reposition a dropped view.
	
	    // Give the user a little guidance.
//	    String message = mLongClickStartsDrag ? "Press and hold to start dragging." 
//	                                          : "Toca un animal para arrastrarlo.";
//	    Toast.makeText (getApplicationContext(), message, Toast.LENGTH_LONG).show ();
	
	}
	
	public static int verifyAnimalChosen(View v) {
		if(v.getId() == R.id.animal1 || v.getId() == R.id.animal2 || v.getId() == R.id.animal6){
			success += 1;
			return success;
		}
		else
			return -1;
	}

    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i = new Intent(this,SplitAnimalActivity.class);
        i.putExtra("animal", "gorilla");
        startActivity(i);    	
    }
//	private void plus_success() {
//		success +=1;
//		TextView tx = (TextView)mDragLayer.findViewById(R.id.text_success);
//		tx.setText(success+"/3");
//	}

} // end class
