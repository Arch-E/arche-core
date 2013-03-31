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

/*******************************************************************************
 * The Seeker controls the reasoning about the current architecture. Interacts
 * with the reasoning frameworks to discover problems, possible alternative
 * solutions, and trade-off conditions.
 * So far the Seeker�s responsibilities are:
 * - Defining the scope (set of scenarios) for exploring solutions
 * - Interacting with reasoning frameworks to evaluate the current architecture
 * - Interacting with reasoning frameworks to create candidate architectures
 * - Assessing candidate architecture
 * - Searching of better solutions with possibly interacting with external tools such as SAT solvers
 * - Recognizing trade-off situations
 *******************************************************************************/
 
/* Now the require statements of all the file required by this module */
(require Seeker)
(require MAIN)
(require Planner)
(require Configuration)
(require Logger)
(set-current-module Seeker)


/******************************************************************************
 * Now the options part. The following options are available:
 * (ModuleOption Debug) - enables debugging facility
 * (ModuleOption Events Enter Exit) - enables the OnEnter and/or the OnExit event
 * (ModuleOptions Sequence A B C) - enables the sequence of phases (1st A, then B, and last C)
 *******************************************************************************/
(ModuleOption Debug)
(ModuleOption Sequence init create save candidates assess next user cleanup refreshUI)

/******************************************************************************
 * Now the questions part. For every question asked in this module provide the
 * following information:
 * (bind ?qu (new ArchEQuestion XXX)) - creates a new question instance with the questionID XXX
 * (?qu setConditions listOfStrings) - sets the CE conditions for when to ask this question
 * (?qu setParent "?sc") - sets the string that identifies the parent fact for this question
 * (?qu setDataCollection listOfStrings) - sets the jess statements to collect data used in the question
 * (?qu setAffectedFacts "(create$ ?sc ?rr)") - sets the jess command that delivers the affected facts
 * (?qu setParameters "?rr") - sets the jess command that delivers the parameters
 * (?qu setDefaults "yes") - sets the jess command that delivers the default values
 * (?qu setOptions "2") - sets the jess command that delivers the option values
 * (?qu setActions "yes" listOfStrings) - sets the jess statements executed if users answer equal first parameter
 *                                      - many of those statements can follow according to different answers
 * (?qu generate (engine)) - generates the set of rule that will ask the question and react to the answers
 *******************************************************************************/

 /******************************************************************************
 * init init init init init init init init init init init init init init init  
 *******************************************************************************/
;
; Activate all the reasoning frameworks to create, evaluate a new current architecture
; and to suggest tactics on how to make it better
;
(defglobal ?*QuestionGroup* = group)
(defrule GetStartTimeofSearch
	(declare (salience 100))
	(T_ControlModelExecution init)    
    (not (Seeker::SearchStartTime ?))
=>
	(assert (Seeker::SearchStartTime (time)))
)

(defrule SeekerCleanupEvaluationResult
	(T_ControlModelExecution init)    
    ?cm <- (Seeker::EvaluationResult) 
=>
    (printout t "--------Remove old EvaluationResult " crlf)
    (retract ?cm)
)

(defrule SeekerCleanupDeleteRecord
	(T_ControlModelExecution init)    
    ?s <- (Seeker::Delete ? ?) 
=>
    (printout t "--------Remove old DeteteRecord " crlf)
    (retract ?s)
)

(defrule SeekerCleanupRestoreRecord
	(T_ControlModelExecution init)    
    ?s <- (Seeker::Restore ? ?) 
=>
    (printout t "--------Remove old RestoreRecord " crlf)
    (retract ?s)
)

(defrule SeekerCleanupSaveRecord
	(T_ControlModelExecution init)    
    ?s <- (Seeker::Save ? ?) 
=>
    (printout t "--------Remove old SaveRecord " crlf)
    (retract ?s)
)

(defrule SeekerCleanupSearchDurationRecord
	(T_ControlModelExecution init)    
    ?sd <- (Seeker::SearchDuration ? ?) 
=>
    (printout t "--------Remove old SearchDuration " crlf)
    (retract ?sd)
)

