package edu.cmu.sei.arche.ruleengine;

import java.util.EventObject;


public class ArcheEngineEvent extends EventObject {

	  private Fact fact;
	  private String type;
		
	  public ArcheEngineEvent (Object source, Fact fact, String type) {
	    super(source);
	    this.fact = fact;
	    this.type = type;
	  }
	  
	  public Fact getFact(){
		 return fact;
	  }
	  
	  public String getType(){
		 return type;
	  }
}
