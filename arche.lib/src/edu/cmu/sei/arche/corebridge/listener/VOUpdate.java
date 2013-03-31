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

package edu.cmu.sei.arche.corebridge.listener;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import jess.Context;
import jess.Deftemplate;
import jess.Fact;
import jess.Funcall;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;

import org.xml.sax.SAXException;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.rfconfig.ConfigReader;
import edu.cmu.sei.arche.vo.AnalysisResultVO;
import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.EvaluatedTacticsVO;
import edu.cmu.sei.arche.vo.EvaluationResultVO;
import edu.cmu.sei.arche.vo.FunctionResponsibilityMapVO;
import edu.cmu.sei.arche.vo.FunctionVO;
import edu.cmu.sei.arche.vo.ModelElementTypeVO;
import edu.cmu.sei.arche.vo.ModelElementVO;
import edu.cmu.sei.arche.vo.ModelRelationTypeVO;
import edu.cmu.sei.arche.vo.ModelRelationVO;
import edu.cmu.sei.arche.vo.OperandsVO;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.RawEvaluationResultVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.RelationshipVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;
import edu.cmu.sei.arche.vo.ScenarioPartMetadataVO;
import edu.cmu.sei.arche.vo.ScenarioPartVO;
import edu.cmu.sei.arche.vo.ScenarioResponsibilityMapVO;
import edu.cmu.sei.arche.vo.ScenarioTypeVO;
import edu.cmu.sei.arche.vo.ScenarioVO;
import edu.cmu.sei.arche.vo.TreeVO;
import edu.cmu.sei.arche.vo.ViewElementTypeVO;
import edu.cmu.sei.arche.vo.ViewElementVO;
import edu.cmu.sei.arche.vo.ViewRelationTypeVO;
import edu.cmu.sei.arche.vo.ViewRelationVO;

/**
 * It's called by CoreListener when Jess notifies that a fact was asserted, modified or retracted.
 * The only public method available, <code>parseFactAndUpdateVO</code>, gets a jess.Fact object
 * and extracts the slot values from it using the Jess java API. Then, based on the type of fact,
 * the method simply creates, updates or deletes the corresponding VO object.
 * <p>
 * Important: the Fact object passed by Jess to the listener is meant to be read-only. The VO object
 * properties can reference contents in the slots, but no part of the application should set values
 * direclty in a VO that maps to a fact. That should happen only in this VOUpdate class.
 * 
 * @author Neel Mullick
 * @author Paulo Merson
 * @author Hyunwoo Kim
 */
public class VOUpdate {

    
     /* Represents the UI layer that is currently displaying the data of the factbase that this
     * object is receiving. The reference to the UI is needed by the VO objects so that they know
     * what UI to call to flag views to refresh.
     */
    private IRefreshableUI ui;

    /**
     * The ProjectVO object is the entry point to access all the facts in memory. VOUpdate will
     * needs this reference so that when a new fact is asserted/modified/retracted, we can
     * add/change/remove the corresponding VO to this project.
     */
    private ProjectVO      project;

    /**
     * Maps: int fact id --> VO object
     * <p>
     * When we need to change or delete a VO object because of fact modification or retraction, we
     * need to locate that VO object. Instead of walking the data structures from ProjectVO, this
     * data structure offers a constant time performance access to all VOs that were created by this
     * VOUpdate instance.
     */
    private Hashtable      factIndexById;

    /**
     * Maps: String fact type --> RelationshipTypeVO object
     * <p>
     * Stores all relationship types between responsibilities. It allows a quick lookup to check if
     * a given fact that has been asserted is a relationship between responsibilities. Keys in this
     * Hashtable are the string values in RelationshipTypeVO.id of all reasoning frameworks--it
     * doesn't matter if the RF is active or not because we need to update the VOs upon core changes
     * for all RFs. Values in this Hashtable are the corresponding RelationshipTypeVO instance.
     */
    private Hashtable      respoRelationTypes;

    /**
     * Maps: String fact type --> ParameterTypeVO object
     * <p>
     * Store all parameter types (of responsibilities and relationships). It allows a quick lookup
     * to check if a given fact that has been asserted is a parameter of a responsibility or
     * relationship. We need to look at slot source in the actual parameter fact to determine if the
     * source is a responsibility or relationship. Keys in this Hashtable are the string values in
     * ParameterTypeVO.id of all reasoning frameworks--it doesn't matter if the RF is active or not
     * because we need to update the VOs upon core changes for all RFs. Values in this Hashtable are
     * the corresponding ParameterTypeVO instance.
     */
    private Hashtable      allParamTypes;

    /**
     * Maps: String fact type --> ScenarioTypeVO object
     */
    private Hashtable      scenarioTypes;

    /**
     * Maps: String fact type --> ModelElementTypeVO object
     * <p>
     * Store all model element types. It allows a quick lookup to check if a given fact that has
     * been asserted/modified/retracted is one of the model elements or not. We need to look at the
     * name of the fact asserted/modified/retracted to see if it is a model element or not. That
     * name will be compared with the keys in this HashTable. Keys in this Hashtable are the string
     * values in ModelElementTypeVO.factType of all reasoning frameworks--it doesn't matter if the
     * RF is active or not because we need to update the VOs upon core changes for all RFs.
     */
    private Hashtable      allModelElementTypes;

    /**
     * Maps: String fact type --> ModelRelationTypeVO object
     * <p>
     * Store all model relation types. It allows a quick lookup to check if a given fact that has
     * been asserted/modified/retracted is one of the model relations or not. We need to look at the
     * name of the fact asserted/modified/retracted to see if it is a model relation or not. That
     * name will be compared with the keys in this HashTable. The keys in this Hashtable are the
     * string values in ModelRelationTypeVO.factType of all reasoning frameworks--it doesn't matter
     * if the RF is active or not because we need to update the VOs upon core changes for all RFs.
     */
    private Hashtable      allModelRelationTypes;

    /**
     * Maps: String fact type --> DesignElementTypeVO object
     * <p>
     * Store all design element types. It allows a quick lookup to check if a given fact that has
     * been asserted/modified/retracted is one of the design elements or not. We need to look at the
     * name of the fact asserted/modified/retracted to see if it is a design element or not. That
     * name will be compared with the keys in this HashTable. Keys in this Hashtable are the string
     * values in DesignElementTypeVO.factType--we need to update the VOs upon changes in the Core.
     */
    private Hashtable      allDesignElementTypes;

    /**
     * Maps: String fact type --> DesignRelationTypeVO object
     * <p>
     * Store all design relation types. It allows a quick lookup to check if a given fact that has
     * been asserted/modified/retracted is one of the design relations or not. We need to look at
     * the name of the fact asserted/modified/retracted to see if it is a design relation or not.
     * That name will be compared with the keys in this HashTable. The keys in this Hashtable are
     * the string values in DesignRelationTypeVO.factType--we need to update the VOs upon changes in
     * the Core.
     */
    private Hashtable      allDesignRelationTypes;
    private Hashtable      allTreeTypes;
    
    
    public void setRefreshableUI(IRefreshableUI ui){
    	this.ui = ui;
    }
    
    public void setProjectVO(ProjectVO project){
    	this.project = project;
    }
    
    
    /**
     * 
     * 
     */

    /**
     * Constructor initializes data structures that allow optimized searches for specific facts.
     * 
     * @param ui represents the UI layer that is currently displaying the data of the factbase that
     *            this object is listening to. The reference to the UI is needed further ahead by
     *            the VO objects so that they know what UI to call to flag views to refresh.
     * @param project The ProjectVO object is the entry point to access all the facts in memory.
     *            VOUpdate needs this reference so that when a new fact is
     *            assertedasserted/modified/retracted, we can add/change/remove the corresponding VO
     *            to this project.
     * @param designElementTypes List of design element type definitions
     * @param designRelationTypes List of design relation type definitions
     */
    public VOUpdate(IRefreshableUI ui, ProjectVO project, List designElementTypes,
            List designRelationTypes, List trees) {
        this.ui = ui;
        this.project = project;

        reset(designElementTypes,designRelationTypes,trees);
    }
    
    public void reset(List designElementTypes,
            List designRelationTypes, List trees){
        // Initialize the following Hashtables
        //0. responsibility relationship types.
        //1. parameter types
        //2. scenario types
        //3. model element types
        //4. model relation types
        //5. design element types
        //6. design relation types
    	
     	if(factIndexById == null)
     		factIndexById = new Hashtable();
    	else
    		factIndexById.clear();
    	
    	if(respoRelationTypes == null)
    		respoRelationTypes = new Hashtable();
    	else
    		respoRelationTypes.clear();
        
    	
    	if(allParamTypes == null)
    		allParamTypes = new Hashtable();
    	else
    		allParamTypes.clear();

    	if(scenarioTypes == null)
    		scenarioTypes = new Hashtable();
    	else
    		scenarioTypes.clear();

    	if(allModelElementTypes == null)
    		allModelElementTypes = new Hashtable();
    	else
    		allModelElementTypes.clear();

    	if(allModelRelationTypes == null)
    		allModelRelationTypes = new Hashtable();
    	else
    		allModelRelationTypes.clear();

    	
    	if(allDesignElementTypes == null)
    		allDesignElementTypes = new Hashtable();
    	else
    		allDesignElementTypes.clear();

        for (Iterator it = designElementTypes.iterator(); it.hasNext();) {
        	ViewElementTypeVO designElementType  = (ViewElementTypeVO) it.next();
        	allDesignElementTypes.put(designElementType.getFactType(), designElementType);
        }           
    	
    	if(allDesignRelationTypes == null)
    		allDesignRelationTypes = new Hashtable();
    	else
    		allDesignRelationTypes.clear();

        for (Iterator it = designRelationTypes.iterator(); it.hasNext();) {
        	ViewRelationTypeVO designRelationType  = (ViewRelationTypeVO) it.next();
        	allDesignRelationTypes.put(designRelationType.getFactType(), designRelationType);
        }           
    	
        // TODO: how do we support Tree types?
    	if(allTreeTypes == null)
    		allTreeTypes = new Hashtable();
    	else
    		allTreeTypes.clear();
        
        // tree types
        for (Iterator it = trees.iterator(); it.hasNext();) {
            TreeVO tree = (TreeVO) it.next();
            allTreeTypes.put(tree.getFactType(), tree);
        }           
    	
//        // In the loop below, itt doesn't matter if the RF is active or not because we need to
//        // update the VOs upon core changes for all RFs, so we load all of them. That's why we
//        // get all keys from getActiveRFs() but we don't check whether the mapped value is true or
//        // not
//        for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
//            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
//            
//            // loop through relationship types of that RF
//            for (Iterator it = rf.getRelationshipTypes().iterator(); it.hasNext();) {
//                RelationshipTypeVO relationType = (RelationshipTypeVO) it.next();
//                // loop through allowed operands of that relationship type
//                for (Iterator it2 = relationType.getAllowedOperands().iterator(); it2.hasNext();) {
//                    OperandsVO operands = (OperandsVO) it2.next();
//                    if (operands.getLhs().equals(OperandsVO.RESPONSIBILITY)
//                            && operands.getRhs().equals(OperandsVO.RESPONSIBILITY)) {
//                        // Found a relationship type between responsibilities.
//                        respoRelationTypes.put(relationType.getId(), relationType);
//                    }
//                }
//            }
//            
//            // param types associated with responsibilities
//            for (Iterator it = rf.getRespParamTypes().iterator(); it.hasNext();) {
//                ParameterTypeVO paramType = (ParameterTypeVO) it.next();
//                allParamTypes.put(paramType.getId(), paramType);
//            }
//            // param types associated with relationships
//            for (Iterator it = rf.getRelationshipTypes().iterator(); it.hasNext();) {
//                RelationshipTypeVO relType = (RelationshipTypeVO) it.next();
//                for (Iterator it2 = relType.getParamTypes().iterator(); it2.hasNext();) {
//                    ParameterTypeVO paramType = (ParameterTypeVO) it2.next();
//                    allParamTypes.put(paramType.getId(), paramType);
//                }
//            }
//            // scenario types
//            for (Iterator it = rf.getScenarioTypes().iterator(); it.hasNext();) {
//                ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
//                scenarioTypes.put(scenarioType.getId(), scenarioType);
//            }
//
//            // model element types
//            for (Iterator it = rf.getModelElementTypes().iterator(); it.hasNext();) {
//                ModelElementTypeVO modelElementType = (ModelElementTypeVO) it.next();
//                allModelElementTypes.put(modelElementType.getFactType(), modelElementType);
//            }
//
//            // model relation types
//            for (Iterator it = rf.getModelRelationTypes().iterator(); it.hasNext();) {
//                ModelRelationTypeVO modelRelationType = (ModelRelationTypeVO) it.next();
//                allModelRelationTypes.put(modelRelationType.getFactType(), modelRelationType);
//            }
//
//            // design element types
//            for (Iterator it = rf.getViewElementTypes().iterator(); it.hasNext();) {
//                ViewElementTypeVO designElementType = (ViewElementTypeVO) it.next();
//                allDesignElementTypes.put(designElementType.getFactType(), designElementType);
//            }
//
//            // design relation types
//            for (Iterator it = rf.getViewRelationTypes().iterator(); it.hasNext();) {
//                ViewRelationTypeVO designRelationType = (ViewRelationTypeVO) it.next();
//                allDesignRelationTypes.put(designRelationType.getFactType(), designRelationType);
//            }
//            
//        }    	
        
    }

