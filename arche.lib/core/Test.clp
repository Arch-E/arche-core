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

(provide Test)
(defmodule Test)
; This Module contains the definition needed for the test suites
(deffacts NamesOfTestCase
	(CaseSelection Case1 "TestInitialDesign")
    (CaseSelection Case2 "TestAfterLocalizeCB")
    (CaseSelection Case3 "TestEBBafterLocalizeE")
    (CaseSelection Case4 "TestAfterEncapsulation")
    (CaseSelection Case5 "TestLocalizeAfterEncapsulate")
    (CaseSelection Case6 "TestLocalize2AfterEncapsulate")
    (CaseSelection Case7 "TestAfterEncapsulationACB")
)
(deftemplate Responsibilities
    (slot name (type STRING))
)
(deftemplate Leave_Dependencies
    (slot parent (type STRING))
    (slot child (type STRING))
)
(deftemplate Module
    (slot costOfChange (type FLOAT))
    (slot name (type STRING))
)
(deftemplate Interface
    (slot name (type STRING))
    (slot moduleName (type STRING))
    (slot costOfChange (type FLOAT))
)
(deftemplate Module_Responsibilities
    (slot module (type STRING))
    (slot responsibility (type STRING))
    (slot type (type SYMBOL))
)
(deftemplate Module_Dependencies
    (slot parent (type STRING))
    (slot child (type STRING))
    (slot childType (type SYMBOL))
)
(deftemplate Responsibility_Refinement
    (slot parent (type STRING))
    (slot child (type STRING))
)

(deftemplate Model_Nodes
    (slot costOfChange (type FLOAT))
    (slot node (type STRING))
)
(deftemplate Model_Arcs
    (slot parent (type STRING))
    (slot child (type STRING))
    (slot probability (type FLOAT))
)
(deftemplate Model_Nodes_Affected
    (slot parent (type STRING))
    (slot child (type STRING))
)
