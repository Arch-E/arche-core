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

package edu.cmu.sei.arche.ui.prefs;

import java.util.Enumeration;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;

/**
 * Preferences page for reasoning frameworks. Let the user enable/disable a specific RF (only for
 * filtering RF specific data in the UI).
 * 
 * @author Paulo Merson
 */
public class ReasoningFrameworks extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * @param style
     */
    public ReasoningFrameworks() {
        super(GRID);
        setPreferenceStore(ArchEUIPlugin.getDefault().getPreferenceStore());
    }

    /**
     * Creates the field editors. For each available RF create a check box that will be checked if
     * that RF is active. Editors are abstractions of the common GUI blocks needed to manipulate
     * various types of preferences. Each field editor knows how to save and restore itself.
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    protected void createFieldEditors() {
        Group activeRFsGroup = new Group(getFieldEditorParent(), SWT.NONE);
        activeRFsGroup.setText("Display data specific to");
        activeRFsGroup.setLayout(new FillLayout(SWT.VERTICAL));
        GridData ggd = new GridData(GridData.FILL_HORIZONTAL);
        activeRFsGroup.setLayoutData(ggd);

        for (Enumeration en = ArchEUIPlugin.getDefault().getAvailableRFs().keys(); en
                .hasMoreElements();) {
            ReasoningFrameworkVO rfVO = (ReasoningFrameworkVO) en.nextElement();
            BooleanFieldEditor editor = new BooleanFieldEditor(rfVO.getId(), rfVO.getName(),
                    activeRFsGroup);
            editor.setPreferencePage(this);
            editor.setPreferenceStore(getPreferenceStore());
            addField(editor);
        }
    }

    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk() {
        ArchEUIPlugin.getDefault().savePluginPreferences();
        return super.performOk();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

}