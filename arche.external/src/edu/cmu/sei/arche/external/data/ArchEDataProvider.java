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

package edu.cmu.sei.arche.external.data;
import edu.cmu.sei.arche.ArchEException;

/**
 * A general interface for external reasoning frameworks to manipulate data
 * 
 * All the methods defined in this interface are of transaction-per-method, which
 * means executing each method implies doing a DB transaction by commit. Also each method
 * needs to support rollback if an exception occurs during the transaction.
 * 
 * @author Andres Diaz-Pace, Hyunwoo Kim
 */

public interface ArchEDataProvider {

	///////////////////////////////////
	// transaction-per-method
	
	/* Version */ 
	/**
	 * Return the current version of the architecture
	 * 
	 * @param architectureName Versioned architecture
	 * @throws ArchEException
	 */
	public ArchEVersion getVersion(String architectureName) throws ArchEException;		
	
	/**
	 * Return a version for a candidate architecture
	 * 
	 * @param architectureName Versioned architecture
	 * @param parentVersion	Version that generates this candidate
	 * @throws ArchEException
	 */
	public ArchEVersion newChildVersion(ArchEVersion parentVersion, String architectureName) throws ArchEException;		
	
	/* Requirement Model */
	/**
	 * Restore the requirement model (i.e., scenarios) from the database
	 * 
	 * @param version Version of the architecture to be recovered
	 * @throws ArchEException
	 */
	public ArchERequirementModel restoreRequirementModel(ArchEVersion version) throws ArchEException;

	/**
	 * Save the requirement model (i.e., scenarios) to the database
	 * 
	 * @param requirementModel The requirement model being saved
	 * @throws ArchEException
	 */
	public void saveRequirementModel(ArchERequirementModel requirementModel) throws ArchEException;
	
	/**
	 * Save the requirement model (i.e., scenarios) with a new version (different architecture) in the database 
	 * 
	 * @param requirementModel The requirement model being saved
	 * @param newVersion Version for this requirement model
	 * @throws ArchEException
	 */
	public void saveRequirementModelAs(ArchERequirementModel requirementModel, ArchEVersion newVersion) throws ArchEException;
	
	/* Architecture */
	/**
	 * Restore an architecture from the database
	 * 
	 * @param version The version for the architecture
	 * @throws ArchEException
	 */
	public ArchEArchitecture restoreArchitecture(ArchEVersion version) throws ArchEException;	

	/**
	 * Save an architecture to the database
	 * 
	 * @param architecture The architecture being saved
	 * @throws ArchEException
	 */
	public void saveArchitecture(ArchEArchitecture architecture) throws ArchEException;	

	/**
	 * Save the architecture with a new version in the database (different architecture)
	 * 
	 * @param architecture The architecture being saved
	 * @param newVersion The version for this architecture
	 * @throws ArchEException
	 */
	public void saveArchitectureAs(ArchEArchitecture architecture, ArchEVersion newVersion) throws ArchEException;	
	
	/**
	 * Delete a complete architecture from the database
	 * 
	 * @param version The version for the architecture
	 * @throws ArchEException
	 */
	public void deleteArchitecture(ArchEVersion version) throws ArchEException;	
}
