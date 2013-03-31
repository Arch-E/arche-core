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

package edu.cmu.sei.ORM.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.graph.Node;
import org.objectstyle.cayenne.access.DataContext;
import org.objectstyle.cayenne.query.SQLTemplate;

import edu.cmu.sei.ORM.model.DesignModule;
import edu.cmu.sei.ORM.model.DesignModuledependencyrelation;
import edu.cmu.sei.ORM.model.DesignModuleinterface;
import edu.cmu.sei.ORM.model.DesignModulerefinementrelation;
import edu.cmu.sei.ORM.model.DesignRealizerelation;
import edu.cmu.sei.ORM.model.Versions;
import edu.cmu.sei.designview.DesignViewPlugin;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.Interface;
import edu.cmu.sei.designview.model.Module;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.impl.SimpleDirectedSparseVertex;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseTree;
import edu.uci.ics.jung.graph.impl.SparseVertex;
import edu.uci.ics.jung.graph.impl.UndirectedSparseEdge;
import edu.uci.ics.jung.utils.UserData;



/**
 * Generates a directed and/or undirected graph for each view such as Decomposition and Dependency
 * from the ArchE model in the ArchE database
 *
 * @author Hyunwoo Kim
 * @date July 10, 2006
 */

public class ArcheModel {
	
	private static ArcheModel instance;
	private static DataContext context; 	
	public final static String DECOMPOSITION = "Decomposition";
	public final static String REALIZATION = "Realization";
	public final static String DEPENDENCY = "Dependency";
	public final static String ELEMENT = "Element";
	public final static String RELATION_IN = "RelationIn";
	public final static String RELATION_OUT = "RelationOut";
	
	private ArcheModel() {
		ArcheConfig.configure();
		context = DataContext.createDataContext();
	}
	
	public static ArcheModel GetInstance()
	{
		if(instance == null) // Need to create the instance
        {
            instance = new ArcheModel(); 
        }
        return instance;
	}

	//	for Full Dependency Arche Model
	public SparseGraph getFullDependencyArcheModel(String versionName, String parentFactId, boolean bRootMarked, int parentFactType)
	{
		
		SparseGraph archGraph = new SparseGraph();
		Node parentNode = null;
		if(parentFactType == DesignViewPlugin.ROOT_FACT_TYPE_MODULE)
			parentNode = getRootModuleNode(versionName,parentFactId, bRootMarked);
		else if (parentFactType == DesignViewPlugin.ROOT_FACT_TYPE_MODULE_INTERFACE)
			parentNode = getRootModuleInterfaceNode(versionName,parentFactId, bRootMarked);

		if(parentNode == null)
			return null;
		
		Vertex parentVertex = new SparseVertex();
		parentVertex.addUserDatum(ELEMENT,parentNode.data, UserData.SHARED);
		archGraph.addVertex(parentVertex);

		ArrayList childModuleNodeList = getDependencyChildModuleNodes(versionName, parentFactId);
		if(childModuleNodeList.size()!= 0)
		{
			createDependencyGraph(archGraph,versionName,parentFactId, parentVertex,childModuleNodeList);
		}
		
		ArrayList childInterfaceNodeList = getDependencyChildInterfaceNodes(versionName, parentFactId);
		if(childInterfaceNodeList.size()!= 0)
		{
			createDependencyGraph(archGraph,versionName,parentFactId, parentVertex,childInterfaceNodeList);
		}
		

		ArrayList parentModuleNodeList = getDependencyParentModuleNodes(versionName, parentFactId);
		if(parentModuleNodeList.size()!= 0)
		{
			createReverseDependencyGraph(archGraph,versionName,parentFactId, parentVertex,parentModuleNodeList);
		}
		
		ArrayList parentInterfaceNodeList = getDependencyParentInterfaceNodes(versionName, parentFactId);
		if(parentInterfaceNodeList.size()!= 0)
		{
			createReverseDependencyGraph(archGraph,versionName,parentFactId, parentVertex,parentInterfaceNodeList);
		}
		
//		new DirectedGraphLayout().visit(ArcheModel);
		return archGraph;
	}	
	
