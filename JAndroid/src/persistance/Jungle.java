package persistance;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import animal.Animal;

public class Jungle extends Application{
	
	public final int CHOOSE_CHALLENGE = 0;
	public final int ADVENTURE = 1;
	public final int CONGO = 0;
	public final int BORNEO = 1;
	
	private List<Animal> congo_animals;
	private List<Animal> borneo_animals;
	private Context bsc;
	private Food food;
	private int destiny;
	private int state;
	
	public Jungle(){
		super();
		destiny = CONGO;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		bsc = getBaseContext();
		readJungleJson();
		
		this.state = this.ADVENTURE;
	}
	
	public void readJungleJson(){
		try {			
			JsonParser parser = new JsonParser();

			InputStream in = null;
			in = bsc.getAssets().open("jungle.json");
			JsonElement jsonElement = parser.parse(new InputStreamReader(in, "UTF-8"));
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			JsonElement Congo = jsonObject.get("Congo");
			JsonElement Borneo = jsonObject.get("Borneo");

			Gson gson = new Gson();
			Type type = new TypeToken<List<Animal>>(){}.getType();
			
			congo_animals = gson.fromJson(Congo, type);
			borneo_animals = gson.fromJson(Borneo, type);
			
			// foods
			in = bsc.getAssets().open("food.json");
			JsonElement foods_element = parser.parse(new InputStreamReader(in, "UTF-8"));
			JsonObject foods_object = foods_element.getAsJsonObject();
			type = new TypeToken<Food>(){}.getType();
			food = gson.fromJson(foods_object, type);
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Animal> getCongo_animals() {
		return congo_animals;
	}

	public List<Animal> getBorneo_animals() {
		return borneo_animals;
	}
	
	@SuppressLint({ "UseValueOf", "UseValueOf" })
	public Hashtable<String,List<Integer>> getChallengeFoodIds(int ambient, String animal_name, int amount){
		List<Animal> animals = getAnimalsByAmbient(ambient);
		
		int size = animals.size();
		List<String> food_types = null;
		for(int i = 0; i < size; i++){
			Animal animal = animals.get(i);
			if(animal.getName().equals(animal_name)){
				food_types = animal.getFood();
				break;
			}
		}
		
		// right foods
		List<String> right_foods = new ArrayList<String>();
		for(String food_type : food_types){
			right_foods.addAll(food.getFoodByType(food_type));
		}
		
		//wrong foods
		List<String> all_food_types = food.getFoodTypes();
		List<String> wrong_foods = new ArrayList<String>();
		for(String food_type : all_food_types){
			if(!food_types.contains(food_type)){
				wrong_foods.addAll(food.getFoodByType(food_type));
			}
		}
		
		// generating number of right and wrong answers
		int right_answers, wrong_answers;
		right_answers = (int)(3 + (int)(Math.random() * ((6 - 3) + 1)));
		size = 7;//right_foods.size();
		if(size <= 3){
			right_answers = size;
		}
		else{
			if(size > 6)
				size = 6;
			right_answers = (int)(3 + (int)(Math.random() * ((size - 3) + 1)));
		}
		wrong_answers = amount - right_answers;
		
		//generating random answers and getting images ids from context
		List<Integer> right_food_ids = getRandomFoodIds(right_foods, right_answers);
		List<Integer> wrong_food_ids = getRandomFoodIds(wrong_foods, wrong_answers);
		
		Hashtable<String,List<Integer>> challenge_foods = new Hashtable<String,List<Integer>>();
		challenge_foods.put("right_foods", right_food_ids);
		challenge_foods.put("wrong_foods", wrong_food_ids);
		
		return challenge_foods;
	}
	
	public String getSpanishName(int ambient, String name){
		List<Animal> animals = getAnimalsByAmbient(ambient);
		for(Animal anim : animals){
			if(anim.getName().equals(name))
				return anim.getSpanish();
		}
		
		return "";
			
	}
	
	public Animal getSingleAnimal(int ambient, String name){
		List<Animal> animals = getAnimalsByAmbient(ambient);
		for(Animal anim : animals){
			if(anim.getName().equals(name))
				return anim;
		}
		
		return null;
			
	}
	
	public List<Animal> getAnimalsByAmbient(int ambient){
		List<Animal> animals;
		switch(ambient){
			case CONGO: { animals = this.congo_animals; break;} 
			case BORNEO: { animals = this.borneo_animals; break;}
			default: animals = null;
		}
		return animals;
	}
	
	public int getFoodImageId(String name){
		Context context = getApplicationContext();
		return context.getResources().getIdentifier("food_" + name, "drawable", context.getPackageName());
	}
	
	@SuppressLint("UseValueOf")
	public List<Integer> getRandomFoodIds(List<String> foods, int amount){
		List<Integer> food_ids = new ArrayList<Integer>();
		//List<Integer> number_random = new ArrayList<Integer>();
		/*int size = foods.size();
		for(int i = 0, n; i<amount; i++){
			n = (int)(Math.random() * size);
			while(number_random.contains(new Integer(n))){
				n = (int)(Math.random() * size);	
			}
			
			number_random.add(new Integer(n));
			food_ids.add(getFoodImageId(foods.get(n)));
		}*/
		List<Integer> random_numbers = getRandomRange(0,foods.size()-1,amount);
		int size = random_numbers.size();
		int k;
		for(int i = 0; i < size; i++){
			k = random_numbers.get(i);
			food_ids.add(getFoodImageId(foods.get(k)));
		}
		
		return food_ids;
	}

	public int getDestiny() {
		return destiny;
	}

	public void setDestiny(int destiny) {
		this.destiny = destiny;
	}
	
	public List<Integer> getRandomRange(int min, int max, int amount){
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> random_numbers = new ArrayList<Integer>();
		int size = max - min + 1;
		
		for(int i = min; i <= max; i++)
			numbers.add(i);
		
		for(int i = 0, n; i<amount; i++){
			n = (int)(Math.random() * size);
			random_numbers.add(numbers.get(n));
			numbers.remove(n);
			size = numbers.size();
		}
		
		return random_numbers;
	}
	
	@SuppressLint("UseValueOf")
	public List<Animal> getAnimalsRandom(int ambient, int amount){
		List<Animal> animals = getAnimalsByAmbient(ambient);
		
		List<Animal> random_animals = new ArrayList<Animal>();
		
		if(amount > animals.size() || amount <= 0){
			return animals;  
		}
		List<Integer> numbers = getRandomRange(0, animals.size()-1, amount);
		
		int size = numbers.size();
		for(int i = 0; i < size; i++){
			random_animals.add(animals.get(i));
		}
		
		return random_animals;
	}

	public int getImageId(String prefix, String animal_name, String suffix) {
		Context context = getApplicationContext();
		return context.getResources().getIdentifier(prefix + animal_name + suffix, "drawable", context.getPackageName());
	}

	public void setState(int state) {
		this.state = state;  		
	}
	
	public int getState() {
		return this.state;  		
	}
}
