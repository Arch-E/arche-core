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

(defmodule AquireRequirements)
(require MAIN)
(require Planner)
(ModuleOption Debug)
(ModuleOption Events Exit)

;============================================================
; Start of rule set AquireFunctionalties
;
;
; User has new functional requirement available. Those requirments are read and make available to the system.
;
(defrule AquireFunctionaltiesWithParent
    ?fch <- (FunctionHasParent ?ch ?pa)
    ?fpa <- (MAIN::Function (id ?pa))
=>
    (assert (MAIN::FunctionRefinementRelation (parent ?fpa) (child ?ch) (source User)))
    (retract ?fch)
	(assert (Planner::C_RequirementsChanged))
)
(defrule AquireFunctionalitiesWithoutParent
"Get a functional requirement that has no reference to another functional requirement (top level requirement)"
    ?f <- (Planner::C_AddFunction (functionID ?id) (description ?d))
=>
    (bind ?index (find-from-end ?id "."))
    (bind ?ch (assert (MAIN::Function (id ?id) (description ?d))))
    (if (> ?index 1) then
        (assert (FunctionHasParent ?ch (sub-string 1 (- ?index 1) ?id))))
    (retract ?f)
	(assert (Planner::C_RequirementsChanged))
)
;
; End of rule set AquireFunctionalties
;------------------------------------------------------------

;============================================================
; Start of rule set TranslateFunctionsToResponsibilities
;
(defrule TranslateFuntionRelationToResponsibilityRelation
"Checks if there is a refinement relation between functions that is not yet translated into a refinement relation between responsibilities."
    (MAIN::FunctionRefinementRelation (parent ?pa) (child ?ch))
    (MAIN::TranslationRelation (parent ?pa) (child ?rep))
    (MAIN::TranslationRelation (parent ?ch) (child ?rec))
    (not (MAIN::ResponsibilityRefinementRelation (parent ?rep) (child ?rec)))
=>
    (assert (MAIN::ResponsibilityRefinementRelation (parent ?rep) (child ?rec) (source ArchE)))
	(assert (Planner::C_RequirementsChanged))
)
(defrule TranslateFunctionsToResponsibilities
"Checks if there is a top-level function with no relation to responsibilities. If yes, it creates the responsibility"
    ?fu <- (MAIN::Function (description ?d))
    (not (exists (MAIN::TranslationRelation (parent ?fu))))
    (not (exists (MAIN::P_Function_Responsibility_Created  (owner ?fu))))
=>
    (bind ?re (assert (MAIN::Responsibilities (name ?d) (description ?d) (source ArchE))))
    (assert (MAIN::TranslationRelation (parent ?fu) (parentType Functionality) (child ?re) (source ArchE)))
    (assert (MAIN::P_Function_Responsibility_Created (owner ?fu) (value true) (source ArchE)))
	(assert (Planner::C_RequirementsChanged))
)

(defrule CheckRespCreatedProperty
"Checks if there is a top-level function with no relation to responsibilities. If yes, it creates the responsibility"
    ?fu <- (MAIN::Function (description ?d))
    (MAIN::TranslationRelation (parent ?fu))
    (not (MAIN::P_Function_Responsibility_Created  (owner ?fu)))
=>
    (assert (MAIN::P_Function_Responsibility_Created (owner ?fu) (value true) (source ArchE)))
)

