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

(provide TestAfterLocalize)
(require Test)
(require MAIN)
(require Design)
(require ModifiabilityReasoningFrameworks)
(defmodule TestDesign)
(ModuleOption Sequence load prepare execute cleanup stop)
(defglobal ?*oldMask* = 0)
(defrule TestDesignLoadTestData
	"Load the data to compare from database"
    (T_ControlModelExecution load)
    ?t <- (Test::RunTestCase ?tc)
    (Test::CaseSelection ?tc ?td)
=>
    (bind ?rete (engine))
    (bind ?*oldMask* (?rete getEventMask))
    (unwatch all)
   	(RestoreFactSet "Test" ?td)
    (retract ?t)
)
;*********************************************
; Preparation
;*********************************************
(defrule TestDesignPrepareResponsibilityRefinementRelation
    (T_ControlModelExecution prepare)
    ?r1 <- (MAIN::Responsibilities (name ?n1))
    ?r2 <- (MAIN::Responsibilities (name ?n2))
    (MAIN::ResponsibilityRefinementRelation (parent ?r1) (child ?r2))
=>
    (assert (ResponsibilityRefinement ?n1 ?n2))
)

(defrule TestDesignPrepareModuleDependencyRelation
    (T_ControlModelExecution prepare)
    ?r1 <- (Design::Module (name ?n1))
    ?r2 <- (Design::Module (name ?n2))
    (Design::ModuleDependencyRelation (parent ?r1) (child ?r2))
=>
    (assert (ModuleDependencyRelation ?n1 ?n2 Module))
)
(defrule TestDesignPrepareModuleInterfaceDependencyRelation1
    (T_ControlModelExecution prepare)
    ?in <- (Design::ModuleInterface (name ?ni))
    ?mo <- (Design::Module (name ?nm))
    (Design::ModuleDependencyRelation (parent ?in) (child ?mo))
=>
    (assert (ModuleDependencyRelation ?ni ?nm Module))
)
(defrule TestDesignPrepareModuleInterfaceDependencyRelation2
    (T_ControlModelExecution prepare)
    ?in <- (Design::ModuleInterface (name ?ni))
    ?mo <- (Design::Module (name ?nm))
    (Design::ModuleDependencyRelation (parent ?mo) (child ?in))
=>
    (assert (ModuleDependencyRelation ?nm ?ni Interface))
)
(defrule TestDesignPrepareResponsibilityDependencyRelation
    (T_ControlModelExecution prepare)
    ?r1 <- (MAIN::Responsibilities (name ?n1))
    ?r2 <- (MAIN::Responsibilities (name ?n2))
    (or
    	(MAIN::LeafResponsibilityDependencyRelation (parent ?r1) (child ?r2))
    	(MAIN::LeafResponsibilityDependencyRelation (parent ?r2) (child ?r1)))
=>
    (assert (LeafDependencyRelation ?n1 ?n2))
)
(defrule TestDesignPrepareModuleInterfaces
    (T_ControlModelExecution prepare)
    ?mo <- (Design::Module (name ?mn))
	?if <- (Design::ModuleInterface (name ?in))
	(Design::RealizeRelation (parent ?mo) (child ?if) (childType Interface))
=>
    (assert (ModuleInterface ?in ?mn))
)
(defrule TestDesignPrepareResponsibilityAssignmentsModule
    (T_ControlModelExecution prepare)
    ?mo <- (Design::Module (name ?mn))
	?re <- (MAIN::Responsibilities (name ?rn))
	(Design::ResponsibilityToModuleRelation (parent ?mo) (child ?re))
=>
    (assert (ResponsibilityAssignmentM ?mn ?rn))
)
(defrule TestDesignPrepareResponsibilityAssignmentsInterface
    (T_ControlModelExecution prepare)
    ?mo <- (Design::ModuleInterface (name ?mn))
	?re <- (MAIN::Responsibilities (name ?rn))
	(Design::ResponsibilityToModuleRelation (parent ?mo) (child ?re))
=>
    (assert (ResponsibilityAssignmentI ?mn ?rn))
)
(defrule TestDesignPrepareModelArcs
    (T_ControlModelExecution prepare)
    ?r1 <- (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?n1))
    ?r2 <- (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?n2))
    (ModifiabilityReasoningFrameworks::Arc_Relation (source ?r1) (target ?r2) (probability ?pr))
=>
    (assert (ModelArc ?n1 ?n2 ?pr))
)
(defrule TestDesignPrepareModelNodesAffected
    (T_ControlModelExecution prepare)
    ?no <- (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?nn))
    ?sc <- (MAIN::Scenarios (description ?sn))
    (ModifiabilityReasoningFrameworks::Node_affected (scenario ?sc) (nodeId ?no))
