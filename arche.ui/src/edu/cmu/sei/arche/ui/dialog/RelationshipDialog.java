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

import java.util.ArrayList;
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
import edu.cmu.sei.arche.vo.OperandsVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.RelationshipVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;

/**
 * Dialog box where the user can add a new Relationship or edit an existing one.
 * 
 * @author Henry Chen
 */
public class RelationshipDialog extends Dialog {

    /** The VO object being edited or <code>null</code> for a new one. */
    private RelationshipVO vo;

    /** The combo box widget for relationships. */
    private Combo          relationshipsCombo;

    /** The combo box widget for parent responsibilities. */
    private Combo          parentRespsCombo;

    /** The combo box widget for child responsibilities. */
    private Combo          childRespsCombo;

    /** RelationshipTypes for combo box display */
    private String[]       relationshipTypes;

    /** RelationshipTypes for storing original VO objects */
    private RelationshipTypeVO[] relationshipTypeVOs;
        
    /** Responsibilities for combo box display * */
    private String[]       respos;

    /** Responsibility fact IDs, in the order matching the text in respos[] */
    private int[]          respFactIds;

    /** Project that is currently open */
    private ProjectVO      project;

    /** Parent reponsibility item id of this relationship */
    private int            selParentIndex           = -1;

    /** Child reponsibility item id of this relationship */
    private int            selChildIndex            = -1;

    /** Child relationshiptype item id of this relationship */
    private int            selRelationshipTypeIndex = -1;

    /**
     * Creates the dialog. This constructor is used when we want to create a new item rather than
     * edit and existing one.
     * 
     * @param shell the parent shell
     */
    public RelationshipDialog(Shell parentShell) {
        this(parentShell, null);
    }

    /**
     * Creates the dialog. This constructor is used when we want to edit a new item (if vo parameter
     * is not null) rather than creating a new one.
     * 
     * @param shell the parent shell
     * @param vo the VO object that will be edited in this dialog (if null, it's creating a new)
     */
    public RelationshipDialog(Shell parentShell, RelationshipVO vo) {
        super(parentShell);
        this.vo = vo;
    }

    /**
     * Method declared on Window. Set the title of the dialog box.
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (vo == null) {
            newShell.setText("New Relationship");
        } else {
            newShell.setText("Edit Relationship");
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

        // PARENT RESPONSIBILITY LABEL AND COMBO BOX
        Label label = new Label(composite, SWT.NONE);
        label.setText("Responsibility");
        label.setFont(font);
        populateResponsibilities();
        parentRespsCombo = new Combo(composite, SWT.READ_ONLY);
        parentRespsCombo.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        parentRespsCombo.setLayoutData(gd);
        parentRespsCombo.setItems(respos);
        if (selParentIndex >= 0) {
            parentRespsCombo.select(selParentIndex);
        }

        //RELATIONSHIP TYPES LABEL AND COMBO BOX

        label = new Label(composite, SWT.NONE);
        label.setText("Relationship");
        label.setFont(font);
        initParamTypes();
        relationshipsCombo = new Combo(composite, SWT.READ_ONLY);
        relationshipsCombo.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        relationshipsCombo.setLayoutData(gd);
        relationshipsCombo.setItems(relationshipTypes);
        if (selRelationshipTypeIndex >= 0) {
            relationshipsCombo.select(selRelationshipTypeIndex);
        }

        // CHILD RESPONSIBILITY LABEL AND COMBO BOX
        label = new Label(composite, SWT.NONE);
        label.setText("Responsibility");
        label.setFont(font);
        populateResponsibilities();
        childRespsCombo = new Combo(composite, SWT.READ_ONLY);
        childRespsCombo.setFont(font);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalSpan = 2;
        childRespsCombo.setLayoutData(gd);
        childRespsCombo.setItems(respos);
        if (selChildIndex >= 0) {
            childRespsCombo.select(selChildIndex);
        }

    }

    /**
     * Populate the Responsibility descripitions into member variables respos and respFactIds
     * 
     * @return index of the responsibility of the mapping being edited; -1 if creating a new mapping
     */
    private void populateResponsibilities() {
        int size = project.getResponsibilities().size();
        respos = new String[size];
        respFactIds = new int[size];
        int toSelect = -1;
        int i = 0;
        for (Enumeration e = project.getResponsibilities().elements(); e.hasMoreElements();) {
            ResponsibilityVO respo = (ResponsibilityVO) e.nextElement();
            respos[i] = respo.getName();
            respFactIds[i] = respo.getFactId();
            if (vo != null) {
                if (respFactIds[i] == vo.getParentFactId()) {
                    selParentIndex = i;
                } else if (respFactIds[i] == vo.getChildFactId()) {
                    selChildIndex = i;
                }

            }

            i++;
        }
    }

