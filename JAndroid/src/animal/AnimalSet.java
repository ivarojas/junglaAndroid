package animal;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

public class AnimalSet {

	private Context context;
	
	public AnimalSet(Context context) {
		this.context = context;
	}
	
	public Context getContext(){
		return this.context;
	}
	
	@SuppressLint("UseValueOf")
	public List<String> getAnimalsRandom(String key, int amount){
		String[] animals = null;
		if(key.equals("congo"))
			animals = getAnimalsCongo();
		else if(key.equals("borneo"))
			animals = getAnimalsBorneo();
		
		List<String> animal_random = new ArrayList<String>();
		List<Integer> number_random = new ArrayList<Integer>();
		
		int length = animals.length;
		int n;
		for(int i = 0; i<amount; i++){
			n = (int)(Math.random() * length);
			while(number_random.contains(new Integer(n))){
				n = (int)(Math.random() * length);	
			}
			
			number_random.add(new Integer(n));
			animal_random.add(animals[n]);
		}
		
		return animal_random;
	}
	

	public int getDrawableAnimalId(String name) {
		Resources resources = this.context.getResources();
		return resources.getIdentifier(name, "drawable", context.getPackageName());
	}
	
	public int getShadowAnimalId(String name){
		Resources resources = this.context.getResources();
		return resources.getIdentifier("shadow_" + name, "drawable", context.getPackageName());
	}
	
	public int getSoundAnimalId(String name){
		Resources resources = this.context.getResources();
		return resources.getIdentifier("sound_" + name, "raw", context.getPackageName()); 
	}
	
	public String[] getAnimalsCongo(){
		String[] animals = {
			"elephant",
			"hippo",
			"gorilla",
			"okapi",
			"bonobo"
		};
		return animals;
	}
	
	public String[] getAnimalsBorneo(){
		String[] animals = {
			"leopard",
			"frog",
			"bear",
			"orangutan",
			"proboscis",
			"rhino",
			"hornbill",
		};
		return animals;
	}
}
