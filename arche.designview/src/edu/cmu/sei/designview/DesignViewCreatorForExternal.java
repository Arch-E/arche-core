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

package edu.cmu.sei.designview;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import edu.cmu.sei.ORM.main.ArcheModel;
import edu.cmu.sei.designview.layouts.DecompositionViewLayout;
import edu.cmu.sei.designview.layouts.DependencyViewLayout;
import edu.cmu.sei.designview.layouts.ViewLayout;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseTree;

	
/**
 * This is the class that allows software components, which are external to this cmu.sei.archeGui plugin,
 * to create graphical views according to the given version_name(project_name) and fact_id(module).
 * 
 * <p>Example of using this class:
 * <pre>
 *	// get the static instance of this class 
 *	AViewCreatorForExternal creator = AViewCreatorForExternal.getDefault();
 * 
 *	// get it configured with input parameters
 *	creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId);
 *
 *	// get it to create a graphical view and return the view in a file
 *	IFile gViewCached = creator.createGraphicalView(currentProject);			
 *
 *	// open the view file in editor
 * 	editor = IDE.openEditor(page, gViewCached, true);
 * </pre>
 * </p>

 * @author Hyunwoo Kim
 * @version $Revision: 1.1.2.1 $
 */

public class DesignViewCreatorForExternal {

	//The shared instance.
	private static DesignViewCreatorForExternal creator;

	// Version check
	private static Hashtable versions;
    // cache of newly-created graphical view
    private IFile fileCache;
    
	public static final String DEFAULT_EDITOR_TITLE = "Design View";
	public static final String DEFAULT_FILE_CACHE = DesignViewEditor.DEFAULT_EXTENSION;
	private static final String CRAPHICAL_VIEW_CREATION_ERROR_TITLE = "Graphical Design View Creation Error";
	private static final String CRAPHICAL_VIEW_CREATION_PROGRESS = "Graphical Design View Creating...";
		
	private String versionName;
	private int versionID;
	private String rootFactId;
	private int rootFactType;
	private boolean bRootMarked;
	private String selectedViewType;
	private String selectedLayoutType;
	private ArcheModel model = ArcheModel.GetInstance();

	/**
	 * The configuration before the creation of a graph.
	 */
	public int configure(String viewType, String selectedLayoutType, String versionName, String rootFactId, boolean bRootMarked, int rootFactType){
		this.selectedViewType = viewType;
		this.versionName = versionName;		
		this.rootFactId = rootFactId;	
		this.bRootMarked  = bRootMarked;
		this.rootFactType = rootFactType; // Module or ModuleInterface
		this.selectedLayoutType = selectedLayoutType;
		versionID = ArcheModel.GetInstance().getVersionID(versionName);
		return versionID;
	}
	
	public DesignViewCreatorForExternal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the shared instance.
	 */
	public static DesignViewCreatorForExternal getDefault() {
		if(creator == null){
			creator = new DesignViewCreatorForExternal();
			versions = new Hashtable();
		}
		return creator;
	}	   
    
