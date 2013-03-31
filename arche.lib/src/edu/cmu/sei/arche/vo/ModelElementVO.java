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
 * Generic model element that is used in some RF.
 * <p>
 * 
 * @author Paulo Merson
 */
public class ModelElementVO extends CoreFact {

    /** Model element type for this actual model element value. */
    private ModelElementTypeVO type;

    /**
     * Name of this specific model element (e.g. "Halt door"). It's obtained by looking at the slot
     * defined by ModelElementTypeVO.nameSlotName.
     */
    private String             name;

    /**
     * Each element of the array is the value of a parameter of this model element. The parameters
     * are the ones defined by ModelElementTypeVO.paramSlotNames.
     */
    private String[]           paramSlotValues;

    /**
     * @param factId
     * @param factType
     * @param parameterType
     * @param value
     * @param source
     */
    public ModelElementVO(int factId, ModelElementTypeVO type, String name,
            String[] paramSlotValues, IRefreshableUI ui) {
        super(factId, type.getFactType(), ui);
        this.type = type;
        this.name = name;
        this.paramSlotValues = paramSlotValues;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the type.
     */
    public ModelElementTypeVO getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(ModelElementTypeVO type) {
        this.type = type;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
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