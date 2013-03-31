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

import java.util.List;

/** Class _Versions was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Versions extends org.objectstyle.cayenne.CayenneDataObject {

    public static final String ID_PROPERTY = "ID";
    public static final String DATE_CREATED_PROPERTY = "dateCreated";
    public static final String FACT_SET_PROPERTY = "factSet";
    public static final String PARENT_PROPERTY = "parent";
    public static final String STORAGE_TYPE_PROPERTY = "storageType";
    public static final String VERSION_NAME_PROPERTY = "versionName";
    public static final String DESIGN_MODULE_ARRAY_PROPERTY = "designModuleArray";
    public static final String DESIGN_MODULEDEPENDENCYRELATION_ARRAY_PROPERTY = "designModuledependencyrelationArray";
    public static final String DESIGN_MODULEINTERFACE_ARRAY_PROPERTY = "designModuleinterfaceArray";
    public static final String DESIGN_MODULEREFINEMENTRELATION_ARRAY_PROPERTY = "designModulerefinementrelationArray";
    public static final String DESIGN_REALIZERELATION_ARRAY_PROPERTY = "designRealizerelationArray";
    public static final String DESIGN_RESPONSIBILITY_ARRAY_PROPERTY = "designResponsibilityArray";
    public static final String DESIGN_RESPONSIBILITYTOMODULERELATION_ARRAY_PROPERTY = "designResponsibilitytomodulerelationArray";
    public static final String DESIGN_SHAREDRESOURCE_ARRAY_PROPERTY = "designSharedresourceArray";
    public static final String DESIGN_SHAREDRESOURCERELATION_ARRAY_PROPERTY = "designSharedresourcerelationArray";
    public static final String DESIGN_VARIATIONPOINT_ARRAY_PROPERTY = "designVariationpointArray";
    public static final String DESIGN_VPINPUTVALUE_ARRAY_PROPERTY = "designVpinputvalueArray";
    public static final String DESIGN_WRAPPER_ARRAY_PROPERTY = "designWrapperArray";
    public static final String MAIN_FUNCTION_ARRAY_PROPERTY = "mainFunctionArray";
    public static final String MAIN_FUNCTIONREFINEMENTRELATION_ARRAY_PROPERTY = "mainFunctionrefinementrelationArray";
    public static final String MAIN_RESPONSIBILITIES_ARRAY_PROPERTY = "mainResponsibilitiesArray";
    public static final String MAIN_RESPONSIBILITYREFINEMENTRELATION_ARRAY_PROPERTY = "mainResponsibilityrefinementrelationArray";
    public static final String MAIN_RESPONSIBILITYTORESPONSIBILITYRELATION_ARRAY_PROPERTY = "mainResponsibilitytoresponsibilityrelationArray";
    public static final String MAIN_SCENARIOREFINEMENTRELATION_ARRAY_PROPERTY = "mainScenariorefinementrelationArray";
    public static final String MAIN_SCENARIOS_ARRAY_PROPERTY = "mainScenariosArray";
    public static final String MAIN_SEQUENCERELATION_ARRAY_PROPERTY = "mainSequencerelationArray";
    public static final String MAIN_TRANSLATIONRELATION_ARRAY_PROPERTY = "mainTranslationrelationArray";
    public static final String MODIFIABILITYREASONINGFRAMEWORKS_PCOSTOFCHANGE_ARRAY_PROPERTY = "modifiabilityreasoningframeworksPCostofchangeArray";
    public static final String MODIFIABILITYREASONINGFRAMEWORKS_PENCAPSULATIONLEVEL_ARRAY_PROPERTY = "modifiabilityreasoningframeworksPEncapsulationlevelArray";
    public static final String MODIFIABILITYREASONINGFRAMEWORKS_PPREPAREDFORCHANGE_ARRAY_PROPERTY = "modifiabilityreasoningframeworksPPreparedforchangeArray";
    public static final String MODIFIABILITYREASONINGFRAMEWORKS_PPROBABILITYINCOMING_ARRAY_PROPERTY = "modifiabilityreasoningframeworksPProbabilityincomingArray";
    public static final String MODIFIABILITYREASONINGFRAMEWORKS_PPROBABILITYOUTGOING_ARRAY_PROPERTY = "modifiabilityreasoningframeworksPProbabilityoutgoingArray";
    public static final String PATTERNS_DEPENDENCY_ARRAY_PROPERTY = "patternsDependencyArray";
    public static final String PATTERNS_INTERFACEREALIZATION_ARRAY_PROPERTY = "patternsInterfacerealizationArray";
    public static final String PATTERNS_ISARELATION_ARRAY_PROPERTY = "patternsIsarelationArray";
    public static final String PATTERNS_PATTERN_ARRAY_PROPERTY = "patternsPatternArray";
    public static final String PATTERNS_PATTERNCONNECTOR_ARRAY_PROPERTY = "patternsPatternconnectorArray";
    public static final String PATTERNS_PATTERNCONTAINER_ARRAY_PROPERTY = "patternsPatterncontainerArray";
    public static final String PATTERNS_PATTERNELEMENT_ARRAY_PROPERTY = "patternsPatternelementArray";
    public static final String PATTERNS_PATTERNELEMENTINTERFACE_ARRAY_PROPERTY = "patternsPatternelementinterfaceArray";
    public static final String PATTERNS_PATTERNITEMPROPERTY_ARRAY_PROPERTY = "patternsPatternitempropertyArray";
    public static final String PATTERNS_REFINEMENT_ARRAY_PROPERTY = "patternsRefinementArray";
    public static final String PERFORMANCEREASONINGFRAMEWORKS_PEXECUTIONTIME_ARRAY_PROPERTY = "performancereasoningframeworksPExecutiontimeArray";
    public static final String PERFORMANCEREASONINGFRAMEWORKS_PMUTUALEXCLUSION_ARRAY_PROPERTY = "performancereasoningframeworksPMutualexclusionArray";
    public static final String PERFORMANCEREASONINGFRAMEWORKS_PSHAREDRESOURCEASKED_ARRAY_PROPERTY = "performancereasoningframeworksPSharedresourceaskedArray";
    public static final String PLANNER_CADDFUNCTION_ARRAY_PROPERTY = "plannerCAddfunctionArray";
    public static final String PLANNER_CADDRESPONSIBILITYRELATION_ARRAY_PROPERTY = "plannerCAddresponsibilityrelationArray";
    public static final String PLANNER_CADDSCENARIO_ARRAY_PROPERTY = "plannerCAddscenarioArray";
    public static final String PLANNER_CADDTRANSLATIONRELATION_ARRAY_PROPERTY = "plannerCAddtranslationrelationArray";
    public static final String VARIABILITYREASONINGFRAMEWORK_VARIABILITYMECHANISMRESPONSIBILITY_ARRAY_PROPERTY = "variabilityreasoningframeworkVariabilitymechanismresponsibilityArray";
    public static final String VARIABILITYREASONINGFRAMEWORK_VPCONFIGURATION_ARRAY_PROPERTY = "variabilityreasoningframeworkVpconfigurationArray";

    public static final String ID_PK_COLUMN = "ID";

    public void setID(Integer ID) {
        writeProperty("ID", ID);
    }
    public Integer getID() {
        return (Integer)readProperty("ID");
    }
    
    
    public void setDateCreated(java.util.Date dateCreated) {
        writeProperty("dateCreated", dateCreated);
    }
    public java.util.Date getDateCreated() {
        return (java.util.Date)readProperty("dateCreated");
    }
    
    
    public void setFactSet(String factSet) {
        writeProperty("factSet", factSet);
    }
    public String getFactSet() {
        return (String)readProperty("factSet");
    }
    
    
    public void setParent(Integer parent) {
        writeProperty("parent", parent);
    }
    public Integer getParent() {
        return (Integer)readProperty("parent");
    }
    
    
    public void setStorageType(String storageType) {
        writeProperty("storageType", storageType);
    }
    public String getStorageType() {
        return (String)readProperty("storageType");
    }
    
    
    public void setVersionName(String versionName) {
        writeProperty("versionName", versionName);
    }
    public String getVersionName() {
        return (String)readProperty("versionName");
    }
    
    
    public void addToDesignModuleArray(DesignModule obj) {
        addToManyTarget("designModuleArray", obj, true);
    }
    public void removeFromDesignModuleArray(DesignModule obj) {
        removeToManyTarget("designModuleArray", obj, true);
    }
    public List getDesignModuleArray() {
        return (List)readProperty("designModuleArray");
    }
    
    
    public void addToDesignModuledependencyrelationArray(DesignModuledependencyrelation obj) {
        addToManyTarget("designModuledependencyrelationArray", obj, true);
    }
    public void removeFromDesignModuledependencyrelationArray(DesignModuledependencyrelation obj) {
        removeToManyTarget("designModuledependencyrelationArray", obj, true);
    }
    public List getDesignModuledependencyrelationArray() {
        return (List)readProperty("designModuledependencyrelationArray");
    }
    
    
    public void addToDesignModuleinterfaceArray(DesignModuleinterface obj) {
        addToManyTarget("designModuleinterfaceArray", obj, true);
    }
    public void removeFromDesignModuleinterfaceArray(DesignModuleinterface obj) {
        removeToManyTarget("designModuleinterfaceArray", obj, true);
    }
    public List getDesignModuleinterfaceArray() {
        return (List)readProperty("designModuleinterfaceArray");
    }
    
    
    public void addToDesignModulerefinementrelationArray(DesignModulerefinementrelation obj) {
        addToManyTarget("designModulerefinementrelationArray", obj, true);
    }
    public void removeFromDesignModulerefinementrelationArray(DesignModulerefinementrelation obj) {
        removeToManyTarget("designModulerefinementrelationArray", obj, true);
    }
    public List getDesignModulerefinementrelationArray() {
        return (List)readProperty("designModulerefinementrelationArray");
    }
    
    
    public void addToDesignRealizerelationArray(DesignRealizerelation obj) {
        addToManyTarget("designRealizerelationArray", obj, true);
    }
    public void removeFromDesignRealizerelationArray(DesignRealizerelation obj) {
        removeToManyTarget("designRealizerelationArray", obj, true);
    }
    public List getDesignRealizerelationArray() {
        return (List)readProperty("designRealizerelationArray");
    }
    
    
    public void addToDesignResponsibilityArray(DesignResponsibility obj) {
        addToManyTarget("designResponsibilityArray", obj, true);
    }
    public void removeFromDesignResponsibilityArray(DesignResponsibility obj) {
        removeToManyTarget("designResponsibilityArray", obj, true);
    }
    public List getDesignResponsibilityArray() {
        return (List)readProperty("designResponsibilityArray");
    }
    
    
    public void addToDesignResponsibilitytomodulerelationArray(DesignResponsibilitytomodulerelation obj) {
        addToManyTarget("designResponsibilitytomodulerelationArray", obj, true);
    }
    public void removeFromDesignResponsibilitytomodulerelationArray(DesignResponsibilitytomodulerelation obj) {
        removeToManyTarget("designResponsibilitytomodulerelationArray", obj, true);
    }
    public List getDesignResponsibilitytomodulerelationArray() {
        return (List)readProperty("designResponsibilitytomodulerelationArray");
    }
    
    
    public void addToDesignSharedresourceArray(DesignSharedresource obj) {
        addToManyTarget("designSharedresourceArray", obj, true);
    }
    public void removeFromDesignSharedresourceArray(DesignSharedresource obj) {
        removeToManyTarget("designSharedresourceArray", obj, true);
    }
    public List getDesignSharedresourceArray() {
        return (List)readProperty("designSharedresourceArray");
    }
    
    
    public void addToDesignSharedresourcerelationArray(DesignSharedresourcerelation obj) {
        addToManyTarget("designSharedresourcerelationArray", obj, true);
    }
    public void removeFromDesignSharedresourcerelationArray(DesignSharedresourcerelation obj) {
        removeToManyTarget("designSharedresourcerelationArray", obj, true);
    }
    public List getDesignSharedresourcerelationArray() {
        return (List)readProperty("designSharedresourcerelationArray");
    }
    
    
    public void addToDesignVariationpointArray(DesignVariationpoint obj) {
        addToManyTarget("designVariationpointArray", obj, true);
    }
    public void removeFromDesignVariationpointArray(DesignVariationpoint obj) {
        removeToManyTarget("designVariationpointArray", obj, true);
    }
    public List getDesignVariationpointArray() {
        return (List)readProperty("designVariationpointArray");
    }
    
    
    public void addToDesignVpinputvalueArray(DesignVpinputvalue obj) {
        addToManyTarget("designVpinputvalueArray", obj, true);
    }
    public void removeFromDesignVpinputvalueArray(DesignVpinputvalue obj) {
        removeToManyTarget("designVpinputvalueArray", obj, true);
    }
    public List getDesignVpinputvalueArray() {
        return (List)readProperty("designVpinputvalueArray");
    }
    
    
    public void addToDesignWrapperArray(DesignWrapper obj) {
        addToManyTarget("designWrapperArray", obj, true);
    }
    public void removeFromDesignWrapperArray(DesignWrapper obj) {
        removeToManyTarget("designWrapperArray", obj, true);
    }
    public List getDesignWrapperArray() {
        return (List)readProperty("designWrapperArray");
    }
    
    
    public void addToMainFunctionArray(MainFunction obj) {
        addToManyTarget("mainFunctionArray", obj, true);
    }
    public void removeFromMainFunctionArray(MainFunction obj) {
        removeToManyTarget("mainFunctionArray", obj, true);
    }
    public List getMainFunctionArray() {
        return (List)readProperty("mainFunctionArray");
    }
    
    
    public void addToMainFunctionrefinementrelationArray(MainFunctionrefinementrelation obj) {
        addToManyTarget("mainFunctionrefinementrelationArray", obj, true);
    }
    public void removeFromMainFunctionrefinementrelationArray(MainFunctionrefinementrelation obj) {
        removeToManyTarget("mainFunctionrefinementrelationArray", obj, true);
    }
    public List getMainFunctionrefinementrelationArray() {
        return (List)readProperty("mainFunctionrefinementrelationArray");
    }
    
    
    public void addToMainResponsibilitiesArray(MainResponsibilities obj) {
        addToManyTarget("mainResponsibilitiesArray", obj, true);
    }
    public void removeFromMainResponsibilitiesArray(MainResponsibilities obj) {
        removeToManyTarget("mainResponsibilitiesArray", obj, true);
    }
    public List getMainResponsibilitiesArray() {
        return (List)readProperty("mainResponsibilitiesArray");
    }
    
    
    public void addToMainResponsibilityrefinementrelationArray(MainResponsibilityrefinementrelation obj) {
        addToManyTarget("mainResponsibilityrefinementrelationArray", obj, true);
    }
    public void removeFromMainResponsibilityrefinementrelationArray(MainResponsibilityrefinementrelation obj) {
        removeToManyTarget("mainResponsibilityrefinementrelationArray", obj, true);
    }
    public List getMainResponsibilityrefinementrelationArray() {
        return (List)readProperty("mainResponsibilityrefinementrelationArray");
    }
    
    
    public void addToMainResponsibilitytoresponsibilityrelationArray(MainResponsibilitytoresponsibilityrelation obj) {
        addToManyTarget("mainResponsibilitytoresponsibilityrelationArray", obj, true);
    }
    public void removeFromMainResponsibilitytoresponsibilityrelationArray(MainResponsibilitytoresponsibilityrelation obj) {
        removeToManyTarget("mainResponsibilitytoresponsibilityrelationArray", obj, true);
    }
    public List getMainResponsibilitytoresponsibilityrelationArray() {
        return (List)readProperty("mainResponsibilitytoresponsibilityrelationArray");
    }
    
    
    public void addToMainScenariorefinementrelationArray(MainScenariorefinementrelation obj) {
        addToManyTarget("mainScenariorefinementrelationArray", obj, true);
    }
    public void removeFromMainScenariorefinementrelationArray(MainScenariorefinementrelation obj) {
        removeToManyTarget("mainScenariorefinementrelationArray", obj, true);
    }
    public List getMainScenariorefinementrelationArray() {
        return (List)readProperty("mainScenariorefinementrelationArray");
    }
    
    
    public void addToMainScenariosArray(MainScenarios obj) {
        addToManyTarget("mainScenariosArray", obj, true);
    }
    public void removeFromMainScenariosArray(MainScenarios obj) {
        removeToManyTarget("mainScenariosArray", obj, true);
    }
    public List getMainScenariosArray() {
        return (List)readProperty("mainScenariosArray");
    }
    
    
    public void addToMainSequencerelationArray(MainSequencerelation obj) {
        addToManyTarget("mainSequencerelationArray", obj, true);
    }
    public void removeFromMainSequencerelationArray(MainSequencerelation obj) {
        removeToManyTarget("mainSequencerelationArray", obj, true);
    }
    public List getMainSequencerelationArray() {
        return (List)readProperty("mainSequencerelationArray");
    }
    
    
    public void addToMainTranslationrelationArray(MainTranslationrelation obj) {
        addToManyTarget("mainTranslationrelationArray", obj, true);
    }
    public void removeFromMainTranslationrelationArray(MainTranslationrelation obj) {
        removeToManyTarget("mainTranslationrelationArray", obj, true);
    }
    public List getMainTranslationrelationArray() {
        return (List)readProperty("mainTranslationrelationArray");
    }
    
    
    public void addToModifiabilityreasoningframeworksPCostofchangeArray(ModifiabilityreasoningframeworksPCostofchange obj) {
        addToManyTarget("modifiabilityreasoningframeworksPCostofchangeArray", obj, true);
    }
    public void removeFromModifiabilityreasoningframeworksPCostofchangeArray(ModifiabilityreasoningframeworksPCostofchange obj) {
        removeToManyTarget("modifiabilityreasoningframeworksPCostofchangeArray", obj, true);
    }
    public List getModifiabilityreasoningframeworksPCostofchangeArray() {
        return (List)readProperty("modifiabilityreasoningframeworksPCostofchangeArray");
    }
    
    
    public void addToModifiabilityreasoningframeworksPEncapsulationlevelArray(ModifiabilityreasoningframeworksPEncapsulationlevel obj) {
        addToManyTarget("modifiabilityreasoningframeworksPEncapsulationlevelArray", obj, true);
    }
    public void removeFromModifiabilityreasoningframeworksPEncapsulationlevelArray(ModifiabilityreasoningframeworksPEncapsulationlevel obj) {
        removeToManyTarget("modifiabilityreasoningframeworksPEncapsulationlevelArray", obj, true);
    }
    public List getModifiabilityreasoningframeworksPEncapsulationlevelArray() {
        return (List)readProperty("modifiabilityreasoningframeworksPEncapsulationlevelArray");
    }
    
    
    public void addToModifiabilityreasoningframeworksPPreparedforchangeArray(ModifiabilityreasoningframeworksPPreparedforchange obj) {
        addToManyTarget("modifiabilityreasoningframeworksPPreparedforchangeArray", obj, true);
    }
    public void removeFromModifiabilityreasoningframeworksPPreparedforchangeArray(ModifiabilityreasoningframeworksPPreparedforchange obj) {
        removeToManyTarget("modifiabilityreasoningframeworksPPreparedforchangeArray", obj, true);
    }
    public List getModifiabilityreasoningframeworksPPreparedforchangeArray() {
        return (List)readProperty("modifiabilityreasoningframeworksPPreparedforchangeArray");
    }
    
    
    public void addToModifiabilityreasoningframeworksPProbabilityincomingArray(ModifiabilityreasoningframeworksPProbabilityincoming obj) {
        addToManyTarget("modifiabilityreasoningframeworksPProbabilityincomingArray", obj, true);
    }
    public void removeFromModifiabilityreasoningframeworksPProbabilityincomingArray(ModifiabilityreasoningframeworksPProbabilityincoming obj) {
        removeToManyTarget("modifiabilityreasoningframeworksPProbabilityincomingArray", obj, true);
    }
    public List getModifiabilityreasoningframeworksPProbabilityincomingArray() {
        return (List)readProperty("modifiabilityreasoningframeworksPProbabilityincomingArray");
    }
    
    
    public void addToModifiabilityreasoningframeworksPProbabilityoutgoingArray(ModifiabilityreasoningframeworksPProbabilityoutgoing obj) {
        addToManyTarget("modifiabilityreasoningframeworksPProbabilityoutgoingArray", obj, true);
    }
    public void removeFromModifiabilityreasoningframeworksPProbabilityoutgoingArray(ModifiabilityreasoningframeworksPProbabilityoutgoing obj) {
        removeToManyTarget("modifiabilityreasoningframeworksPProbabilityoutgoingArray", obj, true);
    }
    public List getModifiabilityreasoningframeworksPProbabilityoutgoingArray() {
        return (List)readProperty("modifiabilityreasoningframeworksPProbabilityoutgoingArray");
    }
    
    
    public void addToPatternsDependencyArray(PatternsDependency obj) {
        addToManyTarget("patternsDependencyArray", obj, true);
    }
    public void removeFromPatternsDependencyArray(PatternsDependency obj) {
        removeToManyTarget("patternsDependencyArray", obj, true);
    }
    public List getPatternsDependencyArray() {
        return (List)readProperty("patternsDependencyArray");
    }
    
    
    public void addToPatternsInterfacerealizationArray(PatternsInterfacerealization obj) {
        addToManyTarget("patternsInterfacerealizationArray", obj, true);
    }
    public void removeFromPatternsInterfacerealizationArray(PatternsInterfacerealization obj) {
        removeToManyTarget("patternsInterfacerealizationArray", obj, true);
    }
    public List getPatternsInterfacerealizationArray() {
        return (List)readProperty("patternsInterfacerealizationArray");
    }
    
    
    public void addToPatternsIsarelationArray(PatternsIsarelation obj) {
        addToManyTarget("patternsIsarelationArray", obj, true);
    }
    public void removeFromPatternsIsarelationArray(PatternsIsarelation obj) {
        removeToManyTarget("patternsIsarelationArray", obj, true);
    }
    public List getPatternsIsarelationArray() {
        return (List)readProperty("patternsIsarelationArray");
    }
    
    
    public void addToPatternsPatternArray(PatternsPattern obj) {
        addToManyTarget("patternsPatternArray", obj, true);
    }
    public void removeFromPatternsPatternArray(PatternsPattern obj) {
        removeToManyTarget("patternsPatternArray", obj, true);
    }
    public List getPatternsPatternArray() {
        return (List)readProperty("patternsPatternArray");
    }
    
    
    public void addToPatternsPatternconnectorArray(PatternsPatternconnector obj) {
        addToManyTarget("patternsPatternconnectorArray", obj, true);
    }
    public void removeFromPatternsPatternconnectorArray(PatternsPatternconnector obj) {
        removeToManyTarget("patternsPatternconnectorArray", obj, true);
    }
    public List getPatternsPatternconnectorArray() {
        return (List)readProperty("patternsPatternconnectorArray");
    }
    
    
    public void addToPatternsPatterncontainerArray(PatternsPatterncontainer obj) {
        addToManyTarget("patternsPatterncontainerArray", obj, true);
    }
    public void removeFromPatternsPatterncontainerArray(PatternsPatterncontainer obj) {
        removeToManyTarget("patternsPatterncontainerArray", obj, true);
    }
    public List getPatternsPatterncontainerArray() {
        return (List)readProperty("patternsPatterncontainerArray");
    }
    
    
    public void addToPatternsPatternelementArray(PatternsPatternelement obj) {
        addToManyTarget("patternsPatternelementArray", obj, true);
    }
    public void removeFromPatternsPatternelementArray(PatternsPatternelement obj) {
        removeToManyTarget("patternsPatternelementArray", obj, true);
    }
    public List getPatternsPatternelementArray() {
        return (List)readProperty("patternsPatternelementArray");
    }
    
    
    public void addToPatternsPatternelementinterfaceArray(PatternsPatternelementinterface obj) {
        addToManyTarget("patternsPatternelementinterfaceArray", obj, true);
    }
    public void removeFromPatternsPatternelementinterfaceArray(PatternsPatternelementinterface obj) {
        removeToManyTarget("patternsPatternelementinterfaceArray", obj, true);
    }
    public List getPatternsPatternelementinterfaceArray() {
        return (List)readProperty("patternsPatternelementinterfaceArray");
    }
    
    
    public void addToPatternsPatternitempropertyArray(PatternsPatternitemproperty obj) {
        addToManyTarget("patternsPatternitempropertyArray", obj, true);
    }
    public void removeFromPatternsPatternitempropertyArray(PatternsPatternitemproperty obj) {
        removeToManyTarget("patternsPatternitempropertyArray", obj, true);
    }
    public List getPatternsPatternitempropertyArray() {
        return (List)readProperty("patternsPatternitempropertyArray");
    }
    
    
    public void addToPatternsRefinementArray(PatternsRefinement obj) {
        addToManyTarget("patternsRefinementArray", obj, true);
    }
    public void removeFromPatternsRefinementArray(PatternsRefinement obj) {
        removeToManyTarget("patternsRefinementArray", obj, true);
    }
    public List getPatternsRefinementArray() {
        return (List)readProperty("patternsRefinementArray");
    }
    
    
    public void addToPerformancereasoningframeworksPExecutiontimeArray(PerformancereasoningframeworksPExecutiontime obj) {
        addToManyTarget("performancereasoningframeworksPExecutiontimeArray", obj, true);
    }
    public void removeFromPerformancereasoningframeworksPExecutiontimeArray(PerformancereasoningframeworksPExecutiontime obj) {
        removeToManyTarget("performancereasoningframeworksPExecutiontimeArray", obj, true);
    }
    public List getPerformancereasoningframeworksPExecutiontimeArray() {
        return (List)readProperty("performancereasoningframeworksPExecutiontimeArray");
    }
    
    
    public void addToPerformancereasoningframeworksPMutualexclusionArray(PerformancereasoningframeworksPMutualexclusion obj) {
        addToManyTarget("performancereasoningframeworksPMutualexclusionArray", obj, true);
    }
    public void removeFromPerformancereasoningframeworksPMutualexclusionArray(PerformancereasoningframeworksPMutualexclusion obj) {
        removeToManyTarget("performancereasoningframeworksPMutualexclusionArray", obj, true);
    }
    public List getPerformancereasoningframeworksPMutualexclusionArray() {
        return (List)readProperty("performancereasoningframeworksPMutualexclusionArray");
    }
    
    
    public void addToPerformancereasoningframeworksPSharedresourceaskedArray(PerformancereasoningframeworksPSharedresourceasked obj) {
        addToManyTarget("performancereasoningframeworksPSharedresourceaskedArray", obj, true);
    }
    public void removeFromPerformancereasoningframeworksPSharedresourceaskedArray(PerformancereasoningframeworksPSharedresourceasked obj) {
        removeToManyTarget("performancereasoningframeworksPSharedresourceaskedArray", obj, true);
    }
    public List getPerformancereasoningframeworksPSharedresourceaskedArray() {
        return (List)readProperty("performancereasoningframeworksPSharedresourceaskedArray");
    }
    
    
    public void addToPlannerCAddfunctionArray(PlannerCAddfunction obj) {
        addToManyTarget("plannerCAddfunctionArray", obj, true);
    }
    public void removeFromPlannerCAddfunctionArray(PlannerCAddfunction obj) {
        removeToManyTarget("plannerCAddfunctionArray", obj, true);
    }
    public List getPlannerCAddfunctionArray() {
        return (List)readProperty("plannerCAddfunctionArray");
    }
    
    
    public void addToPlannerCAddresponsibilityrelationArray(PlannerCAddresponsibilityrelation obj) {
        addToManyTarget("plannerCAddresponsibilityrelationArray", obj, true);
    }
    public void removeFromPlannerCAddresponsibilityrelationArray(PlannerCAddresponsibilityrelation obj) {
        removeToManyTarget("plannerCAddresponsibilityrelationArray", obj, true);
    }
    public List getPlannerCAddresponsibilityrelationArray() {
        return (List)readProperty("plannerCAddresponsibilityrelationArray");
    }
    
    
    public void addToPlannerCAddscenarioArray(PlannerCAddscenario obj) {
        addToManyTarget("plannerCAddscenarioArray", obj, true);
    }
    public void removeFromPlannerCAddscenarioArray(PlannerCAddscenario obj) {
        removeToManyTarget("plannerCAddscenarioArray", obj, true);
    }
    public List getPlannerCAddscenarioArray() {
        return (List)readProperty("plannerCAddscenarioArray");
    }
    
    
    public void addToPlannerCAddtranslationrelationArray(PlannerCAddtranslationrelation obj) {
        addToManyTarget("plannerCAddtranslationrelationArray", obj, true);
    }
    public void removeFromPlannerCAddtranslationrelationArray(PlannerCAddtranslationrelation obj) {
        removeToManyTarget("plannerCAddtranslationrelationArray", obj, true);
    }
    public List getPlannerCAddtranslationrelationArray() {
        return (List)readProperty("plannerCAddtranslationrelationArray");
    }
    
    
    public void addToVariabilityreasoningframeworkVariabilitymechanismresponsibilityArray(VariabilityreasoningframeworkVariabilitymechanismresponsibility obj) {
        addToManyTarget("variabilityreasoningframeworkVariabilitymechanismresponsibilityArray", obj, true);
    }
    public void removeFromVariabilityreasoningframeworkVariabilitymechanismresponsibilityArray(VariabilityreasoningframeworkVariabilitymechanismresponsibility obj) {
        removeToManyTarget("variabilityreasoningframeworkVariabilitymechanismresponsibilityArray", obj, true);
    }
    public List getVariabilityreasoningframeworkVariabilitymechanismresponsibilityArray() {
        return (List)readProperty("variabilityreasoningframeworkVariabilitymechanismresponsibilityArray");
    }
    
    
    public void addToVariabilityreasoningframeworkVpconfigurationArray(VariabilityreasoningframeworkVpconfiguration obj) {
        addToManyTarget("variabilityreasoningframeworkVpconfigurationArray", obj, true);
    }
    public void removeFromVariabilityreasoningframeworkVpconfigurationArray(VariabilityreasoningframeworkVpconfiguration obj) {
        removeToManyTarget("variabilityreasoningframeworkVpconfigurationArray", obj, true);
    }
    public List getVariabilityreasoningframeworkVpconfigurationArray() {
        return (List)readProperty("variabilityreasoningframeworkVpconfigurationArray");
    }
    
    
}
