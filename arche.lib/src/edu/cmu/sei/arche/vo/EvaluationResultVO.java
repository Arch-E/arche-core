/*
 * ArchE
 * Copyright (c) 2012 Carnegie Mellon University.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following acknowledgments and disclaimers.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. All advertising materials for third-party software mentioning features or
 * use of this software must display the following disclaimer:
 *
 * “Neither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this software”
 *
 * 4. The names “Carnegie Mellon University,” and/or “Software Engineering
 * Institute" shall not be used to endorse or promote products derived from
 * this software without prior written permission. For written permission,
 * please contact permission@sei.cmu.edu.
 *
 * 5. Redistributions of any form whatsoever must retain the following
 * acknowledgment:
 *
 * Copyright 2012 Carnegie Mellon University.
 *
 * This material is based upon work funded and supported by the United States
 * Department of Defense under Contract No. FA8721-05-C-0003 with Carnegie
 * Mellon University for the operation of the Software Engineering Institute, a
 * federally funded research and development center.
 *
 * NO WARRANTY
 *
 * THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE MATERIAL
 * IS FURNISHED ON AN “AS-IS” BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.arche.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * Corresponds to fact "Seeker::EvaluationResult" in ArchE core. Not all slots are mapped to
 * properties in this VO class. Basically, for ArchE UI, only displays the informarion of the tactics 
 * description, the scenatios, the relevance of the tactic to the scenarion, and the utility of the 
 * tactic to the scenario.
 * 
 * @author Ricardo Vazquez
 */

public class EvaluationResultVO extends CoreFact {

	
	private final static String SEEKER_EVALUATIONRESULT = "Seeker::EvaluationResult";

    
    /**
     * Stores the id of the fact related to the current evaluation result
     */
    private int factId;
    

    /**
     * Indicates the description of the tactic
     */
    private String scenarioName;
    
    /**
     * Idicates the id of the scenario
     */
    private int scenarioId;

    
    /**
     * Represents the tactics applied to the current scenario
     */
    private Hashtable tactics;
    
    private String tacticId;
	private String tacticDesc;
	private double utility;
	private int relevance;
	private double change;
	
    
    /**
     * @param factId
     * @param isSatisfied
     * @param value
     * @param ownerFactId
     * @param source
     */

    public EvaluationResultVO(int factId, double utility, int scenarioId,
            IRefreshableUI ui, double change, int relevance, String tacticDescription, String scenarioName, String tacticId) {
        super(factId, SEEKER_EVALUATIONRESULT, ui);
        tactics = new Hashtable();
        
        if(tacticId != null && !tacticId.equals("")){
        	EvaluatedTacticsVO tactic = new EvaluatedTacticsVO(tacticId, tacticDescription, utility, relevance, change);
         
        	tactics.put(tacticId,tactic);
        }
        
        this.change = change;
        this.tacticDesc = tacticDescription;
        this.utility = utility;
        this.relevance = relevance;
        this.change = change;
        this.scenarioName = scenarioName;
        this.tacticId = tacticId;
        this.scenarioId   = scenarioId;
        ui.flagViewsToRefresh(this.getClass());   
    }
        
	/**
	 *@return Returns the name of the scenario 
	 */
	public String getScenarioName() {
		return scenarioName;
	}

	/**
	 * 
	 * @param scenarioName The description of the scenario.
	 */
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public int getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public int getFactId() {
		return factId;
	}

	public void setFactId(int factId) {
		this.factId = factId;
	}

	public Object [] getTactics() {
		if(tactics != null){
			ArrayList scenarioTactics = new ArrayList();  
			 int count =0;
			 for (Enumeration e = tactics.elements() ; e.hasMoreElements() ;count++) {
				 scenarioTactics.add((EvaluatedTacticsVO)e.nextElement());
		     }
			 Collections.sort(scenarioTactics);
			 return scenarioTactics.toArray();
		}
		return null;
	}
	
	public Hashtable getTactics(boolean inProject){
		return this.tactics;
	}

	/**
	 * @param factTacticId
	 * @param tactic
	 */
	/**
	 * @param factTacticId
	 * @param tactic
	 */
	public void addTactic(String tacticId, EvaluatedTacticsVO tactic){
		//ArrayList sortTactics = new ArrayList();
		if( tacticId != null && !tacticId.equals("")){
			this.tactics.put(tacticId,tactic);
		}
			
	}
	
	public void cleanInvalidTactics(){
		this.tactics.remove(new Integer(-1));
	}
	
	public void setTactics(Hashtable in_tactics) {
		this.tactics = in_tactics;
	}
	
	public void clearTactics(){
		this.tactics.clear();
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}

	public String getTacticDesc() {
		return tacticDesc;
	}

	public void setTacticDesc(String tacticDesc) {
		this.tacticDesc = tacticDesc;
	}

	public String getTacticId() {
		return tacticId;
	}

	public void setTacticId(String tacticId) {
		this.tacticId = tacticId;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}
	
}