	private Vertex findExistingVertex(SparseGraph archGraph, String factId){
	    Set vertexSet = archGraph.getVertices();
	    Iterator vIt = vertexSet.iterator(); 
		while(vIt.hasNext())
		{
			Vertex v = (Vertex)vIt.next();
			ArchElement e = (ArchElement)v.getUserDatum(ELEMENT);
			if(e != null){
				if(e.getFactid().equalsIgnoreCase(factId))
						return v;					
			}
		}
		return null;
	}
	
	private Edge findExistingEdge(SparseGraph archGraph, String sourceFactId, String destinationFactId){
	    Set edgeSet = archGraph.getEdges();
	    Iterator eIt = edgeSet.iterator(); 
		while(eIt.hasNext())
		{
			Edge edge = (Edge)eIt.next();
		    Vertex sv = (Vertex) edge.getEndpoints().getFirst();
		    Vertex dv = (Vertex) edge.getEndpoints().getSecond();
                                        
		    if(sv!=null && dv!=null){
				ArchElement se = (ArchElement)sv.getUserDatum(ArcheModel.ELEMENT);
				ArchElement de = (ArchElement)dv.getUserDatum(ArcheModel.ELEMENT);				
				if(se!=null && de!=null && se.getFactid().equals(sourceFactId) && de.getFactid().equals(destinationFactId))					
					return edge;					
		    }
		}
		return null;
	}
	
	private void createDependencyGraph(SparseGraph archGraph, String versionName,String parentFactId,Vertex parentVertex,ArrayList childNodeList)
	{
		Iterator childIt = childNodeList.listIterator();
		
		
		while(childIt.hasNext())
		{
			Node childNode = (Node)childIt.next();
			ArchElement theChildElement = (ArchElement)childNode.data;
		   
			/**
			 * Check if the child vertex has been visited.
			 * If yes, create an edge from the parent to child vertex.
			 * Otherwise, create a new child vertex and a new directed edge
			 * from the parent to this new child vertex, and then, repeat this
			 * with the child's descendants which can be either Module or Interface.
			 */            
			Vertex childVertex = findExistingVertex(archGraph, theChildElement.getFactid());
			
			if(childVertex != null) {
//	            Edge newEdge = new UndirectedSparseEdge(parentVertex,childVertex);
				/**
				 * Check if a directed edge from the child vertex to its parent vertex has been created.
				 * If yes, remove the existing edge to create/add an new bi-directed edge.
				 * Otherwise, add a new directed edge from the parent to child vertex if not already existed.
				 */
				Edge reversedEdge = findExistingEdge(archGraph,theChildElement.getFactid(),parentFactId);
				if(reversedEdge != null){
					DesignModuledependencyrelation theInRelationExisting = (DesignModuledependencyrelation)reversedEdge.getUserDatum(RELATION_IN); 
					DesignModuledependencyrelation theOutRelationExisting = (DesignModuledependencyrelation)reversedEdge.getUserDatum(RELATION_OUT); 
					if(theInRelationExisting != null && theOutRelationExisting == null){
						archGraph.removeEdge(reversedEdge);
			            Edge newEdge = new UndirectedSparseEdge(childVertex,parentVertex);
						DesignModuledependencyrelation theRelationNew = getDependencyRelation(versionName,parentFactId,theChildElement.getFactid());				
						newEdge.addUserDatum(RELATION_OUT,theRelationNew,UserData.SHARED);	
						newEdge.addUserDatum(RELATION_IN,theInRelationExisting,UserData.SHARED);	
						archGraph.addEdge(newEdge);										
					}
				}
				else {
					Edge forwardEdge = findExistingEdge(archGraph, parentFactId, theChildElement.getFactid()); 					
					if(forwardEdge == null){
			            Edge newEdge = new DirectedSparseEdge(parentVertex,childVertex); // (Source, Destination)
						DesignModuledependencyrelation theRelation = getDependencyRelation(versionName,parentFactId,theChildElement.getFactid());				
						newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
						archGraph.addEdge(newEdge);										
					}
				}
			}
			else {
				childVertex = new SparseVertex();
				childVertex.addUserDatum(ELEMENT,theChildElement, UserData.SHARED);
				archGraph.addVertex(childVertex);
				
				Edge newEdge = new DirectedSparseEdge(parentVertex,childVertex);
				DesignModuledependencyrelation theRelation = getDependencyRelation(versionName,parentFactId,theChildElement.getFactid());				
				newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
				archGraph.addEdge(newEdge);
				
				ArrayList childModuleNodeList = getDependencyChildModuleNodes(versionName, theChildElement.getFactid());
				if(childModuleNodeList.size()!= 0)
				{
					createDependencyGraph(archGraph, versionName,theChildElement.getFactid(),childVertex,childModuleNodeList);
				}
				
				ArrayList childInterfaceNodeList = getDependencyChildInterfaceNodes(versionName, theChildElement.getFactid());
				if(childInterfaceNodeList.size()!= 0)
				{
					createDependencyGraph(archGraph, versionName,theChildElement.getFactid(),childVertex,childInterfaceNodeList);										
				}				

				ArrayList parentModuleNodeList = getDependencyParentModuleNodes(versionName, theChildElement.getFactid());
				if(parentModuleNodeList.size()!= 0)
				{
					createReverseDependencyGraph(archGraph, versionName,theChildElement.getFactid(),childVertex,parentModuleNodeList);
				}
				
				ArrayList parentInterfaceNodeList = getDependencyParentInterfaceNodes(versionName, theChildElement.getFactid());
				if(parentInterfaceNodeList.size()!= 0)
				{
					createReverseDependencyGraph(archGraph,versionName,theChildElement.getFactid(), childVertex,parentInterfaceNodeList);
				}
			}
		}
	}

