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

package edu.cmu.sei.ORM.model;

/** Class _PatternsPatternitemproperty was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _PatternsPatternitemproperty extends org.objectstyle.cayenne.CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String FACTID_PROPERTY = "factid";
    public static final String NAME_PROPERTY = "name";
    public static final String OWNER_PROPERTY = "owner";
    public static final String PATTERN_PROPERTY = "pattern";
    public static final String VALUE_ABS_PROPERTY = "valueAbs";
    public static final String VALUE_FACTOR_PROPERTY = "valueFactor";
    public static final String TO_VERSIONS_PROPERTY = "toVersions";

    public static final String UID_PK_COLUMN = "uid";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }
    
    
    public void setFactid(String factid) {
        writeProperty("factid", factid);
    }
    public String getFactid() {
        return (String)readProperty("factid");
    }
    
    
    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }
    
    
    public void setOwner(byte[] owner) {
        writeProperty("owner", owner);
    }
    public byte[] getOwner() {
        return (byte[])readProperty("owner");
    }
    
    
    public void setPattern(byte[] pattern) {
        writeProperty("pattern", pattern);
    }
    public byte[] getPattern() {
        return (byte[])readProperty("pattern");
    }
    
    
    public void setValueAbs(String valueAbs) {
        writeProperty("valueAbs", valueAbs);
    }
    public String getValueAbs() {
        return (String)readProperty("valueAbs");
    }
    
    
    public void setValueFactor(String valueFactor) {
        writeProperty("valueFactor", valueFactor);
    }
    public String getValueFactor() {
        return (String)readProperty("valueFactor");
    }
    
    
    public void setToVersions(Versions toVersions) {
        setToOneTarget("toVersions", toVersions, true);
    }

    public Versions getToVersions() {
        return (Versions)readProperty("toVersions");
    } 
    
    
}
