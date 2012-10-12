package com.taller.jandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ShadowActivity extends Activity {

    private AlertDialog alertDialog;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Has fallado")
        		.setCancelable(false)
        		.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {}
		  });

		// create alert dialog
		alertDialog = builder.create();
       
        setContentView(R.layout.activity_shadow);
    }
    
    public void verifyAnswer(View view){
    	Button button = (Button) findViewById(view.getId());
    	if(button.getText().equals("Elefante")){
    		Intent intent = new Intent(this, AnimalInformationActivity.class);
    		intent.putExtra("animal", "elephant");
            startActivity(intent);
            finish();
    	}else{
    		// show it
    		alertDialog.show();
    	}
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
        Intent i=new Intent(this,PresentationActivity.class);
        startActivity(i);
    }

}
