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

;
; Here is the startup stuff (NEW VERSION AFTER RESTRUCTURING CORE ARTIFACTS)
;

;
; Load definitions
;
;(clear)

(batch (str-cat ?*dir* "MAIN.clp"))
(batch (str-cat ?*dir* "Logger.clp"))
(batch (str-cat ?*dir* "Configuration.clp"))
(batch (str-cat ?*dir* "Design.clp"))
(batch (str-cat ?*dir* "Planner.clp"))
(batch (str-cat ?*dir* "MySQL.clp"))
(batch (str-cat ?*dir* "Seeker.clp"))
;;(batch (str-cat ?*dir* "ModifiabilityLearn.clp"))
;(batch (str-cat ?*dir* "PerformanceReasoningFrameworks.clp"))
;(batch (str-cat ?*dir* "RMAModel.clp"))
;(batch (str-cat ?*dir* "ApplyRMATactics.clp"))
;;(batch (str-cat ?*dir* "ModifiabilityReasoningFrameworks.clp"))
;(batch (str-cat ?*dir* "VariabilityReasoningFrameworks.clp"))
;;(batch (str-cat ?*dir* "CreateDesign.clp"))
;;;(batch (str-cat ?*dir* "Errorlogging.clp"))
;(batch (str-cat ?*dir* "Patterns.clp"))

; Added for supportig external RFs
(batch (str-cat ?*dir* "ExternalInteraction.clp"))


;
; Load configuration file
;
(deffacts SchedulerConfigurations
	(Configuration::SchedulePlanner "(focus MySQL AquireRequirements ManageResponsibilities Seeker)")
;	(Configuration::ActiveReasoningFrameworks ModifiabilityReasoningFrameworks Internal Tactical)
;    (Configuration::ActiveReasoningFrameworks PerformanceReasoningFrameworks Internal Tactical)
;    (Configuration::ActiveReasoningFrameworks VariabilityReasoningFrameworks Internal Tactical)
;	(Configuration::ScheduleReasoningFrameworks "(focus ModifiabilityReasoningFrameworks VariabilityReasoningFrameworks)")
)
; 
;Load the the database interface
;
(load-function edu.cmu.sei.arche.core.RestoreFactSet)
(load-function edu.cmu.sei.arche.core.SaveFactSet)
(load-function edu.cmu.sei.arche.core.SaveDependentFactSet)
(load-function edu.cmu.sei.arche.core.DeleteFactSet)
(load-function edu.cmu.sei.arche.core.DeleteFactSetFromMemory)
(load-function edu.cmu.sei.arche.core.ExportFactSetToSQL)
(load-function edu.cmu.sei.arche.core.UpdateFactSet)
;;(load-function edu.cmu.sei.arche.core.MOD_Tactic_Localize)
;;(load-function edu.cmu.sei.arche.core.MOD_Tactic_Encapsulate)
;;(load-function edu.cmu.sei.arche.core.MOD_Tactic_Split_Responsibility)
(load-function edu.cmu.sei.arche.core.ModuleOption)
(load-function edu.cmu.sei.arche.core.RemoveOrphanFacts)
;;(load-function edu.cmu.sei.arche.core.MOD_CreateModuleDependencies)
;;(load-function edu.cmu.sei.arche.core.MOD_CreateLeafDependencies)
;;(load-function edu.cmu.sei.arche.core.ResponsibilitiesByID)

;;(load-function edu.cmu.sei.arche.core.SameDecomposition)
;;(load-function edu.cmu.sei.arche.core.SameDecompositionPath)
; 
;Load the the external reasoning framework interface
;
(load-function edu.cmu.sei.arche.core.E_Connect)
(load-function edu.cmu.sei.arche.core.E_Disconnect)
(load-function edu.cmu.sei.arche.core.E_Cancel)
(load-function edu.cmu.sei.arche.core.E_FindExternalRFs)
(load-function edu.cmu.sei.arche.core.E_AutoDetect)
(load-function edu.cmu.sei.arche.core.E_ApplyTactics)
(load-function edu.cmu.sei.arche.core.E_AnalyzeAndSuggest)
(load-function edu.cmu.sei.arche.core.E_ApplySuggestedTactic)
(load-function edu.cmu.sei.arche.core.E_Analyze)
(load-function edu.cmu.sei.arche.core.E_DescribeTactic)

; 
;Load the aquire module
;
;;(load-function edu.cmu.sei.arche.core.MOD_CalculateModelJessFunction)	; Load the java function for modifiability model calculation
;;(load-function edu.cmu.sei.arche.core.PER_CalculateModelJessFunction)	; Load the java function for modifiability model calculation
;
; Load infrastructure
;
(batch (str-cat ?*dir* "Rules/Planner-rules.clp"))
(batch (str-cat ?*dir* "Rules/DetermineQuality-rules.clp"))
(batch (str-cat ?*dir* "Rules/AquireRequirements-rules.clp"))
(batch (str-cat ?*dir* "Rules/ManageResponsibilities-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerControlSearch-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerSuggestScope-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerEvaluateCandidates-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerAssessCandidates-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerAssessMinMax-rules.clp"))
;(batch (str-cat ?*dir* "Rules/ReasoningFrameworks-rules.clp"))
;(batch (str-cat ?*dir* "Rules/PerformanceReasoningFrameworks-rules.clp"))
;;(batch (str-cat ?*dir* "Rules/ModifiabilityReasoningFrameworks-rules.clp"))
(batch (str-cat ?*dir* "Rules/SeekerRFDependencies-rules.clp"))
(batch (str-cat ?*dir* "Rules/ConsistencyCheck-rules.clp"))
;(batch (str-cat ?*dir* "Rules/CreateDesign-rules.clp"))
;(batch (str-cat ?*dir* "Rules/Patterns-rules.clp"))
(batch (str-cat ?*dir* "Rules/Logger-rules.clp"))

(batch (str-cat ?*dir* "Rules/ExternalInteraction-rules.clp"))


;
; Load test cases if needed
;
;(batch (str-cat ?*dir* "Test.clp"))
;(batch (str-cat ?*dir* "Rules/TestDesign.clp"))

