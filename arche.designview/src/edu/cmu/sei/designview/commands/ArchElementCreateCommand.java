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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;


/**
 * A command to add a ArchElement to a AViewDiagram.
 * The command can be undone or redone.
 * @author Hyunwoo Kim
 */

public class ArchElementCreateCommand extends Command {

	/** The new ArchElement. */ 
	private ArchElement newElement;
	/** AViewDiagram to add to. */
	private final DesignViewDiagram parent;
	/** The bounds of the new Shape. */
	private Rectangle bounds;

	/**
	 * Create a command that will add a new ArchElement to a AViewDiagram.
	 * @param newElement the new ArchElement that is to be added
	 * @param parent the AViewDiagram that will hold the new element
	 * @param bounds the bounds of the new ArchElement; the size can be (-1, -1) if not known
	 * @throws IllegalArgumentException if any parameter is null, or the request
	 * 						  does not provide a new ArchElement instance
	 */
	public ArchElementCreateCommand(ArchElement newElement, DesignViewDiagram parent, Rectangle bounds) {
		this.newElement = newElement;
		this.parent = parent;
		this.bounds = bounds;
		setLabel("ArchElement creation");
	}

	/**
	 * Can execute if all the necessary information has been provided. 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return newElement != null && parent != null && bounds != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		newElement.setLocation(bounds.getLocation());
		Dimension size = bounds.getSize();
		if (size.width > 0 && size.height > 0)
			newElement.setSize(size);
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		parent.addChild(newElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.removeChild(newElement);
	}
		
}
