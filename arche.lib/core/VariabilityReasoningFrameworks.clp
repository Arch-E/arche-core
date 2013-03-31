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

;    (import Planner deftemplate T_MakeAnotherRound)
;    (import MAIN OrderedFact T_SaveFactBase)
;    (import MAIN OrderedFact T_RestoreFactBase)
;    (import VariabilityReasoningFramework deftemplate VPConfiguration)
;    (import VariabilityReasoningFramework deftemplate VPInputValue)
;    (import VariabilityReasoningFramework deftemplate VariabilityMechanismResponsibility)
;    (import VariabilityReasoningFramework deftemplate VariationPoint)
;    (import VariabilityReasoningFramework deftemplate Variant)
;    (export deffunction GetVariationPoint)
;    (export deffunction InstantiateVariabilityMechanism)
;    (export deftemplate VPInputValue)
;    (export deffacts BuilderSkills)
;    (export deffacts BuilderSkills)
;    (export deffacts BuilderSkills)
;    (export deffacts BuilderSkills)
;    (export deffacts BuilderSkills)
;    (export deftemplate AppropriateMechanism)
;    (export deftemplate VariabilityMechanismInstance)
;    (export deftemplate VariabilityDependencyRelation)
;    (export deftemplate VariationPoint)
;    (export deftemplate Variant)
; Dependencies to modules: Planner.clp,MAIN.clp
(require Design)
(defmodule VariabilityReasoningFrameworks)
;
; Defines which alternative responsibilities belong to this alternative
;
(deftemplate VPAlternativeResponsibilities
    (slot condition (type STRING))
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; Defines a name for a configuration. A configuration is a concrete set of values for all input parameters that determine an instance architecture
;
(deftemplate VPConfiguration
    (slot name (type STRING))
    (multislot inputs)
)
(set-current-module Design)
;
; A value that has to be given by a user to determine an instance of the architecture
;
(deftemplate VPInputValue
    (slot id (type STRING))
    (slot name (type STRING))
    (multislot value)
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
(set-current-module VariabilityReasoningFrameworks)
;
; The responsibility that realizes the variability mechanism
;
(deftemplate VariabilityMechanismResponsibility
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
(deftemplate AppropriateMechanism
    (slot owner (type OBJECT))
    (multislot mechanism)
)
(set-current-module Design)
(deftemplate VariabilityMechanismInstance
    (slot instantiationCost (type FLOAT))
    (slot patternGroup (type STRING))
    (slot name (type STRING))
    (slot description (type STRING))
    (slot skills (type STRING))
    (slot methode (type SYMBOL)) ;type of mechanism. either "New" if mechanism support creation of new variants or "Select" if mechanis supports variant selection
    (slot automation (type SYMBOL))
    (slot cost (type Integer))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
(deftemplate VariabilityDependencyRelation
    (slot childType (type SYMBOL))
    (slot id (type STRING)) ;Unique id for a relation
    (slot source (type SYMBOL)) ;Documents who inserted this relation (ArchE/User)
    (slot parent (type OBJECT)) ;Id of the parent in this relation
    (slot child (type OBJECT)) ;Id of the child in this relation
)
;
; Contains all the values necessary to describe a variation point in the architecture
;
(deftemplate VariationPoint
    (slot id (type STRING))
    (slot name (type STRING))
    (multislot value)
    (slot mechanism (type STRING))
    (slot condition (type STRING))
    (multislot alternatives)
    (slot howTo (type STRING))
    (slot cost (type FLOAT))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
(set-current-module VariabilityReasoningFrameworks)
(deftemplate VariabilityMechanism
    (slot name (type STRING))
    (slot description (type STRING))
    (slot skills (type STRING))
    (slot methode (type SYMBOL)) ;type of mechanism. either "New" if mechanism support creation of new variants or "Select" if mechanis supports variant selection
    (slot automation (type SYMBOL))
    (slot cost (type Integer))
    (slot owner (type OBJECT)) ;Id of the owner of the property
    (slot source (type SYMBOL)) ;Origin of the property value: ArchE, User
    (slot status (type SYMBOL)) ;Indicators for the property:nil, conflict
)
;
; This is a responsibility that substitues a variation point when an instance is created
;
(deftemplate Variant
    (slot owner (type OBJECT)) ;owner is a variationpoint
    (slot vpValue (type STRING)) ;The value the assigned variationpoint must hace to choose this variant
    (slot id (type STRING))
    (slot name (type STRING))
    (slot description (type STRING))
    (slot source (type SYMBOL))
)
(defglobal ?*VP* = 1 ?*IP* = 1)
;
; Defines the roles of poduct builders in sequence of expected skill sets to determine appropriate variability mechnisms
;
(deffacts VariabilitySkills
    (BuilderSkills Developer 1)
    (BuilderSkills Integrator 2)
    (BuilderSkills ProductBuilder 3)
    (BuilderSkills Admin 4)
    (BuilderSkills EndUser 5)
)
(deffacts DefaultVariabilityMechanisms
    (VariabilityMechanism (name ComponentSelection) (description "Enables the selection of alternative components which all comply to one interface") (skills ProductBuilder) (methode Select) (automation TRUE) (cost 5) (source ArchE))
    (VariabilityMechanism (name Framework) (description "Provides an implementation framework to developers to ease the creation of new components complying to a specified interface") (skills Developer) (methode New) (automation FALSE) (cost 8) (source ArchE))
    (VariabilityMechanism (name UserPreference) (description "Gives an end user the ability to define his/her own settings") (skills EndUser) (methode Select) (automation TRUE) (cost 7) (source ArchE))
    (VariabilityMechanism (name RunTimeConfigurationFile) (description "Enables changing behavior of the system by setting parameters in a configuration file. Requires file loading and interpretation responsibilities in the system") (skills Admin) (methode Select) (automation TRUE) (cost 5) (source ArchE))
    (VariabilityMechanism (name DevelopmentTimeConfigurationFile) (description "Enables changing behavior of the system by setting parameters in a configuration file during development time. Does not require file I/O capabilities.") (skills Integrator) (methode Select) (automation TRUE) (cost 3) (source ArchE))
    (VariabilityMechanism (name TableDriven) (description "Enables changing behavior of the system by changing the content of a table in the system.") (skills Integrator) (methode Select) (automation TRUE) (cost 2) (source ArchE))
)
(deffunction GetVariationPoint (?responsibility)
    (bind ?mo (GetSlotvalueFromFacts parent "(Design::ResponsibilityToModuleRelation (child ?1))" ?responsibility))
    (return (GetSlotvalueFromFacts child "(Design::RealizeRelation (childType variationPoint) (parent ?1))" ?mo))
    
)
;
; Creates an instance of a variability mechanism and returns its fact-id
;
(deffunction InstantiateVariabilityMechanism (?template)
    (return (assert (Design::VariabilityMechanismInstance
        (name (str-cat "Instance of " (fact-slot-value ?template name)))
        (description (fact-slot-value ?template description))
        (skills (fact-slot-value ?template skills))
        (methode (fact-slot-value ?template methode))
        (automation (fact-slot-value ?template automation))
        (cost (fact-slot-value ?template cost))
        (patternGroup (fact-slot-value ?template name)))))
)
