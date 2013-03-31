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

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import edu.cmu.sei.ORM.main.ArcheModel;
import edu.cmu.sei.designview.layouts.DecompositionViewLayout;
import edu.cmu.sei.designview.layouts.DependencyViewLayout;
import edu.cmu.sei.designview.layouts.ViewLayout;
import edu.cmu.sei.designview.model.DesignViewDiagram;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseTree;

/**
 * This WizardPage can create an empty .aview file for the AViewEditor.
 * 
 * @author Hyunwoo Kim
 * @date July 26, 2006
 */

public class DesignViewCreationPage extends WizardNewFileCreationPage {
	private final IWorkbench workbench;
	private static int fileCount = 1;
	private String versionName;
	private String rootFactId;
	private int rootFactType;
	private String selectedViewType;
	private String selectedLayoutType;
	private ArcheModel model = ArcheModel.GetInstance();
	
	/**
	 * Create a new wizard page instance.
	 * @param workbench the current workbench
	 * @param selection the current object selection
	 * @see ShapesCreationWizard#init(IWorkbench, IStructuredSelection)
	 */
	DesignViewCreationPage(IWorkbench workbench, IStructuredSelection selection) {
		super("AViewCreationPage", selection);
		this.workbench = workbench;
		setTitle("ArchE View ");
		setDescription("Create a new " + DesignViewEditor.DEFAULT_EXTENSION + " file");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		setFileName("AViewExample" + fileCount + DesignViewEditor.DEFAULT_EXTENSION);
		setPageComplete(validatePage());
	}
	
	
	/** Return a new AViewDiagram instance. */
	private Object createDefaultContent() {
		DesignViewDiagram aviewDiagram =  null;
		
		if (selectedViewType.equalsIgnoreCase(ViewLayout.DECOMPOSITION_VIEW)){
//			System.out.println(System.currentTimeMillis());
			SparseTree archTree = model.getFullDecompositionArcheModel(versionName, rootFactId, false); 			
//			System.out.println(System.currentTimeMillis());
	
			DecompositionViewLayout	layout = new DecompositionViewLayout();
			aviewDiagram = layout.generateTreeLayout(archTree);
		}
		else if (selectedViewType.equalsIgnoreCase(ViewLayout.DEPENDENCY_VIEW)){
//			System.out.println(System.currentTimeMillis());
			SparseGraph archGraph = model.getFullDependencyArcheModel(versionName, rootFactId, false, rootFactType); 				
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
			aviewDiagram.setRootNodeFactId(rootFactId);
			aviewDiagram.setRootNodeFactType(rootFactType);
			aviewDiagram.setSelectedViewType(selectedViewType);
			aviewDiagram.setSelectedLayoutType(selectedLayoutType);			
		}
		
		return aviewDiagram;
	}
	
	/**
	 * This method will be invoked, when the "Finish" button is pressed.
	 * @see DesignViewCreationWizard#performFinish()
	 */
	boolean finish(String versionName, String rootFactId, String selectedViewType, String selectedLayoutType) {
		this.versionName = versionName;
		this.rootFactId = rootFactId;
		this.rootFactType = DesignViewPlugin.ROOT_FACT_TYPE_MODULE;
		this.selectedViewType = selectedViewType;
		this.selectedLayoutType = selectedLayoutType;
		
		// create a new file, result != null if successful
		IFile newFile = createNewFile();
		fileCount++;
		
		// open newly created file in the editor
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		if (newFile != null && page != null) {
			try {
				IDE.openEditor(page, newFile, true);
			} catch (PartInitException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getInitialContents()
	 */
	protected InputStream getInitialContents() {
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(createDefaultContent()); // argument must be Serializable
			oos.flush();
			oos.close();
			bais = new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return bais;
	}
	
	/**
	 * Return true, if the file name entered in this page is valid.
	 */
	private boolean validateFilename() {
		if (getFileName() != null && getFileName().endsWith(DesignViewEditor.DEFAULT_EXTENSION)) {
			return true;
		}
		setErrorMessage("The 'file' name must end with " + DesignViewEditor.DEFAULT_EXTENSION);
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validatePage()
	 */
	protected boolean validatePage() {
		return super.validatePage() && validateFilename();
	}
	
	
	
}