=>
    (assert (Nodes_Affected ?sn ?nn))
)


;
; Print comment if everything is correct
;
(defrule TestDesignCheckResponsibilitiesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Responsibilities (name ?na))
            (MAIN::Responsibilities (name ?na)))
    (forall (MAIN::Responsibilities (name ?na2))
            (Test::Responsibilities (name ?na2)))
=>
    (printout t "R  : All Responsibilities accounted for" crlf)
)
(defrule TestDesignCheckLeaveDependenciesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Leave_Dependencies (parent ?n1) (child ?n2))
    		(or
    			(LeafDependencyRelation ?n1 ?n2)
    			(LeafDependencyRelation ?n2 ?n1)))
    (forall (LeafDependencyRelation ?n22 ?n12)
    		(or
    			(Test::Leave_Dependencies (parent ?n12) (child ?n22))
    			(Test::Leave_Dependencies (parent ?n22) (child ?n12))))
=>
    (printout t "RR : All Leave-Responsibility dependencies accounted for" crlf)
)

(defrule TestDesignCheckModulesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Module (name ?na))
    	    (Design::Module (name ?na)))
    (forall (Design::Module (name ?na2))
    	    (Test::Module (name ?na2)))
=>
    (printout t "M  : All Modules accounted for" crlf)
)
(defrule TestDesignCheckInterfacesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Interface (name ?in) (moduleName ?mn))
    	    (ModuleInterface ?in ?mn))
    (forall (ModuleInterface ?in2 ?mn2)
    	    (Test::Interface (name ?in2) (moduleName ?mn2)))
=>
    (printout t "I  : All Interfaces accounted for" crlf)
)
(defrule TestDesignCheckResponsibilityAssignmentsMCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Module))
    		(ResponsibilityAssignmentM ?mn ?rn))
    (forall (ResponsibilityAssignmentM ?mn2 ?rn2)
    		(Test::Module_Responsibilities (module ?mn2) (responsibility ?rn2) (type Module)))
=>
    (printout t "RM : All Responsibility to Module assignments accounted for" crlf)
)
(defrule TestDesignCheckResponsibilityAssignmentsICorrect
    (T_ControlModelExecution execute)
    (forall (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Interface))
    		(ResponsibilityAssignmentI ?mn ?rn))
    (forall (ResponsibilityAssignmentI ?mn2 ?rn2)
    		(Test::Module_Responsibilities (module ?mn2) (responsibility ?rn2) (type Interface)))
=>
    (printout t "RI : All Responsibility to Interface assignments accounted for" crlf)
)
(defrule TestDesignCheckModuleDependenciesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Module))
			(ModuleDependencyRelation ?n1 ?n2 Module))
    (forall (ModuleDependencyRelation ?n12 ?n22 Module)
        	(Test::Module_Dependencies (parent ?n12) (child ?n22) (childType Module)))
=>
    (printout t "MM : All Dependencies between Modules accounted for" crlf)
)
(defrule TestDesignCheckResponsibilityRefinementCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Responsibility_Refinement (parent ?n1) (child ?n2))
			(ResponsibilityRefinement ?n1 ?n2))
    (forall (ResponsibilityRefinement ?n12 ?n22)
        	(Test::Responsibility_Refinement (parent ?n12) (child ?n22)))
=>
    (printout t "RD : All Responsibility decompositions accounted for" crlf)
)

(defrule TestDesignCheckModuleInterfaceDependenciesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Interface))
			(ModuleDependencyRelation ?n1 ?n2 Interface))
    (forall (ModuleDependencyRelation ?n12 ?n22 Interface)
        	(Test::Module_Dependencies (parent ?n12) (child ?n22) (childType Interface)))
=>
    (printout t "MI : All Dependencies between Modules and Interfaces accounted for" crlf)
)
(defrule TestDesignCheckNodesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Model_Nodes (node ?na))
    		(ModifiabilityReasoningFrameworks::Node_Responsibility (name ?na)))
    (forall (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?na2))
       		(Test::Model_Nodes (node ?na2)))
=>
    (printout t "N  : All Model Nodes accounted for" crlf)
)
(defrule TestDesignCheckScenarioAssignmentsCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Model_Nodes_Affected (parent ?sc) (child ?no))
    		(Nodes_Affected ?sc ?no))
    (forall (Nodes_Affected ?sc ?no)
       		(Test::Model_Nodes_Affected (parent ?sc) (child ?no)))
=>
    (printout t "SN : All Scenario to Node assignements accounted for" crlf)
)

(defrule TestDesignCheckNodeDependenciesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Model_Arcs (parent ?n1) (child ?n2))
			(ModelArc ?n1 ?n2))
    (forall (ModelArc ?n12 ?n22)
        	(Test::Model_Arcs (parent ?n12) (child ?n22)))
=>
    (printout t "NN : All Arcs between Model Nodes accounted for" crlf)
)
(defrule TestDesignCheckNodeDependencyPropertiesCorrect
    (T_ControlModelExecution execute)
    (forall (Test::Model_Arcs (parent ?n1) (child ?n2) (probability ?pr))
			(ModelArc ?n1 ?n2 ?p2&:(eq (round2 ?pr) (round2 ?p2))))
=>
    (printout t "NNV: All Probabilities of Arcs between Model Nodes accounted for" crlf)
)

;
; Check that we have all responsibilities
;
(defrule TestDesignCheckResponsibilities
    (T_ControlModelExecution execute)
    (Test::Responsibilities (name ?na))
    (not (MAIN::Responsibilities (name ?na)))
=>
    (printout t "R  : Responsibility '" ?na "' missing" crlf)
)
;
; Check that we don't have too many responsibilities
;
(defrule TestDesignCheckResponsibilitiesTooMany
    (T_ControlModelExecution execute)
    ?r <- (MAIN::Responsibilities (name ?na))
    (not (Test::Responsibilities (name ?na)))
=>
    (printout t "R  : Responsibility '" ?na "' should not be there (" ?r ")" crlf)
)

;
; Check that we have the required responsibility decompositions
;
(defrule TestDesignCheckResponsibilityRefinement
    (T_ControlModelExecution execute)
    (Test::Responsibility_Refinement (parent ?n1) (child ?n2))
    (not (ResponsibilityRefinement ?n1 ?n2))
=>
    (printout t "RD : Decomposition of responsibility '" ?n1 "' into responsibility '" ?n2 "' missing" crlf)
)
;
; Check that we don't have too many dependencies
;
(defrule TestDesignCheckResponsibilityRefinementTooMany
    (T_ControlModelExecution execute)
    (ResponsibilityRefinement ?n1 ?n2)
    (not (Test::Responsibility_Refinement (parent ?n1) (child ?n2)))
=>
    (printout t "RD : Decomposition of responsibility '" ?n1 "' into responsibility '" ?n2 "' should not be there" crlf)
)

;
; Check that we have the required leave dependencies
;
(defrule TestDesignCheckLeaveDependencies
    (T_ControlModelExecution execute)
    (Test::Leave_Dependencies (parent ?n1) (child ?n2))
    (not (or
    	(LeafDependencyRelation ?n1 ?n2)
    	(LeafDependencyRelation ?n2 ?n1)))
=>
    (printout t "RR : Dependency between responsibility '" ?n1 "' and '" ?n2 "' missing" crlf)
)
;
; Check that we don't have too many dependencies
;
(defrule TestDesignCheckLeaveDependenciesTooMany
    (T_ControlModelExecution execute)
    (LeafDependencyRelation ?n1 ?n2)
    (not (or
    	(Test::Leave_Dependencies (parent ?n1) (child ?n2))
    	(Test::Leave_Dependencies (parent ?n2) (child ?n1))))
=>
    (printout t "RR : Dependency between responsibility '" ?n1 "' and '" ?n2 "' should not be there" crlf)
)
;
; Check that we have all modules
;
(defrule TestDesignCheckModules
    (T_ControlModelExecution execute)
    (Test::Module (name ?na))
    (not (Design::Module (name ?na)))
=>
    (printout t "M  : Module '" ?na "' missing" crlf)
)
;
; Check that we don't have too many modules
;
(defrule TestDesignCheckModulesTooMany
    (T_ControlModelExecution execute)
    ?r <- (Design::Module (name ?na))
    (not (Test::Module (name ?na)))
=>
    (printout t "M  : Module '" ?na "' should not be there (" ?r ")" crlf)
)
;
; Check that we have the correct costOfChange
;
(defrule TestDesignCheckModulesCost
    (T_ControlModelExecution execute)
    (Test::Module (name ?na) (costOfChange ?ce&~nil))
    (Design::Module (name ?na) (costOfChange ?ci&~nil))
    (test (<> (round2 ?ce) (round2 ?ci)))
=>
    (printout t "M V: Module '" ?na "' has wrong cost. Expected: " ?ce " found: " ?ci crlf)
)
(defrule TestDesignCheckInterfaceCost
    (T_ControlModelExecution execute)
    (Test::Interface (name ?na) (costOfChange ?ce&~nil))
    (Design::ModuleInterface (name ?na) (costOfChange ?ci&~nil))
    (test (<> (round2 ?ce) (round2 ?ci)))
=>
    (printout t "I V: Interface '" ?na "' has wrong cost. Expected: " ?ce " found: " ?ci crlf)
)
;
; Check that we have all Interfaces
;
(defrule TestDesignCheckInterfaces
    (T_ControlModelExecution execute)
    (Test::Interface (name ?in) (moduleName ?mn))
    (not (ModuleInterface ?in ?mn))
=>
    (printout t "I  : Interface '" ?in "' for module '" ?mn "' missing" crlf)
)
;
; Check that we don't have too many Interfaces
;
(defrule TestDesignCheckInterfacesTooMany
    (T_ControlModelExecution execute)
    (ModuleInterface ?in ?mn)
    (not (Test::Interface (name ?in) (moduleName ?mn)))
=>
    (printout t "I  : Interface '" ?in "' for module '" ?mn "' should not be there" crlf)
)
;
; Check that we have all Module responsibility assignments
;
(defrule TestDesignCheckResponsibilityAssignmentsM
    (T_ControlModelExecution execute)
    (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Module))
    (not (ResponsibilityAssignmentM ?mn ?rn))
