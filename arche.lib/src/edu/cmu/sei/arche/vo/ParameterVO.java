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
 * A parameter value associated with a responsibility or relationship. Parameters don't map to a
 * single type of fact in the fact base. For each parameter type there is one types of fact. For
 * example, execution time is a parameter of responsibilities in the performance RF. In the fact
 * base they are stored as facts of type PerformanceReasoningFrameworks::P_ExecutionTime. In
 * general. facts that have the prefix "P_" are parameters. All this facts have at least these
 * fields:
 * <ul>
 * <li>owner: id of the fact that owns this parameter. Used in VOUpdate to map add ParameterVO
 * object to the proper ResponsibilityVO or RelationshipVO object.
 * <li>value: goes to property value
 * <li>source: translated to property source
 * </ul>
 * ParameterVO has ownerFactId and ResponsibilityVO has parameters. So, they both point to each
 * other. This redundancy is needed because when a parameter is asserted, the responsibility may not
 * exist yet--issue related to the order of loading facts.
 * 
 * @author Paulo Merson
 */
public class ParameterVO extends CoreFact {

    /** Indicates the parameter type for this parameter value. */
    private ParameterTypeVO parameterType;

    /**
     * Value of the parameter (slot value). All slot values in Jess are strings, although the data
     * stored may be a number or boolean.
     */
    private String          value;

    /** Corresponds to slot source. Values can be CoreFact.NIL, .ARCHE_CORE or .USER. */
    private int             source;

    /**
     * Fact id of the responsibility or relationship that this parameter is assigned to.
     * <p>
     * Note that ParameterVO has ownerFactId and ResponsibilityVO has parameters. So, they both
     * point to each other. This redundancy is needed because when a parameter is asserted, the
     * responsibility may not exist yet (issue related to the order of loading facts).
     */
    private int             ownerFactId;

    /**
     * @param factId
     * @param factType
     * @param parameterType
     * @param value
     * @param source
     */
    public ParameterVO(int factId, ParameterTypeVO parameterType, String value, int source,
            int ownerFactId, IRefreshableUI ui) {
        super(factId, parameterType.getId(), ui);
        this.parameterType = parameterType;
        this.value = value;
        this.source = source;
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

    /**
     * @return Returns the parameterType.
     */
    public ParameterTypeVO getParameterType() {
        return parameterType;
    }

    /**
     * The parameter type is not a slot, so there's no need to flag views to refresh.
     * 
     * @param parameterType The parameterType to set.
     */
    public void setParameterType(ParameterTypeVO parameterType) {
        this.parameterType = parameterType;
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
}