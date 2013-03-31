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

package edu.cmu.sei.arche.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This is the class that has to be defined to create a new perspective. Defines the layout and
 * actions available in the ArchE perspective.
 * 
 * @see IPerspectiveFactory
 * @author Paulo Merson, Hyunwoo Kim
 */

public class ArchEPerspective implements IPerspectiveFactory {

    /**
     * Defines the initial layout for a perspective.
     * <p>
     * A perspective page layout is initialized with one area for displaying an editor. The
     * perspective factory is responsible for adding additional views relative to the editor. Views
     * are added to the layout relative to (top, bottom, left, right) another part. Placeholders
     * (empty space) can also be added for items that are not initially shown.
     * <p>
     * To organize related views and reduce clutter, you can use IFolderLayout to group views into
     * tabbed folders. For example, the Resource perspective places the resource navigator inside a
     * folder at the top left corner of the workbench. Placeholders are commonly used with folder
     * layouts. The Resource perspective defines a placeholder for the bookmarks view in the same
     * folder as the resource navigator. If the user shows the bookmarks view, it will appear in the
     * same folder with the navigator, with a tab for each view.
     * <p>
     * Implementors of this method may add additional views to a perspective. The perspective
     * already contains an editor folder with <code>ID = ILayoutFactory.ID_EDITORS</code>. Add
     * additional views to the perspective in reference to the editor folder. This method is only
     * called when a new perspective is created. If an old perspective is restored from a
     * persistence file then this method is not called.
     * 
     * @param layout the page layout
     */
    public void createInitialLayout(IPageLayout layout) {
        defineActions(layout);
        defineLayout(layout);
    }

    /**
     * Define the initial actions for a page (options of views in the "show views" menu, options of
     * wizards in the "new" menu, perspective shortcut itself, etc.
     * 
     * @param layout the page layout
     */
    private void defineActions(IPageLayout layout) {
        // Add ArchE perspective to perspective menu
        layout.addPerspectiveShortcut("SEI.ArchE.UI.ArchEPerspective");

        // Additions to "new" menu
        layout.addNewWizardShortcut("SEI.ArchE.UI.NewProjectWizard");

        // Add ArchE views to "show views".
        layout.addShowViewShortcut("SEI.ArchE.UI.ScenariosView");
//        layout.addShowViewShortcut("SEI.ArchE.UI.TrafficLightView");      
        layout.addShowViewShortcut("SEI.ArchE.UI.ResponsibilitiesView");
        layout.addShowViewShortcut("SEI.ArchE.UI.FunctionsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.RelationshipsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.ScenarioRespMappingView");
        layout.addShowViewShortcut("SEI.ArchE.UI.FunctionRespMappingView");
        layout.addShowViewShortcut("SEI.ArchE.UI.ModelElementsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.ModelRelationsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.DesignElementsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.DesignRelationsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.DesignTreeView");
        layout.addShowViewShortcut("SEI.ArchE.UI.QuestionsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.EvaluationResultsView");
        layout.addShowViewShortcut("SEI.ArchE.UI.JessConsole");

        layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);

        // Add action sets (new menu options, etc.)
        // layout.addActionSet("ArchE_menu");
    }

    /**
     * Define the initial layout for the page. Define folder areas and add views to each folder
     * area. This method defines the default positioning of the views when the perspective is open.
     * 
     * @param layout
     */
    private void defineLayout(IPageLayout layout) {
        // The editor area is automatically added to each layout and should be
        // used as reference for placing other views.
        String editorArea = layout.getEditorArea();

        // Bottom area with roughly 30% of screen vertically and 100% horizontally
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.65f, editorArea);
        bottom.addView("SEI.ArchE.UI.ModelElementsView");
        bottom.addView("SEI.ArchE.UI.ModelRelationsView");
        bottom.addView("SEI.ArchE.UI.DesignElementsView");
        bottom.addView("SEI.ArchE.UI.DesignRelationsView");
        bottom.addView("SEI.ArchE.UI.QuestionsView");
        bottom.addView("SEI.ArchE.UI.JessConsole");
        bottom.addView("SEI.ArchE.UI.EvaluationResultsView");
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(IPageLayout.ID_PROP_SHEET);

        // Top left area with roughly 26% horizontally and 60% vertically
        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, .26f, editorArea);
        topLeft.addView(IPageLayout.ID_RES_NAV);
        topLeft.addView("SEI.ArchE.UI.DesignTreeView");

        // Top Right area with roughly 74% horizontally and 35% vertically
        IFolderLayout topRight = layout
                .createFolder("topRight", IPageLayout.TOP, 0.60f, editorArea);
        topRight.addView("SEI.ArchE.UI.ScenariosView");
//        topRight.addView("SEI.ArchE.UI.TrafficLightView");
        topRight.addView("SEI.ArchE.UI.FunctionsView");
        topRight.addView("SEI.ArchE.UI.ResponsibilitiesView");
        topRight.addView("SEI.ArchE.UI.ScenarioRespMappingView");
        topRight.addView("SEI.ArchE.UI.FunctionRespMappingView");
        topRight.addView("SEI.ArchE.UI.RelationshipsView");

        // Middle area with roughly 74% horizontally and 35% vertically
//        IFolderLayout middle = layout.createFolder("middle", IPageLayout.TOP, 1.00f, editorArea);
//        middle.addPlaceholder("cmu.sei.archeGui.AViewEditor");
//        middle.addView("SEI.ArchE.UI.ScenarioRespMappingView");
//        middle.addView("SEI.ArchE.UI.FunctionRespMappingView");
//        middle.addView("SEI.ArchE.UI.RelationshipsView");

        // hide editor since ArchE doesn't have an editor (minimal scope)
        layout.setEditorAreaVisible(false);
    }

}