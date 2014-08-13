package edu.cmu.sei.arche.ruleengine;

import java.util.HashMap;


public class Fact {
	
	private int factId;
	private String name;
	private String module;
	private static int idCounter = 0;
	private HashMap slots;
	
	public Fact(String name){
		this.name = name;
		factId = idCounter;
		idCounter++;
	}
	
	public String getName(){
		return name;
	}
	
	public String getModule(){
		return name;
	}
	
	public void setModule(String module){
		 this.module = module;
	}
	
	public void setSlot(String key, String value){
		slots.put(key, value);
	}
	
	public String getSlotStringValue(String key){
		return slots.get(key).toString();
	}

	
	public Object getSlot(String key){
		return slots.get(key);
	}

}
