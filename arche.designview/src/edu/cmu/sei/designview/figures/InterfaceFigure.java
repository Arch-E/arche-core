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

package edu.cmu.sei.designview.figures;

import org.eclipse.draw2d.text.TextFlow;

/**
 * A figure, for Interface, with an embedded Label that contains text.
 *
 * @author Hyunwoo Kim
 */
public class InterfaceFigure extends UMLClassFigure {

	/** The inner TextFlow **/
	private TextFlow textFlow; 
	
	/**
	 *  Creates a new ModuleFigure with a default Label.
	 */
	public InterfaceFigure() {		
		this(new TextFlow());	
	}

	/** 
	 * Creates a new InterfaceFigure with a textflow.
	 * and a Label.
	 * 
	 * @param name the textflow
	 */
	public InterfaceFigure(TextFlow name) {
		super(name);
		textFlow = name;
	}

	/**
	 * Returns the text inside the Label.
	 * 
	 * @return the text flow inside the text.
	 */
	public String getText() {
		return textFlow.getText();
	}

	/**
	 * Sets the text of the Label to the given value.
	 * 
	 * @param newText the new text value.
	 */
	public void setText(String newText) {
		textFlow.setText(newText);
	}	
}
