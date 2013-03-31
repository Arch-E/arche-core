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

/*******************************************************************************
 * The Seeker controls the reasoning about the current architecture. Interacts
 * with the reasoning frameworks to discover problems, possible alternative
 * solutions, and trade-off conditions.
 * So far the Seeker�s responsibilities are:
 * - Defining the scope (set of scenarios) for exploring solutions
 * - Interacting with reasoning frameworks to evaluate the current architecture
 * - Interacting with reasoning frameworks to create candidate architectures
 * - Assessing candidate architecture
 * - Searching of better solutions with possibly interacting with external tools such as SAT solvers
 * - Recognizing trade-off situations
 *******************************************************************************/
 
/* Now the require statements of all the file required by this module */
(require MAIN)
(require Seeker)
(require Configuration)
(defmodule SeekerEvaluateCandidates)


/******************************************************************************
 * Now the options part. The following options are available:
 * (ModuleOption Debug) - enables debugging facility
 * (ModuleOption Events Enter Exit) - enables the OnEnter and/or the OnExit event
 * (ModuleOptions Sequence A B C) - enables the sequence of phases (1st A, then B, and last C)
 *******************************************************************************/
(ModuleOption Debug)

/******************************************************************************
 * Now the questions part. For every question asked in this module provide the
 * following information:
 * (bind ?qu (new ArchEQuestion XXX)) - creates a new question instance with the questionID XXX
 * (?qu setConditions listOfStrings) - sets the CE conditions for when to ask this question
 * (?qu setParent "?sc") - sets the string that identifies the parent fact for this question
 * (?qu setDataCollection listOfStrings) - sets the jess statements to collect data used in the question
 * (?qu setAffectedFacts "(create$ ?sc ?rr)") - sets the jess command that delivers the affected facts
 * (?qu setParameters "?rr") - sets the jess command that delivers the parameters
 * (?qu setDefaults "yes") - sets the jess command that delivers the default values
 * (?qu setOptions "2") - sets the jess command that delivers the option values
 * (?qu setActions "yes" listOfStrings) - sets the jess statements executed if users answer equal first parameter
 *                                      - many of those statements can follow according to different answers
 * (?qu generate (engine)) - generates the set of rule that will ask the question and react to the answers
 *******************************************************************************/

 /******************************************************************************
 * The rest of the module is now for the rules
 *******************************************************************************/
;
; First step is to create the candidate architecture.
; This activates the reasoning framework according to the reasononmg framework mentioned
; in the ApplySuggestedTactic fact
;
(defrule CreateCandidateArchitecture
    (declare (salience 10))
    (Seeker::RFCommand (command ApplySuggestedTactic) (parameter1 ?id))
    (Seeker::ApplySuggestedTactic (scenario ?sc) (reasoningFramework ?rf) (tacticId ?id) )
    (Configuration::ActiveReasoningFrameworks ?ev ? Tactical)
	(test (eq (str-index ?rf ?ev) 1))
=>
    (printout t "------------CreateCandidateArchitecture!!!!"  ?rf " " ?ev " " ?id crlf)
	(focus ?ev)	
)

(defrule FillTacticIntoResult
;    (Seeker::RFCommand (command ApplySuggestedTactic) (parameter1 ?id))
	?sc <- (MAIN::Scenarios (measureValue ?desVal))
	?tsc <- (MAIN::Scenarios)
;	?er <- (Seeker::EvaluationResult (tacticId nil) (reasoningFramework nil) (result ?curVal))
    (Seeker::ApplySuggestedTactic (tacticId ?id) (scenario ?tsc) (reasoningFramework ?rf) (tactic ?ta) (tacticDescription ?td) (haveResults FALSE))
	?er <- (Seeker::EvaluationResult (scenario ?sc) (result ?curVal) (tacticId nil) (reasoningFramework nil) (nresult -1.0))
	(Seeker::SearchStep ?step)
=>
;	(printout t "------------FillTacticIntoResult !!!!" ?id " " ?rf " " ?ta crlf)
	
    ; normalized evaluation result for comparison later
    (if (eq ?desVal 0) then (bind ?desVal 0.000001))
    (bind ?val (round2 (/ (- ?curVal ?desVal) ?desVal))) 	
	(if (< ?val 0) then (bind ?val 0.0))
	
	(modify ?er (index ?step) (tacticId ?id) (tacticScenario ?tsc) (reasoningFramework ?rf) (tactic ?ta) (tacticDescription ?td)
		(nresult ?val))
    	    	
;	(modify ?er (index ?step) (tacticId ?id) (tacticScenario ?tsc) (reasoningFramework ?rf) (tactic ?ta) (tacticDescription ?td)
;    	(result ?val))
)

;(defrule NormalizeEvaluationResult
;	?sc <- (MAIN::Scenarios (measureValue ?desVal))
;	?er <- (Seeker::EvaluationResult (scenario ?sc) (reasoningFramework ?rf) (result ?curVal) (nresult nil))
;=>

    ; normalized evaluation result for comparison later
;	(bind ?val (round2 (/ (- ?curVal ?desVal) ?desVal))) 	
;	(if (< ?val 0) then (bind ?val 0))
	
;	(modify ?er (nresult ?val))
	
;   (__Log NormalizeEvaluationResult (format nil "(%s) Got normalized result (%10.2f)" ?rf ?val))
;	(printout t "------------Normalized EvaluationResult!!!!" ?rf ?val crlf)
;)

;
; Then ask all the reasoning frameworks to evaluate the current archtecture
;
(defrule EvaluateCandidateArchitecture
    (Seeker::RFCommand (command Analyze))
    (Configuration::ActiveReasoningFrameworks ?ev ? ?)
=>
    (__Log EvaluateCandidateArchitecture (format nil "(%s) Evaluate Candidate Architecture" ?ev))
	(focus ?ev)
)

;
; Halt until all the external RFs finish their analysis if they're running
;
(defrule HaltIfExtneralRF
    (declare (salience -10))
    (Seeker::RFCommand (command Analyze))
    (exists (Seeker::ExternalJob (command Analyze) (reasoningFramework ?)))
=>
	(halt)
	(printout t "------------Halted after requesting 'Analyze' to external RFs!!!!"  crlf)
)
