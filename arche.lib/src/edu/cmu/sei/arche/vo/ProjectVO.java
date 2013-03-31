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
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Note that ProjectVO is not a fact, so it does not extend CoreFact. There will be a separate
 * fact/rule base for each project, so there�s no need for a project fact. In ArchE UI we need
 * ProjectVO because the user can work with multiple (independent) projects at the same time.
 * 
 * @author Paulo Merson
 * @author Neel Mullick
 */
public class ProjectVO {

    /** Name of the project provided by the user. */
    private String    name;

    /**
     * Hashtable that maps ReasoningFrameworkVO objects to Boolean objects. It shall contain as keys
     * all RFs that Eclipse found as RF plugins. The Boolean value is true if that RF is active for
     * this given project and false otherwise.
     */
    private Hashtable activeRFs;

    /** Set of existing scenarios (ScenarioVO objects) for this project. */
    private HashSet   scenarios;

    /** Set of existing functions (FunctionVO objects) for this project. */
    private HashSet   functions;

    /**
     * Set of existing scenario-resp. mappings (ScenarioResponsibilityMapVO objects) for this
     * project.
     */
    private HashSet   scenarioResps;

    /**
     * Set of existing function-resp. mappings (FunctionResponsibilityMapVO objects) for this
     * project.
     */
    private HashSet   functionResps;

    /**
     * Hashtable of existing responsibilities for this project (Integer factId -->
     * ResponsibilityVO).
     */
    private Hashtable responsibilities;

    /** Set of existing relationships (RelationshipVO objects) for this project. */
    private HashSet   relationships;

    /** Set of existing model elements (ModelElementVO objects) for this project. */
    private Set       modelElements;

    /** Set of existing model relations (ModelRelationVO objects) for this project. */
    private Set       modelRelations;

    /** Set of existing design elements (DesignElementVO objects) for this project. */
    private Set       designElements;

    /** Set of existing design relations (DesignRelationVO objects) for this project. */
    private Set       designRelations;
    
    /** Set of existing trees for this project. */
    private Set       trees;

    /** List of existing questions (QuestionToUserVO objects) for this project. */
    private List      questions;
    
    /** Set of evaluated scenarios with tactics*/
    private Hashtable   evaluationResult;

    /**
     * Constructor that takes only name and activeRFs, and initializes empty sets for other
     * properties.
     */
    public ProjectVO(String name, Hashtable activeRFs) {
        this.name = name;
        this.activeRFs = activeRFs;
        scenarios = new HashSet();
        functions = new HashSet();
        scenarioResps = new HashSet();
        functionResps = new HashSet();
        responsibilities = new Hashtable();
        relationships = new HashSet();
        questions = new ArrayList();
        modelElements = new HashSet();
        modelRelations = new HashSet();
        designElements = new HashSet();
        designRelations = new HashSet();
        evaluationResult = new Hashtable();
        trees = new HashSet();
    }

    /**
     * @return Returns the activeRFs, a Hashtable that maps ReasoningFrameworkVO objects to Boolean
     *         objects. It shall contain as keys all RFs that Eclipse found as RF plugins. The
     *         Boolean value is true if that RF is active for this given project and false
     *         otherwise.
     */
    public Hashtable getActiveRFs() {
        return activeRFs;
    }

    /**
     * @param activeRFs The activeRFs to set.
     */
    public void setActiveRFs(Hashtable activeRFs) {
        this.activeRFs = activeRFs;
    }

    /**
     * @return Returns the Set of existing function-resp. mappings (FunctionResponsibilityMapVO
     *         objects) for this project.
     */
    public HashSet getFunctionResps() {
        return functionResps;
    }

    /**
     * @param functionResps The functionResps to set.
     */
    public void setFunctionResps(HashSet functionResps) {
        this.functionResps = functionResps;
    }

    /**
     * @return Returns the set of existing functions (FunctionVO objects) for this project.
     */
    public HashSet getFunctions() {
        return functions;
    }

    /**
     * @param functions The functions to set.
     */
    public void setFunctions(HashSet functions) {
        this.functions = functions;
    }

