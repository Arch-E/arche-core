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

/**
 * Describes a model relation type, i.e., gives meta information that is associated with a generic
 * model relation of any RF. It comes from the RF configuration file. A model relation is
 * necessarily a binary relation between two model elements, one is denominated the lhs and the
 * other is the rhs element (for left- and right-hand side).
 * 
 * @author Hyunwoo Kim
 */
public class ModelRelationTypeVO {

    /** Fact type for this model relation. Example: "ModifiabilityReasoningFrameworks::Arc_Relation" */
    private String factType;

    /**
     * Name of the slot in the fact factType that contains the reference to the lhs element of this
     * relation. In some fact the slot can be "parent", in another fact it can be "element1", etc.
     */
    private String lhsSlotName;

    /**
     * Name of the slot in the fact factType that contains the reference to the rhs element of this
     * relation. In some fact the slot can be "child", in another fact it can be "element2", etc.
     */
    private String rhsSlotName;

    /**
     * Names of the slots in the fact factType that correspond to the parameters of that model
     * relation. Note that model relations don't use "P_" facts--their parameters are slots within
     * the fact itself.
     */
    private String paramSlotNames[];
    
    private String paramSlotTypes[];
    
    private String paramSlotDisplays[];


    /**
     * @param factType
     * @param lhsSlotName
     * @param rhsSlotName
     * @param paramSlotNames
     */
    public ModelRelationTypeVO(String factType, String lhsSlotName, String rhsSlotName,
    		String[] paramSlotNames, String[] paramSlotTypes, String[] paramSlotDisplays) {
        this.factType = factType;
        this.lhsSlotName = lhsSlotName;
        this.rhsSlotName = rhsSlotName;
        this.paramSlotNames = paramSlotNames;
        this.paramSlotTypes = paramSlotTypes;
        this.paramSlotDisplays = paramSlotDisplays;
    }

    /**
     * @return Returns the factType.
     */
    public String getFactType() {
        return factType;
    }

    /**
     * @param factType The factType to set.
     */
    public void setFactType(String factType) {
        this.factType = factType;
    }

    /**
     * @return Returns the lhsSlotName.
     */
    public String getLhsSlotName() {
        return lhsSlotName;
    }

    /**
     * @param lhsSlotName The lhsSlotName to set.
     */
    public void setLhsSlotName(String lhsSlotName) {
        this.lhsSlotName = lhsSlotName;
    }

    /**
     * @return Returns the paramSlotNames.
     */
    public String[] getParamSlotNames() {
        return paramSlotNames;
    }

    /**
     * @param paramSlotNames The paramSlotNames to set.
     */
    public void setParamSlotNames(String[] paramSlotNames) {
        this.paramSlotNames = paramSlotNames;
    }

    /**
     * @return Returns the rhsSlotName.
     */
    public String getRhsSlotName() {
        return rhsSlotName;
    }

    /**
     * @param rhsSlotName The rhsSlotName to set.
     */
    public void setRhsSlotName(String rhsSlotName) {
        this.rhsSlotName = rhsSlotName;
    }
    
    public String[] getParamSlotTypes() {
        return paramSlotTypes;
    }

    public String[] getParamSlotDisplays() {
        return paramSlotDisplays;
    }
    
    public void setParamSlotTypes(String[] paramSlotTypes) {
        this.paramSlotTypes = paramSlotTypes;
    }

    public void setParamSlotDisplays(String[] paramSlotDisplays) {
        this.paramSlotDisplays = paramSlotDisplays;
    }

    
}