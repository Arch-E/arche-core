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
import java.util.List;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * Corresponds to different facts in ArchE Core. The core fact type corresponds to the id of the
 * RelationshipTypeVO that is a property of the this relationship vo. Examples of core facts that
 * are relationships:
 * <ul>
 * <li>MAIN::SequenceRelation
 * <li>MAIN::ResponsibilityToResponsibilityRelation
 * <li>MAIN::ResponsibilityRefinementRelation
 * </ul>
 * In all these facts, the relationship is a relationship between responsibilitys iff both elements
 * being related (aka parent and child, or lhs and rhs) are responsibilities.
 * <p>
 * All relationship facts in the core have at least the following slots that are used to fill the VO
 * properties:
 * <ul>
 * <li>source
 * <li>parent
 * <li>child
 * </ul>
 * 
 * @author Paulo Merson
 */
public class RelationshipVO extends CoreFact {

    /**
     * Defines the type of the relationship. Note that type.id should match the fact type in the
     * core that originated this relationship vo.
     */
    private RelationshipTypeVO type;

    /** Corresponds to slot source. Values can be CoreFact.NIL, .ARCHE_CORE or .USER. */
    private int                source;

    /** Corresponds to slot parent. */
    private int                parentFactId;

    /** Corresponds to slot child. */
    private int                childFactId;

    /** set of parameter values of this relationship. */
    private List               parameters;

    /**
     * @param factId
     * @param factType
     * @param type
     * @param source
     * @param parentFactId
     * @param childFactId
     */
    public RelationshipVO(int factId, String factType, RelationshipTypeVO type, int source,
            int parentFactId, int childFactId, IRefreshableUI ui) {
        super(factId, factType, ui);
        this.type = type;
        this.source = source;
        this.parentFactId = parentFactId;
        this.childFactId = childFactId;
        parameters = new ArrayList();
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the parentFactId.
     */
    public int getParentFactId() {
        return parentFactId;
    }

    /**
     * @param parentFactId The parentFactId to set.
     */
    public void setParentFactId(int parentFactId) {
        this.parentFactId = parentFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the childFactId.
     */
    public int getChildFactId() {
        return childFactId;
    }

    /**
     * @param childFactId The childFactId to set.
     */
    public void setChildFactId(int childFactId) {
        this.childFactId = childFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the parameters.
     */
    public List getParameters() {
        return parameters;
    }

    /**
     * There's no need to call flagViewsToRefresh() because 'parameters' is not a slot that can
     * change. (When a parameter is created/changed for this relationship, the parameter object will
     * call flagViewsToRefresh() in the constructor/setter method.
     * 
     * @param parameters The parameters to set.
     */
    public void setParameters(List parameters) {
        this.parameters = parameters;
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
     * @return Returns the type.
     */
    public RelationshipTypeVO getType() {
        return type;
    }

    /**
     * The relationship type is not a slot, so there's not need to flag views to refresh.
     * 
     * @param type The type to set.
     */
    public void setType(RelationshipTypeVO type) {
        this.type = type;
    }

}