package edu.cmu.sei.arche.ruleengine.modules;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sei.arche.ruleengine.ArcheEngine;
import edu.cmu.sei.arche.ruleengine.Fact;

public class MySQL extends ModuleSequence{
	
	private ArcheEngine archeEngine;
	public List<Fact> currentFacts = new ArrayList();
	public List<Fact> newFacts = new ArrayList();

	public MySQL(ArcheEngine archeEngine) {
		this.archeEngine = archeEngine;
	}

	public void setArcheEngine(ArcheEngine archeEngine) {
		this.archeEngine = archeEngine;
	}

	public void run(){

	}

}