    /**
     * @return Returns the name of the project provided by the user.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the Set of existing relationships (RelationshipVO objects) for this project.
     */
    public HashSet getRelationships() {
        return relationships;
    }

    /**
     * @return Returns the relationships that are valid for the Reasoning Framework(s) set to
     *         "active" for this project.
     */
    public HashSet getActiveRelationships() {
        HashSet<String> activeRelationshipTypes = new HashSet<String>();
        HashSet<RelationshipVO> activeRelationships = new HashSet<RelationshipVO>();

        for (Enumeration<ReasoningFrameworkVO> e = this.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = e.nextElement();
            // If the reasoning framework is disabled, continue next loop.
            if (!((Boolean) this.getActiveRFs().get(rf)).booleanValue()) {
                continue;
            }
            for(Iterator<RelationshipTypeVO> it=rf.getRelationshipTypes().iterator(); it.hasNext(); ){
            	RelationshipTypeVO item = it.next();
            	activeRelationshipTypes.add(item.getId());
            }
        }

        for (Iterator<RelationshipVO> it = this.getRelationships().iterator(); it.hasNext();) {
            RelationshipVO relationship = (RelationshipVO) it.next();
            if (activeRelationshipTypes.contains(relationship.getType().getId())) {
                activeRelationships.add(relationship);
            }
        }
        return activeRelationships;
    }

    /**
     * @param relationships The relationships to set.
     */
    public void setRelationships(HashSet relationships) {
        this.relationships = relationships;
    }

    /**
     * @return Returns the Hashtable of existing responsibilities for this project (Integer factId
     *         --> ResponsibilityVO).
     */
    public Hashtable getResponsibilities() {
        return responsibilities;
    }

    /**
     * @param responsibilities The responsibilities to set.
     */
    public void setResponsibilities(Hashtable responsibilities) {
        this.responsibilities = responsibilities;
    }

    /**
     * @return Returns the Set of existing scenario-resp. mappings (ScenarioResponsibilityMapVO
     *         objects) for this project.
     */
    public HashSet getScenarioResps() {
        return scenarioResps;
    }

    /**
     * @param scenarioResps The scenarioResps to set.
     */
    public void setScenarioResps(HashSet scenarioResps) {
        this.scenarioResps = scenarioResps;
    }

    /**
     * @return Returns the set of existing scenarios (ScenarioVO objects) for this project.
     */
    public HashSet getScenarios() {
        return scenarios;
    }

    /**
     * @param scenarios The scenarios to set.
     */
    public void setScenarios(HashSet scenarios) {
        this.scenarios = scenarios;
    }

    /**
     * @return Returns the Set of existing model elements (ModelElementVO objects) for this project.
     */
    public Set getModelElements() {
        return modelElements;
    }

    /**
     * @return Returns the modelElements that are valid for the Reasoning Framework(s) set to
     *         "active" for this project.
     */
    public HashSet getActiveModelElements() {
        HashSet<String> activeModelElementTypes = new HashSet<String>();
        HashSet<ModelElementVO> activeModelElements = new HashSet<ModelElementVO>();

        for (Enumeration<ReasoningFrameworkVO> e = this.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = e.nextElement();
            // If the reasoning framework is disabled, continue next loop.
            if (!((Boolean) this.getActiveRFs().get(rf)).booleanValue()) {
                continue;
            }
            for(Iterator<ModelElementTypeVO> it=rf.getModelElementTypes().iterator(); it.hasNext(); ){
            	ModelElementTypeVO item = it.next();
            	activeModelElementTypes.add(item.getFactType());
            }
        }

        for (Iterator<ModelElementVO> it = this.getModelElements().iterator(); it.hasNext();) {
            ModelElementVO modelElement = (ModelElementVO) it.next();
            if (activeModelElementTypes.contains(modelElement.getType().getFactType())) {
                activeModelElements.add(modelElement);
            }
        }
        return activeModelElements;
    }

