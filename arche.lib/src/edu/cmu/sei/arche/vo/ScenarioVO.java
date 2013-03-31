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
import java.util.Set;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * Scenario value object. Scenario corresponds to fact MAIN::Scenarios in the fact base.
 * 
 * @author Paulo Merson
 */
public class ScenarioVO extends CoreFact {

    /** Scenario was not analyzed yet, that is, there's no AnalysisResultVO associated with it. */
    public static final int NOT_ANALYZED    = 0;

    /*
     * At least one AnalysisResultVO associated with this scenario has isSatisfied = true and at
     * least one has isSatisfied = false.
     */
    //Reserved for later versions of ArchE.
    //The status of a scenario will be PARTIALLY_SATISFIED when the user has activated at least
    // 2 Reasoning Frameworks
    //and if at least one AnalysisResultVO for the scenario for one of the active RFs has
    // isSatisfied = true and
    //at least one AnalysisResultVO for the scenario for one of the active RFs has isSatisfied
    // = false.
    //public static final int PARTIALLY_SATISFIED = 1;
    /** All AnalysisResultVO associated with this scenario have isSatisfied = true. */
    public static final int FULLY_SATISFIED = 2;

    /** All AnalysisResultVO associated with this scenario have isSatisfied = false. */
    public static final int NOT_SATISFIED   = 3;

    /** Slot quality in the fact. */
    private String          quality;
    
    /** Slot description in the fact. */
    private String          description;

    /** From slot quality, which corresponds to a valid ScenarioTypeVO.id */
    private ScenarioTypeVO  scenarioType;

    /**
     * Array of scenario parts. Contains up to six elements corresponding to stimulus, stimulus
     * source, artifact, environment, response and response measure.
     */
    private ScenarioPartVO  parts[];

    /** Set of all AnalysisResultVO objects whose ownerFactId points to this scenario. */
    private Set             analysisResults;

    private String previous_value;
    
    private String current_value;
    
    private boolean previous_isSatisfied;
    
    private boolean current_isSatisfied;
    
