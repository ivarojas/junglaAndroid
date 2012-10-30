package persistance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Application;
import animal.Animal;

public class Jungle extends Application{
	
	private List<Animal> congo_animals;
	private List<Animal> borneo_animals;
	
	public Jungle(){
		super();
		readJungleJson();
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	public void readJungleJson(){
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader("/home/vito/workspace/CheckAds3/src/jungle.json"));
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			JsonElement Congo = jsonObject.get("Congo");
			JsonElement Borneo = jsonObject.get("Borneo");
			
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Animal>>(){}.getType();
			
			congo_animals = gson.fromJson(Congo, listType);
			
			borneo_animals = gson.fromJson(Borneo, listType);
			
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Animal> getCongo_animals() {
		return congo_animals;
	}

	public List<Animal> getBorneo_animals() {
		return borneo_animals;
	}
}
