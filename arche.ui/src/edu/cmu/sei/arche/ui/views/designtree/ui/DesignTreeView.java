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

package edu.cmu.sei.arche.ui.views.designtree.ui;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import jess.Deftemplate;
import jess.Fact;
import jess.Rete;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.ui.views.designtree.model.Model;
import edu.cmu.sei.arche.ui.views.designtree.model.TreeNode;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.TreeElementTypeVO;
import edu.cmu.sei.arche.vo.TreeVO;
import edu.cmu.sei.designview.DesignViewCreatorForExternal;
import edu.cmu.sei.designview.DesignViewEditor;
import edu.cmu.sei.designview.DesignViewPlugin;
import edu.cmu.sei.designview.IExternalSelect;
import edu.cmu.sei.designview.layouts.DependencyViewLayout;
import edu.cmu.sei.designview.model.ArchElement;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.cmu.sei.designview.model.Interface;
import edu.cmu.sei.designview.model.Module;

/**
 * Insert the type's description here.
 * @see ViewPart
 * @author Phil Bianco, Hyunwoo Kim
 */
public class DesignTreeView extends ViewPart implements IPropertyChangeListener, IExternalSelect{
	protected DesignTreeView self;
	protected TreeViewer treeViewer;
	protected Text text;
	protected MovingBoxLabelProvider labelProvider;
	   /** The parent control for the design tree view */
    private Composite              parent;
	//protected Action onlyBoardGamesAction, atLeastThreeItems;
	//protected Action booksBoxesGamesAction, noArticleAction;
	//protected Action addBookAction, removeAction;
	//protected ViewerFilter onlyBoardGamesFilter, atLeastThreeFilter;
	//protected ViewerSorter booksBoxesGamesSorter, noArticleSorter;
	protected TreeNode root;
	  /** The project VO for this instance of the scenario view */
    private ProjectVO              project;

    /** The HashSet that is to be the input for the Scenarios view */
    Hashtable                        trees;
	
    /** The constant to describe tree elements which are not facts */
    protected static final int NO_FACT_ID = -1;
    
    /** The editor that is to be used for displaying a graphical view
     *  derived from a design element(tree node) which is double clicked
     *  in this DesignTreeView
     */
    private DesignViewEditor editor;

    /**
	 * The constructor.
	 */
	public DesignTreeView() {		
		editor = null;
		self = this;
	}

	/*
	 * @see IWorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		/* Create a grid layout object so the text and treeviewer
		 * are layed out the way I want. */
		this.parent = parent;
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 2;
		parent.setLayout(layout);
		
		/* Create a "label" to display information in. I'm
		 * using a text field instead of a lable so you can
		 * copy-paste out of it. */
		text = new Text(parent, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
		// layout the text field above the treeviewer
		GridData layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		text.setLayoutData(layoutData);
		
		// Create the tree viewer as a child of the composite parent
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(new MovingBoxContentProvider());
		labelProvider = new MovingBoxLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		
		treeViewer.setUseHashlookup(true);
		
		// layout the tree viewer below the text field
		layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		layoutData.verticalAlignment = GridData.FILL;
		treeViewer.getControl().setLayoutData(layoutData);
		
		// Create menu, toolbars, filters, sorters.
		createFiltersAndSorters();
		createActions();
		createMenus();
		createToolbar();
		hookListeners();
		
		treeViewer.setInput(getInput());
		treeViewer.expandToLevel(3);
		refreshDesignViewEditor();
	}
	
	protected void createFiltersAndSorters() {
		//atLeastThreeFilter = new ThreeItemFilter();
		//onlyBoardGamesFilter = new BoardgameFilter();
		//booksBoxesGamesSorter = new BookBoxBoardSorter();
		//noArticleSorter = new NoArticleSorter();
	}

	public void select(String name){
		TreeNode root = (TreeNode)treeViewer.getInput();
		if(root != null){
			TreeNode moduleview = (TreeNode)root.getTreeNodes().get(0);
			if(moduleview != null){
				TreeNode dependencies = (TreeNode)moduleview.getTreeNodes().get(0);
				if(dependencies != null){
					List nodes = dependencies.getTreeNodes();
					for(int i=0; i < nodes.size(); i++){
						TreeNode node = (TreeNode)nodes.get(i);
						if(node.getName().equals(name)){
							IStructuredSelection selection = new StructuredSelection(node);
							treeViewer.setSelection(selection);
							break;
						}				
					}									
				}
			}			
		}
	}
	