	private void createReverseDependencyGraph(SparseGraph archGraph, String versionName,String childFactId,Vertex childVertex,ArrayList parentNodeList)
	{
		Iterator parentIt = parentNodeList.listIterator();
		
		
		while(parentIt.hasNext())
		{
			Node parentNode = (Node)parentIt.next();
			ArchElement theParentElement = (ArchElement)parentNode.data;
		   
			/**
			 * Check if the child vertex has been visited.
			 * If yes, create an edge from the parent to child vertex.
			 * Otherwise, create a new child vertex and a new directed edge
			 * from the parent to this new child vertex, and then, repeat this
			 * with the child's descendants which can be either Module or Interface.
			 */            
			Vertex parentVertex = findExistingVertex(archGraph, theParentElement.getFactid());
			
			if(parentVertex != null) {
				/**
				 * Check if a directed edge from the child vertex to its parent vertex has been created.
				 * If yes, remove the existing edge to create/add an new bi-directed edge.
				 * Otherwise, add a new directed edge from the parent to child vertex if not already existed.
				 */
				Edge reversedEdge = findExistingEdge(archGraph,childFactId,theParentElement.getFactid());
				if(reversedEdge != null){
					DesignModuledependencyrelation theInRelationExisting = (DesignModuledependencyrelation)reversedEdge.getUserDatum(RELATION_IN); 
					DesignModuledependencyrelation theOutRelationExisting = (DesignModuledependencyrelation)reversedEdge.getUserDatum(RELATION_OUT); 
					if(theInRelationExisting != null && theOutRelationExisting == null){
						archGraph.removeEdge(reversedEdge);
			            Edge newEdge = new UndirectedSparseEdge(childVertex, parentVertex);
						DesignModuledependencyrelation theRelationNew = getDependencyRelation(versionName,theParentElement.getFactid(),childFactId);				
						newEdge.addUserDatum(RELATION_OUT,theRelationNew,UserData.SHARED);	
						newEdge.addUserDatum(RELATION_IN,theInRelationExisting,UserData.SHARED);	
						archGraph.addEdge(newEdge);																
					}
				}
				else {
					Edge forwardEdge = findExistingEdge(archGraph, theParentElement.getFactid(), childFactId); 					
					if(forwardEdge == null){					
			            Edge newEdge = new DirectedSparseEdge(parentVertex,childVertex);
						DesignModuledependencyrelation theRelation = getDependencyRelation(versionName,theParentElement.getFactid(),childFactId);				
						newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
						archGraph.addEdge(newEdge);									
					}
				}
			}
			else {
				parentVertex = new SparseVertex();
				parentVertex.addUserDatum(ELEMENT,theParentElement, UserData.SHARED);
				archGraph.addVertex(parentVertex);
				
				Edge newEdge = new DirectedSparseEdge(parentVertex,childVertex);
				DesignModuledependencyrelation theRelation = getDependencyRelation(versionName,theParentElement.getFactid(),childFactId);				
				newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
				archGraph.addEdge(newEdge);
				
				
				ArrayList childModuleNodeList = getDependencyChildModuleNodes(versionName, theParentElement.getFactid());
				if(childModuleNodeList.size()!= 0)
				{
					createDependencyGraph(archGraph, versionName,theParentElement.getFactid(),parentVertex,childModuleNodeList);
				}
				
				ArrayList childInterfaceNodeList = getDependencyChildInterfaceNodes(versionName, theParentElement.getFactid());
				if(childInterfaceNodeList.size()!= 0)
				{
					createDependencyGraph(archGraph, versionName,theParentElement.getFactid(),parentVertex,childInterfaceNodeList);										
				}				
				
				ArrayList parentParentNodeList = getDependencyParentModuleNodes(versionName, theParentElement.getFactid());
				if(parentParentNodeList.size()!= 0)
				{
					createReverseDependencyGraph(archGraph, versionName,theParentElement.getFactid(),parentVertex,parentParentNodeList);
				}
				
				ArrayList parentInterfaceNodeList = getDependencyParentInterfaceNodes(versionName, theParentElement.getFactid());
				if(parentInterfaceNodeList.size()!= 0)
				{
					createReverseDependencyGraph(archGraph,versionName,theParentElement.getFactid(), parentVertex,parentInterfaceNodeList);
				}				
			}
		}
	}
	
	
	//for single step of Arche Model
/*	
	public DirectedGraph GetDependencyArcheModel(String VersionName, String ParentFactId)
	{
		DirectedGraph ArcheModel = new DirectedGraph();
		ArrayList ChildNodeList = GetDependencyChildNodes(VersionName, ParentFactId);
		Node ParentModule = GetRootNode(VersionName,ParentFactId);
				
		ArcheModel.nodes.add(ParentModule);
		Iterator ChildIt = ChildNodeList.listIterator();
		while(ChildIt.hasNext()){
			Node ChildModule = (Node)ChildIt.next();
			ArcheModel.nodes.add(ChildModule);
			ArcheModel.edges.add(new Edge(DECOMPOSITION,ParentModule,ChildModule));
			
		}
		new DirectedGraphLayout().visit(ArcheModel);
		return ArcheModel;
	}	
*/
	
