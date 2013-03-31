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

import edu.cmu.sei.arche.corebridge.IRefreshableUI;

/**
 * Corresponds to a question that ArchE wants to ask to the user. Maps to fact MAIN::AskQuestion.
 * Further information about a question is found in the questions bundle properties file.
 * 
 * @author Paulo Merson
 */
public class QuestionToUserVO extends CoreFact {

    /**
     * Identification of the kind of question (slot questionId). Corresponds to the prefix of the
     * keys in the properties file. Examples of questionId: confirmCost, inconsistentData
     */
    private String   questionId;
    
    /**
     * Priority of the fact that this question refers to (slot priority in MAIN::AskQuestion).
     */
    private String     priority;

    /**
     * Fact id of the fact that this question refers to (slot parent in MAIN::AskQuestion).
     */
    private int      parentFactId;

    /**
     * Fact ids of the facts that are affected by this question (and hence should have a marker in
     * the UI). Values come from multislot affectedFacts in MAIN::AskQuestion.
     */
    private int[]    affectedFacts;

    /**
     * Array os strings that correspond to data elements used to build the text of a question. (Slot
     * parameters in MAIN::AskQuestion) In the static text of a question (in the properties file),
     * these dynamic data elements are represented by tags which map to elements of this array
     * following this convention:
     * <ul>
     * <li><1>tag is replaced with text of first parameter , i.e., parameters[0]
     * <li><2+>tag is replaced with text of all parameters from the 2nd to the last in parameters
     * (i.e., parameters[1], parameters[2], ... The Strings should be separated by comma.
     * <li><3:desc>third parameter (parameters[2]) is necessarily a fact id in this case. Retrieve
     * the fact from ArchE Core and replace the tag with the value in slot "desc" in that fact. Note
     * that a call to core is necessary otherwise we�ll need to use introspection to translate the
     * slot name into a getter method call to some generic fact VO class.
     * <li><4+:desc>tag is replaced with the value of slot "desc" in the facts whose fact ids are
     * the contents of parameters from the 4th to the last in parameters. The Strings should be
     * separated by comma.
     */
    private String[] parameters;

    /**
     * Array of strings that correspond to data elements used to build the text in the answer
     * options. (Slot options in MAIN::AskQuestion)
     */
    private String[] options;

    /**
     * Array of strings that contain the answer initially suggested by ArchE. (Slot defaultAnswer in
     * MAIN::AskQuestion). The way defaultAnswer is used varies according to "questionType"
     * (questionType is defined by key <questionId>.questionType in the properties file). In all
     * cases the default answer may be null or non-existent).
     * <ul>
     * <li>singleChoice: defaultAnswer[0] has the index of the default selection (e.g. "1" for
     * selecting the first option).
     * <li>multipleChoice: defaultAnswer[0] has a space-separated list of the indexes of the
     * default selections (e.g. "1 4 5" for selecting the 1st, 4th and 5th options).
     * <li>multipleValues: defaultAnswer[i] has the default answer for the input text box i.
     * <li>yesNo: defaultAnswer[0] contains "yes" or "no".
     * <li>alert: defaultAnswer is ignored.
     * <ul>
     */
    private String[] defaultAnswer;

    /**
     * Holds the answer provided by the user. Syntax of the contents TBD.
     */
    private String[] answer;

    /**
     * @param factId
     * @param factType
     * @param ui
     * @param questionId
     * @param parentFactId
     * @param affectedFacts
     * @param parameters
     * @param options
     * @param defaultAnswer
     * @param answer
     */
    public QuestionToUserVO(int factId, String questionId, int parentFactId, int[] affectedFacts,
            String[] parameters, String[] options, String priority, String[] defaultAnswer, String[] answer,
            IRefreshableUI ui) {
        super(factId, "MAIN::AskQuestion", ui);
        this.questionId = questionId;
        this.parentFactId = parentFactId;
        this.affectedFacts = affectedFacts;
        this.parameters = parameters;
        this.options = options;
        this.defaultAnswer = defaultAnswer;
        this.answer = answer;
        this.priority = priority;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the affectedFacts.
     */
    public int[] getAffectedFacts() {
        return affectedFacts;
    }

    /**
     * @param affectedFacts The affectedFacts to set.
     */
    public void setAffectedFacts(int[] affectedFacts) {
        this.affectedFacts = affectedFacts;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the answer.
     */
    public String[] getAnswer() {
        return answer;
    }

    /**
     * @param answer The answer to set.
     */
    public void setAnswer(String[] answer) {
        this.answer = answer;
        // answer is only set by ArchE UI itself. After that, the fact should be retracted by
        // core. Therefore, we don't need to refresh views.
    }

    /**
     * @return Returns the defaultAnswer.
     */
    public String[] getDefaultAnswer() {
        return defaultAnswer;
    }

    /**
     * @param defaultAnswer The defaultAnswer to set.
     */
    public void setDefaultAnswer(String[] defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the options.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * @param options The options to set.
     */
    public void setOptions(String[] options) {
        this.options = options;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the parameters.
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * @param parameters The parameters to set.
     */
    public void setParameters(String[] parameters) {
        this.parameters = parameters;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the parentFactId.
     */
    public int getParentFactId() {
        return parentFactId;
    }

    /**
     * @param Priority The Priority to set.
     */
    public void setPriority(String priority) {
        this.priority = priority;
        ui.flagViewsToRefresh(this.getClass());
    }
    
    /**
     * @return Returns the Priority.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param parentFactId The parentFactId to set.
     */
    public void setParentFactId(int parentFactId) {
        this.parentFactId = parentFactId;
        ui.flagViewsToRefresh(this.getClass());
    }

    /**
     * @return Returns the questionId.
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId The questionId to set.
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
        ui.flagViewsToRefresh(this.getClass());
    }
}