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

; Dependencies to  MAIN.clp,Planner.clp
(require MAIN ../MAIN.clp)
(require PerformanceReasoningFrameworks ../PerformanceReasoningFrameworks.clp)
(require Planner ../Planner.clp)
(require Seeker ../Seeker.clp)
(require Configuration ../Configuration.clp)
(set-current-module PerformanceReasoningFrameworks)
(ModuleOption Debug)
(ModuleOption Sequence switch cleanup)
;(ModuleOption Sequence consistencyCheck execute)
;(defrule CheckForRMAModel
    ;(Planner::T_MakeAnotherRound)
    ;(exists (MAIN::Scenarios (quality Performance) (measureType HardDeadline) (stimulusType ?st&periodic|sporadic)))
;   ;(exists (Seeker::ApplySuggestedTactic))
;=>
    ;(focus RMAModel)
;)

(defrule SetTheScenariosOfInterest
    (T_ControlModelExecution switch)
    (not (PerformanceReasoningFrameworks::ScenariosOfInterest (quality Performance) (measureType HardDeadline)))
=>
    (assert (PerformanceReasoningFrameworks::ScenariosOfInterest (quality Performance) (measureType HardDeadline)))
    ;(halt)
)
;============================================================
; Start of rule set CheckWhatNeedsToBeDone
;
;
; Do everything if not ApplySuggestedTactic and not ArchitectureEvaluationOnly
;


;(defrule CheckWhatNeedsToBeDoneEverything
    ;(T_ControlModelExecution switch)
    ;(not (or (Seeker::ApplySuggestedTactic (reasoningFramework Performance))
            ; (Seeker::DescribeTacticToUser (reasoningFramework Performance))))
;=>
;    (printout t "CheckWhatNeedsToBeDoneEverything Fired"  crlf)
    ;;(focus ModifiabilityLearn InitialModifiabilityDesign ApplyModifiabilityTactics ModifiabilityAnalysis SuggestModifiabilityTactics)
    ;(assert (SaveModel))
    ;(focus InitialRMADesign ApplyRMATactics RMAAnalysis PerformanceConsistency SuggestRMATactics)
    ;;(focus InitialRMADesign)
    ;(halt)
;)
;
; Apply and evaluate if not ApplySuggestedTactic
;


(defrule CheckWhatNeedsToBeDoneApply
    (T_ControlModelExecution switch)
    (Seeker::RFCommand (command ApplyTactics))
=>
     ;(printout t "Performance-CheckWhatNeedsToBeDoneApply Fired"  crlf)
     ;(focus InitialRMADesign ApplyRMATactics RMAAnalysis)
    (focus InitialRMADesign ApplyRMATactics)
     ;(halt)
)

(defrule CheckWhatNeedsToBeDoneAnalyzeSuggest
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command AnalyzeAndSuggest))
=>
    
    ;(printout t "Performance-CheckWhatNeedsToBeDoneAnalyzeSuggest Fired"  crlf)
    (assert (SaveModel))
    (focus InitialRMADesign RMAAnalysis SuggestRMATactics)
    ;(halt)
)

;
; Apply and evaluate if not ApplySuggestedTactic
;
(defrule CheckWhatNeedsToBeDoneApplySuggested
    (T_ControlModelExecution switch)
    (Seeker::ApplySuggestedTactic (reasoningFramework Performance))
	(Seeker::RFCommand (command ApplySuggestedTactic))
=>
    ;(printout t "Performance-CheckWhatNeedsToBeDoneApplySuggested Fired"  crlf)
    (focus InitialRMADesign ApplyRMATactics)
    ;(halt)
)

(defrule CheckWhatNeedsToBeDoneAnalyze
    (T_ControlModelExecution switch)
	(Seeker::RFCommand (command Analyze))
=>
    ;(printout t "Performance-CheckWhatNeedsToBeDoneAnalyze Fired"  crlf)
    (focus InitialRMADesign RMAAnalysis)
    ;(halt)
)
;
; Only evaluate if ArchitectureEvaluationOnly
;

(defrule CheckWhatNeedsToBeDoneDescriptionTactic
    (T_ControlModelExecution switch)
    (Seeker::DescribeTacticToUser (scenario ?sc) (reasoningFramework Performance))
	(Seeker::RFCommand (command DescribeTactic))
=>
 
    ;(printout t "Performance-CheckWhatNeedsToBeDoneDescriptionTactic Fired"  crlf)
 	(assert (RestoreModel))
    (focus DescribeRMATactics)
    ;(halt)
)

(defrule CheckWhatNeedsToBeDoneDescription
     (T_ControlModelExecution switch)
     (not (Seeker::DescribeTacticToUser (scenario ?sc) (reasoningFramework Performance)))
	 (Seeker::RFCommand (command DescribeTactic))
=>
     ;(printout t "Performance-CheckWhatNeedsToBeDoneDescription Fired"  crlf)
     (assert (RestoreModel))
     (focus DescribeRMATactics)
     ;(halt)
)


;
; save the model as it is after applying user provided tactics
;

(defrule SaveCurrentModel
    (T_ControlModelExecution cleanup)
    ?c <- (SaveModel)
=>
    (DeleteFactSet "RMAModel1")
	;(printout t " RMAModel1 should be saved" crlf)
    (SaveFactSet "RMAModel" "RMAModel1" "temp") 
    (retract ?c)
)
;
; restore the model as it is after applying user provided tactics after search
;



(defrule RestoreCurrentModel
    (T_ControlModelExecution cleanup)
    ?c <- (RestoreModel)
=>
    ;(printout t " RMAModel1 should be restored" crlf)
    (RestoreFactSet "RMAModel" "RMAModel1")
    (retract ?c)
)
;
; Translate the evaluation results into analysis results it we just created the
; new current architecture
;
;
; Translate the evaluation results into analysis results it we just created the
; new current architecture
;
;
; Translate the evaluation results into analysis results it we just created the
; new current architecture
;

(defrule MakeAnalysisResults
    (T_ControlModelExecution cleanup)
    ?er <- (Seeker::EvaluationResult (reasoningFramework nil) (tactic nil) (scenario ?sc) (result ?res) (utility ?ut))
    (not (MAIN::P_AnalysisResult (owner ?sc)))
=>
   (bind ?ar (assert (MAIN::P_AnalysisResult (owner ?sc) (value ?res) (source ArchE) (utility ?ut) (quality Performance) (reasoningFramework Performance))))
     (if (eq ?res Infinity) then
          (modify ?ar (isSatisfied false))
      else
          (if (> ?res (fact-slot-value ?sc measureValue)) then
             (modify ?ar (value ?res) (isSatisfied false) )
           else
              (modify ?ar (value ?res) (isSatisfied true) )
          )
      )
    
	(retract ?er)
)