	private DesignModuledependencyrelation getDependencyRelation(String VersionName, String ParentFactId, String ChildFactId)
	{
		SQLTemplate DependencyQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DependencyRelationQuery");
		Map Params = new HashMap();
		VersionName = "'" + VersionName + "'";
		ParentFactId = "'" + ParentFactId + "'";
		ChildFactId = "'" + ChildFactId + "'";
		Params.put("VersionName",VersionName);
		Params.put("ParentFactId",ParentFactId);
		Params.put("ChildFactId",ChildFactId);
		SQLTemplate newDependencyQuery = DependencyQuery.queryWithParameters(Params);
//		Iterator ChildIt= context.performQuery(newDependencyQuery).listIterator();
		List result= (List)context.performQuery(newDependencyQuery);

//		Iterator iter = result.iterator(); 
		if(result.size() == 1) {
//			DesignModuledependencyrelation dmData = (DesignModuledependencyrelation)iter.next();
			DesignModuledependencyrelation theRelation = (DesignModuledependencyrelation)result.get(0);			
			return theRelation;			
		}
		else
			return null;		
	}
	
	private ArrayList getDependencyChildModuleNodes(String VersionName, String ParentFactId)
	{
		SQLTemplate DependencyQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DependencyQueryOnModule");
		Map Params = new HashMap();
		VersionName = "'" + VersionName + "'";
		ParentFactId = "'" + ParentFactId + "'";
		Params.put("VersionName",VersionName);
		Params.put("ParentFactId",ParentFactId);
		SQLTemplate newDependencyQuery = DependencyQuery.queryWithParameters(Params);
		Iterator ChildIt= context.performQuery(newDependencyQuery).listIterator();
		ArrayList ChildList =new ArrayList();
		while(ChildIt.hasNext()){
			DesignModule dmData = (DesignModule)ChildIt.next();
			Module childModule = new Module(dmData); 		
			childModule.setFactid(dmData.getFactid()); 
			childModule.setName(dmData.getName()); 						
			childModule.setOrigin(dmData.getSource());
			childModule.setCostOfChange(dmData.getCostOfChange());
		//tempChildModule.width = m.getName().length() ;
			//The actual length of the name should suffice, however it somehow doesn't seem
			// to work at all
			Node childModuleNode = new Node();
			childModuleNode.data = childModule;
			childModuleNode.width = 136;
			ChildList.add(childModuleNode);
		}
		return ChildList;
	}

