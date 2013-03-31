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

package edu.cmu.sei.arche.external.reasoningframework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.SAXException;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.external.communication.RFAgentException;
import edu.cmu.sei.arche.external.communication.RFInteractionAgent;
import edu.cmu.sei.arche.external.data.ArchEArchitecture;
import edu.cmu.sei.arche.external.data.ArchEDataProvider;
import edu.cmu.sei.arche.external.data.ArchERequirementModel;
import edu.cmu.sei.arche.external.data.ArchEResponsibilityStructure;
import edu.cmu.sei.arche.external.data.ArchEScenario;
import edu.cmu.sei.arche.external.data.ArchEView;
import edu.cmu.sei.arche.external.execution.ReasoningFrameworkExecutor;
import edu.cmu.sei.arche.rfconfig.ConfigReader;
import edu.cmu.sei.arche.vo.OperandsVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.ScenarioTypeVO;


/**
 * A base class for implementing external reasoning frameworks
 * A XXX reasoning framework has the following responsibilities:
 * 	1)	Initialize XXX Design
 *  2)	Apply XXX Tactics
 *  3)	XXX Analysis
 *  4)	Suggest XXX Tactics
 *  5)	Describe XXX Tactics
 *  6)	XXX Learn 
 *  
 *  Each responsibility is linked to a method below, except that
 *  the analyzeAndSuggest method implements the Suggest XXX Tactics responsibility
 *  as well as the XXX Analysis responsibility just in order to avoid using an analytic
 *  model directly. It will free RF developers from having to stick to a built-in
 *  analytic model.
 * 
 * A reasoning framework has the following components:
 *  1) a communication agent of type RFInteractionAgent
 * 	2) a data provider of type RFDataProvider.
 *  
 * We assume that no more than one reasoning framework can share a communication agent and
 * a data provider.
 * 
 * Also, it is configured by loading its configuration XML file.
 * It might have a logger for logging its status.
 * 
 * @author Andres Diaz-Pace, Hyunwoo Kim
 */

public abstract class ArchEReasoningFramework {
	
	protected RFInteractionAgent interactionAgent;     	
	protected ArchEDataProvider dataProvider;
	protected ReasoningFrameworkVO rfVO;
		
	// <ScenarioFactID,ArchEAnalysisResult>
	protected Hashtable<String,ArchEAnalysisResult> repositoryAnalysisResult = new Hashtable<String,ArchEAnalysisResult>();	

	// <TacticID,ArchETryTacticResult>
	private Hashtable<String,ArchETryTacticResult> repositorySuggestedTactic = new Hashtable<String,ArchETryTacticResult>();

	// <id,ArchEUserQuestion>
	private Hashtable<String,ArchEUserQuestion> repositoryUserQuestion = new Hashtable<String,ArchEUserQuestion>();
	
	public ArchEReasoningFramework(){}

	
	/////////////////////////////////////////////////////////////////////////////////////
	// Abstract methods for key responsibilities of a reasoning framework 

    /** 
     * Implement this method iff your reasoning framework wants to assure that if another reasoning
     * framework has already made changes to the given responsibility structure and some of the changes
     * have any dependencies (negative effects) on your reasoning framework (your view and your analysis model),
     * the dependencies have to be checked and resolved. 
     * 
     * The possible negative dependencies include:
     * 	- Responsibility relations of your interest that have missing responsibilities (orphans)
     * 	  (e.g., another reasoning framework deleted a responsibility that is part of the responsibility
     *    relation in which you're interested)
     *    Resolution: remove such responsibility relations
     *    
     *  - Parameters of responsibilities of your interest that have no values
     *    (e.g., unbound execution time of a responsibility for a real-time scheduling reasoning framework) 
     *    Resolution: Assign default values to unbound parameters
     *    
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param requirementModel the requirement model instance given only as an input, it might be used to
     * find the responsibilities and their relations in which you're interested 
     * @param responsibilityStructure the responsibility structure instance used as an input and output
     * @return true if this check made any changes to the responsibility structure. Otherwise, return false.
     * @exception ArchEException
     */        
    public abstract boolean checkRFDependencies(ArchERequirementModel requirementModel,ArchEResponsibilityStructure responsibilityStructure) throws ArchEException;
    
