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

package edu.cmu.sei.arche.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import edu.cmu.sei.arche.ui.dialog.ScenarioDialog;
import edu.cmu.sei.arche.ui.views.ScenariosView;

/**
 * Action handler for editing a scenario. Open scenario dialog box.
 * 
 * @author Paulo Merson
 */
public class NewScenarioActionDelegate implements IViewActionDelegate {

    /** View from where the action was invoked */
    private ScenariosView view;

    /**
     * Constructor simply calls super.
     */
    public NewScenarioActionDelegate() {
        super();
//        System.out.println("[NewScenarioActionDelegate] constructor");
    }

    /**
     * Opens the Scenario dialog box passing the data of the selected scenario to be edited.
     * 
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) {
//        System.out.println("[NewScenarioActionDelegate] run");
        Shell shell = new Shell();

        ScenarioDialog dialog = new ScenarioDialog(shell, null);
        dialog.open();
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * This is called by the platform. Because this is an action handler for view actions, this
     * method is the way the platform tells the action handler which view called it.
     * 
     * @param view communicates takes the view from where the action was invoked as the param
     * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
     */
    public void init(IViewPart view) {
//        System.out.println("[NewScenarioActionDelegate] init");
        this.view = (ScenariosView) view;
    }

}