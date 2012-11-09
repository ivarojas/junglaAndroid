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
	public List<String> getAnimalsRandom(String key, int amount, boolean sound){
		List<String> animals = new ArrayList<String>();
		if(key.equals("congo"))
			animals = getAnimalsCongo(sound);
		else if(key.equals("borneo"))
			animals = getAnimalsBorneo(sound);
		
		List<String> animal_random = new ArrayList<String>();
		List<Integer> number_random = new ArrayList<Integer>();
		
		int length = animals.size();
		int n;
		for(int i = 0; i<amount; i++){
			n = (int)(Math.random() * length);
			while(number_random.contains(new Integer(n))){
				n = (int)(Math.random() * length);	
			}
			
			number_random.add(new Integer(n));
			animal_random.add(animals.get(n));
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
	
	public List<String> getAnimalsCongo(boolean sound){
		List<String> animals = new ArrayList<String>();
		if(sound){
			animals.add("elephant");
			animals.add("hippo");
			animals.add("gorilla");
			animals.add("bonobo");
		}
		else{
			animals.add("elephant");
			animals.add("hippo");
			animals.add("gorilla");
			animals.add("okapi");
			animals.add("bonobo");
		}
		return animals;
	}
	
	public List<String> getAnimalsBorneo(boolean sound){
		List<String> animals = new ArrayList<String>();
		if(sound){
			animals.add("leopard");
			animals.add("frog");
			animals.add("bear");
			animals.add("orangutan");
			animals.add("rhino");
			animals.add("hornbill");
		}
		else{
			animals.add("leopard");
			animals.add("frog");
			animals.add("bear");
			animals.add("orangutan");
			animals.add("proboscis");
			animals.add("rhino");
			animals.add("hornbill");
		}
		return animals;
	}
}
