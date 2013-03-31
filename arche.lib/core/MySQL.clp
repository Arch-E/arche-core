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

(defmodule MySQL)
;(require Planner)
(require MAIN)

(defrule DebugMySQLEnter
    (declare (salience 1000))
    (MAIN::Debug MySQL ?trace ?break)
=>
    (printout t "***Entered MySQL" crlf)
    (if (neq ?trace none) then
        (watch ?trace))
    (if ?break then
        (printout t "   Stopped at beginning of module MySQL" crlf)
        (halt))
)
(defrule DebugMtSQLExit
    (declare (salience -1000))
    ?d <- (MAIN::Debug MySQL ?trace ?break)
=>
    (printout t "***Exited  MySQL" crlf)
    (if (neq ?trace none) then
        (unwatch ?trace))
    (retract ?d)
    (assert (MAIN::Debug Planner ?trace ?break))
    (return)
)

;
; This rule saves all the persistant data belonging to the current project into
; the database
;
(defrule SaveFactBaseMySQL
    ?c <- (MAIN::T_SaveFactBase ?pn)
=>
    (bind ?res (DeleteFactSet ?pn))
    (bind ?res (SaveFactSet "Project" ?pn "project"))
    (if (not (floatp ?res)) then
    	(assert (MAIN::AskQuestion (questionId wrongOutputFilename) (parent System) (affectedFacts nil) (default ?pn)))
    )
    (retract ?c)
)

;
; This rule reads the facts from the database and stores them as facts
;
(defrule RestoreFactBaseMySQL
    ?c <- (MAIN::T_RestoreFactBase ?fs ?pn ?invalid ?delete)
;   ?c <- (MAIN::T_RestoreFactBase ?fs ?pn )
=>
   	(bind ?res (RestoreFactSet ?fs ?pn ?invalid ?delete))
;   	(bind ?res (RestoreFactSet ?fs ?pn))
    (if (not (floatp ?res)) then
    	(assert (MAIN::AskQuestion (questionId wrongOutputFilename) (parent System) (affectedFacts nil) (default ?res)))
    )
    (retract ?c)
    (assert (MAIN::T_CheckConsistency)) ; check consistency of the data first before running the reasoning frameworks
)

;
; This rule exports all the persistant data belonging to the current project into a sql file
;
(defrule ExportFactBaseToSQL
    ?c <- (MAIN::T_ExportFactBase ?pn ?fp)
=>
    (bind ?res (ExportFactSetToSQL "Project" ?pn "project" ?fp))
    (if (not (floatp ?res)) then
    	(assert (MAIN::AskQuestion (questionId wrongOutputFilename) (parent System) (affectedFacts nil) (default ?pn)))
    )
    (retract ?c)
)
