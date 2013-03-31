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

package edu.cmu.sei.arche.undo;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;

/**
 * @author Andres Diaz-Pace
 */

public class HistoryManager {
	
	private static HistoryManager soleInstance = null;
	
	public static HistoryManager getInstance() {
		if (soleInstance == null)
			soleInstance = new HistoryManager();
		return (soleInstance);
	}
	
	private int currentMySQLVersion;
	private DirectedGraph<ArchitectureMemento, DefaultEdge> historyGraph = null;
	private ArchitectureMemento currentSnapshot = null;
	
	public HistoryManager() {
		historyGraph = new DefaultDirectedGraph<ArchitectureMemento, DefaultEdge>(DefaultEdge.class);
		currentMySQLVersion = 0;
		currentSnapshot = null;
	}
	
	public void setInitialSnapshot(ArchitectureMemento memento) {
		currentSnapshot = memento;		
		historyGraph.addVertex(currentSnapshot);
		currentMySQLVersion = 0;
		
		return;
	}
	
	public String getUndoMySQLVersion() {
		String currentVersion = "snapshot" + currentMySQLVersion +".sql";
//		currentMySQLVersion++;
		return (currentVersion);
	}
	
	public boolean addMemento(ArchitectureMemento op) {
		if (currentSnapshot == null) {
			historyGraph.addVertex(op);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>SAVING MEMENTO (First version): "+op.getName());
		}
		else {
			historyGraph.addVertex(op);
			historyGraph.addEdge(currentSnapshot, op);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>SAVING MEMENTO: "+op.getName());
		}
		currentSnapshot = op;
		currentMySQLVersion++;

		return (op.save());
	}
	
	public ArchitectureMemento goBack() {
		
		ArchitectureMemento previousSnapshot = null;
		if (historyGraph.inDegreeOf(currentSnapshot) == 1) {
			DefaultEdge edge = historyGraph.incomingEdgesOf(currentSnapshot).iterator().next();
			previousSnapshot = historyGraph.getEdgeSource(edge);
			currentSnapshot = previousSnapshot;
		}		
		
		return (previousSnapshot);
	}
	
	public ArchitectureMemento createSnapshotMemento(ArchEFacade facade, String aSQLFilePath) {
		
		MySQLFileMemento memento = new MySQLFileMemento(facade, "Architecture Snapshot");
		memento.setSQLFilePath(aSQLFilePath);     
		
		boolean ok = this.addMemento(memento);
		
		if (ok)
			return (memento);
		else
			return (null);
	}

	public ArchitectureMemento createSnapshotMemento(ArchEFacade facade, IProject projectHandle) {

		ArchitectureMemento memento = null;
		
        if (projectHandle.exists()) {
    		IFolder subdir = projectHandle.getFolder("design");
            if (subdir.exists()) {
            	IFile file = subdir.getFile(this.getUndoMySQLVersion());
            	String temp  = file.getLocation().toFile().getAbsolutePath();
    	    	int first = 0;
    	    	int end = temp.indexOf("\\", first);
    	    	String sqlFilePath = "/" + temp.substring(first, end);

    	    	while(true){
    		    	 first = end + 1;
    		    	 end = temp.indexOf("\\", first);
    		    	 if(end == -1){
    		    		 sqlFilePath = sqlFilePath + "/" + temp.substring(first);			    		 
    		    		 break;
    		    	 }
    		    	 sqlFilePath = sqlFilePath + "/" + temp.substring(first, end);
    	    	}
    		
    	    memento = this.createSnapshotMemento(facade, sqlFilePath);
    		
            }
        }		
		
		return (memento);
	}

}
