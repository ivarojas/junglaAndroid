package com.taller.jandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends MyActivity implements OnClickListener{
	private Dialog dialog;
	private String infolinks="" +
			"<p><a href='www.heathwood.org/simpson/quicklinks/animalsoftherainforest/animalmap2.htm'>Heathwood.org</a></p>" +
			"<p><a href='www.enchantedlearning.com/subjects/rainforest/animals/Sampling.shtml'>Enchantedlearning.com</a></p>" +
			"www.kids.mongabay.com/slideshows/congo-rainforest-tour/" +
			"www.pbs.org/wnet/africa/explore/rainforest/rainforest_animals_lo.html" +
			"www.arkive.org/" +
			"www.a-z-animals.com" +
			"www.visit50.com/2011/06/borneo-proboscis-monkey-up-close/" +
			"www.animalstown.com/\nwww.waza.org" +
			"www.mongabay.com/borneo/borneo_wildlife.html" +
			"www.telegraph.co.uk/travel/destinations/asia/malaysia/738665/The-jungles-of-Borneo.html";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        Button start = (Button)findViewById(R.id.start);
        Button exit = (Button)findViewById(R.id.exit);
        Button credits= (Button)findViewById(R.id.credits);
        
        dialog=new Dialog(this);
		dialog.setContentView(R.layout.credits);
		Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
		
		
		TextView ac=(TextView)dialog.findViewById(R.id.authors_content);
		ac.setText("Brahim Usen (busenmo@gmail.com)\nIván Rojas (ivanrohe@gmail.com)\nVíctor Rojas (vitorohe@gmail.com)");
		Linkify.addLinks(ac, Linkify.ALL);
		
		TextView ic=(TextView)dialog.findViewById(R.id.info_content);
		ic.setText(Html.fromHtml(infolinks));
//		Linkify.addLinks(ic, Linkify.ALL);
		ic.append("Microsoft ® Encarta ® 2007. © 1993-2006 Microsoft Corporation. Reservados todos los derechos.\n");
		
		TextView sc=(TextView)dialog.findViewById(R.id.sounds_content);
		sc.setText("www.freesound.org\nwww.findsounds.com\nwww.freeinfosociety.com/media_index.php?cat=20&type=3");
		Linkify.addLinks(sc, Linkify.ALL);
		
		TextView imc=(TextView)dialog.findViewById(R.id.images_content);
		imc.setText("Animales y avión: Noemí Rojas H. (noemirohe@gmail.com)\nFondo: www.vectorjungle.com\n(libre uso, incluso comercial)\nOtras imágenes: insectos, frutas, etc.\n(libre uso, incluso comercial)\n");
		Linkify.addLinks(imc, Linkify.ALL);
		
		TextView cc=(TextView)dialog.findViewById(R.id.character_content);
		cc.setText("Creadora:\nwww.misscoffee.deviantart.com\nwww.etsy.com/shop/michellecoffee\nwww.michelle-coffee.tumblr.com/");
		Linkify.addLinks(cc, Linkify.ALL);
		
        start.setOnClickListener(this);
        exit.setOnClickListener(this);
        credits.setOnClickListener(this);
        closeButton.setOnClickListener(this);
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
		case R.id.credits:
			dialog.show();
			break;
		case R.id.closeButton:
			dialog.dismiss();
			break;
		}
		
	}

	@Override
	public void verifyChoice(View v) {
		
	}
}