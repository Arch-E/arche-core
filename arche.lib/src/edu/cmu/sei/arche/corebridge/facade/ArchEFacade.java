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

package edu.cmu.sei.arche.corebridge.facade;

import java.util.List;

import jess.JessException;
import jess.Rete;

import org.eclipse.core.runtime.IPath;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.corebridge.interactions.FunctionInteractions;
import edu.cmu.sei.arche.corebridge.interactions.LifeCycleInteractions;
import edu.cmu.sei.arche.corebridge.interactions.MiscellanyInteractions;
import edu.cmu.sei.arche.corebridge.interactions.QuestionInteractions;
import edu.cmu.sei.arche.corebridge.interactions.RespoRespoRelationsInteractions;
import edu.cmu.sei.arche.corebridge.interactions.ResponsibilityInteractions;
import edu.cmu.sei.arche.corebridge.interactions.ScFnRespoRelationsInteractions;
import edu.cmu.sei.arche.corebridge.interactions.ScenarioInteractions;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.RelationshipVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;
import edu.cmu.sei.arche.vo.ScenarioPartMetadataVO;

/**
 * The Facade resolves calls to the ArchE Core and passes the centrally located Rete Object to the
 * function calls.
 * 
 * @author Neel Mullick
 */

public class ArchEFacade {

