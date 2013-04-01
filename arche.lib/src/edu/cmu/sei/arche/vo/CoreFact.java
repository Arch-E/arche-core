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

package edu.cmu.sei.arche.vo;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * This abstract class represents a Fact in the fact base of ArchE Core.
 * 
 * @author Paulo Merson
 */
public abstract class CoreFact {

    /** Constant to indicate the source is nil in the source slot. */
    public static final int  NIL        = 0;

    /** Constant to indicate the source is ArchE core, i.e. ArchE created the fact. */
    public static final int  ARCHE_CORE = 1;

    /** Constant to indicate the source is the user, i.e. the user created the fact. */
    public static final int  USER       = 2;

    /**
     * Numeric fact id that is automatically generated by Jess for all facts. Corresponds to the
     * value returned by jess.Fact.getFactid()
     */
    private int              factId;

    /** Type of a fact. Examples: MAIN::Scenarios, MAIN::Function, MAIN::AskQuestion. */
    private String           factType;

    /**
     * Represents an object in the user interface layer that is responsible for controlling the
     * flagging and refreshing of views.
     */
    protected IRefreshableUI ui;

    /**
     * @return Returns the id.
     */
    public int getFactId() {
        return factId;
    }

    /**
     * @param id The id to set.
     */
    public void setFactId(int id) {
        this.factId = id;
    }

    /**
     * @return Returns the type.
     */
    public String getFactType() {
        return factType;
    }

    /**
     * @param factType The type of the fact to set.
     */
    public void setFactType(String factType) {
        this.factType = factType;
    }

    /**
     * @param factId
     * @param factType
     * @param ui
     */
    protected CoreFact(int factId, String factType, IRefreshableUI ui) {
        this.factId = factId;
        this.factType = factType;
        this.ui = ui;
    }
}