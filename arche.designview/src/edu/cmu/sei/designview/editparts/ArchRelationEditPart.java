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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import edu.cmu.sei.designview.commands.ArchRelationDeleteCommand;
import edu.cmu.sei.designview.figures.DecompositionFigure;
import edu.cmu.sei.designview.figures.DependencyFigure;
import edu.cmu.sei.designview.figures.RealizationFigure;
import edu.cmu.sei.designview.model.ArchModel;
import edu.cmu.sei.designview.model.ArchRelation;
import edu.cmu.sei.designview.model.Decomposition;
import edu.cmu.sei.designview.model.Dependency;
import edu.cmu.sei.designview.model.Realization;

/**
 * Edit part for ArchRelation model elements.
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Hyunwoo Kim
 * @date July 26, 2006
 */
public class ArchRelationEditPart extends AbstractConnectionEditPart implements
		PropertyChangeListener {
	
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
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// Selection handle edit policy. 
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,	new ConnectionEndpointEditPolicy());
		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArchRelationDeleteCommand(getCastedModel());
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return createFigureForModel();
	}
	
	/**
	 * Return a IFigure depending on the instance of the current model element.
	 * This allows this EditPart to be used for both subclasses of ArchRelation. 
	 */
	private IFigure createFigureForModel() {		
		if (getModel() instanceof Dependency) {
			DependencyFigure dependencyFigure = new DependencyFigure();
			if(getCastedModel().isBidirected())
				dependencyFigure.setSourceDecoration(new PolylineDecoration()); // arrow at source endpoint
			if(!((Dependency)getModel()).getOrigin().equals(ArchModel.DEFAULT_ORIGIN)){
				dependencyFigure.setForegroundColor(ColorConstants.darkGreen);
			}	
			return dependencyFigure;
		} else if (getModel() instanceof Decomposition) {
			DecompositionFigure decompositionFigure = new DecompositionFigure();
			if(!((Decomposition)getModel()).getOrigin().equals(ArchModel.DEFAULT_ORIGIN)){
				decompositionFigure.setForegroundColor(ColorConstants.darkGreen);
			}	
			return decompositionFigure;
		} else if (getModel() instanceof Realization) {
			RealizationFigure realizationFigure = new RealizationFigure();
			if(!((Realization)getModel()).getOrigin().equals(ArchModel.DEFAULT_ORIGIN)){
				realizationFigure.setForegroundColor(ColorConstants.darkGreen);
			}	
			return realizationFigure;
		} else {
			// if ArchRelation gets extended, the conditions above must be updated
			throw new IllegalArgumentException();
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

	private ArchRelation getCastedModel() {
		return (ArchRelation) getModel();
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO: You could add the coding to synchronize changes to this ArchRelation
		//       with corresponding records in the ArchE database, whereby the AViewEditor
		//       can be extended to support for direct edition of data in the database.
	}
}
