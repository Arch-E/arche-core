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

/**
 * This class provides the main setters/getters for the MAIN::TryTacticResult 
 * template in the ArchE working memory
 * 
 * @author Hyunwoo Kim
 */

public class RawArchETryTacticResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tacticID;
	private String scenarioFactID;
	private String rfID;
	private String tacticName;
	private String tacticDescription;
		
	public String getReasoningFramework() {
		return rfID;
	}

	public String getScenario() {
		return scenarioFactID;
	}

	public String getTacticDescription() {
		return tacticDescription;
	}

	public String getTacticName() {
		return tacticName;
	}

	public String getTacticID() {
		return tacticID;
	}

	public void setReasoningFramework(String rfID) {
		this.rfID = rfID;
	}


	public void setScenario(String scenarioFactID) {
		this.scenarioFactID = scenarioFactID;
	}

	public void setTacticDescription(String tacticDescription) {
		this.tacticDescription = tacticDescription;
	}

	public void setTacticName(String taticName) {
		this.tacticName = taticName;
	}
	
	public void setTacticID(String tacticID) {
		this.tacticID = tacticID;
	}
}
