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

package edu.cmu.sei.arche.external.execution;

import java.io.Serializable;
import java.util.List;

/**
 * This class provides the main setters/getters for the MAIN::AskQuestion 
 * template in the ArchE working memory
 * 
 * @author Hyunwoo Kim
 */

public class RawArchEUserQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String instanceId;
	private String questionId;
	private String questionGroup;
	private String rfID;
	private int searchStep;
	private int priority;
	private String parentFactID;
	private List<String> affectedFacts;  // List of FactID strings
	private List<String> parameters;  // List of strings, some of which might be FactID
	private List<String> options;  // List of strings, some of which might be FactID
	private List<String> defaults;  // List of strings, some of which might be FactID	
	private List<String> answers;  // List of strings	
	
	
	public void setID(String instanceId){
		this.instanceId = instanceId;
	}
	
	public String getID(){
		return instanceId;
	}	
	
	public void setQuestionID(String questionId){
		this.questionId = questionId;
	}
	
	public String getQuestionID(){
		return questionId;
	}

	public void setQuestionGroup(String questionGroup){
		this.questionGroup = questionGroup;
	}
	
	public String getQuestionGroup(){
		return questionGroup;
	}
	
	public void setReasoningFramework(String rfID){
		this.rfID = rfID;
	}
	
	public String getReasoningFramework(){
		return rfID;
	}
	
	public void setSearchStep(int searchStep){
		this.searchStep = searchStep;
	}
	
	public int getSearchStep(){
		return searchStep;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public void setParent(String parentFactID){
		this.parentFactID = parentFactID;
	}

	public String getParent(){
		return parentFactID;
	}
	
	public void setAffectedFacts(List<String> affectedFactIDs){
		this.affectedFacts = affectedFactIDs;
	}
	
	public List<String> getAffectedFacts(){
		return affectedFacts;
	}
	
	public void setParameters(List<String> parameters){
		this.parameters = parameters;
	}
	
	public List<String> getParameters(){
		return parameters;
	}

	public void setOptions(List<String> options){
		this.options = options;
	}
	
	public List<String> getOptions(){
		return options;
	}

	public void setDefaults(List<String> defaults){
		this.defaults = defaults;
	}
	
	public List<String> getDefaults(){
		return defaults;
	}
	
	public void setAnswers(List<String> answers){
		this.answers = answers;
	}
	
	public List<String> getAnswers(){
		return answers;
	}

}
