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

(require MAIN MAIN.clp)
(require ModifiabilityReasoningFrameworks ModifiabilityReasoningFrameworks.clp)
(require Planner Planner.clp)
(require Seeker Seeker.clp)
(require Configuration Configuration.clp)
(set-current-module ModifiabilityReasoningFrameworks)
(ModuleOption Debug)
(ModuleOption Sequence switch cleanup)
 /******************************************************************************
 * switch switch switch switch switch switch switch switch switch switch switch  
 *******************************************************************************/
(defrule SetTheScenariosOfInterest
    (T_ControlModelExecution switch)
    (not (ModifiabilityReasoningFrameworks::ScenariosOfInterest (quality Modifiability) (measureType CostConstraint)))
=>
    (assert (ModifiabilityReasoningFrameworks::ScenariosOfInterest (quality Modifiability) (measureType CostConstraint)))
)
;============================================================
; Start of rule set CheckWhatNeedsToBeDone
;
;
; Do everything if not ApplySuggestedTactic and not ArchitectureEvaluationOnly
;
(defrule CheckWhatNeedsToBeDoneApply
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command ApplyTactics))
=>
    ;(printout t "Modifiability-CheckWhatNeedsToBeDoneApply Fired"  crlf)   
    (assert (ModifiabilityReasoningFrameworks::NewModuleDependencies))	; Make sure we have all the dependencies
    (focus ModifiabilityLearn InitialModifiabilityDesign ApplyModifiabilityTactics)
)
(defrule CheckWhatNeedsToBeDoneAnalyzeSuggest
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command AnalyzeAndSuggest))
=>
    (printout t "Modifiability-CheckWhatNeedsToBeDoneAnalyzeSuggest Fired"  crlf)
    (assert (ModifiabilityReasoningFrameworks::NewModuleDependencies))	; Make sure we have all the dependencies    
	(assert (SaveModel))
    (focus ModifiabilityLearn InitialModifiabilityDesign ModifiabilityAnalysis SuggestModifiabilityTactics)
)
;
; Apply and evaluate if not ApplySuggestedTactic
;
(defrule CheckWhatNeedsToBeDoneApplySuggested
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command ApplySuggestedTactic) (parameter1 ?id))
    (Seeker::ApplySuggestedTactic (reasoningFramework Modifiability) (tacticId ?id))
=>
    ;(printout t "Modifiability-CheckWhatNeedsToBeDoneApplySuggested Fired"  crlf)
    (focus InitialModifiabilityDesign ApplyModifiabilityTactics)
)
(defrule CheckWhatNeedsToBeDoneAnalyze
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command Analyze))
=>
    ;(printout t "Modifiability-CheckWhatNeedsToBeDoneAnalyze Fired"  crlf)
    (focus InitialModifiabilityDesign ModifiabilityAnalysis)
)
;
; Only evaluate if ArchitectureEvaluationOnly
;
(defrule CheckWhatNeedsToBeDoneDescriptionTactic
    (T_ControlModelExecution switch)
    (Seeker::DescribeTacticToUser (scenario ?sc) (reasoningFramework Modifiability))
	(Seeker::RFCommand (command DescribeTactic))
=>
    (printout t "Modifiability-CheckWhatNeedsToBeDoneDescriptionTactic Fired"  crlf)
    (assert (RestoreModel))
    (focus DescribeModifiabilityTactics)
)
(defrule CheckWhatNeedsToBeDoneDescription
    (T_ControlModelExecution switch)
    (not (Seeker::DescribeTacticToUser (scenario ?sc) (reasoningFramework Modifiability)))
	(Seeker::RFCommand (command DescribeTactic))
=>
    (printout t "Modifiability-CheckWhatNeedsToBeDoneDescription Fired"  crlf)
    (assert (RestoreModel))
    (focus DescribeModifiabilityTactics)
)
 /******************************************************************************
 * cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup cleanup  
 *******************************************************************************/
