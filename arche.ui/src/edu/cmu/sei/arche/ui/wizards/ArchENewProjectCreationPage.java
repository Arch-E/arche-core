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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * ArchE New Project wizard. This class is the only page of the ArchE project creation wizard.
 * Methods in this class are called by ArchENewProjectWizard. This class extends the standard
 * WizardNewProjectCreationPage, which is the same dialog box we see in Eclipse when we select File |
 * New | Project | Simple | Project.
 * 
 * @author Paulo Merson
 */
public class ArchENewProjectCreationPage extends WizardNewProjectCreationPage {

    /** folder of sample dat files are located within lib plugin */
    public static final String SAMPLES_FOLDER                   = "core/samples/";

    /** If selected will create an empty project. */
    private Button             blankProjectRadioButton;

    /** If selected will create a project pre-populated with sample data. */
    private Button             sampleDataRadioButton;

    private Label              fileLabel;

    private Combo              sampleProject;

    private Button             sampleFileBrowseButton;

    /** cache of newly-created project */
    private IProject           newProject;
    
    private String			   projectName;

    /**
     * Creates the (only) page for the new ArchE project wizard.
     * 
     * @param name just a string that identifies the page.
     */
    public ArchENewProjectCreationPage(String name) {
        super(name);
        this.setTitle("ArchE Project");
        this.setDescription("A project will contain scenarios, responsibilities and other "
                + "data elements.");
        
    }

    /**
     * A WizardNewProjectCreationPage has already widgets to let the user specify the project name
     * and folder. This methods calls createControl on the super class to do exactly that and then
     * adds the other widgets that are specific to ArchE. Method declared on IDialogPage.
     * 
     * @param parent the parent control
     */
    public void createControl(Composite parent) {
        // inherit default container and name specification widgets
        super.createControl(parent);
        Composite composite = (Composite) getControl();
               
        // Group to select blank project or pre-populated
        Group initialDataGroup = new Group(composite, SWT.NONE);
        initialDataGroup.setLayout(new GridLayout(3, false));
        initialDataGroup.setText("Initial data for the new project");
        initialDataGroup.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));

        blankProjectRadioButton = new Button(initialDataGroup, SWT.RADIO);
        blankProjectRadioButton.setText("Blank project");
        blankProjectRadioButton.setToolTipText("Create a project with no data.");
        GridData gd = new GridData();
        gd.horizontalSpan = 3;
        blankProjectRadioButton.setLayoutData(gd);
        blankProjectRadioButton.setSelection(true);
//        blankProjectRadioButton.addSelectionListener(new SelectionAdapter() {
//
//            public void widgetSelected(SelectionEvent e) {
//                pageChanged();
//            }
//        });

        sampleDataRadioButton = new Button(initialDataGroup, SWT.RADIO);
        sampleDataRadioButton.setText("Sample project");
        sampleDataRadioButton
                .setToolTipText("Create a project pre-populated with sample scenarios, functions, responsibilities and other data elements.");
        sampleDataRadioButton.setSelection(false);
        gd = new GridData();
        gd.horizontalSpan = 1;
        sampleDataRadioButton.setLayoutData(gd);
        sampleDataRadioButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                pageChanged();
            }
        });

        // project name for the sample fact base
//        fileLabel = new Label(initialDataGroup, SWT.NULL);
//        fileLabel.setText("Sample project:");
        sampleProject = new Combo(initialDataGroup, SWT.SINGLE | SWT.BORDER |SWT.READ_ONLY);
        gd = new GridData();
        gd.horizontalSpan = 2;
        sampleProject.setLayoutData(gd);
        sampleProject.add("ctas_sample");
        sampleProject.select(0);
//        sampleProject.add("ctas_2");
//        sampleProject.add("moditest");

//        File[] sampleFiles = getSampleFiles();
//        String suggestedFile = null;
//        for (int i = 0; i < sampleFiles.length; i++) {
//            suggestedFile = sampleFiles[i].getAbsolutePath();
//            sampleFactBaseFile.add(suggestedFile);
//        }
//        sampleFactBaseFile.setText(suggestedFile); // last one on the list
        
//        sampleProject.setText("moditest"); // last one on the list
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        data.widthHint = 250;
        sampleProject.setLayoutData(data);
        sampleProject.addListener(SWT.Modify, new Listener() {

            public void handleEvent(Event e) {
            	
            	// get projectNameField
                Control[] clist = ((Composite)getControl()).getChildren();
                Control[] clist2 = ((Composite)clist[0]).getChildren();    	
                
                // update the project name being created with the same project name selected.
               ((Text)clist2[1]).setEnabled(false);    	        	
               ((Text)clist2[1]).setText(sampleProject.getText());
               
                // validate page
                boolean valid = validatePage();
                setPageComplete(valid);
            }
        });
        
        
//        sampleFactBaseFile.addListener(SWT.Modify, sampleFactBaseFileModifyListener);

        // destination browse button
//        sampleFileBrowseButton = new Button(initialDataGroup, SWT.PUSH);
//        sampleFileBrowseButton.setText("Browse...");
//        //        sampleFileBrowseButton.addListener(SWT.Selection, this);
//        setButtonLayoutData(sampleFileBrowseButton);
//        sampleFileBrowseButton.addSelectionListener(new SelectionAdapter() {
//
//            public void widgetSelected(SelectionEvent e) {
//                handleBrowseButtonPressed();
//            }
//        });

        projectName = getProjectName();        
        pageChanged();
    }

    /**
     * @return array of File objects of the files in the sample folder
     */
