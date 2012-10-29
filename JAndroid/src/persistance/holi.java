package persistance;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class holi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gson = new Gson();

		Type collectionType = new TypeToken<Collection<String>>(){}.getType();
		Collection<String> ints2 = gson.fromJson("jungle.json", collectionType);
		System.out.println(ints2.toString());

	}

}
