package edu.cmu.sei.arche.ruleengine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import edu.cmu.sei.arche.ruleengine.ArcheEngine;
import edu.cmu.sei.arche.ruleengine.Fact;

public class Planner implements Module {

	private ArcheEngine archeEngine;
	public List<Fact> currentFacts = new ArrayList();
	public List<Fact> newFacts = new ArrayList();
	private List<Fact> tempFacts;

	public Planner(ArcheEngine archeEngine) {
		this.archeEngine = archeEngine;
	}

	public void setArcheEngine(ArcheEngine archeEngine) {
		this.archeEngine = archeEngine;
	}

	// add every fact to currentFacts
	private void setCurrentFacts() {
		currentFacts = new ArrayList<Fact>(archeEngine.facts);
	}

	public void run() {
		while (currentFacts.size() > 0) {
			saveFactBase();
			// copy the new facts, so they will be analyze in the next iteration
			currentFacts = new ArrayList<Fact>(newFacts);
		}
	}

	private void saveFactBase() {

		Fact fact_T_ControlModelExecution = null, fact_T_SaveFactBase = null;

		boolean newFactFound = false;

		// temporary fact list to remove the current fact temporary for this
		// rule
		tempFacts = new ArrayList<Fact>(currentFacts);

		Iterator<Fact> currentFactsIterator = tempFacts.iterator();
		Iterator<Fact> archeEngineIterator = archeEngine.facts.iterator();

		// search for every new facts
		while (currentFactsIterator.hasNext()) {
			Fact fact = currentFactsIterator.next();
			if (fact.getName().equals("T_ControlModelExecution")
					&& fact.getSlotStringValue("type").equals("data")) {
				fact_T_ControlModelExecution = fact;
				newFactFound = true;
			}
			if (fact.getName().equals("T_SaveFactBase")) {
				fact_T_SaveFactBase = fact;
				newFactFound = true;
			}

			// if there is at least one new fact for this rule that has been
			// found, search for the
			// other fact in list with every fact
			if (newFactFound == true) {
				archeEngineIterator = archeEngine.facts.iterator();

				for (Fact archeEngineFact : archeEngine.facts) {
					if (archeEngineFact.getName().equals(
							"T_ControlModelExecution")
							&& fact.getSlotStringValue("type").equals("data")
							&& fact_T_ControlModelExecution == null) {
						fact_T_ControlModelExecution = archeEngineFact;

						// remove the current fact, so the rule won't fire again
						// if both of these fact are in list
						currentFactsIterator.remove();
					}

					if (archeEngineFact.getName().equals("T_SaveFactBase")
							&& fact_T_ControlModelExecution == null) {
						fact_T_SaveFactBase = archeEngineFact;

						// remove the current fact, so the rule won't fire again
						// if both of these fact are in list
						currentFactsIterator.remove();
					}
				}
			}

			// if both facts has been found
			if (fact_T_ControlModelExecution != null
					&& fact_T_SaveFactBase != null) {
				archeEngine.runMySQL();
			}
		}

	}

	private void restoreFactBase() {

	}

	private void exportFactBase() {

	}

	private void cleanUpQuestionsAllUnansweredQuestions() {

	}

	private void RemoveOldAnalysisResults() {

	}

	private void ProcessNewRequirements() {

	}

	private void startOverAgain() {

	}

	private void assertFact(Fact fact) {
		archeEngine.facts.add(fact);
		newFacts.add(fact);
	}

}
