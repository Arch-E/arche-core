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

import java.util.Enumeration;
import java.util.Iterator;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;
import edu.cmu.sei.arche.vo.ScenarioResponsibilityMapVO;
import edu.cmu.sei.arche.vo.ScenarioVO;

/**
 * Dialog box where the user can add a new scenario-responsibility mapping or edit an existing one.
 * 
 * @author Paulo Merson
 */
public class ScenarioRespMappingDialog extends Dialog {

    /** The VO object being edited or <code>null</code> for a new one. */
    private ScenarioResponsibilityMapVO vo;

    /** The combo box widget for scenarios. */
    private Combo                       scenariosCombo;

    /** The combo box widget for responsibilities. */
    private Combo                       respsCombo;

    /** Scenarios for combo box display */
    private String[]                    scenarios;

    /** Scenario fact IDs, in the order matching the text in scenarios[] */
    private int[]                       scenarioFactIds;

    /** Responsibilities for combo box display * */
    private String[]                    respos;

    /** Responsibility fact IDs, in the order matching the text in respos[] */
    private int[]                       respFactIds;

    /** Project that is currently open */
    private ProjectVO                   project;

    /**
     * Creates the dialog. This constructor is used when we want to create a new item rather than
     * edit and existing one.
     * 
     * @param shell the parent shell
     */
    public ScenarioRespMappingDialog(Shell parentShell) {
        this(parentShell, null);
    }

    /**
     * Creates the dialog. This constructor is used when we want to edit a new item (if vo parameter
     * is not null) rather than creating a new one.
     * 
     * @param shell the parent shell
     * @param vo the VO object that will be edited in this dialog (if null, it's creating a new)
     */
    public ScenarioRespMappingDialog(Shell parentShell, ScenarioResponsibilityMapVO vo) {
        super(parentShell);
        this.vo = vo;
    }

    /**
     * Method declared on Window. Set the title of the dialog box.
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (vo == null) {
            newShell.setText("New Scenario-Responsibility Mapping");
        } else {
            newShell.setText("Edit Scenario-Responsibility Mapping");
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
        gd.heightHint = 250;
        composite.setLayoutData(gd);

        // SCENARIO LABEL AND COMBO BOX
        Label label = new Label(composite, SWT.NONE);
        label.setText("Scenario");
        label.setFont(font);
        int toSelect = populateScenarios();
        scenariosCombo = new Combo(composite, SWT.READ_ONLY);
        scenariosCombo.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        gd.verticalSpan = 2;
        scenariosCombo.setLayoutData(gd);
        scenariosCombo.setItems(scenarios);
        if (toSelect >= 0) {
            scenariosCombo.select(toSelect);
        }

        // RESPONSIBILITY LABEL AND COMBO BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Responsibility");
        label.setFont(font);
        toSelect = populateResponsibilities();
        respsCombo = new Combo(composite, SWT.READ_ONLY);
        respsCombo.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        respsCombo.setLayoutData(gd);
        respsCombo.setItems(respos);
        if (toSelect >= 0) {
            respsCombo.select(toSelect);
        }
    }

    /**
     * Populate the Scenarios descripitions into member variables scenarios and scenarioFactIds
     * 
     * @return index of the scenario of the mapping being edited; -1 if creating a new mapping
     */
    private int populateScenarios() {
        int size = project.getScenarios().size();
        scenarios = new String[size];
        scenarioFactIds = new int[size];
        int toSelect = -1;
        int i = 0;
        for (Iterator it = project.getScenarios().iterator(); it.hasNext();) {
            ScenarioVO scenario = (ScenarioVO) it.next();
            scenarios[i] = scenario.getDescription();
            scenarioFactIds[i] = scenario.getFactId();
            if (vo != null && scenarioFactIds[i] == vo.getScenarioFactId()) {
                toSelect = i;
            }
            i++;
        }
        return toSelect;
    }

    /**
     * Populate the Responsibility descripitions into member variables respos and respFactIds
     * 
     * @return index of the responsibility of the mapping being edited; -1 if creating a new mapping
     */
    private int populateResponsibilities() {
        int size = project.getResponsibilities().size();
        respos = new String[size];
        respFactIds = new int[size];
        int toSelect = -1;
        int i = 0;
        for (Enumeration e = project.getResponsibilities().elements(); e.hasMoreElements();) {
            ResponsibilityVO respo = (ResponsibilityVO) e.nextElement();
            respos[i] = respo.getName();
            respFactIds[i] = respo.getFactId();
            if (vo != null && respFactIds[i] == vo.getResponsibilityFactId()) {
                toSelect = i;
            }
            i++;
        }
        return toSelect;
    }

    /**
     * Check that both a scenario and responsibility were selected from the list. Then save the
     * mapping to the core. Method declared on Dialog.
     */
    protected void okPressed() {
        int scenarioIndex = scenariosCombo.getSelectionIndex();
        if (scenarioIndex == -1) {
            MessageDialog.openError(getShell(), "Error", "Please select a scenario.");
            scenariosCombo.setFocus();
            return;
        }
        int respIndex = respsCombo.getSelectionIndex();
        if (respIndex == -1) {
            MessageDialog.openError(getShell(), "Error", "Please select a responsibility.");
            respsCombo.setFocus();
            return;
        }
        saveChanges(scenarioIndex, respIndex);
        super.okPressed();
    }

    /**
     * Saves the changes made in the dialog to the core. Creates a new mapping or edit the existing
     * mapping.
     * 
     * @param scenarioIndex index of selected scenario in the combo box
     * @param respIndex index of selected responsibility in the combo box
     */
    private void saveChanges(int scenarioIndex, int respIndex) {
        ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
        if (vo == null) {
            // add new
            facade.addScFnRespoRelation(scenarioFactIds[scenarioIndex], respFactIds[respIndex]);
        } else {
            // edit existing if changed
            if (vo.getScenarioFactId() != scenarioFactIds[scenarioIndex]
                    || vo.getResponsibilityFactId() != respFactIds[respIndex]) {
                facade.editScFnRespoRelation(vo.getFactId(), scenarioFactIds[scenarioIndex],
                                             respFactIds[respIndex]);
            }
        }
    }

}