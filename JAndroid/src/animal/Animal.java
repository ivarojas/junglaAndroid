package animal;

import java.util.List;

public class Animal {
	
	private String name;
    private String spanish;
    private List<String> food;
    private List<String> ambient;
    private String description;

    // getters
    public String getName() { 
    	return name; 
    }
    public String getSpanish() { 
    	return spanish; 
    }
    public List<String> getFood() { 
    	return food; 
    }
    public List<String> getAmbient() { 
    	return ambient; 
    }
	public String getDescription() {
		return description;
	}
	// setters
    public void setName(String name) { 
    	this.name = name; 
    }
    public void setSpanish(String spanish) { 
    	this.spanish = spanish; 
    }
    public void setFood(List<String> food) { 
    	this.food = food; 
    }
    public void setAmbient(List<String> ambient) { 
    	this.ambient = ambient; 
    }
	public void setDescription(String description) {
		this.description = description;
	}	
}

