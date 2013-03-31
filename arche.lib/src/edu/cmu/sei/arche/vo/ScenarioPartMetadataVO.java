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

import java.util.ArrayList;

/**
 * Describes a scenario part, i.e., gives meta information that is associated with a scenario. This
 * is not a fact in the fact base. It comes from the RF configuration file. *
 * 
 * @author Alexander Berendeyev
 */
public class ScenarioPartMetadataVO {

    /** Defines the type of the part */
    private int             partType;

    /** Default text of the part. */
    private String          defaultText;

    /** Types defined for the part. */
    private ArrayList       types              = new ArrayList();

    /** Default type of the part. */
    private PartOptionsVO   defaultType;

    /** Units defined for the part. */
    private ArrayList       units              = new ArrayList();

    /** Default unit of the part. */
    private PartOptionsVO   defaultUnit;

    /** Default value of the part. */
    private String          defaultValue;

    /** Stimulus */
    public static final int STIMULUS           = 0;

    /** Source of stimulus */
    public static final int SOURCE_OF_STIMULUS = 1;

    /** Environment */
    public static final int ENVIRONMENT        = 2;

    /** Artifact */
    public static final int ARTIFACT           = 3;

    /** Response */
    public static final int RESPONSE           = 4;

    /** Response measure */
    public static final int RESPONSE_MEASURE   = 5;

    /**
     * @param partType
     * @param defaultText
     * @param defaultValue
     */
    public ScenarioPartMetadataVO(int partType, String defaultText, String defaultValue) {
        this.partType = partType;
        this.defaultText = defaultText;
        this.defaultValue = defaultValue;
    }

    /**
     * @param partType
     * @param defaultText
     * @param types
     * @param defaultType
     * @param units
     * @param defaultUnit
     * @param defaultValue
     */
    public ScenarioPartMetadataVO(int partType, String defaultText, PartOptionsVO defaultType,
            PartOptionsVO defaultUnit, String defaultValue) {
        this.partType = partType;
        this.defaultText = defaultText;
        this.defaultType = defaultType;
        this.defaultUnit = defaultUnit;
        this.defaultValue = defaultValue;
    }

    /**
     * @return Returns the defaultUnit.
     */
    public PartOptionsVO getDefaultUnit() {
        return defaultUnit;
    }

    /**
     * @param defaultUnit The defaultUnit to set.
     */
    public void setDefaultUnit(PartOptionsVO defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    /**
     * @return Returns the defaultValue.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue The defaultValue to set.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
     * @return Returns the types.
     */
    public ArrayList getTypes() {
        return types;
    }

    /**
     * @return Returns the units.
     */
    public ArrayList getUnits() {
        return units;
    }

    /**
     * @return Returns the defaultText.
     */
    public String getDefaultText() {
        return defaultText;
    }

    /**
     * @param defaultText The defaultText to set.
     */
    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    /**
     * @return Returns the defaultType.
     */
    public PartOptionsVO getDefaultType() {
        return defaultType;
    }

    /**
     * @param defaultType The defaultType to set.
     */
    public void setDefaultType(PartOptionsVO defaultType) {
        this.defaultType = defaultType;
    }
}