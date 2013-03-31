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
 * Describes a scenario type, i.e., gives meta information that is associated with a scenario. This
 * is not a fact in the fact base. It comes from the RF configuration file. *
 * 
 * @author Alexander Berendeyev
 */
public class ScenarioTypeVO {

    /**
     * Id as informed by the user in ArchE configurator. It should map to the fact type for
     * scenarios of this type (otherwise ArchE UI cannot identify what kind of fact to use).
     */
    private String                   id;

    /** Name that is used as label. */
    private String                   name;

    /** Description is for tooltip or help text. */
    private String                   description;

    /** ID of reasoning framework associated with this scenario type */
    private String                   rfID;
    
    /**
     * PartsMetadata contain the following data about scenario types: stimulus, source of stimulus,
     * environment, artifact, response and response measure.
     */
    private ScenarioPartMetadataVO[] partsMetadata = new ScenarioPartMetadataVO[6];

    /**
     * @param id
     * @param name
     * @param description
     */
    public ScenarioTypeVO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * @return Returns the partsMetadata.
     */
    public ScenarioPartMetadataVO[] getPartsMetadata() {
        return partsMetadata;
    }

    /**
     * @param rfID The RF's ID to set.
     */
    public void setRFID(String rfID) {
        this.rfID = rfID;
    }

    /**
     * @return Returns the RF's ID.
     */
	public String getRFID() {
		return rfID;
	}
}