;
; save the model as it is after applying user provided tactics
;
(defrule SaveCurrentModel
    (T_ControlModelExecution cleanup)
    ?c <- (SaveModel)
=>
    (DeleteFactSet "UserModModel")
	(printout t " UserModModel should be saved" crlf)
    (SaveFactSet "ModifiabilityModel" "UserModModel" "temp")   
    (retract ?c)
)
;
; restore the model as it is after applying user provided tactics after search
;
(defrule RestoreCurrentModel
    (T_ControlModelExecution cleanup)
    ?c <- (RestoreModel)
=>
    (printout t " UserModModel should be restored" crlf)
    (RestoreFactSet "ModifiabilityModel" "UserModModel")
    (retract ?c)
)
;
; Translate the evaluation results into analysis results it we just created the
; new current architecture
;
(defrule MakeAnalysisResults
    (T_ControlModelExecution cleanup)
    ?er <- (Seeker::EvaluationResult (reasoningFramework nil) (tactic nil) (scenario ?sc) (result ?res) (utility ?ut))
    (not (MAIN::P_AnalysisResult (owner ?sc)))
=>
    (assert (MAIN::P_AnalysisResult (owner ?sc) (value ?res) (utility ?ut) (source ArchE) (quality Modifiability) (reasoningFramework ImpactAnalysis) (isSatisfied true)))
	(retract ?er)
)
        
 /******************************************************************************
 * independent rules---independent rules---independent rules---independent rules
 *******************************************************************************/

;
; Creates a new responsibility that represents the common service.
;
(defrule AbstractCommonServicesCreateCommonService
    ?cc <- (Planner::C_AbstractCommonServices (common nil) (name ?n))
=>
    (bind ?rc (assert (MAIN::Responsibilities (description ?n))))
    (modify ?cc (common ?rc))
)
;
; Creates a new responsibility for every responsibility included in the "Abstract Common Services" command with a description containing the description of the old responsibility and the description of the common service.
; It also creates a containment relationship between the old and the new responsibility as well as between the old responsibility and the newly created common service.
; For every new responsibility a question is issued to the user to give the opportunity the change the description of the newly created responsibilities.
; The rule also creates an internal control fact for every changed responsibility to enable follow-up work, such as adjustment of the sequence relation between the responsibilities.
;
(defrule AbstractCommonServicesSplitResponsibilities
    ?cs <- (Planner::C_AbstractCommonServices (name ?n) (common ?c) (responsibilities $? ?r $?)) ;get the command parameters
    (not (MAIN::ResponsibilityToResponsibilityRelation (parent ?r)))     ;Only leaf nodes can be split
    
=>
    (bind ?nr (ExtractResponsibility ?r ?c)) ; extract the common service responsibility from the affected once
    (bind $?sc (GetParticipatingScenarios ?r))
    (foreach ?s ?sc (assert (CheckForSequenceChanges ;CLIPS requires progn$ here
                                (changedResponsibility ?nr)
                                (additionalResponsibility ?c)
                                (scenario ?s))))
    (retract ?cs)   ;delete the command
)
(defrule AssignIdToRelations
"Checks if there is a ResponsibilityToResponsibility relation with no ID. If found, assigns an ID"
    ?rr <- (MAIN::ResponsibilityToResponsibilityRelation (id nil))
=>
    (modify ?rr (id (gensym*)))
)

(defrule UserAssignedProbabilities
"Takes the probability given by the user and assigns it to the dependency"
    ?q <- (MAIN::AskQuestion (questionId probabilityOfPropagation) (parent ?rr) (answerAvailable true) (answer ?nv) (log nil))
=>
    (assert (P_ProbabilityIncoming (owner ?rr) (value ?nv) (source User)))
    (assert (P_ProbabilityOutgoing (owner ?rr) (value ?nv) (source User)))
    (modify ?q (log true))
)
;
; End of rule set GenerateArcsForImpactAnalysis
;------------------------------------------------------------
