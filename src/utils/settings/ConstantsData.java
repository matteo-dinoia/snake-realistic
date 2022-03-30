package utils.settings;

import java.util.*;

public class ConstantsData {
	private TreeMap<String, Constant> lista=new TreeMap<String, Constant>();
	
	//COSTRUCTOR  ----------------------------------------------------------
	public void addNotNull(Constant constant) {
		
		if(constant==null) return;
		
		lista.put(constant.getName(), constant);
		
	}
	
	
	//TO_STRING  -----------------------------------------------------------
	public String toString() {
		String ris="Print of all the value(of size "+lista.size()+"):\n";
		
		var collection=lista.values();
		for(Constant c : collection) {
			ris+=c+"\n";
		}
		
		return ris;
	}


	public Constant get(String key) {
		return lista.get(key);
	}
}
