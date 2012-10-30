package animal;

import java.util.List;

import android.app.Application;
import android.util.Log;

public class Jungle extends Application{
	
	public Jungle(){
		super();
		readJungleJson();
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	public void readJungleJson(){
		Log.i("HOLA", "OUBOSIUBCPSIWPBDIWHDPÑKIWCP");
	}
	
	public List<Integer> getRightRandomFoodIds(int amount, String animal, List<String> conditions){
		
		return null;
	}
}
