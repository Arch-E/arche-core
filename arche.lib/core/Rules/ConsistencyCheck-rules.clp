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

; Dependencies to modules: ModifiabilityReasoningFrameworks.clp,MAIN.clp
(defmodule ConsistencyCheck)
(require MAIN)
(require Configuration)
;(require Design)
;(require ModifiabilityReasoningFrameworks)
(ModuleOption Debug)

;
; Make sure at the end all orphan facts are removed
;
(defrule RemoveOrphansForConsistency
    (declare (auto-focus TRUE) (salience -50))
    ?t <- (MAIN::T_CheckConsistency)
=>
    (RemoveOrphanFacts)
    (retract ?t)
)
;
; End of rule set PropertiesWithoutOwner
;------------------------------------------------------------
; Consistency rules for data flows
; - Check if all dataflow relations have corresponding dependency relations
;         AssignDependencyForDataflow
; - Every dataitem has to be assigned to at least one dataflow
;         RemoveOrphansForConsistency
; Consistency rules from dependencies between leaf responsibilities
; - Every dependency between responsibilities that are leafs gets a leaf responsibility
;   dependency relation
;         AssignLeafDependencies
; - A dependency to a decomposed responsibility is moved to its children
;         MoveDependencyToChildren
; - Only one dependency relation is allowed between any two leaf responsibilities
;         RemoveDoubleDependencies
; - Every leaf responsibility dependency relation must have at least one dependency
;   relation either directly or inherited from parent responsibilities
;         RemoveLeafDependencies

/* TODO: Need to be done by modifiability RF
(defrule AssignDependencyForDataflow
    (declare (auto-focus TRUE))
    (MAIN::T_CheckConsistency)
    ?df <- (MAIN::DataFlow (producer ?p) (consumer ?c) (source ?s))
    (not (or (MAIN::ResponsibilityToResponsibilityRelation (parent ?p) (child ?c))
            (MAIN::ResponsibilityToResponsibilityRelation (parent ?c) (child ?c)))
    )
    (Configuration::DataflowIncomingProbability ?pi)
    (Configuration::DataflowOutgoingProbability ?po)
=>
    (bind ?rr (assert (MAIN::ResponsibilityToResponsibilityRelation (parent ?p) (child ?c) (source ?s))))
    (assert (ModifiabilityReasoningFrameworks::P_ProbabilityOutgoing (owner ?rr) (value ?po) (source ?s)))
    (assert (ModifiabilityReasoningFrameworks::P_ProbabilityIncoming (owner ?rr) (value ?pi) (source ?s)))
    (retract ?df)
)
*/

/* TODO: Need to be done by modifiability RF
(defrule RemoveAssignedDataflows
    (declare (auto-focus TRUE))
    (MAIN::T_CheckConsistency)
    ?df <- (MAIN::DataFlow (producer ?p) (consumer ?c) (source ?s))
    (or (MAIN::ResponsibilityToResponsibilityRelation (parent ?p) (child ?c))
        (MAIN::ResponsibilityToResponsibilityRelation (parent ?c) (child ?c)))
=>
    (retract ?df)
)
*/

(defrule AssignIdToResponsibility
    ?re <- (MAIN::Responsibilities (id nil))
=>
    (modify ?re (id (gensym*)))
)

(defrule AssignStatusToResponsibility
    ?re <- (MAIN::Responsibilities (status nil))
=>
    (modify ?re (status 0))
)

(defrule SetStatusFlagsToZero
    (or
    	?fact <- (MAIN::Responsibilities (status nil))
;    	?fact <- (Design::Module (status nil))
;    	?fact <- (Design::ModuleInterface (status nil))
;    	?fact <- (ModifiabilityReasoningFrameworks::P_CostOfChange (status nil))
    )
=>
    (modify ?fact (status 0))
)

/* TODO: Need to be done by modifiability RF
(defrule FinishConsistencyCheck
    (declare (auto-focus TRUE) (salience -100))
    ?t <- (MAIN::T_CheckConsistency)
    
=>
    (CreateLeafDependencies)
    (retract ?t)
)
*/