    /**
     * @param modelElements The modelElements to set.
     */
    public void setModelElements(Set modelElements) {
        this.modelElements = modelElements;
    }

    /**
     * @return Returns the Set of existing model relations (ModelRelationVO objects) for this
     *         project.
     */
    public Set getModelRelations() {
        return modelRelations;
    }

    /**
     * @return Returns the modelRelations that are valid for the Reasoning Framework(s) set to
     *         "active" for this project.
     */
    public HashSet getActiveModelRelations() {
        HashSet<String> activeModelRelationTypes = new HashSet<String>();
        HashSet<ModelRelationVO> activeModelRelations = new HashSet<ModelRelationVO>();

        for (Enumeration<ReasoningFrameworkVO> e = this.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            // If the reasoning framework is disabled, continue next loop.
            if (!((Boolean) this.getActiveRFs().get(rf)).booleanValue()) {
                continue;
            }
            for(Iterator<ModelRelationTypeVO> it=rf.getModelRelationTypes().iterator(); it.hasNext(); ){
            	ModelRelationTypeVO item = it.next();
            	activeModelRelationTypes.add(item.getFactType());
            }
        }

        for (Iterator<ModelRelationVO> it = this.getModelRelations().iterator(); it.hasNext();) {
            ModelRelationVO modelRelation = it.next();
            if (activeModelRelationTypes.contains(modelRelation.getType().getFactType())) {
                activeModelRelations.add(modelRelation);
            }
        }
        return activeModelRelations;
    }

    /**
     * @param modelRelations The modelRelations to set.
     */
    public void setModelRelations(Set modelRelations) {
        this.modelRelations = modelRelations;
    }

    /**
     * @return Returns the List of existing questions (QuestionToUserVO objects) for this project.
     */
    public List getQuestions() {
        return questions;
    }

    /**
     * @param questions The questions to set.
     */
    public void setQuestions(List questions) {
        this.questions = questions;
    }

    /**
     * @return Returns the Set of existing design elements (DesignElementVO objects) for this
     *         project.
     */
    public Set getDesignElements() {
        return designElements;
    }

    /**
     * @param designElements The designElements to set.
     */
    public void setDesignElements(Set designElements) {
        this.designElements = designElements;
    }

    /**
     * @return Returns the Set of existing design relations (DesignRelationVO objects) for this
     *         project.
     */
    public Set getDesignRelations() {
        return designRelations;
    }

    /**
     * @param designRelations The designRelations to set.
     */
    public void setDesignRelations(Set designRelations) {
        this.designRelations = designRelations;
    }
    
    /**
     * @return Returns the Set of existing Trees for this
     *         project.
     */
    public Set getTrees() {
        return trees;
    }

    /**
     * @param trees The trees to set.
     */
    public void setTrees(Set trees) {
        this.trees = trees;
    }

    /**
     * @return Returns the Set of existing Evaluated results existen for
     *         project.
     */
	public HashSet getEvaluationResult() {
		HashSet returnSet = new	HashSet();
		for (Enumeration enums = evaluationResult.elements(); enums.hasMoreElements() ;) {
			returnSet.add(enums.nextElement());
	     }
		return returnSet;
	}

