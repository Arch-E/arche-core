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
 * Describes a TreeElementType type, i.e., gives meta information that is associated with a generic
 * TreeElementType. TreeElementType are RF-independent and come from a separate XML configuration
 * 
 * @author Phil Bianco
 */
public class TreeElementTypeVO {

	   private String factType;
	   private String parentFactType;
	   private String parentSlotName;
	   private String childFactType;
	   private String childSlotName;
	   private String relationFactType;
	   private String name;
       private String slotNameToDisplay;
    /**
     * @param factType
     * @param lhsSlotName
     * @param rhsSlotName
     * @param paramSlotNames
     */
    public TreeElementTypeVO(String factType, String parentFactType, String parentSlotName,
                             String childFactType, String childSlotName, String relationFactType, String name,
                             String slotNameToDisplay) {
        super();
        this.factType = factType;
        this.parentFactType = parentFactType;
        this.parentSlotName = parentSlotName;
        this.childFactType = childFactType;
        this.childSlotName = childSlotName;
        this.relationFactType = relationFactType;
        this.name = name;
        this.slotNameToDisplay = slotNameToDisplay;
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
     * @return Returns the parentFactType.
     */
    public String getParentFactType() {
        return parentFactType;
    }

    /**
     * @param parentFactType The parentFactType to set.
     */
    public void setParentFactType(String parentFactType) {
        this.parentFactType = parentFactType;
    }

    /**
     * @return Returns the parentSlotName.
     */
    public String getParentSlotName() {
        return parentSlotName;
    }

    /**
     * @param parentSlotName The parentSlotName to set.
     */
    public void setParentSlotName(String parentSlotName) {
        this.parentSlotName = parentSlotName;
    }
    
    /**
     * @return Returns the childFactType.
     */
    public String getChildFactType() {
        return childFactType;
    }

    /**
     * @param childFactType The childFactType to set.
     */
    public void setChildFactType(String childFactType) {
        this.childFactType = childFactType;
    }

    /**
     * @return Returns the childSlotName.
     */
    public String getChildSlotName() {
        return childSlotName;
    }

    /**
     * @param childSlotName The childSlotName to set.
     */
    public void setChildSlotName(String childSlotName) {
        this.childSlotName = childSlotName;
    }
    
    /**
     * @return Returns the relationFactType.
     */
    public String getRelationFactType() {
        return relationFactType;
    }

    /**
     * @param relationFactType The relationFactType to set.
     */
    public void setRelationFactType(String relationFactType) {
        this.relationFactType = relationFactType;
    }
    
    /**
     * @return Returns the Name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name The Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Returns the slotNameToDisplay.
     */
    public String getSlotNameToDisplay() {
        return slotNameToDisplay;
    }

    /**
     * @param slotNameToDisplay The slotNameToDisplay to set.
     */
    public void setSlotNameToDisplay(String slotNameToDisplay) {
        this.slotNameToDisplay = slotNameToDisplay;
    }
    
}