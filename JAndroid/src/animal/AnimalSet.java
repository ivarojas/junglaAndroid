package animal;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import animal.Animal;

public class AnimalSet {

	private Context context;
	
	public AnimalSet(Context context) {
		this.context = context;
	}
	
	public Context getContext(){
		return this.context;
	}
	
	public String[] getAnimalRandom(String key, int amount){
		String[] animals = null;
		if(key.equals("congo"))
			animals = getAnimalsCongo();
		else if(key.equals("extra"))
			animals = getAnimalsExtras();
		
		List<Animal> animal_random = new ArrayList<Animal>();
		List<Integer> number_random = new ArrayList<Integer>();
		
		int length = animals.length;
		int n;
		for(int i = 0; i<amount; i++){
			n = (int)(Math.random() * (length + 1));
			if(!number_random.contains(new Integer(n))){
				Animal animal = new Animal(animals[n], this.context);
				animal_random.add(null);	
			}
		}
		return null;
	}
	
	public String[] getAnimalsCongo(){
		String[] animals = {
			"elephant",
			"hippo",
			"gorilla",
			"okapi"
		};
		return animals;
	}
	
	public String[] getAnimalsExtras(){
		String[] animals = {
			"cow",
			"dog",
			"giraffe",
			"lion",
			"rabbit",
			"rhino",
			"zebra",
		};
		return animals;
	}

}