    /**
     * In ScenarioInteractions, the data fields of a scenario are store in an array. This constant
     * defines what is the position of each scenario part within that array.
     * 
     * @see edu.cmu.sei.arche.corebridge.interactions.ScenarioInteractions#ScenarioInteractions()
     */
    public static final int[]               INDEX_OF_PART_IN_SCENARIO_ARRAY = new int[6];
    static {
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.STIMULUS] = 2;
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.SOURCE_OF_STIMULUS] = 6;
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.ARTIFACT] = 10;
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.ENVIRONMENT] = 14;
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.RESPONSE] = 18;
        INDEX_OF_PART_IN_SCENARIO_ARRAY[ScenarioPartMetadataVO.RESPONSE_MEASURE] = 22;
    }

    /** Centrally located Rete Object, also shared instance that is used all over */
    private static Rete                     engineArchE = null;

    /** Object responsible for all project lifecycle interactions */
    private LifeCycleInteractions           lifeArchE;

    /**
     * Object responsible for all interactions related to the "responsibility" architectural
     * elements
     */
    private ResponsibilityInteractions      responsibilitiesArchE;

    /**
     * Object responsible for all interactions related to the "responsibility relation"
     * architectural elements
     */
    private RespoRespoRelationsInteractions respoRelationsArchE;

    /**
     * Object responsible for all interactions related to the "scenario/function to responsibility
     * relation" architectural elements. This maps to translation relations in the core.
     */
    private ScFnRespoRelationsInteractions  scfnRespoArchE;

    /**
     * Object responsible for all interactions related to the "Scenario" architectural elements.
     * This maps to MAIN::Scenarios in the Core.
     */
    private ScenarioInteractions            scenarioArchE;

    /**
     * Object responsible for all interactions related to the "function" architectural element
     */
    private FunctionInteractions            functionArchE;

    /**
     * Object responsible for all miscellaneous (other) interactions with the Core.
     */
    private MiscellanyInteractions          miscellanyArchE;

    /**
     * Object responsible for all miscellaneous (other) interactions with the Core.
     */
    private QuestionInteractions            questionArchE;

    /** Base absolute path for the workspace * */
    private String                          baseLocation;

    /**
     * Full path of the project used to persist the fact base.
     */
    private IPath                          pathProject;

    /**
     * Represents the UI layer that is currently displaying the data of the factbase that will
     * accessed by this facade instance. After each facade transaction that affects data in the
     * views, refreshViews() in the UI is called.
     */
    private IRefreshableUI                  ui;

    /**
     * Initializes the objects responsible for actually interacting with the ArchE Core.
     */
    public ArchEFacade(QuestionParser questionParser) {
        lifeArchE = new LifeCycleInteractions();
        responsibilitiesArchE = new ResponsibilityInteractions();
        respoRelationsArchE = new RespoRespoRelationsInteractions();
        scfnRespoArchE = new ScFnRespoRelationsInteractions();
        scenarioArchE = new ScenarioInteractions();
        functionArchE = new FunctionInteractions();
        miscellanyArchE = new MiscellanyInteractions();
        questionArchE = new QuestionInteractions(questionParser);
    }

    /**
     * Resolves the project startup command to the startupRete method. The Rete object to be
     * centrally located is returned by the startupRete method.
     * 
     * @param pathProject full path of the project
     * @param ui represents the UI layer that is currently displaying the data of the factbase that
     *            will accessed by this facade instance. The reference to the UI is needed further
     *            ahead by the VO objects so that they know what UI to call to flag views to
     *            refresh.
     * @param project The ProjectVO object is the entry point to access all the facts in memory.
     *            VOUpdate will later on need this reference so that when a new fact is
     *            asserted/modified/retracted, we can add/change/remove the corresponding VO to this
     *            project.
     * @param designElementTypes List of design element type definitions
     * @param designRelationTypes List of design relation type definitions
     * @throws IllegalStateException in case it's trying to startup project that is already started
     * @throws ArchEException in case Jess could not start the project.
     * @throws IllegalArgumentException if ui or project is null
     */
    public void startupArchE(IPath pathProject, IRefreshableUI ui, ProjectVO project,
            List designElementTypes, List designRelationTypes, List trees) throws ArchEException {
//        if (this.pathProject != null) {
//            throw new IllegalStateException("Trying to startup facade that is already started.");
//        }
        if (ui == null) {
            throw new IllegalArgumentException("The IRefreshableUI argument cannot be null.");
        }
        if (project == null) {
            throw new IllegalArgumentException("The ProjectVO argument cannot be null.");
        }
        if (designElementTypes == null) {
            throw new IllegalArgumentException(
                    "The List of DesignElementTypes argument cannot be null.");
        }
        if (designRelationTypes == null) {
            throw new IllegalArgumentException(
                    "The List of DesignRelationTypes argument cannot be null.");
        }
        
        if (trees == null) {
            throw new IllegalArgumentException(
                    "The List of Trees argument cannot be null.");
        }
        this.ui = ui;
        this.pathProject = pathProject;
        lifeArchE.startupRete(getEngineArchE(), pathProject, ui, project, designElementTypes,
                                                 designRelationTypes, trees);
    }

    /**
     * Resolves the addResponsbility command to the assertResponsibility method The centrally
     * located Rete object is passed to the assertResponsibility method
     * @param nameSlot TODO
     * @param descriptionSlot The string description slot of the fact.
     * 
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addResponsibility(String nameSlot, String descriptionSlot) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        responsibilitiesArchE.assertResponsiblity(engineArchE, nameSlot, descriptionSlot);
//        ui.refreshViews();
    }

    /**
     * Resolves the addResponsbilityParameter command to the assertResponsibilityParameter method
     * The centrally located Rete object is passed to the assertResponsibilityParameter method
     * 
     * @param paramName The name of the parameter - maps to a fact in the ArchE Core.
     * @param ownerSlot The integer owner (parent) slot of the fact.
     * @param valueSlot The string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addResponsbilityParameter(String paramName, int ownerSlot, String valueSlot,
            int dataType) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        responsibilitiesArchE.assertResponsiblityParameter(engineArchE, paramName, ownerSlot,
                                                           valueSlot, dataType);
//        ui.refreshViews();
    }

    /**
     * Resolves the editResponsibility command to the modifyResponsibility method The centrally
     * located Rete object is passed to the modifyResponsibility method
     * @param voRInitial the original Responsbility Value Object
     * @param nameSlot TODO
     * @param descriptionSlot The modified string description slot of the fact.
     * 
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editResponsibility(ResponsibilityVO voRInitial, String nameSlot, String descriptionSlot) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        responsibilitiesArchE.modifyResponsibility(engineArchE, voRInitial, nameSlot, descriptionSlot);
//        ui.refreshViews();
    }

    /**
     * Resolves the editResponsbilityParameter command to the modifyResponsibilityParameter method
     * The centrally located Rete object is passed to the modifyResponsibilityParameter method
     * 
     * @param voParamInitial the original Parameter Value Object
     * @param ownerSlotI The initial integer owner (parent) slot of the fact.
     * @param ownerSlotM The modified integer owner (parent) slot of the fact.
     * @param valueSlotM The modified string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editResponsbilityParameter(ParameterVO voParamInitial, int ownerSlotI,
            int ownerSlotM, String valueSlotM, int dataType) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        responsibilitiesArchE.modifyResponsiblityParameter(engineArchE, voParamInitial, ownerSlotI,
                                                           ownerSlotM, valueSlotM, dataType);
//        ui.refreshViews();
    }

    /**
     * Resolves the deleteResponsibility command to the retractResponsibility method The centrally
     * located Rete object is passed to the retractResponsibility method
     * 
     * @param applyToSlotD the fact Id of the Responsbility that is to be deleted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteResponsibility(int applyToSlotD) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        responsibilitiesArchE.retractResponsibility(engineArchE, applyToSlotD);
//        ui.refreshViews();
    }

    /**
     * Resolves the addRespoRelation command to the assertRespoRelation method The centrally located
     * Rete object is passed to the assertRespoRelation method
     * 
     * @param parentSlot The integer factId that is the parent in the relationship.
     * @param childSlot The integer factId that is the child in the relationship.
     * @param typeSlot The string that represents the type of the relationship.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addRespoRelation(int parentSlot, int childSlot, String typeSlot) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.assertRespoRelation(engineArchE, parentSlot, childSlot, typeSlot);
//        ui.refreshViews();
    }

    public void addRespoRelation(int parentSlot, int childSlot, RelationshipTypeVO typeVO) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.assertRespoRelation(engineArchE, parentSlot, childSlot, typeVO);
//        ui.refreshViews();
    }
    
    
    /**
     * Resolves the addRespoRelationParameter command to the assertRespoRelationParameter method The
     * centrally located Rete object is passed to the assertRespoRelationParameter method
     * 
     * @param paramName The name of the parameter - maps to a fact in the ArchE Core.
     * @param ownerSlot The integer owner (parent) slot of the fact.
     * @param valueSlot The string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addRespoRelationParameter(String paramName, int ownerSlot, String valueSlot,
            int dataType) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.assertRespoRelationParameter(engineArchE, paramName, ownerSlot,
                                                         valueSlot, dataType);
//        ui.refreshViews();
    }

    /**
     * Resolves the editRespoRelation command to the modifyRespoRelation method The centrally
     * located Rete object is passed to the modifyRespoRelation method
     * 
     * @param voRshipInitial The Relationship Value Object which is to be modified.
     * @param parentSlotM The modified integer factId that is the parent in the relationship.
     * @param childSlotM The modified integer factId that is the child in the relationship.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editRespoRelation(RelationshipVO voRshipInitial, int parentSlotM, int childSlotM) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.modifyRespoRelation(engineArchE, voRshipInitial, parentSlotM,
                                                childSlotM);
//        ui.refreshViews();
    }

    /**
     * Resolves the editRespoRelationParameter command to the modifyRespoRelationParameter method
     * The centrally located Rete object is passed to the modifyRespoRelationParameter method
     * 
     * @param voParamInitial the original Parameter Value Object
     * @param ownerSlotI The initial integer owner (parent) slot of the fact.
     * @param ownerSlotM The modified integer owner (parent) slot of the fact.
     * @param valueSlotM The modified string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editRespoRelationParameter(ParameterVO voParamInitial, int ownerSlotI,
            int ownerSlotM, String valueSlotM, int dataType) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.modifyRespoRelationParameter(engineArchE, voParamInitial, ownerSlotI,
                                                         ownerSlotM, valueSlotM, dataType);
//        ui.refreshViews();
    }

    /**
     * Resolves the deleteRespoRelation command to the retractRespoRelation method The centrally
     * located Rete object is passed to the retractRespoRelation method
     * 
     * @param applyToSlotD the fact Id of the Responsbility Relation (Fact) that is to be deleted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteRespoRelation(int applyToSlotD) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.retractRespoRelation(engineArchE, applyToSlotD);
//        ui.refreshViews();
    }

    /**
     * Resolves the deleteRespoRelationParameter command to the retractRespoRelationParameter
     * method. The centrally located Rete object is passed to the retractRespoRelationParameter
     * method
     * 
     * @param factIdD the FactId of the parameter fact to be deleted
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteRespoRelationParameter(int factIdD) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        respoRelationsArchE.retractRespoRelationParameter(engineArchE, factIdD);
//        ui.refreshViews();
    }

    /**
     * Resolves the addScFnRespoRelation command to the assertScFnRespoRelation method The centrally
     * located Rete object is passed to the assertScFnRespoRelation method
     * 
     * @param parentSlot The integer factId that is the parent (scenario/fucntion) in the
     *            relationship.
     * @param childSlot The integer factId that is the child (responsibility) in the relationship.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addScFnRespoRelation(int parentSlot, int childSlot) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scfnRespoArchE.assertScFnRespoRelation(engineArchE, parentSlot, childSlot);
//        ui.refreshViews();
    }

    /**
     * Resolves the editScFnRespoRelation command to the modifyScFnRespoRelation method The
     * centrally located Rete object is passed to the modifyScFnRespoRelation method
     * 
     * @param applyToSlot The initial integer factId that is the parent (scenario/function) in the
     *            relationship. This will be the fact id of the initial translation relation that
     *            will now be retracted from the core and replaced with a new translation relation.
     * @param parentSlotM The modified integer factId that is the parent (scenario/function) in the
     *            relationship.
     * @param childSlotM The modified integer factId that is the child (responsibility) in the
     *            relationship.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editScFnRespoRelation(int applyToSlot, int parentSlotM, int childSlotM) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scfnRespoArchE.modifyScFnRespoRelation(engineArchE, applyToSlot, parentSlotM, childSlotM);
//        ui.refreshViews();
    }

    /**
     * Resolves the deleteScFnRespoRelation command to the retractScFnRespoRelation method The
     * centrally located Rete object is passed to the retractScFnRespoRelation method
     * 
     * @param applyToSlot The integer (parent-scenario/function) factId for which the (translation)
     *            relationship is to be deleted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteScFnRespoRelation(int applyToSlot) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scfnRespoArchE.retractScFnRespoRelation(engineArchE, applyToSlot);
//        ui.refreshViews();
    }

    /**
     * Resolves the call to close the project after saving the persisted fact base to the
     * shutdownRete method. Fact base is persisted.
     * 
     * @throws IllegalStateException if this method is called before starting up or if trying to
     *             shutdown a project that was not up.
     */
    public void shutdownArchE() {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        if (this.pathProject == null) {
            throw new IllegalStateException("Trying to shutdown facade that was not started.");
        }
        lifeArchE.shutdownRete(engineArchE, pathProject, 0);        
    }

    /**
     * Resolves the call to save the project to the shutdownRete method. Fact base is persisted. The
     * Core is not halted.
     * 
     * @throws IllegalStateException if this method is called before starting up or if trying to
     *             shutdown a project that was not up.
     */
    public void saveArchE() {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        if (this.pathProject == null) {
            throw new IllegalStateException("Trying to shutdown facade that was not started.");
        }
        lifeArchE.shutdownRete(engineArchE, pathProject, 1);
    }

    
    /**
     * Resolves the call to shutdown the Core without saving the persistent fact base to the
     * shutdownRete method. Fact base is not persisted. The Core is halted.
     * 
     * @throws IllegalStateException if this method is called before starting up or if trying to
     *             shutdown a project that was not up.
     */
    public void closeArchE() {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        if (this.pathProject == null) {
            throw new IllegalStateException("Trying to shutdown facade that was not started.");
        }
        lifeArchE.shutdownRete(engineArchE, pathProject, 2);
    }

    /**
     * @return Returns the baseLocation.
     */
    public String getBaseLocation() {
        return baseLocation;
    }

    /**
     * @param baseLocation The baseLocation to set.
     */
    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
        LifeCycleInteractions.baseLocation = baseLocation;
    }

    /**
     * Resolves the addScenario command to the assertScenario method The centrally located Rete
     * object is passed to the assertScenario method
     * 
     * @param scenario The string arrays of all the contents of a scenario.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addScenario(String[] scenario) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scenarioArchE.assertScenario(engineArchE, scenario, "final");
//        ui.refreshViews();
    }

    /**
     * Resolves the thinkScenario command to the assertScenario and thinkScenario method. The
     * centrally located Rete object is passed to the assertScenario method. There's no need to
     * refresh the views because 'think' doesn't change the data being displayed on the views.
     * 
     * @param scenario The string arrays of all the contents of a scenario.
     * @return arrScenario The String array that contatins the processes (though about!) scenario.
     * @throws IllegalStateException if this method is called before starting up
     */
    public String[] thinkScenario(String[] scenario) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scenarioArchE.assertScenario(engineArchE, scenario, "notfinal");
        return scenarioArchE.thinkScenario(engineArchE);
    }

    /**
     * Resolves the editScenario command to the modifyScenario method The centrally located Rete
     * object is passed to the modifyScenario method
     * 
     * @param scenario The string arrays of all the contents of a modified scenario.
     * @param applyToSlotM The Scenario Fact Id which is to be modified.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editScenario(String[] scenario, int applyToSlotM) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scenarioArchE.modifyScenario(engineArchE, scenario, applyToSlotM);
//        ui.refreshViews();
    }

    /**
     * Resolves the deleteScenario command to the retractScenario method The centrally located Rete
     * object is passed to the retractScenario method
     * 
     * @param applyToSlotD the fact Id of the Scenario that is to be deleted.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteScenario(int applyToSlotD) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        scenarioArchE.retractScenario(engineArchE, applyToSlotD);
//        ui.refreshViews();
    }

    /**
     * Add a function to ArchE core using the corresponding assert method in the interactions layer.
     * 
     * @param id The string ID of the new function.
     * @param description The description of the new function.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void addFunction(String id, String description) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        functionArchE.assertFunction(engineArchE, id, description);
//        ui.refreshViews();
    }

    /**
     * Changes id and/or description of a function in ArchE core using the corresponding assert
     * method in the interactions layer.
     * 
     * @param applyToFactId fact id of the function to be edited
     * @param id The new value for the ID of the function.
     * @param description The new value for the description of the new function.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void editFunction(int applyToFactId, String id, String description) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        functionArchE.modifyFunction(engineArchE, applyToFactId, id, description);
//        ui.refreshViews();
    }

    /**
     * Delete a function in ArchE core using the corresponding assert method in the interactions
     * layer.
     * 
     * @param applyToFactId fact id of the function to be deleted
     * @param id The new value for the ID of the function.
     * @param description The new value for the description of the new function.
     * @throws IllegalStateException if this method is called before starting up
     */
    public void deleteFunction(int applyToFactId) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        functionArchE.retractFunction(engineArchE, applyToFactId);
