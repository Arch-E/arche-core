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

/**
 * A base class defining the analysis result of an ArchE analyzeAndSuggest() command
 * 
 * @author Andres Diaz-Pace, Hyunwoo Kim
 */

public class ArchEAnalysisResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String quality;
	private String rfID;
	private boolean satisfied;
	private double utility;
	private double value;
	private double oldValue;
	private String ownerScenarioID;
	
	public void setQuality(String quality){
		this.quality = quality;
	}
	
	public String getQuality(){
		return quality;
	}
	
	public void setReasoningFramework(String rfID){
		this.rfID = rfID;
	}
	
	public String getReasoningFramework(){
		return rfID;
	}
	
	public void setSatisfied(boolean satisfied){
		this.satisfied = satisfied;
	}

	public boolean isSatisfied(){
		return satisfied;
	}
	
	public void setUtility(double utility){
		this.utility = utility;
	}
	
	public double getUtility(){
		return utility;
	}
	
	public void setValue(double value){
		this.value = value;
	}

	public double getValue(){
		return value;
	}
		
	public void setOldValue(double oldValue){
		this.oldValue = oldValue;
	}

	public double getOldValue(){
		return oldValue;
	}
	
	public void setOwner(String scenariofactID){
		this.ownerScenarioID = scenariofactID;
	}
	
	public String getOwner(){
		return ownerScenarioID;
	}		
}


