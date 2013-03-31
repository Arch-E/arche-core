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

package edu.cmu.sei.designview.layouts;

import java.awt.geom.Point2D;
import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;

import edu.cmu.sei.ORM.main.ArcheModel;
import edu.cmu.sei.ORM.model.DesignModulerefinementrelation;
import edu.cmu.sei.ORM.model.DesignRealizerelation;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.Decomposition;
import edu.cmu.sei.designview.model.Interface;
import edu.cmu.sei.designview.model.Module;
import edu.cmu.sei.designview.model.Realization;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.SparseTree;
import edu.uci.ics.jung.visualization.Layout;
import edu.uci.ics.jung.visualization.contrib.TreeLayout;

/**
 * @author Hyunwoo Kim
 */
public class DecompositionViewLayout extends ViewLayout{

	public DesignViewDiagram generateTreeLayout(SparseTree archTree){
		
		if(archTree == null)
			throw new NullPointerException("The archTree object must be not null.");
			
		int distX = 200;
		int distY = 100;
		Layout layout = new TreeLayout(archTree,distX,distY);
	
		setGraphLayout(layout, null);
		init();
		
		return generateAViewDiagram();
	}
	
	protected DesignViewDiagram generateAViewDiagram(){
		
		if(layout == null)
			throw new NullPointerException("The layout object must be not null.");

		DesignViewDiagram aviewDiagram = new DesignViewDiagram();
		// creaet all the nodes
		for (Iterator nIt = layout.getGraph().getVertices().iterator(); nIt.hasNext(); )
		{
			Vertex sourceNode = (Vertex)nIt.next();
			Point2D p = layout.getLocation(sourceNode);
			ArchElement sourceElement = (ArchElement)sourceNode.getUserDatum(ArcheModel.ELEMENT);
			sourceElement.setLocation(new Point(p.getX(),p.getY()));				

			int w=80;
			int h=30;
			int t = sourceElement.getName().length()*7; 
			if(t > 80)
				w = t;		
			sourceElement.setSize(new org.eclipse.draw2d.geometry.Dimension(w, h));								
			aviewDiagram.addChild(sourceElement);		
			
		}
		
		// creaet all the edges
		for (Iterator eIt = layout.getGraph().getEdges().iterator(); eIt.hasNext(); )
		{
			edu.uci.ics.jung.graph.Edge e = (edu.uci.ics.jung.graph.Edge) eIt.next();
		    Vertex v1 = (Vertex) e.getEndpoints().getFirst();
		    Vertex v2 = (Vertex) e.getEndpoints().getSecond();
                            
			ArchElement sourceElement = (ArchElement)v1.getUserDatum(ArcheModel.ELEMENT);
			ArchElement destElement = (ArchElement)v2.getUserDatum(ArcheModel.ELEMENT);					

			if(destElement instanceof Interface){
				DesignRealizerelation relationData = (DesignRealizerelation)e.getUserDatum(ArcheModel.RELATION_IN);					
				
				Realization newRelation = new Realization(sourceElement,destElement);					
				newRelation.setFactid(relationData.getFactid());
				newRelation.setOrigin(relationData.getSource());
				newRelation.setParent(sourceElement.getName());
				newRelation.setChild(destElement.getName());
			}
			else if (destElement instanceof Module){
				DesignModulerefinementrelation relationDataIn = (DesignModulerefinementrelation)e.getUserDatum(ArcheModel.RELATION_IN);					

				Decomposition newRelation = new Decomposition(sourceElement,destElement);					
				newRelation.setFactid(relationDataIn.getFactid());
				newRelation.setOrigin(relationDataIn.getSource());
				newRelation.setParent(sourceElement.getName());
				newRelation.setChild(destElement.getName());
			}					
		}
		return aviewDiagram;			
	}
}
