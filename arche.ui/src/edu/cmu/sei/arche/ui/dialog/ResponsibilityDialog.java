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

package edu.cmu.sei.arche.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;

/**
 * Dialog box where the user can add a new responsibility or edit an existing one.
 * 
 * @author Henry Chen
 */
public class ResponsibilityDialog extends Dialog {

    /** The VO object being edited or <code>null</code> for a new one. */
    private ResponsibilityVO vo;

    /** The description text box for responsibility. */
    private Text             responsibilityDescription;

    /** The description text box for responsibility. */
    private Text             responsibilityName;

    /** Project that is currently open */
    private ProjectVO        project;

    /**
     * Creates the dialog. This constructor is used when we want to create a new item rather than
     * edit and existing one.
     * 
     * @param shell the parent shell
     */
    public ResponsibilityDialog(Shell parentShell) {
        this(parentShell, null);
    }

    /**
     * Creates the dialog. This constructor is used when we want to edit a new item (if vo parameter
     * is not null) rather than creating a new one.
     * 
     * @param shell the parent shell
     * @param vo the VO object that will be edited in this dialog (if null, it's creating a new)
     */
    public ResponsibilityDialog(Shell parentShell, ResponsibilityVO vo) {
        super(parentShell);
        this.vo = vo;
    }

    /**
     * Method declared on Window. Set the title of the dialog box.
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (vo == null) {
            newShell.setText("New Responsibility");
        } else {
            newShell.setText("Edit Responsibility");
        }
    }

    /**
     * Method declared on Dialog. Set the area with widgets between title and buttons.
     */
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        initializeDialogUnits(composite);
        project = ArchEUIPlugin.getDefault().getProjectVo();
        if (project == null) {
            MessageDialog.openError(getShell(), "Error", "No ArchE project is opened!");
            close();
            return null;
        }
        createDescriptionArea(composite);
        return composite;
    }

    /**
     * Creates only the OK and Cancel buttons.
     */
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Creates the area with the widgets used to enter data.
     */
    private void createDescriptionArea(Composite parent) {
        Font font = parent.getFont();
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd.horizontalAlignment = GridData.FILL;
        gd.widthHint = 500;
        gd.heightHint = 150;
        composite.setLayoutData(gd);

        // RESPONSIBILITY LABEL
        Label label = new Label(composite, SWT.NONE);
        label.setText("Responsibility");
        label.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        gd.verticalSpan = 2;

        // NAME LABEL AND TEXT BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Name");
        label.setFont(font);
        responsibilityName = new Text(composite, SWT.SINGLE | SWT.BORDER);
        if (vo != null) {
            responsibilityName.setText(vo.getName());
        }

        responsibilityName.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        responsibilityName.setLayoutData(gd);

        // DESCRIPTION LABEL AND TEXT BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Description");
        label.setFont(font);
        responsibilityDescription = new Text(composite, SWT.SINGLE | SWT.BORDER);
        if (vo != null) {
            responsibilityDescription.setText(vo.getDescription());
        }

        responsibilityDescription.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        responsibilityDescription.setLayoutData(gd);
    }

    /**
     * Check that both a responsibility and responsibility were selected from the list. Then save
     * the mapping to the core. Method declared on Dialog.
     */
    protected void okPressed() {
        if (responsibilityName.getText().trim().equals("")) {
            MessageDialog.openError(getShell(), "Error",
                                    "Please enter the name of responsibility.");
            responsibilityName.setFocus();
            return;
        }
        saveChanges();
        super.okPressed();
    }

    /**
     * Saves the changes made in the dialog to the core. Creates a new responsibility or edit the
     * existing one.
     */
    private void saveChanges() {
        ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
        if (vo == null) {
            // add new
            facade.addResponsibility(responsibilityName.getText(), responsibilityDescription.getText());

        } else {
            // edit existing if changed
            facade.editResponsibility(vo, responsibilityName.getText(), responsibilityDescription.getText());
        }
    }

}