package com.taller.jandroid;

public class Animal {
	
	String name;
	int sound_id;
	int image_id;
	int[] image_split_ids;
	
	public Animal(String n, int sound, int image, int[] split_images) {
		name = n;
		sound_id = sound;
		image_id = image;
		image_split_ids = split_images;
	}

	public String[] getAnimalsCongo(){
		String[] animals = {
			"elephant","hippo",""
		};
		return animals;
	}
	
}

