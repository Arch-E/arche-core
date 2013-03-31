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

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import edu.cmu.sei.designview.commands.ArchRelationDeleteCommand;
import edu.cmu.sei.designview.model.ArchRelation;

/**
 * TreeEditPart used for ArchElement instances (more specific for Module and
 * Interface instances). This is used in the Outline View of the AViewEditor.
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Hyunwoo Kim
 */
public class ArchRelationTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	/**
	 * Create a new instance of this edit part using the given model element.
	 * @param model a non-null ArchElement instance
	 */
	ArchRelationTreeEditPart(ArchRelation model) {
		super(model);
	}

	/**
	 * Upon activation, attach to the model element as a property change listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ArchRelation) getModel()).addPropertyChangeListener(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArchRelationDeleteCommand(getCastedModel());
			}
		});
	}

	/**
	 * Upon deactivation, detach from the model element as a property change listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ArchRelation) getModel()).removePropertyChangeListener(this);
		}
	}

	private ArchRelation getCastedModel() {
		return (ArchRelation) getModel();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
	 */
	protected Image getImage() {
		return getCastedModel().getIcon();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getText()
	 */
	protected String getText() {
		return getCastedModel().toString();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals(); // this will cause an invocation of getImage() and getText(), see below
	}
	
	/**
	 * Refreshes the Widget of this based on the property
	 * given to update. All major properties are updated
	 * irrespective of the property input.
	 *
	 * @param property  Property to be refreshed.
	 */
	protected void refreshVisuals(){
		if (getWidget() instanceof Tree)
			return;		
		Image image = getCastedModel().getIcon();
		TreeItem item = (TreeItem)getWidget();
		if (image != null)
			image.setBackground(item.getParent().getBackground());
		setWidgetImage(image);
		setWidgetText(getCastedModel().toString());
	}
}
