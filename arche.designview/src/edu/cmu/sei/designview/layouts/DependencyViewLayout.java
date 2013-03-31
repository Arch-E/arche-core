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
import edu.cmu.sei.ORM.model.DesignModuledependencyrelation;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.Dependency;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.UndirectedSparseEdge;
import edu.uci.ics.jung.visualization.FRLayout;
import edu.uci.ics.jung.visualization.contrib.CircleLayout;
import edu.uci.ics.jung.visualization.contrib.KKLayout;

/**
 * @author Hyunwoo Kim
 */

public class DependencyViewLayout extends ViewLayout {

	public static final String FR_LAYOUT = "FR Layout";
	public static final String KK_LAYOUT = "KK Layout";
	public static final String CIRCLE_LAYOUT = "Circle Layout";
	
	public DesignViewDiagram generateFRLayout(SparseGraph archGraph){
		if(archGraph == null)
			throw new NullPointerException("The archGraph object must be not null.");
				
		double attraction = 4.9;
		double repulsion = 0.9;
		layout = new FRLayout(archGraph);
		((FRLayout)layout).setAttractionMultiplier(attraction);
		((FRLayout)layout).setRepulsionMultiplier(repulsion);
		
		setGraphLayout(layout, null);
		init();		
		
		return generateAViewDiagram();
	}

	public DesignViewDiagram generateKKLayout(SparseGraph archGraph){
		if(archGraph == null)
			throw new NullPointerException("The archGraph object must be not null.");
		
		double length_factor = 1.5;
		layout = new KKLayout(archGraph);
		((KKLayout)layout).setLengthFactor(length_factor);
						
		setGraphLayout(layout, null);
		init();

		return generateAViewDiagram();
	}
	
	
	public DesignViewDiagram generateCircleLayout(SparseGraph archGraph){
		if(archGraph == null)
			throw new NullPointerException("The archGraph object must be not null.");
		
		layout = new CircleLayout(archGraph);
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
			
			int w=90;
			int h=80; 
//			int w=80;
//			int h=30;
//			int t = sourceElement.getName().length()*7; 
//			if(t > 80)
//				w = t;
			
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

			if(e instanceof DirectedSparseEdge){
				DesignModuledependencyrelation relationData = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_IN);					
				
				Dependency newRelation = new Dependency(sourceElement,destElement);					
				newRelation.setBidirected(false);
				newRelation.setFactid(relationData.getFactid());
				newRelation.setOrigin(relationData.getSource());
				newRelation.setParent(sourceElement.getName());
				newRelation.setChild(destElement.getName());
				newRelation.setIncoming(relationData.getProbability().doubleValue());
				newRelation.setOutgoing(0);
			}
			else if (e instanceof UndirectedSparseEdge){
				DesignModuledependencyrelation relationDataIn = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_IN);					
				DesignModuledependencyrelation relationDataOut = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_OUT);					

				Dependency newRelation = new Dependency(sourceElement,destElement);					
				newRelation.setBidirected(true);
				String pairFactid = relationDataIn.getFactid() + "," + relationDataOut.getFactid();
				newRelation.setFactid(pairFactid);
				newRelation.setOrigin(relationDataIn.getSource());
				newRelation.setParent(sourceElement.getName());
				newRelation.setChild(destElement.getName());		
				newRelation.setIncoming(relationDataIn.getProbability().doubleValue()); 
				newRelation.setOutgoing(relationDataOut.getProbability().doubleValue());						
			}	
		}
		return aviewDiagram;
	}
}
