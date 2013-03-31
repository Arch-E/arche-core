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

; Dependencies to modules: RMAModel.clp,Design.clp,ModifiabilityReasoningFrameworks.clp,MAIN.clp
(require CreateDesign)
(require Design)
(require MAIN)
(require ModifiabilityReasoningFrameworks)
(set-current-module CreateDesign)
(ModuleOption Sequence delete structure decompose properties)
(ModuleOption Debug)

(defrule CalculateCostOfChangeForModule
    (T_ControlModelExecution properties)
    ?m <- (Design::Module (costOfChange nil))
=>
    (bind ?rr (GetSlotvalueFromFacts child "(Design::ResponsibilityToModuleRelation (parent ?1) (parentType Module))" ?m))
    (bind ?sum 0.0)
    (foreach ?r ?rr
    	(bind ?v (HasProperty "ModifiabilityReasoningFrameworks::P_CostOfChange" ?r))
    	   	(if (neq ?v FALSE) then (bind ?sum (+ ?sum (fact-slot-value ?v value)))))
    (modify ?m (costOfChange ?sum))
)
(defrule CalculateCostOfChangeForInterface
    (T_ControlModelExecution properties)
    ?m <- (Design::ModuleInterface (costOfChange nil))
=>
    (bind ?rr (GetSlotvalueFromFacts child "(Design::ResponsibilityToModuleRelation (parent ?1) (parentType Interface))" ?m))
    (bind ?sum 0.0)
    (foreach ?r ?rr
    	(bind ?v (HasProperty "ModifiabilityReasoningFrameworks::P_CostOfChange" ?r))
    	   	(if (neq ?v FALSE) then (bind ?sum (+ ?sum (fact-slot-value ?v value)))))
    (modify ?m (costOfChange ?sum))
)
;============================================================
; Start of rule set RefineModulesFromModifiability
;
;(defrule RefineModulesFromModifiability
;    (T_ControlModelExecution decompose)
;    ?rm1 <- (Design::ResponsibilityToModuleRelation (parent ?m) (child ?r1))
;    ?rm2 <- (Design::ResponsibilityToModuleRelation (parent ?m) (child ?r2&~?r1))
;=>
;    (bind ?m1 (assert (Design::Module (name (CreateModuleName (fact-slot-value ?r1 name))) (source ArchE))))
;    (bind ?m2 (assert (Design::Module (name (CreateModuleName (fact-slot-value ?r2 name))) (source ArchE))))
;    (modify ?rm1 (parent ?m1))
;    (modify ?rm2 (parent ?m2))
;    (assert (Design::ModuleRefinementRelation (source ArchE) (parent ?m) (child ?m1)))
;    (assert (Design::ModuleRefinementRelation (source ArchE) (parent ?m) (child ?m2)))
;)
(defrule RefineModulesFromModifiabilityRest
    (T_ControlModelExecution decompose)
    ?rm1 <- (Design::ResponsibilityToModuleRelation (parent ?m) (child ?r1) (parentType Module))
    (exists (Design::ModuleRefinementRelation (parent ?m)))
=>
    (bind ?m1 (assert (Design::Module (name (CreateModuleName (fact-slot-value ?r1 name))) (source ArchE))))
    (modify ?rm1 (parent ?m1))
    (assert (Design::ModuleRefinementRelation (source ArchE) (parent ?m) (child ?m1)))
)
;
; End of rule set RefineModulesFromModifiability
;------------------------------------------------------------