	private ArrayList getDependencyParentModuleNodes(String VersionName, String ChildFactId)
	{
		SQLTemplate DependencyQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("ReverseDependencyQueryOnModule");
		Map Params = new HashMap();
		VersionName = "'" + VersionName + "'";
		ChildFactId = "'" + ChildFactId + "'";
		Params.put("VersionName",VersionName);
		Params.put("ChildFactId",ChildFactId);
		SQLTemplate newDependencyQuery = DependencyQuery.queryWithParameters(Params);
		Iterator ParentIt= context.performQuery(newDependencyQuery).listIterator();
		ArrayList ParentList =new ArrayList();
		while(ParentIt.hasNext()){
			DesignModule dmData = (DesignModule)ParentIt.next();
			Module parentModule = new Module(dmData); 		
			parentModule.setFactid(dmData.getFactid()); 
			parentModule.setName(dmData.getName()); 						
			parentModule.setOrigin(dmData.getSource());
			parentModule.setCostOfChange(dmData.getCostOfChange());
			Node parentModuleNode = new Node();
			parentModuleNode.data = parentModule;
			parentModuleNode.width = 136;
			ParentList.add(parentModuleNode);
		}
		return ParentList;
	}
	
	
	
	private ArrayList getDependencyChildInterfaceNodes(String VersionName, String ParentFactId)
	{
		SQLTemplate InterfaceDecompostionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DependencyQueryOnInterface");
		Map Params = new HashMap();
		VersionName = "'" + VersionName + "'";
		ParentFactId = "'" + ParentFactId + "'";
		Params.put("VersionName",VersionName);
		Params.put("ParentFactId",ParentFactId);
		SQLTemplate newInterfaceDecompostionQuery = InterfaceDecompostionQuery.queryWithParameters(Params);
					
		Iterator InterfaceIt= context.performQuery(newInterfaceDecompostionQuery).listIterator();
		ArrayList InterfaceList =new ArrayList();
		while(InterfaceIt.hasNext()){
			DesignModuleinterface dmiData = (DesignModuleinterface)InterfaceIt.next();
			Node interfaceNode = new Node();
			//tempChildModule.width = m.getName().length() ;
			//The actual length of the name should suffice, however it somehow doesn't seem
			// to work at all
			
			Interface oInterface = new Interface(dmiData); 		
			interfaceNode.data = oInterface;
			oInterface.setFactid(dmiData.getFactid()); 
			oInterface.setName(dmiData.getName()); 			
			oInterface.setOrigin(dmiData.getSource());
			oInterface.setCostOfChange(dmiData.getCostOfChange());
			interfaceNode.width = 136;
			InterfaceList.add(interfaceNode);			
		}
		return InterfaceList;
	} 