    /**
     * Creates a grachpical view from design elements produced by ArchE,
     * saves it in the file cache, and then, open it in the editor.
     * 
     * @param projectPath: a path relative to the current workspace, describing the currently opened project
     * 					   e.g., /project_sample 
     * @return a file caching a graphical view derived from design elements
     */    
    public IFile createGraphicalView(IProject project) {
    	if(versionName == null || versionName.equals(""))
    		throw new NullPointerException("AViewCreatorForExternal Design Version Name: The name cannot be null");

    	if(rootFactId == null || rootFactId.equals(""))
    		throw new NullPointerException("AViewCreatorForExternal Design Module FactID: The factid cannot be null");

    	if(selectedViewType == null || selectedViewType.equals(""))
    		throw new NullPointerException("AViewCreatorForExternal Design View Type: The view type cannot be null");

    	if(selectedLayoutType == null || selectedLayoutType.equals(""))
    		throw new NullPointerException("AViewCreatorForExternal Design Layout Type: The layout type cannot be null");   	

    	if(project == null)
    		throw new NullPointerException("AViewCreatorForExternal Project: The project cannot be null");   	
    	
		// if the cache file exists,
    	// replace its contents with a new graphical view derived from ArchE design elements.   	
//        if (fileCache != null && fileCache.getProject().getName().equals(project.getName())  ){        	
//        	if(!replaceGraphicalViewInExistingCache())
//        		return null;
//        }    
//        else {

        	IPath newFilePath = project.getFullPath().append(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE);
        	if(!createCacheWithGraphicalView(newFilePath))
        		return null;               			
//        }    	
    	       
        return fileCache;
    }
	
	
	protected boolean createCacheWithGraphicalView(IPath newFilePath) {
        final IFile newFileHandle = createFileHandle(newFilePath);
        final InputStream initialContents = getInitialContents();

        WorkspaceModifyOperation opCreate = new WorkspaceModifyOperation(createRule(newFileHandle)) {
            protected void execute(IProgressMonitor monitor)
                    throws CoreException {
                try {
                    monitor.beginTask(CRAPHICAL_VIEW_CREATION_PROGRESS, 2000);
                    createCache(newFileHandle, initialContents,
                            new SubProgressMonitor(monitor, 1000));
                } finally {
                    monitor.done();
                }
            }
        };
    	
        try {
        	DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().run(false, true, opCreate);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog
                        .openError(
                        		DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), // Was Utilities.getFocusShell()
                        		CRAPHICAL_VIEW_CREATION_ERROR_TITLE,
                                null, // no special message
                                ((CoreException) e.getTargetException())
                                        .getStatus());
            } else {
                // CoreExceptions are handled above, but unexpected runtime exceptions and errors may still occur.
            	DesignViewPlugin.log(getClass(),
                        "createNewFile()", e.getTargetException());
                MessageDialog
                        .openError(
                        		DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
                        		CRAPHICAL_VIEW_CREATION_ERROR_TITLE, NLS.bind("Internal error: {0}", e.getTargetException().getMessage()));
            }
            return false;
        }        	
        fileCache = newFileHandle;		
        return true;
	}

	protected boolean replaceGraphicalViewInExistingCache() {
        final InputStream initialContents = getInitialContents();

        WorkspaceModifyOperation opReplace = new WorkspaceModifyOperation(createRule(fileCache)) {
            protected void execute(IProgressMonitor monitor)
                    throws CoreException {
                try {
                    monitor.beginTask(CRAPHICAL_VIEW_CREATION_PROGRESS, 2000);
                    replaceCache(fileCache, initialContents,
                            new SubProgressMonitor(monitor, 1000));
                } finally {
                    monitor.done();
                }
            }
        };
        
        try {
        	DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().run(false, true, opReplace);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog
                        .openError(
                        		DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), 
                        		CRAPHICAL_VIEW_CREATION_ERROR_TITLE,
                                null, // no special message
                                ((CoreException) e.getTargetException())
                                        .getStatus());
            } else {
                // CoreExceptions are handled above, but unexpected runtime exceptions and errors may still occur.
            	DesignViewPlugin.log(getClass(),
                        "createGraphicalView()", e.getTargetException()); 
                MessageDialog
                        .openError(
                        		DesignViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
                        		CRAPHICAL_VIEW_CREATION_ERROR_TITLE, NLS.bind("Internal error: {0}", e.getTargetException().getMessage()));
            }
            return false;
        }        	    
        return true;
	}

    protected void createCache(IFile fileHandle, InputStream contents,
            IProgressMonitor monitor) throws CoreException {

    	if (contents == null)
            contents = new ByteArrayInputStream(new byte[0]);

        try {
            // Create a new file resource in the workspace
            IPath path = fileHandle.getFullPath();
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            int numSegments= path.segmentCount();
            if (numSegments > 2 && !root.getFolder(path.removeLastSegments(1)).exists()) {
                // If the direct parent of the path doesn't exist, try to create the
                // necessary directories.
                for (int i= numSegments - 2; i > 0; i--) {
                    IFolder folder = root.getFolder(path.removeLastSegments(i));
                    if (!folder.exists()) {
                        folder.create(false, true, monitor);
                    }
                }
            }
            
            fileHandle.create(contents, true, monitor);
        } catch (CoreException e) {
            // If the file already existed locally, just change its contents
            if (e.getStatus().getCode() == IResourceStatus.PATH_OCCUPIED)
            	fileHandle.setContents(contents, true, true, monitor);
            else
                throw e;
        }

        if (monitor.isCanceled())
            throw new OperationCanceledException();
    }	
    
    protected void replaceCache(IFile fileHandle, InputStream contents,
    			IProgressMonitor monitor) throws CoreException {

    	if (contents == null)
            contents = new ByteArrayInputStream(new byte[0]);

        try {
            if (fileHandle.exists())
            	fileHandle.setContents(contents, true, true, monitor);
        } catch (CoreException e) {
            throw e;
        }

        if (monitor.isCanceled())
            throw new OperationCanceledException();		
	}    

	/** Return a new AViewDiagram instance. */
	protected Object createCraphicalViewInternal() {
		DesignViewDiagram aviewDiagram =  null;
			
		if (selectedViewType.equalsIgnoreCase(ViewLayout.DECOMPOSITION_VIEW)){
//			System.out.println(System.currentTimeMillis());
			
			SparseTree archTree = null;
//			archTree = ArcheModel.GetInstance().getFullDecompositionArcheModel("Architecture1", rootFactId, bRootMarked); 			
			archTree = ArcheModel.GetInstance().getFullDecompositionArcheModel(versionName, rootFactId, bRootMarked); 			
			if(archTree == null){
//				archTree = ArcheModel.GetInstance().getFullDecompositionArcheModel(versionName, rootFactId, bRootMarked); 							
//				if(archTree == null){
//					System.out.println("Architecture might not exist in the ArchE database");
					return null;
//				}
			}
			
//			System.out.println(System.currentTimeMillis());
	
			DecompositionViewLayout	layout = new DecompositionViewLayout();
			aviewDiagram = layout.generateTreeLayout(archTree);
		}
		else if (selectedViewType.equalsIgnoreCase(ViewLayout.DEPENDENCY_VIEW)){
//			System.out.println(System.currentTimeMillis());
			SparseGraph archGraph = null;
//			archGraph = model.getFullDependencyArcheModel("Architecture1", rootFactId, bRootMarked, rootFactType); 	
			archGraph = model.getFullDependencyArcheModel(versionName, rootFactId, bRootMarked, rootFactType); 	
			if(archGraph == null){
//				archGraph = model.getFullDependencyArcheModel(versionName, rootFactId, bRootMarked, rootFactType); 	
//				if(archGraph == null){
//					System.out.println("Architecture might not exist in the ArchE database");
					return null;
//				}
			}
//			System.out.println(System.currentTimeMillis());
						
			DependencyViewLayout	layout = new DependencyViewLayout();
			if(selectedLayoutType.equals(DependencyViewLayout.FR_LAYOUT)) {	
				aviewDiagram = layout.generateFRLayout(archGraph);
			}
			else if (selectedLayoutType.equals(DependencyViewLayout.KK_LAYOUT)) {
				aviewDiagram = layout.generateKKLayout(archGraph);
			}
			else if (selectedLayoutType.equals(DependencyViewLayout.CIRCLE_LAYOUT)) {
				aviewDiagram = layout.generateCircleLayout(archGraph);				
			}
		}		
		else if (selectedViewType.equalsIgnoreCase(ViewLayout.CONCURRENCY_VIEW)){
			// TODO: [Extension Point] for concurrency view 
		}
		// TODO: [Extension Point] for additional views 
		
		if(aviewDiagram != null){
			aviewDiagram.setVersionName(versionName);
			aviewDiagram.setVersionID(versionID);
			aviewDiagram.setRootNodeFactId(rootFactId);
			aviewDiagram.setRootNodeFactType(rootFactType);
			aviewDiagram.setSelectedViewType(selectedViewType);
			aviewDiagram.setSelectedLayoutType(selectedLayoutType);			
		}
		
		return aviewDiagram;
	}		
	
	protected InputStream getInitialContents() {
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(createCraphicalViewInternal()); // argument must be Serializable
			oos.flush();
			oos.close();
			bais = new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return bais;
	}
	
    protected IFile createFileHandle(IPath filePath) {    	
        return ResourcesPlugin.getWorkspace().getRoot().getFile(
                filePath);
    }

    protected ISchedulingRule createRule(IResource resource) {
		IResource parent = resource.getParent();
    	while (parent != null) {
    		if (parent.exists())
    			return resource.getWorkspace().getRuleFactory().createRule(resource);
    		resource = parent;
    		parent = parent.getParent();
    	}
		return resource.getWorkspace().getRoot();
	}
         
}
