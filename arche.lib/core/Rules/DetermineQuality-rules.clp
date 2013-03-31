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

(defmodule DetermineQuality)
(require MAIN)
(require Configuration)
(ModuleOption Debug)

;============================================================
; Start of rule set ScenarioIsPerformance
;
;
; Dependent on type of stimulus, type of source and response measure units, ArchE can determine which quality a scenario addresses.
;
(defrule ScenarioIsPerformance1
"If the resonse measure unit is a Deadline and the source type is InternalEvent or ExternalEvent then the scenario is a performance scenario"
    ?sc <- (MAIN::Scenarios (quality nil) (measureType Deadline) (sourceType ?st&InternalEvent|ExternalEvent))
=>
    (modify ?sc (quality Performance))
)
(defrule ScenarioIsPerformance2
"If the resonse measure unit is either throughput or interval then the scenario is a performance scenario."
    ?sc <- (MAIN::Scenarios (quality nil) (measureType Deadline) (sourceType ?st&:InternalEvent | ExternalEvent))
=>
    (modify ?sc (quality Performance))
)
;
; End of rule set ScenarioIsPerformance
;------------------------------------------------------------
;============================================================
; Start of rule set ScenarioIsModifiability
;
;
; Dependent on type of stimulus, type of source and response measure units, ArchE can determine which quality a scenario addresses.
;
(defrule ScenarioIsModifiability1
    ?sc <- (MAIN::Scenarios (quality nil))
    (test (or (str-index "change" (lowcase (fact-slot-value ?sc stimulusValue)))
                 (str-index "delete" (lowcase (fact-slot-value ?sc stimulusValue)))))
=>
    (modify ?sc (quality Modifiability) (measureType CostConstraint) (stimulusType Change))
)
(defrule ScenarioIsModifiability2
    (declare (salience -100))
    ?sc <- (or (MAIN::Scenarios (quality Modifiability) (stimulusType Change))
         	(MAIN::Scenarios (quality nil)))
    (test  (str-index "add" (lowcase (fact-slot-value ?sc stimulusValue))))
=>
    (modify ?sc (quality Modifiability) (measureType CostConstraint) (stimulusType Add))
)
(defrule ScenarioIsModifiability3
"If unit efforts are in weeks, convert them into hours"
    ?sc <- (MAIN::Scenarios (quality Modifiability) (measureValue ?v) (measureUnit "Week"))
    (Configuration::HoursPerWeek ?f)
=>
    (modify ?sc (measureUnit hours) (measureValue (* ?v ?f)))
)
(defrule ScenarioIsModifiability4
"If unit efforts are in days, convert them into hours"
    ?sc <- (MAIN::Scenarios (quality Modifiability) (measureValue ?v) (measureUnit "Day"))
    (Configuration::HoursPerDay ?f)
=>
    (modify ?sc (measureUnit hours) (measureValue (* ?v ?f)))
)
(defrule ScenarioIsModifiability5
"If unit efforts are in months, convert them into hours"
    ?sc <- (MAIN::Scenarios (quality Modifiability) (measureValue ?v) (measureUnit "Month"))
    (Configuration::HoursPerMonth ?f)
=>
    (modify ?sc (measureUnit hours) (measureValue (* ?v ?f)))
)
(defrule ScenarioIsModifiability6
"If unit efforts are in years, convert them into hours"
    ?sc <- (MAIN::Scenarios (quality Modifiability) (measureValue ?v) (measureUnit "Year"))
    (Configuration::HoursPerYear ?f)
=>
    (modify ?sc (measureUnit hours) (measureValue (* ?v ?f)))
)
;
; End of rule set ScenarioIsModifiability
;------------------------------------------------------------
