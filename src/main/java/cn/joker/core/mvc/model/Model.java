package cn.joker.core.mvc.model;

import java.util.HashMap;
import java.util.Iterator;

public class Model implements Cloneable {

	private static Model model = new Model();
	
	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	public static Model getModelInstance() {
		
		return model;
	}
	
	public Object put(String key,Object value){
		return this.map.put(key, value);
	}
	
	public Object get(String key){
		return this.map.get(key);
	}
	
	public Object remove(String key){
		return this.map.remove(key);
	}
	
	public Iterator<String> iteratorKey(){
		return map==null?null:map.keySet().iterator();
	}
	
	public Model clone(){
		try {
			model = (Model) super.clone();
			model.map = (HashMap<String, Object>) this.map.clone();
			model.map.clear();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	
}
