package animal;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

public class Animal {
	
	String name;
	int sound_id;
	int image_id;
	int[] image_split_ids;
	
	//public Animal(String n, int sound, int image, int[] split_images) {
	public Animal(String name, Context context) {
		this.name = name;
		
		Resources resources = context.getResources();
		sound_id = resources.getIdentifier(name, "drawable", context.getPackageName());
		image_id = resources.getIdentifier(name, "drawable", context.getPackageName());
		//image_split_ids = resources.getIdentifier(name, "drawable", context.getPackageName());
	}
	
}

