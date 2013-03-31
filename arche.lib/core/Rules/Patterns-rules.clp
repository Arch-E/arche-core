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

; Dependencies to modules: MAIN.clp,Design.clp,ModifiabilityReasoningFrameworks.clp
(set-current-module Patterns)
;
; The following two rule are generated automatically for debugging purposes
; Please do not change them
;
(defrule DebugPatternsEnter
    (declare (salience 1000))
    (MAIN::Debug Patterns ?trace ?break)
=>
    ;(printout t "***Entered Patterns" crlf)
    (if (neq ?trace none) then
        (watch ?trace))
    (if ?break then
        ;(printout t "   Stopped at beginning of module Patterns" crlf)
        (halt))
)
(defrule DebugPatternsExit
    (declare (salience -1000))
    ?d <- (MAIN::Debug Patterns ?trace ?break)
=>
    ;(printout t "***Exited  Patterns" crlf)
    (if (neq ?trace none) then
        (unwatch ?trace))
    (retract ?d)
    (assert (MAIN::Debug Patterns ?trace ?break))
    (return)
)
;
; End of the generated debugging rules
;
;============================================================
; Start of rule set ReplaceResponsibilityWithPattern
;
(defrule ReplaceResponsibilityWithModule
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pc <- (PatternContainer (pattern ?pa) (name ?pcn))
    (PatternConnector (pattern ?pa) (owner ?pc))
    ?dr <- (Design::Responsibility (owner ?res))
    (Design::ResponsibilityToModuleRelation (parent ?mo) (child ?dr))
=>
    (modify ?mo (name (str-cat "(M) " (SubstituteFields ?pcn ?res))) (source User))
    (assert (PatternClassInstanceRelation ?res ?pc ?mo))
)
(defrule ReplaceResponsibilityWithElement
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pe <- (PatternElement (pattern ?pa) (name ?pen) (description ?ped))
    (PatternConnector (pattern ?pa) (owner ?pe))
=>
    (modify ?res (name (SubstituteFields ?pen ?res)) (description (SubstituteFields ?ped ?res)))
    (assert (AdjustProperties ?res ?pe ?res))
)
;
; End of rule set ReplaceResponsibilityWithPattern
;------------------------------------------------------------
;============================================================
; Start of rule set CreateNewPatternElements
;
(defrule CreateNewPatternElements
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pe <- (PatternElement (pattern ?pa) (name ?pen) (description ?ped))
    (not (PatternConnector (pattern ?pa) (owner ?pe)))
=>
    (bind ?nr (assert (MAIN::Responsibilities (id (gensym*)) (name (SubstituteFields ?pen ?res)) (description (SubstituteFields ?ped ?res)) (source User))))
    (assert (AdjustProperties ?nr ?pe ?res))
    	(assert (PatternClassInstanceRelation ?res ?pe ?nr))
)
(defrule CreateNewPatternInterface
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pi <- (PatternElementInterface (pattern ?pa) (name ?in))
=>
    (bind ?el (GetSlotvalueFromFacts valueAbs (str-cat "(PatternItemProperty (name P_" "EncapsulationLevel) (owner ?1))") ?pi))
    (if (= (length$ ?el) 1) then
        (bind ?el (float(nth$ 1 ?el)))
     else
        (bind ?el nil))
    (bind ?mi (assert (Design::ModuleInterface (encapsulationLevel ?el) (name (SubstituteFields ?in ?res)) (source User))))
    	(assert (PatternClassInstanceRelation ?res ?pi ?mi))
)
;
; End of rule set CreateNewPatternElements
;------------------------------------------------------------
(defrule AdjustPatternElementProperties
    (AdjustProperties ?dres ?pe ?sres)
    (PatternItemProperty (name ?prop) (owner ?pe) (valueAbs ?nva) (valueFactor ?nvf))
    (MAIN::PropertyModuleAssignment ?prop ?mod)
=>
    (bind ?mprop (sym-cat ?mod "::" ?prop))
    (bind ?op (GetPropertyFact ?mprop ?sres))
    (bind ?np (GetPropertyFact ?mprop ?dres))
    (if (neq ?op nil) then 
        (bind ?ov (fact-slot-value ?op value))
        (if (numberp ?ov) then
            (bind ?ov (roundValue(* ?ov ?nvf))))     
        (if (eq ?np nil) then
    	        (assert-string (str-cat "(" ?mprop " (owner " ?dres ") (value " ?ov ") (source " (fact-slot-value ?op source) "))"))
    	     else
    	        (modify ?np (value ?ov)))
    else
    		(if (numberp ?nva) then
            (bind ?nva (floatp ?nva)))    
    	(assert-string (str-cat "(" ?mprop " (owner " ?dres ") (value " ?nva ") (source User))")))
)
;============================================================
; Start of rule set CreatePatternRelation
;
(defrule CreatePatternDependencyRelation
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pr <- (Dependency (parent ?from) (child ?to) (pattern ?pa))
    (PatternClassInstanceRelation ?res ?from ?r1)
    (PatternClassInstanceRelation ?res ?to ?r2)
=>
    (bind ?rel (assert (MAIN::ResponsibilityToResponsibilityRelation (id (gensym*)) (source User) (parent ?r1) (child ?r2))))
    (assert (AdjustProperties ?rel ?pr ?res))
)
(defrule CreatePatternRefinementRelation
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pr <- (Refinement (parent ?from) (child ?to) (pattern ?pa))
    (PatternClassInstanceRelation ?res ?from ?par)
    (PatternClassInstanceRelation ?res ?to ?cr)
    ?dr <- (Design::Responsibility (owner ?cr))
=>
    (bind ?rel (assert (MAIN::ResponsibilityRefinementRelation (source User) (parent ?res) (child ?cr))))
    (bind ?drel (QueryFactbase "(Design::ResponsibilityToModuleRelation (child ?1))" ?dr))
    (if (= (length$ ?drel) 0) then
        (assert (Design::ResponsibilityToModuleRelation (child ?dr) (parent ?par) (source User)))
     else
    	(modify (nth$ 1 ?drel) (source User) (parent ?par)))
    (assert (AdjustProperties ?rel ?pr ?res))
)
(defrule CreatePatternInterfaceRelation
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    ?pr <- (InterfaceRealization (parent ?pm) (child ?pi) (pattern ?pa))
    (PatternClassInstanceRelation ?res ?pm ?rr)
    (PatternClassInstanceRelation ?res ?pi ?in)
    ?dr <- (Design::Responsibility (owner ?rr))
    (Design::ResponsibilityToModuleRelation (child ?dr) (parent ?mo))
=>
    (bind ?rel (assert (Design::RealizeRelation (source User) (parent ?mo) (child ?in) (childType interface))))
    (assert (AdjustProperties ?rel ?pr ?res))
)
;
; End of rule set CreatePatternRelation
;------------------------------------------------------------
;============================================================
; Start of rule set MoveDependenciesToPatternConnector
;
(defrule MoveDependenciesToModuleInterface
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    (PatternConnector (type interface) (owner ?in) (valueAbs "UseForDependencies") (pattern ?pa))
    (InterfaceRealization (pattern ?pa) (parent ?mo) (child ?in))
    (PatternItemProperty (pattern ?pa) (name ?ppn&:(eq ?ppn (sym-cat "P_" "EncapsulationLevel"))) (owner ?in) (valueAbs ?el))
    (or ?orel <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?res) (child ?sres))
    	?orel <- (MAIN::ResponsibilityToResponsibilityRelation (child ?res) (parent ?sres)))
    (Refinement (pattern ?pa) (parent ?mo) (child ?pe))
    (PatternClassInstanceRelation ?res ?pe ?nres)
