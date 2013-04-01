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
 * A mapping from a function to a responsibility. It corresponds to a MAIN::TranslationRelation fact
 * in the fact base.
 * 
 * @author Paulo Merson
 */
public class FunctionResponsibilityMapVO extends CoreFact {

    /**
     * Function pointed by this mapping (from slot parent). Ideally this would be a reference to the
     * actual FunctionVO object, but that object may not exist when this mapping is created. So we
     * just store the value that was in the slot.
     */
    private int functionFactId;

    /**
     * Responsibility pointed by this mapping (from slot child). Ideally this would be a reference
     * to the actual ResponsibilityVO object, but that object may not exist when this mapping is
     * created. So we just store the value that was in the slot.
     */
    private int responsibilityFactId;

    /** Corresponds to slot source. Values can be CoreFact.NIL, .ARCHE_CORE or .USER. */
    private int source;

    /**
     * @param factId
     * @param factType
     * @param function
     * @param responsibility
     * @param source
     */
    public FunctionResponsibilityMapVO(int factId, int functionFactId, int responsibilityFactId,
            int source, IRefreshableUI ui) {
        super(factId, "MAIN::TranslationRelation", ui);
        this.functionFactId = functionFactId;
        this.responsibilityFactId = responsibilityFactId;
        this.source = source;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the functionFactId.
     */
    public int getFunctionFactId() {
        return functionFactId;
    }

    /**
     * @param functionFactId The functionFactId to set.
     */
    public void setFunctionFactId(int functionFactId) {
        this.functionFactId = functionFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the responsibilityFactId.
     */
    public int getResponsibilityFactId() {
        return responsibilityFactId;
    }

    /**
     * @param responsibilityFactId The responsibilityFactId to set.
     */
    public void setResponsibilityFactId(int responsibilityFactId) {
        this.responsibilityFactId = responsibilityFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the source.
     */
    public int getSource() {
        return source;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(int source) {
        this.source = source;
        ui.flagViewsToRefresh(this.getClass());
    }

}