	private ArrayList getDependencyParentInterfaceNodes(String VersionName, String childFactId)
	{
		SQLTemplate InterfaceDecompostionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("ReverseDependencyQueryOnInterface");
		Map Params = new HashMap();
		VersionName = "'" + VersionName + "'";
		childFactId = "'" + childFactId + "'";
		Params.put("VersionName",VersionName);
		Params.put("ChildFactId",childFactId);
		SQLTemplate newInterfaceDecompostionQuery = InterfaceDecompostionQuery.queryWithParameters(Params);
					
		Iterator InterfaceIt= context.performQuery(newInterfaceDecompostionQuery).listIterator();
		ArrayList InterfaceList =new ArrayList();
		while(InterfaceIt.hasNext()){
			DesignModuleinterface dmiData = (DesignModuleinterface)InterfaceIt.next();
			Interface oInterface = new Interface(dmiData); 		
			oInterface.setFactid(dmiData.getFactid()); 
			oInterface.setName(dmiData.getName()); 			
			oInterface.setOrigin(dmiData.getSource());
			oInterface.setCostOfChange(dmiData.getCostOfChange());
			Node interfaceNode = new Node();
			interfaceNode.data = oInterface;
			interfaceNode.width = 136;
			InterfaceList.add(interfaceNode);			
		}
		return InterfaceList;
	} 
	
	
/*	
	//for single step of Arche Model
	public DirectedGraph GetDecompositionArcheModel(String VersionName, String ParentFactId)
	{
		DirectedGraph ArcheModel = new DirectedGraph();
		ArrayList ChildNodeList = GetDecompositionChildNodes(VersionName, ParentFactId);
		Node ParentModule = GetRootNode(VersionName,ParentFactId);
				
		ArcheModel.nodes.add(ParentModule);
		Iterator ChildIt = ChildNodeList.listIterator();
		while(ChildIt.hasNext()){
			Node ChildModule = (Node)ChildIt.next();
			ArcheModel.nodes.add(ChildModule);
			ArcheModel.edges.add(new org.eclipse.draw2d.graph.Edge(DECOMPOSITION,ParentModule,ChildModule));
			
		}
		new DirectedGraphLayout().visit(ArcheModel);
		return ArcheModel;
	}
*/	
	
	
	//	for Full Decomposition Arche Model
	public SparseTree getFullDecompositionArcheModel(String versionName, String rootFactId, boolean bRootMarked)
	{
		// TODO: Need to change the variable name and not use the type of Node
		Node roottMoudleNode = getRootModuleNode(versionName,rootFactId, bRootMarked);
		if(roottMoudleNode == null)
			return null;
		
		Vertex rootModuleVertex = new SimpleDirectedSparseVertex();
		rootModuleVertex.addUserDatum(ELEMENT,roottMoudleNode.data, UserData.SHARED);
		
		SparseTree archTree = new SparseTree(rootModuleVertex);	
		
		ArrayList childInterfaceNodeList = getDecompositionChildInterfaceNodes(versionName, rootFactId);
		for(int i=0;i<childInterfaceNodeList.size();i++)
		{
			Node childInterfaceNode = (Node)childInterfaceNodeList.get(i);
			ArchElement theChildElement = (ArchElement)childInterfaceNode.data;
			
			Vertex childInterfaceVertex = new SimpleDirectedSparseVertex();
			childInterfaceVertex.addUserDatum(ELEMENT,childInterfaceNode.data, UserData.SHARED);
			archTree.addVertex(childInterfaceVertex);
			
			Edge newEdge = new DirectedSparseEdge(rootModuleVertex,childInterfaceVertex);
			DesignRealizerelation theRelation = getRealizationRelation(versionName,rootFactId,theChildElement.getFactid());				
			newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
			archTree.addEdge(newEdge);			
		}
		
		ArrayList childModuleNodeList = getDecompositionChildModuleNodes(versionName, rootFactId);
		if(childModuleNodeList.size()!= 0)
		{
			createDecompositionGraph(archTree,versionName,rootFactId, rootModuleVertex,childModuleNodeList);
		}
		
		return archTree;				
	}
	
