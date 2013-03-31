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

(provide Planner)
(require MAIN)
(defmodule Planner)
; This template is depreciated. Use Seeker::TryTacticResult instead
(deftemplate TryModifiabilityTacticResult
    (slot scenario (type OBJECT))
    (slot index (type Integer)) ;preference; lower index is more preferable; 1 = best
    (slot tactic (type SYMBOL)) ;name of the tactic to use
    (multislot at) ;the positions in the model where to apply the tactic
    (multislot parameters) ;parameters required for this tactic
    (slot result (type FLOAT)) ;the result of the model evaluation function for this tactic
)
(deftemplate TryRMATacticResult
    (multislot scenarios)
    (slot index (type INTEGER)) ;preference; lower index is more preferable; 1 = best
    (slot tactic (type SYMBOL)) ;name of the tactic to use
    (multislot at) ;the positions in the model where to apply the tactic
    (multislot parameters) ;parameters required for this tactic
    (slot result (type FLOAT)) ;the result of the model evaluation function for this tactic
)
(deftemplate C_RemoveResponsibiltyFromScenario
    (slot ScenarioID (type OBJECT))
    (slot ResponsibilityID (type OBJECT))
)
;
; Creating this fact leads to assigning the given responsibility to the given scenario
;
(deftemplate C_AddTranslationRelation
    (slot parent (type OBJECT))
    (slot child (type OBJECT))
    (slot type (type STRING))
    (slot state (type STRING))
)
(deftemplate C_AddResponsibilityRelation
    (slot parent (type OBJECT))
    (slot child (type OBJECT))
    (slot type (type STRING))
    (slot state (type STRING))
)
(deftemplate C_DeleteResponsibilityRelation
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_DeleteResponsibility
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_ChangeTranslationRelation
    (slot parent (type OBJECT))
    (slot child (type OBJECT))
    (slot type (type STRING))
    (slot state (type STRING))
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_DeleteTranslationRelation
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_DeleteFunction
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_DeleteScenario
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_ChangeFunction
    (slot functionID (type STRING)) ;Identification of this function
    (slot description (type STRING)) ;Description of the function
    (slot state (type STRING))
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_ChangeScenario
    (slot description (type STRING))
    (slot quality (type SYMBOL))
	(slot reasoningFramework (type SYMBOL))
    (slot stimulusText (type STRING)) ;Name of the scenario
    (slot stimulusType (type SYMBOL))
    (slot stimulusUnit (type SYMBOL))
    (slot stimulusValue (type FLOAT))
    (slot sourceText (type STRING))
    (slot sourceType (type SYMBOL))
    (slot sourceUnit (type SYMBOL))
    (slot sourceValue (type FLOAT))
    (slot artifactText (type STRING))
    (slot artifactType (type SYMBOL))
    (slot artifactUnit (type SYMBOL))
    (slot artifactValue (type FLOAT))
    (slot environmentText (type STRING))
    (slot environmentType (type SYMBOL))
    (slot environmentUnit (type SYMBOL))
    (slot environmentValue (type FLOAT))
    (slot responseText (type STRING))
    (slot responseType (type SYMBOL))
    (slot responseUnit (type SYMBOL))
    (slot responseValue (type FLOAT))
    (slot measureText (type STRING))
    (slot measureType (type SYMBOL))
    (slot measureUnit (type SYMBOL))
    (slot measureValue (type FLOAT))
    (slot state (type STRING))
    (slot applyTo (type FLOAT)) ;The id of the fact to be deleted
)
(deftemplate C_AddScenario
    (slot description (type STRING))
    (slot quality (type SYMBOL))
	(slot reasoningFramework (type SYMBOL))
    (slot stimulusText (type STRING)) ;Name of the scenario
    (slot stimulusType (type SYMBOL))
    (slot stimulusUnit (type SYMBOL))
    (slot stimulusValue (type FLOAT))
    (slot sourceText (type STRING))
    (slot sourceType (type SYMBOL))
    (slot sourceUnit (type SYMBOL))
    (slot sourceValue (type FLOAT))
    (slot artifactText (type STRING))
    (slot artifactType (type SYMBOL))
    (slot artifactUnit (type SYMBOL))
    (slot artifactValue (type FLOAT))
    (slot environmentText (type STRING))
    (slot environmentType (type SYMBOL))
    (slot environmentUnit (type SYMBOL))
    (slot environmentValue (type FLOAT))
    (slot responseText (type STRING))
    (slot responseType (type SYMBOL))
    (slot responseUnit (type SYMBOL))
    (slot responseValue (type FLOAT))
    (slot measureText (type STRING))
    (slot measureType (type SYMBOL))
    (slot measureUnit (type SYMBOL))
    (slot measureValue (type FLOAT))
    (slot state (type STRING))
)
(deftemplate C_AddFunction
    (slot functionID (type STRING)) ;Identification of this function
    (slot description (type STRING)) ;Description of the function
    (slot state (type STRING))
)
(deftemplate C_AssignSequenceOfResponsibilitiesToScenario
    (slot scenario (type OBJECT)) ;The id of the scenario the responsibilities are assigned to
    (slot responsibilities (type OBJECT)) ;A sequence of responsibilities that have to be assigned to the scenario. The first responsibility comes before the second and so on.
)
;
; Triggers the use of the localization tactic and identifies the scneario whose effect should be localized.
;
(deftemplate C_ApplyLocalization
    (slot scenario (type OBJECT)) ;Identification of the modifiability scenario whose effect should be localized.
)
;
; This requests the set the number of processors to the specified value.
;
(deftemplate C_ChangeNumberOfProcessors
    (slot value (type INTEGER))
)
(deftemplate C_ChangeScheduler
    (slot value (type SYMBOL)) ;specificatiion of the scheduler wanted
)
;
; Triggers the tactic "abstract common services" with a list of (existing) responsibilities that all contain the common service.
; Only responsibilities that are not further decomposed can be used here
;
(deftemplate C_AbstractCommonServices
    (slot name (type STRING)) ;Name of the new common service
    (slot common (type OBJECT)) ;After the common service is created, this value contains the reference to the responsibility that is the common service. The value is empty before
    (multislot responsibilities) ;Identifiers of the responsibilities that contain the common service
)
;
; Asserting this fact triggers ArchE to make another round through the modules to execute potentially activated rules
;
(deftemplate T_MakeAnotherRound
)
;
; Retrieves the sequence relations for a specific scenario
;
(defquery GetSequenceRelations
    (declare (variables ?scenario))
    (MAIN::SequenceRelation (scenario ?scenario))
)
;
; Retrieves the scenario id's for the given responsibility
;
(defquery GetScenariosToResponsibility
    (declare (variables ?r))
    (or (MAIN::SequenceRelation (parent ?r))
         (MAIN::SequenceRelation (child ?r))) 
)
(defquery GetRelatedResponsibilities
    (declare (variables ?s))
    ?ri <- (MAIN::Responsibilities)
    (MAIN::TranslationRelation (parent ?s) (child ?ri))
)
;
; Get all the responsibilities that are NOT related to a given scenario
;
(defquery GetUnrelatedResponsibilities
    (declare (variables ?s))
    ?ri <- (MAIN::Responsibilities)
    (or (MAIN::TranslationRelation (parent ?x&:(<> ?x ?s)) (child ?ri))
        (not (MAIN::TranslationRelation (child ?ri))))
)
;
; Accepts a scenario identifier and delivers all the sequence relations that belong to this scenario as a multislot field. This is the Jess implementation
;
(deffunction GetSequenceOfResponsibilities (?scenario)
    (bind ?it (run-query GetSequenceRelation ?scenario))
    (bind $?sr (create$))
    (while (?it hasNext)
                (bind ?token (call ?it next))
                (bind ?fact (call ?token fact 1))
                (insert$ ?sr 1 ?fact)
    )
    (return ?sr)
)
;
; Retrieves the scenarios the given responsibility participates in.
; The ID's of the scenarios is returned as a multivalue field.
; This is the Jess implementation
;
(deffunction GetParticipatingScenarios (?r)
    (return (query-to-multislot (run-query GetScenariosToResponsibility ?r) scenario))
)
;
; Retrieves the responsibilities related to the given scenario
; The ID's of the responsibilities are returned as a multivalue field.
; This is the Jess implementation
;
(deffunction GetRelatedResponsibilities (?s)
    (return (query-to-multislot (run-query GetRelatedResponsibilities ?s) child))
)

/* TODO: Need to move to the Modifiability RF module
;
; Changes the name of remaining responsibility to reflect the extraction
; Issues a question to the user to rename the changed responsibility
; Creates containment relations from the previous responsibility to both, the changed resposnibility as well as the extracted responsibility
; Returns the fact identifier of the reduced responsibility
;
(deffunction ExtractResponsibility (?old ?extract)
    (bind ?split (duplicate ?old (Description (str-cat (fact-slot-value ?old Description) "-" (fact-slot-value ?extract Description)))))
    (assert (ContainmentRelation (parent ?old) (parentType Design::Responsibility) (child ?split) (childType Design::Responsibility)))
    (assert (ContainmentRelation (parent ?old) (parentType Design::Responsibility) (child ?extract) (childType Design::Responsibility)))
    (assert (QA_ChangeResponsibilityDescription
    		(parent ?split)
    		(question "Would you like to change the modified responsibility description?")
    		(oldDescription (fact-slot-value ?split Description))))
    (return ?split)
)
*/