(defrule ChangeResponsibilityDescriptionFromFunction
	?fi <- (MAIN::Function (description ?de))
    ?re <- (MAIN::Responsibilities (source ArchE) (description ?rd&~?de))
    (MAIN::TranslationRelation (parent ?fi) (child ?re))
=>
	(modify ?re (description ?de))
	(assert (Planner::C_RequirementsChanged))
)
;
; End of rule set TranslateFunctionsToResponsibilities
;------------------------------------------------------------
;============================================================
; Start of rule set DeleteFunction
;
;
; This set of rules deletes a function and all possible relationships
;
(defrule DeleteFunction
"Removes the function from the fact base"
    (Planner::C_DeleteFunction (applyTo ?pf))
=>
    (bind ?trr (QueryFactbase "(MAIN::TranslationRelation (parent ?1))" ?pf))
    (foreach ?tr ?trr (retract ?tr))
    (retract ?pf)
	(assert (Planner::C_RequirementsChanged))
)
(defrule RemoveFunctionRefinement
"Removes possible refinement relation from and to this function from the fact base"
    (Planner::C_DeleteFunction (applyTo ?pf))
    (or
        ?fr <- (MAIN::FunctionRefinementRelation (parent ?pf))
        ?fr <- (MAIN::FunctionRefinementRelation (child ?pf)))
=>
    (retract ?fr)
	(assert (Planner::C_RequirementsChanged))
)
;
; End of rule set DeleteFunction
;------------------------------------------------------------
(defrule ChangeFunction
"Changes a function by removing the old function and inserting a new one"
    ?c <- (Planner::C_ChangeFunction (functionID ?id) (description ?de) (applyTo ?pf))
    ?f <- (MAIN::Function (id ?oid))
    (test (eq ?pf ?f))
=>
    (modify ?f (id ?id) (description ?de))
    (if (neq ?id ?oid) then
    	(bind ?frr (QueryFactbase "(MAIN::FunctionRefinementRelation (parent ?1))" ?f))
    	(bind ?frr (union$ ?frr (QueryFactbase "(MAIN::FunctionRefinementRelation (child ?1))" ?f)))
    	(foreach ?fr ?frr (retract ?fr))
        (bind ?index (find-from-end ?id "."))
        (if (> ?index 1) then
            (assert (FunctionHasParent ?f (sub-string 1 (- ?index 1) ?id)))))
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))
)
;============================================================
; Start of rule set AquireQualityScenarios
;
;
; Read the scenarios the user has available and make them available to ArchE
;
(defrule AquireQualityScenarios
    ?f <- (Planner::C_AddScenario (state final)
        (description ?de)
        (quality ?qu)
        (reasoningFramework ?rf)
        (stimulusText ?sx)
        (stimulusType ?st)
        (stimulusUnit ?su)
        (stimulusValue ?sv)
        (sourceText ?ox)
        (sourceType ?ot)
        (sourceUnit ?ou)
        (sourceValue ?ov)
        (artifactText ?ax)
        (artifactType ?at)
        (artifactUnit ?au)
        (artifactValue ?av)
        (environmentText ?ex)
        (environmentType ?et)
        (environmentUnit ?eu)
        (environmentValue ?ev)
        (responseText ?rx)
        (responseType ?rt)
        (responseUnit ?ru)
        (responseValue ?rv)
        (measureText ?mx)
        (measureType ?mt)
        (measureUnit ?mu)
        (measureValue ?mv))
=>
    (assert (MAIN::Scenarios
        (description ?de)
        (quality ?qu)
        (reasoningFramework ?rf)
        (stimulusText ?sx)
        (stimulusType ?st)
        (stimulusUnit ?su)
        (stimulusValue ?sv)
        (sourceText ?ox)
        (sourceType ?ot)
        (sourceUnit ?ou)
        (sourceValue ?ov)
        (artifactText ?ax)
        (artifactType ?at)
        (artifactUnit ?au)
        (artifactValue ?av)
        (environmentText ?ex)
        (environmentType ?et)
        (environmentUnit ?eu)
        (environmentValue ?ev)
        (responseText ?rx)
        (responseType ?rt)
        (responseUnit ?ru)
        (responseValue ?rv)
        (measureText ?mx)
        (measureType ?mt)
        (measureUnit ?mu)
        (measureValue ?mv)))
    (retract ?f)
	(assert (Planner::C_RequirementsChanged))
)
(defrule ModifyExistingScenario
"Changes the content of an existing scenario"
    ?f <- (Planner::C_ChangeScenario (state final) (applyTo ?sc)
        (description ?de)
        (quality ?qu)
        (reasoningFramework ?rf)
        (stimulusText ?sx)
        (stimulusType ?st)
        (stimulusUnit ?su)
        (stimulusValue ?sv)
        (sourceText ?ox)
        (sourceType ?ot)
        (sourceUnit ?ou)
        (sourceValue ?ov)
        (artifactText ?ax)
        (artifactType ?at)
        (artifactUnit ?au)
        (artifactValue ?av)
        (environmentText ?ex)
        (environmentType ?et)
        (environmentUnit ?eu)
        (environmentValue ?ev)
        (responseText ?rx)
        (responseType ?rt)
        (responseUnit ?ru)
        (responseValue ?rv)
        (measureText ?mx)
        (measureType ?mt)
        (measureUnit ?mu)
        (measureValue ?mv))
=>
    (modify ?sc
        (description ?de)
        (quality ?qu)
        (reasoningFramework ?rf)
        (stimulusText ?sx)
        (stimulusType ?st)
        (stimulusUnit ?su)
        (stimulusValue ?sv)
        (sourceText ?ox)
        (sourceType ?ot)
        (sourceUnit ?ou)
        (sourceValue ?ov)
        (artifactText ?ax)
        (artifactType ?at)
        (artifactUnit ?au)
        (artifactValue ?av)
        (environmentText ?ex)
        (environmentType ?et)
        (environmentUnit ?eu)
        (environmentValue ?ev)
        (responseText ?rx)
        (responseType ?rt)
        (responseUnit ?ru)
        (responseValue ?rv)
        (measureText ?mx)
        (measureType ?mt)
        (measureUnit ?mu)
        (measureValue ?mv))
    (retract ?f)
	(assert (Planner::C_RequirementsChanged))
)
(defrule DeleteExistingScenario
"Removes a scenario and the translation relations from the fact base"
    ?c <- (Planner::C_DeleteScenario (applyTo ?sc))
=>
    (bind ?trr (QueryFactbase "(MAIN::TranslationRelation (parent ?1))" ?sc))
    (foreach ?tr ?trr (retract ?tr))
    (retract ?sc)
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))    
)
;
; End of rule set AquireQualityScenarios
;------------------------------------------------------------
(defrule CheckIfQualityMightBeModifiability
    (or ?c <- (Planner::C_AddScenario (description ?de&~nil) (quality nil) (state ?s&~final))
        ?c <- (Planner::C_ChangeScenario (description ?de&~nil) (quality nil) (state ?s&~final)))
=>
    (bind ?pp (explode$ ?de))
    (bind ?parts (create$))
    (foreach ?p ?pp (bind ?parts (insert$ ?parts 1 (lowcase ?p))))
    (bind ?score 0)
    (if (or (bind ?pst (member$ "add" ?parts))
        (bind ?pst (member$ "delete" ?parts))
        (bind ?pst (member$ "change" ?parts))
        (bind ?pst (member$ "replace" ?parts))
        (bind ?pst (member$ "add" ?parts))
        (bind ?pst (member$ "remove" ?parts))) then (bind ?score (+ ?score 1)))
    (if (or (bind ?pva (member$ "effort" ?parts))
        (bind ?pva (member$ "cost" ?parts))) then (bind ?score (+ ?score 1)))
    (if (> ?score 1) then (modify ?c
        (quality Modifiability)
        (stimulusText (str-cat (nth$ ?pst ?parts) " " (nth$ (- ?pst 1) ?parts)))
        (measureText (nth$ ?pva ?parts))
        (measureType CostConstraint)))
)
;
; Every scenarion need to have an id
;
(defrule AssignIdToScenario
    ?sc <- (MAIN::Scenarios (id nil))
=>
    (modify ?sc (id (unique-number "gen")))
;    (modify ?sc (id (gensym*)))
)

