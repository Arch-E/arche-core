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
 * Corresponds to fact "MAIN::P_AnalysisResult" in ArchE core. Not all slots are mapped to
 * properties in this VO class. Basically, for ArchE UI, only the owner fact id and the indication
 * whether the scenario is satisfied or not is sufficient. Property "source" is also part of this
 * class for future use in the UI.
 * 
 * @author Paulo Merson
 */
public class AnalysisResultVO extends CoreFact {

    /**
     * Indicates whether the owner scenario is satisfied (internally, each analysis result fact
     * represents the analysis of a specific RF--the RF is not part of the VO class).
     */
    private boolean isSatisfied;

    /**
     * Indicates the value of the Analysis Result.
     */
    private String  value;
    
    private String  previous_value;

    /** Corresponds to slot source. Values can be CoreFact.NIL, .ARCHE_CORE or .USER. */
    private int     source;

    /** Fact id of the scenario this analysis result maps to. */
    private int     ownerFactId;

    /**
     * @param factId
     * @param isSatisfied
     * @param value
     * @param ownerFactId
     * @param source
     */
    public AnalysisResultVO(int factId, boolean isSatisfied, String value, String previous_value, int ownerFactId,
            int source, IRefreshableUI ui) {
        super(factId, "MAIN::P_AnalysisResult", ui);
        this.isSatisfied = isSatisfied;
        this.value = value;
        this.previous_value = previous_value;
        this.ownerFactId = ownerFactId;
        this.source = source;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the isSatisfied.
     */
    public boolean isSatisfied() {
        return isSatisfied;
    }

    /**
     * @param isSatisfied The isSatisfied to set.
     */
    public void setSatisfied(boolean isSatisfied) {
        this.isSatisfied = isSatisfied;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
//    	  System.out.println("Prev = " + this.previous_value); 
//          System.out.println("New = " + this.value); 
        
        this.value = value;
    }
    
    /**
     * @return Returns the value.
     */
    public String getPreviousValue() {
        return previous_value;

    }

    /**
     * @param previous_value The value to set.
     */
    public void setPreviousValue(String val) {
        this.previous_value = val;
    }

    /**
     * @return Returns the ownerFactId.
     */
    public int getOwnerFactId() {
        return ownerFactId;
    }

    /**
     * @param ownerFactId The ownerFactId to set.
     */
    public void setOwnerFactId(int ownerFactId) {
        this.ownerFactId = ownerFactId;
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