   /**
    * @param Evaluated  results to set.
    */
	public void setEvaluationResults(HashSet evaluationResult) {
		Iterator it = evaluationResult.iterator();
		while(it.hasNext()){
			EvaluationResultVO evaluationResults = (EvaluationResultVO) it.next();
			this.evaluationResult.put(new Integer(evaluationResults.getScenarioId()), evaluationResults);
		}
	}
	/**
	 * Adds or updates the evaluated result and the tactics related with the evaluated result
	 * @param vo evaluated result to be add or updated in the project
	 */
	public void addUpdateEvaluationResults(RawEvaluationResultVO vo){
		
		boolean addScenario = true;
		boolean addTactic   = true;
		
		EvaluationResultVO evaluatedScenario = new EvaluationResultVO(vo.getFactId(), vo.getUtility(), vo.getScenarioId(),
				 vo.getUi(),vo.getChange(),
				 vo.getRelevance(), vo.getTacticDesc(),
				 vo.getScenarioName(), vo.getTacticId());
		if(evaluationResult.containsKey(new Integer(vo.getScenarioId())) && vo.getScenarioId() > 0){
			
			Hashtable temporalTactics = new Hashtable();
			Enumeration enumLocal = ( (EvaluationResultVO) evaluationResult.get(new Integer(evaluatedScenario.getScenarioId()))).getTactics(true).elements();
			EvaluatedTacticsVO inCopy = vo.getTactics();
			EvaluatedTacticsVO localCopy = null;
			addTactic = true;
			
			for(;enumLocal.hasMoreElements();){
				localCopy = (EvaluatedTacticsVO) enumLocal.nextElement();
				
				if(inCopy.getTacticId() == localCopy.getTacticId()){
					//System.out.println(" Found Tactic and updating relevance " + inCopy.getRelevance());
					addTactic = false;
					localCopy.setChange(inCopy.getChange());
					localCopy.setRelevance(inCopy.getRelevance());
					localCopy.setUtility(inCopy.getUtility());
				}
				
				temporalTactics.put(localCopy.getTacticId(), localCopy);
				//System.out.println("Tactic found in the local table and not updated :" + localCopy.getTacticId());
			}
			if( addTactic && !(inCopy.getTacticId().equals(""))){
				//System.out.println(" Tactic =" + inCopy.getTacticId() + " added in scenario " + vo.getScenarioId());
				temporalTactics.put(inCopy.getTacticId(), inCopy);
			}
			
		    //Clean the values of the tactics that were stored before in the evaluated result
			((EvaluationResultVO)evaluationResult.get(new Integer(vo.getScenarioId()))).clearTactics();
			//System.out.println("Number of tactics in temp tactics for scenario = " + temporalTactics.size());
			//Assign the new information of the tactics to evaluatedresult
			((EvaluationResultVO)evaluationResult.get(new Integer(vo.getScenarioId()))).setTactics(temporalTactics);
		
		}else if (vo.getScenarioId() > 0){
			evaluationResult.put(new Integer(vo.getScenarioId()), evaluatedScenario);
			//if(vo.getFactId() >= 3109){
				//System.out.println("Adding scenario :" + vo.getScenarioId() + " tactics in scenario: " + vo.getTactics().length + 
				//		"Added tactic :" + ((EvaluatedTacticsVO)vo.getTactics()[0]).getTacticId() + 
				//		" Desc " + ((EvaluatedTacticsVO)vo.getTactics()[0]).getTacticDesc());
			
			//}
		}
	}
	
	/**
	 * Removes a specified evaluated result from the list of 
	 * evaluated results stored in the project.
	 * @param vo evaluated scenario to be removed from
	 */
	public void removeTacticER(RawEvaluationResultVO vo){
		
		boolean found = false;
		if(evaluationResult.containsKey(new Integer(vo.getScenarioId()))){

			//validate it the current scenario contains the desired tactic to be modified or added

			Enumeration enumLocal = ( (EvaluationResultVO) evaluationResult.get(new Integer(vo.getScenarioId()))).getTactics(true).elements();
			
			EvaluatedTacticsVO inCopy = vo.getTactics();
			EvaluatedTacticsVO localCopy = null;				
			for(;enumLocal.hasMoreElements() && true;){
				localCopy = (EvaluatedTacticsVO) enumLocal.nextElement();
				if(inCopy.getTacticId() == localCopy.getTacticId()){
					//System.out.println("Removing Tactic..." + inCopy.getTacticId());
					((EvaluationResultVO)evaluationResult.get(new Integer(vo.getScenarioId()))).getTactics(true).
													   remove(inCopy.getTacticId());
					found = true;
					break;
				}
			}
			//remove scenarios that don't have a tactic
			if(((EvaluationResultVO)evaluationResult.get(new Integer(vo.getScenarioId()))).getTactics().length <= 0){
				evaluationResult.remove(new Integer(vo.getScenarioId()));
			}

		}
	}
}