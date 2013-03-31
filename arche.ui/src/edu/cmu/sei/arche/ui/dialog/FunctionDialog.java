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

import java.util.Iterator;

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
import org.eclipse.ui.help.WorkbenchHelp;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.vo.FunctionVO;
import edu.cmu.sei.arche.vo.ProjectVO;

/**
 * Dialog box where the user can add a new function or edit an existing one.
 * 
 * @author Henry Chen
 */
public class FunctionDialog extends Dialog {

    /** The VO object being edited or <code>null</code> for a new one. */
    private FunctionVO vo;

    /** The id text box for Function. */
    private Text       functionIdText;

    /** The description text box for Function. */
    private Text       functionDescText;

    /** Project that is currently open */
    private ProjectVO  project;

    /**
     * Creates the dialog. This constructor is used when we want to create a new item rather than
     * edit and existing one.
     * 
     * @param shell the parent shell
     */
    public FunctionDialog(Shell parentShell) {
        this(parentShell, null);
    }

    /**
     * Creates the dialog. This constructor is used when we want to edit a new item (if vo parameter
     * is not null) rather than creating a new one.
     * 
     * @param shell the parent shell
     * @param vo the VO object that will be edited in this dialog (if null, it's creating a new)
     */
    public FunctionDialog(Shell parentShell, FunctionVO vo) {
        super(parentShell);
        this.vo = vo;
    }

    /**
     * Method declared on Window. Set the title of the dialog box.
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (vo == null) {
            newShell.setText("New Function");
        } else {
            newShell.setText("Edit Function");
        }
        WorkbenchHelp.setHelp(newShell, "SEI.ArchE.UI.functionDlgBox");
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

        // FUNCTION LABEL
        Label label = new Label(composite, SWT.NONE);
        label.setText("Function");
        label.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        gd.verticalSpan = 2;

        // ID LABEL AND TEXT BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Id");
        label.setFont(font);
        functionIdText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        if (vo != null) {
            functionIdText.setText(vo.getId());
        }
        functionIdText.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        //gd.verticalSpan = 2;
        functionIdText.setLayoutData(gd);

        // DESCRIPTION LABEL AND TEXT BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Description");
        label.setFont(font);
        functionDescText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        if (vo != null) {
            functionDescText.setText(vo.getDescription());
        }
        functionDescText.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        //gd.verticalSpan = 2;
        functionDescText.setLayoutData(gd);
    }

    /**
     * Check that both a function and function were selected from the list. Then save the mapping to
     * the core. Method declared on Dialog.
     */
    public void okPressed() {
        if (functionIdText.getText().trim().equals("")) {
            MessageDialog.openError(getShell(), "Error", "Please enter the id of function.");
            functionIdText.setFocus();
            return;
        }
        else{
        	try{
            	double dID = Double.parseDouble(functionIdText.getText().trim());
        	} catch(NumberFormatException e){
                MessageDialog.openError(getShell(), "Error", "Please enter the id of function following one of the formats: integer or integer.integer");
                functionIdText.setFocus();
        		return;
        	}        	
        }
        

        if (functionDescText.getText().trim().equals("")) {
            MessageDialog.openError(getShell(), "Error",
                                    "Please enter the description of function.");
            functionDescText.setFocus();
            return;
        }
        if (saveChanges()) {
            super.okPressed();
        }

    }

    /**
     * Saves the changes made in the dialog to the core. Creates a new function or edit the existing
     * one.
     */
    private boolean saveChanges() {
        ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
        //Check whether the function already existed in the core
        if (vo == null || !vo.getId().equalsIgnoreCase(functionIdText.getText().trim())) {

            Iterator it = project.getFunctions().iterator();
            while (it.hasNext()) {
                FunctionVO funcVo = (FunctionVO) it.next();
                if (funcVo.getId().equalsIgnoreCase(functionIdText.getText().trim())) {
                    MessageDialog
                            .openError(getShell(), "Error",
                                       "There is already a function with the same id. Please change it.");
                    functionIdText.setFocus();
                    return false;
                }
            }
        }
        if (vo == null) {
            // add new
            facade.addFunction(functionIdText.getText(), functionDescText.getText());

        } else {
            // edit existing if changed
            facade.editFunction(vo.getFactId(), functionIdText.getText(), functionDescText
                    .getText());
        }
        return true;
    }

    /**
     * @return Returns the functionDescText.
     */
    public Text getFunctionDescText() {
        return functionDescText;
    }

    /**
     * @return Returns the functionIdText.
     */
    public Text getFunctionIdText() {
        return functionIdText;
    }

}