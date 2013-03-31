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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import edu.cmu.sei.ORM.main.ArcheModel;
import edu.cmu.sei.ORM.model.DesignModuledependencyrelation;
import edu.cmu.sei.ORM.model.DesignModulerefinementrelation;
import edu.cmu.sei.ORM.model.DesignRealizerelation;
import edu.cmu.sei.designview.editparts.ArchElementEditPart;
import edu.cmu.sei.designview.editparts.DesignViewEditPartFactory;
import edu.cmu.sei.designview.editparts.DesignViewTreeEditPartFactory;
import edu.cmu.sei.designview.layouts.DecompositionViewLayout;
import edu.cmu.sei.designview.layouts.DependencyViewLayout;
import edu.cmu.sei.designview.layouts.ViewLayout;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.ArchRelation;
import edu.cmu.sei.designview.model.Decomposition;
import edu.cmu.sei.designview.model.Dependency;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.Realization;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseTree;
import edu.uci.ics.jung.graph.impl.UndirectedSparseEdge;

/**
 * A graphical editor that can edit .aview files.
 * The binding between the .aview file extension and this editor is done in plugin.xml
 * 
 * @author Hyunwoo Kim
 * @version $Revision: 1.1.2.1 $
 */
public class DesignViewEditor extends GraphicalEditor
{
	/** default file extension corresponding to this editor */
	public static final String DEFAULT_EXTENSION = ".view";
	
	/** This is the root of the editor's model. */
	private DesignViewDiagram diagram;

	private boolean editorSaving = false;
	private DesignViewOutlinePage outlinePage;
	private IExternalSelect external;
	