;============================================================
; Start of rule set UserAssignedResponsibility
;
;
; User assigns a translation relation from a function or a scenario to a responsibility
;
(defrule UserAssignedResponsibilityToFunction
    ?m <- (Planner::C_AddTranslationRelation (parent ?s) (child ?r))
    (not (exists (MAIN::TranslationRelation (parent ?s) (child ?r))))
    ?fu <- (MAIN::Function)
    (test (eq ?s ?fu))
=>
    (assert (MAIN::TranslationRelation (parent ?s) (parentType Functionality) (child ?r) (source User)))
    (retract ?m)
	(assert (Planner::C_RequirementsChanged))
)
(defrule UserAssignedResponsibilityToScenario
    ?m <- (Planner::C_AddTranslationRelation (parent ?s) (child ?r))
    (not (exists (MAIN::TranslationRelation (parent ?s) (child ?r))))
    ?sc <- (MAIN::Scenarios)
    (test (eq ?s ?sc))
=>
    (assert (MAIN::TranslationRelation (parent ?s) (parentType Scenario) (child ?r) (source User)))
    (retract ?m)
	(assert (Planner::C_RequirementsChanged))
)
(defrule UserModifiesResponsibilityAssignment
    ?c <- (Planner::C_ChangeTranslationRelation (parent ?np) (child ?nc) (applyTo ?tr))
    ?r <- (MAIN::TranslationRelation (parent ?op) (child ?oc))
    (test (eq ?r ?tr))
=>
    (modify ?r (parent ?np) (child ?nc) (source User))
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))
)
(defrule UserDeletesResponsibilityAssignment
    ?c <- (Planner::C_DeleteTranslationRelation (applyTo ?tr))
    ?r <- (MAIN::TranslationRelation)
    (test (eq ?r ?tr))
=>
    (retract ?c ?r)
	(assert (Planner::C_RequirementsChanged))
)
;
; End of rule set UserAssignedResponsibility
;------------------------------------------------------------

(defrule ArchEDetermineQuality
    (OnExit)
    (MAIN::Scenarios (quality nil))
=>
    (focus DetermineQuality)
)
