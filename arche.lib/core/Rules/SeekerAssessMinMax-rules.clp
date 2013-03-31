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
(require Seeker)
(require MAIN)
(defmodule SeekerAssessMinMax)


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
; Determine the scenario with the biggest distance to a solution for every candidate architecture
;
(defrule MaxScenarioDistance
	(declare (salience 1))
	?s1 <- (MAIN::Scenarios (measureValue ?dv1))
;	?keep <- (Seeker::EvaluationResult (scenario ?s1) (tacticScenario ?ts) (reasoningFramework ?rf) (tactic ?ta) (result ?r1) (relevance nil))    
	?keep <- (Seeker::EvaluationResult (scenario ?s1) (tacticScenario ?ts) (reasoningFramework ?rf) (tactic ?ta) (nresult ?r1) (relevance nil))    
	?s2 <- (MAIN::Scenarios (measureValue ?dv2))
;	?er <- (Seeker::EvaluationResult (scenario ?s2) (tacticScenario ?ts) (reasoningFramework ?rf) (tactic ?ta) 
;    		(result ?r2&:(<= ?r2 ?r1)) (relevance nil))
	?er <- (Seeker::EvaluationResult (scenario ?s2) (tacticScenario ?ts) (reasoningFramework ?rf) (tactic ?ta) 
    		(nresult ?r2&:(<= ?r2 ?r1)) (relevance nil))
	(test (neq ?keep ?er))
=>
	(retract ?er)
)
;
; Choose the candidate architecture with the closed distance to a solution
;
(defrule MinArchitectureDistance
	?s1 <- (MAIN::Scenarios (measureValue ?dv1))
;	?keep <- (Seeker::EvaluationResult (scenario ?s1) (result ?r1) (relevance nil))    
	?keep <- (Seeker::EvaluationResult (scenario ?s1) (nresult ?r1) (relevance nil))    
	?s2 <- (MAIN::Scenarios (measureValue ?dv2))
;	?er <- (Seeker::EvaluationResult (scenario ?s2) (result ?r2&:(>= ?r2 ?r1 )) (relevance nil))   
	?er <- (Seeker::EvaluationResult (scenario ?s2) (nresult ?r2&:(>= ?r2 ?r1 )) (relevance nil))   
	(test (neq ?keep ?er))
=>
	(retract ?er)
)
