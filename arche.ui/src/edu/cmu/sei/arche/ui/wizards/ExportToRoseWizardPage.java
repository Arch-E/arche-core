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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.export.ExportToRose;

/**
 * Simple page that asks for the name of the file to export and calls the export program.
 * 
 * @author Paulo Merson
 */
public class ExportToRoseWizardPage extends WizardPage implements Listener {

    private IProject project;

    // widgets
    private Combo    destinationNameField;

    private Button   destinationBrowseButton;

    /**
     * @param pageName
     * @param selection
     */
    protected ExportToRoseWizardPage(String pageName, IProject project) {
        super(pageName);
        this.project = project;
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
        destinationLabel.setText("Export to:");

        // destination name entry field
        destinationNameField = new Combo(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);

        String suggestedFile = getDefaultExportFileName();
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

        ExportToRose exportToRose = new ExportToRose(ArchEUIPlugin.getDefault().getArchEFacade());
        try {
            exportToRose.createArchEUML(destinationNameField.getText());
            MessageDialog.openInformation(null, "Export design",
                                          "ArchE design is successfully exported!");
            result = true;
        } catch (Exception e) {
            //MessageDialog.openInformation(null, "Export design",
            //                              "An error occurs for export design to "
            //                                      + destinationNameField.getText());
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * User clicked browse button. Open a FileDialog so the user can specify the file to save the
     * exported design.
     */
    private void handleBrowseButtonPressed() {
        String path;
        String file;
        String txt = destinationNameField.getText();
        int lastBarPos = txt.lastIndexOf(System.getProperty("file.separator"));
        if (lastBarPos == -1) {
            // it's empty or just a file name
            txt = getDefaultExportFileName();
            path = txt.substring(0, txt.lastIndexOf(System.getProperty("file.separator")));
            if (destinationNameField.getText().trim().length() == 0) {
                // it's empty
                lastBarPos = txt.lastIndexOf(System.getProperty("file.separator"));
                file = txt.substring(lastBarPos + 1, txt.length());
            } else {
                // it's just a file name
                file = destinationNameField.getText();
            }
        } else {
            path = txt.substring(0, lastBarPos);
            if (path.length() == 0) {
                path = System.getProperty("file.separator"); // root folder
            }
            file = txt.substring(lastBarPos + 1, txt.length());
        }
        FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE);
        dialog.setFileName(file);
        String extensions[] = { ".xml", "*.*"};
        dialog.setFilterExtensions(extensions);
        String filterNames[] = { "XMI UML file (*.xml)", "All files (*.*)"};
        dialog.setFilterNames(filterNames);
        dialog.setText("Save Exported Design As");
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
            setMessage("Click finish and the design will be exported to the given file.");
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
     * Helper method that returns a String with the full path name of the default exported file,
     * which is a .xmi file with the same name of the project in the design folder inside the
     * project.
     * 
     * @return
     */
    private String getDefaultExportFileName() {
        IFile exportedFile = project.getFile("design/" + project.getName() + ".xml");
        IPath fullPath = Platform.getLocation();
        fullPath = fullPath.append(exportedFile.getFullPath());
        return fullPath.toOSString();
    }
}