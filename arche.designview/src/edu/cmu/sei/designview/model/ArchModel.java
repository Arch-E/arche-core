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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Abstract prototype of a model element
 * <p>This class provides features necessary for all model elements, like:</p>
 * <ul>
 * <li>property-change support (used to notify edit parts of model changes),</li> 
 * <li>property-source support (used to display property values in the Property View) and</li>
 * <li>serialization support (the model hierarchy must be serializable, so that the editor can
 * save and restore a binary representation. You might not need this, if you store the model
 * a non-binary form like XML).</li>
 * </ul>
 * @author Hyunwoo Kim
 */

public abstract class ArchModel implements IPropertySource, Serializable {

	/** 
	 * Data which can be used to make a reference to any type of Arche DB data representation.
	 * For example, this can be DesignModel or DesignInterface.
	 * @see edu.cmu.sei.ORM.model
	 */
	public Object data;

	/** Default origin of this model **/
	public static final String DEFAULT_ORIGIN = "ArchE";
	
	/** An empty property descriptor. */
	private static final IPropertyDescriptor[] EMPTY_ARRAY = new IPropertyDescriptor[0];

	private static final long serialVersionUID = 1;
	/** Delegate used to implemenent property-change-support. */
	private transient PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	/** 
	 * Attach a non-null PropertyChangeListener to this object.
	 * @param l a non-null PropertyChangeListener instance
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		pcsDelegate.addPropertyChangeListener(l);
	}

	/** 
	 * Report a property change to registered listeners (for example edit parts).
	 * @param property the programmatic name of the property that changed
	 * @param oldValue the old value of this property
	 * @param newValue the new value of this property
	 */
	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}

	/**
	 * Returns a value for this property source that can be edited in a property sheet.
	 * <p>My personal rule of thumb:</p>
	 * <ul>
	 * <li>model elements should return themselves and</li> 
	 * <li>custom IPropertySource implementations (like DimensionPropertySource in the GEF-logic
	 * example) should return an editable value.</li>
	 * </ul>
	 * <p>Override only if necessary.</p>
	 * @return this instance
	 */
	public Object getEditableValue() {
		return this;
	}

	/**
	 * Children should override this. The default implementation returns an empty array.
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return EMPTY_ARRAY;
	}

	/**
	 * Children should override this. The default implementation returns null.
	 */
	public Object getPropertyValue(Object id) {
		return null;
	}

	/**
	 * Children should override this. The default implementation returns false.
	 */
	public boolean isPropertySet(Object id) {
		return false;
	}

	/** 
	 * Deserialization constructor. Initializes transient fields.
	 * @see java.io.Serializable
	 */
	private void readObject(ObjectInputStream in) 
	throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		pcsDelegate = new PropertyChangeSupport(this);
	}

	/** 
	 * Remove a PropertyChangeListener from this component.
	 * @param l a PropertyChangeListener instance
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		if (l != null) {
			pcsDelegate.removePropertyChangeListener(l);
		}
	}

	/**
	 * Children should override this. The default implementation does nothing.
	 */
	public void resetPropertyValue(Object id) {
		// do nothing
	}

	/**
	 * Children should override this. The default implementation does nothing.
	 */
	public void setPropertyValue(Object id, Object value) {
		// do nothing
	}
}
