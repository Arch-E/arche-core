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
 * This module contains the definitions required for the Seeker
 * This especially includes the definition of the commands used to interact
 * with the reasoning frameworks
 *******************************************************************************/
 
/* Now the require statements of all the file required by this module */

/* Here goes the defmodule statement, which is followed by the provide statement
 * that names the feature name of this module (typically the same as the module name
 */
(defmodule Seeker)
(provide Seeker)

/******************************************************************************
 * Now the options part. The following options are available:
 * (ModuleOption Debug) - enables debugging facility
 * (ModuleOption Events Enter Exit) - enables the OnEnter and/or the OnExit event
 * (ModuleOptions Sequence A B C) - enables the sequence of phases (1st A, then B, and last C)
 *******************************************************************************/

; Module does not contain rules. So no options required

 /******************************************************************************
 * Here the definitions of the interaction commands with the reasoning frameworks
 *******************************************************************************/
; Command Apply Tactic � tells a reasoning framework to change the current architecture
; by applying the specified tactic. The information is taken directly from one of
; the suggested tactics. The seeker makes sure that the reasoning framework 
; mentioned in the tactic is activatedunderstands the tactic is executed
;
(deftemplate ApplySuggestedTactic
	(slot tacticId (type SYMBOL)) ; generated by an external reasoning framework, must be unique within the RF
    (slot scenario (type OBJECT)) ;The scenario this result belongs to
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework that produced this result
    (slot tactic (type SYMBOL)) ;name of the tactic to use
    (slot tacticDescription (type STRING)) ;description of the tactic to use
	(slot haveResults (type SYMBOL)) ; is set to TRUE if the tactic was applied and the resoning frameworks produced results
;    (multislot at) ;the positions in the model where to apply the tactic
;    (multislot parameters) ;parameters required for this tactic
)
; Command Suggested Tactic � Tactic suggested by a reasoning framework to move the architecture closer to a solution
; for at least one scenario.
(deftemplate TryTacticResult
	(slot tacticId (type SYMBOL)) ; generated by an external reasoning framework, must be a unique tactic id within the RF
    (slot scenario (type OBJECT)) ;The scenario this result belongs to
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework that produced this result
;    (slot index (type INTEGER)) ;ordering; defines in which order tactics should be applied
    (slot tactic (type SYMBOL)) ;name of the tactic to use
    (slot tacticDescription (type STRING)) ;description of the tactic to use
;    (multislot at) ;the positions in the model where to apply the tactic
;    (multislot parameters) ;parameters required for this tactic
;    (slot result (type FLOAT)) ;the result of the model evaluation function for this tactic
)
;
; Evaluation result � Returns the result of an evaluation of a candidate architecture
; specified by the reasonibg framework, the tactics and the scenario
;
(deftemplate EvaluationResult
	(slot index (type INTEGER)) ; The search step number
	(slot tacticId (type SYMBOL))
    (slot scenario (type OBJECT)) ;The scenario this result belongs to
	(slot tacticScenario (type OBJECT)) ; The scenario the tactic applies to
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework that applied the tactic
    (slot tactic (type SYMBOL)) ;name of the tactic to use
    (slot tacticDescription (type STRING)) ;user readable description of the tactic
    (slot nresult (type FLOAT)) ; the normalized evaluation result for comparison
    (slot result (type FLOAT)) ;the result of the model evaluation function for this tactic
    (slot utility (type FLOAT)) ;a value between 0 and 1 that describes the utility achieved
    (slot change (type FLOAT)) ;the change of the result when applying this tactic
	(slot relevance (type INTEGER)) ;the relevance of this tactics
)
; Command Describe Tactic � tells a reasoning framework to collect all the necessary data, such as the architecture 
; elements involved in this tactic, and provide the information in a way that it can be displayed to the 
; user. The information contained is taken from the information �Suggested tactic� provided by the reasoning
; framework
(deftemplate DescribeTacticToUser
    (slot scenario (type OBJECT))
    (slot reasoningFramework (type SYMBOL)) ;The reasoning framework that applied the tactic
    (slot index (type Integer)) ; The search step number
    (slot relevance (type Integer)) ;preference; lower index is more preferable; 1 = best
	(slot tacticId (type SYMBOL))
    (slot tactic (type SYMBOL)) ;name of the tactic to use
;    (multislot at) ;the positions in the model where to apply the tactic
;    (multislot parameters) ;parameters required for this tactic
;    (slot result (type FLOAT)) ;the result of the model evaluation function for this tactic
    (slot questionGroup (type SYMBOL)) ;the ID to be used in the resulting question to enable grouping
)
(deftemplate CurrentArchitectureName (declare (ordered TRUE)))
;
; The command that is sent to the reasoning frameworks to tell them what to do.
; the command can have th efollowing values:
; - ApplyTactics (Apply tactics that th euser accepted)
; - AnalyzeAndSuggest (Analyze the architecture, provide the analysis results and suggest tactics if needed)
; - ApplySuggestedTactic (what the name says)
; - Analyze (analyzes the currecnt architecture and provide evaluation results)
; - DescribeTactic (describe a suggested tactic to the user)
;
(deftemplate RFCommand
	(slot command (type SYMBOL))
    (slot parameter1 (type STRING))
    (slot parameter2 (type STRING))
    (slot parameter3 (type STRING))
)
;
; Command Do Deep Search - tells the seeker to do an n-level deep search the next round
; This fact is typically issues by the user interface when the user pushes a "Search"
; button
;
(deftemplate DoDeepSearch
    (slot levels (type INTEGER))  ;Maximum depth of search
	(slot maxTime (type INTEGER)) ;Maximum time in sec to spend for search
)

(deftemplate ExternalJob
	(slot command (type SYMBOL))
	(slot reasoningFramework (type SYMBOL))
)

(defglobal ?*ArchNode* = 1)