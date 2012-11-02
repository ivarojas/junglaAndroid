package persistance;

import java.util.ArrayList;
import java.util.List;

public class Food {

    private List<String> fruit;
    private List<String> insect;
    private List<String> meat;
    private List<String> plant;
    private List<String> extra;
    
	public List<String> getFruit() {
		return fruit;
	}
	public void setFruit(List<String> fruit) {
		this.fruit = fruit;
	}
	public List<String> getInsect() {
		return insect;
	}
	public void setInsect(List<String> insect) {
		this.insect = insect;
	}
	public List<String> getMeat() {
		return meat;
	}
	public void setMeat(List<String> meat) {
		this.meat = meat;
	}
	public List<String> getPlant() {
		return plant;
	}
	public void setPlant(List<String> plant) {
		this.plant = plant;
	}
	public List<String> getExtra() {
		return extra;
	}
	public void setExtra(List<String> extra) {
		this.extra = extra;
	}
	
	public List<String> getFoodByType(String type){
		if(type.equals("fruit"))
			return this.getFruit();
		else if(type.equals("insect"))
			return this.getInsect();
		else if(type.equals("meat"))
			return this.getMeat();
		else if(type.equals("plant"))
			return this.getPlant();
		else
			return this.getExtra();
	}
	
	public List<String> getFoodTypes(){
		List<String> food_types = new ArrayList<String>();
		food_types.add("fruit");
		food_types.add("meat");
		food_types.add("plant");
		food_types.add("insect");
		food_types.add("extra");
		
		return food_types;
	}
}
