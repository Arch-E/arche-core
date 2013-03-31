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

package edu.cmu.sei.designview.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchModel;


/**
 * TreeEditPart for a AViewDiagram instance.
 * This is used in the Outline View of the AViewEditor.
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Hyunwoo Kim
 */
public class DesignViewTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	/** 
	 * Create a new instance of this edit part using the given model element.
	 * @param model a non-null AViewDiagram instance
	 */
	DesignViewTreeEditPart(DesignViewDiagram model) {
		super(model);
	}

	/**
	 * Upon activation, attach to the model element as a property change listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ArchModel) getModel()).addPropertyChangeListener(this);
		}
	}

	/* (non-Javadoc)
	 * @see cmu.sei.archeGui.editparts.ArchElementTreeEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// If this editpart is the root content of the viewer, then disallow removal
		if (getParent() instanceof RootEditPart) {
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		}
	}

	/**
	 * Upon deactivation, detach from the model element as a property change listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ArchModel) getModel()).removePropertyChangeListener(this);
		}
	}

	private DesignViewDiagram getCastedModel() {
		return (DesignViewDiagram) getModel();
	}

	/**
	 * Convenience method that returns the EditPart corresponding to a given child.
	 * @param child a model element instance
	 * @return the corresponding EditPart or null
	 */
	private EditPart getEditPartForChild(Object child) {
		return (EditPart) getViewer().getEditPartRegistry().get(child);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		return getCastedModel().getChildren(); // a list of shapes
	}

	/* (non-Javadoc)
	* @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	*/
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (DesignViewDiagram.CHILD_ADDED_PROP.equals(prop)) {
			// add a child to this edit part
			// causes an additional entry to appear in the tree of the outline view
			addChild(createChild(evt.getNewValue()), -1);
		} else if (DesignViewDiagram.CHILD_REMOVED_PROP.equals(prop)) {
			// remove a child from this edit part
			// causes the corresponding edit part to disappear from the tree in the outline view
			removeChild(getEditPartForChild(evt.getNewValue()));
		} else {
			refreshVisuals();
		}
	}
}
