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
(defmodule UserDetermineQuality)
; Dependencies to modules: MAIN.clp
(set-current-module UserDetermineQuality)
;
; The following two rule are generated automatically for debugging purposes
; Please do not change them
;
(defrule DebugUserDetermineQualityEnter
    (declare (salience 1000))
    (MAIN::Debug UserDetermineQuality ?trace ?break)
=>
    ;(printout t "***Entered UserDetermineQuality" crlf)
    (if (neq ?trace none) then
        (watch ?trace))
    (if ?break then
        ;(printout t "   Stopped at beginning of module UserDetermineQuality" crlf)
        (halt))
)
(defrule DebugUserDetermineQualityExit
    (declare (salience -1000))
    ?d <- (MAIN::Debug UserDetermineQuality ?trace ?break)
=>
    ;(printout t "***Exited  UserDetermineQuality" crlf)
    (if (neq ?trace none) then
        (unwatch ?trace))
    (retract ?d)
    (assert (MAIN::Debug UserDetermineQuality ?trace ?break))
    (return)
)
;
; End of the generated debugging rules
;
;
; To be able to determine the constituent parts of a scenario it is important to know which quality is addressed by the scenario. The user is asked to assign one of the defined qualities to the scenario.
;
(defrule DetermineQuality
    Scenarios.Quality is empty
=>
    PresentUserListOfQualities  // ask the user to select one quality out
    AskUserForInput                 // of the list of available qualities
    Scenarios.Quality = Input    // store the given quality in the scenario
    
)
(defrule CreateQuestionForQuality
"Checks if there is a scenario with now quality assigned. If that is the case, it issues a question to the user to specify the appropriate quality."
    ?s <- (MAIN::Scenarios (Quality nil))
=>
    (assert (QA_ScenarioQuality (parent ?s) (question "Please specify the quality addressed by this scenario")))
    
)
(defrule AssignQualityToScenario
"As soon as the user specifies the quality for the scenario, it is changed accordingly"
    ?q <- (QA_ScenarioQuality 
                  (parent ?s)
                  (answerAvailable TRUE)
                  (answer ?a))
    
=>
    (modify ?s (Quality ?a))
    (retract ?q)
)
;
; Ask user for type and value of scenario stimulus
;
(defrule TypeCheckStimulus
    Scenarios.StimulusType is empty
=>
    Scenarios.StimulusValue = UserInput	//the user to select the text that represent the
    Scenarios.StimulusType = UserInput	//value and select one of the available types
    
)
;
; Ask user for type and value of scenario source
;
(defrule TypeCheckSource
    Scenarios.SourceType is empty
=>
    Scenarios.SourceValue = UserInput	//the user to select the text that represent the
    Scenarios.SourceType = UserInput	//value and select one of the available types
    
)
;
; Ask user for type and value of scenario artifact
;
(defrule TypeCheckArtifact
    Scenarios.ArtifactType is empty
=>
    Scenarios.ArtifactValue = UserInput  //the user to select the text that represent the
    Scenarios.ArtifactType = UserInput   //value and select one of the available types
    
)
;
; Ask user for the scenario constraint and the units in which the contraint is measured
;
(defrule TypeCheckConstraint
    Scenarios.ConstraintValue is empty
=>
    Scenarios.ConstraintValue = UserInput	//the user to select the text that represent the responses
    Scenarios.Unit = UserInput	//the user to select the text that represent the responses
    
)
;
; Ask user for type and value of scenario responses
;
(defrule TypeCheckResponses
    Scenarios.Responses is empty
=>
    Scenarios.Responses = UserInput	//the user to select the text that represent the responses
    
)
;
; Ask user for type and value of scenario environment
;
(defrule TypeCheckEnvironment
    Scenarios.EnvironmentType is empty
=>
    Scenarios.EnvironmentValue = UserInput	//the user to select the text that represent the
    Scenarios.EnvironmentType = UserInput		//value and select one of the available types
    
)