(defrule RemoveOldUIRefreshViews
    (T_ControlModelExecution init)
    ?ui <- (Seeker::UI_RefreshViews)
=>
    (printout t "--------Remove old UI_RefreshViews " crlf)
    (retract ?ui)
)

 /******************************************************************************
 * create create create create create create create create create create create 
 *******************************************************************************/
(defrule SaveCurrentArchitecture
	(declare (salience 100))
	(T_ControlModelExecution create)    
=>
    (printout t "--------SaveCurrentArchitecture create " crlf)
    
	; ensure data consistency 
;	(assert (MAIN::T_CheckConsistency))
;	(focus ConsistencyCheck)
    
    ; Save current architecture in DB
    (assert (Seeker::Delete (DeleteFactSet (str-cat "Architecture" ?*ArchNode*)) SaveCurrentArchitectureForCreate))	; make sure we delete the current architecture in DB if exists. Architecture1 is the root
	(assert (Seeker::Save (SaveDependentFactSet "Design" (str-cat "Architecture" ?*ArchNode*) "temp" ?*projectfile*) SaveCurrentArchitectureForCreate))    
)
 
 
;
; Activate all the reasoning frameworks to create, evaluate a new current architecture
; and to suggest tactics on how to make it better
;
(defrule CreateNewCurrentArchitectureByApplyingTactics
	(declare (salience 20))
	(T_ControlModelExecution create)   
    (exists (MAIN::Scenarios (quality ?qa&~nil)))
    (exists (MAIN::AskQuestion (answerAvailable true) (log nil)))
    (Configuration::ActiveReasoningFrameworks ?ev ? Tactical)
=>
;    (__Log CreateNewCurrentArchitecture (format nil "================ New Seeker Run ================"))
;    (__Log CreateNewCurrentArchitecture (format nil "(%s) Apply tactic from the User" ?ev))
    (printout t "------------CreateNewCurrentArchitectureByApplyingTactics: " ?ev crlf)    

	; execute ApplyTactics
	(assert (Seeker::RFCommand (command ApplyTactics)))
	(focus ?ev)	    	
)

;(defrule CreateNewCurrentArchitectureOtherInternalRFs
;	(declare (salience 10))
;	(T_ControlModelExecution create)   
;    (exists (MAIN::Scenarios (quality ?qa&~nil)))
;    (Configuration::ActiveReasoningFrameworks ?ev Internal Tactical)
;;    (not (exists (MAIN::AskQuestion (answerAvailable true) (log nil))))
;=>
;    (__Log CreateNewCurrentArchitectureOtherInternalRFs (format nil "(%s) Analyze and Suggest Tactics" ?ev))
;    (assert (Seeker::RFCommand (command AnalyzeAndSuggest)))
;    (printout t "------------CreateNewCurrentArchitectureOtherInternalRFs: " ?ev crlf)    
;    (assert (MAIN::T_CheckConsistency)) ; check consistency of the data first before running the reasoning frameworks
;	 (focus ?ev ConsistencyCheck)
;)

(defrule CreateNewCurrentArchitectureByAnalyzingAndSuggesting
	(declare (salience 10))
	(T_ControlModelExecution create)   
    (exists (MAIN::Scenarios (quality ?qa&~nil)))
    (Configuration::ActiveReasoningFrameworks ?ev External Tactical)
=>
    (__Log CreateNewCurrentArchitectureOtherExternalRFs (format nil "(%s) Analyze and Suggest Tactics" ?ev))
    (printout t "------------CreateNewCurrentArchitectureByAnalyzingAndSuggesting: " ?ev crlf)    

	; execute AnalyzeAndSuggest
    (assert (Seeker::RFCommand (command AnalyzeAndSuggest)))
	(focus ?ev)	    
)

(defrule SeekerCleanupCreate1
    ?cm <- (Seeker::RFCommand (command ApplyTactics)) 
=>
    (retract ?cm)
)

(defrule SeekerCleanupCreate2
    ?cm <- (Seeker::RFCommand (command AnalyzeAndSuggest)) 
=>
    (retract ?cm)
)
        
 /******************************************************************************
 * save save save save save save save save save save save save save save save  
 *******************************************************************************/
;
; Checks if any reasoning framework suggested tactics. If so, we have at least one
; unsatisfied scenario and we have to go through the search procedure
; First step is saving the current architecture, then loop through the suggested tactics
;