//    public static File[] getSampleFiles() {
//        URL libURL = Platform.getPluginRegistry().getPluginDescriptor("SEI.ArchE.Lib")
//                .getInstallURL();
//
//        String sampleFolderStr = null;
//        try {
//            URL sampleFolderURL = new URL(libURL, SAMPLES_FOLDER);
//            sampleFolderStr = Platform.resolve(sampleFolderURL).getPath();
//        } catch (IOException e) {
//            // nothing to do. just don't add the suggested files to the combo list
//        }
//        File sampleFolder = new File(sampleFolderStr);
//        File[] sampleFiles = sampleFolder.listFiles(new FileFilter() {
//
//            // only files are listed
//            public boolean accept(File arg0) {
//                if (arg0.isFile()) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        return sampleFiles;
//    }

    /**
     * Called by the wizard class to check if it's all right to enable the finish button.
     * 
     * @return true if finish can be enabled or false otherwise.
     */
    public boolean canFinish() {
        String projName = this.getProjectName();
        if (projName == null || projName.trim().length() == 0) {
            return false;
        }
//        if (sampleDataRadioButton.getSelection()) {
//            // will copy from sample file--it has to exist
//            File f = new File(sampleFactBaseFile.getText());
//            if (!f.exists()) {
//                setErrorMessage(null);
//                setMessage("Sample fact base file does not exist.");
//                return false;
//            }
//        }
        return true;
    }

    /**
     * Called when the finish button is clicked.
     * 
     * @return true if the project could be created or null otherwise.
     */
    public boolean finish() {
        createNewProject();
        return (newProject == null) ? false : true;
    }

    /**
     * Simply enable/disable the widgets to pick the name of the sample file depending on the radio
     * button selection.
     */
    private void pageChanged() {
    	// get projectNameField
        Control[] clist = ((Composite)getControl()).getChildren();
        Control[] clist2 = ((Composite)clist[0]).getChildren();    	
        
        // update the project name being created with the same project name selected.
        if(sampleDataRadioButton.getSelection()){
            ((Text)clist2[1]).setEnabled(false);    	        	
            projectName = ((Text)clist2[1]).getText();
            ((Text)clist2[1]).setText(sampleProject.getText());
            sampleProject.setEnabled(true);
        }else{
            ((Text)clist2[1]).setEnabled(true);    	        	
            ((Text)clist2[1]).setText(projectName);        	
            sampleProject.setEnabled(false);
        }        
        setPageComplete(validatePage());
    }

    /**
     * User clicked browse button. Open a FileDialog so the user can select the file to be used to
     * copy from as the sample persisted fact base.
     */
//    private void handleBrowseButtonPressed() {
//        String path;
//        String file;
//        String txt = sampleFactBaseFile.getText();
//        int lastBarPos = txt.lastIndexOf(System.getProperty("file.separator"));
//        if (lastBarPos == -1) {
//            // it's empty or just a file name
//            path = "";
//            file = sampleFactBaseFile.getText();
//        } else {
//            path = txt.substring(0, lastBarPos);
//            if (path.length() == 0) {
//                path = System.getProperty("file.separator"); // root folder
//            }
//            file = txt.substring(lastBarPos + 1, txt.length());
//        }
//        FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.OPEN);
//        //        dialog.setFileName("abcdefg.xmi");
//        //        dialog.setFileName(destinationNameField.getText());
//        dialog.setFileName(file);
//        String extensions[] = { "*.dat", "*.*"};
//        dialog.setFilterExtensions(extensions);
//        String filterNames[] = { "Persisted fact base (*.dat)", "All files (*.*)"};
//        dialog.setFilterNames(filterNames);
//        dialog.setText("Select persisted fact base");
//        dialog.setFilterPath(path);
//        String fileName = dialog.open();
//        if (fileName != null) {
//            setErrorMessage(null);
//            sampleFactBaseFile.setText(fileName);
//        }
//    }

    /**
     * Create the IProject object and then calls createProject to create resources (folders and
     * files) of the new project.
     */
    private void createNewProject() {
        if (newProject != null) {
            return;
        }

        // get a project handle
        final IProject newProjectHandle = this.getProjectHandle();

        // get a project descriptor
        IPath newPath = null;
        if (!this.useDefaults()) {
//        	String temp = this.getLocationPath().toString();
 //       	temp = temp + "/" + this.getProjectName();
  //      	newPath = new Path(temp);           
        	newPath = this.getLocationPath().append(getProjectName()); 
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription description = workspace.newProjectDescription(newProjectHandle
                .getName());
        description.setLocation(newPath);

        // create the new project operation (required to properly make changes to workspace)
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

            protected void execute(IProgressMonitor monitor) throws CoreException {
                try {
                    String factFileToCopy = null;
                    if (sampleDataRadioButton.getSelection()) {
                        factFileToCopy = sampleProject.getText();
                    }
                    createProject(description, newProjectHandle, monitor, factFileToCopy);
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
     * @param createBlank true to create a blank project with no pre-populated data; false to copy a
     *            file that contains a persisted sample fact base that will be copied as the
     *            factbase for the new project.
     * @exception CoreException if the operation fails
     * @exception OperationCanceledException if the operation is canceled
     */
    public static void createProject(IProjectDescription description, IProject projectHandle,
            IProgressMonitor monitor, String factFileToCopy) throws CoreException,
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

}