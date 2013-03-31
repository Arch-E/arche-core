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

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;

import edu.cmu.sei.designview.DesignViewColorConstants;
import edu.cmu.sei.designview.figures.InterfaceFigure;
import edu.cmu.sei.designview.figures.ModuleFigure;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchModel;
import edu.cmu.sei.designview.model.Interface;
import edu.cmu.sei.designview.model.Module;

/**
 * EditPart used for ArchElment instances (more specific for Interface and
 * Module instances).
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Hyunwoo Kim
 */
public class ArchElementEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener, NodeEditPart {

	private ConnectionAnchor anchor;

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
		// allow removal of the associated model element
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ArchElementComponentEditPolicy());
		// allow the creation of connections and 
		// and the reconnection of connections between Shape instances
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {
			/* (non-Javadoc)
			 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
			 */
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
//				ArchRelationCreateCommand cmd 
//					= (ArchRelationCreateCommand) request.getStartCommand();
//				cmd.setTarget((ArchElement) getHost().getModel());
//				return cmd;
				return null;
			}
			/* (non-Javadoc)
			 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
			 */
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
//				ArchElement source = (ArchElement) getHost().getModel();
//				int style = ((Integer) request.getNewObjectType()).intValue();
//				ArchRelationCreateCommand cmd = new ArchRelationCreateCommand(source, style);
//				request.setStartCommand(cmd);
//				return cmd;
				return null;
			}
			/* (non-Javadoc)
			 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
			 */
			protected Command getReconnectSourceCommand(ReconnectRequest request) {
//				ArchRelation conn = (ArchRelation) request.getConnectionEditPart().getModel();
//				ArchElement newSource = (ArchElement) getHost().getModel();
//				ArchRelationReconnectCommand cmd = new ArchRelationReconnectCommand(conn);
//				cmd.setNewSource(newSource);
//				return cmd;
				return null;
			}
			/* (non-Javadoc)
			 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
			 */
			protected Command getReconnectTargetCommand(ReconnectRequest request) {
//				ArchRelation conn = (ArchRelation) request.getConnectionEditPart().getModel();
//				ArchElement newTarget = (ArchElement) getHost().getModel();
//				ArchRelationReconnectCommand cmd = new ArchRelationReconnectCommand(conn);
//				cmd.setNewTarget(newTarget);
//				return cmd;
				return null;
			}
		});
	}
		
	/*(non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return createFigureForModel();
	}

	/**
	 * Return a IFigure depending on the instance of the current model element.
	 * This allows this EditPart to be used for both sublasses of ArchElement. 
	 */
	private IFigure createFigureForModel() {
		if (getModel() instanceof Interface) {
			InterfaceFigure interfaceFigure = new InterfaceFigure();
			interfaceFigure.setText("�interface�\n" + getCastedModel().getName());
			if(!((Interface)getModel()).getOrigin().equals(ArchModel.DEFAULT_ORIGIN)){
				interfaceFigure.setBackgroundColor(DesignViewColorConstants.UserCreatedBackground);
			}
//			if(((Interface)getModel()).getRoot()){			
//				interfaceFigure.setForegroundColor(ColorConstants.red);
//			}			
			return interfaceFigure;
		} else if (getModel() instanceof Module) {
			ModuleFigure moduleFigure = new ModuleFigure();
			moduleFigure.setText(getCastedModel().getName());
			if(!((Module)getModel()).getOrigin().equals(ArchModel.DEFAULT_ORIGIN)){
				moduleFigure.setBackgroundColor(DesignViewColorConstants.UserCreatedBackground);
			}
//			if(((Module)getModel()).getRoot()){
//				moduleFigure.setForegroundColor(ColorConstants.red);
//			}			
			return moduleFigure;
		} else {
			// if ArchElement gets extended, the conditions above must be updated
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

	private ArchElement getCastedModel() {
		return (ArchElement) getModel();
	}

	protected ConnectionAnchor getConnectionAnchor() {
		if (anchor == null) {
			if (getModel() instanceof Interface) 
				anchor = new ChopboxAnchor(getFigure());
//				anchor = new EllipseAnchor(getFigure());
			else if (getModel() instanceof Module)
				anchor = new ChopboxAnchor(getFigure());
			else
				// if Shapes gets extended the conditions above must be updated
				throw new IllegalArgumentException("unexpected model");
		}
		return anchor;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	protected List getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	protected List getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (ArchElement.SIZE_PROP.equals(prop) || ArchElement.LOCATION_PROP.equals(prop)) {
			refreshVisuals();
		} else if (ArchElement.SOURCE_CONNECTIONS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (ArchElement.TARGET_CONNECTIONS_PROP.equals(prop)) {
			refreshTargetConnections();
		} else if (ArchElement.ROOT_PROP.equals(prop)){
			Boolean val = (Boolean)evt.getNewValue();
			if(val.booleanValue()){
//				getFigure().setForegroundColor(ColorConstants.red);
				this.getViewer().select(this); 
			}
			else{
//				getFigure().setForegroundColor(ColorConstants.black);	
				this.getViewer().deselectAll();				
			}
//			this.fireSelectionChanged();
			this.getViewer().reveal(this);
		}
		
	}

	protected void refreshVisuals() {
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the AViewDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
	}
}