	protected void hookListeners() {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {				
								
				editor = null;
				boolean bChangMade = true;
				IWorkbenchPage page = getSite().getPage();
				if(page==null){
	            	System.out.println("Page is null");		       				 
					return;												
				}
				IEditorReference[] editors = getSite().getPage().getEditorReferences();
				for(int i=0; i < editors.length ; i++){
					try {
						if(editors[i].getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE)){
							editor = (DesignViewEditor)editors[i].getEditor(false);
							break;
						}
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}																				
				if(editor != null)				
					return;						
				
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				TreeNode treeNode = (TreeNode)selection.getFirstElement();
				boolean bRootMarked = true;

				if(treeNode.getFactID() == NO_FACT_ID) {
					if(treeNode.getName().equals("dependencies")){
						if(treeNode.getTreeNodes().size() == 0){ 
							TreeNode e = new TreeNode("(M)Empty");
							e.setFactID(0);	
							e.setTreeElementType(treeNode.getTreeElementType());
							treeNode = e;
						}
						else
							treeNode = (TreeNode)treeNode.getTreeNodes().get(0);
						bRootMarked = false;
					}
					else{
//		            	System.out.println("This is not a fact");		       				 
						return;						
					}
				}	

				IProject currentProject = ArchEUIPlugin.getDefault().getOpenProject();  
				DesignViewCreatorForExternal creator = DesignViewCreatorForExternal.getDefault();
				
				String selectedViewType = null;
				String versionName = null;
				String selectedLayoutType = null;
				String rootFactId = null;

				if(treeNode.getTreeElementType().getName().equalsIgnoreCase("modules")){
					selectedViewType = "DecompositionView";
					if(treeNode.getName().indexOf("(I)") != -1) // hardcoded to support interface, treeconfig.xml file should be re-designed to include interface
						rootFactId = "<Fact-" + treeNode.getParent().getFactID() + ">" ;										
					else
						rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;					
				}
				else if(treeNode.getTreeElementType().getName().equalsIgnoreCase("dependencies")){
					selectedViewType = "DependencyView";
					rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;
				}
				
//				versionName = ArchEUIPlugin.getDefault().getOpenProject().getName();
				versionName = "Architecture1"; // hardcoded for one-level search, should be changed later to support multi-level search
				selectedLayoutType = DependencyViewLayout.KK_LAYOUT;	// The default value of this variable can be defined as user preference.
				if(selectedViewType != null && versionName != null && rootFactId != null ){
					// configure the creator with input parameters
					if(treeNode.getName().indexOf("(M)") != -1)
						creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE);
					else if (treeNode.getName().indexOf("(I)") != -1) 
						creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE_INTERFACE);
					else {
		            	System.out.println("Unsupported node type");		       				 
						return;						
					}
					
					if(bChangMade){
						// create a graphical view for the configuration given
						IFile gViewCached = creator.createGraphicalView(currentProject);			
					
						// open the graphical view file in the editor
						if (gViewCached != null && page != null) {
							try {
								
								if(editor != null)
									page.closeEditor((IEditorPart)editor,false); 							
								else{
									for(int i=0; i < editors.length ; i++){
										if(editors[i].getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE)){
											page.closeEditor((IEditorPart)editors[i],false); 							
										}
									}								
								}													
								editor = (DesignViewEditor)IDE.openEditor(page, gViewCached, true);
								if(editor!=null){
									editor.setSelectionSynchronizerExternal(self);									
									if(bRootMarked){
										// To draw RootFact within the viewer's clientarea  
										String rootFactID = editor.getModel().getRootNodeFactId();
										Iterator iter = editor.getModel().getChildren().iterator();
										while(iter.hasNext()){
											ArchElement e = (ArchElement)iter.next();
											if(e.getFactid().equals(rootFactID))
												if(e instanceof Module){
													Module m = (Module)e;
													m.setRoot(true);
													break;
												}								
												else if(e instanceof Interface){
													Interface i = (Interface)e;
													i.setRoot(true);
													break;											
												}
										}					
									}
								}								
							} catch (PartInitException e) {
								e.printStackTrace();
							}
						}					
					}

				}
