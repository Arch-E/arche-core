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
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * A modle for Decomposition relation.
 * @author Hyunwoo Kim
 */

public class Decomposition extends ArchRelation {

	private static IPropertyDescriptor[] descriptors;
	private static final long serialVersionUID = 1;
		
//	/** ID for the FactID property value (used for by the corresponding property descriptor).  */
//	private static final String FACTID_PROP = "ArchRelation.FactID";
	/** ID for the Parent property value (used for by the corresponding property descriptor).  */
	private static final String PARENT_PROP = "ArchRelation.Parent";
	/** ID for the Child property value (used for by the corresponding property descriptor).  */
	private static final String CHILD_PROP = "ArchRelation.Child";
	/** ID for the Origin property value (used for by the corresponding property descriptor).  */
	private static final String ORIGIN_PROP = "ArchElement.Origin";
	
	/** Factid of this ArchRelation. */
	private String factid;
	/** Parent(P) factid of this ArchRelation. */
	private String parent;
	/** Child(C) factid of this ArchRelation. */
	private String child;
	
	static {		
		descriptors = new IPropertyDescriptor[] { 
//				new PropertyDescriptor(FACTID_PROP, "FactID"), // id and description pair
				new PropertyDescriptor(PARENT_PROP, "Parent"),
				new PropertyDescriptor(CHILD_PROP, "Child"),
				new PropertyDescriptor(CHILD_PROP, "Interface")
		};
		// use a custom cell editor validator for all four array entries
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor propertyDescriptor= (PropertyDescriptor) descriptors[i];
			propertyDescriptor.setCategory("Decomposition Relation");
		}		
	}		
	
	public Decomposition(ArchElement source, ArchElement target) {
		super(source, target);
	}

	/**
	 * Returns the descriptor for the lineStyle property
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	/**
	 * Returns the lineStyle as String for the Property Sheet
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object propertyId) {
//		if (FACTID_PROP.equals(propertyId)) {
//			return factid;
//		}
		if (PARENT_PROP.equals(propertyId)) {
			return parent;
		}		
		if (CHILD_PROP.equals(propertyId)) {
			return child;
		}		
		if (ORIGIN_PROP.equals(propertyId)) {
			return origin;
		}		
		throw new IllegalArgumentException("unexpected property");
	}	
	
	/**
	 * Sets the lineStyle based on the String provided by the PropertySheet
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		// Can't change any property values.
	}
	
	public void setFactid(String newFactid) {
		factid = newFactid;
	}

	public void setParent(String newParent) {
		parent = newParent;
	}

	public void setChild(String newChild) {
		child = newChild;
	}
	
	public String getFactid(){
		return factid;
	}

	public String getParent(){
		return parent;
	}	
	
	public String getChild(){
		return child;
	}	
	
	/** Origin of this ArchElement. */
	private String origin;
	
	public void setOrigin(String newOrigin) {
		origin = newOrigin;
	}
	
	public String getOrigin(){
		return origin;
	}
	
	
	/** 
	 * Used for indicating that a Connection with solid line style should be created.
	 * @see org.eclipse.gef.examples.shapes.parts.ShapeEditPart#createEditPolicies() 
	 */
//	public static final Integer SOLID_CONNECTION = new Integer(Graphics.LINE_SOLID);
	
	/** A 16x16 pictogram of a solid-lined Decomposition. */
	private static final Image DECOMPOSITION_ICON = createImage("icons/connection_s16.gif");

	public Image getIcon() {
		return DECOMPOSITION_ICON;
	}

	public String toString() {
		return "Decomposition " + hashCode();
	}	
}
