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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;

import edu.cmu.sei.designview.DesignViewColorConstants;

public class UMLClassFigure extends Figure {
	  public static Color classColor = DesignViewColorConstants.ArchECreatedBackground;
	  private CompartmentFigure attributeFigure = new CompartmentFigure();
//	  private CompartmentFigure methodFigure = new CompartmentFigure();
	  private static int DEFAULT_MARGIN_SIZE = 7;
	  
	  public UMLClassFigure(TextFlow name) {		  	  
	    ToolbarLayout layout = new ToolbarLayout();
	    setLayoutManager(layout);	
	    setBorder(new LineBorder(ColorConstants.black,1));
	    setBackgroundColor(classColor);
	    setOpaque(true);    
	    
		FlowPage flowPage = new FlowPage();
		flowPage.setBorder(new MarginBorder(DEFAULT_MARGIN_SIZE));
		flowPage.setHorizontalAligment(PositionConstants.CENTER);
		name.setLayoutManager(new ParagraphTextLayout(name,
				ParagraphTextLayout.WORD_WRAP_SOFT));		
		
		flowPage.add(name);
		add(flowPage);
	    
	    add(attributeFigure);
//	    add(methodFigure);
	  }
	  public CompartmentFigure getAttributesCompartment() {
	    return attributeFigure;
	  }
//	  public CompartmentFigure getMethodsCompartment() {
//	    return methodFigure;
//	  }
	}