    /** 
     * Implement this method iff your reasoning framework wants to initialize its view before
     * starting an analysis or applying a tactic on the architecture.
     * In the process of the initialization, you might implement your design rules of creating or
     * updating your view based on the given responsibility structure (set of responsibilities
     * and set of their relations) 
     * 
     * If your reasoning framework will support only analysis, just do nothing with this method.
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param view the view instance used as an input and output. It's created by the creatView() method above
     * and restored from DB if the view already exists in the DB. You can directly update this view by adding
     * or deleting or modifying a design element or design relation. 
     * @param responsibilityStructure the responsibility structure instance given only as an input
     * @param requirementModel the requirement model instance given only as an input
     * @return true if this initialization made any changes to the view or the responsibility structure. Otherwise, return false.
     * @exception ArchEException
     */        
    public abstract boolean initializeView(ArchEView view,ArchEResponsibilityStructure responsibilityStructure,ArchERequirementModel requirementModel) throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to apply a tactic chosen by the user
     * of ArchE. You need to apply the tactic described by the given user question to the architecture.
	 *
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param architecture the architecture to which a tactic is to be applied
     * @param userQuestion the user question describing a tactic to apply and its all necessary
     * parameters
     * @return the boolean value. Return true if the tactic is applied successfully. Otherwise,
     * return false.
     * @exception ArchEException
     */        
	public abstract boolean applyTacticByUserQuestion(ArchEArchitecture architecture,
			ArchEUserQuestion userQuestion) throws ArchEException;
	
