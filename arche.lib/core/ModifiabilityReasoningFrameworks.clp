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

;
; Assigns responsibilities to modules and checks if the given modifiability scenarios can be achieved in the generated configuration.
; Generating configurations and checking them is done until a possible configuration is found or until no configuration can be found that satisfies all modifiability scnearios
;
(provide ModifiabilityReasoningFrameworks)
(defmodule ModifiabilityReasoningFrameworks)
;
; This fact describes the type of scenarios the modifiability reasoning framework
; is interested in
;
(deftemplate ScenariosOfInterest
    (slot quality (type SYMBOL))
    (slot measureType (type SYMBOL))
)
;
; This is the relation between elements of a modifiability model, which are various dependencies
;
(deftemplate ModifiabilityModelDependency
    (multislot type) ;the specific type of dependency
    (slot parent (type OBJECT))
    (slot child (type OBJECT))
)
;
; This triggers the check if sequences of responsibilities have to be changed because a responsibility changed
;
(deftemplate CheckForSequenceChanges
    (slot changedResponsibility (type OBJECT)) ;the ID of the responsibility that was changed
    (slot additionalResponsibility (type OBJECT)) ;The responsibility that triggered the change
    (slot scenario (type OBJECT)) ;the scenario that is the origin of the sequence
)
;
; Directed dependency between two responsibilities for impact analysis
;
(deftemplate Arc_Relation
    (slot source (type OBJECT))
    (slot target (type OBJECT))
    (slot probability (type FLOAT))
)
;
; A responsibility node for impact analysis
;
(deftemplate Node_Responsibility
    (slot id (type INTEGER))
    (slot name (type STRING))
    (slot cost (type FLOAT))
    (slot cumulativeprob (type FLOAT))
)
;
; Keepd track on what nodes are affected by a scenario
;
(deftemplate Node_affected
    (slot scenario (type OBJECT))
    (slot responsibilityId (type OBJECT))
    (slot nodeId (type OBJECT))
)
;
; Store the desired response measure
;
(deftemplate Response_Measures
    (slot scenario (type OBJECT))
    (slot responseMeasure (type FLOAT))
)
;
; Value that defines the probability that a change of one responsibility requires a change in another responsibility
;
(deftemplate P_ProbabilityOutgoing
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; Value that defines the cost of changing the responsibility
;
(deftemplate P_CostOfChange
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; The responsibility is prepared for the changes specified by the scenarios affecting this responsibility
;
(deftemplate P_PreparedForChange
    (slot value (type SYMBOL))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; The responsibility is an interface router
;
(deftemplate P_InterfaceRouter
    (slot value (type SYMBOL))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; The responsibility translates the abstraction level of an interface
;
(deftemplate P_AbstractionTranslator
    (slot value (type FLOAT))	; the abstraction level (0-no abstraction, 10-complete abstraction)
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; Describes the level of encapsulation for a responsibility (10 = complete, 0 = not at all)
;
(deftemplate P_EncapsulationLevel
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
(deftemplate P_ProbabilityIncoming
    (slot value (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
