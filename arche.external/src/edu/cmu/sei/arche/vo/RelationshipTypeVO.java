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

/**
 * Describes a relationship, i.e., gives meta information that is associated with a relationship.
 * This is not a fact in the fact base. It comes from the RF configuration file. *
 * 
 * @author Alexander Berendeyev
 */
public class RelationshipTypeVO {

    /**
     * Id as informed by the user in ArchE configurator. It should map to the ID defined in ArchE
     * Core (otherwise ArchE UI cannot identify what kind of fact to use).
     */
    private String    id;

    /** Name that is used as label. */
    private String    name;

    /** Description is for tooltip or help text. */
    private String    description;

    /** Is relationship recursive */
    private boolean   recursive;

    /**
     * Each element in the list is an OperandsVO object, which contains a pair of types of operands
     * that can be used with this relationship type.
     */
    private List      allowedOperands = new ArrayList();

    /** Parameter types of the relationship. */
    private ArrayList paramTypes      = new ArrayList();
    
    /** Is relationship defined by an external reasoning framework*/
    private boolean external;

    /**
     * @param id
     * @param name
     * @param description
     * @param lhs
     * @param rhs
     * @param recursive
     */
    public RelationshipTypeVO(String id, String name, String description, boolean recursive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recursive = recursive;
        this.external = false;
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
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
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
    }

    /**
     * @return Returns the recursive.
     */
    public boolean isRecursive() {
        return recursive;
    }

    /**
     * @param recursive The recursive to set.
     */
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    /**
     * @return Returns the external.
     */
    public boolean isExternal() {
        return external;
    }

    /**
     * @param external The external to set.
     */
    public void setExternal(boolean external) {
        this.external = external;
    }    
    
    /**
     * @return Returns the paramTypes.
     */
    public ArrayList getParamTypes() {
        return paramTypes;
    }

    /**
     * @return Returns the allowedOperands.
     */
    public List getAllowedOperands() {
        return allowedOperands;
    }

}