    /** 
     * Implement this method iff your reasoning framework wants to suggest tactics back to the ArchE
     * based on your analysis on the architecture for the given scenario.
     * 
     * You might need to do the following things.
     * 1) create your quality attribute model based on the architecture (i.e., view)
     * 2) analyze the quality attribute model
     * 3) suggest tactics that result from adjusting parameters of the quality attribute model
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param architecture the input architecture to be analyzed
     * @param scenario the input scenario for which the architecture will be analyzed
     * @param outTactics the output list of suggested tactics 
     * @return the ArchEAnalysisResult instance representing an analysis result of the architecture
     * for the given scenario
     * @exception ArchEException
     */        
	public abstract ArchEAnalysisResult analyzeAndSuggest(ArchEArchitecture architecture,
			ArchEScenario scenario, /* out */ List<ArchETryTacticResult> outTactics)
			throws ArchEException;

	
    /** 
     * Implement this method iff your reasoning framework wants to apply one of its tactics suggested by
     * the analyzeAndSuggest() method. You need to apply the suggested tactic to the given architecture.
	 *
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param architecture the architecture to which a tactic is to be applied
     * @param suggestedTactic one of the tactics suggested as a result of executing the analyzeAndSuggest() method
     * @return the boolean value. Return true if the tactic is applied successfully. Otherwise,
     * return false.
     * @exception ArchEException
     */
	public abstract boolean applySuggestedTactic(
			ArchEArchitecture architecture,
			ArchETryTacticResult suggestedTactic) throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to evaluate the architecture
     * for the given scenario and to return evaluation results.
     * 
     * You might need to do the following things.
     * 1) create your quality attribute model based on the architecture (i.e., view)
     * 2) analyze the quality attribute model
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param architecture the input architecture to be analyzed
     * @param scenario the input scenario for which the architecture will be analyzed
     * @return the ArchEEvaluationResult instance representing an evaluation result of the architecture
     * for the given scenario
     * @exception ArchEException
     */        
	public abstract ArchEEvaluationResult analyze(ArchEArchitecture architecture,
			ArchEScenario scenario) throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to describe its tactic to the user of ArchE.
     * It must return a question of ArchEUserQuestion so that, in turn, ArchE can make a question for the user.
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param tactic the tactic to describe to the user of ArchE
     * @return the ArchEUserQuestion instance describing the tactic in a way that the ArchE user
     * can understand
     * @exception ArchEException
     */        	
	public abstract ArchEUserQuestion describeTactic(ArchETryTacticResult tactic) throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to describe suggestions other than tactics
     * to the user of ArchE. You can return more than one question.
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @param requirementModel the input requirement model which you might refer to 
     * @param architecture the input architecture which you might refer to
     * @return the list of ArchEUserQuestion instances, each representing a question for the user of ArchE
     * @exception ArchEException
     */        	
	public abstract List<ArchEUserQuestion> describeOtherThanTactic(ArchERequirementModel requirementModel,ArchEArchitecture architecture) throws ArchEException;
	
    /** 
     * Implement this method iff your reasoning framework works with tactics and has to
     * make assumptions about what the concrete instance of a tactic might be. For example,
     * putting a more abstract interface to a module will influence the probabilities of
     * the dependencies, but how much strongly depends on the level of abstraction. 
     * 
     * This method begins a learning process, at least, by making "default" assumptions.
     * These are opportunities for learning.
     * 
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @exception ArchEException
     */        
	public abstract void beginLearning() throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to learn about data of interest
     * for later adjusting the assumptions made to create concrete instances of a tactic. 
     * 
     * This method learns about the data given as input arguments. It might probe and
     * keep track of all "Learn Values" of interest.
     * 
     * Note that you can call this method any place in your code where your reasoning framework wants to learn.
     * It will not be called by the ArchE External Interface.
     * 
     * @param typeOfLearning the integer indicating a type of learning for which relevant data will be learned
     * @param args the list of Java objects of any type that will be given as inputs for the probing
     * @exception ArchEException
     */        
	public abstract void learnBy(int typeOfLearning, Object[] args) throws ArchEException;

    /** 
     * Implement this method iff your reasoning framework wants to describe suggestions based on
     * what have been learned to the user of ArchE who, in turn, will make a decision about
     * whether to apply those suggestions or not. 
	 *
     * Note that you must not call this method directly because the calling is governed by the ArchE
     * External Interface.
     * 
     * @return the list of ArchEUserQuestion instances, each is a question to ask whether to
     * adjust some assumption(s) or not
     * @exception ArchEException
     */        
	public abstract List<ArchEUserQuestion> describeWhatLearned() throws ArchEException;

	
	/////////////////////////////////////////////////////////////////////////////////////
	// Managing Analysis Results
	public void clearRepositoryForAnalysisResults(){
		repositoryAnalysisResult.clear();
	}
	
	public void saveAnalysisResult(String scenarioFactID,ArchEAnalysisResult analysisResult){
		repositoryAnalysisResult.put(scenarioFactID, analysisResult);
	}
	public ArchEAnalysisResult restoreAnalysisResult(String scenarioFactID){		
		return repositoryAnalysisResult.get(scenarioFactID);
	}
	
	public Hashtable<String,ArchEAnalysisResult> getRepositoryForAnalysisResults(){
		return repositoryAnalysisResult;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Managing TryTactic Results
	public void clearRepositoryForSuggestedTactics(){
		repositorySuggestedTactic.clear();
	}
	
	public void saveSuggestedTactic(String tacticID,ArchETryTacticResult suggestedTactic){
		repositorySuggestedTactic.put(tacticID, suggestedTactic);
	}
	public ArchETryTacticResult restoreSuggestedTactic(String tacticID){		
		return repositorySuggestedTactic.get(tacticID);
	}
	
	public Hashtable<String,ArchETryTacticResult> getRepositoryForSuggestedTactics(){
		return repositorySuggestedTactic;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Managing User Questions
	public void clearRepositoryForUserQuestions(){
		repositoryUserQuestion.clear();
	}
	
	public void saveUserQuestion(String id,ArchEUserQuestion userQuestion){
		repositoryUserQuestion.put(id, userQuestion);
	}
	public ArchEUserQuestion restoreUserQuestion(String id){		
		return repositoryUserQuestion.get(id);
	}
	
	protected List<ArchEUserQuestion> getAllUserQuestions(String questionId) {
		List<ArchEUserQuestion> result = new ArrayList<ArchEUserQuestion>();
		ArchEUserQuestion question = null;
		for(Enumeration<ArchEUserQuestion> enume = repositoryUserQuestion.elements(); enume.hasMoreElements();)  {
			question = enume.nextElement();
			if (question.getQuestionID().equals(questionId))
				result.add(question);
		}
		return (result);
	}
	
	public Hashtable<String,ArchEUserQuestion> getRepositoryForUserQuestions(){
		return repositoryUserQuestion;
	}	
		
	/////////////////////////////////////////////////////////////////////////////////////
	// Data Provider
	public ArchEDataProvider getDataProvider() {
		return (dataProvider);
	}	
	
	public void setDataProvider(ArchEDataProvider provider) {
		dataProvider = provider;
		return;
	}	
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Manifesto: Self Description
	public void configure(String rfConfigFilePath){
        try {
			rfVO = ConfigReader.parse(rfConfigFilePath);
			if(rfVO != null){
				rfVO.setInstallPathName(rfConfigFilePath.substring(0,rfConfigFilePath.lastIndexOf("/")) + "/");
				rfVO.setRFConfigFileName(rfConfigFilePath.substring(rfConfigFilePath.lastIndexOf("/") + 1));				
			}
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ArchEException e1) {
			e1.printStackTrace();
		}				
		
		// Instantiate an interaction agent for communicating ArchE vis XmlBlaster
		interactionAgent = new RFInteractionAgent(this);    
		
		// Set ReasoningFrameworkExecutor as a default RF commmand listener
		interactionAgent.addRFCommandListener(new ReasoningFrameworkExecutor(this));		
	}
	
	/* Self Description */
	public String getID() {
		return rfVO.getId();
	}

	public String getName() {
		return rfVO.getName();
	}

	public String getQuality() {
		return rfVO.getQuality();
	}
	
	public String getInstallPathName(){
		return rfVO.getInstallPathName();	
	}

	public String getRFConfigFileName(){
		return rfVO.getRFConfigFileName();
	}
	
	public String[] getScenarioTypeIDs(){
		ArrayList<String> scenarioTypeList = new ArrayList<String>();		
		Iterator<ScenarioTypeVO> iter = rfVO.getScenarioTypes().iterator();
		while(iter.hasNext()){
			ScenarioTypeVO scenarioType = (ScenarioTypeVO)(iter.next());
			scenarioTypeList.add(scenarioType.getId());
		}		
		return (String[]) scenarioTypeList.toArray(new String[0]);
//		return (String[]) scenarioTypeList.toArray(new String[0]);
	}
	
	
    public String[]  getRelationshipTypeIDs() {
		ArrayList<String> getRelationshipTypeList = new ArrayList<String>();		
        for (Iterator<RelationshipTypeVO> it = rfVO.getRelationshipTypes().iterator(); it.hasNext();) {
            RelationshipTypeVO relationType = (RelationshipTypeVO) it.next();
            relationType.setExternal(true);
            // loop through allowed operands of that relationship type
            for (Iterator<OperandsVO> it2 = relationType.getAllowedOperands().iterator(); it2.hasNext();) {
                OperandsVO operands = (OperandsVO) it2.next();
                if (operands.getLhs().equals(OperandsVO.RESPONSIBILITY)
                        && operands.getRhs().equals(OperandsVO.RESPONSIBILITY)) {
                    // Found a relationship type between responsibilities.
                	getRelationshipTypeList.add(relationType.getId());
                }
            }
        }    	
		return (String[]) getRelationshipTypeList.toArray(new String[0]);
    }
			
	/////////////////////////////////////////////////////////////////////////////////////
	// Start/Stop reasoning framework
    public void start() throws ArchEException{
    	try {
			interactionAgent.start();
		} catch (RFAgentException e) {
			throw new ArchEException(e.getMessage(),e.getCause());
		}    	
	}

    public void stop() throws ArchEException {
    	try {
    		interactionAgent.stop();						    		
		} catch (RFAgentException e) {
			throw new ArchEException(e.getMessage(),e.getCause());
		}    	    	
	}	
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Logging
    private Logger log = null;
    
    public Logger getLogger(){
    	if(log == null)
    		log  = Logger.getLogger(getID());
    	return log;
    }

    public void printLog(int depth, Level level, String msg){
    	String taps = "";
    	for (int i=0; i < depth; i++)
    		taps += "\t";
    	
    	if(level == Level.INFO)
    		getLogger().info(taps + msg);
    	else if(level == Level.WARNING)
    		getLogger().warning(taps + msg);
    	else if(level == Level.SEVERE)
    		getLogger().severe(taps + msg);
    }	
	/////////////////////////////////////////////////////////////////////////////////////	
    // Problem reports

	protected static final int RF_OK = 0;
	protected static final int RF_WARNING = 1;
	protected static final int RF_ERROR = 2;

	protected int responsibilityStructureStatus = RF_OK ;
	protected int analysisStatus = RF_OK; 
	protected Hashtable<Integer,ArchEAnalysisProblem> reportedProblems = new Hashtable<Integer,ArchEAnalysisProblem>();

	public void clearAnalysisStatus() {
		analysisStatus = RF_OK;
		reportedProblems.clear();
	}
	
	protected int setAnalysisStatus(int status) {
		int oldValue = analysisStatus;
		analysisStatus = status;
		return (oldValue);
	}

	protected int setAnalysisStatus(int status, int errorType, ArchEAnalysisProblem problem) {
		reportedProblems.put(errorType, problem);
		return (this.setAnalysisStatus(status));
	}

	protected int getAnalysisStatus() {
		return (analysisStatus);
	}
	
	protected boolean isAnalysisValid() {
		return (analysisStatus == RF_OK) || (analysisStatus == RF_WARNING);
	}

	public void clearResponsibilityStructureStatus() {
		responsibilityStructureStatus = RF_OK ;
		reportedProblems.clear();
	}

	protected int setResponsibilityStructureStatus(int status) {
		int oldValue = responsibilityStructureStatus;
		responsibilityStructureStatus = status;
		return (oldValue);
	}

	protected int setResponsibilityStructureStatus(int status, int errorType, ArchEAnalysisProblem problem) {
		reportedProblems.put(errorType, problem);
		return (this.setResponsibilityStructureStatus(status));
	}

	protected int getResponsibilityStructureStatus() {
		return (responsibilityStructureStatus);
	}

	protected boolean isResponsibilityStructureValid() {
		return (responsibilityStructureStatus == RF_OK) 
			|| (responsibilityStructureStatus == RF_WARNING);
	}
   
}