=>
    (if (eq (fact-slot-value ?orel parent) ?res) then
        (bind ?nrel (DuplicateFactWithProperties ?orel parent ?nres))
        (bind ?opr (GetPropertyFact "ModifiabilityReasoningFrameworks::P_ProbabilityOutgoing" ?nrel))
        (if (neq ?opr nil) then
        	(modify ?opr (value (roundValue (* (fact-slot-value ?opr value) (/ (- 10 ?el) 10))))))
     else
        (bind ?nrel (DuplicateFactWithProperties ?orel child ?nres))
        (bind ?opr (GetPropertyFact "ModifiabilityReasoningFrameworks::P_ProbabilityIncoming" ?nrel))
        (if (neq ?opr nil) then
        	(modify ?opr (value (roundValue (* (fact-slot-value ?opr value) (/ (- 10 ?el) 10)))))))
    (assert (RemoveOldRelation ?orel))
)
(defrule MoveDependenciesToModule
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    (PatternConnector (type container) (owner ?mo) (valueAbs "UseForDependencies") (pattern ?pa))
    (or ?orel <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?res) (child ?sres))
    	?orel <- (MAIN::ResponsibilityToResponsibilityRelation (child ?res) (parent ?sres)))
    (Refinement (pattern ?pa) (parent ?mo) (child ?pe))
    (PatternClassInstanceRelation ?res ?pe ?nres)
