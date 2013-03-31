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

package edu.cmu.sei.designview.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchRelation;

/**
 * A command to reconnect an ArchRelation to a different start point or end point.
 * The command can be undone or redone.
 * <p>
 * This command is designed to be used together with a GraphicalNodeEditPolicy.
 * To use this command propertly, following steps are necessary:
 * </p>
 * <ol>
 * <li>Create a subclass of GraphicalNodeEditPolicy.</li>
 * <li>Override the <tt>getReconnectSourceCommand(...)</tt> method.
 * Here you need to obtain the Connection model element from the ReconnectRequest,
 * create a new ConnectionReconnectCommand, set the new connection <i>source</i> by calling
 * the <tt>setNewSource(Shape)</tt> method and return the command instance.
 * <li>Override the <tt>getReconnectTargetCommand(...)</tt> method.</li>
 * Here again you need to obtain the Connection model element from the ReconnectRequest,
 * create a new ConnectionReconnectCommand, set the new connection <i>target</i> by calling
 * the <tt>setNewTarget(Shape)</tt> method and return the command instance.</li>
 * </ol>
 * @see edu.cmu.sei.designview.editparts.ArchElementEditPart#createEditPolicies() for an
 * 			 example of the above procedure.
 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
 * @see #setNewSource(ArchElement)
 * @see #setNewTarget(ArchElement)
 *
 * @author Hyunwoo Kim
 */
public class ArchRelationReconnectCommand extends Command {

	/** The ArchRelation instance to reconnect. */
	private ArchRelation connection;
	/** The new source endpoint. */
	private ArchElement newSource;
	/** The new target endpoint. */
	private ArchElement newTarget;
	/** The original source endpoint. */
	private final ArchElement oldSource;
	/** The original target endpoint. */
	private final ArchElement oldTarget;

	/**
	 * Instantiate a command that can reconnect a ArchRelation instance to a different source
	 * or target endpoint.
	 * @param conn the ArchRelation instance to reconnect (non-null)
	 * @throws IllegalArgumentException if conn is null
	 */
	public ArchRelationReconnectCommand(ArchRelation conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		this.connection = conn;
		this.oldSource = conn.getSource();
		this.oldTarget = conn.getTarget();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (newSource != null) {
			return checkSourceReconnection();
		} else if (newTarget != null) {
			return checkTargetReconnection();
		}
		return false;
	}

	/**
	 * Return true, if reconnecting the connection-instance to newSource is allowed.
	 */
	private boolean checkSourceReconnection() {
		// connection endpoints must be different Shapes
		if (newSource.equals(oldTarget)) {
			return false;
		}
		// return false, if the connection exists already
		for (Iterator iter = newSource.getSourceConnections().iterator(); iter.hasNext();) {
			ArchRelation conn = (ArchRelation) iter.next();
			// return false if a newSource -> oldTarget connection exists already
			// and it is a different instance than the connection-field
			if (conn.getTarget().equals(oldTarget) &&  !conn.equals(connection)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return true, if reconnecting the connection-instance to newTarget is allowed. 
	 */
	private boolean checkTargetReconnection() {
		// connection endpoints must be different Shapes
		if (newTarget.equals(oldSource)) {
			return false;
		}
		// return false, if the connection exists already
		for (Iterator iter = newTarget.getTargetConnections().iterator(); iter.hasNext();) {
			ArchRelation conn = (ArchRelation) iter.next();
			// return false if a oldSource -> newTarget connection exists already
			// and it is a differenct instance that the connection-field
			if (conn.getSource().equals(oldSource) && !conn.equals(connection)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Reconnect the connection to newSource (if setNewSource(...) was invoked before)
	 * or newTarget (if setNewTarget(...) was invoked before).
	 */
	public void execute() {
		if (newSource != null) {
			connection.reconnect(newSource, oldTarget);
		} else if (newTarget != null) {
			connection.reconnect(oldSource, newTarget);
		} else {
			throw new IllegalStateException("Should not happen");
		}
	}

	/**
	 * Set a new source endpoint for this connection.
	 * When execute() is invoked, the source endpoint of the connection will be attached
	 * to the supplied Shape instance.
	 * <p>
	 * Note: Calling this method, deactivates reconnection of the <i>target</i> endpoint.
	 * A single instance of this command can only reconnect either the source or the target 
	 * endpoint.
	 * </p>
	 * @param connectionSource a non-null ArchElement instance, to be used as a new source endpoint
	 * @throws IllegalArgumentException if connectionSource is null
	 */
	public void setNewSource(ArchElement connectionSource) {
		if (connectionSource == null) {
			throw new IllegalArgumentException();
		}
		setLabel("move relation startpoint");
		newSource = connectionSource;
		newTarget = null;
	}

	/**
	 * Set a new target endpoint for this connection
	 * When execute() is invoked, the target endpoint of the connection will be attached
	 * to the supplied Shape instance.
	 * <p>
	 * Note: Calling this method, deactivates reconnection of the <i>source</i> endpoint.
	 * A single instance of this command can only reconnect either the source or the target 
	 * endpoint.
	 * </p>
	 * @param connectionTarget a non-null ArchElement instance, to be used as a new target endpoint
	 * @throws IllegalArgumentException if connectionTarget is null
	 */
	public void setNewTarget(ArchElement connectionTarget) {
		if (connectionTarget == null) {
			throw new IllegalArgumentException();
		}
		setLabel("move relation endpoint");
		newSource = null;
		newTarget = connectionTarget;
	}

	/**
	 * Reconnect the connection to its original source and target endpoints.
	 */
	public void undo() {
		connection.reconnect(oldSource, oldTarget);
	}
}
