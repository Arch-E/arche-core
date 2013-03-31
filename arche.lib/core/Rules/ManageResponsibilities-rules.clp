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

; Dependencies to modules: MAIN.clp,Planner.clp
(defmodule ManageResponsibilities)
(require MAIN)
(require Planner)
;(require Design)
;(require Configuration)
(ModuleOption Debug)



/* TODO: Need to move to modifiability RF
(defrule CheckInvalidDependencyToParentInterface
"Checks if there are responsibilities that are decomposed, where the parent has a dependency to another responsibility"
    (MAIN::ResponsibilityRefinementRelation (parent ?r1))
    (or
        ?d <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?r1) (child ?r2))
        ?d <- (MAIN::ResponsibilityToResponsibilityRelation (parent ?r2) (child ?r1)))
     (not (exists (MAIN::ResponsibilityRefinementRelation (parent ?r1) (child ?r2))))
     ?rmr <- (Design::ResponsibilityToModuleRelation (parent ?mo) (child ?r1))
     (Design::RealizeRelation  (parent ?mo) (childType interface) )
     (not (exists (MAIN::AskQuestion (questionId interfaceMapping) (parent ?r1))))
=>
    (bind ?rc (GetAllLeafResps ?r1))
    (bind ?modules (create$))
    (foreach ?leaf ?rc
       (foreach ?rmr1 (QueryFactbase "(Design::ResponsibilityToModuleRelation (child ?1))" ?leaf)
            (bind ?modules (insert$ ?modules 1 (fact-slot-value ?rmr1 parent) ))))
   (assert  (MAIN::AskQuestion (questionId interfaceMapping) (parent ?r1) (affectedFacts ?rc) (parameters ?modules) (options ?rc) (default (string-of-numbers (length$ ?rc)))))  
)
*/

/* TODO: Need to move to modifiability RF
(defrule MoveRealizeRelationToChildren
    ?q <- (MAIN::AskQuestion (questionId interfaceMapping) (parent ?r) (options $?rc) (answer ?ans) (answerAvailable true) )
=>
     (bind ?num (explode$ ?ans))
     (bind ?count 1)
     (bind ?parentMod (GetResponsibilityModule ?r))
     (bind ?pmRR nil)
   

     (foreach ?rr (QueryFactbase "(Design::RealizeRelation (parent ?1))" ?parentMod )
        (bind ?pmRR ?rr)
     )


     (foreach ?leaf ?rc
       (bind ?childMod (GetResponsibilityModule ?leaf))
       (foreach ?in ?num 
        ;(printout t ?count crlf)
        ;(printout t ?in crlf)

        (if (= ?in ?count) then
         (assert (Design::RealizeRelation (childType interface)(source ArchE) (parent ?childMod ) (child (fact-slot-value ?pmRR child)) ))     
        )
       )
        (bind ?count (+ ?count 1))

     )
      
      (if (= (length$ ?num) 0) then
        (bind ?interface (fact-slot-value ?pmRR child)) 
        (retract ?interface)
      )
      (retract ?pmRR ) 
      (retract ?q) 
     
	(assert (Planner::C_RequirementsChanged))
)
*/

;
; End of rule set CheckInvalidDependencyToParent
;------------------------------------------------------------
;============================================================
; Start of rule set ManageResponsibilityRelations
;
(defrule AddDependencyRelation
"User specifies a data flow between responsibilities that translates to a dependency relation"
    ?c <- (Planner::C_AddResponsibilityRelation (parent ?pa) (child ?ch) (type dependency))
    (not (or (MAIN::ResponsibilityToResponsibilityRelation (parent ?pa) (child ?ch))
        (MAIN::ResponsibilityToResponsibilityRelation (parent ?ch) (child ?pa))))
=>
    (assert (MAIN::ResponsibilityToResponsibilityRelation (source User) (parent ?pa) (child ?ch)))
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))
)
(defrule AddRefinementRelation
"User specifies a decomposition between responsibilities"
    ?c <- (Planner::C_AddResponsibilityRelation (parent ?pa) (child ?ch) (type Contains))
    (not (MAIN::ResponsibilityRefinementRelation (parent ?pa) (child ?ch)))
=>
    (assert (MAIN::ResponsibilityRefinementRelation (source User) (parent ?pa) (child ?ch)))
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))
)
(defrule AddSequenceRelation
"User specifies a sequence between responsibilities"
    ?c <- (Planner::C_AddResponsibilityRelation (parent ?pa) (child ?ch) (type Sequence))
    (not (MAIN::SequenceRelation (parent ?pa) (child ?ch)))
=>
    (assert (MAIN::SequenceRelation (source User) (parent ?pa) (child ?ch)))
    (retract ?c)
	(assert (Planner::C_RequirementsChanged))
)
(defrule DeleteDependencyRelation
"User removes a dependency relation"
    ?c <- (Planner::C_DeleteResponsibilityRelation (applyTo ?pa))
    (test (fact-exists ?pa))
=>
    (retract ?c ?pa)
	(assert (Planner::C_RequirementsChanged))
)
;
; End of rule set ManageResponsibilityRelations
;------------------------------------------------------------

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


