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

package edu.cmu.sei.arche.actions;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.export.ExportToSQL;
import edu.cmu.sei.arche.export.ImportFromSQL;
import edu.cmu.sei.arche.undo.ArchitectureMemento;
import edu.cmu.sei.arche.undo.HistoryManager;
import edu.cmu.sei.arche.undo.UndoManager;
import edu.cmu.sei.arche.vo.ProjectVO;

/**
 * Action handler for undoing the last applied tactic.
 * 
 * @author Hyunwoo Kim
 */
public class UndoLastTactic implements IObjectActionDelegate,
		IWorkbenchWindowActionDelegate {

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) {
        IProject projectHandle = ArchEUIPlugin.getDefault().getOpenProject();
        if(projectHandle != null){
//            IFolder subdir = projectHandle.getFolder("design");
//            if (subdir.exists()) {
//            	String temp  = subdir.getLocation().toFile().getAbsolutePath();
//            	temp = temp + "\\undo.sql";
//    	    	int first = 0;
//    	    	int end = temp.indexOf("\\", first);
//    	    	String sqlFilePath = "/" + temp.substring(first, end);
//
//    	    	while(true){
//    		    	 first = end + 1;
//    		    	 end = temp.indexOf("\\", first);
//    		    	 if(end == -1){
//    		    		 sqlFilePath = sqlFilePath + "/" + temp.substring(first);			    		 
//    		    		 break;
//    		    	 }
//    		    	 sqlFilePath = sqlFilePath + "/" + temp.substring(first, end);
//    	    	}
//    	    	
//    	    	File f = new File(sqlFilePath);
//    	    	if(f.exists()){
                    //ImportFromSQL importFromSQL = new ImportFromSQL();
        	    	//importFromSQL.importFrom("Architecture1",sqlFilePath,"Design","temp");
    	    		ArchitectureMemento memento = UndoManager.getInstance().undo();
    	    		HistoryManager.getInstance().goBack();
        	    	
//                    ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();                    
//                    facade.restoreFromDB();
                    ArchEUIPlugin.getDefault().refreshViews();
//    	    	}        	    	
//            }
        }           
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * @param view communicates takes the view from where the action was invoked as the param
     * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
     */
    public void init(IViewPart view) {
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose() {
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init(IWorkbenchWindow window) {
    }

}
