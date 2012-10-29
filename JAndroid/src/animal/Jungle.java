package animal;

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
}