    private int current_analysis_fact_id;
    /**
     * @param factId
     * @param factType
     * @param description
     * @param isSatisfied
     * @param scenarioType
     */
    public ScenarioVO(int factId, String quality, String description, boolean isSatisfied,
            ScenarioTypeVO scenarioType, IRefreshableUI ui) {
        super(factId, "MAIN::Scenarios", ui);
        this.quality = quality;
        this.description = description;
        this.scenarioType = scenarioType;
        parts = new ScenarioPartVO[6];
        analysisResults = new HashSet();
        ui.flagViewsToRefresh(this.getClass());
        current_value = "0";
        previous_value = "0";
        current_analysis_fact_id = 0;
        this.previous_isSatisfied = true;
        this.current_isSatisfied = true;
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
     * @return Returns the parts.
     */
    public ScenarioPartVO[] getParts() {
        return parts;
    }

    /**
     * There's no need to call flagViewsToRefresh() because if any of the parts change for a given
     * scenario, the ScenarioPartVO methods will call flagViewsToRefresh().
     * 
     * @param parts The parts to set.
     */
    public void setParts(ScenarioPartVO[] parts) {
        this.parts = parts;
    }

    /**
     * @return Returns the scenarioType.
     */
    public ScenarioTypeVO getScenarioType() {
        return scenarioType;
    }

    /**
     * @param scenarioType The scenarioType to set.
     */
    public void setScenarioType(ScenarioTypeVO scenarioType) {
        this.scenarioType = scenarioType;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the analysisResults.
     */
    public Set getAnalysisResults() {
        return analysisResults;
    }

    /**
     * @param analysisResults The analysisResults to set.
     */
    public void setAnalysisResults(Set analysisResults) {
        this.analysisResults = analysisResults;
    }
    
    /**
     * @return Returns the value.
     */
    public String getCurrentValue() {
        return current_value;
    }

    /**
     * @param previous_value The value to set.
     */
    public void setCurrentIsSatisfied(boolean val) {
    	this.previous_isSatisfied = this.current_isSatisfied;
        this.current_isSatisfied = val;
    }
    
    /**
     * @return Returns the value.
     */
    public boolean getCurrentIsSatisfied() {
        return current_isSatisfied;
    }

    /**
     * @param previous_value The value to set.
     */
    public void setCurrentValue(String val) {
    	this.previous_value = this.current_value;
        this.current_value = val;
    }
    
    /**
     * @return Returns the value.
     */
    public int getCurrentAnalysisFactId() {
        return current_analysis_fact_id;
    }

    /**
     * @param previous_value The value to set.
     */
    public void setCurrentAnalysisFactId(int val) {
        this.current_analysis_fact_id = val;
    }
    
    /**
     * @return Returns the value.
     */
    public String getPreviousValue() {
        return previous_value;
    }

    /**
     * @param previous_value The value to set.
     */
    public void setPreviousValue(String val) {
        this.previous_value = val;
    }

    /**
     * @param previous_value The value to set.
     */
    public void setPreviousIsSatisfied(boolean val) {
    	this.previous_isSatisfied = val;
    }
    
    /**
     * @return Returns the value.
     */
    public boolean getPreviousIsSatisfied() {
        return previous_isSatisfied;
    }
    /**
     * Computes the status of the scenario based on the AnalysisResultVO objects associated with
     * this scenario. There is at most one AnalysisResultVO per Reasoning Framework for each
     * scenario. The Status of the scenario (displayed to the User of ArchE depends on the
     * following:
     * <ul>
     * <li>the number of AnalysisResultVOs per scenario
     * <li>the value of the isSatisfied member variable for each of the AnalysisResultVOs
     * <li>the Reasoning Frameworks activated by the user
     * </ul>
     * The Status of each scenario can be:
     * <ul>
     * <li>NOT_ANALYZED: Scenario was not analyzed yet, that is, there's no AnalysisResultVO
     * associated with it.
     * <li>NOT_SATISFIED: Any one of the AnalysisResultVOs associated with this scenario has
     * isSatisfied = false. Ideally if all AnalysisResultVOs for all Reasoning Frameworks activated
     * by the User have isSatisfied = false, then this status should be "not satisfied"
     * <li>FULLY_SATISFIED: All AnalysisResultVO associated with this scenario have isSatisfied =
     * true, irrespective of the Reasoning Frameworks activated by the user.
     * </ul>
     * 
     * @return NOT_ANALYZED, NOT_SATISFIED, FULLY_SATISFIED
     */
    public int getStatus() {
        boolean foundSatisfied = true;
        //boolean foundNotSatisfied = false;
        if (analysisResults.isEmpty()) {
            return NOT_ANALYZED;
        } else {
            for (Iterator it = analysisResults.iterator(); it.hasNext();) {
                AnalysisResultVO vo = (AnalysisResultVO) it.next();
                if (!(vo.isSatisfied())) {
                    foundSatisfied = false;
                }
                //else {
                //  foundNotSatisfied = true;
                //}
            }
            return (foundSatisfied ? FULLY_SATISFIED : NOT_SATISFIED);
        }

        //This piece of code allows for incorporating the status PARTIALLY_SATISFIED for later
        // versions of ArchE.
        //The status of a scenario will be PARTIALLY_SATISFIED when the user has activated at least
        // 2 Reasoning Frameworks
        //and if at least one AnalysisResultVO for the scenario for one of the active RFs has
        // isSatisfied = true and
        //at least one AnalysisResultVO for the scenario for one of the active RFs has isSatisfied
        // = false.
        /*
         * return (foundSatisfied ? (foundNotSatisfied ? PARTIALLY_SATISFIED : FULLY_SATISFIED) :
         * (foundNotSatisfied ? NOT_SATISFIED : NOT_ANALYZED));
         */
    }
}