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

import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;

/**
 * @author Andres Diaz-Pace
 */

public class UndoManager {
	
	private static final int AllowedUndoLevels = 2;

	private static UndoManager soleInstance = null;
	
	public static UndoManager getInstance() {
		 if (soleInstance == null)
			 soleInstance = new UndoManager();
		 return (soleInstance);
	}
	
	private int undoMySQLVersion;
	private Stack<ArchitectureMemento> undoStack = null;
//	public boolean canUndo = false;
	
	public UndoManager() {
		undoStack = new Stack<ArchitectureMemento>();
		undoMySQLVersion = 0;
		System.setProperty("CanUndoArchitecture", "false");
//		canUndo = false;
	}
	
	public String getUndoMySQLVersion() {
		String currentVersion = "undo" + undoMySQLVersion +".sql";
//		undoMySQLVersion++;
		return (currentVersion);
	}
	
	public boolean addMemento(ArchitectureMemento op) {
		if (undoStack.size() < AllowedUndoLevels) {
//			System.out.println(">>>>>>>>>>>>>>>>>>>>SAVING MEMENTO: "+op.getName());
		}
		else { // It discards the element at the base of the stack
			ArchitectureMemento last = undoStack.elementAt(0);
			last.dispose();
			undoStack.removeElementAt(0);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>SAVING MEMENTO (with remove): "+op.getName());
		}

		undoStack.push(op);
		undoMySQLVersion++;
		
		this.updateUndoStatus();
		
		return (op.save());
		
	}
	
	protected void updateUndoStatus() {
		if (undoStack.size() == 0)
			System.setProperty("CanUndoArchitecture", "false");
		else
			System.setProperty("CanUndoArchitecture", "true");
		return;
	}
	
	public ArchitectureMemento undo() {
		boolean ok = false;
		ArchitectureMemento op = null;
		if (!undoStack.isEmpty()) {
			op = undoStack.pop();
			ok = op.restore();
//			System.out.println(">>>>>>>>>>>>>>>>>>>>RESTORING MEMENTO: "+op.getName());
			op.dispose();
		}

		this.updateUndoStatus();

		if (ok)
			return (op);
		else
			return (null);
	}

	public ArchitectureMemento createApplyTacticMemento(ArchEFacade facade, String aSQLFilePath) {
		
		MySQLFileMemento memento = new MySQLFileMemento(facade, "Before Applying Tactic");
		memento.setSQLFilePath(aSQLFilePath);     
		
		boolean ok = this.addMemento(memento);
		
		if (ok)
			return (memento);
		else
			return (null);
	}

	public ArchitectureMemento createApplyTacticMemento(ArchEFacade facade, IProject projectHandle) {

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
    		
    	    memento = this.createApplyTacticMemento(facade, sqlFilePath);
    		
            }
        }		
		
		return (memento);
	}

	public ArchitectureMemento createUserChangesMemento(ArchEFacade facade, String aSQLFilePath) {
		
		MySQLFileMemento memento = new MySQLFileMemento(facade, "After User Changes");
		memento.setSQLFilePath(aSQLFilePath);     
		
		boolean ok = this.addMemento(memento);
		
		if (ok)
			return (memento);
		else
			return (null);
	}
	
	public ArchitectureMemento createUserChangesMemento(ArchEFacade facade, IProject projectHandle) {

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
    		
    	    memento = this.createUserChangesMemento(facade, sqlFilePath);
    		
            }
        }		
		
		return (memento);
	}

}
