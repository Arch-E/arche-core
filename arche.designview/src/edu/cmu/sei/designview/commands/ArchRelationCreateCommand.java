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

import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.commands.Command;

import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchRelation;
import edu.cmu.sei.designview.model.Decomposition;
import edu.cmu.sei.designview.model.Realization;


/**
 * A command to create a connection between two ArchElements.
 * The command can be undone or redone.
 * <p>
 * This command is designed to be used together with a GraphicalNodeEditPolicy.
 * To use this command properly, following steps are necessary:
 * </p>
 * <ol>
 * <li>Create a subclass of GraphicalNodeEditPolicy.</li>
 * <li>Override the <tt>getConnectionCreateCommand(...)</tt> method, 
 * to create a new instance of this class and put it into the CreateConnectionRequest.</li>
 * <li>Override the <tt>getConnectionCompleteCommand(...)</tt>  method,
 * to obtain the Command from the ConnectionRequest, call setTarget(...) to set the
 * target endpoint of the connection and return this command instance.</li>
 * </ol>
 * @see edu.cmu.sei.designview.editparts.ShapeEditPart#createEditPolicies() for an
 * 			 example of the above procedure.
 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
 *
 * @author Hyunwoo Kim
 */
public class ArchRelationCreateCommand extends Command {

	/** The ArchRelation instance. */
	private ArchRelation connection;
	/** The desired line style for the connection (dashed or solid). */
	private final int lineStyle;

	/** Start endpoint for the connection. */
	private final ArchElement source;
	/** Target endpoint for the connection. */
	private ArchElement target;

	/**
	 *	Instantiate a command that can create a connection between two shapes.
	 * @param source the source endpoint (a non-null Shape instance)
	 * @param lineStyle the desired line style. See Connection#setLineStyle(int) for details
	 * @throws IllegalArgumentException if source is null
	 * @see Connection#setLineStyle(int)
	 */
	public ArchRelationCreateCommand(ArchElement source, int lineStyle) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel("ArchRelation creation");
		this.source = source;
		this.lineStyle = lineStyle;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		// disallow source -> source connections
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Iterator iter = source.getSourceConnections().iterator(); iter.hasNext();) {
			ArchRelation conn = (ArchRelation) iter.next();
			if (conn.getTarget().equals(target)) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if(lineStyle == Graphics.LINE_SOLID)
		{
			// create a new ArchRelation between source and target
			connection = new Decomposition(source, target);
			// use the supplied line style
			connection.setLineStyle(lineStyle);
		}
		else if(lineStyle == Graphics.LINE_DASH ){
			// create a new ArchRelation between source and target
			connection = new Realization(source, target);
			// use the supplied line style
			connection.setLineStyle(lineStyle);			
		}
			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		connection.reconnect();
	}

	/**
	 * Set the target endpoint for the connection.
	 * @param target that target endpoint (a non-null ArchElement instance)
	 * @throws IllegalArgumentException if target is null
	 */
	public void setTarget(ArchElement target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		connection.disconnect();
	}
}
