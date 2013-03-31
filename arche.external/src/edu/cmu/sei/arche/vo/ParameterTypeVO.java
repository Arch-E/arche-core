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
 * Describes a parameter type, i.e., gives meta information that is associated with a parameter
 * value. This is not a fact in the fact base. It comes from the RF configuration file.
 * 
 * @author Paulo Merson
 */
public class ParameterTypeVO {

    /**
     * Id as informed by the user in ArchE configurator. It should map to the fact type for
     * parameter values of this parameter type (otherwise ArchE UI cannot identify what kind of fact
     * to use). Therefore, an example of id would be
     * "PerformanceReasoningFrameworks::P_ExecutionTime" (?).
     */
    private String          id;

    /** Name that is used as label for parameters of this type. */
    private String          name;

    /** Description is for tooltip or help text. */
    private String          description;

    /**
     * A parameter value of this parameter type will have the data type defined by this property. It
     * can be double, string or boolean. Based on this type, ArchE UI can validate and present data
     * propertly.
     */
    private int             dataType;

    /** Default value when a parameter is created. */
    private String          defaultValue;

    
    /** Constant used for dataType when it's to be validated as a double */
    public static final int DOUBLE  = 1;

    /** Constant used for dataType when it's to be validated as a string */
    public static final int STRING  = 2;

    /** Constant used for dataType when it's to be validated as a boolean */
    public static final int BOOLEAN = 3;

    /** Constant used for dataType when it's to be validated as a integer */
    public static final int INTEGER  = 4;
    /**
     * @param id
     * @param name
     * @param description
     * @param dataType
     * @param defaultValue
     */
    public ParameterTypeVO(String id, String name, String description, int dataType,
            String defaultValue) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.defaultValue = defaultValue;
    }

    /**
     * @return Returns the dataType.
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * @param dataType The dataType to set.
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
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
     * @return Returns the defaultValue.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue The defaultValue to set.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}