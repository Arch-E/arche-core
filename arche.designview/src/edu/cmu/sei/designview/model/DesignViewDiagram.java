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

package edu.cmu.sei.designview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for multiple ArchElements.
 * This is the "root" of the model data structure.
 * @author Hyunwoo Kim
 */
public class DesignViewDiagram extends ArchModel {

	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "AViewDiagram.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "AViewDiagram.ChildRemoved";
	private static final long serialVersionUID = 1;
	private List archElements = new ArrayList();
	private double zoom = 1.0;
	
	private String versionName;
	private int versionID;
	private String rootNodeFactId;
	private int rootNodeFactType;
	private String selectedViewType;
	private String selectedLayoutType;
	

	/** 
	 * Add an ArchElement to this diagram.
	 * @param s a non-null ArchElement instance
	 * @return true, if the ArchElement was added, false otherwise
	 */
	public boolean addChild(ArchElement s) {
		if (s != null && archElements.add(s)) {
			firePropertyChange(CHILD_ADDED_PROP, null, s);
			return true;
		}
		return false;
	}

	/** Return a List of ArchElements in this diagram.  The returned List should not be modified. */
	public List getChildren() {
		return archElements;
	}
	
	/** 
	 * Check if this diagram contains an ArchElement.
	 * @param s a non-null ArchElement instance
	 * @return true, if the ArchElement was contained, false otherwise
	 */
	public boolean containChild(ArchElement s) {
		if (s != null && archElements.contains(s)) {
			return true;
		}
		return false;
	}
	

	/**
	 * Remove an ArchElement from this diagram.
	 * @param s a non-null ArchElement instance;
	 * @return true, if the ArchElement was removed, false otherwise
	 */
	public boolean removeChild(ArchElement s) {
		if (s != null && archElements.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}
	
	public double getZoom() {
		return zoom;
	}	
	
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public String getVersionName() {
		return versionName;
	}	
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getVersionID() {
		return versionID;
	}	
	
	public void setVersionID(int versionID) {
		this.versionID = versionID;
	}
	
	public String getRootNodeFactId() {
		return rootNodeFactId;
	}	
	
	public void setRootNodeFactId(String rootNodeFactId) {
		this.rootNodeFactId = rootNodeFactId;
	}

	public String getSelectedViewType() {
		return selectedViewType;
	}	
	
	public void setSelectedViewType(String selectedViewType) {
		this.selectedViewType = selectedViewType;
	}
	
	public String getSelectedLayoutType() {
		return selectedLayoutType;
	}	
	
	public void setSelectedLayoutType(String selectedLayoutType) {
		this.selectedLayoutType = selectedLayoutType;
	}

	public int getRootNodeFactType() {
		return rootNodeFactType;
	}
		
	public void setRootNodeFactType(int rootNodeFactType) {
		this.rootNodeFactType = rootNodeFactType;
	}
}