	/** Create a new AViewEditor instance. This is called by the Workspace. */
	public DesignViewEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
	}

	private RulerComposite rulerComp;
	
	protected Control getGraphicalControl() {
		return rulerComp;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createGraphicalViewer(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGraphicalViewer(Composite parent) {
		rulerComp = new RulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComp);
		rulerComp.setGraphicalViewer((ScrollingGraphicalViewer)getGraphicalViewer());
	}
	
	/**
	 * Configure the graphical viewer before it receives contents.
	 * <p>This is the place to choose an appropriate RootEditPart and EditPartFactory
	 * for your editor. The RootEditPart determines the behavior of the editor's "work-area".
	 * For example, GEF includes zoomable and scrollable root edit parts. The EditPartFactory
	 * maps model elements to edit parts (controllers).</p>
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new DesignViewEditPartFactory());
		
		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
		List zoomLevels = new ArrayList(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		root.getZoomManager().setZoomLevelContributions(zoomLevels);

		IAction zoomIn = new ZoomInAction(root.getZoomManager());
		IAction zoomOut = new ZoomOutAction(root.getZoomManager());
		getActionRegistry().registerAction(zoomIn);
		getActionRegistry().registerAction(zoomOut);
		getSite().getKeyBindingService().registerAction(zoomIn);
		getSite().getKeyBindingService().registerAction(zoomOut);

		viewer.setRootEditPart(root);		
//		viewer.setRootEditPart(new ScalableFreeformRootEditPart());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
	
		// configure the context menu provider
		ContextMenuProvider cmProvider =
				new DesignViewEditorContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);
		
		loadProperties();
		
		// Actions
		IAction showRulers = new ToggleRulerVisibilityAction(getGraphicalViewer());
		getActionRegistry().registerAction(showRulers);
		
		IAction snapAction = new ToggleSnapToGeometryAction(getGraphicalViewer());
		getActionRegistry().registerAction(snapAction);

		IAction showGrid = new ToggleGridAction(getGraphicalViewer());
		getActionRegistry().registerAction(showGrid);
		
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				handleActivationChanged(event);
			}
		};
		getGraphicalControl().addListener(SWT.Activate, listener);
		getGraphicalControl().addListener(SWT.Deactivate, listener);		
				
	}
	
	public void setSelectionSynchronizerExternal(IExternalSelect external) {
		this.external = external;
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		super.selectionChanged(part, selection);
		// If not the active editor, ignore selecti;on changed.
		if (this.equals(getSite().getPage().getActiveEditor())){
			if(((IStructuredSelection) selection).getFirstElement() instanceof ArchElementEditPart){
				ArchElementEditPart editPart = (ArchElementEditPart)((IStructuredSelection) selection).getFirstElement();							
				ArchElement data = (ArchElement)editPart.getModel();
				if(external!=null)
					external.select(
							data.getName());
			}			
		}
	}

	
	protected void handleActivationChanged(Event event) {
		IAction copy = null;
		if (event.type == SWT.Deactivate)
			copy = getActionRegistry().getAction(ActionFactory.COPY.getId());
		if (getEditorSite().getActionBars().getGlobalActionHandler(ActionFactory.COPY.getId()) 
				!= copy) {
			getEditorSite().getActionBars().setGlobalActionHandler(
					ActionFactory.COPY.getId(), copy);
			getEditorSite().getActionBars().updateActionBars();
		}
	}	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}
	
	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(getModel());
		oos.close();
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		try {
			editorSaving = true;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			saveProperties();
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(
				new ByteArrayInputStream(out.toByteArray()), 
				true,  // keep saving, even if IFile is out of sync with the Workspace
				false, // dont keep history
				monitor); // progress monitor
			getCommandStack().markSaveLocation();
		} catch (CoreException ce) { 
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			editorSaving = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave() {
		try {
			editorSaving = true;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(
				new ByteArrayInputStream(out.toByteArray()), 
				true,  // keep saving, even if IFile is out of sync with the Workspace
				false, // dont keep history
				null); // progress monitor
			getCommandStack().markSaveLocation();
		} catch (CoreException ce) { 
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			editorSaving = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
		// Show a SaveAs dialog
		Shell shell = getSite().getWorkbenchWindow().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();
		
		IPath path = dialog.getResult();	
		if (path != null) {
			// try to save the editor's contents under a different file name
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			try {
				new ProgressMonitorDialog(shell).run(
						false, // don't fork
						false, // not cancelable
						new WorkspaceModifyOperation() { // run this operation
							public void execute(final IProgressMonitor monitor) {
								saveProperties();
								try {
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									createOutputStream(out);
									file.create(
										new ByteArrayInputStream(out.toByteArray()), // contents
										true, // keep saving, even if IFile is out of sync with the Workspace
										monitor); // progress monitor
								} catch (CoreException ce) {
									ce.printStackTrace();
								} catch (IOException ioe) {
									ioe.printStackTrace();
								} 
							}
						});
				// set input to the new file
				setInput(new FileEditorInput(file));
				getCommandStack().markSaveLocation();
			} catch (InterruptedException ie) {
	  			// should not happen, since the monitor dialog is not cancelable
				ie.printStackTrace(); 
			} catch (InvocationTargetException ite) { 
				ite.printStackTrace(); 
			}
		}
	}
	
	public Object getAdapter(Class type) {
//		if (type == IContentOutlinePage.class) {
//			outlinePage = new DesignViewOutlinePage(new TreeViewer());
//			return outlinePage;
//		}		
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());

		return super.getAdapter(type);
	}
	
	
	public DesignViewDiagram getModel() {
		return diagram;
	}
	
	
	private void handleLoadException(Exception e) {
		System.err.println("** Load failed. Using default model. **");
		e.printStackTrace();
		diagram = new DesignViewDiagram();
	}
	
	/**
	 * Set up the editor's inital content (after creation).
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getModel()); // set the contents of this editor
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	
	/* (non-Javadoc)
	 * Any exception means two graphs don't equal. 
	 */
	private boolean equalTwoGraphs(SparseGraph archGraph, Map mapFactIdToArchElement){
		if(archGraph == null || mapFactIdToArchElement == null)
			throw new NullPointerException();			
		
		int numMatchedElement = 0;
		
		Map mapTargetFactIdToOutArchRelation = new HashMap();
		Map mapSourceFactIdToInArchRelation = new HashMap();

		for (Iterator vIt = archGraph.getVertices().iterator(); vIt.hasNext(); )
		{					
			Vertex sourceVertex = (Vertex)vIt.next();
			if(sourceVertex == null)
				throw new NullPointerException();
			ArchElement sourceElement = (ArchElement)sourceVertex.getUserDatum(ArcheModel.ELEMENT);
			if(sourceElement == null)
				throw new NullPointerException();			
			ArchElement mappedElement = (ArchElement)mapFactIdToArchElement.get(sourceElement.getFactid());
			if(mappedElement == null)
				return false;				
			numMatchedElement++;
			Set incidentEdgeList = sourceVertex.getIncidentEdges(); 
			List outRelationlist = mappedElement.getSourceConnections();
			List inRelationlist = mappedElement.getTargetConnections();
			if(incidentEdgeList == null || outRelationlist == null || inRelationlist == null)
				throw new NullPointerException();			
			
			// Compare the outgoing edge numbers
			if(incidentEdgeList.size() != (outRelationlist.size() + inRelationlist.size()))
				return false;
			
			// Create hashmaps for key search 
			mapTargetFactIdToOutArchRelation.clear();
			mapSourceFactIdToInArchRelation.clear();
			for (Iterator rIt = outRelationlist.iterator(); rIt.hasNext(); )
			{					
				ArchRelation relation = (ArchRelation)rIt.next();
				if(relation == null)
					throw new NullPointerException();			
				mapTargetFactIdToOutArchRelation.put(relation.getTarget().getFactid(),relation);
			}				
			for (Iterator rIt = inRelationlist.iterator(); rIt.hasNext(); )
			{					
				ArchRelation relation = (ArchRelation)rIt.next();
				if(relation == null)
					throw new NullPointerException();			
				mapSourceFactIdToInArchRelation.put(relation.getSource().getFactid(),relation);
			}				
			
			int numMatchedRelation = 0;
			for (Iterator eIt = incidentEdgeList.iterator(); eIt.hasNext(); )
			{		
				Edge e = (Edge)eIt.next();
				if(e == null)
					throw new NullPointerException();			
				Vertex targetVertex = (Vertex)e.getOpposite(sourceVertex);
				if(targetVertex == null)
					throw new NullPointerException();								
				ArchElement targetElement = (ArchElement)targetVertex.getUserDatum(ArcheModel.ELEMENT);
				if(targetElement == null)
					throw new NullPointerException();								

				ArchElement child, parent;
				ArchRelation mappedRelation=null;
				mappedRelation = (ArchRelation) mapTargetFactIdToOutArchRelation.get(targetElement.getFactid());					
				if(mappedRelation == null){
					mappedRelation = (ArchRelation) mapSourceFactIdToInArchRelation.get(targetElement.getFactid());					
					if(mappedRelation == null)
						return false;
					parent = targetElement;
					child = sourceElement;					
				}
				else {
					parent = sourceElement;
					child = targetElement;										
				}
					
				numMatchedRelation++;	
				
				// Thoroughly, validate the properties of the relation according to its type
				if(mappedRelation instanceof Realization){						
					DesignRealizerelation relationData = (DesignRealizerelation)e.getUserDatum(ArcheModel.RELATION_IN);											
					if(!mappedRelation.getFactid().equals(relationData.getFactid()))
						return false;	
					if(!mappedRelation.getParent().equals(parent.getName()))
						return false;	
					if(!mappedRelation.getChild().equals(child.getName()))
						return false;							
				}
				else if(mappedRelation instanceof Decomposition){
					DesignModulerefinementrelation relationData = (DesignModulerefinementrelation)e.getUserDatum(ArcheModel.RELATION_IN);					
					if(!mappedRelation.getFactid().equals(relationData.getFactid()))
						return false;	
					if(!mappedRelation.getParent().equals(parent.getName()))
						return false;	
					if(!mappedRelation.getChild().equals(child.getName()))
						return false;												
				}
				else if(mappedRelation instanceof Dependency){						
					if(e instanceof DirectedSparseEdge){			
						DesignModuledependencyrelation relationData = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_IN);					
						if(mappedRelation.isBidirected() == true)
							return false;
						if(!mappedRelation.getFactid().equals(relationData.getFactid()))
							return false;	
						if(!mappedRelation.getParent().equals(parent.getName()))
							return false;	
						if(!mappedRelation.getChild().equals(child.getName()))
							return false;												
						if(((Dependency)mappedRelation).getIncoming() != relationData.getProbability().doubleValue())  // Might cause an error since it is a comparison on double numbers.
							return false;																			
					}
					else if (e instanceof UndirectedSparseEdge){
						DesignModuledependencyrelation relationDataIn = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_IN);					
						DesignModuledependencyrelation relationDataOut = (DesignModuledependencyrelation)e.getUserDatum(ArcheModel.RELATION_OUT);					
						String pairFactid = relationDataIn.getFactid() + "," + relationDataOut.getFactid();
						
						if(mappedRelation.isBidirected() == false)
							return false;
						if(!mappedRelation.getFactid().equals(pairFactid))
							return false;	
						if(!mappedRelation.getParent().equals(parent.getName()))
							return false;	
						if(!mappedRelation.getChild().equals(child.getName()))
							return false;												
						if(((Dependency)mappedRelation).getIncoming() != relationDataIn.getProbability().doubleValue())  // Might cause an error since it is a comparison on double numbers.
							return false;																			
						if(((Dependency)mappedRelation).getOutgoing() != relationDataOut.getProbability().doubleValue())  // Might cause an error since it is a comparison on double numbers.
							return false;																			
					}
					else
						throw new IllegalArgumentException("unexpected edge type");
				}
				else 
					throw new IllegalArgumentException("unexpected relation");
			}
			
			if(incidentEdgeList.size() != numMatchedRelation)
				return false;
		}
		if(archGraph.getVertices().size() != numMatchedElement)
			return false;
		
		return true;
	}
	
	protected boolean confirmLocalViewUpdate(){		
		return MessageDialog.openQuestion(getSite().getShell(), "Update Local View?",
                "The remote view in the ArchE database is updated. Do you want to update this local view?");
	}

	protected void validateAViewDiagram(){
		if(diagram == null)
			throw new NullPointerException("The diagram object must be not null.");
		
		String versionName = diagram.getVersionName();
		int versionID = diagram.getVersionID();
		String rootFactId = diagram.getRootNodeFactId();
		int rootFactType = diagram.getRootNodeFactType();
		String selectedViewType = diagram.getSelectedViewType();
		String selectedLayoutType = diagram.getSelectedLayoutType();
		boolean bMismatched = false;
		
		Map mapFactIdToArchElement = new HashMap();		
		for (Iterator eIt = diagram.getChildren().iterator(); eIt.hasNext(); )
		{					
			ArchElement element = (ArchElement)eIt.next();
			mapFactIdToArchElement.put(element.getFactid(),element);
		}
		
		if (selectedViewType.equalsIgnoreCase(ViewLayout.DECOMPOSITION_VIEW)){
//			System.out.println(System.currentTimeMillis());
			SparseTree archTree = ArcheModel.GetInstance().getFullDecompositionArcheModel(versionName, rootFactId, false); 			
//			System.out.println(System.currentTimeMillis());

			if(archTree == null)
				return;		// The graph wasn't created, meaning that there is nothing to compare
//				throw new NullPointerException("The archGraph object must be not null.");
	
			// compare all the nodes in the curretn diagram with those in the archTree derived from the database;
			if(archTree.getVertices().size() != diagram.getChildren().size())
				bMismatched = true;
			else{
				
				try {
					bMismatched = !equalTwoGraphs(archTree, mapFactIdToArchElement);
				} catch (RuntimeException e) {
					e.printStackTrace();
					bMismatched = true;
				}					
			}
			// If two graphs are mismatched, create a new layout for the graph derived from the database
//			if(bMismatched == true && confirmLocalViewUpdate()){				
			if(bMismatched == true){				
				DesignViewDiagram aviewDiagram =  null;				
				DecompositionViewLayout	layout = new DecompositionViewLayout();
				aviewDiagram = layout.generateTreeLayout(archTree);		
				if(aviewDiagram != null){
					aviewDiagram.setVersionName(versionName);
					aviewDiagram.setVersionID(versionID);
					aviewDiagram.setRootNodeFactId(rootFactId);
					aviewDiagram.setRootNodeFactType(rootFactType);										
					aviewDiagram.setSelectedViewType(selectedViewType);
					aviewDiagram.setSelectedLayoutType(selectedLayoutType);	
					diagram = aviewDiagram;
					doSave();
				}				
			}
		}
		else if (selectedViewType.equalsIgnoreCase(ViewLayout.DEPENDENCY_VIEW)){
//			System.out.println(System.currentTimeMillis());
			SparseGraph archGraph = ArcheModel.GetInstance().getFullDependencyArcheModel(versionName, rootFactId, false, rootFactType); 				
//			System.out.println(System.currentTimeMillis());

			if(archGraph == null)
				return;		// The graph wasn't created, meaning that there is nothing to compare
//				throw new NullPointerException("The archGraph object must be not null.");
	
			// compare all the nodes in the curretn diagram with those in the archGraph derived from the database;
			if(archGraph.getVertices().size() != diagram.getChildren().size())
				bMismatched = true;
			else{
				
				try {
					bMismatched = !equalTwoGraphs(archGraph, mapFactIdToArchElement);
				} catch (RuntimeException e) {
					e.printStackTrace();
					bMismatched = true;
				}					
			}
			// If two graphs are mismatched, create a new layout for the graph derived from the database
			if(bMismatched == true && confirmLocalViewUpdate()){
				DesignViewDiagram aviewDiagram =  null;				
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
				if(aviewDiagram != null){
					aviewDiagram.setVersionName(versionName);
					aviewDiagram.setVersionID(versionID);
					aviewDiagram.setRootNodeFactId(rootFactId);
					aviewDiagram.setRootNodeFactType(rootFactType);
					aviewDiagram.setSelectedViewType(selectedViewType);
					aviewDiagram.setSelectedLayoutType(selectedLayoutType);	
					diagram = aviewDiagram;
					doSave();
				}
			}			
		}		
		else if (selectedViewType.equalsIgnoreCase(ViewLayout.CONCURRENCY_VIEW)){
			// TODO: [Extension Point] for concurrency view
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		try {
			IFile file = ((IFileEditorInput) input).getFile();
			file.refreshLocal( IResource.DEPTH_ZERO, null );
			ObjectInputStream in = new ObjectInputStream(file.getContents());
			diagram = (DesignViewDiagram) in.readObject();
			in.close();
			if(!file.getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE))
				setPartName(file.getName());
			else{
				if(diagram == null)
					setPartName(DesignViewCreatorForExternal.DEFAULT_EDITOR_TITLE);				
				else if(diagram.getSelectedViewType().equals(ViewLayout.DECOMPOSITION_VIEW)) 
					setPartName("Decomposition View");				
				else if(diagram.getSelectedViewType().equals(ViewLayout.DEPENDENCY_VIEW)) 
					setPartName("Dependency View");
				else if (diagram.getSelectedViewType().equals(ViewLayout.CONCURRENCY_VIEW)){
					// TODO: [Extension Point] for concurrency view 
				}
			}
			
		} catch (IOException e) { 
//			handleLoadException(e); 
		} catch (CoreException e) { 
//			handleLoadException(e); 
		} catch (ClassNotFoundException e) { 
//			handleLoadException(e); 
		}

		// Validate the diagram read from the file against the one derived from the ArchE database
//		if(diagram != null)
//			validateAViewDiagram();
//		else
//			diagram = new AViewDiagram();
		
		if(diagram == null)
			diagram = new DesignViewDiagram();		
		
		if (!editorSaving) {
			if (getGraphicalViewer() != null) {
				getGraphicalViewer().setContents(diagram);
				loadProperties();
			}
			if (outlinePage != null) {
				outlinePage.setContents(diagram);
			}
		}
		
	}
	
	protected void loadProperties() {
		
		// Zoom
		ZoomManager manager = (ZoomManager)getGraphicalViewer()
				.getProperty(ZoomManager.class.toString());
		if (manager != null)
			if(getModel() != null)
				manager.setZoom(getModel().getZoom());
		// Scroll-wheel Zoom
		getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), 
				MouseWheelZoomHandler.SINGLETON);

	}
	
	protected void saveProperties() {
		ZoomManager manager = (ZoomManager)getGraphicalViewer()
				.getProperty(ZoomManager.class.toString());
		if (manager != null)
			getModel().setZoom(manager.getZoom());		
	}	
	
	protected FigureCanvas getEditor(){
		return (FigureCanvas)getGraphicalViewer().getControl();
	}
	
	public boolean isDirty(){
		if(getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE))
			return false;
		return super.isDirty();
	}
	
	
	public class DesignViewOutlinePage	extends ContentOutlinePage implements IAdaptable
	{
		
		private PageBook pageBook;
		private Control outline;
		private Canvas overview;
		private IAction showOutlineAction, showOverviewAction;
		static final int ID_OUTLINE  = 0;
		static final int ID_OVERVIEW = 1;
		private Thumbnail thumbnail;
		private DisposeListener disposeListener;
		
		public DesignViewOutlinePage(EditPartViewer viewer){
			super(viewer);
		}
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
//			id = ActionFactory.DELETE.getId();
//			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();
		}
	
		protected void configureOutlineViewer(){
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new DesignViewTreeEditPartFactory());
			ContextMenuProvider provider = new DesignViewEditorContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);
			getSite().registerContextMenu(
					"cmu.sei.archeGui.outline.contextmenu", //$NON-NLS-1$
				provider, getSite().getSelectionProvider());
			IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
			showOutlineAction = new Action() {
				public void run() {
					showPage(ID_OUTLINE);
				}
			};
			showOutlineAction.setImageDescriptor(ImageDescriptor.createFromFile(
									DesignViewPlugin.class,"icons/outline.gif")); //$NON-NLS-1$
			tbm.add(showOutlineAction);
			showOverviewAction = new Action() {
				public void run() {
					showPage(ID_OVERVIEW);
				}
			};
			showOverviewAction.setImageDescriptor(ImageDescriptor.createFromFile(
									DesignViewPlugin.class,"icons/overview.gif")); //$NON-NLS-1$
			tbm.add(showOverviewAction);
			showPage(ID_OUTLINE);
		}
	
		public void createControl(Composite parent){
			pageBook = new PageBook(parent, SWT.NONE);
			outline = getViewer().createControl(pageBook);
			overview = new Canvas(pageBook, SWT.NONE);
			pageBook.showPage(outline);
			configureOutlineViewer();
			hookOutlineViewer();
			initializeOutlineViewer();
		}
		
		public void dispose(){
			unhookOutlineViewer();
			if (thumbnail != null) {
				thumbnail.deactivate();
				thumbnail = null;
			}
			super.dispose();
			DesignViewEditor.this.outlinePage = null;
		}
		
		public Object getAdapter(Class type) {
			if (type == ZoomManager.class)
				return getGraphicalViewer().getProperty(ZoomManager.class.toString());
			return null;
		}
	
		public Control getControl() {
			return pageBook;
		}
	
		protected void hookOutlineViewer(){
			getSelectionSynchronizer().addViewer(getViewer());
		}
	
		protected void initializeOutlineViewer(){
			setContents(getModel());
		}
		
		protected void initializeOverview() {
			LightweightSystem lws = new LightweightSystem(overview);
			RootEditPart rep = getGraphicalViewer().getRootEditPart();
			if (rep instanceof ScalableFreeformRootEditPart) {
				ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart)rep;
				thumbnail = new ScrollableThumbnail((Viewport)root.getFigure());
				thumbnail.setBorder(new MarginBorder(3));
				thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
				lws.setContents(thumbnail);
				disposeListener = new DisposeListener() {
					public void widgetDisposed(DisposeEvent e) {
						if (thumbnail != null) {
							thumbnail.deactivate();
							thumbnail = null;
						}
					}
				};
				getEditor().addDisposeListener(disposeListener);
			}
		}
		
		public void setContents(Object contents) {
			getViewer().setContents(contents);
		}
		
		protected void showPage(int id) {
			if (id == ID_OUTLINE) {
				showOutlineAction.setChecked(true);
				showOverviewAction.setChecked(false);
				pageBook.showPage(outline);
				if (thumbnail != null)
					thumbnail.setVisible(false);
			} else if (id == ID_OVERVIEW) {
				if (thumbnail == null)
					initializeOverview();
				showOutlineAction.setChecked(false);
				showOverviewAction.setChecked(true);
				pageBook.showPage(overview);
				thumbnail.setVisible(true);
			}
		}
		
		protected void unhookOutlineViewer(){
			getSelectionSynchronizer().removeViewer(getViewer());
			if (disposeListener != null && getEditor() != null && !getEditor().isDisposed())
				getEditor().removeDisposeListener(disposeListener);
		}
		
	}	
	
	/**
	 * Creates an outline pagebook for this editor.
	 */
	public class Old_AViewOutlinePage extends ContentOutlinePage {	
		/**
		 * Create a new outline page for the shapes editor.
		 * @param viewer a viewer (TreeViewer instance) used for this outline page
		 * @throws IllegalArgumentException if editor is null
		 */
		public Old_AViewOutlinePage(EditPartViewer viewer) {
			super(viewer);
		}
	
		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		public void createControl(Composite parent) {
			// create outline viewer page
			getViewer().createControl(parent);
			// configure outline viewer
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new DesignViewTreeEditPartFactory());
			// configure & add context menu to viewer
			ContextMenuProvider cmProvider = new DesignViewEditorContextMenuProvider(
					getViewer(), getActionRegistry()); 
			getViewer().setContextMenu(cmProvider);
			getSite().registerContextMenu(
					"cmu.sei.archeGui.outline.contextmenu",
					cmProvider, getSite().getSelectionProvider());		
			// hook outline viewer
			getSelectionSynchronizer().addViewer(getViewer());
			// initialize outline viewer with model
			getViewer().setContents(getModel());
			// show outline viewer
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#dispose()
		 */
		public void dispose() {
			// unhook outline viewer
			getSelectionSynchronizer().removeViewer(getViewer());
			// dispose
			super.dispose();
		}
	
		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.IPage#getControl()
		 */
		public Control getControl() {
			return getViewer().getControl();
		}
		
		/**
		 * @see org.eclipse.ui.part.IPageBookViewPage#init(org.eclipse.ui.part.IPageSite)
		 */
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
//			id = ActionFactory.DELETE.getId();
//			bars.setGlobalActionHandler(id, registry.getAction(id));
		}

		public void setContents(Object contents) {
			getViewer().setContents(contents);
		}
		
	}

}