//        ui.refreshViews();
    }

    /**
     * The user has answerd a question. The QuestionToUserVO object contains both the question and
     * the answer. We simply change the question in the fact base. ArchE Core takes care of removing
     * the question once it's answered.
     * 
     * @param questionVo
     */
    public void answerQuestion(QuestionToUserVO questionVo) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        
        questionArchE.modifyQuestion(engineArchE, questionVo);
//        ui.refreshViews();
    }

    /**
     * gets the value (string contents) for a given slot of a fact in the core.
     * 
     * @param factId The integer FactId of the fact in the Core.
     * @param slotName The string slot for which the value needs ot be read.
     * @return The String value (contents) of the given slot for the fact found using the Id.
     * @throws IllegalStateException if this method is called before starting up
     */
    public String getSlotValue(int factId, String slotName) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        return miscellanyArchE.getValue(engineArchE, factId, slotName);
    }

    /**
     * Writes the XML file containing all the facts, their slots and values from the core to an XML
     * file which will be converted into the UML design XMI file using XSLT.
     */
    public void writeFacts(String fileName) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }

        miscellanyArchE.writeFactsXML(engineArchE, fileName, baseLocation);
    }

    /**
     * Used by Jess Console to hook up the Rete object to the UI.
     * 
     * @return Returns the engineArchE.
     */
    public Rete getEngineArchE() {
    	if(engineArchE == null)
    		engineArchE = new Rete();
        return engineArchE;
    }
    
    /**
     * Export the ArchE project(design) to a SQL file which will be read by MySQL
     * 
     */
    public void exportToSQL(String sqlfilePath, boolean runEngine) {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        try {
        	if(runEngine)
        		lifeArchE.exportFactBaseToSQL(engineArchE, pathProject, sqlfilePath);
        	else
        		lifeArchE.exportFactBaseToSQLWithoutJessRun(engineArchE, pathProject, sqlfilePath);
		} catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
			je.printStackTrace();
            throw new RuntimeException(je.getMessage(), je);
		}                
    }

	public void restoreFromDB() {
        if (engineArchE == null) {
            throw new IllegalStateException("ArchE Core has to be started first.");
        }
        try {
       		lifeArchE.restoreFromDB(engineArchE);
		} catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
			je.printStackTrace();
            throw new RuntimeException(je.getMessage(), je);
		}                
	}
}