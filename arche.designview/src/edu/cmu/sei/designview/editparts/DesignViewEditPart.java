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

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import edu.cmu.sei.designview.commands.ArchElementCreateCommand;
import edu.cmu.sei.designview.commands.ArchElementSetConstraintCommand;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchModel;
import edu.cmu.sei.designview.model.Interface;
import edu.cmu.sei.designview.model.Module;

/**
 * EditPart for the a AViewDiagram instance.
 * <p>This edit part server as the main diagram container, the white area where
 * everything else is in. Also responsible for the container's layout (the
 * way the container rearanges is contents) and the container's capabilities
 * (edit policies).
 * </p>
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Hyunwoo Kim
 * @date July 10, 2006
 */
public class DesignViewEditPart extends AbstractGraphicalEditPart implements
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
		// disallows the removal of this edit part from its parent
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		// handles constraint changes (e.g. moving and/or resizing) of model elements
		// and creation of new model elements
		installEditPolicy(EditPolicy.LAYOUT_ROLE,  new DesignViewXYLayoutEditPolicy());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setBorder(new MarginBorder(3));
		f.setLayoutManager(new FreeformLayout());

		// Create the static router for the connection layer
		ConnectionLayer connLayer = (ConnectionLayer)getLayer(LayerConstants.CONNECTION_LAYER);
		connLayer.setConnectionRouter(new ShortestPathConnectionRouter(f));
		
		return f;
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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		return getCastedModel().getChildren(); // return a list of shapes
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		// these properties are fired when elements or relations are added into or removed from 
		// the AViewDiagram instance and must cause a call of refreshChildren()
		// to update the diagram's contents.
		if (DesignViewDiagram.CHILD_ADDED_PROP.equals(prop)
				|| DesignViewDiagram.CHILD_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}

	/**
	 * EditPolicy for the Figure used by this edit part.
	 * Children of XYLayoutEditPolicy can be used in Figures with XYLayout.
	 * @author Elias Volanakis
	 */
	private static class DesignViewXYLayoutEditPolicy extends XYLayoutEditPolicy {
		
		protected Command createAddCommand(EditPart child, Object constraint) {
			return null;
		}
		
		/* (non-Javadoc)
		 * @see ConstrainedLayoutEditPolicy#createChangeConstraintCommand(ChangeBoundsRequest, EditPart, Object)
		 */
		protected Command createChangeConstraintCommand(ChangeBoundsRequest request,
				EditPart child, Object constraint) {
			if (child instanceof ArchElementEditPart && constraint instanceof Rectangle) {
				// return a command that can move and/or resize a Shape
				return new ArchElementSetConstraintCommand(
						(ArchElement) child.getModel(), request, (Rectangle) constraint);
			}
			return super.createChangeConstraintCommand(request, child, constraint);
		}
		
		/* (non-Javadoc)
		 * @see ConstrainedLayoutEditPolicy#createChangeConstraintCommand(EditPart, Object)
		 */
		protected Command createChangeConstraintCommand(EditPart child,
				Object constraint) {
			// not used in this example
			return null;
		}
		
		/* (non-Javadoc)
		 * @see LayoutEditPolicy#getCreateCommand(CreateRequest)
		 */
		protected Command getCreateCommand(CreateRequest request) {
			Object childClass = request.getNewObjectType();
			if (childClass == Interface.class || childClass == Module.class) {
				// return a command that can add a Shape to a ShapesDiagram 
				return new ArchElementCreateCommand((ArchElement)request.getNewObject(), 
						(DesignViewDiagram)getHost().getModel(), (Rectangle)getConstraintFor(request));
			}
			return null;
		}
		
		protected Command getDeleteDependantCommand(Request request) {
			return null;
		}
		
	}

}
