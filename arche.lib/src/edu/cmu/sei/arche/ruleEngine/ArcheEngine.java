package edu.cmu.sei.arche.ruleengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.sei.arche.ruleengine.modules.*;

public class ArcheEngine {

	private List listeners = new ArrayList();
	private Map<String,Fact> templates;
	public List<Fact> facts = new ArrayList();

	
	private Planner planner;
	private MySQL mysql;
	
	public ArcheEngine(){
		templates = new HashMap <String, Fact>();
		planner = new Planner(this);
		mysql = new MySQL(this);
	}

	public synchronized void addEventListener(ArcheEngineEventListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeEventListener(
			ArcheEngineEventListener listener) {
		listeners.remove(listener);
	}
	
	private synchronized void fireFactEvent(Fact fact, String type) {
		ArcheEngineEvent event = new ArcheEngineEvent(this, fact, type);
		Iterator i = listeners.iterator();
		while (i.hasNext()) {
			((ArcheEngineEventListener) i.next()).handleFactEvent(event);
		}
	}
	
	public void runPlanner(){
		planner.run();
	}
	
	public void runMySQL(){
		planner.run();
	}
	

}