(defrule DeleteResponsibility
;    (declare (auto-focus TRUE))
    ?c <- (Planner::C_DeleteResponsibility (applyTo ?re))
    (not (exists (MAIN::ResponsibilityRefinementRelation (parent ?re) )))
    (not (exists (MAIN::ResponsibilityRefinementRelation (child ?re) )))

=>
    (bind ?trr (create$ ))
   
    ;(bind ?trr (union$ ?trr ( GetPropertiesToOwner ?re)))
    ;(if (RemovePropertiesForFact ?re) then
       ;(printout t "property facts were deleted" crlf)

    ;)
    (bind ?trr (union$ ?trr (QueryFactbase "(MAIN::TranslationRelation (child ?1))" ?re)))
    (bind ?trr (union$ ?trr (QueryFactbase "(MAIN::ResponsibilityToResponsibilityRelation (parent ?1))" ?re)))
    (bind ?trr (union$ ?trr (QueryFactbase "(MAIN::ResponsibilityToResponsibilityRelation (child ?1))" ?re)))
    ;(printout t "before query delete resp" crlf)
     ;(printout t (length$ ?trr ) crlf)

;    (bind ?trr (union$ ?trr (QueryFactbase "(ModifiabilityReasoningFrameworks::Node_Responsibility (id ?1))" (call ?re getFactId))))  
     ;(printout t "after query delete resp" crlf)
      ;(printout t (length$ ?trr )crlf)

    (bind ?trr (union$ ?trr (QueryFactbase "(MAIN::AskQuestion (answerAvailable nil))" )))  
    
    (foreach ?tr ?trr (retract ?tr))
    (retract ?c)
    (retract ?re)
    ;(assert (Planner::T_MakeAnotherRound))

	(assert (Planner::C_RequirementsChanged))
)

(defrule ResponsibilityRefinementConsistency
    (declare (auto-focus TRUE))
    ?c <- (Planner::C_DeleteResponsibility (applyTo ?re))
    ?rrr <- (MAIN::ResponsibilityRefinementRelation (parent ?re) (child ?r1))
    (not (exists (MAIN::ResponsibilityRefinementRelation (child ?re) )))

=>
    (retract ?rrr)   
	(assert (Planner::C_RequirementsChanged))
)

(defrule ResponsibilityRefinementConsistency_v2
    (declare (auto-focus TRUE))
    ?c <- (Planner::C_DeleteResponsibility (applyTo ?re))
    ?rrr <- (MAIN::ResponsibilityRefinementRelation (parent ?re) )
    (MAIN::ResponsibilityRefinementRelation (parent ?r1) (child ?re) )

=>
      (modify ?rrr (parent ?r1))
	(assert (Planner::C_RequirementsChanged))
)

(defrule ResponsibilityRefinementConsistency_v3
    (declare (auto-focus TRUE))
    ?c <- (Planner::C_DeleteResponsibility (applyTo ?re))
    ?rrr <- (MAIN::ResponsibilityRefinementRelation (child ?re))
    (not (exists (MAIN::ResponsibilityRefinementRelation (parent ?re) )))

=>
    (retract ?rrr)
	(assert (Planner::C_RequirementsChanged))    
)

;
; Checks wether there is a responsibility that only has a description and no name, then it takes the description as name
;
(defrule ResponsibilitiesWithoutName
    	?r <- (MAIN::Responsibilities (name nil) (description ?d&~nil))
=>
    (modify ?r (name ?d))
	(assert (Planner::C_RequirementsChanged))
)


;
; Save the whole requirements if any change is made to them, say, by adding a responsibility,
; to the persisted fact base. The saved requirements will be used by external reasoning frameworks
; to update their own requirements of interest, which is a subset of the saved requiremetns.
;
(defrule SaveRequirementsIfChanged
	(declare (salience -20))
	?rc <- (Planner::C_RequirementsChanged)
=>
;    (DeleteFactSet ?*projectfile*)
;    (SaveFactSet "Project" ?*projectfile* "project")
    (printout t "------------SaveRequirementsIfChanged ProjectName=" ?*projectfile* crlf)
    (retract ?rc)
    
	; ensure data consistency 
	(assert (MAIN::T_CheckConsistency))
	(focus ConsistencyCheck)    
)