=>
    (printout t "RM : Responsibility '" ?rn "' assigned to module '" ?mn "' missing" crlf)
)
(defrule TestDesignCheckResponsibilityAssignmentsI
    (T_ControlModelExecution execute)
    (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Interface))
    (not (ResponsibilityAssignmentI ?mn ?rn))
=>
    (printout t "RI : Responsibility '" ?rn "' assigned to interface '" ?mn "' missing" crlf)
)
;
; Check that we don't have too many Module responsibility assignments
;
(defrule TestDesignCheckResponsibilityAssignmentsMTooMany
    (T_ControlModelExecution execute)
	(ResponsibilityAssignmentM ?mn ?rn)
    (not (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Module)))
=>
    (printout t "RM : Responsibility '" ?rn "' assigned to module '" ?mn "' should not be there" crlf)
)
(defrule TestDesignCheckResponsibilityAssignmentsITooMany
    (T_ControlModelExecution execute)
	(ResponsibilityAssignmentI ?mn ?rn)
    (not (Test::Module_Responsibilities (module ?mn) (responsibility ?rn) (type Interface)))
=>
    (printout t "RI : Responsibility '" ?rn "' assigned to interface '" ?mn "' should not be there" crlf)
)
;
; Check that we have the required module dependencies
;
(defrule TestDesignCheckModuleDependencies1
    (T_ControlModelExecution execute)
    (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Module))
	(not (ModuleDependencyRelation ?n1 ?n2 Module))
=>
    (printout t "MM : Dependency between modules '" ?n1 "' and '" ?n2 "' missing" crlf)
)
(defrule TestDesignCheckModuleDependencies2
    (T_ControlModelExecution execute)
    (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Interface))
	(not (ModuleDependencyRelation ?n1 ?n2 Interface))
=>
    (printout t "MI : Dependency between module '" ?n1 "' and Interface '" ?n2 "' missing" crlf)
)
;
; Check that we don't have too many dependencies
;
(defrule TestDesignCheckModuleDependenciesTooMany1
    (T_ControlModelExecution execute)
    (ModuleDependencyRelation ?n1 ?n2 Module)
    (not (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Module)))
=>
    (printout t "MM : Dependency between modules '" ?n1 "' and '" ?n2 "' should not be there" crlf)
)
(defrule TestDesignCheckModuleDependenciesTooMany2
    (T_ControlModelExecution execute)
    (ModuleDependencyRelation ?n1 ?n2 Interface)
    (not (Test::Module_Dependencies (parent ?n1) (child ?n2) (childType Interface)))
=>
    (printout t "MI : Dependency between module '" ?n1 "' and Interface '" ?n2 "' should not be there" crlf)
)
;
; Check that we have dependencies that refere to themselves
;
(defrule TestDesignCheckCircularModuleDependencies
    (T_ControlModelExecution execute)
    (Design::ModuleDependencyRelation (parent ?r1) (child ?r2))
    (test (eq ?r1 ?r2))
=>
    (printout t "MM : Dependency between modules '" (fact-slot-value ?r1 name) "' and '" (fact-slot-value ?r2 name) "' should not be there" crlf)
)

;
; Check that we have all model nodes
;
(defrule TestDesignCheckNodes
    (T_ControlModelExecution execute)
    (Test::Model_Nodes (node ?na))
    (not (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?na)))