	private void createDecompositionGraph(SparseTree archTree, String versionName,String parentFactId,Vertex parentVertex,ArrayList childNodeList)
	{
		Iterator childIt = childNodeList.listIterator();
		while(childIt.hasNext())
		{
			Node childNode = (Node)childIt.next();
			ArchElement theChildModuleElement = (ArchElement)childNode.data;
			Vertex childModuleVertex = new SimpleDirectedSparseVertex();
			childModuleVertex.addUserDatum(ELEMENT,theChildModuleElement, UserData.SHARED);
			archTree.addVertex(childModuleVertex);
			
			Edge newEdge = new DirectedSparseEdge(parentVertex,childModuleVertex);
			DesignModulerefinementrelation theRelation = getDecompositionRelation(versionName,parentFactId,theChildModuleElement.getFactid());				
			newEdge.addUserDatum(RELATION_IN,theRelation,UserData.SHARED);	
			archTree.addEdge(newEdge);
						
						
			ArrayList childInterfaceNodeList = getDecompositionChildInterfaceNodes(versionName, theChildModuleElement.getFactid());
			for(int i=0;i<childInterfaceNodeList.size();i++)
			{
				Node childInterfaceNode = (Node)childInterfaceNodeList.get(i);
				ArchElement theChildInterfaceElement = (ArchElement)childInterfaceNode.data;				
				Vertex childInterfaceVertex = new SimpleDirectedSparseVertex();
				childInterfaceVertex.addUserDatum(ELEMENT,childInterfaceNode.data, UserData.SHARED);
				archTree.addVertex(childInterfaceVertex);
				
				Edge newRealizationEdge = new DirectedSparseEdge(childModuleVertex,childInterfaceVertex);
				DesignRealizerelation theRealizationRelation = getRealizationRelation(versionName,theChildModuleElement.getFactid(),theChildInterfaceElement.getFactid());				
				newRealizationEdge.addUserDatum(RELATION_IN,theRealizationRelation,UserData.SHARED);	
				archTree.addEdge(newRealizationEdge);							
			}
			
			ArrayList childModuleNodeList = getDecompositionChildModuleNodes(versionName, theChildModuleElement.getFactid());
			if(childModuleNodeList.size()!= 0)
			{
				createDecompositionGraph(archTree, versionName,theChildModuleElement.getFactid(),childModuleVertex,childModuleNodeList);										
			}			
		}
	}

			
	private ArrayList getDecompositionChildModuleNodes(String versionName, String parentFactId)
	{
		SQLTemplate decompostionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DecompositionQueryOnModule");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		SQLTemplate newDecompostionQuery = decompostionQuery.queryWithParameters(params);
		Iterator ChildIt= context.performQuery(newDecompostionQuery).listIterator();
		
		ArrayList ChildList =new ArrayList();
		while(ChildIt.hasNext()){
			DesignModule dmData = (DesignModule)ChildIt.next();
			Node childModuleNode = new Node();
			Module childModule = new Module(dmData); 		
			childModuleNode.data = childModule;
			childModule.setFactid(dmData.getFactid()); 
			childModule.setName(dmData.getName()); 
			childModule.setOrigin(dmData.getSource());
			//tempChildModule.width = m.getName().length() ;
			//The actual length of the name should suffice, however it somehow doesn't seem
			// to work at all
			childModuleNode.width = 136;
			ChildList.add(childModuleNode);
		}
		return ChildList;
	}
	
	private ArrayList getDecompositionChildInterfaceNodes(String versionName, String parentFactId)
	{
		SQLTemplate interfaceDecompostionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DecompositionQueryOnInterface");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		SQLTemplate newInterfaceDecompostionQuery = interfaceDecompostionQuery.queryWithParameters(params);				
		Iterator InterfaceIt= context.performQuery(newInterfaceDecompostionQuery).listIterator();
		
		ArrayList InterfaceList =new ArrayList();
		while(InterfaceIt.hasNext()){
			DesignModuleinterface dmiData = (DesignModuleinterface)InterfaceIt.next();
			Node interfaceNode = new Node();
			//tempChildModule.width = m.getName().length() ;
			//The actual length of the name should suffice, however it somehow doesn't seem
			// to work at all
			
			Interface oInterface = new Interface(dmiData); 		
			interfaceNode.data = oInterface;
			oInterface.setFactid(dmiData.getFactid()); 
			oInterface.setName(dmiData.getName()); 			
			oInterface.setOrigin(dmiData.getSource());
			interfaceNode.width = 136;
			InterfaceList.add(interfaceNode);
		}
		return InterfaceList;
	} 
		
