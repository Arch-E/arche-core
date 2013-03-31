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

(provide MAIN)
(set-current-module MAIN)
/* TODO: Has dependencies on RFs
(deffacts PropertyModuleAssignments
    (PropertyModuleAssignment P_ExecutionTime PerformanceReasoningFrameworks)
    (PropertyModuleAssignment P_Scheduler SchedulingTheory)
    (PropertyModuleAssignment P_Processors SchedulingTheory)
    (PropertyModuleAssignment P_ArrivalPeriod SchedulingTheory)
    (PropertyModuleAssignment P_Latency RMAModel)
    (PropertyModuleAssignment P_ProbabilityOutgoing ModifiabilityReasoningFrameworks)
    (PropertyModuleAssignment P_CostOfChange ModifiabilityReasoningFrameworks)
    (PropertyModuleAssignment P_PreparedForChange ModifiabilityReasoningFrameworks)
    (PropertyModuleAssignment P_EncapsulationLevel ModifiabilityReasoningFrameworks)
    (PropertyModuleAssignment P_ProbabilityIncoming ModifiabilityReasoningFrameworks)
    (PropertyModuleAssignment P_HMLpriority MAIN)
    (PropertyModuleAssignment P_AnalysisResult MAIN)
)
*/

/* Has dependency on modifiability RF
;
; Status flags That describe outstanding activities ArchE or the User has to perform
;
(deffacts StatusFlags
    (StatusFlag	AskForName 1)
    (StatusFlag AskForValue 2)
)
*/
;
; Responsibilities of a system. Can be assigned to any element using containers
;
(deftemplate Responsibilities
    (slot id (type SYMBOL))
    (slot name (type STRING))
    (slot description (type STRING))
    (slot source (type SYMBOL))
    (slot status (type INTEGER))
)
;
; Contains the description and classification of the scenarios the system has to fulfill
;
(deftemplate Scenarios
    (slot id (type STRING)) ;Contains the identification of a scenario
    (slot description (type STRING))
    (slot quality (type SYMBOL)) ; Name of the quality (actually scenariotype's tID) 
	(slot reasoningFramework (type SYMBOL)) ; the name of the reasoing framework
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
;
; The sequence relation describes sequences of responsibilities for a specific scenario. The responsibility referred to by the parent attribute has to execute before the responsibility referred to by the child attribute.
;
(deftemplate SequenceRelation
    (slot scenario (type OBJECT)) ;Scenario that defines the specific sequence
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; One ArchE element (parent) is translated into another ArchE element (child). Examples are:
; - Scenario -> Responsibility
; - Function -> Responsibility
;
(deftemplate TranslationRelation
    (slot parentType (type SYMBOL)) ;Scenario or Function
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; This relation is used for refineiment of functions
;
(deftemplate FunctionRefinementRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; Describes a bi-directional dependency relation between two responsibilities
;
(deftemplate ResponsibilityToResponsibilityRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; Describes dependencies between leaf responsibilities. Those are the ones that
; are of interest for design
;
;(deftemplate LeafResponsibilityDependencyRelation
;    (slot id (type OBJECT)) 
;    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
;    (slot parent (type OBJECT)) ;Id of the parent in this relation
;    (slot child (type OBJECT)) ;Id of the child in this relation
;    (slot incoming (type FLOAT)) ; Incoming probability
;    (slot outgoing (type FLOAT)) ; Outgoing probability
;)
;
; Defines a data item that is exchanged between responsibilities
;
;(deftemplate DataItems
;    (slot name (type STRING)) ;The name of the data item
;    (slot description (type STRING)) ;A description of this item
;    (slot source (type SYMBOL)) ;The origin of the input ArchE/User
;)
;
; Describes a directed dataflow relation between two responsibilities
;
;(deftemplate DataFlow
;    (slot id (type STRING)) ;Unique id for a relation
;    (slot source (type SYMBOL)) ;The origin of the input ArchE/User
;    (slot producer (type OBJECT)) ;Id of the producer of the data item
;    (slot consumer (type OBJECT)) ;Id of the consumer of the data item
;    (slot dataitem (type OBJECT)) ;Id of the data item being exchanged
;)
;
; Assigns a responsibility to a unit of concurrency
;
;(deftemplate ResponsibilityToUnitOfConcurrencyRelation
;    (slot responsibility (type OBJECT)) ;the responsibility in the relation
;    (slot unitOfConcurrency (type OBJECT)) ;the unit of concurrecny in the relation
;)
;
; High/medium/low value that defines the priority of the owner
;
(deftemplate P_HMLpriority
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; Function was translated into responsibility
;
(deftemplate P_Function_Responsibility_Created
    (slot value (type SYMBOL))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; Describes the refinement of responsibilities
;
(deftemplate ResponsibilityRefinementRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; A piece of functionality the system has to proved. Functionalities are translated into responsibilities that are assigned to elements in the design.
;
(deftemplate Function
    (slot id (type STRING)) ;Identification of the function. This is not a fact id. It is some external identification of a function.
    (slot description (type STRING)) ;Description of the function
)

;
; This is a question for the user. A specialization might be required.
;
(deftemplate AskQuestion
    (slot id (type SYMBOL)) ; id of this fact
    (slot questionId (type SYMBOL)) ;id of the question text to use
    (slot questionGroup (type SYMBOL)) ;the ID to enable grouping
	(slot reasoningFramework (type SYMBOL)) ; the name of the reasoing framework that issued the question
	(slot searchStep (type INTEGER))	; the search step number
	(slot priority (type INTEGER))	; the priority in which the questions should be displayed. 1=highest
    (slot parent (type OBJECT)) ;The element that triggered the question
    (multislot affectedFacts) ;List of facts where this question should be shown
    (multislot parameters)
    (multislot options) ;objects among which the user can choose
    (multislot default) ;the default answers
    (multislot answer) ;the answers the user gave
    (slot answerAvailable (type SYMBOL)) ;Describes if an answer from the user is available
    (slot log (type SYMBOL)) ;When set the Q/A fact will be logged and then removed
)
;
; Contains the result of a specific analysis and is assigned to a scenario
;
(deftemplate P_AnalysisResult
    (slot quality (type SYMBOL)) ;The quality for which the analysis was done
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework that produced this result
    (slot isSatisfied (type SYMBOL))
    (slot utility (type FLOAT)) ; 1 if fully satisfied, 0 if not satisfied, any other value, partially satisfied
    (slot value (type FLOAT))
    (slot oldValue (type FLOAT)); the evaluation result that existed previously
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
(deftemplate ScenarioRefinementRelation
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
(deftemplate T_CheckConsistency (declare (ordered TRUE)))
(deftemplate Tactic_Rejected
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework 
    (slot id_of_fact_tactic_applied_to (type SYMBOL))
    (slot tactic_name (type SYMBOL)) 
    (slot id_of_scenario (type SYMBOL))
)
;
; Global variables in MAIN
;
(defglobal ?*QueryList* = (new java.util.HashMap))

(defglobal ?*QuestionFile* = "")

;
; This function rounds a floating value to th enearest 2 decimal places
;
(deffunction round2 (?number)
	(return (/ (round(* ?number 100)) 100))  
)
;
; Delivers a string beginning with the provided string and a unique number added. Should be used to produce unique id's.
;
(deffunction unique-number (?st)
    (bind ?str (gensym*))
    (return (str-cat ?st (sub-string 4 (str-length ?str) ?str)))
)
(deffunction string-of-numbers (?c)
    (bind ?count 1)
    (bind ?def "")
    (while (<= ?count ?c) do
        (bind ?def (str-cat ?def ?count " "))
        (bind ?count (+ ?count 1)))
    (return (sub-string 1 (- (str-length ?def) 1) ?def))
)
;
; Function truncates a given string and appends '...' if string was trancated
; the returns string inclusiv the ellipsis is of the given length
;
(deffunction ellipsis (?string ?length)
    (if (> (str-length ?string) ?length) then
        (return (str-cat (sub-string 1 (- ?length 3) ?string) "...")) else
        (return ?string))
)
;
; Function search a string from the end from the occurence of the given character
; returns the position of the character or zero in case the character is not found
;
(deffunction find-from-end (?string ?char)
    (bind ?index (- (str-length ?string) 1))
    (while (and (> ?index 1) (neq (sub-string ?index ?index ?string) ?char)) do
        (bind ?index (- ?index 1)))
    (return ?index)
)
;
; Checks if a given fact-id is still pointing a an existing fact. Returns true if fact exists
;
(deffunction fact-exists (?fid)
; If ?fid is not an integer, assume it is a fact and try to get its fact ID
; If it is not a fact, an exception will be thrown and the function will return FALSE
    (if (not (integerp ?fid))
         then
             ;(printout t "before try " crlf)
             (try (bind ?fid (call ?fid getFactId))
             catch
                ;(printout t "after catch" crlf)

                (return FALSE)
              )
              ;(printout t "try ok" crlf)

    )
; Calling fact-id with an integer that does not match a fact in the fact base
; will throw an exception, returning FALSE.
; If the call to fact-id succeeds, this functio will return TRUE.
    (try
        (fact-id ?fid)
        (return TRUE)
    catch
        (return FALSE)
    )
)
;
; This function returns a list of fact-ids of all the facts whose name contain the given string.
; The list is returned as a multi field value
;
(deffunction ListFacts (?string)
    (bind ?it (call (engine) listFacts))
    (bind ?fi (create$))
    (bind ?ind 1)
    (while (?it hasNext)
        (bind ?fact (call ?it next))
        (if (str-index ?string (?fact getName)) then
            (bind ?fi (insert$ ?fi ?ind (fact-id (call ?fact getFactId))))
            (bind ?ind (+ ?ind 1)))
    )
    (return ?fi)
)
;
; This function returns a list belonging either to the current module (has focus),
; the given module or if * is given as parameter, then for all modules
;
(deffunction get-fact-list ($?string)
    (bind ?it (call (engine) listFacts))
    (bind ?fi (create$))
    (bind ?ind 1)
    (if (= (length$ ?string) 0) then
        (bind ?module (get-focus))
    else
        (bind ?module (nth$ 1 ?string)))
    (while (?it hasNext)
        (bind ?takeit FALSE)
        (bind ?fact (call ?it next))
        (if (= (str-compare ?module "*") 0) then
            (bind ?takeit TRUE)
        else
            (if (str-index (str-cat ?module "::") (?fact getName)) then
                (bind ?takeit TRUE)))
        (if ?takeit then
            (bind ?fi (insert$ ?fi ?ind (fact-id (call ?fact getFactId))))
            (bind ?ind (+ ?ind 1)))
    )
    (return ?fi)
)
(deffunction roundValue (?val $?dig)
    (if (= (length$ ?dig) 0) then
        (bind ?fac 100.0)
     else
        (bind ?fac (** 10.0 (nth$ 1 ?dig))))
    (return (/ (round(* ?val ?fac)) ?fac))
)
;
; Interprets the first parameter as the content of a query, without having to explicitely define the query
; Parameters in the query are referred to as ?1 ?2 ?3 and so on, meaning that for ?1 take the first value
; from the second parameter given, ?2 take the seconde value and so on. For example:
; If the first parameter is something like this: (MAIN::TranslationRelation (parent ?1))
; and the second parameter is a fact id of a scenario, then this function will create a query that looks
; for TranslationRealtions where the given scenario is the parent, runs that query and returns a multislot
; value with the fact ids of those TranslationRelations
; 
; NOTE: Do not use this function or any functions that call this function in the LHS (left-hand side) of a rule.
; This function has side effects that change the fact base by changing a global object *QueryList* and defining new queries. 
; Functions that have side effects should not be executed in rule LHS's because they can cause the Jess engine
; to crash and provide unhelpful error messages that make them difficult to track down. 
; I discovered this when writing the parent-resp-allocated function in the RMAModel.clp file.
;  - Charles Shelton, March 3, 2005
;
(deffunction QueryFactbase (?qu $?pa)
        (bind ?fi (create$))
        (bind ?qn (?*QueryList* get ?qu))
        (if (eq ?qn nil) then
            (bind ?qn (sym-cat (get-focus) "::" (gensym*)))
            (bind ?pn 1)
            (bind ?qd (sym-cat "(defquery " ?qn " (declare (variables"))
            (foreach ?c ?pa (bind ?qd (sym-cat ?qd " ?" ?pn)) (bind ?pn (+ ?pn 1)))
            (bind ?qd (sym-cat ?qd "))" ?qu ")"))
;(printout t "Query: " ?qd crlf)
            (eval ?qd)
            (?*QueryList* put ?qu ?qn))
        (bind ?qc "(run-query ?qn")
        (bind ?pn 1)
        (foreach ?c ?pa (bind ?qc (sym-cat ?qc " (nth$ " ?pn " ?pa)")) (bind ?pn (+ ?pn 1)))
        (bind ?qc (sym-cat ?qc ")"))	
        (bind ?it (eval ?qc))
        (while (?it hasNext)
            (bind ?token (call ?it next))
            (bind ?fact (call ?token fact 1))
            (if (fact-exists (call ?fact getFactId)) then
    			(bind ?fi (insert$ ?fi 1 (fact-id (call ?fact getFactId))))))
        (return ?fi)
)
;
; This function queries the factbase using the given string (second parameter) and returns the content of
; a given slot (first parameter) from the resulting facts. 
;
(deffunction GetSlotvalueFromFacts (?slotname ?qu $?pa)
    (bind ?fi (QueryFactbase ?qu ?pa))
    (bind ?nfi (create$))
    (foreach ?fa ?fi (bind ?nfi (insert$ ?nfi 1 (fact-slot-value ?fa ?slotname))))
    (return ?nfi)
)
;
; This function returns the number of facts the result from the given query
;
(deffunction NumberOfFacts (?qu $?pa)
    (bind ?fi (QueryFactbase ?qu ?pa))
    (return (length$ ?fi))
)
;
; Returns the fact-ids of all the responsibilities that depend on the given scenario, that is have a translation relation
;
(deffunction GetDependentResponsibilities (?sc)
    (bind ?rr (GetSlotvalueFromFacts child "(TranslationRelation (parent ?1))" ?sc))
    (return ?rr)
)
;
; Returns the fact-ids of all the responsibilities that are children to the given responsibility
;
(deffunction GetChildResponsibilities (?rp)
    (bind ?rr (GetSlotvalueFromFacts child "(ResponsibilityRefinementRelation (parent ?1))" ?rp))
    (if (= (length$ ?rr) 0) then (return (create$ ?rp)))
    (bind ?subrs (create$))
    (foreach ?r ?rr
        (bind ?subrs (insert$ ?subrs 1 (GetChildResponsibilities ?r)))
        (if (> (length$ ?subrs) 1) then
            (bind ?rr (complement$ (create$ ?r) ?rr))
            (bind ?rr (union$ ?rr ?subrs))))
    (return ?rr)
)
;
; Returns the fact-id of a given property that belongs to the given owner
;
(deffunction GetPropertyFact (?prop ?owner)
    (bind ?rr (QueryFactbase (str-cat "(" ?prop " (owner ?1))") ?owner))
    (if (> (length$ ?rr) 0) then
        (bind ?rr (nth$ 1 (QueryFactbase (str-cat "(" ?prop " (owner ?1))") ?owner)))
     else
        (bind ?rr nil))
     (return ?rr)
)
;
; Returns the value slot of a given property that belongs to the given owner
;
(deffunction GetPropertyValue (?prop ?owner)
    (bind ?p (GetPropertyFact ?prop ?owner))
    (if (eq ?p nil) then
        return 0
    else
        return (fact-slot-value ?p value))
)
;
; Checks if the given fact has the requested property. Returns FALSE if not, otherwise returns the fact id of the property fact
;
(deffunction HasProperty (?prop ?owner)
    (bind ?fi (GetPropertyFact ?prop ?owner))
    (if (eq ?fi nil) then
        (return FALSE)
    else
        (return ?fi))
)
;
; Duplicates the given fact including its dependent properties
; The first parameter is the fact to duplicate and the second parameter
; are one or more slotname value pairs as required for the Jess function "duplicate"
; Returns the fact-id of the newly created fact
;
(deffunction DuplicateFactWithProperties (?fact $?values)
    (bind ?newfact (duplicate ?fact ?values))
    (bind ?own (create$ owner ?newfact))
    (bind ?props (ListFacts "::P_"))
    (foreach ?prop ?props
        (if (eq (fact-slot-value ?prop owner) ?fact) then
            (duplicate ?prop ?own)))
    (return ?newfact)
)
(deffunction GetPropertiesToOwner (?owner)
    (bind ?it (call (engine) listFacts))
    (bind ?fi (create$))
    (bind ?ind 1)
    (while (?it hasNext)
        (bind ?fact (call ?it next))
        (if (eq (sub-string 1 2 (?fact getName)) "P_") then
            (if (eq (fact-slot-value ?fact owner) ?owner) then
                (bind ?fi (insert$ ?fi ?ind (fact-id (call ?fact getFactId))))
                (bind ?ind (+ ?ind 1))))
    )
    (return ?fi)
)

;
; This function returns a list of fact-ids of all the leafs of a given responsibility.
; 
;
(deffunction GetAllLeafResps (?resp)
  
    (bind ?children (create$)) 
    (bind ?children (create$ ?children (QueryFactbase "( MAIN::ResponsibilityRefinementRelation  (parent ?1) )" ?resp)
    ))   
    (bind ?leafs (create$))  
       (if (= (length$ ?children) 0) then
             ;(printout t "***resp should be leaf:" crlf)
             ;(printout t ?resp crlf)
             (bind ?leafs (create$ ?leafs ?resp))  
       )
   
       (if (> (length$ ?children) 0) then
           (foreach ?fact ?children
             (bind ?leafs (create$ ?leafs ( GetAllLeafResps (fact-slot-value ?fact child)) ))
           )
       )
    (return ?leafs )
)
;

(deffunction RemovePropertiesForFact (?owner)
    (bind ?myBool FALSE)
    (bind ?props (ListFacts "::P_"))
    (foreach ?prop ?props
        (if (eq (fact-slot-value ?prop owner) ?owner) then
          (retract ?prop)
          (bind ?myBool TRUE)

        )
    )
    (return ?myBool)
)

/*
(deffunction GetResponsibilityModule (?resp)
    (bind ?mod nil)
    (foreach ?dr (QueryFactbase "(Design::Responsibility (owner ?1))" ?resp)
        (foreach ?rmr (QueryFactbase "(Design::ResponsibilityToModuleRelation (child ?1))" ?dr)
                (bind ?mod (fact-slot-value ?rmr parent))

         )
     )

    (return ?mod)
)
*/

(deffunction Properties (?owner)
    (bind ?fi (ListFacts "P_"))
    (bind ?pf (create$))
    (foreach ?f ?fi
        (if (eq ?owner (fact-slot-value ?f owner)) then
        (bind ?pf (insert$ ?pf 1 ?f))))
    (return ?pf)
)