    /**
     * Gets a jess.Fact object and extracts the slot values from it using the Jess java API. Then,
     * based on the type of fact, the method simply creates, updates or deletes the corresponding VO
     * object.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to this method.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    public void parseFactAndUpdateVO(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {

    	
        if (jFact == null) {
            System.err.println("[VOUpdate] jFact IS NULL!!!");
        } else {
        //                System.out.println("[VOUpdate] "
        //                        + (statusFact == CoreListener.ASSERT ? "ASSERT "
        //                                : (statusFact == CoreListener.MODIFY ? "MODIFY " : "RETRACT"))
        //                        + " fact (" + jFact.getFactId() + ") " + jFact.getName());
        }
        
        if (jFact.getName().equalsIgnoreCase("MAIN::Responsibilities")) {
            parseAndUpdateResponsibility(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("MAIN::Scenarios")) {
            parseAndUpdateScenario(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("MAIN::Function")) {
            parseAndUpdateFunction(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("MAIN::TranslationRelation")) {
            parseAndUpdateTranslationRelation(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("MAIN::P_AnalysisResult")) {
            parseAndUpdateScenarioStatus(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("MAIN::AskQuestion")) {
            parseAndUpdateAskQuestion(jFact, contextArchE, statusFact);
        } else if (jFact.getName().equalsIgnoreCase("Seeker::EvaluationResult")) {
        	//System.out.println(jFact.getName() + "===" + jFact.getFactId() + "Status = " + statusFact);
        	parseEvaluationResult(jFact, contextArchE, statusFact);
        } else if (respoRelationTypes.containsKey(jFact.getName())) {
            parseAndUpdateRelationship(jFact, contextArchE, statusFact);
        } else if (allParamTypes.containsKey(jFact.getName())) {
            parseAndUpdateParameter(jFact, contextArchE, statusFact);
        } else if (allModelElementTypes.containsKey(jFact.getName())) {
            parseAndUpdateModelElement(jFact, contextArchE, statusFact);
        } else if (allModelRelationTypes.containsKey(jFact.getName())) {
            parseAndUpdateModelRelation(jFact, contextArchE, statusFact);
        } else if (allDesignElementTypes.containsKey(jFact.getName())) {
            parseAndUpdateDesignElement(jFact, contextArchE, statusFact);
        } else if (allDesignRelationTypes.containsKey(jFact.getName())) {
            parseAndUpdateDesignRelation(jFact, contextArchE, statusFact);
        } else if (allTreeTypes.containsKey(jFact.getName())) {
            parseAndUpdateTree(jFact, contextArchE, statusFact);
	    } else if (jFact.getName().equalsIgnoreCase("ExternalInteraction::ReasoningFramework")) {
	        parseAndUpdateExternalReasoningFramework(jFact, contextArchE, statusFact);
	    } else if (jFact.getName().equalsIgnoreCase("Seeker::UI_RefreshViews")) {
	        if (statusFact == CoreListener.ASSERT){        
	        	ui.refreshViews();	    	
	        }
	    }
    }

    private void parseAndUpdateExternalReasoningFramework(Fact fact, Context contextArchE, int statusFact) {
    	
    	//TODO Update the hashtable for active reasoning frameworks
    	String id = null;
    	String rfconfigfile = null;
    	try {
			id = fact.getSlotValue("id").symbolValue(contextArchE);
			rfconfigfile = fact.getSlotValue("rfconfigfile").symbolValue(contextArchE);
		} catch (JessException e) {
			e.printStackTrace();
		}		

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
        	try {
        		boolean bNew = true;
	        	String installPathName = rfconfigfile.substring(0,rfconfigfile.lastIndexOf("\\"));
	        	ReasoningFrameworkVO rfVO = ConfigReader.parse(rfconfigfile);
	        	rfVO.setInstallPathName(installPathName);
	        	Boolean active = new Boolean(true);

	            for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
	                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
	                if(rf.getId().equalsIgnoreCase(rfVO.getId())){
		                if (((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
		                	bNew = false;
		                	break;
		                }
		                else{
		    	        	project.getActiveRFs().put(rf, active);		               
		                    ui.flagViewsToRefresh(ReasoningFrameworkVO.class);
		                    bNew = false;
		                	break;
		                }
	                }
	            }

	            if(bNew){
	            	project.getActiveRFs().put(rfVO, active);		  
	            }
	        	
	            // Update the following Hashtables
	        	//1. responsibility relation types
	            //2. parameter types
	            //3. scenario types
	            //4. model element types
	            //5. model relation types
	            //4. view element types
	            //5. view relation types
	        	
	            for (Iterator it1 = rfVO.getRelationshipTypes().iterator(); it1.hasNext();) {
	                RelationshipTypeVO relationType = (RelationshipTypeVO) it1.next();
	                relationType.setExternal(true);	                
	                // loop through allowed operands of that relationship type
	                for (Iterator it2 = relationType.getAllowedOperands().iterator(); it2.hasNext();) {
	                    OperandsVO operands = (OperandsVO) it2.next();	                    
	                    if (operands.getLhs().equals(OperandsVO.RESPONSIBILITY)
	                            && operands.getRhs().equals(OperandsVO.RESPONSIBILITY)) {
	                        // Found a relationship type between responsibilities.
//		                    	String factRespRelationTypeName = "ExternalInteraction::" + relationType.getId();
	                    	String factRespRelationTypeName = relationType.getId();
	                    	if(!respoRelationTypes.containsKey(factRespRelationTypeName)){
	                    		respoRelationTypes.put(factRespRelationTypeName, relationType);				                    		
	                        	// Find the fact template for the given relation type
	                        	// If not found, define it in the working memory	                    		
	                        	Deftemplate d;
								try {
									d = contextArchE.getEngine().findDeftemplate(factRespRelationTypeName);
		                        	if(d == null){
		                        		d = new Deftemplate(factRespRelationTypeName, "A " + relationType.getName() + " relation", contextArchE.getEngine());
//		                        		d.addSlot("scenario", Funcall.NIL, "OBJECT");
		                        		d.addSlot("id", Funcall.NIL, "STRING");
		                        		d.addSlot("source", Funcall.NIL, "SYMBOL");
		                        		d.addSlot("parent", Funcall.NIL, "OBJECT");
		                        		d.addSlot("child", Funcall.NIL, "OBJECT");
		                        		contextArchE.getEngine().addDeftemplate(d);        		        		
	
		                        		// New element into 'Project' and 'Design' factsets.
		                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + factRespRelationTypeName + "\" 2 TRUE)"); 
		                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + factRespRelationTypeName + "\" 2 FALSE)"); 
		                        	}
		                        	else{
		                        		contextArchE.getEngine().removeFacts(d.getName());		                        		
		                        	}
		                        	
		                    		// Add rules for consistency checking between responsibilities and this relations 
		                    		String typeName = factRespRelationTypeName.substring(factRespRelationTypeName.indexOf("::")+2, factRespRelationTypeName.length());
			                    	
		                    		if(contextArchE.getEngine().findDefrule("ManageResponsibilities::ResponsibilityChildConsistency_" + typeName ) == null){
		                    			String newRule = "(defrule ManageResponsibilities::ResponsibilityChildConsistency_" + typeName +" \n" +
		                    			"(declare (salience 100)) \n" +
//	                        			    "(declare (auto-focus TRUE))\n" +
                        			    "?c <- (Planner::C_DeleteResponsibility (applyTo ?re))\n" +
                        			    "?rrr <- (" + factRespRelationTypeName + " (child ?re))\n" +
										"=>\n" +
										"(retract ?rrr)\n" +
									    "(printout t \"------------" + factRespRelationTypeName + "_ResponsibilityChildConsistency!!!!\"  crlf)\n" +
										"(assert (Planner::C_RequirementsChanged))\n" +
										")";		
		                    			contextArchE.getEngine().eval(newRule, contextArchE);
		                    			
//			                    			newRule = "(defrule " + factRespRelationTypeName + "_ResponsibilityParentConsistency \n" +
		                    			newRule = "(defrule ManageResponsibilities::ResponsibilityParentConsistency_" + typeName +" \n" +
		                    			"(declare (salience 100)) \n" +
//	                        			    "(declare (auto-focus TRUE))\n" +
                        			    "?c <- (Planner::C_DeleteResponsibility (applyTo ?re))\n" +
                        			    "?rrr <- (" + factRespRelationTypeName + " (parent ?re))\n" +
										"=>\n" +
										"(retract ?rrr)\n" +
									    "(printout t \"------------" + factRespRelationTypeName + "_ResponsibilityParentConsistency!!!!\"  crlf)\n" +
										"(assert (Planner::C_RequirementsChanged))\n" +
										")";		
		                    			contextArchE.getEngine().eval(newRule, contextArchE);
		                        	}			                        	
								} catch (JessException e) {
									e.printStackTrace();
								}	                    		
	                    	}
	                    }
	                }
	            }    		            	            
	            
                // param types associated with responsibilities
                for (Iterator it = rfVO.getRespParamTypes().iterator(); it.hasNext();) {
                    ParameterTypeVO paramType = (ParameterTypeVO) it.next();
                	if(!allParamTypes.containsKey(paramType.getId())){
                		allParamTypes.put(paramType.getId(), paramType);	                		
                		
                    	String fullFactID = paramType.getId();	                		
						try {
							Deftemplate d = contextArchE.getEngine().findDeftemplate(fullFactID);
                        	if(d == null){
                    			// Define and add a new fact template for a parameter
                        		defineAndAddJessFactTemplateForParameter(fullFactID,paramType,contextArchE.getEngine());
                        	}
                        	else{
                        		contextArchE.getEngine().removeFacts(d.getName());
                        		modifyJessFactTemplateForParameter(d,paramType,contextArchE.getEngine());
                        	}
                        	
                    		// New element into 'Project' and 'Design' factsets.
                    		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactID + "\" 5 TRUE)"); 
                    		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactID + "\" 5 FALSE)"); 
                        		
						} catch (JessException e) {
							e.printStackTrace();
						}	                    			                		
                	}
                }
                // param types associated with relationships
                for (Iterator it = rfVO.getRelationshipTypes().iterator(); it.hasNext();) {
                    RelationshipTypeVO relType = (RelationshipTypeVO) it.next();
                    for (Iterator it2 = relType.getParamTypes().iterator(); it2.hasNext();) {
                        ParameterTypeVO paramType = (ParameterTypeVO) it2.next();
                    	if(!allParamTypes.containsKey(paramType.getId())){
	                		allParamTypes.put(paramType.getId(), paramType);	                				                        	

	                		String fullFactID = paramType.getId();	                		
							try {
								Deftemplate d = contextArchE.getEngine().findDeftemplate(fullFactID);
	                        	if(d == null){
                        			// Define and add a new fact template for a parameter
	                        		defineAndAddJessFactTemplateForParameter(fullFactID,paramType,contextArchE.getEngine());
	                        	}
	                        	else{
	                        		contextArchE.getEngine().removeFacts(d.getName());
	                        		modifyJessFactTemplateForParameter(d,paramType,contextArchE.getEngine());
	                        	}

                        		// New element into 'Project' and 'Design' factsets.
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactID + "\" 5 TRUE)"); 
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactID + "\" 5 FALSE)"); 		                        		
	                        	
							} catch (JessException e) {
								e.printStackTrace();
							}	                    			                			                    		
                    	}
                    }
                }
                // scenario types
                for (Iterator it = rfVO.getScenarioTypes().iterator(); it.hasNext();) {
                    ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
                	if(!scenarioTypes.containsKey(scenarioType.getId()))
                		scenarioTypes.put(scenarioType.getId(), scenarioType);
                }

                // model element types
                for (Iterator it = rfVO.getModelElementTypes().iterator(); it.hasNext();) {
                    ModelElementTypeVO modelElementType = (ModelElementTypeVO) it.next();
                	if(!allModelElementTypes.containsKey(modelElementType.getFactType())){
                		// Add this object to the list
                		allModelElementTypes.put(modelElementType.getFactType(), modelElementType);
                		
                    	String fullFactType = modelElementType.getFactType();
                    	Deftemplate d;
						try {
							d = contextArchE.getEngine().findDeftemplate(fullFactType);
                    		String[] paramSlotTypes = modelElementType.getParamSlotTypes();
                    		String[] paramSlotNames = modelElementType.getParamSlotNames();
                    		
                        	if(d == null){                        		
                    			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                        		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			
                        			// Define and add a new fact template corresponding to the new user-defined input type
                        			defineAndAddJessFactTemplate(fullFactType,paramSlotTypes,paramSlotNames,contextArchE.getEngine())	                        			;
                            		// New element into 'Project' and 'Design' factsets.
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 3 TRUE)"); 
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 3 FALSE)"); 		                        		
                                	
                        		}
                        	}
                        	else{
                        		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			                       			
	                        		contextArchE.getEngine().removeFacts(d.getName());
                        			modifyJessFactTemplate(d,paramSlotTypes,paramSlotNames,contextArchE.getEngine())	                        			;
                            		// New element into 'Project' and 'Design' factsets.
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 3 TRUE)"); 
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 3 FALSE)"); 		                        		
                                	
                        		}
                        	}
                        	
						} catch (JessException e) {
							e.printStackTrace();
						}	                    		
                	}
                }

                // model relation types
                for (Iterator it = rfVO.getModelRelationTypes().iterator(); it.hasNext();) {
                    ModelRelationTypeVO modelRelationType = (ModelRelationTypeVO) it.next();
                	if(!allModelRelationTypes.containsKey(modelRelationType.getFactType())){
                		// Add this object to the list
                		allModelRelationTypes.put(modelRelationType.getFactType(), modelRelationType);
                		
                    	String fullFactType = modelRelationType.getFactType();
                    	Deftemplate d;
						try {
							d = contextArchE.getEngine().findDeftemplate(fullFactType);
                    		String[] paramSlotTypes = modelRelationType.getParamSlotTypes();
                    		String[] paramSlotNames = modelRelationType.getParamSlotNames();

                    		if(d == null){                        		
                    			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                        		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			
                        			// Define and add a new fact template corresponding to the new user-defined input type
                        			defineAndAddJessFactTemplate(fullFactType,paramSlotTypes,paramSlotNames,contextArchE.getEngine())	                        			;
                            		// New element into 'Project' and 'Design' factsets.
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 4 TRUE)"); 
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 4 FALSE)"); 	                        		
                        		}
                        	}
                        	else{
                        		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			                       			
	                        		contextArchE.getEngine().removeFacts(d.getName());
                        			modifyJessFactTemplate(d,paramSlotTypes,paramSlotNames,contextArchE.getEngine())	                        			;
                            		// New element into 'Project' and 'Design' factsets.
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 4 TRUE)"); 
                            		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 4 FALSE)"); 	                        		
                        		}
                        	}
                			
                    		
						} catch (JessException e) {
							e.printStackTrace();
						}	                    			                		
                	}
                }
                
                // view element types
                for (Iterator it = rfVO.getViewElementTypes().iterator(); it.hasNext();) {
                    ViewElementTypeVO viewElementType = (ViewElementTypeVO) it.next();
                	String fullFactType = viewElementType.getFactType();
                	Deftemplate d;
					try {
                		// Add this object to the list
                    	if(!allDesignElementTypes.containsKey(viewElementType.getFactType())){
                    		allDesignElementTypes.put(viewElementType.getFactType(), viewElementType);
                    	}
                    	else{
                    		allDesignElementTypes.remove(viewElementType.getFactType());
                    		allDesignElementTypes.put(viewElementType.getFactType(), viewElementType);
                    	}
                		
						d = contextArchE.getEngine().findDeftemplate(fullFactType);
                		String[] paramSlotTypes = viewElementType.getParamSlotTypes();
                		String[] paramSlotNames = viewElementType.getParamSlotNames();

                    	if(d == null){
                			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                    		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			
                    			
                    			// Define and add a new fact template corresponding to the new user-defined input type
                    			defineAndAddJessFactTemplate(fullFactType,paramSlotTypes,paramSlotNames,contextArchE.getEngine());
                    			
//        							d = contextArchE.getEngine().findDeftemplate(fullFactType);
//        							d.addSlot("test", new Value("test",RU.STRING), "STRING");
//        							contextArchE.getEngine().addDeftemplate(d);
//                        			defineAndAddJessFactTemplate(fullFactType,paramSlotTypes,paramSlotNames,contextArchE.getEngine());
                    			
                        		// New element into 'Project' and 'Design' factsets.
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 3 TRUE)"); 
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 3 FALSE)"); 		                        		
                    		}
                    	}
                    	else{
                			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                    		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			                        			                        			
                    			// Delete existing facts of this type 
                        		contextArchE.getEngine().removeFacts(d.getName());

                    			// Modify the existing fact template
                        		modifyJessFactTemplate(d,paramSlotTypes,paramSlotNames,contextArchE.getEngine());                        		
                    			
                        		// New element or update into 'Project' and 'Design' factsets.
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 3 TRUE)"); 
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 3 FALSE)"); 		                        		
                    		}                        		
//                        		//for testing
//    							d.addSlot("test3", new Value("test3",RU.STRING), "STRING");
//    							contextArchE.getEngine().addDeftemplate(d);
							
                    	}
                    	
					} catch (JessException e) {
						e.printStackTrace();
					}	                    		
                }
                
                // view relation types
                for (Iterator it = rfVO.getViewRelationTypes().iterator(); it.hasNext();) {
                	ViewRelationTypeVO viewRelationType = (ViewRelationTypeVO) it.next();
                	String fullFactType = viewRelationType.getFactType();
                	Deftemplate d;
					try {
                		// Add this object to the list
                    	if(!allDesignRelationTypes.containsKey(viewRelationType.getFactType())){
                    		allDesignRelationTypes.put(viewRelationType.getFactType(), viewRelationType);
                    	}
                    	else{
                    		allDesignRelationTypes.remove(viewRelationType.getFactType());
                    		allDesignRelationTypes.put(viewRelationType.getFactType(), viewRelationType);
                    	}
						
						d = contextArchE.getEngine().findDeftemplate(fullFactType);
                		String[] paramSlotTypes = viewRelationType.getParamSlotTypes();
                		String[] paramSlotNames = viewRelationType.getParamSlotNames();
                    	if(d == null){
                    		
                			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                    		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			
                    			
                    			// Define and add a new fact template corresponding to the new user-defined input type
                    			defineAndAddJessFactTemplate(fullFactType,paramSlotTypes,paramSlotNames,contextArchE.getEngine())	                        			;
                    			
                        		// New element into 'Project' and 'Design' factsets.
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 4 TRUE)"); 
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 4 FALSE)"); 		                        		
                    		}
                    	}
                    	else{
                			// Supported Jess Types are OBJECT, STRING, FLOAT, INTEGER, not SYMBOL
                    		if(paramSlotTypes != null && paramSlotTypes.length > 0){	                        			                        			                        			
                    			// Delete existing facts of this type 
                        		contextArchE.getEngine().removeFacts(d.getName());

                    			// Modify the existing fact template
                        		modifyJessFactTemplate(d,paramSlotTypes,paramSlotNames,contextArchE.getEngine());                        		
                    			
                        		// New element or update into 'Project' and 'Design' factsets.
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + fullFactType + "\" 4 TRUE)"); 
                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + fullFactType + "\" 4 FALSE)"); 		                        		
                    		}                        		    							
                    	}
					} catch (JessException e) {
						e.printStackTrace();
					}	                    		
                }
                
                // Refresh user questions
	        	String qFilePath = installPathName + "\\" + rfVO.getId() + ".properties";
				File file = new File(qFilePath);
				if(file.exists()){
					BufferedReader in = new BufferedReader(new FileReader(file));   
					String content = "";
					for(String line=in.readLine(); line != null; line=in.readLine())
						content = content + line + "\n";
					
		        	InputStream stream = new ByteArrayInputStream(content.getBytes());
	                ui.refreshQuestions(stream);
				}
	            // When a ReasoningFrameworkVO VO is created or modified, VOUpdate has to do it
	            // because its constructor doesn't do that.
	            ui.flagViewsToRefresh(ReasoningFrameworkVO.class);	                
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ArchEException e) {
				e.printStackTrace();
			}

			
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* If the fact was retracted the appropriate VO will be removed */
        } else {
        	// After ExternalInteraction::ReasoningFramework is removed,
        	
			try {
        	
	        	
				ReasoningFrameworkVO rfVO = ConfigReader.parse(rfconfigfile);
	        	Boolean inactive = new Boolean(false);					
	            for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
	                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
	                if(rf.getId().equalsIgnoreCase(id)){
	                	project.getActiveRFs().remove(rf);
	                	break;
//			                if (((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
//			    	        	project.getActiveRFs().put(rf, inactive);		               
//			    	        	// When a ExternalInteraction::ReasoningFramework fact is deleted, VOUpdate has to do it because there
//			    	            // is no "destructor" in the VO object to do that.
//			    	            ui.flagViewsToRefresh(ReasoningFrameworkVO.class);	  
//			                	break;
//			                }
	                }
				}				

//	            // Update the following Hashtables
//	        	//1. responsibility relation types
//	            //2. parameter types
//	            //3. scenario types
//	            //4. model element types
//	            //5. model relation types
//	        	
	            for (Iterator it = rfVO.getRelationshipTypes().iterator(); it.hasNext();) {
	                RelationshipTypeVO relationType = (RelationshipTypeVO) it.next();
	                // loop through allowed operands of that relationship type
	                for (Iterator it2 = relationType.getAllowedOperands().iterator(); it2.hasNext();) {
	                    OperandsVO operands = (OperandsVO) it2.next();
	                    if (operands.getLhs().equals(OperandsVO.RESPONSIBILITY)
	                            && operands.getRhs().equals(OperandsVO.RESPONSIBILITY)) {
	                        // Found a relationship type between responsibilities.
	                    	String factRespRelationTypeName = relationType.getId();
	                    	if(respoRelationTypes.containsKey(factRespRelationTypeName)){
	                    		// Remove this relation type from UI
	                    		respoRelationTypes.remove(factRespRelationTypeName);
	
//	                    		// Don't remove this relation type (fact template) from the Jess engine                    			
//	                    		
//	//                        	// Find the fact template for the given relation type
//	//                        	// If not found, define it in the working memory	                    		
//	//                        	Deftemplate tRelation;
//	//							try {
//	//								tRelation = contextArchE.getEngine().findDeftemplate(factRespRelationTypeName);
//	//	                        	if(tRelation != null){
//	//	                        		contextArchE.getEngine().remove(tRelation);
//	//	                        		// New element into 'Project' and 'Design' factsets.
//	////	                        		contextArchE.getEngine().eval("(UpdateFactSet \"Project\" \"" + factRespRelationTypeName + "\" 2)"); 
//	////	                        		contextArchE.getEngine().eval("(UpdateFactSet \"Design\" \"" + factRespRelationTypeName + "\" 2)"); 
//	//	                        	}
//	//							} catch (JessException e) {
//	//								e.printStackTrace();
//	//							}	                    		
	                    	}
	                    }
	                }
	            }
	            	            
	            // param types associated with responsibilities
	            for (Iterator it = rfVO.getRespParamTypes().iterator(); it.hasNext();) {
	                ParameterTypeVO paramType = (ParameterTypeVO) it.next();
	            	if(allParamTypes.containsKey(paramType.getId()))
	            		allParamTypes.remove(paramType.getId());
	            }
	            // param types associated with relationships
	            for (Iterator it = rfVO.getRelationshipTypes().iterator(); it.hasNext();) {
	                RelationshipTypeVO relType = (RelationshipTypeVO) it.next();
	                for (Iterator it2 = relType.getParamTypes().iterator(); it2.hasNext();) {
	                    ParameterTypeVO paramType = (ParameterTypeVO) it2.next();
	                	if(allParamTypes.containsKey(paramType.getId()))
	                		allParamTypes.remove(paramType.getId());
	                }
	            }
	            
//	            // scenario types
//	            for (Iterator it = rfVO.getScenarioTypes().iterator(); it.hasNext();) {
//	                ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
//	            	if(scenarioTypes.containsKey(scenarioType.getId()))
//	            		scenarioTypes.remove(scenarioType.getId());
//	            }
	
	            // model element types
	            for (Iterator it = rfVO.getModelElementTypes().iterator(); it.hasNext();) {
	                ModelElementTypeVO modelElementType = (ModelElementTypeVO) it.next();
	            	if(allModelElementTypes.containsKey(modelElementType.getFactType()))
	            		allModelElementTypes.remove(modelElementType.getFactType());
	            }
	
	            // model relation types
	            for (Iterator it = rfVO.getModelRelationTypes().iterator(); it.hasNext();) {
	                ModelRelationTypeVO modelRelationType = (ModelRelationTypeVO) it.next();
	            	if(allModelRelationTypes.containsKey(modelRelationType.getFactType()))
	            		allModelRelationTypes.remove(modelRelationType.getFactType());
	            }
	        		        	        	
	            // view element types
	            for (Iterator it = rfVO.getViewElementTypes().iterator(); it.hasNext();) {
	            	ViewElementTypeVO viewElementType = (ViewElementTypeVO) it.next();
	            	if(allDesignElementTypes.containsKey(viewElementType.getFactType()))
	            		allDesignElementTypes.remove(viewElementType.getFactType());
	            }

	            // view relation types
	            for (Iterator it = rfVO.getViewRelationTypes().iterator(); it.hasNext();) {
	            	ViewRelationTypeVO viewRelationType = (ViewRelationTypeVO) it.next();
	            	if(allDesignRelationTypes.containsKey(viewRelationType.getFactType()))
	            		allDesignRelationTypes.remove(viewRelationType.getFactType());
	            }
	            
	            // When a ExternalInteraction::ReasoningFramework fact is deleted, VOUpdate has to do it because there
	            // is no "destructor" in the VO object to do that.
	            ui.flagViewsToRefresh(ReasoningFrameworkVO.class);	  
	            
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ArchEException e) {
				e.printStackTrace();
			}
        }				
	}

	/**
     * Jess uses integer values as unique ids for facts. However, Jess doesn't work with these
     * values consistently throughout the API. So, Fact.getFactId() returns an int value (say,
     * 1234). OTOH, when a slot contains a reference to a fact id, it stores it as a string in the
     * format:
     * <p>
     * <code><Fact-999></code> where 999 is the integer fact id.
     * <p>
     * 
     * @param strFactId
     * @return This method returns the int value given a string in that format. If the argument is
     *         null or in an invalid format, it prints a message to the standard error and returns
     *         -1.
     */
    public static int strToIntFactId(String strFactId) {
        int factId;
        if (strFactId == null) {
            //System.err.println("[VOUpdate.strToInFactId] Argument is null!");
            return -1;
        }
        int i = strFactId.indexOf((int) '-');
        int j = strFactId.indexOf((int) '>');
        try {
            if (i > 0 && j > 0) {
                factId = Integer.parseInt(strFactId.substring(i + 1, j));
            } else {
                // format is different; maybe the string contains the clean integer value
                factId = Integer.parseInt(strFactId);
            }
        } catch (NumberFormatException e) {
            //System.err.println("[VOUpdate.strToInFactId] value of fact id in slot not parseable: "
            //        + strFactId);
            factId = -1;
        }
        return factId;
    }
    
    /**
     * Converts the string value of the stored segments in the facts to a double value. 
     * @param strValue
     * @return This method returns the double value given a string in that format. If the argument is
     *         null or in an invalid format, it returns -1.0
     */
    public static double strToDouble(String strValue){
    	
    	if(strValue == null || strValue.equals("")){
    		return -1.0;
    	}
    	try {
    		return Double.valueOf(strValue.trim()).doubleValue();
         } catch (NumberFormatException nfe) {
        	 return -1.0;
         }
    }
    
    /**
     * Many facts have a slot "source" that stores as a string the origin of that fact, which can be
     * "User", "ArchE" or "nil". In the VO objects, the source is stored as an int value, using the
     * constants defined in CoreFact.
     * 
     * @param strSource
     * @return This method returns the int constant value given a string value of a source slot. If
     *         the passed argument is null or an invalid value, it also returns CoreFact.NIL.
     */
    public static int strToIntSource(String strSource) {
        int source;
        if (strSource == null) {
            //System.err.println("[VOUpdate.strToInSource] Argument is null");
            source = CoreFact.NIL;
        } else if (strSource.equalsIgnoreCase("nil")) {
            source = CoreFact.NIL;
        } else if (strSource.equalsIgnoreCase("User")) {
            source = CoreFact.USER;
        } else if (strSource.equalsIgnoreCase("ArchE")) {
            source = CoreFact.ARCHE_CORE;
        } else {
            //System.err.println("[VOUpdate.strToInSource] Invalid argument");
            source = CoreFact.NIL;
        }
        return source;
    }

    /**
     * Many facts have a slot that could have either of the following values -"true" or "false" In
     * the ArchE Core however, it is maintained as a string varaible. For the sake of value objects,
     * however these are stored as boolean variables. This method takes a string value and returns
     * the boolean equivalent.
     * 
     * @param strAlmostBoolean The string value that is to be converted to its boolean equivalent.
     * @return This method returns the boolean equivalent of the string passed as a parameter. It
     *         returns true if the string was "true"; false otherwise.
     */
    public static boolean strToBoolValue(String strAlmostBoolean) {
        return strAlmostBoolean.equalsIgnoreCase("true") ? true : false;
    }

    /**
     * When a value for a slot is not specified (or set) Jess defaults it to a nil object (Jess
     * specific term). In the VO however this should be reflected as a null or an empty string "".
     * However, in the Eclipse model rendering a null object in the table implies not having the
     * default ability to modify that cell. Therefore this helper method sets the member variable
     * data to "" upon finding such a nil slot.
     * 
     * @param slotVal The slot value being checked for being nil.
     * @return This method returns the an empty string if nil was found to be the content of the
     *         slot's string equivalent or the slot itself.
     */
    public static String checkNilSlot(String slotVal) {
        return slotVal.equalsIgnoreCase("nil") ? "" : slotVal;
    }

    /**
     * Parse a MAIN::Responsibilities fact and create, update or delete a ResponsibilityVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateResponsibility(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {

        int factId = jFact.getFactId();
        String name = checkNilSlot(jFact.getSlotValue("name").stringValue(contextArchE));
        String desc = checkNilSlot(jFact.getSlotValue("description").stringValue(contextArchE));
        int source = strToIntSource(jFact.getSlotValue("source").stringValue(contextArchE));

        if (statusFact == CoreListener.ASSERT) {
            // Create new VO
            ResponsibilityVO vo = new ResponsibilityVO(factId, name, desc, source, ui);
            /*
             * If at the time of assertion of a responsibility fact, there were already parameter
             * facts associated with the responsibility , the references to these parameter facts
             * need to be added to the responsibility VO. This might happen when loading a fact base
             * from a persistent file.
             */
            for (Enumeration e = factIndexById.elements(); e.hasMoreElements();) {
                CoreFact responsibilityParam = (CoreFact) e.nextElement();
                if (responsibilityParam instanceof ParameterVO) {
                    if (((ParameterVO) responsibilityParam).getOwnerFactId() == factId) {
                        vo.getParameters().add((ParameterVO) responsibilityParam);
                    }
                }
            }

            factIndexById.put(new Integer(factId), vo);
            project.getResponsibilities().put(new Integer(factId), vo);
        } else if (statusFact == CoreListener.MODIFY) {
            // Update existing VO
            ResponsibilityVO vo = (ResponsibilityVO) factIndexById.get(new Integer(factId));
            vo.setDescription(desc);
            vo.setName(name);
            vo.setSource(source);
        } else {
            // Remove existing VO
            Integer _factId = new Integer(factId);
            ResponsibilityVO vo = (ResponsibilityVO) factIndexById.get(_factId);
            project.getResponsibilities().remove(_factId);
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ResponsibilityVO.class);
        }
    }

    /**
     * Parse a MAIN::Scenarios fact and create, update or delete a ScenarioVO and ScenarioPartVO
     * objects accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateScenario(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        /* Read values from slots */
        String desc = checkNilSlot(jFact.getSlotValue("description").stringValue(contextArchE));
        String quality = checkNilSlot(jFact.getSlotValue("quality").stringValue(contextArchE));
        // System.out.println("\t\tquality: " + quality);
        String stimulusText = checkNilSlot(jFact.getSlotValue("stimulusText")
                .stringValue(contextArchE));
        String stimulusType = checkNilSlot(jFact.getSlotValue("stimulusType")
                .stringValue(contextArchE));
        String stimulusUnit = checkNilSlot(jFact.getSlotValue("stimulusUnit")
                .stringValue(contextArchE));
        String stimulusValue = checkNilSlot(jFact.getSlotValue("stimulusValue")
                .stringValue(contextArchE));

        String sourceText = checkNilSlot(jFact.getSlotValue("sourceText").stringValue(contextArchE));
        String sourceType = checkNilSlot(jFact.getSlotValue("sourceType").stringValue(contextArchE));
        String sourceUnit = checkNilSlot(jFact.getSlotValue("sourceUnit").stringValue(contextArchE));
        String sourceValue = checkNilSlot(jFact.getSlotValue("sourceValue")
                .stringValue(contextArchE));

        String artifactText = checkNilSlot(jFact.getSlotValue("artifactText")
                .stringValue(contextArchE));
        String artifactType = checkNilSlot(jFact.getSlotValue("artifactType")
                .stringValue(contextArchE));
        String artifactUnit = checkNilSlot(jFact.getSlotValue("artifactUnit")
                .stringValue(contextArchE));
        String artifactValue = checkNilSlot(jFact.getSlotValue("artifactValue")
                .stringValue(contextArchE));

        String environmentText = checkNilSlot(jFact.getSlotValue("environmentText")
                .stringValue(contextArchE));
        String environmentType = checkNilSlot(jFact.getSlotValue("environmentType")
                .stringValue(contextArchE));
        String environmentUnit = checkNilSlot(jFact.getSlotValue("environmentUnit")
                .stringValue(contextArchE));
        String environmentValue = checkNilSlot(jFact.getSlotValue("environmentValue")
                .stringValue(contextArchE));

        String responseText = checkNilSlot(jFact.getSlotValue("responseText")
                .stringValue(contextArchE));
        String responseType = checkNilSlot(jFact.getSlotValue("responseType")
                .stringValue(contextArchE));
        String responseUnit = checkNilSlot(jFact.getSlotValue("responseUnit")
                .stringValue(contextArchE));
        String responseValue = checkNilSlot(jFact.getSlotValue("responseValue")
                .stringValue(contextArchE));

        String measureText = checkNilSlot(jFact.getSlotValue("measureText")
                .stringValue(contextArchE));
        String measureType = checkNilSlot(jFact.getSlotValue("measureType")
                .stringValue(contextArchE));
        String measureUnit = checkNilSlot(jFact.getSlotValue("measureUnit")
                .stringValue(contextArchE));
        String measureValue = checkNilSlot(jFact.getSlotValue("measureValue")
                .stringValue(contextArchE));

        /*
         * There�s no fact exclusively for parts of a scenario. All data is inside the scenario fact.
         * However, ScenarioPartVO extends CoreFact so that it can use the same ui refresh mechanism
         * of other classes. This is why the constructor of the ScenarioPartVO has an integer factId
         * and for the sake of simplicity this id is the same as the id of the parent scenario.
         */
        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new Scenario VO */
            ScenarioVO scenario = new ScenarioVO(factId, quality, desc, false,
                    (ScenarioTypeVO) scenarioTypes.get(quality), ui);
            /* Create the 6 parts of the Scenario VO */
            ScenarioPartVO stimulus = new ScenarioPartVO(factId, ScenarioPartMetadataVO.STIMULUS,
                    stimulusText, stimulusType, stimulusUnit, stimulusValue, ui);
            ScenarioPartVO stimulusSource = new ScenarioPartVO(factId,
                    ScenarioPartMetadataVO.SOURCE_OF_STIMULUS, sourceText, sourceType, sourceUnit,
                    sourceValue, ui);
            ScenarioPartVO artifact = new ScenarioPartVO(factId, ScenarioPartMetadataVO.ARTIFACT,
                    artifactText, artifactType, artifactUnit, artifactValue, ui);
            ScenarioPartVO envmt = new ScenarioPartVO(factId, ScenarioPartMetadataVO.ENVIRONMENT,
                    environmentText, environmentType, environmentUnit, environmentValue, ui);
            ScenarioPartVO response = new ScenarioPartVO(factId, ScenarioPartMetadataVO.RESPONSE,
                    responseText, responseType, responseUnit, responseValue, ui);
            ScenarioPartVO responseMeasure = new ScenarioPartVO(factId,
                    ScenarioPartMetadataVO.RESPONSE_MEASURE, measureText, measureType, measureUnit,
                    measureValue, ui);
            ScenarioPartVO[] scParts = { stimulus, stimulusSource, artifact, envmt, response,
                    responseMeasure};
            /* Set the part of the scenario */
            scenario.setParts(scParts);

            /*
             * If at the time of assertion of a scenario fact, there were already P_AnalysisProperty
             * facts associated with the scenario, the references to these P_AnalysisProperty facts
             * need to be added to the scenario VO. This might happen when loading a fact base from
             * a persistent file.
             */
            for (Enumeration e = factIndexById.elements(); e.hasMoreElements();) {
                CoreFact analysisResult = (CoreFact) e.nextElement();
                if (analysisResult instanceof AnalysisResultVO) {
                    if (((AnalysisResultVO) analysisResult).getOwnerFactId() == factId) {
                        scenario.getAnalysisResults().add((AnalysisResultVO) analysisResult);
                    }
                }
            }
            /* Add the ScenarioVO to the project VO */
            project.getScenarios().add(scenario);
            /* Add the ScenarioVO to the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), scenario);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the scenario VO in the hashtable local to VOUpdate */
            ScenarioVO scenario = (ScenarioVO) factIndexById.get(new Integer(factId));
            /* Update existing VO values */
            scenario.setDescription(desc);
            scenario.setScenarioType((ScenarioTypeVO) scenarioTypes.get(quality));
            ScenarioPartVO stimulus = new ScenarioPartVO(factId, ScenarioPartMetadataVO.STIMULUS,
                    stimulusText, stimulusType, stimulusUnit, stimulusValue, ui);
            ScenarioPartVO stimulusSource = new ScenarioPartVO(factId,
                    ScenarioPartMetadataVO.SOURCE_OF_STIMULUS, sourceText, sourceType, sourceUnit,
                    sourceValue, ui);
            ScenarioPartVO artifact = new ScenarioPartVO(factId, ScenarioPartMetadataVO.ARTIFACT,
                    artifactText, artifactType, artifactUnit, artifactValue, ui);
            ScenarioPartVO envmt = new ScenarioPartVO(factId, ScenarioPartMetadataVO.ENVIRONMENT,
                    environmentText, environmentType, environmentUnit, environmentValue, ui);
            ScenarioPartVO response = new ScenarioPartVO(factId, ScenarioPartMetadataVO.RESPONSE,
                    responseText, responseType, responseUnit, responseValue, ui);
            ScenarioPartVO responseMeasure = new ScenarioPartVO(factId,
                    ScenarioPartMetadataVO.RESPONSE_MEASURE, measureText, measureType, measureUnit,
                    measureValue, ui);
            scenario.getParts()[0] = stimulus;
            scenario.getParts()[1] = stimulusSource;
            scenario.getParts()[2] = artifact;
            scenario.getParts()[3] = envmt;
            scenario.getParts()[4] = response;
            scenario.getParts()[5] = responseMeasure;
            /* If the fact was retracted the appropriate VO will be removed */
        } else {
            /* Find the scenario VO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            ScenarioVO scenario = (ScenarioVO) factIndexById.get(_factId);
            /* Remove the scenario VO from the project VO */
            project.getScenarios().remove(scenario);
            /* Remove the scenario VO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ScenarioVO.class);
        }
    }

    /**
     * Parse a MAIN::P_Analysis fact and create, update or delete a AnalysisResultVO and update the
     * status of the owner Scenario VO.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateScenarioStatus(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Initiliazing the owner fact ids */
//    	 System.out.println("WE are in Update Scenario status");
        int owner = -1;
        /* Read FactId */
        int factId = jFact.getFactId();
//        String globalId = checkNilSlot(jFact.getSlotValue("id").stringValue(contextArchE));
        /* Read values from slots */
        boolean isSatisfied = strToBoolValue(jFact.getSlotValue("isSatisfied")
                .stringValue(contextArchE));
        int source = strToIntSource(jFact.getSlotValue("source").stringValue(contextArchE));
        String value = checkNilSlot(jFact.getSlotValue("value").stringValue(contextArchE));
        value = (value == "") ? "0" : value;
        String previous_value = checkNilSlot(jFact.getSlotValue("oldValue").stringValue(contextArchE));
        previous_value = (previous_value == "") ? "0" : previous_value;

        /*
         * The owner fact ids can be strings or Fact values depending on whether the
         * MAIN::P_AnalysisResult fact was read from a persisted file (in which case they will be
         * strings) or are asserted by the rules inside of the ArchE Core (in which case they will
         * be references to facts therefore returning values of type RU.FACT / RU.EXTERNAL_ADDRESS
         */
        if ((jFact.getSlotValue("owner").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("owner").type() == RU.FACT)) {
            owner = jFact.getSlotValue("owner").factValue(contextArchE).getFactId();
        } else {
            owner = strToIntFactId(jFact.getSlotValue("owner").stringValue(contextArchE));
        }

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new AnalysisResult VO */
//        	 System.out.println("WE are in assert");
//        	 System.out.println("Fact ID  " + factId);
            AnalysisResultVO analysisResult = new AnalysisResultVO(factId, isSatisfied, value,
            		previous_value, owner, source, ui);

            /* Add the reference to this analysis result in the appropriate Scenario VO if it exists */
            if (factIndexById.get(new Integer(owner)) != null) {
                ScenarioVO scenario = (ScenarioVO) factIndexById.get(new Integer(owner));
                scenario.getAnalysisResults().add(analysisResult);
                scenario.setCurrentValue(value);
                scenario.setCurrentIsSatisfied(isSatisfied);
                scenario.setCurrentAnalysisFactId(factId);
            }
            /* Add the AnalysisResultVO to the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), analysisResult);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the scenario VO in the hashtable local to VOUpdate */
//        	 System.out.println("WE are in modify");
            AnalysisResultVO analysisResult = (AnalysisResultVO) factIndexById.get(new Integer(
                    factId));

            /* If owner has changed then the reference needs to be removed form the owner ScenarioVO */
            if (analysisResult.getOwnerFactId() != owner) {

                /*
                 * Remove the reference to this analysis result in the appropriate Scenario VO if it
                 * exists
                 */
                if (factIndexById.get(new Integer(analysisResult.getOwnerFactId())) != null) {
                    ScenarioVO scenario = (ScenarioVO) factIndexById.get(new Integer(analysisResult
                            .getOwnerFactId()));
                    scenario.getAnalysisResults().remove(analysisResult);
                }
                /*
                 * Add the reference to this analysis result in the appropriate Scenario VO if it
                 * exists
                 */
                if (factIndexById.get(new Integer(owner)) != null) {
                    ScenarioVO scenario = (ScenarioVO) factIndexById.get(new Integer(owner));
                    scenario.getAnalysisResults().add(analysisResult);
                    if (scenario.getCurrentAnalysisFactId() != factId){
                       scenario.setCurrentValue(value);
                       scenario.setCurrentIsSatisfied(isSatisfied);
                    }  
                }
                /* Update existing VO value of the ownerFactId */
                analysisResult.setOwnerFactId(owner);
            }
            /* Update other existing VO values */
            analysisResult.setSatisfied(isSatisfied);
            //System.out.println("Value before = " +  analysisResult.getValue());
            //System.out.println("Previous value before = " +  analysisResult.getPreviousValue());
            //analysisResult.setPreviousValue(analysisResult.getValue().toString());
            //System.out.println("Value after = " +  analysisResult.getValue());
            //System.out.println("Previous value after = " +  analysisResult.getPreviousValue());
            analysisResult.setValue(value);
            analysisResult.setPreviousValue(previous_value);
            //System.out.println("new Value after = " +  analysisResult.getValue());
            //System.out.println("Previous value after = " +  analysisResult.getPreviousValue());
            analysisResult.setSource(source);
            /* If the fact was retracted the appropriate VO will be removed */
        } else {
            /* Find the AnalysisResult VO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            AnalysisResultVO analysisResult = (AnalysisResultVO) factIndexById.get(_factId);
            //System.out.println("WE are in else statement local VO Update");
            /*
             * Remove the reference to this analysis result in the appropriate Scenario VO if it
             * exists
             */
            if (factIndexById.get(new Integer(analysisResult.getOwnerFactId())) != null) {
                ScenarioVO scenario = (ScenarioVO) factIndexById.get(new Integer(analysisResult
                        .getOwnerFactId()));
                scenario.getAnalysisResults().remove(analysisResult);
            }

            /* Remove the AnalysisResult VO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(AnalysisResultVO.class);
        }
    }

    /**
     * Parse a MAIN::Function fact and create, update or delete a FunctionVO object accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateFunction(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        /* Read values from slots */
        String id = checkNilSlot(jFact.getSlotValue("id").stringValue(contextArchE));
        String desc = checkNilSlot(jFact.getSlotValue("description").stringValue(contextArchE));

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new VO */
            FunctionVO function = new FunctionVO(factId, desc, id, ui);
            /* adding the function VO to the project Vo */
            project.getFunctions().add(function);
            /* Put the function VO in the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), function);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the function VO in the hashtable local to VOUpdate */
            FunctionVO function = (FunctionVO) factIndexById.get(new Integer(factId));
            /* Update existing VO values */
            function.setDescription(desc);
            function.setId(id);
            /* If the fact was retracted the appropriate VO will be removed */
        } else {
            /* Find the function VO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            FunctionVO function = (FunctionVO) factIndexById.get(_factId);
            /* Remove the function VO from the project VO */
            project.getFunctions().remove(function);
            /* Remove the function VO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(FunctionVO.class);
        }
    }

    /**
     * Parse a MAIN::TranslationRelation fact and create, update or delete a
     * ScenarioResponsibilityMapVO or FunctionResponsibilityMapVO object accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateTranslationRelation(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Initiliazing the parent and the child fact ids */
        int parent = -1;
        int child = -1;
        /* Read FactId */
        int factId = jFact.getFactId();
        /* Read values from slots */
        int source = strToIntSource(jFact.getSlotValue("source").stringValue(contextArchE));

        /*
         * The parent and the child fact ids can be strings or Fact values depending on whether the
         * TranslationRElation fact was read from a persisted file (in which case they will be
         * strings) or are asserted by the rules inside of the ArchE Core (in which case they will
         * be references to facts therefore returning values of type RU.FACT / RU.EXTERNAL_ADDRESS
         */
        if ((jFact.getSlotValue("parent").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("parent").type() == RU.FACT)) {
            parent = jFact.getSlotValue("parent").factValue(contextArchE).getFactId();
        } else {
            parent = strToIntFactId(jFact.getSlotValue("parent").stringValue(contextArchE));
        }
        if ((jFact.getSlotValue("child").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("child").type() == RU.FACT)) {
            child = jFact.getSlotValue("child").factValue(contextArchE).getFactId();
        } else {
            child = strToIntFactId(jFact.getSlotValue("child").stringValue(contextArchE));
        }

        /*
         * Reasing the parent type of the TranslationRelation so that the fact can be appropriately
         * resolved into either a ScenarioResponsibilityMappingVO or a
         * FunctionResponsibilityMapingVO
         */
        if (jFact.getSlotValue("parentType").stringValue(contextArchE).equalsIgnoreCase("scenario")) {
            /* It's a SCENARIO RESPONSIBILITY MAPPING */
            /* If the fact was asserted the appropriate VO will be created */
            if (statusFact == CoreListener.ASSERT) {
                /* Create new ScenarioResponsibilityMappingVO */
                ScenarioResponsibilityMapVO vo = new ScenarioResponsibilityMapVO(factId, parent,
                        child, source, ui);
                /* Add the ScenarioResponsibilityMappingVO to the project VO */
                project.getScenarioResps().add(vo);
                /* Add the ScenarioResponsibilityMappingVO to the hashtable local to VOUpdate */
                factIndexById.put(new Integer(factId), vo);
                /* If the fact was modified the appropriate VO will be updated */
            } else if (statusFact == CoreListener.MODIFY) {
                /* Find the ScenarioResponsibilityMappingVO from the hashtable local to VOUpdate */
                ScenarioResponsibilityMapVO vo = (ScenarioResponsibilityMapVO) factIndexById
                        .get(new Integer(factId));
                /* Update the VO values */
                vo.setScenarioFactId(parent);
                vo.setResponsibilityFactId(child);
                vo.setSource(source);
                /* If the fact was retracted the appropriate VO will be removed */
            } else {
                /* Find the ScenarioResponsibilityMapVO from the hashtable local to VOUpdate */
                Integer _factId = new Integer(factId);
                ScenarioResponsibilityMapVO vo = (ScenarioResponsibilityMapVO) factIndexById
                        .get(_factId);
                /* Remove the ScenarioResponsibilityMappingVO from the hashtable local to VOUpdate */
                factIndexById.remove(_factId);
                /* Remove the ScenarioResponsibilityMappingVO from the porject VO */
                project.getScenarioResps().remove(vo);
                // When a fact VO is created or modified, the VO object itself flag the UI what
                // views to refresh. When a fact is deleted, VOUpdate has to do it because there
                // is no "destructor" in the VO object to do that.
                ui.flagViewsToRefresh(ScenarioResponsibilityMapVO.class);
            }
        } else if (jFact.getSlotValue("parentType").stringValue(contextArchE)
                .equalsIgnoreCase("functionality")) {
            /* It's a FUNCTION RESPONSIBILITY MAPPING */
            /* If the fact was asserted the appropriate VO will be created */
            if (statusFact == CoreListener.ASSERT) {
                /* Create new FunctionResponsibilityMappingVO */
                FunctionResponsibilityMapVO vo = new FunctionResponsibilityMapVO(factId, parent,
                        child, source, ui);
                /* Add the FunctionResponsibilityMappingVO to the project VO */
                project.getFunctionResps().add(vo);
                /* Add the FunctionResponsibilityMappingVO to the hashtable local to VOUpdate */
                factIndexById.put(new Integer(factId), vo);
                /* If the fact was modified the appropriate VO will be updated */
            } else if (statusFact == CoreListener.MODIFY) {
                /* Find the FunctionResponsibilityMappingVO in the hashtable local to VOUpdate */
                FunctionResponsibilityMapVO vo = (FunctionResponsibilityMapVO) factIndexById
                        .get(new Integer(factId));
                /* Update the FunctionResponsibilityMappingVO values */
                vo.setFunctionFactId(parent);
                vo.setResponsibilityFactId(child);
                vo.setSource(source);
                /* If the fact was retracted the appropriate VO will be removed */
            } else {
                /* Find the FunctionResponsibilityMappingVO in the hashtable local to VOUpdate */
                Integer _factId = new Integer(factId);
                FunctionResponsibilityMapVO vo = (FunctionResponsibilityMapVO) factIndexById
                        .get(_factId);
                /* Remove the FunctionResponsibilityMappingVO from the hashtable local to VOUpdate */
                factIndexById.remove(_factId);
                /* Remove the FunctionResponsibilityMappingVO from the project VO */
                project.getFunctionResps().remove(vo);
                // When a fact VO is created or modified, the VO object itself flag the UI what
                // views to refresh. When a fact is deleted, VOUpdate has to do it because there
                // is no "destructor" in the VO object to do that.
                ui.flagViewsToRefresh(FunctionResponsibilityMapVO.class);
            }
        }
    }

    /**
     * Parse a parameter fact and create, update or delete a ParameterVO object and associate it to
     * the proper ResponsibilityVO or RelationshipVO object, depending if it's a parameter of a
     * responsibility or relationship. It may not be able to udpate the list of parameters in the
     * ResponsibilityVO or RelationshipVO object if that object is not created yet. In this case,
     * the ParameterVO the value of ownerFactId will be set.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateParameter(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {

        // fact id of the parameter
        int factId = jFact.getFactId();
        int ownerFactId = 0;
        if ((jFact.getSlotValue("owner").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("owner").type() == RU.FACT)) {
            ownerFactId = jFact.getSlotValue("owner").factValue(contextArchE).getFactId();
        } else {
            ownerFactId = strToIntFactId(jFact.getSlotValue("owner").stringValue(contextArchE));
        }
        if (statusFact == CoreListener.RETRACT) {
            // Remove existing VO
            Integer _factId = new Integer(factId);
            ParameterVO vo = (ParameterVO) factIndexById.get(_factId);
            // deassociate parameter to responsibility or relationship depending on source
            removeParamFromOwner(vo, ownerFactId);
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ParameterVO.class);
            return;
       }
        
        // parameter value
        String value = checkNilSlot(jFact.getSlotValue("value").stringValue(contextArchE));
        // owner (which is a responsibility or relationship)
        // source
        int source = strToIntSource(jFact.getSlotValue("source").stringValue(contextArchE));
        // parameter type
        ParameterTypeVO paramType = (ParameterTypeVO) allParamTypes.get(jFact.getName());

        // Create/update/remove VO object
        if (statusFact == CoreListener.ASSERT) {
            // Create new VO
            ParameterVO vo = new ParameterVO(factId, paramType, value, source, ownerFactId, ui);
            // associate parameter to responsibility or relationship depending on owner,
            // but only if the corresponding ResponsibilityVO or RelationshipVO has already
            // been loaded.
            addParamToOwner(vo, ownerFactId);
            factIndexById.put(new Integer(factId), vo);

        } else if (statusFact == CoreListener.MODIFY) {
            // Update existing VO
            ParameterVO vo = (ParameterVO) factIndexById.get(new Integer(factId));
            int previousOwnerFactId = vo.getOwnerFactId();

            // If owner fact id being reassigned, we need to go to the previous owner and remove
            // this parameter from its list.
            removeParamFromOwner(vo, previousOwnerFactId);

            // Now we can change the values
            vo.setValue(value);
            vo.setSource(source);
            vo.setOwnerFactId(ownerFactId);

            // Associate parameter to new owner (responsibility or relationship),
            // but only if the corresponding ResponsibilityVO or RelationshipVO has already
            // been loaded.
            addParamToOwner(vo, ownerFactId);
        }
    }

    /**
     * Method factored out of parseAndUpdateParameter. It locates the owner of a parameter and add
     * the ParameterVO to the owner's list of parameters, if the owner exists.
     * 
     * @param vo
     * @param ownerFactId
     */
    private void addParamToOwner(ParameterVO vo, int ownerFactId) {
        CoreFact owner = (CoreFact) factIndexById.get(new Integer(ownerFactId));
        if (owner != null) {
            if (owner instanceof ResponsibilityVO) {
                ((ResponsibilityVO) owner).getParameters().add(vo);
            } else if (owner instanceof RelationshipVO) {
                ((RelationshipVO) owner).getParameters().add(vo);
            } else {
                System.err.println("[VOUpdate.addParamToOwner] invalid owner with fact id = "
                        + ownerFactId);
            }
        }
    }

    /**
     * Method factored out of parseAndUpdateParameter. It locates the owner of a parameter and
     * removes the ParameterVO from the owner's list of parameters, if the owner exists.
     * 
     * @param vo
     * @param ownerFactId
     */
    private void removeParamFromOwner(ParameterVO vo, int ownerFactId) {
        CoreFact owner = (CoreFact) factIndexById.get(new Integer(ownerFactId));
        if (owner != null) {
            if (owner instanceof ResponsibilityVO) {
                ((ResponsibilityVO) owner).getParameters().remove(vo);
            } else if (owner instanceof RelationshipVO) {
                ((RelationshipVO) owner).getParameters().remove(vo);
            } else {
                System.err.println("[VOUpdate.removeParamFromOwner] invalid owner with fact id = "
                        + ownerFactId);
            }
        }
    }

    /**
     * Parse relationships between responsibilities facts and create, update or delete a
     * RelationshipVO object accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateRelationship(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {

        int factId = jFact.getFactId();
        if (statusFact == CoreListener.RETRACT) {
            // Remove existing VO
            Integer _factId = new Integer(factId);
            RelationshipVO vo = (RelationshipVO) factIndexById.get(_factId);
            factIndexById.remove(_factId);
            project.getRelationships().remove(vo);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(RelationshipVO.class);
            return;
       }
        
        String typeName = jFact.getName();
        int source = strToIntSource(jFact.getSlotValue("source").stringValue(contextArchE));

        int parent = -1;
        int child = -1;
        /*
         * The parent and the child fact ids can be strings or Fact values depending on whether the
         * TranslationRElation fact was read from a persisted file (in which case they will be
         * strings) or are asserted by the rules inside of the ArchE Core (in which case they will
         * be references to facts therefore returning values of type RU.FACT / RU.EXTERNAL_ADDRESS
         */
        if ((jFact.getSlotValue("parent").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("parent").type() == RU.FACT)) {
            parent = jFact.getSlotValue("parent").factValue(contextArchE).getFactId();
        } else {
            parent = strToIntFactId(jFact.getSlotValue("parent").stringValue(contextArchE));
        }

        if ((jFact.getSlotValue("child").type() == RU.EXTERNAL_ADDRESS)
                || (jFact.getSlotValue("child").type() == RU.FACT)) {
            child = jFact.getSlotValue("child").factValue(contextArchE).getFactId();
        } else {
            child = strToIntFactId(jFact.getSlotValue("child").stringValue(contextArchE));
        }

        if (statusFact == CoreListener.ASSERT) {

            RelationshipVO vo = new RelationshipVO(factId, jFact.getName().toString(),
                    (RelationshipTypeVO) respoRelationTypes.get(typeName), source, parent, child,
                    ui);

            /*
             * If at the time of assertion of a responsibility relationship fact, there were already
             * parameters facts associated with the responsibility relationship, the references to
             * these responsibility relationship facts need to be added to the RelationshipVO. This
             * might happen when loading a fact base from a persistent file.
             */
            for (Enumeration e = factIndexById.elements(); e.hasMoreElements();) {
                CoreFact relationshipParam = (CoreFact) e.nextElement();
                if (relationshipParam instanceof ParameterVO) {
                    if (((ParameterVO) relationshipParam).getOwnerFactId() == factId) {
                        vo.getParameters().add((ParameterVO) relationshipParam);
                    }
                }
            }

            factIndexById.put(new Integer(factId), vo);
            project.getRelationships().add(vo);
        } else if (statusFact == CoreListener.MODIFY) {
            // Update existing VO
            RelationshipVO vo = (RelationshipVO) factIndexById.get(new Integer(factId));
            vo.setSource(source);
            vo.setChildFactId(child);
            vo.setParentFactId(parent);
            vo.setFactType(typeName);
            vo.setType((RelationshipTypeVO) respoRelationTypes.get(typeName));
        }
    }

    /**
     * Parse a MAIN::AskQuestion fact and create, update or delete a QuestionToUserVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateAskQuestion(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {

        int factId = jFact.getFactId();
        String answerAvailable = jFact.getSlotValue("answerAvailable").stringValue(contextArchE);
        if (statusFact == CoreListener.RETRACT || answerAvailable.equals("true")) {
            // Remove existing VO
            Integer _factId = new Integer(factId);
            QuestionToUserVO vo = (QuestionToUserVO) factIndexById.get(_factId);
            factIndexById.remove(_factId);
            project.getQuestions().remove(vo);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(QuestionToUserVO.class);

        } else {

            String questionId = jFact.getSlotValue("questionId").stringValue(contextArchE);
            //            System.out.println("\t[VOUpdate] question id: " + questionId);

            int parentFactId = 0;
            if ((jFact.getSlotValue("parent").type() == RU.EXTERNAL_ADDRESS)
                    || (jFact.getSlotValue("parent").type() == RU.FACT)) {
                parentFactId = jFact.getSlotValue("parent").factValue(contextArchE).getFactId();
            } else {
                parentFactId = strToIntFactId(jFact.getSlotValue("parent")
                        .stringValue(contextArchE));
            }
            
            String priority = checkNilSlot(jFact.getSlotValue("priority")
                    .stringValue(contextArchE));
            // parse multislot affectedFacts
            ValueVector list = jFact.getSlotValue("affectedFacts").listValue(contextArchE);
            int affectedFacts[] = new int[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                Value v = list.get(i);
                if (v.type() == RU.EXTERNAL_ADDRESS || v.type() == RU.FACT) {
                    affectedFacts[i] = v.factValue(contextArchE).getFactId();
                } else {
                    affectedFacts[i] = strToIntFactId(v.stringValue(contextArchE));
                }
                //System.out.println("[VOUpdate.parseAndUpdateAskQuestion] affected fact[" + i
                //        + "]: " + affectedFacts[i]);
            }

            // parse multislot parameters
            list = jFact.getSlotValue("parameters").listValue(contextArchE);
            String parameters[] = new String[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                Value v = list.get(i);
                if (v.type() == RU.EXTERNAL_ADDRESS || v.type() == RU.FACT) {
                    parameters[i] = "" + v.factValue(contextArchE).getFactId();
                } else {
                    parameters[i] = checkNilSlot(v.stringValue(contextArchE));
                }
                //System.out.println("[VOUpdate.parseAndUpdateAskQuestion] parameter[" + i + "]: "
                //        + parameters[i]);
            }

            // parse multislot options
            list = jFact.getSlotValue("options").listValue(contextArchE);
            String options[] = new String[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                Value v = list.get(i);
                if (v.type() == RU.EXTERNAL_ADDRESS || v.type() == RU.FACT) {
                    options[i] = "" + v.factValue(contextArchE).getFactId();
                } else {
                    options[i] = checkNilSlot(v.stringValue(contextArchE));
                }
                //System.out.println("[VOUpdate.parseAndUpdateAskQuestion] options[" + i + "]: "
                //        + options[i]);
            }

            // parse multislot default
            list = jFact.getSlotValue("default").listValue(contextArchE);
            String defaultAnswer[] = new String[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                Value v = list.get(i);
                if (v.type() == RU.EXTERNAL_ADDRESS || v.type() == RU.FACT) {
                    defaultAnswer[i] = "" + v.factValue(contextArchE).getFactId();
                } else {
                    defaultAnswer[i] = checkNilSlot(v.stringValue(contextArchE));
                }
                //System.out.println("[VOUpdate.parseAndUpdateAskQuestion] defaultAnswer[" + i
                //        + "]: " + defaultAnswer[i]);
            }

            if (statusFact == CoreListener.ASSERT) {
                // Create new VO
                QuestionToUserVO vo = new QuestionToUserVO(factId, questionId, parentFactId,
                        affectedFacts, parameters, options, priority, defaultAnswer, null, ui);

                factIndexById.put(new Integer(factId), vo);
                project.getQuestions().add(vo);                                
                ui.flagViewsToRefresh(QuestionToUserVO.class);
            } else if (statusFact == CoreListener.MODIFY) {
                // Update existing VO
                QuestionToUserVO vo = (QuestionToUserVO) factIndexById.get(new Integer(factId));
                vo.setQuestionId(questionId);
                vo.setAffectedFacts(affectedFacts);
                vo.setParameters(parameters);
                vo.setPriority(priority);
                vo.setOptions(options);
                vo.setDefaultAnswer(defaultAnswer);
            }
        }
    }

    /**
     * Parse a fact that is a model element and create, update or delete a ModelElementVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateModelElement(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        if (statusFact == CoreListener.RETRACT) {
            /* Find the ModelElementVO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            ModelElementVO modelElement = (ModelElementVO) factIndexById.get(_factId);
            /* Remove the ModelElementVO from the project VO */
            project.getModelElements().remove(modelElement);
            /* Remove the ModelElementVO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ModelElementVO.class);
            return;
       }
        
        ModelElementTypeVO modelElementType = (ModelElementTypeVO) allModelElementTypes.get(jFact
                .getName());
        /* Read values from slots */
        String name = checkNilSlot(jFact.getSlotValue(modelElementType.getNameSlotName())
                .stringValue(contextArchE));

        String[] paramSlotValues;
        if (modelElementType.getParamSlotNames() != null) {
            paramSlotValues = new String[modelElementType.getParamSlotNames().length];
            for (int i = 0; i < modelElementType.getParamSlotNames().length; i++) {
                paramSlotValues[i] = checkNilSlot(jFact
                        .getSlotValue(modelElementType.getParamSlotNames()[i])
                        .stringValue(contextArchE));
            }
        } else {
            paramSlotValues = null;
        }

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new VO */
            ModelElementVO modelElement = new ModelElementVO(factId, modelElementType, name,
                    paramSlotValues, ui);
            /* adding the ModelElementVO to the project Vo */
            project.getModelElements().add(modelElement);
            /* Put the ModelElementVO in the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), modelElement);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the ModelElementVO in the hashtable local to VOUpdate */
            ModelElementVO modelElement = (ModelElementVO) factIndexById.get(new Integer(factId));
            /* Update existing VO values */
            modelElement.setName(name);
            modelElement.setParamSlotValues(paramSlotValues);
            /* If the fact was retracted the appropriate VO will be removed */
        }
    }

    /**
     * Parse a fact that is a model relation and create, update or delete a ModelRelationVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateModelRelation(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        if (statusFact == CoreListener.RETRACT) {
             /* Find the ModelRelationVO in the hashtable local to VOUpdate */
             Integer _factId = new Integer(factId);
             ModelRelationVO modelRelation = (ModelRelationVO) factIndexById.get(_factId);
             /* Remove the ModelRelationVO from the project VO */
             project.getModelRelations().remove(modelRelation);
             /* Remove the ModelRelationVO from the hashtable local to VOUpdate */
             factIndexById.remove(_factId);
             // When a fact VO is created or modified, the VO object itself flag the UI what
             // views to refresh. When a fact is deleted, VOUpdate has to do it because there
             // is no "destructor" in the VO object to do that.
             ui.flagViewsToRefresh(ModelRelationVO.class);
             return;
        }
        
        ModelRelationTypeVO modelRelationType = (ModelRelationTypeVO) allModelRelationTypes
                .get(jFact.getName());
        /* Read values from slots */
        int lhsFactId = 0;
        int rhsFactId = 0;

        if (jFact.getSlotValue(modelRelationType.getLhsSlotName()).type() == RU.FACT) {
            lhsFactId = jFact.getSlotValue(modelRelationType.getLhsSlotName())
                    .factValue(contextArchE).getFactId();
        } else {
            lhsFactId = strToIntFactId(jFact.getSlotValue(modelRelationType.getLhsSlotName())
                    .stringValue(contextArchE));
        }

        if (jFact.getSlotValue(modelRelationType.getRhsSlotName()).type() == RU.FACT) {
            rhsFactId = jFact.getSlotValue(modelRelationType.getRhsSlotName())
                    .factValue(contextArchE).getFactId();
        } else {
            rhsFactId = strToIntFactId(jFact.getSlotValue(modelRelationType.getRhsSlotName())
                    .stringValue(contextArchE));
        }

        String[] paramSlotValues;
        if (modelRelationType.getParamSlotNames() != null) {
            paramSlotValues = new String[modelRelationType.getParamSlotNames().length];
            for (int i = 0; i < modelRelationType.getParamSlotNames().length; i++) {
                paramSlotValues[i] = checkNilSlot(jFact
                        .getSlotValue(modelRelationType.getParamSlotNames()[i])
                        .stringValue(contextArchE));
            }
        } else {
            paramSlotValues = null;
        }

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new VO */
            ModelRelationVO modelRelation = new ModelRelationVO(factId, modelRelationType,
                    lhsFactId, rhsFactId, paramSlotValues, ui);
            /* adding the ModelRelationVO to the project Vo */
            project.getModelRelations().add(modelRelation);
            /* Put the ModelRelationVO in the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), modelRelation);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the ModelRelationVO in the hashtable local to VOUpdate */
            ModelRelationVO modelRelation = (ModelRelationVO) factIndexById
                    .get(new Integer(factId));
            /* Update existing VO values */
            modelRelation.setLhsFactId(lhsFactId);
            modelRelation.setRhsFactId(rhsFactId);
            modelRelation.setParamSlotValues(paramSlotValues);
            /* If the fact was retracted the appropriate VO will be removed */
        }
    }

    /**
     * Parse a fact that is a design element and create, update or delete a DesignElementVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateDesignElement(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        if (statusFact == CoreListener.RETRACT) {
            /* Find the DesignElementVO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            ViewElementVO designElement = (ViewElementVO) factIndexById.get(_factId);
            /* Remove the DesignElementVO from the project VO */
            project.getDesignElements().remove(designElement);
            /* Remove the DesignElementVO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ViewElementVO.class);
            return;
        }

        ViewElementTypeVO designElementType = (ViewElementTypeVO) allDesignElementTypes
        											.get(jFact.getName());
	    /* Read values from slots */
        String name = checkNilSlot(jFact.getSlotValue(designElementType.getNameSlotName())
	            .stringValue(contextArchE));
        String[] paramSlotValues;        
        if (designElementType.getParamSlotNames() != null) {
            paramSlotValues = new String[designElementType.getParamSlotNames().length];
            for (int i = 0; i < designElementType.getParamSlotNames().length; i++) {
                paramSlotValues[i] = checkNilSlot(jFact
                        .getSlotValue(designElementType.getParamSlotNames()[i])
                        .stringValue(contextArchE));
            }
        } else {
            paramSlotValues = null;
        }        	
        
        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
        	
            /* Create new VO */
            ViewElementVO designElement = new ViewElementVO(factId, designElementType, name,
                    paramSlotValues, ui);
            /* adding the DesignElementVO to the project Vo */
            project.getDesignElements().add(designElement);
            /* Put the DesignElementVO in the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), designElement);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            /* Find the DesignElementVO in the hashtable local to VOUpdate */
            ViewElementVO designElement = (ViewElementVO) factIndexById
                    .get(new Integer(factId));
            /* Update existing VO values */
            designElement.setName(name);
            designElement.setParamSlotValues(paramSlotValues);
            /* If the fact was retracted the appropriate VO will be removed */
        }
    }

    /**
     * Parse a fact that is a design relation and create, update or delete a DesignRelationVO object
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateDesignRelation(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
        /* Read FactId */
        int factId = jFact.getFactId();
        if (statusFact == CoreListener.RETRACT) {
            /* Find the DesignRelationVO in the hashtable local to VOUpdate */
            Integer _factId = new Integer(factId);
            ViewRelationVO designRelation = (ViewRelationVO) factIndexById.get(_factId);
            /* Remove the DesignRelationVO from the project VO */
            project.getDesignRelations().remove(designRelation);
            /* Remove the DesignRelationVO from the hashtable local to VOUpdate */
            factIndexById.remove(_factId);
            // When a fact VO is created or modified, the VO object itself flag the UI what
            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
            // is no "destructor" in the VO object to do that.
            ui.flagViewsToRefresh(ViewRelationVO.class);        	
            return;
        }
        
        ViewRelationTypeVO designRelationType = (ViewRelationTypeVO) allDesignRelationTypes
                .get(jFact.getName());
        /* Read values from slots */
        int lhsFactId = 0;
        int rhsFactId = 0;

        if (jFact.getSlotValue(designRelationType.getLhsSlotName()).type() == RU.FACT) {
            lhsFactId = jFact.getSlotValue(designRelationType.getLhsSlotName())
                    .factValue(contextArchE).getFactId();
        } else {
            lhsFactId = strToIntFactId(jFact.getSlotValue(designRelationType.getLhsSlotName())
                    .stringValue(contextArchE));
        }

        if (jFact.getSlotValue(designRelationType.getRhsSlotName()).type() == RU.FACT) {
            rhsFactId = jFact.getSlotValue(designRelationType.getRhsSlotName())
                    .factValue(contextArchE).getFactId();
        } else {
            rhsFactId = strToIntFactId(jFact.getSlotValue(designRelationType.getRhsSlotName())
                    .stringValue(contextArchE));
        }

        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            String[] paramSlotValues;
            if (designRelationType.getParamSlotNames() != null) {
                paramSlotValues = new String[designRelationType.getParamSlotNames().length];
                for (int i = 0; i < designRelationType.getParamSlotNames().length; i++) {
                	if(designRelationType.getParamSlotTypes()[i].equalsIgnoreCase("object")){
                		int factID;
    	                if (jFact.getSlotValue(designRelationType.getParamSlotNames()[i]).type() == RU.FACT) {
    	                	factID = jFact.getSlotValue(designRelationType.getParamSlotNames()[i])
    	                            .factValue(contextArchE).getFactId();
    	                } else {
    	                	factID = strToIntFactId(jFact.getSlotValue(designRelationType.getParamSlotNames()[i])
    	                            .stringValue(contextArchE));
    	                }
    	                paramSlotValues[i] = "<Fact-" + factID + ">";   		
                	}
                	else{
    	                paramSlotValues[i] = checkNilSlot(jFact
    	                        .getSlotValue(designRelationType.getParamSlotNames()[i])
    	                        .stringValue(contextArchE));
                	}
                }
            } else {
                paramSlotValues = null;
            }
        	
            /* Create new VO */
            ViewRelationVO designRelation = new ViewRelationVO(factId, designRelationType,
                    lhsFactId, rhsFactId, paramSlotValues, ui);
            /* adding the DesignRelationVO to the project Vo */
            project.getDesignRelations().add(designRelation);
            /* Put the DesignRelationVO in the hashtable local to VOUpdate */
            factIndexById.put(new Integer(factId), designRelation);
            /* If the fact was modified the appropriate VO will be updated */
        } else if (statusFact == CoreListener.MODIFY) {
            String[] paramSlotValues;
            if (designRelationType.getParamSlotNames() != null) {
                paramSlotValues = new String[designRelationType.getParamSlotNames().length];
                for (int i = 0; i < designRelationType.getParamSlotNames().length; i++) {
                	if(designRelationType.getParamSlotTypes()[i].equalsIgnoreCase("object")){
                		int factID;
    	                if (jFact.getSlotValue(designRelationType.getParamSlotNames()[i]).type() == RU.FACT) {
    	                	factID = jFact.getSlotValue(designRelationType.getParamSlotNames()[i])
    	                            .factValue(contextArchE).getFactId();
    	                } else {
    	                	factID = strToIntFactId(jFact.getSlotValue(designRelationType.getParamSlotNames()[i])
    	                            .stringValue(contextArchE));
    	                }
    	                paramSlotValues[i] = "<Fact-" + factID + ">";   		
                	}
                	else{
    	                paramSlotValues[i] = checkNilSlot(jFact
    	                        .getSlotValue(designRelationType.getParamSlotNames()[i])
    	                        .stringValue(contextArchE));
                	}
                }
            } else {
                paramSlotValues = null;
            }
        	
            /* Find the DesignRelationVO in the hashtable local to VOUpdate */
            ViewRelationVO designRelation = (ViewRelationVO) factIndexById.get(new Integer(
                    factId));
            /* Update existing VO values */
            designRelation.setLhsFactId(lhsFactId);
            designRelation.setRhsFactId(rhsFactId);
            designRelation.setParamSlotValues(paramSlotValues);
            /* If the fact was retracted the appropriate VO will be removed */
        }
    }
    
    /**
     * Parse a fact that is a tree and create, update or delete a flag the ui to refresh
     * accordingly.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    private void parseAndUpdateTree(Fact jFact, Context contextArchE, int statusFact)
            throws JessException {
            ui.flagViewsToRefresh(TreeVO.class);
       
    }
    
    /**
     * Parse a Seeker::EvaluationResults fact and create, a EvaluationResultsVO object.
     * 
     * @param jFact Fact passed by Jess to CoreListener and then to VOUpdate.
     * @param contextArchE execution context of Jess--here it's basically an auxiliary object.
     * @param statusFact indicates if the fact is being aserted, modified or retracted. Use
     *            constants defined in CoreListener.
     * @throws JessException
     */
    /**
     * @param jFact
     * @param contextArchE
     * @param statusFact
     * @throws JessException
     */
    private void parseEvaluationResult(Fact jFact, Context contextArchE, int statusFact)
    throws JessException {
    	 /* Read FactId */
    	
    
        int factId = jFact.getFactId();
        
        /* Read values from slots */
        String tacticDesc      = checkNilSlot(jFact.getSlotValue("tacticDescription").stringValue(contextArchE));
        String strUtility      = checkNilSlot(jFact.getSlotValue("utility").stringValue(contextArchE));
        String strChange       = checkNilSlot(jFact.getSlotValue("change").stringValue(contextArchE));
        String strRelevance    = checkNilSlot(jFact.getSlotValue("relevance").stringValue(contextArchE));
        
        if(tacticDesc.equals(""))
        	return;
        
        /*
         * There�s no fact exclusively for parts of a scenario. All data is inside the scenario fact.
         * However, ScenarioPartVO extends CoreFact so that it can use the same ui refresh mechanism
         * of other classes. This is why the constructor of the ScenarioPartVO has an integer factId
         * and for the sake of simplicity this id is the same as the id of the parent scenario.
         */
        
        //if(factId >= 3109){
        //System.out.println("current analyzed factID: " + factId + " Status " + statusFact );
        //}
        /* If the fact was asserted the appropriate VO will be created */
        if (statusFact == CoreListener.ASSERT) {
            /* Create new evaluated Evaluation Result VO */
        
        	 if(strRelevance.equals(""))
	            strRelevance = "0";
	          
           
            /*
             * Reads the id of the scenario to get the description
             */
            //Deftemplate template = jFact.getDeftemplate();
            // String strScenatioId     = checkNilSlot(jFact.getSlotValue("scenario").stringValue(contextArchE));
            
            

            String scenarioDesc = "";
            int scenarioId = -1;
            Fact oScenario = jFact.getSlotValue("scenario").factValue(contextArchE);
            if(oScenario != null){
            	scenarioDesc = checkNilSlot(oScenario.getSlotValue("description").stringValue(contextArchE));
            	scenarioId = oScenario.getFactId();  
            }
            
            int factTacticId = -1;
            //Fact fTactic = null;
            String tacticId  = "";
            //Retrieve the fact if of the applied tactic
            try{
//            	tacticId = checkNilSlot(jFact.getSlotValue("tacticScenario").stringValue(contextArchE));
            	tacticId = jFact.getSlotValue("tacticId").symbolValue(contextArchE);
            }catch(JessException je){
            	
            }
            	RawEvaluationResultVO rawEvaluatedResult = null;
            
            	if(!tacticId.equals("") && !tacticId.equals("nil")){
//	            	int indexOne = tacticId.indexOf("gen");
//	            	int indexTwo = tacticId.length();
	            	
	            	rawEvaluatedResult = new RawEvaluationResultVO(factId, strToDouble(strUtility), scenarioId,
						 										ui,strToDouble(strChange),
						 										Integer.parseInt(strRelevance), tacticDesc,
						 										scenarioDesc, tacticId);
//	            												scenarioDesc, Integer.valueOf( tacticId.substring( indexOne + 3, indexTwo)).intValue());
            	
            	
//            		EvaluatedTacticsVO tactic = new EvaluatedTacticsVO(tacticId.substring( indexOne + 3,indexTwo), tacticDesc, strToDouble(strUtility),             	
            		EvaluatedTacticsVO tactic = new EvaluatedTacticsVO(tacticId, tacticDesc, strToDouble(strUtility), 
	            		Integer.parseInt(strRelevance), strToDouble(strChange));
	          
	            	rawEvaluatedResult.setTactics(tactic);

            	}
            	else {
	            	rawEvaluatedResult = new RawEvaluationResultVO(factId, strToDouble(strUtility), scenarioId,
							 ui,strToDouble(strChange),
							 Integer.parseInt(strRelevance), tacticDesc,
							 scenarioDesc, tacticId);	
//	             			 scenarioDesc, factTacticId);	
            	}
	            	
	            	 /*
		             *Check if the relevance is different than  "", if it is equals, then assign the least relevant number 
		             */
	            	            	
		            /* Add the evaluated ScenarioVO to the project VO */
	            	project.addUpdateEvaluationResults(rawEvaluatedResult);
		            
		            /* If the fact was modified the appropriate VO will be updated */
		            factIndexById.put(new Integer(factId), rawEvaluatedResult);  
            	
	           
        } else if (statusFact == CoreListener.MODIFY) {
        	
            
        	/* Find the DesignRelationVO in the hashtable local to VOUpdate */
        	Object obj = factIndexById.get(new Integer(factId));
        	if(obj != null && obj instanceof RawEvaluationResultVO){
            	RawEvaluationResultVO evaluatedScenario = (RawEvaluationResultVO)obj ;        	  

            	//evaluatedScenario.clearTactics();
            	/* Update existing VO values */
            	evaluatedScenario.setUtility(strToDouble(strUtility));
            	evaluatedScenario.setChange(strToDouble(strChange));
            	if(!strRelevance.equals("")){
            		evaluatedScenario.setRelevance(Integer.parseInt(strRelevance));
            	}else
            		strRelevance = "0";
            	
            	//evaluatedScenario.clearTactics();
            	int factTacticId = -1;
                Fact fTactic = null;
                String tacticId  = "";
                //Retrieve the fact if of the applied tactic
                try{
                	
                	tacticId = jFact.getSlotValue("tacticId").symbolValue(contextArchE);
                }catch(JessException je){
                	fTactic = jFact.getSlotValue("tacticScenario").factValue(contextArchE);
                }
                if( tacticId.equals("") && fTactic != null){
                	if(fTactic != null)
                		factTacticId = fTactic.getFactId();        
                }
                
                if(!tacticId.equals("") && !tacticId.equals("nil")){
                	
    	            EvaluatedTacticsVO tactic =  new EvaluatedTacticsVO(tacticId, tacticDesc, strToDouble(strUtility), 
    	            		Integer.parseInt(strRelevance), strToDouble(strChange));
    	            	//int indexOne = tacticId.indexOf("gen");
    	            	//int indexTwo = tacticId.length();
    	            	evaluatedScenario.setTactics(tactic);
    	            	//evaluatedScenario.addTactic(new Integer(Integer.valueOf(tacticId.substring( indexOne + 3,indexTwo)).intValue()), tactic);	
    	            
                
    	            	/* Add the evaluated ScenarioVO to the project VO */
    	            	project.addUpdateEvaluationResults(evaluatedScenario);
                }        		
        	}
        	
        	/* If the fact was retracted the appropriate VO will be removed */
        } else {
            
        	/* Find the EvaluationResultVO in the hashtable local to remove */
            Integer _factId = new Integer(factId);                        
        	Object obj = factIndexById.get(_factId);
        	if(obj != null && obj instanceof RawEvaluationResultVO){
	            RawEvaluationResultVO evaluatedScenario =  (RawEvaluationResultVO)obj;
	            /* Remove the EvaluationResultVO from the project VO */
	            if(evaluatedScenario != null){
	            	project.removeTacticER(evaluatedScenario);
	            	/* Remove the EvaluationResultVO from the hashtable local to VOUpdate */
	            	factIndexById.remove(_factId);
	        	}
	            // When a fact VO is created or modified, the VO object itself flag the UI what
	            // views to refresh. When a fact is deleted, VOUpdate has to do it because there
	            // is no "destructor" in the VO object to do that.
	            //ui.flagViewsToRefresh(EvaluationResultVO.class);
        	}
        }
        ui.flagViewsToRefresh(EvaluationResultVO.class);
    }
    
    private void defineAndAddJessFactTemplate(String fullFactType, String[] paramSlotTypes,String[] paramSlotNames, Rete engine ) throws JessException{
    	Deftemplate d = new Deftemplate(fullFactType, "", engine);    	
    	for(int i=0; i<paramSlotTypes.length; i++){
    		if(paramSlotTypes[i].equalsIgnoreCase("object"))
        		d.addSlot(paramSlotNames[i], Funcall.NIL, "OBJECT");
    		else if(paramSlotTypes[i].equalsIgnoreCase("string"))
        		d.addSlot(paramSlotNames[i], new Value("", RU.STRING), "STRING");
    		else if(paramSlotTypes[i].equalsIgnoreCase("double"))
        		d.addSlot(paramSlotNames[i], new Value(0.0, RU.FLOAT), "FLOAT");
    		else if(paramSlotTypes[i].equalsIgnoreCase("integer"))
        		d.addSlot(paramSlotNames[i], new Value(0, RU.INTEGER), "INTEGER");		                        			
    	}	                        		
    	engine.addDeftemplate(d);      
    }

    private void modifyJessFactTemplate(Deftemplate d, String[] paramSlotTypes,String[] paramSlotNames, Rete engine ) throws JessException{
    	for(int i=0; i<paramSlotTypes.length; i++){
    		if(paramSlotTypes[i].equalsIgnoreCase("object"))
        		d.addSlot(paramSlotNames[i], Funcall.NIL, "OBJECT");
    		else if(paramSlotTypes[i].equalsIgnoreCase("string"))
        		d.addSlot(paramSlotNames[i], new Value("", RU.STRING), "STRING");
    		else if(paramSlotTypes[i].equalsIgnoreCase("double"))
        		d.addSlot(paramSlotNames[i], new Value(0.0, RU.FLOAT), "FLOAT");
    		else if(paramSlotTypes[i].equalsIgnoreCase("integer"))
        		d.addSlot(paramSlotNames[i], new Value(0, RU.INTEGER), "INTEGER");		                        			
    	}	                        		
    	engine.addDeftemplate(d);      
    }
    
    
    private void defineAndAddJessFactTemplateForParameter(String fullFactID, ParameterTypeVO paramType, Rete engine ) throws JessException{
    	Deftemplate d = new Deftemplate(fullFactID, "", engine);
		
		switch(paramType.getDataType()){
		case ParameterTypeVO.DOUBLE:
    		d.addSlot("value", Funcall.NIL, "FLOAT");
    		break;
		case ParameterTypeVO.STRING:
    		d.addSlot("value", Funcall.NIL, "STRING");
    		break;
		case ParameterTypeVO.INTEGER:
    		d.addSlot("value", Funcall.NIL, "INTEGER");
    		break;
		case ParameterTypeVO.BOOLEAN:
    		d.addSlot("value", Funcall.NIL, "SYMBOL");
    		break;	                        			
		}
		d.addSlot("owner", Funcall.NIL, "OBJECT");
		d.addSlot("source", Funcall.NIL, "SYMBOL");
		d.addSlot("status", Funcall.NIL, "SYMBOL");
		engine.addDeftemplate(d);        		        		
    }

    private void modifyJessFactTemplateForParameter(Deftemplate d, ParameterTypeVO paramType, Rete engine) throws JessException{
		switch(paramType.getDataType()){
		case ParameterTypeVO.DOUBLE:
    		d.addSlot("value", Funcall.NIL, "FLOAT");
    		break;
		case ParameterTypeVO.STRING:
    		d.addSlot("value", Funcall.NIL, "STRING");
    		break;
		case ParameterTypeVO.INTEGER:
    		d.addSlot("value", Funcall.NIL, "INTEGER");
    		break;
		case ParameterTypeVO.BOOLEAN:
    		d.addSlot("value", Funcall.NIL, "SYMBOL");
    		break;	                        			
		}
		engine.addDeftemplate(d);      
    }    
}