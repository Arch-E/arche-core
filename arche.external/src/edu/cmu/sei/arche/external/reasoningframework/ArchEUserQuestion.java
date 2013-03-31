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

package edu.cmu.sei.arche.external.reasoningframework;

import java.io.Serializable;
import java.util.List;

import edu.cmu.sei.arche.external.data.ArchEObject;

/**
 * This class provides the main setters/getters for the MAIN::AskQuestion 
 * template in ArchE 
 * 
 * @author Andres Diaz-Pace
 */

public class ArchEUserQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	// members that will be set by the RF Interface framework
	private String questionGroup;
	private String rfID;
	private int searchStep;
	private int priority;
	private List answers;  // List of ???	
	
	// members that will be set by an external reasoning framework
	private String questionId;
	private ArchEObject parent; // object of ArchEObject type
	private List affectedFacts; // List of objects of ArchEObject type
	private List parameters;  	// List of strings, some of which might be objects of ArchEObject type
	private List options;  		// List of strings, some of which might be objects of ArchEObject type
	private List defaults;  	// List of strings, some of which might be objects of ArchEObject type	

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
	
	public void setParent(ArchEObject parent){
		this.parent = parent;
	}

	public ArchEObject getParent(){
		return parent;
	}
	
	public void setAffectedFacts(List affectedFactIDs){
		this.affectedFacts = affectedFactIDs;
	}
	
	public List getAffectedFacts(){
		return affectedFacts;
	}
	
	public void setParameters(List parameters){
		this.parameters = parameters;
	}
	
	public List getParameters(){
		return parameters;
	}

	public void setOptions(List options){
		this.options = options;
	}
	
	public List getOptions(){
		return options;
	}

	public void setDefaults(List defaults){
		this.defaults = defaults;
	}
	
	public List getDefaults(){
		return defaults;
	}
	
	public void setAnswers(List answers){
		this.answers = answers;
	}
	
	public List getAnswers(){
		return answers;
	}
}
