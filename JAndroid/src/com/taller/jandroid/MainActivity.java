package com.taller.jandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Dialog dialog;
	private String infolinks="" +
			"<p><a href='http://www.heathwood.org/simpson/quicklinks/animalsoftherainforest/animalmap2.htm'>Heathwood.org</a></p>" +
			"<p><a href='http://www.enchantedlearning.com/subjects/rainforest/animals/Sampling.shtml'>Enchantedlearning.com</a></p>" +
			"<p><a href='http://www.kids.mongabay.com/slideshows/congo-rainforest-tour/'>Kids.mongabay.com</a></p>" +
			"<p><a href='http://www.pbs.org/wnet/africa/explore/rainforest/rainforest_animals_lo.html'>Pbs.org</a></p>" +
			"<p><a href='http://www.arkive.org/'>Arkive.org</a></p>" +
			"<p><a href='http://www.a-z-animals.com'>A-z-animals.com</a></p>" +
			"<p><a href='http://www.visit50.com/2011/06/borneo-proboscis-monkey-up-close/'>Visit50.com</a></p>" +
			"<p><a href='http://www.animalstown.com/\nwww.waza.org'>Animalstown.com</a></p>" +
			"<p><a href='http://www.mongabay.com/borneo/borneo_wildlife.html'>Mongabay.com</a></p>" +
			"<p><a href='http://www.telegraph.co.uk/travel/destinations/asia/malaysia/738665/The-jungles-of-Borneo.html'>Telegraph.co.uk</a></p>";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        Button start = (Button)findViewById(R.id.start);
        Button challenges = (Button)findViewById(R.id.challenges);
        Button exit = (Button)findViewById(R.id.exit);
        Button credits= (Button)findViewById(R.id.credits);
        
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fawn.ttf");  
        start.setTypeface(font);
        challenges.setTypeface(font);
        exit.setTypeface(font);
        
        dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.credits);
		Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
		closeButton.setTypeface(font);
		
		TextView ac=(TextView)dialog.findViewById(R.id.authors_content);
		ac.setText("Brahim Usen (busenmo@gmail.com)\n\nIván Rojas (ivanrohe@gmail.com)\n\nVíctor Rojas (vitorohe@gmail.com)\n");
		Linkify.addLinks(ac, Linkify.ALL);
		
		TextView ic=(TextView)dialog.findViewById(R.id.info_content);
		ic.setText(Html.fromHtml(infolinks));
		ic.setMovementMethod(LinkMovementMethod.getInstance());
		ic.append("Microsoft ® Encarta ® 2007. © 1993-2006 Microsoft Corporation. Reservados todos los derechos.\n");
		
		TextView sc=(TextView)dialog.findViewById(R.id.sounds_content);
		sc.setText(Html.fromHtml("" +
				"<p><a href='http://www.freesound.org'>Freesound.org</a></p>" +
				"<p><a href='http://www.findsounds.com'>Findsounds.com</a></p>" +
				"<p><a href='http://soundbible.com'>Soundbible.com</a></p>" +
				"<p><a href='http://www.freeinfosociety.com/media_index.php?cat=20&type=3'>Freeinfosociety.com</a></p>"));
		sc.setMovementMethod(LinkMovementMethod.getInstance());
		TextView imc=(TextView)dialog.findViewById(R.id.images_content);
		imc.setText("Animales y avión:\nNoemí Rojas H. (noemirohe@gmail.com)\n\n");
		Linkify.addLinks(imc, Linkify.ALL);
		imc.append(Html.fromHtml("" +
				"<p>Fondo de algunos desafíos: <a href='http://www.vectorjungle.com/2010/09/free-vector-jungle-background/'>Vectorjungle.com</a>" +
				"<br>(libre uso, incluso comercial)</p>" +
				"<p>Fondo de pantalla de inicio, elegir destino y despedida:<br>Víctor Rojas</p>"+
				"<p>Otras imágenes: insectos, frutas, etc.<br>(libre uso, incluso comercial)</p>"));
	
		TextView cc=(TextView)dialog.findViewById(R.id.character_content);
		cc.setText(Html.fromHtml("" +
				"<p>Creadora:<br>" +
				"Michelle Coffee, 27, Canadá</p>"+
				"<p><a href='http://www.etsy.com/shop/michellecoffee'>Etsy.com</a></p>" +
				"<p><a href='http://www.michelle-coffee.tumblr.com'>Tumblr.com</a></p>" +
				"<p><a href='http://www.misscoffee.deviantart.com'>Deviantart.com</a></p>" +
				"<p><a href='http://www.facebook.com/missmichellecoffee'>Facebook.com</a></p>"));
		cc.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView je=(TextView)dialog.findViewById(R.id.jeremy_email);
		je.setText("(jeremy@dcc.uchile.cl)");
		Linkify.addLinks(je, Linkify.ALL);
		
        start.setOnClickListener(this);
        challenges.setOnClickListener(this);
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
		case R.id.challenges: 
			Intent choose_challenge = new Intent(this,ChallengesActivity.class);
	    	this.startActivity(choose_challenge);
	    	break;
		case R.id.exit:
			Intent intent = new Intent("CLOSE_ALL");
	        this.sendBroadcast(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
			finish();
			break;
		case R.id.credits:
			dialog.show();
			break;
		case R.id.closeButton:
			dialog.dismiss();
			break;
		}
		
	}
}