    /**
     * Check that both a relationship and responsibility were selected from the list. Then save the
     * mapping to the core. Method declared on Dialog.
     */
    protected void okPressed() {

        int parentRelationshipIndex = parentRespsCombo.getSelectionIndex();
        if (parentRelationshipIndex == -1) {
            MessageDialog
                    .openError(getShell(), "Error", "Please select the parent responsibility.");
            parentRespsCombo.setFocus();
            return;
        }

        int relationshipTypeIndex = relationshipsCombo.getSelectionIndex();
        if (relationshipTypeIndex == -1) {
            MessageDialog.openError(getShell(), "Error", "Please select the relationship.");
            relationshipsCombo.setFocus();
            return;
        }

        int childRelationshipIndex = childRespsCombo.getSelectionIndex();

        if (childRelationshipIndex == -1) {
            MessageDialog.openError(getShell(), "Error", "Please select the child responsibility.");
            childRespsCombo.setFocus();
            return;
        }

        saveChanges(parentRelationshipIndex, childRelationshipIndex, relationshipTypeIndex);
        super.okPressed();
    }

    /**
     * Saves the changes made in the dialog to the core. Creates a new relationship or edit the
     * existing relationship.
     * 
     * @param relationshipIndex index of selected relationship in the combo box
     * @param respIndex index of selected responsibility in the combo box
     */
    private void saveChanges(int parentRelationshipIndex, int childRelationshipIndex,
            int relationshipTypeIndex) {
        ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
        if (vo == null) {
            // add new

        	if(relationshipTypeVOs[relationshipTypeIndex].isExternal()){
        		facade.addRespoRelation(respFactIds[parentRelationshipIndex],
        				respFactIds[childRelationshipIndex],
        				(RelationshipTypeVO)(relationshipTypeVOs[relationshipTypeIndex]));        		
        	}
        	else{
        		facade.addRespoRelation(respFactIds[parentRelationshipIndex],
        				respFactIds[childRelationshipIndex],
        				relationshipTypes[relationshipTypeIndex]);        		
        	}        		
            
        } else {

            if (selRelationshipTypeIndex != relationshipTypeIndex) {
                //when change a relationship, first retract it, then add a new one, the parameters
                // will be discard.
                facade.deleteRespoRelation(vo.getFactId());
                
            	if(relationshipTypeVOs[relationshipTypeIndex].isExternal()){
            		facade.addRespoRelation(respFactIds[parentRelationshipIndex],
            				respFactIds[childRelationshipIndex],
            				(RelationshipTypeVO)(relationshipTypeVOs[relationshipTypeIndex]));        		            		
            	}
            	else{
                    facade.addRespoRelation(respFactIds[parentRelationshipIndex],
                            respFactIds[childRelationshipIndex],
                            relationshipTypes[relationshipTypeIndex]);            		
            	}
            		
            }

            if (selParentIndex != parentRelationshipIndex
                    || selChildIndex != childRelationshipIndex) {
                facade.editRespoRelation(vo, respFactIds[parentRelationshipIndex],
                        respFactIds[childRelationshipIndex]);            		
            }

        }

    }

    /**
     * Populate the Parameter types from active RFs.
     */
    private void initParamTypes() {
        ArrayList relTypes = new ArrayList();
        ArrayList relTypeVOs = new ArrayList();
        int toSel = -1;
        int i = 0;
        Iterator itrt;
        Iterator itop;
        RelationshipTypeVO relType;
        OperandsVO operand;

        for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            // If the reasoning framwork is disabled, continue next loop.
            if (!((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
                continue;
            }

            itrt = rf.getRelationshipTypes().iterator();
            while (itrt.hasNext()) {
                // add relationship types to array
                relType = (RelationshipTypeVO) itrt.next();

                // Check whether the relationshipType has the required Operant
                itop = relType.getAllowedOperands().iterator();
                while (itop.hasNext()) {
                    operand = (OperandsVO) itop.next();
                    if (operand.getLhs().equals(OperandsVO.RESPONSIBILITY)
                            && operand.getRhs().equals(OperandsVO.RESPONSIBILITY)) {

                    	relTypeVOs.add(relType);
                    	relTypes.add(relType.getName());

                        if (vo != null && vo.getType().getName().equals(relType.getName())) {
                            selRelationshipTypeIndex = i;
                        }
                        // Goto the next relType
                        i++;
                        break;
                    }
                }
            }
        }

        relationshipTypeVOs = (RelationshipTypeVO[])relTypeVOs.toArray(new RelationshipTypeVO[0]);
        relationshipTypes = (String[]) relTypes.toArray(new String[0]);
    }

}