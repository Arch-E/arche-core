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
 * Class that have the information of the tactics
 * applied to each scenario.
 * 
 * @author Ricardo Vazquez
 */

public class EvaluatedTacticsVO implements Comparable {
	/**	tactic Id **/
	private String tacticId;
	/**	tactic Description **/
	private String tacticDesc;
	/**	tactic Utility applied in the scenario **/
	private double utility;
	/**	tactic relevance in the scenario **/
	private int relevance;
	/**	indicates the positive or negative change of a tactic in a scenario **/
	private double change;
	
	
//	public EvaluatedTacticsVO(int tId, String tDesc, double tUt, int tRel, double tCha){
//		tacticId = tId;
//		tacticDesc = tDesc;
//		utility = tUt;
//		relevance = tRel;
//		change = tCha;
//	}
	
	public EvaluatedTacticsVO(String tId, String tDesc, double tUt, int tRel, double tCha){
//		int indexOne = tId.indexOf("gen");
//    	int indexTwo = tId.length();

//		tacticId = Integer.valueOf(tId.substring(indexOne + 3,indexTwo)).intValue();
		tacticId = tId;
		tacticDesc = tDesc;
		utility = tUt;
		relevance = tRel;
		change = tCha;
	}
	
	public EvaluatedTacticsVO( EvaluatedTacticsVO inTacticVO){
		tacticId = inTacticVO.getTacticId();
		tacticDesc = inTacticVO.getTacticDesc();
		utility = inTacticVO.getUtility();
		relevance = inTacticVO.getRelevance();
		change = inTacticVO.getChange();
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public String getTacticId() {
		return tacticId;
	}

	public void setTacticId(String tacticId) {
		this.tacticId = tacticId;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}

	public String getTacticDesc() {
		return tacticDesc;
	}

	public void setTacticDesc(String tacticDesc) {
		this.tacticDesc = tacticDesc;
	}
	
	/** Method used to sort the tactics based in the relevance 
	 *  this method is used by Collections class to compare
	 *  two different tactics 
	 *  @param Object that indicates the object to compare with **/
	public int compareTo(Object second) {
		if( ((EvaluatedTacticsVO) second).relevance == this.relevance){
			return 0;
		}
		if( ((EvaluatedTacticsVO) second).relevance < this.relevance){
			return 1;
		}else if( ((EvaluatedTacticsVO) second).relevance > this.relevance){
			return -1;
		}
		return 0;
	}

	/** Method used to sort the tactics based in the relevance 
	 *  this method is used by Collections class to compare
	 *  two different tactics 
	 *  @param Object that indicates the object to compare with **/
	public boolean equals(EvaluatedTacticsVO second) {
		int diff = (int)(this.relevance - (((EvaluatedTacticsVO) second).relevance));
		return (diff == 0);
		}
	/** Method used to sort the tactics based in the relevance 
	 *  this method is used by Collections class to compare
	 *  two different tactics 
	 *  @param Object that indicates the object to compare with **/
	public boolean equalsChange(EvaluatedTacticsVO second) {
		if(this.change > ((EvaluatedTacticsVO) second).change)
			return false;
		else if(this.change < ((EvaluatedTacticsVO) second).change)
			return false;
		else
			return true;
	}
}
