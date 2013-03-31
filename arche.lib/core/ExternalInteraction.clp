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
 * This module contains the definitions required for interacting with
 * external reasoning frameworks
 *******************************************************************************/

/* Now the require statements of all the file required by this module */

/* Here goes the defmodule statement, which is followed by the provide statement
 * that names the feature name of this module (typically the same as the module name)
 */

(provide ExternalInteraction)
(defmodule ExternalInteraction)

/******************************************************************************
 * Now the options part. The following options are available:
 * (ModuleOption Debug) - enables debugging facility
 * (ModuleOption Events Enter Exit) - enables the OnEnter and/or the OnExit event
 * (ModuleOptions Sequence A B C) - enables the sequence of phases (1st A, then B, and last C)
 *******************************************************************************/

; Module does not contain rules. So no options required

 /******************************************************************************
 * Here are the definitions used to interact with external reasoning frameworks
 *******************************************************************************/
;
; This fact describes how much work is remaining for the external reasoning framework.
;
(deftemplate E_RF_Progress
    (slot externalReasoningFramework (type SYMBOL)) ;The name of the external reasoning framework that is executing the command
    (slot rfCommand (type SYMBOL)) 					;The name of the RF command being executed
    (slot timeElapsed (type Integer))				;The time elapsed (ms)
)

;
; This fact indicates that the execution of the command is completed successfully for an external reasoning framework.
;
(deftemplate E_RF_Complete
    (slot externalReasoningFramework (type SYMBOL)) ;The name of the external reasoning framework that completed the command
    (slot rfCommand (type SYMBOL)) 					;The name of the RF command executed
)

;
; This fact indicates that the execution of the command is canceled by the source.
;
(deftemplate E_RF_Cancel
    (slot externalReasoningFramework (type SYMBOL)) ;The name of the external reasoning framework whose work was canceled 
    (slot rfCommand (type SYMBOL)) 					;The name of the RF command canceled
    (slot source (type SYMBOL)) 					;The source who canceled the external reasoning (ArchE/User)
)

;
; This fact shows the result of the execution of the command for all involved external reasoning frameworks.
;
(deftemplate E_RF_Result
    (slot rfCommand (type SYMBOL)) 					;The name of the RF command executed
    (slot isSuccess (type SYMBOL)) 					;TRUE if the command was successfully executed for all the external RFs
)

;
; This fact indicates the number of the running external RFs.
; It is internally used by the extension to check the completion of the command requested by ArchE.
;
(deftemplate E_RunningERFs
    (slot rfCommand (type SYMBOL)) 					;The name of the RF command being executed
    (slot number (type Integer))					;The number of the running external RFs
    (slot isCanceled (type SYMBOL))					;TRUE if any external RF is canceled
)


;
;
(deftemplate ReasoningFramework
    (slot id (type SYMBOL)) 					
    (slot rfconfigfile (type String))					
    (slot type (type SYMBOL))					
    (slot update (type SYMBOL))					
)

;(deftemplate E_ExternalRFs
;    (multislot erflist) ;List of ReasoningFramework facts
;)


(defquery GetAllExternalRFs			; use AllExternalRFs for ?x
    (declare (variables ?x))
	(ExternalInteraction::ReasoningFramework (id ~?x))
)


;
; This fact contains a list of running eclipse jobs.
; It will be used to support job cancellation by ArchE
;
;(deftemplate E_RunningJobs
;    (slot jobs (type Object)) 					;The Java ArrayList object containing a list of running eclipse jobs
;)
; Global variables in the ExternalInteraction module
;
;(defglobal ?*glob* = (new org.xmlBlaster.util.Global))
;(defglobal ?*glob* = nil)
