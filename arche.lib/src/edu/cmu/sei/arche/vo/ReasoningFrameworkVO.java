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
 * Describes a reasoning framework, i.e., gives meta information that is associated with an RF. This
 * is not a fact in the fact base. It comes from the RF configuration file.
 * <p>
 * Property paramTypes is used internally by SEI.ArchE.Configurator. SEI.ArchE.UI should use
 * property respParamTypes to obtain the parameter types of responsibilities in that RF.
 * 
 * @author Alexander Berendeyev
 */
public class ReasoningFrameworkVO {

    /**
     * Id as informed by the user in ArchE configurator. It should map to the ID defined in ArchE
     * Core (otherwise ArchE UI cannot identify what kind of fact to use).
     */
    private String    id;

    /** Name that is used as label. */
    private String    name;

    /** Quality attribute supported. */
    private String    quality;
    
    /** Reserved for future use */
    private String    version;

    /** Parameter types of responsibilities defined for the RF. */
    private ArrayList respParamTypes     = new ArrayList();

    /** Relationship types of responsibilities defined for the RF. */
    private ArrayList relationshipTypes  = new ArrayList();

    /** Scenario types of responsibilities defined for the RF. */
    private ArrayList scenarioTypes      = new ArrayList();

    /**
     * Parameter types defined for the RF. The list will serve as the datasource for Parameter Types
     * screen of ArchE Configurator.
     * <p>
     * Property paramTypes is used internally by SEI.ArchE.Configurator. SEI.ArchE.UI should use
     * property respParamTypes to obtain the parameter types of responsibilities in that RF.
     */
    private ArrayList paramTypes         = new ArrayList();

    /** List of model element type definitions parsed from the RF config xml file. */
    private List      modelElementTypes  = new ArrayList();

    /** List of model relation type definitions parsed from the RF config xml file. */
    private List      modelRelationTypes = new ArrayList();

    /** List of view element type definitions parsed from the RF config xml file. */
    private List      viewElementTypes  = new ArrayList();

    /** List of view relation type definitions parsed from the RF config xml file. */
    private List      viewRelationTypes = new ArrayList();


    /** Full pathname to the folder where the RF plugin of this RF is installed */
    private String    installPathName;

    /** Name (relative to installPathName) of the XML file for this RF configuration */
    private String    rfConfigFileName;    
    
    /** Name (relative to installPathName) of the clpFile that loads all rules for this RF */
    private String    clpFile;

    public ReasoningFrameworkVO() {
    }

    /**
     * @param id
     * @param name
     * @param version
     */
    public ReasoningFrameworkVO(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }
    
    /**
     * @param id
     * @param quality
     * @param name
     * @param version
     */
    public ReasoningFrameworkVO(String id, String quality, String name, String version) {
        this.id = id;
        this.quality = quality;
        this.name = name;
        this.version = version;
    }

    /**
     * @return Returns the quality.
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality The quality to set.
     */
    public void setQuality(String quality) {
        this.quality = quality;
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
     * @return Returns the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version to set.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return Returns the respParamTypes.
     */
    public ArrayList getParamTypes() {
        return paramTypes;
    }

    /**
     * @return Returns the relationshipTypes.
     */
    public ArrayList getRelationshipTypes() {
        return relationshipTypes;
    }

    /**
     * @return Returns the respParamTypes.
     */
    public ArrayList getRespParamTypes() {
        return respParamTypes;
    }

    /**
     * @return Returns the scenarioTypes.
     */
    public ArrayList getScenarioTypes() {
        return scenarioTypes;
    }

    /**
     * @return Returns the modelElementTypes.
     */
    public List getModelElementTypes() {
        return modelElementTypes;
    }

    /**
     * @return Returns the modelRelationTypes.
     */
    public List getModelRelationTypes() {
        return modelRelationTypes;
    }

    /**
     * @return Returns the viewElementTypes.
     */
    public List getViewElementTypes() {
        return viewElementTypes;
    }

    /**
     * @return Returns the viewRelationTypes.
     */
    public List getViewRelationTypes() {
        return viewRelationTypes;
    }
    
    /**
     * @return Returns the clpFile.
     */
    public String getClpFile() {
        return clpFile;
    }

    /**
     * @param clpFile The clpFile to set.
     */
    public void setClpFile(String clpFile) {
        this.clpFile = clpFile;
    }

    /**
     * @return Returns the installPathName.
     */
    public String getInstallPathName() {
        return installPathName;
    }

    /**
     * @param installPathName The installPathName to set.
     */
    public void setInstallPathName(String installPathName) {
        this.installPathName = installPathName;
    }
    
    /**
     * @return Returns the rfConfigFileName.
     */
    public String getRFConfigFileName() {
        return rfConfigFileName;
    }
    
    /**
     * @param rfConfigFileName The rfConfigFileName to set.
     */
    public void setRFConfigFileName(String rfConfigFileName) {
        this.rfConfigFileName = rfConfigFileName;
    }

}