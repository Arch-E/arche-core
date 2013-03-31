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

package edu.cmu.sei.arche.ui.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import edu.cmu.sei.arche.export.ImportFromSQL;

/**
 * Simple page that asks for the name of the file to import and calls the import program.
 * 
 * @author Hyunwoo Kim
 */
public class ImportFromSQLWizardPage extends WizardPage implements Listener {

	// widgets
    private Combo    destinationNameField;

    private Button   destinationBrowseButton;

    /** cache of newly-created project */
    private IProject           newProject;
    
    
    /**
     * @param pageName
     * @param selection
     */
    protected ImportFromSQLWizardPage(String pageName) {
        super(pageName);
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
                | GridData.HORIZONTAL_ALIGN_FILL));
        composite.setFont(parent.getFont());

        // destination specification group
        Composite destinationSelectionGroup = new Composite(composite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        destinationSelectionGroup.setLayout(layout);
        destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.VERTICAL_ALIGN_FILL));

        Label destinationLabel = new Label(destinationSelectionGroup, SWT.NONE);
        destinationLabel.setText("Import from:");

        // destination name entry field
        destinationNameField = new Combo(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);

        String suggestedFile = getDefaultImportFileName();
        destinationNameField.setText(suggestedFile);
        destinationNameField.add(suggestedFile);
        destinationNameField.addListener(SWT.Modify, this);
        destinationNameField.addListener(SWT.Selection, this);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        data.widthHint = 400;
        destinationNameField.setLayoutData(data);

        // destination browse button
        destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
        destinationBrowseButton.setText("Browse...");
        destinationBrowseButton.addListener(SWT.Selection, this);
        setButtonLayoutData(destinationBrowseButton);

        setControl(composite);

        updatePageCompletion();
        destinationNameField.setFocus();
    }

    /**
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        Widget source = event.widget;
        if (source == destinationBrowseButton) {
            handleBrowseButtonPressed();
        }
        updatePageCompletion();
    }

    /**
     * Export the design here!
     * 
     * @return
     */
    public boolean finish() {
        boolean result = false;

        ImportFromSQL importFromSQL = new ImportFromSQL();
        try {
        	String temp  = destinationNameField.getText();
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
        	
	    	String projectName = sqlFilePath.substring(sqlFilePath.lastIndexOf("/")+1, sqlFilePath.lastIndexOf(".sql"));	    	 

	    	// get a project handle
	        IProject newProjectHandle = this.getProjectHandle(projectName);
	    	
            if(newProjectHandle.exists()){
                MessageDialog.openWarning(Display.getDefault().getActiveShell(), "ArchE",
                        "A project named '" +  newProjectHandle.getName() + "' already exists in the workspace.");
               	return false;
            }
            
	    	importFromSQL.importFrom(projectName,sqlFilePath,"Project","Project");

            MessageDialog.openInformation(null, "Import design",
            "ArchE design is successfully imported!");
	    	
        	// Create a ArchE project in the current workspace
	    	createNewProject(newProjectHandle);	    	
            	    	
            result = true;
        } catch (Exception e) {
            //MessageDialog.openInformation(null, "Import design",
            //                              "An error occurs for import design from "
            //                                      + destinationNameField.getText());
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * User clicked browse button. Open a FileDialog so the user can specify the file to import the
     * SQL file.
     */
    private void handleBrowseButtonPressed() {
        String path;
        String file;
        path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
        
        FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.OPEN);
//        dialog.setFileName(file);
        String extensions[] = { "*.sql", "*.*"};
        dialog.setFilterExtensions(extensions);
        String filterNames[] = { "SQL Backup files (*.sql)", "All files (*.*)"};
        dialog.setFilterNames(filterNames);
        dialog.setText("Import ArchE Design From");
        dialog.setFilterPath(path);
        //        dialog.setFilterPath(destinationNameField.getText().trim());
        String fileName = dialog.open();
        if (fileName != null) {
            setErrorMessage(null);
            destinationNameField.setText(fileName);
        }
    }

    /**
     * Determine if the page is complete and update the message on top.
     */
    private void updatePageCompletion() {
        boolean pageComplete = validateDestinationGroup();
        setPageComplete(pageComplete);
        if (pageComplete) {
            setMessage("Click finish and the design will be imported from the given file.");
        }
    }

    /**
     * Check if there's any filename in the text box.
     */
    private boolean validateDestinationGroup() {
        String destinationValue = destinationNameField.getText().trim();
        if (destinationValue.length() == 0) {
            setMessage("Please enter the destination filename");
            return false;
        }
        //        setErrorMessage("");
        return true;
    }

    /**
     * Helper method that returns a String with the full path name of the default imported file,
     * which is a .sql file with the same name of the project in the design folder inside the
     * project.
     * 
     * @return
     */
    private String getDefaultImportFileName() {
//        IFile exportedFile = project.getFile("design/" + project.getName() + ".sql");
//        IPath fullPath = Platform.getLocation();
//        fullPath = fullPath.append(exportedFile.getFullPath());
//        return fullPath.toOSString();
    	return "";
    }
    
    /**
     * Create the IProject object and then calls createProject to create resources (folders and
     * files) of the new project.
     */
    private void createNewProject(final IProject newProjectHandle) {
        if (newProject != null) {
            return;
        }

        // get a project descriptor
        IPath newPath = null;
//        if (!this.useDefaults()) {
//        	String temp = this.getLocationPath().toString();
 //       	temp = temp + "/" + this.getProjectName();
  //      	newPath = new Path(temp);           
//        	newPath = this.getLocationPath().append(getProjectName()); 
//        }
        	
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription description = workspace.newProjectDescription(newProjectHandle
                .getName());
//        description.setLocation(newPath);

        // create the new project operation (required to properly make changes to workspace)
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

            protected void execute(IProgressMonitor monitor) throws CoreException {
                try {
                    createProject(description, newProjectHandle, monitor);
                } catch (OperationCanceledException e) {
                    throw new RuntimeException("Could not create ArchE project", e);
                } catch (IOException e) {
                    throw new RuntimeException("Could not create ArchE project", e);
                }
            }
        };

        // run the new project creation operation
        try {
            getContainer().run(false, true, op); // run execute() in op in a separate thread
        } catch (InterruptedException ex) {
            return;
        } catch (InvocationTargetException ex) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), "ArchE",
                                    "Error creating new project\n\nError:\n"
                                            + ex.getCause().getMessage());
            return;
        }
        newProject = newProjectHandle;
    }

    /**
     * Creates project resources given the project handle and description. This method is public so
     * it can be used by the JUnit PDE test class.
     * 
     * @param description the project description to create a project resource for
     * @param projectHandle the project handle to create a project resource for
     * @param monitor the progress monitor to show visual progress with
     * @exception CoreException if the operation fails
     * @exception OperationCanceledException if the operation is canceled
     */
    public static void createProject(IProjectDescription description, IProject projectHandle,
            IProgressMonitor monitor) throws CoreException,
            OperationCanceledException, IOException {
        try {
            monitor.beginTask("", 4000);

//            System.out.println("[ArchENewProjectCreationPage] will create project");

            // Set ArchE nature to the project description
            String[] natures = description.getNatureIds();
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = "SEI.ArchE.UI.ArchENature";
            description.setNatureIds(newNatures);
            
            if(projectHandle.exists()){
//                if(projectHandle.isOpen()){
//                	projectHandle.close(monitor);
//                    try {
//    					Thread.sleep(20000);
//    				} catch (InterruptedException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}                
//                }                
               	projectHandle.delete(true, monitor);                       	
            }
            
            // Now will actually create a project with the given description and add it to
            // the workspace. The project is initially closed.
            projectHandle.create(description, new SubProgressMonitor(monitor, 500));

            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }

//            System.out.println("[ArchENewProjectCreationPage] will open project");
            // Open the project
            projectHandle.open(new SubProgressMonitor(monitor, 500));

            // add a subfolder to the project folder
            IFolder subdir = projectHandle.getFolder("design");
            if (!subdir.exists()) {
                subdir.create(true, true, new SubProgressMonitor(monitor, 500));
            }
            
//            System.out.println("[ArchENewProjectCreationPage] new project full path: "
//                    + projectHandle.getFullPath().toString());            
        } finally {
            monitor.done();
        }
    }    
    
    public IProject getProjectHandle(String projectName) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    }
    
}