=>
    (if (eq (fact-slot-value ?orel parent) ?res) then
        (DuplicateFactWithProperties ?orel parent ?nres)
     else
        (DuplicateFactWithProperties ?orel child ?nres))
    (assert (RemoveOldRelation ?orel))
)
(defrule MoveDependenciesToResponsibilityInterface
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    (PatternConnector (type interface) (owner ?in) (valueAbs "UseForDependencies") (pattern ?pa))
    ?pe <- (PatternElement (pattern ?pa))
    (InterfaceRealization (pattern ?pa) (parent ?pe) (child ?in))
    (PatternItemProperty (pattern ?pa) (name ?ppn&:(eq ?ppn (sym-cat "P_" "EncapsulationLevel"))) (owner ?in) (valueAbs ?el))
    (or ?orel <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?res) (child ?sres))
    	?orel <- (MAIN::ResponsibilityToResponsibilityRelation (child ?res) (parent ?sres)))
    (PatternClassInstanceRelation ?res ?pe ?nres)
=>
    (if (eq (fact-slot-value ?orel parent) ?res) then
        (bind ?nrel (DuplicateFactWithProperties ?orel parent ?nres))
        (bind ?opr (GetPropertyFact "ModifiabilityReasoningFrameworks::P_ProbabilityOutgoing" ?nrel))
        (if (neq ?opr nil) then
        	(modify ?opr (value (roundValue (* (fact-slot-value ?opr value) (/ (- 10 ?el) 10))))))
     else
        (bind ?nrel (DuplicateFactWithProperties ?orel child ?nres))
        (bind ?opr (GetPropertyFact "ModifiabilityReasoningFrameworks::P_ProbabilityIncoming" ?nrel))
        (if (neq ?opr nil) then
        	(modify ?opr (value (roundValue (* (fact-slot-value ?opr value) (/ (- 10 ?el) 10)))))))
    (assert (RemoveOldRelation ?orel))
)
(defrule MoveDependenciesToResponsibility
    (InstantiatePattern ?pn ?res)
    ?pa <- (Pattern (name ?pn))
    (PatternConnector (type element) (owner ?pe) (valueAbs "UseForDependencies") (pattern ?pa))
    (or ?orel <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?res) (child ?sres))
    	?orel <- (MAIN::ResponsibilityToResponsibilityRelation (child ?res) (parent ?sres)))
    (PatternClassInstanceRelation ?res ?pe ?nres)
=>
    (if (eq (fact-slot-value ?orel parent) ?res) then
        (DuplicateFactWithProperties ?orel parent ?nres)
     else
        (DuplicateFactWithProperties ?orel child ?nres))
    (assert (RemoveOldRelation ?orel))
)
;
; End of rule set MoveDependenciesToPatternConnector
;------------------------------------------------------------
;============================================================
; Start of rule set CleanupAfterPatternInstantiation
;
(defrule CleanupAfterPatternInstantiation1
    (declare (salience -100))
    ?tf <- (RemoveOldRelation ?rel)
=>
    (bind ?props (GetPropertiesToOwner ?rel))
    (foreach ?prop ?props (retract ?prop))
    (retract ?rel)
    (retract ?tf)
)
(defrule CleanupAfterPatternInstantiation2
    (declare (salience -100))
    ?tf <- (InstantiatePattern ? ?res)
    (not (exists (or (MAIN::ResponsibilityToResponsibilityRelation (parent ?res))
        	 (MAIN::ResponsibilityToResponsibilityRelation (child ?res)))))
=>
    	(if (= (length$ (GetChildResponsibilities ?res)) 0) then
    	(retract ?res))
    (retract ?tf)
)
(defrule CleanupAfterPatternInstantiation3
    (declare (salience -100))
    ?tf <- (AdjustProperties ? ? ?)
=>
    (retract ?tf)
)
(defrule CleanupAfterPatternInstantiation4
    (declare (salience -100))
    ?tf <- (PatternClassInstanceRelation ? ? ?)
=>
    (retract ?tf)
)
(defrule CleanupAfterPatternInstantiationLeftOvers
    (declare (salience -100))
    (InstantiatePattern ? ?res)
    (not (exists (RemoveOldRelation ?)))
    (or ?rel <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?res))
        ?rel <- (MAIN::ResponsibilityToResponsibilityRelation (child ?res)))
=>
    (assert (RemoveOldRelation ?rel))
)
;
; End of rule set CleanupAfterPatternInstantiation
;------------------------------------------------------------
