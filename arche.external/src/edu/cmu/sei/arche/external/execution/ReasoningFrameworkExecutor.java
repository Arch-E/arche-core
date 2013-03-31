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

package edu.cmu.sei.arche.external.execution;

/**
 * A base class for executing a RF command, sent by RFInteractionAgent, on the current reasoning framework.
 * This is an implementation for RFCommandListener interface that is separated from the implementation of
 * an external reasoning framework. In this way, we can hide all the details for the execution of a RF command
 * from a reasoning framework developer.
 * 
 * [TODO: Need to rewrite the following paragraph] 
 * To deal with the ArchE DB, we follows the pattern "transaction per request"
 * If a request on the execution of a RF command arrives, the execution must start
 * with opening a DB transaction, and then, ends with either 'commit' or 'rollback'
 * to complete the transaction.
 * 
 * @author Hyunwoo Kim
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.external.communication.RFAnalyze;
import edu.cmu.sei.arche.external.communication.RFAnalyzeAndSuggest;
import edu.cmu.sei.arche.external.communication.RFApplySuggestedTactic;
import edu.cmu.sei.arche.external.communication.RFApplyTactics;
import edu.cmu.sei.arche.external.communication.RFCommand;
import edu.cmu.sei.arche.external.communication.RFCommandListener;
import edu.cmu.sei.arche.external.communication.RFDescribeTactic;
import edu.cmu.sei.arche.external.data.ArchEArchitecture;
import edu.cmu.sei.arche.external.data.ArchEDataProvider;
import edu.cmu.sei.arche.external.data.ArchEObject;
import edu.cmu.sei.arche.external.data.ArchERequirementModel;
import edu.cmu.sei.arche.external.data.ArchEScenario;
import edu.cmu.sei.arche.external.data.ArchEVersion;
import edu.cmu.sei.arche.external.reasoningframework.ArchEAnalysisResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEEvaluationResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEReasoningFramework;
import edu.cmu.sei.arche.external.reasoningframework.ArchETryTacticResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEUserQuestion;

public class ReasoningFrameworkExecutor implements RFCommandListener{
	
    private static final int NO_CHECK_RF_DEPENDENCIES	= 0;
    private static final int CHECK_RF_DEPENDENCIES 		= 1;

    private static final int NO_INIT_VIEW  	= 0;
    private static final int INIT_VIEW 		= 1;
    
    private static boolean isArchitectureChanged;
    
	/* Target reasoning framework instance for this class */
    private ArchEReasoningFramework reasoningFramework = null;
	
	private boolean isLearning; // Flag for learning mode (optional feature)

	public ReasoningFrameworkExecutor(ArchEReasoningFramework rf){
		// Set the current reasoning framework
		reasoningFramework = rf;	
		isLearning = false;
	}
	
	//////////////////////////////////////////////////////////////
	// Implementation of RFCommandListener
	
	/**
	 * This is a command for requesting a specific reasoning framework to apply a 
	 * tactic to the current architecture in order to refine it. 
	 * The tactic must come from a user question that was given to 
	 * the user of ArchE and the user agreed to apply. 
	 * The expected result is to have the refined version of the current 
	 * architecture in the database.
	 * 
	 *  @param command The command instance to be executed
	 */
	public void applyTactics(RFApplyTactics command) {
		try {
			isArchitectureChanged = false;
			
			// Get a version info. with the architecture name from DB
			ArchEVersion currentVersion = reasoningFramework.getDataProvider()
					.getVersion(command.getArchitectureName());
			
			// The version must already exist in DB.
			if(currentVersion == null){			
				throw new ArchEException("Warning: " + command.getArchitectureName() + " does not exist in DB.");
			}
			
			// Get current requirement model from DB
			ArchERequirementModel requirementModel = reasoningFramework.getDataProvider()
					.restoreRequirementModel(currentVersion);
			
			// Get current architecture from DB
			ArchEArchitecture architecture = getArchitecture(
					currentVersion,requirementModel,INIT_VIEW,NO_CHECK_RF_DEPENDENCIES);
			
			if(IsCanceled(command))
				return;
			
			// Get a list of AskQuestion (UserQuestion)
			List<ArchEUserQuestion> userQuestions = new ArrayList<ArchEUserQuestion> ();
			List<RawArchEUserQuestion> rawUserQuestions = command.getListOfAskQuestion();
			for(Iterator<RawArchEUserQuestion> it=rawUserQuestions.iterator(); it.hasNext();){
				RawArchEUserQuestion rawUserQuestion = it.next();
				ArchEUserQuestion userQuestion = 
						reasoningFramework.restoreUserQuestion(rawUserQuestion.getID());
				synchronizeUserQuestion(rawUserQuestion,userQuestion);
				userQuestions.add(userQuestion);
			}
								
			boolean applied = false;
		
			// For each UserQuestion, apply a corresponding tactic
			// The current architecture will be refined by applying tactic(s).
			for(int i=0; i<userQuestions.size(); i++){
				applied |= reasoningFramework.applyTacticByUserQuestion(architecture, userQuestions.get(i));
				if(IsCanceled(command))
					return;
			}		
			
			// If applied, save the refined architecture to DB
			if(applied == true || isArchitectureChanged){
				reasoningFramework.getDataProvider().saveArchitecture(architecture);
			}
						
		} catch (ArchEException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This is a command for requesting a reasoning framework to analyze the current architecture 
	 * for scenarios of interest and to suggest new tactics if some scenarios are not fulfilled. 
	 * The reasoning framework has to return the analysis results and the tactics, 
	 * if suggested, to ArchE. Also, it might update the current architecture in the ArchE 
	 * database by checking the responsibility structure consistency and by initializing 
	 * its view based on the responsibility structure. 
	 * 
	 * @param command The command instance to be executed
	 */
	public List<ArchEAnalysisResult> analyzeAndSuggest(RFAnalyzeAndSuggest command, 
			/* out */ List<RawArchETryTacticResult> outRawTactics) {
    	ArrayList<ArchEAnalysisResult> resultList = new ArrayList<ArchEAnalysisResult>(); 
    	
		try {
			isArchitectureChanged = false;
			
			// Get a version info. with the architecture name from DB
			ArchEVersion currentVersion = reasoningFramework.getDataProvider()
					.getVersion(command.getArchitectureName());

			if(IsCanceled(command))
				return resultList;

			// The version must already exist in DB.
			if(currentVersion == null){			
				throw new ArchEException("Warning: " + command.getArchitectureName() + " does not exist in DB.");
			}
			
			// Get current interesting requirement model from DB
			ArchERequirementModel requirementModel = reasoningFramework.getDataProvider()
					.restoreRequirementModel(currentVersion);

			if(IsCanceled(command))
				return resultList;
			
			// Get current architecture from DB
			ArchEArchitecture architecture = getArchitecture(
					currentVersion,requirementModel,INIT_VIEW,CHECK_RF_DEPENDENCIES);

			if(IsCanceled(command))
				return resultList;

			if(!isLearning){
				reasoningFramework.beginLearning();
				isLearning = true;
			}			
			
			// Get scenarios related to the current reasoning framework			
			reasoningFramework.clearRepositoryForSuggestedTactics();	    	
	    	ArrayList<ArchETryTacticResult> outTactics = new ArrayList<ArchETryTacticResult>(); 			
	    	
	    	for(Iterator<ArchEScenario> it = requirementModel
	    					.getScenariosByReasoningFramework(reasoningFramework).iterator(); it.hasNext();) {							
				ArchEScenario scenario = (ArchEScenario)(it.next());				
				ArchEAnalysisResult result = 
						reasoningFramework.analyzeAndSuggest(architecture, scenario, outTactics);				

				resultList.add(result);	

				if(IsCanceled(command))
					return resultList;				
			}
	    	
			// Note that tactics proposals with the same name and parameters are eliminated from the list
	    	outTactics = filterDuplicateTacticEntries(outTactics);
	    	
	    	// Update the analysis values, so that they are available for the next round
	    	reasoningFramework.clearRepositoryForAnalysisResults();
	    	ArchEAnalysisResult resultItem = null;
	    	for (Iterator<ArchEAnalysisResult> newResults = resultList.iterator(); newResults.hasNext();) {
	    		resultItem = newResults.next();
	    		reasoningFramework.saveAnalysisResult(resultItem.getOwner(), resultItem);	    		
	    	}  		
	    	
			ArchETryTacticResult tactic = null;
			for (Iterator<ArchETryTacticResult> itTactics = outTactics.iterator(); itTactics.hasNext();) {
				tactic = (ArchETryTacticResult)(itTactics.next());
				RawArchETryTacticResult rawTacticResult = transformToRawTryTacticResult(tactic);
				reasoningFramework.saveSuggestedTactic(rawTacticResult.getTacticID(), tactic);
				outRawTactics.add(rawTacticResult);
			}								
	    	
			if(IsCanceled(command))
				return resultList;				
			
			// If changed, save the refined architecture to DB
			if(isArchitectureChanged == true){
				reasoningFramework.getDataProvider().saveArchitecture(architecture);
			}	    	
		} catch (ArchEException e) {
			e.printStackTrace();
		}
		
		return (resultList);
	}
	
	private static ArrayList<ArchETryTacticResult> filterDuplicateTacticEntries(
			ArrayList<ArchETryTacticResult> modifTactics) {
		// This comparator is defined below as an internal class
		Comparator<ArchETryTacticResult> tryTacticComparator = new TryTacticResultComparator();		
		TreeSet<ArchETryTacticResult> filtered = new TreeSet<ArchETryTacticResult>(tryTacticComparator);
		for (Iterator<ArchETryTacticResult> it = modifTactics.iterator(); it.hasNext();) 
			filtered.add(it.next());
		modifTactics = new ArrayList<ArchETryTacticResult>();
		for (Iterator<ArchETryTacticResult> it = filtered.iterator(); it.hasNext();) 
			modifTactics.add(it.next());
		return (modifTactics);
	}

	private static ArrayList<ArchEUserQuestion> filterDuplicateQuestionEntries(
			ArrayList<ArchEUserQuestion> warnings) {		
		// This comparator is defined below as an internal class
		Comparator<ArchEUserQuestion> userQuestionComparator = new ArchEUserQuestionComparator();		
		TreeSet<ArchEUserQuestion> filtered = new TreeSet<ArchEUserQuestion>(userQuestionComparator);
		for (Iterator<ArchEUserQuestion> it = warnings.iterator(); it.hasNext();) 
			filtered.add(it.next());
		warnings = new ArrayList<ArchEUserQuestion>();
		for (Iterator<ArchEUserQuestion> it = filtered.iterator(); it.hasNext();) 
			warnings.add(it.next());
		return (warnings);
	}

	static class TryTacticResultComparator implements Comparator<ArchETryTacticResult> {
		
		public TryTacticResultComparator() {			
		}

		public int compare(ArchETryTacticResult o1, ArchETryTacticResult o2) {
			int result = 1;
			if (o1.getTacticName().equals(o2.getTacticName())) {
				List at1 = o1.getAt();
				List at2 = o2.getAt();
				if (at1.size() > at2.size())
					result = 1;
				else if (at1.size() < at2.size())
					result = -1;
				else { // They have the same number of parameters
					boolean allEquals = true;
					for(int i = 0; (i < at1.size()) && allEquals; i++) {
						if (!at1.get(i).equals(at2.get(i)))
							allEquals = false;
					}
					if (allEquals)
						result = 0;
				}
			}
			else 
				result = (o1.getTacticName().compareTo(o2.getTacticName()));

			return (result);
		}
		
	}

	static class ArchEUserQuestionComparator implements Comparator<ArchEUserQuestion> {
		
		public ArchEUserQuestionComparator() {			
		}

		public int compare(ArchEUserQuestion o1, ArchEUserQuestion o2) {
			int result = 1;
			if (o1.getQuestionID().equals(o2.getQuestionID())) {
				List parameters1 = o1.getParameters();
				List parameters2 = o2.getParameters();
				if (parameters1.size() > parameters2.size())
					result = 1;
				else if (parameters1.size() < parameters2.size())
					result = -1;
				else { // They have the same number of parameters
					boolean allEquals = true;
					for(int i = 0; (i < parameters1.size()) && allEquals; i++) {
						if (!parameters1.get(i).equals(parameters2.get(i)))
							allEquals = false;
					}
					if (allEquals)
						result = 0;
				}
			}
			else 
				result = (o1.getQuestionID().compareTo(o2.getQuestionID()));

			return (result);
		}
		
	}

	/**
	 * This is a command for requesting a reasoning framework to apply a tactic to the current 
	 * architecture in order to create a new candidate architecture. The tactic must be one 
	 * of the tactics that the reasoning framework suggested as a result of executing 
	 * the AnalyzeAndSuggest command. The expected result is to have a candidate architecture 
	 * in the database.
	 * 
	 * @param command The command instance to be executed
	 */
	public void applySuggestedTactic(RFApplySuggestedTactic command) {
		try {			
			// Get a version info. with the architecture name from DB
			ArchEVersion currentVersion = reasoningFramework.getDataProvider()
					.getVersion(command.getArchitectureName());

			if(IsCanceled(command))
				return;
			
			// The version must already exist in DB.
			if(currentVersion == null){			
				throw new ArchEException("Warning: " + command.getArchitectureName() + " does not exist in DB.");
			}
			
			// Get current requirement model from DB
			ArchERequirementModel requirementModel = reasoningFramework.getDataProvider()
					.restoreRequirementModel(currentVersion);

			if(IsCanceled(command))
				return;
			
			// Get current architecture from DB
			ArchEArchitecture architecture = getArchitecture(
					currentVersion,requirementModel,INIT_VIEW,NO_CHECK_RF_DEPENDENCIES);

			if(IsCanceled(command))
				return;
			
			// Get a suggested tactic to apply from the repository for suggested tactics
			ArchETryTacticResult suggestedTactic = reasoningFramework.restoreSuggestedTactic(
							command.getApplySuggestedTactic().getTacticID());
			
			// Apply the suggested tactic, which in turn modify the architecture
			boolean applied = reasoningFramework.applySuggestedTactic(architecture, suggestedTactic);

			if(IsCanceled(command))
				return;
			
			// If applied, save the refined architecture to DB
			if(applied == true){
				// Get a version info. with the candidate name from DB
				ArchEVersion candidateVersion = reasoningFramework.getDataProvider()
						.getVersion(command.getCandidateName());
				
				// If the candidate version exists, delete it.
				if(candidateVersion != null){
					// Delete the existing candidate architecture from DB	
					reasoningFramework.getDataProvider().deleteArchitecture(candidateVersion);
				}
				
				// Create a new version with the candidate name. 
				ArchEVersion newCandidateVersion = reasoningFramework.getDataProvider()
						.newChildVersion(currentVersion, command.getCandidateName());

				// Save the requirement model as a new version
				reasoningFramework.getDataProvider()
						.saveRequirementModelAs(requirementModel,newCandidateVersion);		
				
				// Save the architecture as a new version
				reasoningFramework.getDataProvider()
						.saveArchitectureAs(architecture,newCandidateVersion);		
			}
		} catch (ArchEException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is a command for requesting a reasoning framework to analyze a candidate 
	 * architecture for scenarios of interest. The reasoning framework has to return 
	 * the evaluation results that ArchE will use to prioritize candidate architectures.
	 * 
	 * @param command The command instance to be executed
	 */
	public List<ArchEEvaluationResult> analyze(RFAnalyze command) {
    	ArrayList<ArchEEvaluationResult> evaluationResultList = new ArrayList<ArchEEvaluationResult>(); 

		try {
			// Get a version info. with the architecture name from DB
			ArchEVersion candidateVersion = reasoningFramework.getDataProvider()
					.getVersion(command.getCandidateName());

			if(IsCanceled(command))
				return evaluationResultList;
			
			// The version must already exist in DB.
			if(candidateVersion == null){			
				throw new ArchEException("Warning: " + command.getCandidateName() + " does not exist in DB.");
			}
			
			
			// Get current requirement model from DB
			ArchERequirementModel requirementModel = reasoningFramework.getDataProvider()
					.restoreRequirementModel(candidateVersion);

			if(IsCanceled(command))
				return evaluationResultList;
			
			// Get current architecture from DB
			ArchEArchitecture architecture = getArchitecture(
					candidateVersion,requirementModel,INIT_VIEW,CHECK_RF_DEPENDENCIES);

			if(IsCanceled(command))
				return evaluationResultList;
			
			if(!isLearning){
				reasoningFramework.beginLearning();
				isLearning = true;
			}			
			
			for(Iterator<ArchEScenario> it = requirementModel
								.getScenariosByReasoningFramework(reasoningFramework).iterator(); it.hasNext();) {
				ArchEScenario scenario = (ArchEScenario)(it.next());				
				ArchEEvaluationResult result = 
						reasoningFramework.analyze(architecture, scenario);				
				evaluationResultList.add(result);		
				
				if(IsCanceled(command))
					return evaluationResultList;				
			}
		} catch (ArchEException e) {
			e.printStackTrace();
		}
		return (evaluationResultList);
	}

	/**
	 * This is a command for requesting a reasoning framework to provide ArchE with 
	 * user-friendly questions that describe tactics or any other recommendations.
	 * 
	 * @param command The command to be executed
	 */
	public List<RawArchEUserQuestion> describeTactics(RFDescribeTactic command) {
		List<RawArchEUserQuestion> rawUserQuestions = new ArrayList<RawArchEUserQuestion>();

		// Get a list of DescribeTacticToUser
		List<ArchEDescribeTacticToUser> listOfDescribeTacticToUser = command.getListOfDescribeTacticToUser();

		try {
			// Get a version info. with the architecture name from DB
			ArchEVersion currentVersion = reasoningFramework.getDataProvider()
					.getVersion(command.getArchitectureName());

			if(IsCanceled(command))
				return rawUserQuestions;
			
			// The version must already exist in DB.
			if(currentVersion == null){			
				throw new ArchEException("Warning: " + command.getArchitectureName() + " does not exist in DB.");
			}
			
			
			// Get current requirement model from DB
			ArchERequirementModel requirementModel = reasoningFramework.getDataProvider()
					.restoreRequirementModel(currentVersion);

			if(IsCanceled(command))
				return rawUserQuestions;
			
			// Get current architecture from DB
			ArchEArchitecture architecture = getArchitecture(
					currentVersion,requirementModel,NO_INIT_VIEW,NO_CHECK_RF_DEPENDENCIES);
			
			if(IsCanceled(command))
				return rawUserQuestions;
						
			reasoningFramework.clearRepositoryForUserQuestions();	    	
			
			// For any tactic to describe, execute describetTactic to get a relevant user question
			for(Iterator<ArchEDescribeTacticToUser> it = listOfDescribeTacticToUser.iterator(); it.hasNext();){
				ArchEDescribeTacticToUser describeTacticToUser = it.next();				
				ArchETryTacticResult tactic = reasoningFramework.restoreSuggestedTactic(
							describeTacticToUser.getTacticID());
				ArchEUserQuestion userQuestion = 
						reasoningFramework.describeTactic(tactic);
				userQuestion.setQuestionGroup(describeTacticToUser.getQuestionGroupID());
				userQuestion.setReasoningFramework(reasoningFramework.getID());
				userQuestion.setPriority(describeTacticToUser.getRelevance());
				userQuestion.setSearchStep(describeTacticToUser.getSearchStep());
				
				RawArchEUserQuestion rawUserQuestion = transformToRawUserQuestion(userQuestion);
				if(rawUserQuestion != null){
					reasoningFramework.saveUserQuestion(rawUserQuestion.getID(), userQuestion);
					rawUserQuestions.add(rawUserQuestion);						
				}

				if(IsCanceled(command))
					return rawUserQuestions;				
			}		
			
			// Get other user questions that are not related to tactics
			List<ArchEUserQuestion> otherQuestions = 
					reasoningFramework.describeOtherThanTactic(requirementModel,architecture);
			if(otherQuestions != null){
				// Note that questions with the same name and parameters are eliminated from the list
				otherQuestions = filterDuplicateQuestionEntries((ArrayList<ArchEUserQuestion>)otherQuestions);
				for(Iterator<ArchEUserQuestion> it = otherQuestions.iterator(); it.hasNext();){
					ArchEUserQuestion userQuestion = it.next();
					userQuestion.setQuestionGroup("others");
					userQuestion.setReasoningFramework(reasoningFramework.getID());
					userQuestion.setPriority(0);
					userQuestion.setSearchStep(0);
					
					RawArchEUserQuestion rawUserQuestion = transformToRawUserQuestion(userQuestion);
					if(rawUserQuestion != null){
						reasoningFramework.saveUserQuestion(rawUserQuestion.getID(), userQuestion);						
						rawUserQuestions.add(rawUserQuestion);						
					}
				}
			}
			
			// Get questions related to "learning"
			List<ArchEUserQuestion> learnQuestions =
					reasoningFramework.describeWhatLearned();
			if(learnQuestions != null){
				for(Iterator<ArchEUserQuestion> it = learnQuestions.iterator(); it.hasNext();){
					ArchEUserQuestion userQuestion = it.next();
					userQuestion.setQuestionGroup("learning");
					userQuestion.setReasoningFramework(reasoningFramework.getID());
					userQuestion.setPriority(0);
					userQuestion.setSearchStep(0);					
					
					RawArchEUserQuestion rawUserQuestion = transformToRawUserQuestion(userQuestion);
					if(rawUserQuestion != null){						
						reasoningFramework.saveUserQuestion(rawUserQuestion.getID(), userQuestion);						
						rawUserQuestions.add(rawUserQuestion);						
					}
				}				
			}
			
			reasoningFramework.clearAnalysisStatus();
			reasoningFramework.clearResponsibilityStructureStatus();


		} catch (ArchEException e) {
			e.printStackTrace();
		}
		return rawUserQuestions;
	}
	
	//////////////////////////////////////////////////////////////
	// local methods
	
	private ArchEArchitecture getArchitecture(ArchEVersion version,ArchERequirementModel requirementModel, int initView, int checkRFDependencies) throws ArchEException{
		// This code should rely on the dataProvider to get the architecture from the database
		// If the design structure doesn�t exist within the architecture, it should be created		

		ArchEDataProvider dataProvider = reasoningFramework.getDataProvider();
		ArchEArchitecture architecture = dataProvider.restoreArchitecture(version);		
		
		// If architecture already exists, 
		if(architecture != null){			
			
			if(needsToCheckRFDependencies(checkRFDependencies)){
				// Check RF dependencies
				isArchitectureChanged |= reasoningFramework.checkRFDependencies(requirementModel, architecture.getResponsibilityStructure());				
			}
			
			if(needsToInitializeView(initView) && architecture.getView() != null){
				// Initialize a view of the architecture based on its responsibility structure
				// if the view exists
				isArchitectureChanged |= reasoningFramework.initializeView(architecture.getView(),architecture.getResponsibilityStructure(),requirementModel);										
			}
			
		}
		else
			throw new ArchEException("Failed to get an architecture in DB: its name is " + version.getVersionName());
		return (architecture);
	}			

	///////////////////////////////////////////////////////////////////////
	// Local utility methods
	
	/*
	 * To check whether to need to check RF dependencies or not
	 */
	private boolean needsToCheckRFDependencies(int checkRFDependencies) {
		if(checkRFDependencies == CHECK_RF_DEPENDENCIES)
			return true;
		else
			return false;
	}

	/*
	 * To check whether to need to initialize a view or not
	 */
	private boolean needsToInitializeView(int initView) {
		if(initView == INIT_VIEW)
			return true;
		else
			return false;
	}	
	
	/*
	 * To check whether this command is canceled by the ArchE or not
	 */
	private boolean IsCanceled(RFCommand command) {
		return (command.getRFInteractionAgent().isCanceled());
	}
	
	private RawArchETryTacticResult transformToRawTryTacticResult(ArchETryTacticResult tryTacticResult){
		RawArchETryTacticResult rawTryTacticResult = new RawArchETryTacticResult();
		rawTryTacticResult.setTacticID("t" + tryTacticResult.hashCode());
		rawTryTacticResult.setReasoningFramework(tryTacticResult.getReasoningFramework());
		rawTryTacticResult.setScenario(tryTacticResult.getScenario().getFactId());
		rawTryTacticResult.setTacticName(tryTacticResult.getTacticName());
		rawTryTacticResult.setTacticDescription(tryTacticResult.getTacticDescription());
		return rawTryTacticResult;
	}
	
	private RawArchEUserQuestion transformToRawUserQuestion(ArchEUserQuestion userQuestion){
		if(userQuestion == null)
			return null;
		
		RawArchEUserQuestion rawUserQuestion = new RawArchEUserQuestion();
		rawUserQuestion.setID("q" + userQuestion.hashCode());
		rawUserQuestion.setQuestionID(userQuestion.getQuestionID());
		rawUserQuestion.setReasoningFramework(userQuestion.getReasoningFramework());				
		rawUserQuestion.setQuestionGroup(userQuestion.getQuestionGroup());				
		rawUserQuestion.setPriority(userQuestion.getPriority());
		rawUserQuestion.setSearchStep(userQuestion.getSearchStep());
		
		ArchEObject parent = userQuestion.getParent();
		if(parent != null)
			rawUserQuestion.setParent(parent.getFactId());
				
		if(userQuestion.getAffectedFacts() != null)
			rawUserQuestion.setAffectedFacts(
					transformToStringList(userQuestion.getAffectedFacts().iterator()));				
		
		if(userQuestion.getParameters() != null)
			rawUserQuestion.setParameters(
					transformToStringList(userQuestion.getParameters().iterator()));				
		
		if(userQuestion.getDefaults() != null)
			rawUserQuestion.setDefaults(
					transformToStringList(userQuestion.getDefaults().iterator()));				

		if(userQuestion.getOptions() != null)
			rawUserQuestion.setOptions(
					transformToStringList(userQuestion.getOptions().iterator()));				
		
		return rawUserQuestion;
	}
	
	private List<String> transformToStringList(Iterator iter){
		List<String> stringList =  new ArrayList<String>();		
		while(iter.hasNext()){
			Object item = iter.next();
			if(item instanceof ArchEObject)
				stringList.add(((ArchEObject)item).getFactId());						
			else if(item instanceof String)
				stringList.add((String)item);
			else if(item instanceof Double)
				stringList.add(((Double)item).toString());
			else if(item instanceof Integer)
				stringList.add(((Integer)item).toString());
		}	
		return stringList;
	}
		
	private void synchronizeUserQuestion(RawArchEUserQuestion newUserQuestion, ArchEUserQuestion oldUserQuestion){		
		// synchronize parent if applicable
		if(oldUserQuestion.getParent() != null){
			String newID = newUserQuestion.getParent();			
			String oldID = oldUserQuestion.getParent().getFactId();
			if(newID != null && oldID != null && !newID.equals(oldID))
				oldUserQuestion.getParent().setFactId(newID);
		}
		
		// synchronize list parameters 
		synchronize(newUserQuestion.getAffectedFacts(), oldUserQuestion.getAffectedFacts());
		synchronize(newUserQuestion.getParameters(), oldUserQuestion.getParameters());
		synchronize(newUserQuestion.getOptions(), oldUserQuestion.getOptions());
		synchronize(newUserQuestion.getDefaults(), oldUserQuestion.getDefaults());
				
		// get answers
		oldUserQuestion.setAnswers(newUserQuestion.getAnswers());			
	}
	
	private void synchronize(List<String> newList, List oldList){
		if(newList == null || oldList == null)
			return;
		
		Iterator<String> itNew=newList.iterator() ; 
		Iterator itOld = oldList.iterator();
		
		while(itOld.hasNext()){
			Object itemOld = itOld.next();
			String newID = itNew.next();
			if(itemOld != null && itemOld instanceof ArchEObject){
				String oldID = ((ArchEObject)itemOld).getFactId();
				if(newID != null && !newID.equals(oldID))
					((ArchEObject)itemOld).setFactId(newID);
			}
		}		
	}
}
