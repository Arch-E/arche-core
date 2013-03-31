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

; Dependencies to modules: MAIN.clp
(provide Design)
(defmodule Design)

;
; Describes with module implements which interface
;
(deftemplate RealizeRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
    (slot childType (type SYMBOL))
)
;
; The interface belonging to a module
;
(deftemplate ModuleInterface
    (slot encapsulationLevel (type FLOAT)) ;Level of encapsulation provided by the interface. 0 = none, 5 = every second change is visible, 10 = complete encapsulation
    (slot costOfChange (type FLOAT)) ;Size of the module. Sum of the cost of change of its included responsibilities
    (slot name (type STRING)) ;The name of the quality attribute model element
    (slot source (type SYMBOL))
    (slot status (type INTEGER))
)
;
; Describes dependencies between modules
;
(deftemplate ModuleDependencyRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
    (slot probability (type FLOAT)) ;probability for changes
)
;
; A module is the element in the modifiability model. It contains responsibilities and has properties assigned, such as "cost of change"
;
(deftemplate Module
    (slot id (type SYMBOL)) ; an internal id used to reference modules
    (slot costOfChange (type FLOAT)) ;Size of the module. Sum of the cost of change of its included responsibilities
    (slot complexity (type FLOAT)) ;The more responsibility contained in the module, the higher the complexity
    (slot name (type STRING)) ;The name of the quality attribute model element
    (slot source (type SYMBOL))
    (slot status (type INTEGER))
)
;
; Assigns a responsibility to a module
;
(deftemplate ResponsibilityToModuleRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the module in this relation
    (slot child (type OBJECT)) ;Id of the responsibility in this relation
    (slot parentType (type SYMBOL)) ;the type of the parent (Module/Interface)
)
;
; This relation is used for refineiment of modules
;
(deftemplate ModuleRefinementRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; Specifies a wrapper around a module. A wrapper tries to encapsulate a module from an outside change
;
(deftemplate Wrapper
    (slot encapsulationLevel (type FLOAT))
    (slot name (type STRING)) ;The name of the quality attribute model element
    (slot source (type SYMBOL))
    (slot status (type INTEGER))
)
;
;
; Specifies an intermediary. 
;
(deftemplate Intermediary
    (slot name (type STRING)) ;The name of the quality attribute model element
    (slot source (type SYMBOL))
    (slot status (type INTEGER))
)
;

; This is a duplication of a responsibility fact for the design only
;
(deftemplate Responsibility
    (slot name (type STRING))
    (slot owner (type OBJECT))
)


(deftemplate SharedResource
    (slot id (type STRING)) 
    (slot name (type STRING))
    (slot description (type STRING))
    (slot source (type SYMBOL))
    ;(multislot resp_ids)
)

(deftemplate SharedResourceRelation
    (slot id (type STRING)) 
    (slot parent (type OBJECT))  ; shared resource
    (slot child (type OBJECT))   ; responsibility
    (slot source (type SYMBOL))
)

(deftemplate Thread
    (slot name (type STRING)) ;name of the task
    (slot scenario (type OBJECT)) ;parent scenario
    (slot stimulusType (type SYMBOL)) ;periodic/sporadic/stochastic
    (slot stimulusValue (type STRING)) ;value of the stimulus e.g. period
    (slot taskType (type SYMBOL)) ;e.g.HardDeadline
    (slot taskValue (type STRING)) ;e.g. value of the deadline
    (slot executionTime (type STRING))
    (slot latency (type STRING))
    (slot time_blocked (type STRING))
    (slot priority (type STRING))
    (slot source (type SYMBOL))
)
(deftemplate UnitOfConcurrency
    (slot name (type STRING)) ;name of the subtask
    (slot task (type OBJECT)) ;parent task
    (slot scenario (type OBJECT))
    (slot responsibility (type OBJECT)) ;parent responsibility
    (slot MutualExclusion (type SYMBOL)) ;true\false is mutually exclusive access needed
    (slot SharedResourceID (type OBJECT)) ;id of shared resource
    (slot taskType (type SYMBOL)) ;e.g.HardDeadline
    (slot taskValue (type STRING)) ;e.g. value of the deadline
    (slot executionTime (type STRING))
    (slot latency (type STRING))
    (slot priority (type STRING))
    (slot source (type SYMBOL))
)
(deftemplate ResponsibilityRealizedInRelation
    (slot parent (type OBJECT)) 
    (slot child (type OBJECT))
    (slot source (type SYMBOL))
)


(defglobal ?*ModuleName* = 1)

/*****************************************************************************
 *             ARCHITECTURE OPERATORS
 * The following functions provide basic operations on the current architecture
 *
 ****************************************************************************/

