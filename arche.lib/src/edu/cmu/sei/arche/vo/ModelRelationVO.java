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
 * Generic model relation that is used in some RF. A model relation is necessarily a binary relation
 * between two model elements (two ModelElementVO objects), one is denominated the lhs and the other
 * is the rhs element (for left- and right-hand side).
 * 
 * @author Paulo Merson
 */
public class ModelRelationVO extends CoreFact {

    /** Model relation type for this model relation. */
    private ModelRelationTypeVO type;

    /**
     * Reference to the lhs element of this relation. It must be the fact id of a ModelElementVO. We
     * don't create a reference to a ModelElementVO object directly because of the sequence in which
     * fact ids are assigned/reassigned in the core.
     */
    private int                 lhsFactId;

    /**
     * Reference to the rhs element of this relation. It must be the fact id of a ModelElementVO. We
     * don't create a reference to a ModelElementVO object directly because of the sequence in which
     * fact ids are assigned/reassigned in the core.
     */
    private int                 rhsFactId;

    /**
     * Each element of the array is the value of a parameter of this model relation. The parameters
     * are the ones defined by ModelRelationTypeVO.paramSlotNames.
     */
    private String[]            paramSlotValues;

    /**
     * @param factId
     * @param factType
     * @param parameterType
     * @param value
     * @param source
     */
    public ModelRelationVO(int factId, ModelRelationTypeVO type, int lhsFactId, int rhsFactId,
            String[] paramSlotValues, IRefreshableUI ui) {
        super(factId, type.getFactType(), ui);
        this.type = type;
        this.lhsFactId = lhsFactId;
        this.rhsFactId = rhsFactId;
        this.paramSlotValues = paramSlotValues;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the type.
     */
    public ModelRelationTypeVO getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(ModelRelationTypeVO type) {
        this.type = type;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the lhsFactId.
     */
    public int getLhsFactId() {
        return lhsFactId;
    }

    /**
     * @param lhsFactId The lhsFactId to set.
     */
    public void setLhsFactId(int lhsFactId) {
        this.lhsFactId = lhsFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the rhsFactId.
     */
    public int getRhsFactId() {
        return rhsFactId;
    }

    /**
     * @param rhsFactId The rhsFactId to set.
     */
    public void setRhsFactId(int rhsFactId) {
        this.rhsFactId = rhsFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the paramSlotValues.
     */
    public String[] getParamSlotValues() {
        return paramSlotValues;
    }

    /**
     * @param paramSlotValues The paramSlotValues to set.
     */
    public void setParamSlotValues(String[] paramSlotValues) {
        this.paramSlotValues = paramSlotValues;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * This is a helper method to get the value of a parameter specified by its name.
     * 
     * @param paramName The name of the parameter.
     * @return parameter value
     */
    public String getParameterValueByName(String paramName) {
        String[] paramNames = this.getType().getParamSlotNames();
        int i = 0;
        if (paramNames != null) {
            for (i = 0; i < paramNames.length; i++) {
                if (paramName.equals(paramNames[i])) {
                    break;
                }
            }

            if (i < this.paramSlotValues.length) {
                return paramSlotValues[i];
            }
        }
        return "";
    }
}