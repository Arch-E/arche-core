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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchRelation;

/**
 * Factory that maps model elements to edit parts.
 * 
 * @author Hyunwoo Kim
 * @date July 10, 2006
 */
public class DesignViewEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object modelElement) {
		// get EditPart for model element
		EditPart part = getPartForElement(modelElement);
		// store model element in EditPart
		part.setModel(modelElement);
		return part;
	}

	/**
	 * Maps an object to an EditPart. 
	 * @throws RuntimeException if no match was found (programming error)
	 */
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof DesignViewDiagram) {
			return new DesignViewEditPart();
		}
		if (modelElement instanceof ArchElement) {
			return new ArchElementEditPart();
		}
		if (modelElement instanceof ArchRelation) {
			return new ArchRelationEditPart();
		}
		throw new RuntimeException(
				"Can't create part for model element: "
				+ ((modelElement != null) ? modelElement.getClass().getName() : "null"));
	}
}
