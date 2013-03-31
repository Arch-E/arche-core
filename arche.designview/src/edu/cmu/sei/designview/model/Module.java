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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * A rectangular Module.
 * @author Hyunwoo Kim
 */

public class Module extends ArchElement {
				
	/** 
	 * A static array of property descriptors.
	 * There is one IPropertyDescriptor entry per editable property.
	 * @see #getPropertyDescriptors()
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	private static IPropertyDescriptor[] descriptors;
//	/** ID for the Height property value (used for by the corresponding property descriptor). */
//	private static final String HEIGHT_PROP = "ArchElement.Size.Height";
//	/** ID for the Width property value (used for by the corresponding property descriptor). */
//	private static final String WIDTH_PROP = "ArchElement.Size.Width";
//
//	/** ID for the X property value (used for by the corresponding property descriptor).  */
//	private static final String XPOS_PROP = "ArchElement.Location.X";
//	/** ID for the Y property value (used for by the corresponding property descriptor).  */
//	private static final String YPOS_PROP = "ArchElement.Location.Y";
//
//	/** ID for the FactID property value (used for by the corresponding property descriptor).  */
//	private static final String FACTID_PROP = "ArchElement.FactID";
	/** ID for the Name property value (used for by the corresponding property descriptor).  */
	private static final String NAME_PROP = "ArchElement.Name";
	/** ID for the Origin property value (used for by the corresponding property descriptor).  */
	private static final String ORIGIN_PROP = "ArchElement.Origin";
	/** ID for the Cost of change property value (used for by the corresponding property descriptor).  */
	private static final String COSTOFCHANGE_PROP = "ArchElement.CostOfChange";	
	
		
	/** Factid of this ArchElement */
	private String factid;
	/** Name of this ArchElement */
	private String name;
	/** Cost of change of this ArchElement */
	private Double costOfChange;
		
	static {
		descriptors = new IPropertyDescriptor[] { 
//				new PropertyDescriptor(FACTID_PROP, "FactID"), // id and description pair
				new PropertyDescriptor(NAME_PROP, "Name"),
				new PropertyDescriptor(ORIGIN_PROP, "Created by"),
//				new PropertyDescriptor(COSTOFCHANGE_PROP, "Cost of change"),
//				new TextPropertyDescriptor(XPOS_PROP, "X"), 
//				new TextPropertyDescriptor(YPOS_PROP, "Y"),
//				new TextPropertyDescriptor(WIDTH_PROP, "Width"),
//				new TextPropertyDescriptor(HEIGHT_PROP, "Height"),
		};
		// use a custom cell editor validator for all four array entries
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor propertyDescriptor= (PropertyDescriptor) descriptors[i];
			if(propertyDescriptor instanceof TextPropertyDescriptor){
//				propertyDescriptor.setCategory("Geometry");
//				propertyDescriptor.setValidator(new ICellEditorValidator() {
//					public String isValid(Object value) {
//						int intValue = -1;
//						try {
//							intValue = Integer.parseInt((String) value);
//						} catch (NumberFormatException exc) {
//							return "Not a number";
//						}
//						return (intValue >= 0) ? null : "Value must be >=  0";
//					}
//				});								
			}
			else
				propertyDescriptor.setCategory("Module Element");
		}
	} // static	
	
	/**
	 * Returns an array of IPropertyDescriptors for this ArchElement.
	 * <p>The returned array is used to fill the property view, when the edit-part corresponding
	 * to this model element is selected.</p>
	 * @see #descriptors
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	/**
	 * Return the property value for the given propertyId, or null.
	 * <p>The property view uses the IDs from the IPropertyDescriptors array 
	 * to obtain the value of the corresponding properties.</p>
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public Object getPropertyValue(Object propertyId) {
//		if (FACTID_PROP.equals(propertyId)) {
//			return factid;
//		}
		if (NAME_PROP.equals(propertyId)) {
			return name;
		}		
		if (ORIGIN_PROP.equals(propertyId)) {
			return origin;
		}		
		if (COSTOFCHANGE_PROP.equals(propertyId)) {
			return costOfChange;
		}		
		
//		if (XPOS_PROP.equals(propertyId)) {
//			return Integer.toString(getLocation().x);
//		}
//		if (YPOS_PROP.equals(propertyId)) {
//			return Integer.toString(getLocation().y);
//		}
//		if (HEIGHT_PROP.equals(propertyId)) {
//			return Integer.toString(getSize().height);
//		}
//		if (WIDTH_PROP.equals(propertyId)) {
//			return Integer.toString(getSize().width);
//		}
		throw new IllegalArgumentException("unexpected property");
	}

	/**
	 * Set the property value for the given property id.
	 * If no matching id is found, the call is forwarded to the superclass.
	 * <p>The property view uses the IDs from the IPropertyDescriptors array to set the values
	 * of the corresponding properties.</p>
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public void setPropertyValue(Object propertyId, Object value) {
//		if (XPOS_PROP.equals(propertyId)) {
//			int x = Integer.parseInt((String) value);
//			setLocation(new Point(x, getLocation().y));
//		} else if (YPOS_PROP.equals(propertyId)) {
//			int y = Integer.parseInt((String) value);
//			setLocation(new Point(getLocation().x, y));
//		} else if (HEIGHT_PROP.equals(propertyId)) {
//			int height = Integer.parseInt((String) value);
//			setSize(new Dimension(getSize().width, height));
//		} else if (WIDTH_PROP.equals(propertyId)) {
//			int width = Integer.parseInt((String) value);
//			setSize(new Dimension(width, getSize().height));
//		} else 
//			throw new IllegalArgumentException("unexpected property");
	}
		
	public void setFactid(String newFactid) {
		factid = newFactid;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	public String getFactid(){
		return factid;
	}

	public String getName(){
		return name;
	}
	
	/** Origin of this ArchElement. */
	private String origin;
	
	public void setOrigin(String newOrigin) {
		origin = newOrigin;
	}
	
	public String getOrigin(){
		return origin;
	}

	private Boolean bRoot = Boolean.FALSE;
	
	public void setRoot(boolean bRoot) {
		if(bRoot)
			this.bRoot = Boolean.TRUE;
		else
			this.bRoot = Boolean.FALSE;
		this.firePropertyChange(ArchElement.ROOT_PROP, null, this.bRoot);
	}
	
	public boolean getRoot(){
		return bRoot.booleanValue();
	}
	
	/** A 16x16 pictogram of a rectangular Module. */
	private static final Image MODULE_ICON = createImage("icons/rectangle16.gif");

	private static final long serialVersionUID = 1;
		
	public Module(Object data){
		this.data = data;
	}
	
	public Image getIcon() {
		return MODULE_ICON;
	}

	public String toString() {
		return getName();
	}

	public void setCostOfChange(Double costOfChange) {
		this.costOfChange = costOfChange;
	}		

	public Double getCostOfChange() {
		return this.costOfChange;
	}
}
