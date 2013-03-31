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

;    (import ApplyRMATactics OrderedFact TacticsAvailable)
;    (import MAIN deftemplate AskQuestion)
;    (import PerformanceReasoningFrameworks deftemplate P_ExecutionTime)
;    (import MAIN deftemplate ResponsibilityRefinementRelation)
;    (import MAIN deftemplate Scenarios)
;    (import MAIN deftemplate TranslationRelation)
;    (import MAIN deftemplate Responsibilities)
;    (import MAIN deffunction GetPropertyFact)
;    (import MAIN deffunction GetPropertyValue)
;    (import MAIN deffunction HasProperty)
;    (import MAIN deffunction DuplicateFactWithProperties)
;    (import MAIN deffunction GetPropertiesToOwner)
;    (import PerformanceReasoningFrameworks deftemplate PerformanceModelDependency)
;    (import MAIN deftemplate ResponsibilityToUnitOfConcurrencyRelation)
;    (import MAIN deftemplate P_AnalysisResult)
;    (import Configuration deffacts MediumPriority)
;    (import MAIN deffunction QueryFactbase)
;    (import MAIN deffunction GetSlotvalueFromFacts)
;    (import MAIN deffunction NumberOfFacts)
;    (import Planner deftemplate TryRMATacticResult)
;    (export defquery queryExecTimeforResp)
;    (export deftemplate Task)
;    (export deftemplate SubTask)
; Dependencies to modules: ApplyRMATactics.clp,MAIN.clp,PerformanceReasoningFrameworks.clp,Configuration.clp,Planner.clp
(provide RMAModel)
(require MAIN)
(require PerformanceReasoningFrameworks)
(require Configuration)
(defmodule RMAModel)

(deftemplate TacticApplied)
(deftemplate OverUtilizationDetected)

(deftemplate Task
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
(deftemplate SubTask
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
(deftemplate P_Latency
    (slot utilization (type STRING))
    (slot value (type STRING))
    (slot time_blocked (type STRING))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status  (type STRING)) ;Indicators for the property:nil, conflict
)
(defglobal ?*task-ID* = 0 ?*task-num* = 0 ?*subtask-num* = 0)
(defquery queryChildrenExecTimeforUOC
    (declare (variables ?root))
    (MAIN::ResponsibilityToUnitOfConcurrencyRelation (unitOfConcurrency ?root) (responsibility ?c))
    (PerformanceReasoningFrameworks::P_ExecutionTime (owner ?c) (value ?v))
)
(defquery queryExecTimeforResp
    (declare (variables ?resp))
    (PerformanceReasoningFrameworks::P_ExecutionTime (owner ?resp) (value ?v))
)
(defquery queryRespsWithNoExecParam
    ?r <- (MAIN::Responsibilities)
    (not (MAIN::ResponsibilityRefinementRelation (parent ?r)))
    (not (PerformanceReasoningFrameworks::P_ExecutionTime (owner ?r) (value ?v)))
)
(defquery queryChildrenExecTimeforResp
    (declare (variables ?root))
        (MAIN::ResponsibilityRefinementRelation (parent ?root) (child ?c))
        (PerformanceReasoningFrameworks::P_ExecutionTime (owner ?c) (value ?v))
)

; Queries defined to support the parent-resp-allocated function:
(defquery queryResponsibilityParentRelations
	(declare (variables ?resp))
		(MAIN::ResponsibilityRefinementRelation (child ?resp))
)
(defquery queryUOCRelationsforResp
	(declare (variables ?resp))
		(MAIN::ResponsibilityToUnitOfConcurrencyRelation (responsibility ?resp))
)

(deffunction create-list-of-resps-with-no-exectime ()
        (bind ?it (run-query queryRespsWithNoExecParam))
        (bind ?resplist (create$))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 1))
            (bind ?resplist (insert$ ?resplist 1 (fact-id (call ?fact getFactId))))
        )
        (return ?resplist)
)
(deffunction get-children-exec-time-for-uoc (?parent)
        (bind ?sumexec 0)
        (bind ?it (run-query queryChildrenExecTimeforUOC ?parent))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 2))
            (bind ?exec (fact-slot-value ?fact value))
            (bind ?sumexec (+ ?sumexec ?exec))
        )
        (return ?sumexec)
)
(deffunction print-children-for-uoc (?parent)
        (bind ?sumexec 0)
        (bind ?it (run-query queryChildrenExecTimeforUOC ?parent))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 2))
            (bind ?resp (fact-slot-value ?fact owner))
            (bind ?exec (fact-slot-value ?fact value))
            ;(printout t "Responsibility " ?resp " with execution time "?exec crlf)
        )
        (return nil)
)
(deffunction check-for-new-execvals ($?resplist)
    	(foreach ?resp ?resplist
    		(if (neq (QueryFactbase "(PerformanceReasoningFrameworks::P_ExecutionTime (owner ?1))" ?resp) nil)
    		 then (return true)
    		)
    	)
    	(return false)
    
)

; This is a recursive function that returns boolean TRUE or FALSE based on whether
; the responsibility passed as a parameter is allocated to a task, or if any of its
; parents are allocated to a task.
;
(deffunction parent-resp-allocated (?resp)
    	(bind ?returnval FALSE)
    	(bind ?iterator (run-query queryResponsibilityParentRelations ?resp))

    	;(printout t "In parent-resp-allocated" crlf)
    	;(printout t "?resp = " ?resp crlf)
    	
    	(if (?iterator hasNext)
    	 then
    	 	(while (?iterator hasNext)
            	(bind ?token (call ?iterator next))
            	(bind ?relation (call ?token fact 1))
    
    	 	    ;(printout t "relation = " ?relation crlf)
    	 	    ;(printout t "parent = " (fact-slot-value ?relation parent) crlf)
    
    			(bind ?UOCiterator (run-query queryUOCRelationsforResp (fact-slot-value ?relation parent)))
    	 		(if (?UOCiterator hasNext)
    	 		 then
		    	    (printout t "found UOC - returning TRUE" crlf)
     				(return TRUE)
    	 		 else
					;(printout t "recursive call in parent-resp-allocated with " (fact-slot-value ?relation parent) crlf)
    				(bind ?returnval (parent-resp-allocated (fact-slot-value ?relation parent)))
                    (if (eq ?returnval TRUE)
                     then (return TRUE)
                    )
                )
            )	 	
        )
    (return ?returnval)
)

(deffunction check-for-missing-facts ($?factlist)
    	(foreach ?fact ?factlist
    		(if (not (fact-exists ?fact))
    		 then (return TRUE)
    		)
    	)
    	(return FALSE)
    
)
(deffunction get-children-exec-time-for-resp (?parent)
        (bind ?sumexec 0)
        (bind ?it (run-query queryChildrenExecTimeforResp ?parent))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 2))
            (bind ?exec (fact-slot-value ?fact value))
            (bind ?sumexec (+ ?sumexec ?exec))
        )
        (return ?sumexec)
    
)
(deffunction print-children-for-resp (?parent)
        (bind ?it (run-query queryChildrenExecTimeforResp ?parent))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 2))
            (bind ?resp (fact-slot-value ?fact owner))
            (bind ?exec (fact-slot-value ?fact value))
            ;(printout t "   child responsibility " ?resp " with execution time " ?exec crlf)
        )
        (return nil)
    
)


(set-current-module Configuration)
;
; Configuration values for RMA reasoning framework
;
(deffacts RMAConfig
    (MediumPriority 500) ;Marks the mid level of task priorities
)


