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
 * Scenario part value object. Data of a scenario part is inside fact MAIN::Scenarios, it's not a
 * separate fact.
 * 
 * @author Paulo Merson
 */
public class ScenarioPartVO extends CoreFact {

    /**
     * Indicates whether this object holds stimulus, stimulus source, environment, artifact,
     * response or response measure.
     */
    private int    partType;

    /** Free text typed by the user. Corresponds to slot stimulusText, ..., or measureText */
    private String text;

    /**
     * Type given by the user for this scenario part. Corresponds to slot stimulusType, ..., or
     * measureType. This type id should be an id in the RF configuration (class PartOptionsVO).
     */
    private String typeId;

    /**
     * Unit selected by the user for this scenario part. Corresponds to slot stimulusUnit, ..., or
     * measureUnit. This unit id should be an id in the RF configuration (class PartOptionsVO).
     */
    private String unitId;

    /** Value provided by the user. Corresponds to slot stimulusValue, ..., or measureValue */
    private String value;

    /**
     * @param factId should be the same fact ID of the Scenario fact because the scenario and its
     *            parts are in a single fact in the core
     * @param factType
     * @param partType
     * @param text
     * @param typeId
     * @param unitId
     * @param value
     */
    public ScenarioPartVO(int factId, int partType, String text, String typeId, String unitId,
            String value, IRefreshableUI ui) {
        super(factId, "MAIN::Scenarios", ui);
        this.partType = partType;
        this.text = text;
        this.typeId = typeId;
        this.unitId = unitId;
        this.value = value;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the partType.
     */
    public int getPartType() {
        return partType;
    }

    /**
     * @param partType The partType to set.
     */
    public void setPartType(int partType) {
        this.partType = partType;
    }

    /**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text to set.
     */
    public void setText(String text) {
        this.text = text;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the typeId.
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId The typeId to set.
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the unitId.
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId The unitId to set.
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
        ui.flagViewsToRefresh(this.getClass());
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
        this.value = value;
        ui.flagViewsToRefresh(this.getClass());
    }

}