=>
    (printout t "N  : Node '" ?na "' missing" crlf)
)
;
; Check the nodes cost of change
;
(defrule TestDesignCheckNodeCost
    (T_ControlModelExecution execute)
    (Test::Model_Nodes (node ?na) (costOfChange ?ce&~nil))
    (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?na) (cost ?ci&~nil))
    (test (<> (round2 ?ce) (round2 ?ci)))
=>
    (printout t "N V: Node '" ?na "' has wrong cost. Expected: " ?ce " found: " ?ci crlf)
)

;
; Check that we don't have too many model nodes
;
(defrule TestDesignCheckNodesTooMany
    (T_ControlModelExecution execute)
    ?r <- (ModifiabilityReasoningFrameworks::Node_Responsibility (name ?na))
    (not (Test::Model_Nodes (node ?na)))
=>
    (printout t "N  : Node '" ?na "' should not be there" crlf)
)

(defrule TestDesignCheckScenarioAssignments
    (T_ControlModelExecution execute)
    (Test::Model_Nodes_Affected (parent ?sc) (child ?no))
    (not (Nodes_Affected ?sc ?no))
=>
    (printout t "SN : Assignment of scenario '" ?sc "' to Node '" ?no "' missing" crlf)
)
(defrule TestDesignCheckScenarioAssignmentsTooMany
    (T_ControlModelExecution execute)
    (Nodes_Affected ?sc ?no)
    (not (Test::Model_Nodes_Affected (parent ?sc) (child ?no)))
=>
    (printout t "SN : Assignment of scenario '" ?sc "' to Node '" ?no "' should not be there" crlf)
)

;
; Check that we have the required model dependencies
;
(defrule TestDesignCheckNodeDependencies1
    (T_ControlModelExecution execute)
    (Test::Model_Arcs (parent ?n1) (child ?n2))
	(not (ModelArc ?n1 ?n2 ?))
=>
    (printout t "NN : Arc between nodes '" ?n1 "' and '" ?n2 "' missing" crlf)
)
;
; Check that we don't have too many model dependencies
;
(defrule TestDesignCheckNodeDependenciesTooMany1
    (T_ControlModelExecution execute)
    (ModelArc ?n1 ?n2 ?)
    (not (Test::Model_Arcs (parent ?n1) (child ?n2)))
=>
    (printout t "NN : Arc between nodes '" ?n1 "' and '" ?n2 "' should not be there" crlf)
)
;
; Check that the probabilities for the model dependencies are correct
;
(defrule TestDesignCheckNodeDependenciesProbability
    (T_ControlModelExecution execute)
    (ModelArc ?n1 ?n2 ?pr)
    (Test::Model_Arcs (parent ?n1) (child ?n2) (probability ?ps&:(neq (round2 ?ps) (round2 ?pr))))
=>
    (printout t "NNV: The probability of the arc between nodes '" ?n1 "' and '" ?n2 "' should be " ?ps " but is " ?pr crlf)
)

;*********************************************
; Cleaning up
;*********************************************
(defrule TestDesignCleanupTemps1
    (T_ControlModelExecution cleanup)
	?f <- (ModuleDependencyRelation ? ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps2
    (T_ControlModelExecution cleanup)
	?f <- (LeafDependencyRelation ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps3
    (T_ControlModelExecution cleanup)
	?f <- (ModuleInterface ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps4
    (T_ControlModelExecution cleanup)
	?f <- (ResponsibilityAssignmentM ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps6
    (T_ControlModelExecution cleanup)
	?f <- (ResponsibilityAssignmentI ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps7
    (T_ControlModelExecution cleanup)
	?f <- (ResponsibilityRefinement ? ?)
=>
    (retract ?f)
)
(defrule TestDesignCleanupTemps8
    (T_ControlModelExecution cleanup)
	?f <- (Nodes_Affected ? ?)
=>
    (retract ?f)
)

(defrule TestDesignCleanupTemps5
    (T_ControlModelExecution cleanup)
	?f <- (ModelArc ? ? ?)
=>
    (retract ?f)
)

;*********************************************
; Stop the execution
;*********************************************
(defrule TestDesignStopAtEnd
    ?t <- (T_ControlModelExecution stop)
=>
    (DeleteFactSetFromMemory "Test")
    (retract ?t)
    (if (bit-and ?*oldMask* 16) then
        (watch facts))
    (if (bit-and ?*oldMask* 2) then
        (watch rules))
;    (if (bit-and ?*oldMask* 4) then
;        (watch activations))
    (if (bit-and ?*oldMask* 1048576) then
        (watch focus))
    (pop-focus)
    (halt)
)