(defrule tmpFixInfinity
    (declare (salience 10))
	(T_ControlModelExecution save)   
    ?ar <- (MAIN::P_AnalysisResult (value ?val&:(> ?val 999999999.0)))
=>
    (modify ?ar (value 999999999.0))
)

(defrule SaveCurrentArchitectureBeforeApplyFirstSuggestedTactic
    (T_ControlModelExecution save)
;    (exists (Seeker::TryTacticResult))
	(not (CurrentArchitectureName ?))
=>	
    ; Save current architecture in DB
	; to make sure that the database reflects exactly the factbase
;    (assert (Seeker::Delete (DeleteFactSet (str-cat "Architecture" ?*ArchNode*)) BeforeApplyFirstSuggestedTactic))	; make sure we delete the current architecture in DB if exists. Architecture1 is the root
;	(assert (Seeker::Save (SaveDependentFactSet "Design" (str-cat "Architecture" ?*ArchNode*) "temp" ?*projectfile*) BeforeApplyFirstSuggestedTactic))    

	; set current architecture name
	(assert (CurrentArchitectureName (str-cat "Architecture" ?*ArchNode*)))
	
	; increase ArchENode
	(bind ?*ArchNode* (+ ?*ArchNode* 1))
	(focus SeekerSuggestScope)
)

;(defrule AddOldValueToScenarioResult
;    (declare (salience 10))
;    (T_ControlModelExecution save)
;    ?ar <- (MAIN::P_AnalysisResult (owner ?sc))
;	?ov <- (MAIN::OldResult ?sc ?val)
;=>
;	(modify ?ar (oldValue ?val))
;	(retract ?ov)
;)

 /******************************************************************************
 * candidates candidates candidates candidates candidates candidates candidates   
 *******************************************************************************/
;
; Loops through all the suggested tactics to get the opinions of the other
; reasoning frameworks
;
(defrule CreateNewCandidateArchitecture
	(declare (salience -10))
	(DoDeepSearch (levels ?l))
	(T_ControlModelExecution candidates)    
	(Seeker::TryTacticResult (tacticId ?id) (scenario ?sc) (reasoningFramework ?rf) (tactic ?ta) (tacticDescription ?td))
	(CurrentArchitectureName ?an)
=>
    (__Log CreateNewCandidateArchitecture (format nil "(%s) New Candidate Architecture using (%s)" ?rf ?ta))
    	
	; execute ApplySuggestedTactic
;	(bind ?id (gensym*)) ; generate a tactic id ; generated by an external RF
    (assert (Seeker::RFCommand (command ApplySuggestedTactic) (parameter1 ?id)))
	(assert (Seeker::ApplySuggestedTactic (tacticId ?id) (scenario ?sc) (reasoningFramework ?rf) (tactic ?ta) (tacticDescription ?td) (haveResults FALSE)))		
    (printout t "------------CreateNewCandidateArchitecture!!!!"  ?rf " " ?id " " ?sc crlf)

	(focus SeekerEvaluateCandidates)	
)
;
; Loops through all the suggested tactics to get the opinions of the other
; reasoning frameworks
;
(defrule CheckConsequencesOfTactics
	(DoDeepSearch (levels ?l))
	(T_ControlModelExecution candidates)    
    ?cm <- (Seeker::RFCommand (command ApplySuggestedTactic))
=>
    (retract ?cm)
        	
    ; Save candidate architecture in DB
	; to make sure that the database reflects exactly the factbase
;    (printout t "------------CheckConsequencesOfTactics & SaveCandidatesinDatabase " crlf)    
;    (assert (Seeker::Delete (DeleteFactSet (str-cat "Architecture" ?*ArchNode*)) AfterApplySuggestedTactic))	; make sure we delete the current architecture in DB if exists. Architecture1 is the root
;    (assert (Seeker::Save (SaveDependentFactSet "Design" (str-cat "Architecture" ?*ArchNode*) "temp" ?*projectfile*) AfterApplySuggestedTactic))    	        
        
	; execute Analyze
    (assert (Seeker::RFCommand (command Analyze))) 
	(focus SeekerEvaluateCandidates)
)
(defrule SeekerCleanupCandidates
    ?cm <- (Seeker::RFCommand (command Analyze))
    ?ap <- (Seeker::ApplySuggestedTactic (haveResults FALSE))
    
=>
    (modify ?ap (haveResults TRUE))
    (retract ?cm)
)
 /******************************************************************************
 * assess assess assess assess assess assess assess assess assess assess assess      
 *******************************************************************************/
            
