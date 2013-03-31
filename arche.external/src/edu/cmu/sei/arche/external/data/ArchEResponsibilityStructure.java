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

import java.util.List;

/**
 * A simple interface for the responsibility structure model used by each external reasoning framework
 * 
 * @author Hyunwoo Kim, Andres Diaz-Pace
 */

public interface ArchEResponsibilityStructure {

	/**
	 * The architecture that contains this responsibility structure instance
	 * 
	 * @return
	 */
	public ArchEArchitecture getParent();
	
	/**
	 * Return all the responsibilities defined in this object
	 * 
	 * @return
	 */
	public List<ArchEResponsibility> getResponsibilities();
	
	/**
	 * Return all the responsibilities that are mapped to a given scenario
	 * 
	 * @scenario The reference scenario
	 * @return
	 */
	public List<ArchEResponsibility> getResponsibilitiesByScenario(ArchEScenario scenario);
	
	/**
	 * Return all the relationships of a given type
	 * 
	 * @param relationTypeVO The type of relations (usually, the VO class name)
	 * @return
	 */
	public List<ArchERelation> getRelations(String relationTypeVO);

	/**
	 * Return all the responsibilities an abstract responsibility has been refined into
	 * 
	 * @param responsibility The abstract responsibility
	 */
	public List<ArchEResponsibility> getChildrenResponsibilities(ArchEResponsibility responsibility);

	/**
	 * Search for a relationship between two responsibilities. If found, the relationships is returned
	 * 
	 * @param parent The left side of the relationship
	 * @param child The right side of the relationship
	 * @param relationTypeVO The relationship type
	 * @return
	 */
	public ArchERelation getRelation(ArchEResponsibility parent, ArchEResponsibility child, String relationTypeVO);

	/**
	 * Checks whether a relationship between two responsibilities exists
	 * 
	 * @param parent The left side of the relationship
	 * @param child The right side of the relationship
	 * @param relationTypeVO The relationship type
	 * @return
	 */
	public boolean  existRelation(ArchEResponsibility parent, ArchEResponsibility child, String relationTypeVO);
}