	private Node getRootModuleNode(String versionName, String parentFactId, boolean bRootMarked)
	{
		SQLTemplate rootQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("RootModuleQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		SQLTemplate newRootQuery = rootQuery.queryWithParameters(params);
		DesignModule dmData = null;
		try {
			List results = context.performQuery(newRootQuery);
			if(results.size() == 1)
				dmData = (DesignModule)context.performQuery(newRootQuery).get(0);
			else
				return null;				
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		Node parentModuleNode = new Node();
		Module parentModule = new Module(dmData); 		
		parentModuleNode.data = parentModule;
		parentModule.setFactid(dmData.getFactid()); 
		parentModule.setName(dmData.getName()); 
		parentModule.setOrigin(dmData.getSource());
		if(bRootMarked)
			parentModule.setRoot(true);		
//		ParentModule.width = ((DesignModule)ParentModule.data).getName().length();
		//The actual length of the name should suffice, however it somehow doesn't seem
		// to work at all
		parentModuleNode.width = 136;
		return parentModuleNode;
	}

	private Node getRootModuleInterfaceNode(String versionName, String parentFactId, boolean bRootMarked)
	{
		SQLTemplate rootQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("RootModuleInterfaceQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		SQLTemplate newRootQuery = rootQuery.queryWithParameters(params);
		DesignModuleinterface dmData = null;
		try {
			List results = context.performQuery(newRootQuery);
			if(results.size() == 1)
				dmData = (DesignModuleinterface)context.performQuery(newRootQuery).get(0);
			else
				return null;				
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		Node parentModuleInterfaceNode = new Node();
		Interface parentInterface = new Interface(dmData); 		
		parentModuleInterfaceNode.data = parentInterface;
		parentInterface.setFactid(dmData.getFactid()); 
		parentInterface.setName(dmData.getName()); 
		parentInterface.setOrigin(dmData.getSource());
		if(bRootMarked)
			parentInterface.setRoot(true);		
//		ParentModule.width = ((DesignModule)ParentModule.data).getName().length();
		//The actual length of the name should suffice, however it somehow doesn't seem
		// to work at all
		parentModuleInterfaceNode.width = 136;
		return parentModuleInterfaceNode;
	}
	
	private DesignRealizerelation getRealizationRelation(String versionName, String parentFactId, String childFactId)
	{
		SQLTemplate realizationQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("RealizationRelationQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		String childFactIdParam = "'" + childFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		params.put("ChildFactId",childFactIdParam);
		SQLTemplate newRealizationQuery = realizationQuery.queryWithParameters(params);
		List result= (List)context.performQuery(newRealizationQuery);

		if(result.size() == 1) {
			DesignRealizerelation theRelation = (DesignRealizerelation)result.get(0);			
			return theRelation;			
		}
		else
			return null;		
	}
		
	private DesignModulerefinementrelation getDecompositionRelation(String versionName, String parentFactId, String childFactId)
	{
		SQLTemplate decompositionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("DecompositionRelationQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		String parentFactIdParam = "'" + parentFactId + "'";
		String childFactIdParam = "'" + childFactId + "'";
		params.put("VersionName",versionNameParam);
		params.put("ParentFactId",parentFactIdParam);
		params.put("ChildFactId",childFactIdParam);
		SQLTemplate newDecompositionQuery = decompositionQuery.queryWithParameters(params);
		List result= (List)context.performQuery(newDecompositionQuery);

		if(result.size() == 1) {
			DesignModulerefinementrelation theRelation = (DesignModulerefinementrelation)result.get(0);			
			return theRelation;			
		}
		else
			return null;		
	}
	


	public SparseGraph getFullConcurrencyArcheModel(String versionName, String rootFactId) {
		// TODO: [Extension Point] A initial graph describing Concurrency view will be created here
		return null;
	}

	public int getVersionID(String versionName) {
		SQLTemplate rootQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("VersionIDQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionName + "'";
		params.put("VersionName",versionNameParam);
		SQLTemplate newRootQuery = rootQuery.queryWithParameters(params);
		Versions versionData = null;
		try {
			List results = context.performQuery(newRootQuery);
			if(results.size() == 1){
				versionData = (Versions)context.performQuery(newRootQuery).get(0);
				return versionData.getID().intValue();
			}
			else
				return -1;				
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}		
	}
	
}