;
; Assess all the results of all the suggested tactics to propose the next step
;
(defrule AssessCandidateArchitectures
	(DoDeepSearch (levels ?l))
	(T_ControlModelExecution assess)    
	(exists (TryTacticResult))
	(forall (TryTacticResult (scenario ?sc) (reasoningFramework ?rf) (tactic ?ta))
		    (exists (EvaluationResult (tacticScenario ?sc) (reasoningFramework ?rf) (tactic ?ta) (relevance nil))))
	(CurrentArchitectureName ?an)
=>
    (focus SeekerAssessCandidates)
)
 /******************************************************************************
 * next next next next next next next next next next next next next      
 *******************************************************************************/
;
; Restore the current architecture
;
(defrule RemoveCurrentArchitectureNameForNext
	(declare (salience 10))
    (T_ControlModelExecution next)  
    (Seeker::SearchStep ?step)
    (DoDeepSearch (levels ?l&:(< ?step ?l)))
	?fan <- (CurrentArchitectureName ?an)
=>
	(retract ?fan)
)
(defrule CreateNewCurrentArchitecture
	(declare (no-loop TRUE))
    (T_ControlModelExecution next)
    ?ss <- (Seeker::SearchStep ?step)
    (DoDeepSearch (levels ?l&:(< ?step ?l)))
    (Seeker::EvaluationResult (reasoningFramework ?rf) (scenario ?sc) (tactic ?ta) (tacticId ?id) (relevance 1))
    ?st <- (Seeker::ApplySuggestedTactic (tacticId ?id))
=>
    (__Log CreateNewRootArchitecture (format nil "(%s) New Root Architecture using (%s)" ?rf ?ta))

    ; create the next search cycle
    (retract ?ss)    
    (assert (Seeker::SearchStep (+ ?step 1)))
    
    ; execute ApplySuggestedTactic to create a new root architecture for next search cycle
	(modify ?st (reasoningFramework ?rf))
	(assert (Seeker::RFCommand (command ApplySuggestedTactic) (parameter1 ?id)))
	(focus (sym-cat ?rf ReasoningFrameworks))
)
(defrule StartNewInnerSearchCycle
	?cf <- (T_ControlModelExecution next)
    ?rc <- (Seeker::RFCommand (command ApplySuggestedTactic))
=>
	(retract ?cf ?rc)
    (assert (T_ControlModelExecution create))
)
 /******************************************************************************
 * user user user user user user user user user user user user user user user user     
 * Note that the following rules do not support multi-level of search because
 * it does nothing with managing a set of series of ApplySuggestdTactic 
 *******************************************************************************/
;
; Restore the root architecture
;
(defrule RestoreRootArchitecture
	(declare (salience 10))
    (T_ControlModelExecution user)    
	?fan <- (CurrentArchitectureName ?an)
=>
    (__Log RestoreCurrentArchitecture (format nil "Restored Architecture (Architecture1)"))
    
	; remove current architecture name
	; make ArchNode point at the root
	; generate question group id
    (retract ?fan)
	(bind ?*ArchNode* 1)
	(bind ?*QuestionGroup* (gensym*))
	
    ; restore the root architecture from DB
	(assert (Seeker::Restore (RestoreFactSet "Design" "Architecture1") RestoreCurrentArchitectureForUser))
	
	; ensure data consistency 
	(assert (MAIN::T_CheckConsistency))
	(focus ConsistencyCheck)
	
	; save the root architecture in DB again
	; to make sure that the database reflects exactly the factbase
	(assert (Seeker::Delete (DeleteFactSet "Architecture1") RestoreCurrentArchitectureForUser))	
	(assert (Seeker::Save (SaveDependentFactSet "Design" "Architecture1" "temp" ?*projectfile*) RestoreCurrentArchitectureForUser))
)
    
