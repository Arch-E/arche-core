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

import java.util.HashSet;
import java.util.Iterator;
import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * Corresponds to fact MAIN::Responsibilities in ArchE core.
 * 
 * @author Paulo Merson
 */
public class ResponsibilityVO extends CoreFact {

    /** Corresponds to slot name */
    private String  name;

    /** Corresponds to slot description */
    private String  description;

    /** Corresponds to slot source. Values can be CoreFact.NIL, .ARCHE_CORE or .USER. */
    private int     source;

    /** Set of ParameterVO objects--the parameters of this particular responsibility. */
    private HashSet parameters;

    /**
     * @param factId
     * @param description TODO
     * @param source
     * @param factType
     * @param description
     * @param parameters
     */
    public ResponsibilityVO(int factId, String name, String description, int source, IRefreshableUI ui) {
        super(factId, "MAIN::Responsibilities", ui);
        this.name = name;
        this.description = description;
        this.source = source;
        parameters = new HashSet();
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param description The name to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * @return Returns the parameters.
     */
    public HashSet getParameters() {
        return parameters;
    }

    /**
     * There's no need to call flagViewsToRefresh() because 'parameters' is not a slot that can
     * change. (When a parameter is created/changed for this responsibility, the parameter object
     * will call flagViewsToRefresh() in the constructor/setter method.
     * 
     * @param parameters The parameters to set.
     */
    public void setParameters(HashSet parameters) {
        this.parameters = parameters;
    }

    /**
     * This is a helper method to get the value of a parameter specified by its name.
     * 
     * @param paramName The name of the parameter.
     * @return parameter value
     */
    public String getParameterValueByName(String paramName) {
        Iterator it = getParameters().iterator();
        while (it.hasNext()) {
            ParameterVO p = (ParameterVO) (it.next());
            if (p.getParameterType().getName().equals(paramName)) {
                return p.getValue();
            }
        }
        return null;
    }

    /**
     * This is a helper method to set the value of a parameter specified by its name.
     * 
     * @param paramName The name of the parameter.
     */
    public void setParameterValueByName(String paramName, String paramValue) {
        Iterator it = getParameters().iterator();
        while (it.hasNext()) {
            ParameterVO p = (ParameterVO) (it.next());
            if (p.getParameterType().getName().equals(paramName)) {
                p.setValue(paramValue);
            }
        }
    }

    /**
     * This is a helper method to get the value of a parameter specified by its name.
     * 
     * @param paramName The name of the parameter.
     * @return ParameterVO
     */
    public ParameterVO getParameterByName(String paramName) {
        Iterator it = getParameters().iterator();
        while (it.hasNext()) {
            ParameterVO p = (ParameterVO) (it.next());
            if (p.getParameterType().getName().equals(paramName)) {
                return p;
            }
        }
        return null;
    }
}