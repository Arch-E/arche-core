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

(provide Configuration)
(defmodule Configuration)
(deftemplate ActiveReasoningFrameworks (declare (ordered TRUE)))

/*
(deffacts ConvertCostOfChange
    (HoursPerDay 8)
    (HoursPerWeek 40)
    (HoursPerMonth 168)
    (HoursPerYear 1848)
)
*/

/*
;
; Includes default values required for the modifiability model
; 
;
(deffacts DefaultsForModifiabilityModel
    (DecompositionProbability 0.8) ;Default probability for dependencies between child responsibilities of the same decomposition
    (DataflowOutgoingProbability 0.7) ;Default probability for dataflows between two responsibilities
    (DataflowIncomingProbability 0.3) ;Default probability for dataflows from consumer to producer
    (BoundaryProbability 0.7) ;Cost that is added per module in a specific design
)
*/

/*

;
; Configuration values for performance modelling
;
(deffacts PerformanceConfig
    (ContextSwitchingTime 10.0) ;Configuration values for performance modelling
)
(deffacts CohesionAndCouplingParameters
    (StrongCohesion 0.7)
    (LooseCoupling 0.3)
    (InterfaceLimit 0.1)
)

(deffunction ConvertComplexityIntoCost (?cx)
    (return (* (** ?cx 1.5) 8.0))
)
*/
