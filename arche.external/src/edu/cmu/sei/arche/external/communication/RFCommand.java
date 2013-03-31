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

/**
 * Superclass of other interaction command classes. It is responsible for having command identification, command name, and database connection information
 * Interaction commands include: 
 *			ApplyTactics
 *			AnalyzeAndSuggest
 *			ApplySuggestedTactic
 *			Analyze
 *			DescribeTactic
 * 
 * @author Hyunwoo Kim
 */	
public class RFCommand {

	public static final int ID_COMMAND_UNKNOWN = 0;
	public static final int ID_COMMAND_APPLYTACTICS = 1;
	public static final int ID_COMMAND_ANALYZEANDSUGGEST = 2;
	public static final int ID_COMMAND_APPLYSUGGESTEDTACTIC = 3;
	public static final int ID_COMMAND_ANALYZE = 4;
	public static final int ID_COMMAND_DESCRIBETACTIC = 5;
	public static final String NAME_COMMAND_UNKNOWN = "Unknown";    
	public static final String NAME_COMMAND_APPLYTACTICS = "ApplyTactics";
	public static final String NAME_COMMAND_ANALYZEANDSUGGEST = "AnalyzeAndSuggest";
	public static final String NAME_COMMAND_APPLYSUGGESTEDTACTIC = "ApplySuggestedTactic";
	public static final String NAME_COMMAND_ANALYZE = "Analyze";
	public static final String NAME_COMMAND_DESCRIBETACTIC = "DescribeTactic";
	
	
	protected RFInteractionAgent agent = null;
	protected int commandID = ID_COMMAND_UNKNOWN;
	protected DBCConfig dbConfig = null;
	protected String projectName = null;
	
	public RFCommand(RFInteractionAgent agent, int commandID, DBCConfig dbConfig, String projectName){
		this.agent = agent;
		this.commandID = commandID; 
		this.dbConfig = dbConfig;
		this.projectName = projectName;
	}
	
	public RFInteractionAgent getRFInteractionAgent(){
		return agent; 
	}

	public int getCommandID(){
		return commandID;
	}
	
	public String getCommandName(){
		return getCommandNameByID(commandID);
	}
	
	public DBCConfig getDBCConfig(){
		return dbConfig;
	}

	public String getCurrentProjectName(){
		return projectName;
	}
	
	public static String getCommandNameByID(int commandID) {
		String commandName = NAME_COMMAND_UNKNOWN;
		if(commandID == ID_COMMAND_APPLYTACTICS)
			commandName = NAME_COMMAND_APPLYTACTICS;
		else if(commandID == ID_COMMAND_ANALYZEANDSUGGEST)
			commandName = NAME_COMMAND_ANALYZEANDSUGGEST;
		else if(commandID == ID_COMMAND_APPLYSUGGESTEDTACTIC)
			commandName = NAME_COMMAND_APPLYSUGGESTEDTACTIC;
		else if(commandID == ID_COMMAND_ANALYZE)
			commandName = NAME_COMMAND_ANALYZE;
		else if(commandID == ID_COMMAND_DESCRIBETACTIC)
			commandName = NAME_COMMAND_DESCRIBETACTIC;
		return commandName;
	}
}
