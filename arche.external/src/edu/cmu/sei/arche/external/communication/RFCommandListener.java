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

package edu.cmu.sei.arche.external.communication;

import java.util.List;

import edu.cmu.sei.arche.external.execution.RawArchETryTacticResult;
import edu.cmu.sei.arche.external.execution.RawArchEUserQuestion;
import edu.cmu.sei.arche.external.reasoningframework.ArchEAnalysisResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEEvaluationResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEUserQuestion;

/**
 * Interface defining a list of methods that will be invoked by
 * an instance of RFInteractionAgent with an incoming interaction command.
 * It must be implemented by an external reasoning framework to
 * provide appropriate reasoning operations for ArchE.
 * 
 * RFCommands include:
 * 		RFApplyTactics
 * 		RFAnalyzeAndSuggest
 * 		RFApplySuggestedTactic
 * 		RFAnalyze
 * 		RFDescribeTactic
 * 
 * @author Hyunwoo Kim
 */
public interface RFCommandListener {
	
	public void  applyTactics(RFApplyTactics command);
	
	public List<ArchEAnalysisResult>  analyzeAndSuggest(RFAnalyzeAndSuggest command, /* out */ List<RawArchETryTacticResult> outRawTactics);
	
	public void  applySuggestedTactic(RFApplySuggestedTactic command);
	
	public List<ArchEEvaluationResult> analyze(RFAnalyze command);
	
	public List<RawArchEUserQuestion>  describeTactics(RFDescribeTactic command);	
}
