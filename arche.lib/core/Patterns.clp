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

; Dependencies to modules: MAIN.clp,Design.clp,ModifiabilityReasoningFrameworks.clp
(defmodule Patterns)
(deftemplate Pattern
    (slot group)
    (slot name)
    (slot description)
)
;
; an element of a pattern. Elements are treated as responsibilities
;
(deftemplate PatternElement
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate PatternElementInterface
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate PatternConnector
    (slot type)
    (slot owner)
    (slot valueAbs)
    (slot valueFactor)
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate PatternContainer
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate Refinement
    (slot parent)
    (slot child)
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate IsARelation
    (slot parent)
    (slot child)
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate InterfaceRealization
    (slot parent)
    (slot child)
    (slot pattern)
    (slot name)
    (slot description)
)
(deftemplate PatternItemProperty
    (slot name)
    (slot owner)
    (slot valueAbs)
    (slot valueFactor)
    (slot pattern)
    (slot description)
)
(deftemplate Dependency
    (slot parent)
    (slot child)
    (slot pattern)
    (slot name)
    (slot description)
)
(deffunction SubstituteFields (?string ?obj)
    (bind ?parts (SplitString ?string))
    (if (= (length$ ?parts) 3) then
        (try
            (return (str-cat (nth$ 1 ?parts) (fact-slot-value ?obj (nth$ 2 ?parts)) (nth$ 3 ?parts)))
         catch
            (return ?string))  
     else
        (return ?string))
)
(deffunction SplitString (?string)
    (bind ?pos1 (str-index "<" ?string))
    (if ?pos1 then
        (bind ?rest (sub-string ?pos1 (str-length ?string) ?string))
        (bind ?pos2 (str-index ">" ?rest))
        (if ?pos2 then
            (bind ?parts (create$ (sub-string 1 (- ?pos1 1) ?string)))
            (bind ?parts (insert$ ?parts 2 (sub-string 2 (- ?pos2 1) ?rest)))
            (if (= ?pos2 (str-length ?rest)) then
                (bind ?parts (insert$ ?parts 3 ""))
             else
                (bind ?parts (insert$ ?parts 3 (sub-string (+ ?pos2 1) (str-length ?rest) ?rest))))
            (return ?parts)))
    (return (create$ ?string))
)