(defrule MakeUserQuestionFromTactics
	(T_ControlModelExecution user)    
;	?er <- (Seeker::EvaluationResult (index ?ind) (scenario ?sc) (tacticScenario ?sc) (relevance ?rel) (tacticId ?id) (change ?val))
;	?ap <- (Seeker::ApplySuggestedTactic (tacticId ?id) (scenario ?sc) (reasoningFramework ?rf) (tactic ?ta) (at $?at) (parameters $?pa))

	?er <- (Seeker::EvaluationResult (index ?ind) (scenario ?sc) (tacticScenario ?sc) (relevance ?rel) (tacticId ?id))
	?ap <- (Seeker::ApplySuggestedTactic (tacticId ?id) (scenario ?sc) (reasoningFramework ?rf) (tactic ?ta))
=>
    (__Log MakeUserQuestionFromTactics (format nil "(%s) Selected Tactic for User (%s)" ?rf ?ta))
    
    (printout t "------------MakeUserQuestionFromTactics Fired"  crlf)   
;    (printout t ?er  crlf)   
;    (printout t ?ap  crlf)   
    
    (assert (Seeker::RFCommand (command DescribeTactic)))
	(assert (Seeker::DescribeTacticToUser (index ?ind) (relevance ?rel) (scenario ?sc) (reasoningFramework ?rf) (tacticId ?id) (tactic ?ta) 
            (questionGroup ?*QuestionGroup*)))
	(retract ?ap)
	(focus Logger)
)
;
; This rule activates when the seeker ran out of tactics
;
(defrule MakeOtherUserQuestions
	(T_ControlModelExecution user)    
    (exists (MAIN::Scenarios (quality ?qa&~nil)))
    (not (Seeker::EvaluationResult (relevance ?)))
=>
    (__Log MakeUserQuestionFromTactics (format nil "No Tactics anymore for User"))
    (printout t "------------MakeOtherUserQuestions Fired"  crlf)   
    (assert (Seeker::RFCommand (command DescribeTactic)))
    (focus Logger)
)
(defrule ActivateRFsForQuestions
	(declare (salience -10))
	(T_ControlModelExecution user)    
	(Seeker::RFCommand (command DescribeTactic))
    (Configuration::ActiveReasoningFrameworks ?ev ? Tactical)
=>
    (__Log ActivateRFsForQuestions (format nil "(%s) Activate for questions" ?ev))
	(focus ?ev)
)

;
; Halt until all the external RFs finish their analysis if they're running
;
(defrule HaltIfExtneralRF
    (declare (salience -20))
	(T_ControlModelExecution user)    
    (Seeker::RFCommand (command DescribeTactic))
    (exists (Seeker::ExternalJob (command DescribeTactic) (reasoningFramework ?)))
=>
	(halt)
	(printout t "------------Halted after requesting 'DescribeTactic' to external RFs!!!!"  crlf)
)

 /******************************************************************************
 * cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup    
 *******************************************************************************/
(defrule RemoveDescriptions
	(T_ControlModelExecution cleanup)    
	?cf <- (Seeker::DescribeTacticToUser)
=>
    (__Log RemoveDescriptions (format nil "================ End Seeker Run ================"))
	(retract ?cf)
)
(defrule RemoveSuggestions
	(T_ControlModelExecution cleanup)    
	?cf <- (Seeker::ApplySuggestedTactic)
=>
	(retract ?cf)
)
(defrule RemoveTryTactics
	(T_ControlModelExecution cleanup)    
	?cf <- (Seeker::TryTacticResult)
=>
	(retract ?cf)
)
(defrule RemoveRFcommands
	(T_ControlModelExecution cleanup)    
	?cf <- (Seeker::RFCommand)
=>
	(retract ?cf)
)
(defrule CalculateSearchDuration
	(T_ControlModelExecution cleanup)    
    ?st <- (Seeker::SearchStartTime ?stime)
	?ss <- (Seeker::SearchStep ?step)
=>
	(assert (Seeker::SearchDuration (- (time) ?stime) (gensym*)))
	(retract ?st)
	(retract ?ss)
)

(defrule RefreshArchEUI
	(T_ControlModelExecution refreshUI)    
=>
    (printout t "--------Do UI_RefreshViews " crlf)
    (assert (Seeker::UI_RefreshViews))
)

(defquery GetRunningRFCommands
    (declare (variables ?c))
    (Seeker::ExternalJob (command ?c))
)

