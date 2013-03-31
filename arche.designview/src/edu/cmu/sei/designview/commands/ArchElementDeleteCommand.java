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
import java.util.List;

import org.eclipse.gef.commands.Command;

import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchRelation;

/**
 * A command to remove a ArchElement from its parent.
 * The command can be undone or redone.
 * @author Hyunwoo Kim
 */
public class ArchElementDeleteCommand extends Command {

	/** ArchElement to remove. */
	private final ArchElement child;

	/** AViewDiagram to remove from. */
	private final DesignViewDiagram parent;
	/** Holds a copy of the outgoing connections of child. */
	private List sourceConnections;
	/** Holds a copy of the incoming connections of child. */
	private List targetConnections;
	/** True, if child was removed from its parent. */
	private boolean wasRemoved;

	/**
	 * Create a command that will remove the ArchElement from its parent.
	 * @param parent the AViewDiagram containing the child
	 * @param child    the ArchElement to remove
	 * @throws IllegalArgumentException if any parameter is null
	 */
	public ArchElementDeleteCommand(DesignViewDiagram parent, ArchElement child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("ArchElement deletion");
		this.parent = parent;
		this.child = child;
	}

	/**
	 * Reconnects a List of ArchRelations with their previous endpoints.
	 * @param connections a non-null List of connections
	 */
	private void addConnections(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ArchRelation conn = (ArchRelation) iter.next();
			conn.reconnect();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return wasRemoved;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		// store a copy of incoming & outgoing connections before proceeding 
		sourceConnections = child.getSourceConnections();
		targetConnections = child.getTargetConnections();
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// remove the child and disconnect its connections
		wasRemoved = parent.removeChild(child);
		if (wasRemoved) {
			removeConnections(sourceConnections);
			removeConnections(targetConnections);
		}
	}

	/**
	 * Disconnects a List of ArchRelations from their endpoints.
	 * @param connections a non-null List of connections
	 */
	private void removeConnections(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ArchRelation conn = (ArchRelation) iter.next();
			conn.disconnect();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		// add the child and reconnect its connections
		if (parent.addChild(child)) {
			addConnections(sourceConnections);
			addConnections(targetConnections);
		}
	}
}
