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

;    (import MAIN deftemplate Scenarios)
;    (import Planner deftemplate T_MakeAnotherRound)
;    (import MAIN OrderedFact T_SaveFactBase)
;    (import MAIN OrderedFact T_RestoreFactBase)
;    (import PerformanceReasoningFrameworks deftemplate P_ExecutionTime)
;    (export deftemplate PerformanceModelDependency)
;    (export deftemplate UnitOfConcurrency)
;    (export deftemplate P_ExecutionTime)
; Dependencies to modules: MAIN.clp,Planner.clp
(provide PerformanceReasoningFrameworks)
(defmodule PerformanceReasoningFrameworks)
;
; This fact describes the type of scenarios the performance reasoning framework
; is interested in
;
(deftemplate ScenariosOfInterest
    (slot quality (type SYMBOL))
    (slot measureType (type SYMBOL))
)
;
;
; This is the relation between elements of a performance model, which are various dependencies
;
(deftemplate PerformanceModelDependency
    (slot type (type SYMBOL)) ;type of the dependency between elements of the performance model
    (slot parent (type SYMBOL))
    (slot child (type SYMBOL))
)
;
; A unit of concurrency is the element in the performance model. It contains responsibilities and has properties assigned, such as "execution time"
;
(deftemplate UnitOfConcurrency
    (slot name (type STRING)) ;The name of the quality attribute model element
    (slot source (type SYMBOL))
)
;
; This parameter defines the execution time of the element it is assigned to.
;
(deftemplate P_ExecutionTime
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)


(deftemplate P_MutualExclusion
    (slot value (type SYMBOL))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)

(deftemplate P_SharedResourceAsked
    (slot value (type SYMBOL))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)

(deftemplate Tactic_Rejected
    (slot reasoningFramework) ;The reasoning framework 
    (slot id_of_fact_tactic_applied_to)
    (slot tactic_name) 
    (slot id_of_scenario)
)
