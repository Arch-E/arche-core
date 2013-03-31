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

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import edu.cmu.sei.arche.ArchEUIPlugin;

/**
 * Entry point of the wizard to export design to SQL (.sql for MySQL). Uses the ExportToSQLWizardPage.
 * 
 * @author Hyunwoo Kim
 */
public class ExportToSQLWizard extends Wizard implements IExportWizard {

    private IWorkbench             workbench;

    private IProject               project;

    private ExportToSQLWizardPage mainPage;

    private boolean                hasToCancel = false;

    /**
     * Creates an ExportToSQLWizard instance and set dialog settings based on plugin's settings.
     */
    public ExportToSQLWizard() {
        super();
        setDialogSettings(ArchEUIPlugin.getDefault().getDialogSettings());
    }

    /**
     * Initializes this export wizard, canceling the operation if there's no project open.
     * 
     * @param workbench the current workbench
     * @param selection current object selection
     * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        //        this.selection = selection;
        // Despite the selection, we'll export the only project that is opened
        project = ArchEUIPlugin.getDefault().getOpenProject();
        if (project == null) {
            MessageDialog.openInformation(null, "Export Design",
                                          "Please open or create an ArchE project first.");
            //this.performCancel();
            this.dispose();
            hasToCancel = true;

            return;
        }

        setDefaultPageImageDescriptor(getImage());

        // settings
        setWindowTitle("Export Design to a SQL File");
        setNeedsProgressMonitor(true);
    }

    /**
     * Add pages to the wizard. This wizard has just one page.
     * 
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        if (hasToCancel) {
            this.performCancel();
            return;
        }
        super.addPages();
        mainPage = new ExportToSQLWizardPage("main", project);
        addPage(mainPage);
    }

    /**
     * Getter for current workbench.
     * 
     * @return IWorkbench object
     */
    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * Goes over all the trouble to get the image gif to show in the dialog.
     * 
     * @return ImageDescriptor
     */
    private ImageDescriptor getImage() {
        URL baseUrlPlugin = ArchEUIPlugin.getDefault().getDescriptor().getInstallURL();
        URL imgUrl = null;
        try {
            imgUrl = new URL(baseUrlPlugin, "icons/RoseExport.gif");
        } catch (MalformedURLException ex) {
            return null;
        }
        return ImageDescriptor.createFromURL(imgUrl);
    }

    /**
     * Calls finish() on the main page to finish the wizard and create the project resources.
     * 
     * @return true to indicate the finish request was accepted, and false to indicate that the
     *         finish request was refused
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish() {
        return mainPage.finish();
    }

}