//            	System.out.println("Double Clicked: End");		       				 
			}
			
		});
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				// if the selection is empty clear the label
				if(event.getSelection().isEmpty()) {
					text.setText("");
					return;
				}
				if(event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					StringBuffer toShow = new StringBuffer();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
						Object domain = (Model) iterator.next();
						String value = labelProvider.getText(domain);
						toShow.append(value);
						toShow.append(", ");
					}
					// remove the trailing comma space pair
					if(toShow.length() > 0) {
						toShow.setLength(toShow.length() - 2);
					}
					text.setText(toShow.toString());
				}

				editor = null;
				boolean bChangMade = true;
				IWorkbenchPage page = getSite().getPage();
				if(page==null){
	            	System.out.println("Page is null");		       				 
					return;												
				}
				IEditorReference[] editors = getSite().getPage().getEditorReferences();
				for(int i=0; i < editors.length ; i++){
					try {
						if(editors[i].getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE)){
							editor = (DesignViewEditor)editors[i].getEditor(false);
							break;
						}
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}							
				
				if(editor == null)
					return;

				editor.setSelectionSynchronizerExternal(self);							
				
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				TreeNode treeNode = (TreeNode)selection.getFirstElement();
				boolean bRootMarked = true;

				if(treeNode.getFactID() == NO_FACT_ID) {
					if(treeNode.getName().equals("dependencies")){
						if(treeNode.getTreeNodes().size() == 0){ 
							TreeNode e = new TreeNode("(M)Empty");
							e.setFactID(0);	
							e.setTreeElementType(treeNode.getTreeElementType());
							treeNode = e;
						}
						else
							treeNode = (TreeNode)treeNode.getTreeNodes().get(0);
						bRootMarked = false;
					}
					else{
//		            	System.out.println("This is not a fact");		       				 
						return;						
					}
				}	
								
				IProject currentProject = ArchEUIPlugin.getDefault().getOpenProject();  
				DesignViewCreatorForExternal creator = DesignViewCreatorForExternal.getDefault();
				
				String selectedViewType = null;
				String versionName = null;
				String selectedLayoutType = null;
				String rootFactId = null;

				if(treeNode.getTreeElementType().getName().equalsIgnoreCase("modules")){
					selectedViewType = "DecompositionView";
					if(treeNode.getName().indexOf("(I)") != -1)
						rootFactId = "<Fact-" + treeNode.getParent().getFactID() + ">" ;										
					else
						rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;					
				}
				else if(treeNode.getTreeElementType().getName().equalsIgnoreCase("dependencies")){
					selectedViewType = "DependencyView";
					rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;
				}
				
				versionName = "Architecture1"; // hardcoded for one-level search, should be changed later to support multi-level search
				selectedLayoutType = DependencyViewLayout.KK_LAYOUT;	// The default value of this variable can be defined as user preference.
				if(selectedViewType != null && versionName != null && rootFactId != null ){
					int newVersionID = -1;
					// configure the creator with input parameters
					if(treeNode.getName().indexOf("(M)") != -1)
						newVersionID = creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE);
					else if (treeNode.getName().indexOf("(I)") != -1)
						newVersionID = creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE_INTERFACE);
					else {
		            	System.out.println("Unsupported node type");		       				 
						return;						
					}

					DesignViewDiagram diagram = editor.getModel();
					String vname = diagram.getVersionName();
					int vid = diagram.getVersionID();
					String oldRootFactID = diagram.getRootNodeFactId();
					if(versionName.equals(vname) && newVersionID == vid){
						if(!oldRootFactID.equals(rootFactId)){
							Iterator iter = diagram.getChildren().iterator();
							while(iter.hasNext()){
								ArchElement e = (ArchElement)iter.next();
								if(e.getFactid().equals(oldRootFactID))
									if(e instanceof Module){
										Module m = (Module)e;
										if(m.getRoot())
											m.setRoot(false);
										break;
									}								
									else if(e instanceof Interface){
										Interface i = (Interface)e;
										if(i.getRoot())
											i.setRoot(false);
										break;											
									}
							}
							
							iter = diagram.getChildren().iterator();
							while(iter.hasNext()){
								ArchElement e = (ArchElement)iter.next();
								if(e.getFactid().equals(rootFactId)){
									if(e instanceof Module){
										Module m = (Module)e;
										if(bRootMarked && !m.getRoot())
											m.setRoot(true);
										diagram.setRootNodeFactId(rootFactId);
										bChangMade = false;
										break;
									}								
									else if(e instanceof Interface){
										Interface i = (Interface)e;
										if(bRootMarked && !i.getRoot())
											i.setRoot(true);
										diagram.setRootNodeFactId(rootFactId);
										bChangMade = false;
										break;											
									}
								}
							}
						}
						else{
							Iterator iter = diagram.getChildren().iterator();
							while(iter.hasNext()){
								ArchElement e = (ArchElement)iter.next();
								if(e.getFactid().equals(oldRootFactID))
									if(e instanceof Module){
										Module m = (Module)e;
										if(bRootMarked && !m.getRoot())
											m.setRoot(true);
										else if(!bRootMarked && m.getRoot())
											m.setRoot(false);
										break;
									}								
									else if(e instanceof Interface){
										Interface i = (Interface)e;
										if(bRootMarked && !i.getRoot())
											i.setRoot(true);
										else if(!bRootMarked && i.getRoot())
											i.setRoot(false);
										break;											
									}
							}								
							bChangMade = false;
						}								
					}
					
					if(bChangMade){
						// create a graphical view for the configuration given
						IFile gViewCached = creator.createGraphicalView(currentProject);			
					
						// open the graphical view file in the editor
						if (gViewCached != null && page != null) {
							try {
								
								if(editor != null)
									page.closeEditor((IEditorPart)editor,false); 							
								else{
									for(int i=0; i < editors.length ; i++){
										if(editors[i].getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE)){
											page.closeEditor((IEditorPart)editors[i],false); 							
										}
									}								
								}													
								editor = (DesignViewEditor)IDE.openEditor(page, gViewCached, true);
								if(editor!=null && bRootMarked){
									// To draw RootFact within the viewer's clientarea  
									String rootFactID = editor.getModel().getRootNodeFactId();
									Iterator iter = editor.getModel().getChildren().iterator();
									while(iter.hasNext()){
										ArchElement e = (ArchElement)iter.next();
										if(e.getFactid().equals(rootFactID))
											if(e instanceof Module){
												Module m = (Module)e;
												if(!m.getRoot())
													m.setRoot(true);
												break;
											}								
											else if(e instanceof Interface){
												Interface i = (Interface)e;
												if(!i.getRoot())
													i.setRoot(true);
												break;											
											}
									}										
									editor.setSelectionSynchronizerExternal(self);							
								}
							} catch (PartInitException e) {
								e.printStackTrace();
							}
						}					
					}
				}	
			}
		});
	}
	
	protected void createActions() {
		//onlyBoardGamesAction = new Action("Only Board Games") {
			//public void run() {
				//updateFilter(onlyBoardGamesAction);
			//}
		//};
		//onlyBoardGamesAction.setChecked(false);
		
		//atLeastThreeItems = new Action("Boxes With At Least Three Items") {
			//public void run() {
				//updateFilter(atLeastThreeItems);
			//}
		//};
		//atLeastThreeItems.setChecked(false);
		
		//booksBoxesGamesAction = new Action("Books, Boxes, Games") {
			//public void run() {
				//updateSorter(booksBoxesGamesAction);
			//}
		//};
		//booksBoxesGamesAction.setChecked(false);
		
		//noArticleAction = new Action("Ignoring Articles") {
			//public void run() {
				//updateSorter(noArticleAction);
			//}
		//};
		//noArticleAction.setChecked(false);
		
		//addBookAction = new Action("Add Book") {
		//	public void run() {
			//	addNewBook();
			//}			
		//};
		//addBookAction.setToolTipText("Add a New Book");
		//addBookAction.setImageDescriptor(edu.cmu.sei.arche.ArchEUIPlugin.getImageDescriptor("newBook.gif"));

		//removeAction = new Action("Delete") {
			//public void run() {
				//removeSelected();
			//}			
		//};
		//removeAction.setToolTipText("Delete");
		//removeAction.setImageDescriptor(edu.cmu.sei.arche.ArchEUIPlugin.getImageDescriptor("remove.gif"));		
	}
	
	/** Add a new book to the selected moving box.
	 * If a moving box is not selected, use the selected
	 * obect's moving box. 
	 * 
	 * If nothing is selected add to the root. */
	protected void addNewBook() {
		TreeNode receivingBox;
		if (treeViewer.getSelection().isEmpty()) {
			receivingBox = root;
		} else {
			IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
			Model selectedDomainObject = (Model) selection.getFirstElement();
			if (!(selectedDomainObject instanceof TreeNode)) {
				receivingBox = selectedDomainObject.getParent();
			} else {
				receivingBox = (TreeNode) selectedDomainObject;
			}
		}
		//receivingBox.add(Book.newBook());
	}

	/** Remove the selected domain object(s).
	 * If multiple objects are selected remove all of them.
	 * 
	 * If nothing is selected do nothing. */
	protected void removeSelected() {
		if (treeViewer.getSelection().isEmpty()) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		/* Tell the tree to not redraw until we finish
		 * removing all the selected children. */
		treeViewer.getTree().setRedraw(false);
		for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			TreeNode parent = model.getParent();
			parent.remove(model);
		}
		treeViewer.getTree().setRedraw(true);
	}
	
	protected void createMenus() {
		IMenuManager rootMenuManager = getViewSite().getActionBars().getMenuManager();
		rootMenuManager.setRemoveAllWhenShown(true);
		rootMenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillMenu(mgr);
			}
		});
		fillMenu(rootMenuManager);
	}


	protected void fillMenu(IMenuManager rootMenuManager) {
		//IMenuManager filterSubmenu = new MenuManager("Filters");
		//rootMenuManager.add(filterSubmenu);
		//filterSubmenu.add(onlyBoardGamesAction);
		//filterSubmenu.add(atLeastThreeItems);
		
		//IMenuManager sortSubmenu = new MenuManager("Sort By");
		//rootMenuManager.add(sortSubmenu);
		//sortSubmenu.add(booksBoxesGamesAction);
		//sortSubmenu.add(noArticleAction);
	}
	
	
	
	protected void updateSorter(Action action) {
		//if(action == booksBoxesGamesAction) {
			//noArticleAction.setChecked(!booksBoxesGamesAction.isChecked());
			//if(action.isChecked()) {
			//	treeViewer.setSorter(booksBoxesGamesSorter);
			//} else {
				//treeViewer.setSorter(null);
			//}
		//} else if(action == noArticleAction) {
			//booksBoxesGamesAction.setChecked(!noArticleAction.isChecked());
			//if(action.isChecked()) {
				//treeViewer.setSorter(noArticleSorter);
			//} else {
				//treeViewer.setSorter(null);
			//}
		//}
			
	}
	
	/* Multiple filters can be enabled at a time. */
	protected void updateFilter(Action action) {
		//if(action == atLeastThreeItems) {
			//if(action.isChecked()) {
				//treeViewer.addFilter(atLeastThreeFilter);
			//} else {
				//treeViewer.removeFilter(atLeastThreeFilter);
			//}
		//} else if(action == onlyBoardGamesAction) {
			//if(action.isChecked()) {
			//	treeViewer.addFilter(onlyBoardGamesFilter);
			//} else {
				//treeViewer.removeFilter(onlyBoardGamesFilter);
			//}
		//}
	}
	
	protected void createToolbar() {
		//IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		//toolbarManager.add(addBookAction);
		//toolbarManager.add(removeAction);
	}
	
	
	public TreeNode getInput() {
		
		  root = new TreeNode();
		  Rete engineArchE = null;
		  //      retrieve the ProjectVO from the workbench
		try{
        project = ArchEUIPlugin.getDefault().getProjectVo();
		
        //trees = (HashSet)project.getTrees();
        trees = ArchEUIPlugin.getDefault().getAvailableTRs();
        //root = new MovingBox();

	        if(project != null){
	        	engineArchE = ArchEUIPlugin.getDefault().getArchEFacade().getEngineArchE();
		        for (Enumeration en = trees.keys(); en.hasMoreElements();) {
		            TreeVO trvo = (TreeVO) en.nextElement();
		            TreeNode treeNodes = new TreeNode(trvo.getName());
		            treeNodes.setFactID(NO_FACT_ID);
		            root.add(treeNodes);
		            for (Iterator it = trvo.getTreeElementTypes().iterator(); it.hasNext();) {
		              	TreeElementTypeVO tretVO = (TreeElementTypeVO)it.next();
		               if(tretVO.getName().equals("modules")){ 
		                   createModuleTree(tretVO, treeNodes, engineArchE);
		                } 
		               if(tretVO.getName().equals("dependencies")){ 
		                   createDependencyTree(tretVO, treeNodes, engineArchE);
		                } 
		               
		            } 
		            // loop through relationship types of that RF
		          
		        }    
	        	
	        }

		}catch(Exception e){
			System.out.println(e.toString());
			return root;
		}
		return root;
	}
	
	public void traverseChildNodes1(Vector children, Vector parent, Hashtable tretVOFactType, 
			                           Hashtable  tretVORelationFactType, TreeNode treeNode, Fact fact, TreeElementTypeVO tretVO, Vector prevVisitedNodes) {
		    for(int i = 0; i < children.size(); i++)
		     {
		    	Integer childFactId = (Integer)children.elementAt(i);
		    	Fact childFact = (Fact)tretVOFactType.get(childFactId);
		    	//System.out.println(childFactId.intValue());
		    	//System.out.println(childFact.toString());
		    	try{
		    		  boolean test = true;
		    		  
		    		  for(int j = 0; j < prevVisitedNodes.size(); j++)
            	       {
		    			  try{
            		       Integer prevVisited = (Integer)prevVisitedNodes.elementAt(j);
            		       if(prevVisited.intValue() == childFactId.intValue()){
            			       test = false;
            			      
            			       //TreeNode myNode = new TreeNode("Repeats previous sequence");
            			       //newNode.add(myNode);
            		       }
		    			  }catch(Exception e)
		    			   {
		    			   }
		    			  }
            	       //}
		    		
		    		 if(test){ 
             	      
		    	      TreeNode newNode = new TreeNode(childFact.getSlotValue(tretVO.getSlotNameToDisplay()).stringValue(null));
		    	      newNode.setFactID(childFact.getFactId());
		    	      newNode.setTreeElementType(tretVO);
		    	      treeNode.add(newNode);
		    	     
		    	      prevVisitedNodes.addElement(childFactId);
		    	      Vector children1 = null;
	    	                try{
	    	    	            children1 = (Vector)parent.elementAt(childFact.getFactId());
	    	                   }catch(Exception e)
	    	                   {
	    	    	             // nothing is done
	    	                   }
	    	                   if(children1 != null)
	    	                   { 
	    	                	    Vector newPrevVisitedNodes = new Vector(10);
	    	                	    newPrevVisitedNodes.setSize(prevVisitedNodes.size());
	    	                	    newPrevVisitedNodes.addAll(prevVisitedNodes);
         			                 //traverseChildNodes1(children1, parent, tretVOFactType, 
	                                 //tretVORelationFactType, newNode, childFact, slotNameToDisplay,  newPrevVisitedNodes);	    	                	      
	    	                   }  
		    	    } 
         		  }catch(Exception e){
         			  e.printStackTrace();
  		    	    }
		    	}    
		
	}
	
	
		
	public void traverseChildNodes(Vector children, Vector parent, Hashtable tretVOFactType, 
			                           Hashtable  tretVORelationFactType, TreeNode treeNode, Fact fact, TreeElementTypeVO tretVO, Vector prevVisitedNodes) {
		    for(int i = 0; i < children.size(); i++)
		     {
		    	Integer childFactId = (Integer)children.elementAt(i);
		    	Fact childFact = (Fact)tretVOFactType.get(childFactId);
		    	//System.out.println(childFactId.intValue());
		    	//System.out.println(childFact.toString());
		    	try{
		    	      TreeNode newNode = new TreeNode(childFact.getSlotValue(tretVO.getSlotNameToDisplay()).stringValue(null));
		    	      newNode.setFactID(childFact.getFactId());
		    	      newNode.setTreeElementType(tretVO);		    	      
		    	      treeNode.add(newNode);
		    	      //boolean test = true;
               	       //for(int j = 0; j < prevVisitedNodes.size(); j++)
               	       //{
               		      // Integer prevVisited = (Integer)prevVisitedNodes.elementAt(j);
               		      // if(prevVisited.intValue() == childFactId.intValue()){
               			       //test = false;
               			      // TreeNode myNode = new TreeNode("Repeats previous sequence");
               			      // newNode.add(myNode);
               		       //}
               	       //}
		    	      prevVisitedNodes.addElement(childFactId);
		    	      Vector children1 = null;
	    	                try{
	    	    	            children1 = (Vector)parent.elementAt(childFact.getFactId());
	    	                   }catch(Exception e)
	    	                   {
	    	    	             // nothing is done
	    	                   }
	    	                   if(children1 != null)
	    	                   { 
	    	                	  
	    	                	   
	    	                	  // if(test){
         			                 traverseChildNodes(children1, parent, tretVOFactType, 
         			                		 		tretVORelationFactType, newNode, childFact, tretVO, prevVisitedNodes);
	    	                	  // }   
	    	                   }    
         		  }catch(Exception e){
         			  e.printStackTrace();
  		    	    }
		    	}    
		
	}
	
	
	
	
	
	
	public void createModuleTree(TreeElementTypeVO tretVO, TreeNode treeNodes, Rete engineArchE ) {
		
			Hashtable tretVOFactType = new Hashtable();
			Hashtable tretVORelationFactType = new Hashtable();
			Vector parent = new Vector(100);
			Vector isTopLevel = new Vector(100);
			TreeNode treeElement = new TreeNode(tretVO.getName());
			treeElement.setFactID(NO_FACT_ID);
			treeElement.setTreeElementType(tretVO);			
			treeNodes.add(treeElement);
			if(tretVO.getSlotNameToDisplay().equals(""))
			{
				TreeNode dummy = new TreeNode("Invalid slot name in the configuration file");
				treeElement.add(dummy);
				return;
			}
          	try{
          	     for (Iterator it1 =  engineArchE.listFacts(); it1.hasNext();) {
          	    	  Fact fact = (Fact)it1.next();
          	    	 
          	    	 if(fact.getFactId() > parent.size())
          	    	 {
          	    		 parent.setSize(fact.getFactId() * 2);
          	    		 isTopLevel.setSize(fact.getFactId() * 2);
          	    	 }
          	    	 if(fact.getName().equals(tretVO.getFactType()) || fact.getName().equals("Design::ModuleInterface"))
          	    	 {
          	    	      // System.out.println(fact.toString());
          	    	       tretVOFactType.put(new Integer(fact.getFactId()), fact);
          	    	       Deftemplate defTemp = fact.getDeftemplate();
          	    	       int val = defTemp.getSlotIndex(tretVO.getSlotNameToDisplay());
          	    	       if (val == -1){
          	    	    	   TreeNode dummy = new TreeNode(tretVO.getSlotNameToDisplay() +
          	    	    			   " is not a valid slot name");
          	                   treeElement.add(dummy); 
          	    	        break;
          	    	       } 
          	    	       
          	    	 } 
          	    	 
          	    	 if(fact.getName().equals(tretVO.getRelationFactType()) || fact.getName().equals("Design::RealizeRelation")){
          	    	       //System.out.println(fact.toString());
          	    	       tretVORelationFactType.put(new Integer(fact.getFactId()), fact);
          	    	       Fact t1 = null;
     	    	               Fact t2 = null;
          	    	       try{
          	    	            t1 = (Fact)fact.getSlotValue(tretVO.getChildSlotName()).factValue(null);
          	    	            t2 = (Fact)fact.getSlotValue(tretVO.getParentSlotName()).factValue(null);
          	    	       }catch(Exception e)
          	    	       {
          	    	    	   TreeNode dummy = new TreeNode("The parent or child slot does not map to a fact in the factbase");
  	                           treeElement.add(dummy); 
  	    	                   break;
          	    	       }
          	    	       
          	    	       if(!t1.getName().equals(tretVO.getFactType())  && !t1.getName().equals("Design::ModuleInterface")){
          	    	    	   TreeNode dummy = new TreeNode(t1.getName() + "is not equal to the fact from the configuration file" +
          	    	    			   tretVO.getFactType());
  	                           treeElement.add(dummy); 
  	    	                   break;
          	    	       }
          	    	    	
          	    	       if(!t2.getName().equals(tretVO.getFactType())  && !t2.getName().equals("Design::ModuleInterface")){
          	    	    	   TreeNode dummy = new TreeNode(t2.getName() + "is not equal to the fact from the configuration file" +
          	    	    			   tretVO.getFactType());
  	                           treeElement.add(dummy); 
  	    	                   break;
          	    	       } 
          	    	       Vector children = null;
          	    	       try{
          	    	    	   children = (Vector)parent.elementAt(t2.getFactId());
          	    	       }catch(Exception e)
          	    	       {
          	    	    	  // nothing is done
          	    	       }
          	    	       Boolean tempy = new Boolean(false);
          	    	      // System.out.println(tempy.toString());
          	    	      // System.out.println(fact.toString());
          	    	       isTopLevel.setSize(t1.getFactId() + isTopLevel.size());
          	    	       isTopLevel.setElementAt(tempy, t1.getFactId());
          	    	       if(children == null){ 
          	    	    	   children = new Vector(1);
          	    	    	   children.addElement(new Integer(t1.getFactId()));
          	    	    	  
          	    	        }
          	    	       else{
          	    	    	   children.addElement(new Integer(t1.getFactId()));
          	    	       }
          	    	       parent.setElementAt(children, t2.getFactId());   
          	    	       //System.out.println(t1.getFactId());
          	    	       //System.out.println(t2.getFactId());
          	    	       //children.put(t1.);
          	    	 }  
          	     }
          	     
          	     for (Enumeration en1 = tretVOFactType.elements(); en1.hasMoreElements();) {
             		  Fact temp = (Fact)en1.nextElement();
             		  //Fact temp2 = (Fact)temp.getSlotValue(tretVO.getParentSlotName()).factValue(null);
             		 // System.out.println("here is temp:" + temp.toString());
             		  //Vector tempVector = (Vector)parent.elementAt(temp.getFactId());
             		  Boolean tempBool = null;
             		  try{
             			tempBool = (Boolean)isTopLevel.elementAt(temp.getFactId());
 	    	          }catch(Exception e)
 	    	           {
 	    	    	       // nothing is done
 	    	            }
             		 
             		if(tempBool != null){
//             		   System.out.println("#############################   tempbool is not equal to null###############");	
             		}
             		if(tempBool == null){
             			// System.out.println("in tempbool if:");
             			 //System.out.println(temp.getSlotValue(tretVO.getSlotNameToDisplay()).stringValue(null));
             			TreeNode treeNodes2 = new TreeNode(temp.getSlotValue(tretVO.getSlotNameToDisplay()).stringValue(null));
             			treeNodes2.setFactID(temp.getFactId());
             			treeNodes2.setTreeElementType(tretVO);
             			treeElement.add(treeNodes2);
             		    Vector children = null;
 	    	                try{
 	    	    	            children = (Vector)parent.elementAt(temp.getFactId());
 	    	                   }catch(Exception e)
 	    	                   {
 	    	                	e.printStackTrace();
 	    	    	             // nothing is done
 	    	                   }
 	    	                   if(children != null)
 	    	                   { 
 	    	                	   Vector prevVisitedNodes = new Vector(10);
 	    	                	   prevVisitedNodes.addElement(new Integer(temp.getFactId()));
             			           traverseChildNodes(children, parent, tretVOFactType, 
             			        		   		tretVORelationFactType, treeNodes2, temp, tretVO, prevVisitedNodes);
 	    	                   }    
             		  }
             			  
             		  
             	  }
          	}catch(Exception e){
          		 e.printStackTrace();
//          		 System.out.println("there was an exception, I hope this message was helpful :-)");
          	}
          	// get a list of all the child nodes only first
          	
          	//factIndexById.put(new Integer(factId), scenario);
          	
          

}
	
	public void createDependencyTree(TreeElementTypeVO tretVO, TreeNode treeNodes, Rete engineArchE ) {
		
      	Hashtable tretVOFactType = new Hashtable();
      	Hashtable tretVORelationFactType = new Hashtable();
      	Vector parent = new Vector(100);
      	
      	TreeNode treeElement = new TreeNode(tretVO.getName());      	
		treeElement.setFactID(NO_FACT_ID);
		treeElement.setTreeElementType(tretVO);			
        treeNodes.add(treeElement);
        if(tretVO.getSlotNameToDisplay().equals(""))
        {
          	TreeNode dummy = new TreeNode("Invalid slot name in the configuration file");
          	treeElement.add(dummy);
          	return;
        }
      	try{
      	     for (Iterator it1 =  engineArchE.listFacts(); it1.hasNext();) {
      	    	  Fact fact = (Fact)it1.next();
      	    	 
      	    	 if(fact.getFactId() > parent.size())
      	    	 {
      	    		 parent.setSize(fact.getFactId() * 2);
      	    		
      	    	 }

    	         // TODO The code that support Design::ModuleInterface in the design tree was hardcoded.          	    	       
    	         // treeconfig.xml must be updated later to include Design::ModuleInterface
      	    	 if(fact.getName().equals(tretVO.getFactType())  || fact.getName().equals("Design::ModuleInterface"))
      	    	 {
      	    	      // System.out.println(fact.toString());
      	    	       tretVOFactType.put(new Integer(fact.getFactId()), fact);
      	    	       Deftemplate defTemp = fact.getDeftemplate();
      	    	       int val = defTemp.getSlotIndex(tretVO.getSlotNameToDisplay());
      	    	       if (val == -1){
      	    	    	   TreeNode dummy = new TreeNode(tretVO.getSlotNameToDisplay() +
      	    	    			   " is not a valid slot name");
      	                   treeElement.add(dummy); 
      	    	        break;
      	    	       } 
      	    	       
      	    	 } 
      	    	 
      	    	 if(fact.getName().equals(tretVO.getRelationFactType())){
      	    	       //System.out.println(fact.toString());
      	    	       tretVORelationFactType.put(new Integer(fact.getFactId()), fact);
      	    	       Fact t1 = null;
 	    	           Fact t2 = null;
      	    	       try{
      	    	            t1 = (Fact)fact.getSlotValue(tretVO.getChildSlotName()).factValue(null);
      	    	            t2 = (Fact)fact.getSlotValue(tretVO.getParentSlotName()).factValue(null);
      	    	       }catch(Exception e)
      	    	       {
      	    	    	   TreeNode dummy = new TreeNode("The parent or child slot does not map to a fact in the factbase");
	                           treeElement.add(dummy); 
	    	                   break;
      	    	       }
      	    	       if(!t1.getName().equals(tretVO.getFactType())  && !t1.getName().equals("Design::ModuleInterface")){
      	    	    	   TreeNode dummy = new TreeNode(t1.getName() + "is not equal to the fact from the configuration file" +
      	    	    			   tretVO.getFactType());
	                           treeElement.add(dummy); 
	    	                   break;
      	    	       }
      	    	    	
      	    	       if(!t2.getName().equals(tretVO.getFactType())  && !t2.getName().equals("Design::ModuleInterface")){
      	    	    	   TreeNode dummy = new TreeNode(t2.getName() + "is not equal to the fact from the configuration file" +
      	    	    			   tretVO.getFactType());
	                           treeElement.add(dummy); 
	    	                   break;
      	    	       } 
      	    	       Vector children = null;
      	    	       try{
      	    	    	   children = (Vector)parent.elementAt(t2.getFactId());
      	    	       }catch(Exception e)
      	    	       {
      	    	    	  // nothing is done
      	    	       }
      	    	       Boolean tempy = new Boolean(false);
      	    	      // System.out.println(tempy.toString());
      	    	      // System.out.println(fact.toString());
      	    	      
      	    	       if(children == null){ 
      	    	    	   children = new Vector(1);
      	    	    	   children.addElement(new Integer(t1.getFactId()));
      	    	    	  
      	    	        }
      	    	       else{
      	    	    	   children.addElement(new Integer(t1.getFactId()));
      	    	       }
      	    	       parent.setElementAt(children, t2.getFactId()); 
      	    	       
      	    	       
      	    	       Vector children2 = null;
    	    	     
      	    	       if(parent.size() <= t1.getFactId()){
      	    	    	    parent.setSize(t1.getFactId() * 2);
      	    	       }
      	    	       else{
      	    	    	 try{
      	    	    	       children2 = (Vector)parent.elementAt(t1.getFactId());
      	    	            }catch(Exception e){
      	    	    	         // nothing is done
      	    	            }
      	    	       }
      	    	       
      	    	     if(children2 == null){ 
    	    	    	   children2 = new Vector(1);
    	    	    	   children2.addElement(new Integer(t2.getFactId()));
    	    	    	  
    	    	        }
    	    	       else{
    	    	    	   children2.addElement(new Integer(t2.getFactId()));
    	    	       }
    	    	       parent.setElementAt(children2, t1.getFactId()); 
      	    	       //System.out.println(t1.getFactId());
      	    	       //System.out.println(t2.getFactId());
      	    	       //children.put(t1.);
      	    	 }  
      	     }
      	     for (Enumeration en1 = tretVOFactType.elements(); en1.hasMoreElements();) {
         		  Fact temp = (Fact)en1.nextElement();
         		
         			TreeNode treeNodes2 = new TreeNode(temp.getSlotValue(tretVO.getSlotNameToDisplay()).stringValue(null));
         			treeNodes2.setFactID(temp.getFactId());
         			treeNodes2.setTreeElementType(tretVO);
         			treeElement.add(treeNodes2);
         		    Vector children = null;
	    	                try{
	    	    	            children = (Vector)parent.elementAt(temp.getFactId());
	    	                   }catch(Exception e)
	    	                   {
	    	                	e.printStackTrace();
	    	    	             // nothing is done
	    	                   }
	    	                   if(children != null)
	    	                   { 
	    	                	   Vector prevVisitedNodes = new Vector(10);
	    	                	   prevVisitedNodes.addElement(new Integer(temp.getFactId()));
	    	                	   traverseChildNodes1(children, parent, tretVOFactType, 
	    	                			   			tretVORelationFactType, treeNodes2, temp, tretVO, prevVisitedNodes);
	    	                   }    
         	  }
      	}catch(Exception e){
      		 e.printStackTrace();
//      		 System.out.println("there was an exception, I hope this message was helpful :-)");
      	}
      	// get a list of all the child nodes only first
      	
      	//factIndexById.put(new Integer(factId), scenario);
}

	public void refresh() {
//		System.out.println(" we are in refresh tree-view");
		treeViewer.remove(root);
		treeViewer.setInput(getInput());
		treeViewer.expandToLevel(3); 	// Use this to refresh and expand the tree instead of treeViewer.refresh();
		refreshDesignViewEditor();		
	}
  
	private void refreshDesignViewEditor() {
		boolean bRootMarked = true;
		TreeNode treeNode = null;
		editor = null;
		
		IWorkbenchPage page = getSite().getPage();		// Don't use "active page"			
		if(page==null)
			return;
		
		IEditorReference[] editors = page.getEditorReferences();
//		IEditorReference[] editors = page.findEditors(null, "edu.cmu.sei.designview.DesignViewEditor", IWorkbenchPage.MATCH_ID);
		for(int i=0; i < editors.length ; i++){
			try {
				if(editors[i].getEditorInput().getName().equals(DesignViewCreatorForExternal.DEFAULT_FILE_CACHE)){
					editor = (DesignViewEditor)editors[i].getEditor(false);
					break;
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}							
		
		if(root.getTreeNodes().size() > 0){
			TreeNode moduleviews = (TreeNode)root.getTreeNodes().get(0);
			Iterator iterMV = moduleviews.getTreeNodes().iterator();
			while(iterMV.hasNext()){
				TreeNode n = (TreeNode)iterMV.next();   
				if(n.getName().equals("dependencies")){
					treeNode = n;
					if(treeNode.getTreeNodes().size() == 0){ 
						TreeNode e = new TreeNode("(M)Empty");
						e.setFactID(0);	
						e.setTreeElementType(treeNode.getTreeElementType());
						treeNode = e;
					}
					else
						treeNode = (TreeNode)treeNode.getTreeNodes().get(0);
					
					bRootMarked = false;
					break;
				}
			}
			if(bRootMarked)
				return;			
		}
		else {
			if(editor != null){
				page.closeEditor((IEditorPart)editor,false); 							
			}			
			return;
		}
		
		
		
				
		// Check version's ID to avoid redrawing the same graph
		if(editor != null){
			editor.setSelectionSynchronizerExternal(self);							
			
			IProject currentProject = ArchEUIPlugin.getDefault().getOpenProject();  
			DesignViewCreatorForExternal creator = DesignViewCreatorForExternal.getDefault();
			
			String selectedViewType = null;
			String versionName = null;
			String selectedLayoutType = null;
			String rootFactId = null;

			if(treeNode.getTreeElementType().getName().equalsIgnoreCase("modules")){
				selectedViewType = "DecompositionView";
				if(treeNode.getName().indexOf("(I)") != -1)
					rootFactId = "<Fact-" + treeNode.getParent().getFactID() + ">" ;										
				else
					rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;					
			}
			else if(treeNode.getTreeElementType().getName().equalsIgnoreCase("dependencies")){
				selectedViewType = "DependencyView";
				rootFactId = "<Fact-" + treeNode.getFactID() + ">" ;
			}
			
			versionName = "Architecture1"; // hardcoded for one-level search, should be changed later to support multi-level search
			selectedLayoutType = DependencyViewLayout.KK_LAYOUT;	// The default value of this variable can be defined as user preference.
			if(selectedViewType != null && versionName != null && rootFactId != null ){
				int newVersionID = -1;
				// configure the creator with input parameters
				if(treeNode.getName().indexOf("(M)") != -1)
					newVersionID = creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE);
				else if (treeNode.getName().indexOf("(I)") != -1) // hardcoded to support interface, treeconfig.xml file should be re-designed to include interface
					newVersionID = creator.configure(selectedViewType,selectedLayoutType,versionName,rootFactId,bRootMarked,DesignViewPlugin.ROOT_FACT_TYPE_MODULE_INTERFACE);
				else {
	            	System.out.println("Unsupported node type");		       				 
					return;						
				}
				
				// create a graphical view for the configuration given
				IFile gViewCached = creator.createGraphicalView(currentProject);			

				// open the graphical view file in the editor
				if (gViewCached != null) {
					try {						
						page.closeEditor((IEditorPart)editor,false); 							
						editor = (DesignViewEditor)IDE.openEditor(page, gViewCached, true);
						if(editor != null)
							editor.setSelectionSynchronizerExternal(self);													
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}									
			}
		}
	}

	/**
     * A property in the ArchE user preferences has changed. Here we pay heed to the property that
     * defines the color for information whose source is ArchE.
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
       
    }
	/*
	 * @see IWorkbenchPart#setFocus()
	 */
	public void setFocus() {}
	
	

}


