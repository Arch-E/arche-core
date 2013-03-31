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
 * Describes a Tree, i.e., gives meta information that is associated with a filter on the
 * design viewd. This is not a fact in the fact base. It comes from the tree configuration file.
 * 
 * @author Phil Bianco
 */
public class TreeVO {

    /** Id that is used as an identifier. */
    private String id;

    /** Name that is used as label. */
    private String name;
    
    /** Name that is used as label. */
    private String factType;

    /** List of design element type definitions parsed from the design config xml file. */
    private List   treeElementTypes;

   

    public TreeVO() {
    }

    /**
     * @param id
     * @param name
     */
    public TreeVO(String id, String name, String factType) {
        this.id = id;
        this.name = name;
        this.factType = factType;
        this.treeElementTypes = new ArrayList();
       
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
     * @param factType The factType to set.
     */
    public void setFactType(String factType) {
        this.factType = factType;
    }
    
    /**
     * @return Returns the factType.
     */
    public String getFactType() {
        return factType;
    }

  

    /**
     * @return Returns the designElementTypes.
     */
    public List getTreeElementTypes() {
        return treeElementTypes;
    }

   
}