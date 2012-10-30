package animal;

import java.util.List;

public class Animal {
	
	private String name;
    private String spanish;
    private List<String> food;
    private List<String> ambient;

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

    public void setName(String name) { 
    	this.name = name; 
    }
    
    // setters
    public void setSpanish(String spanish) { 
    	this.spanish = spanish; 
    }
    public void setFood(List<String> food) { 
    	this.food = food; 
    }
    public void setAmbient(List<String> ambient) { 
    	this.ambient = ambient; 
    }	
}

