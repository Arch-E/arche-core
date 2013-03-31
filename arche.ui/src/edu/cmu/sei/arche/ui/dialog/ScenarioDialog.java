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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.vo.PartOptionsVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.ScenarioPartMetadataVO;
import edu.cmu.sei.arche.vo.ScenarioPartVO;
import edu.cmu.sei.arche.vo.ScenarioTypeVO;
import edu.cmu.sei.arche.vo.ScenarioVO;

/**
 * Scenario dialog box used to add and edit one scenario at a time.
 * 
 * @author Myung-joo Ko
 * @author Paulo Merson
 */
public class ScenarioDialog extends TitleAreaDialog {

    /** top level composite in the area with widgets for data entry */
    private Composite   parentComposite;

    /** multi-line text box to enter scenario description text */
    private Text        scenarioText;

    /** combo box for scenario type (one of the options is always "Unknown") */
    private Combo       scenarioTypeCombo;

    /** Array of 6 text boxes corresponding to 1st column for all six parts. */
    private Text        partDescTexts[];

    /** Array of 6 combo boxes corresponding to 2nd column for all six parts. */
    private Combo       partTypeCombos[];

    /**
     * Array of lists: element [i] is the list of ids of the part options displayed in the type
     * combo box for part i. The jth element in the list is the id (e.g. "sporadic") that correspond
     * to the jth option in the combo box (e.g. "Sporadic arrival").
     */
    private List        partTypeIds[];

    /** Array of 6 combo boxes corresponding to 3rd column for all six parts. */
    private Combo       partUnitCombos[];

    /**
     * Array of lists: element [i] is the list of ids of the part options displayed in the unit
     * combo box for part i.The jth element in the list is the id (e.g. "ms") that correspond to the
     * jth option in the combo box (e.g. "Milliseconds").
     */
    private List        partUnitIds[];

    /** Array of 6 text boxes corresponding to 4th column for all six parts. */
    private Text        partValueTexts[];

    private Action      addAsStimulusAction;

    private Action      addAsSourceOfStimulusAction;

    private Action      addAsArtifactAction;

    private Action      addAsEnvironmentAction;

    private Action      addAsResponseAction;

    private Action      addAsResponseMeasureAction;

    private Action      copyAction;

    private Action      cutAction;

    private Action      pasteAction;

    private ScenarioVO  scenarioVO;

    private ProjectVO   project;

    private ArchEFacade facade;

    /** Dirty flag. True if any changes have been made. */
    private boolean     dirty;

    /**
     * Maps scenario type name String --> ScenarioTypeVO. Contains only scenario types of active RFs
     */
    private Hashtable   activeScenarioTypes;

    /**
     * Constructor for the class. When user click "New Scenario", scenarioVO is null and scenario
     * input dialog box shows default values of text, type, unit and value of each six parts. When
     * user click "Edit Scenario", scenarioVOscenario input dialog box shows user input values of
     * text, type, unit and value of each six parts.
     * 
     * @param parentShell the window from which this dialog is being called.
     * @param scenarioVO scenario to be edited.
     */
    public ScenarioDialog(Shell parentShell, ScenarioVO scenarioVO) {
        super(parentShell);
        this.scenarioVO = scenarioVO;
    }

    /**
     * Configures the given shell in preparation for opening this window in it. Sets the title and
     * the icon in the title bar.
     * 
     * @param shell
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Scenario");
        WorkbenchHelp.setHelp(shell, "SEI.ArchE.UI.scenarioDlgBox");
    }

    /**
     * Method declared in Window. Configures the top part of the dialog (area in the white band).
     * 
     * @param parent parent Composite to which controls will be added
     */
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle("Scenario"); // bold text inside the white section
        setTitleImage(getImageFromPath("icons/arche-logo48.gif"));
        setMessage("A scenario is a quality attribute requirement of a system and is described in six parts.");
        return contents;
    }

    /**
     * Creates an Image object from an ImageDescriptor created based on the image file specified by
     * the relative path.
     * 
     * @param relativePath path inside the plugin (typically "icons/imageName.gif")
     * @return Image object
     */
    private Image getImageFromPath(String relativePath) {
        URL baseUrlPlugin = ArchEUIPlugin.getDefault().getDescriptor().getInstallURL();
        URL imgUrl = null;
        try {
            imgUrl = new URL(baseUrlPlugin, relativePath);
        } catch (MalformedURLException ex) {
            return null;
        }
        return ImageDescriptor.createFromURL(imgUrl).createImage();
    }

    private String getScenarioIDFromName(String ScenarioName){
    	Iterator iter = activeScenarioTypes.values().iterator();
    	while(iter.hasNext()){
    		ScenarioTypeVO scenarioType = (ScenarioTypeVO)iter.next();
    		if(scenarioType.getName().equalsIgnoreCase(ScenarioName))
    			return scenarioType.getId();
    	}
    	return null;
    }
    
    /**
     * Creates the widget in the large area between the white band and the buttons. Method declared
     * on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
        project = ArchEUIPlugin.getDefault().getProjectVo();
        facade = ArchEUIPlugin.getDefault().getArchEFacade();
        activeScenarioTypes = new Hashtable();
        for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            if (((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
                for (Iterator it = rf.getScenarioTypes().iterator(); it.hasNext();) {
                    ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
                    activeScenarioTypes.put(scenarioType.getId(), scenarioType);
                }
            }
        }

        ModifyListener modifyListener = new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                setDirty(true);
            }
        };

        // top level composite
        parentComposite = (Composite) super.createDialogArea(parent);
        Font font = parent.getFont();
        // create a composite with standard margins and spacing
        Composite composite = new Composite(parentComposite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        layout.numColumns = 3;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parentComposite.getFont());

        //
        // Scenario description text
        //
        Label scenarioTextLabel = new Label(composite, SWT.NONE);
        scenarioTextLabel.setFont(font);
        scenarioTextLabel.setText("Scenario Text:");
        scenarioText = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);

        //Create context menus for scenario description textbox
        createContextMenu(scenarioText);

        //Add actions for context menus used inside scenario description
        addAsStimulusAction = new AddAsStimulusAction();
        addAsSourceOfStimulusAction = new AddAsSourceOfStimulusAction();
        addAsArtifactAction = new AddAsArtifactAction();
        addAsEnvironmentAction = new AddAsEnvironmentAction();
        addAsResponseAction = new AddAsResponseAction();
        addAsResponseMeasureAction = new AddAsResponseMeasureAction();

        copyAction = new CopyAction();
        cutAction = new CutAction();
        pasteAction = new PasteAction();

        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalAlignment = GridData.FILL;
        gridData1.horizontalSpan = 3;
        gridData1.widthHint = 150;
        gridData1.heightHint = 100;
        scenarioText.setLayoutData(gridData1);

        //
        // Scenario type combo box
        //
        Composite scTypeLine = new Composite(composite, SWT.NULL);
        GridLayout scTypeLayout = new GridLayout(3, false);
        scTypeLine.setLayout(scTypeLayout);
        GridData scTypeGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        scTypeGridData.horizontalSpan = 3;
        scTypeLine.setLayoutData(scTypeGridData);

        Label scenarioTypeLabel = new Label(scTypeLine, SWT.NONE);
        scenarioTypeLabel.setFont(font);
        scenarioTypeLabel.setText("Type:");
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        scenarioTypeLabel.setLayoutData(gridData2);
        scenarioTypeCombo = new Combo(scTypeLine, SWT.READ_ONLY);

        scenarioTypeCombo.add("Unknown"); // INDEX 0 IS ALWAYS "Unknown"

        ScenarioTypeVO scenarioType = null;
        if (scenarioVO != null) {
            scenarioType = scenarioVO.getScenarioType();
            if(scenarioType == null){
            	scenarioType = (ScenarioTypeVO) activeScenarioTypes.get(scenarioVO.getQuality());    
            	scenarioVO.setScenarioType(scenarioType);
            }            
        }
        
        int k = 0;
        for (Enumeration e = activeScenarioTypes.keys(); e.hasMoreElements();) {
            String scenarioID = (String) e.nextElement();
            ScenarioTypeVO tempScenarioType = (ScenarioTypeVO) activeScenarioTypes.get(scenarioID);
            scenarioTypeCombo.add(tempScenarioType.getName());
            if (scenarioType != null && scenarioType.getId().equals(tempScenarioType.getId())) {
                scenarioTypeCombo.select(k + 1);
            }
            k++;
        }

        scenarioTypeCombo.addSelectionListener(new SelectionListener() {

            // User changed selection in scenario type combo box
            public void widgetSelected(SelectionEvent se) {
                resetCombos();
                if (scenarioTypeCombo.getSelectionIndex() == 0) {
                    // type = unknown
                    fillSixPartsCombos(null);
                    if (scenarioVO == null) {
                        resetTexts(); // will reset text even if it was typed by the user
                    }
                } else {
                    // Scenario type is not Unknown
                    resetTexts(); // will reset text even if it was typed by the user
                    ScenarioTypeVO scenarioType = (ScenarioTypeVO) activeScenarioTypes
                            .get(getScenarioIDFromName(scenarioTypeCombo.getText()));
                    fillSixPartsCombos(scenarioType);
                    setSixPartsWithDefaults(scenarioType);
                }
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        scenarioTypeCombo.setLayoutData(gridData3);

        // 
        // Think button
        //
        Button thinkButton = new Button(scTypeLine, SWT.PUSH);
        thinkButton.setText("Insight");
        GridData gridData4 = new GridData(GridData.HORIZONTAL_ALIGN_END);//.HORIZONTAL_ALIGN_FILL);
        thinkButton.setLayoutData(gridData4);
        thinkButton.setEnabled(false); // Andres 3/14/08
        thinkButton.addSelectionListener(new SelectionListener() {

            // think button pressed
            public void widgetSelected(SelectionEvent se) {
                archEThink(getUserInputs());
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        //
        // Create textboxes and comboboxes for six parts
        //
        Group sixParts = new Group(composite, SWT.NONE);
        sixParts.setText("Six Parts");
        GridLayout groupLayout = new GridLayout();
        groupLayout.numColumns = 5;
        sixParts.setLayout(groupLayout);
        GridData groupGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        groupGridData.horizontalSpan = 5;
        groupGridData.grabExcessHorizontalSpace = true;
        sixParts.setLayoutData(groupGridData);

        //Header label for text, type, unit, value
        Label headerEmptyLabel = new Label(sixParts, SWT.NONE);
        headerEmptyLabel.setFont(font);
        headerEmptyLabel.setText("");

        Label headerTextLabel = new Label(sixParts, SWT.NONE);
        GridData headerTextGridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        headerTextLabel.setLayoutData(headerTextGridData);
        headerTextLabel.setFont(font);
        headerTextLabel.setText("Text");

        Label headerTypeLabel = new Label(sixParts, SWT.NONE);
        GridData headerTypeGridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        headerTypeLabel.setLayoutData(headerTypeGridData);
        headerTypeLabel.setFont(font);
        headerTypeLabel.setText("Type");

        Label headerUnitLabel = new Label(sixParts, SWT.NONE);
        GridData headerUnitGridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        headerUnitLabel.setLayoutData(headerUnitGridData);
        headerUnitLabel.setFont(font);
        headerUnitLabel.setText("Unit");

        Label headerValueLabel = new Label(sixParts, SWT.NONE);
        GridData headerValueGridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        headerValueLabel.setLayoutData(headerValueGridData);
        headerValueLabel.setFont(font);
        headerValueLabel.setText("Value");

        partDescTexts = new Text[6];
        partTypeCombos = new Combo[6];
        partTypeIds = new ArrayList[6];
        partUnitCombos = new Combo[6];
        partUnitIds = new ArrayList[6];
        partValueTexts = new Text[6];

        String labelTexts[] = { "Stimulus:", "Source of stimulus:", "Environment:", "Artifact:",
                "Response:", "Response measure:"};
        for (int i = 0; i < 6; i++) { // loop over six parts
            // label
            Label descLabel = new Label(sixParts, SWT.NONE);
            descLabel.setFont(font);
            descLabel.setText(labelTexts[i]);

            // 1st column: text
            partDescTexts[i] = new Text(sixParts, SWT.SINGLE | SWT.WRAP | SWT.BORDER);
            GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
            gridData.widthHint = 300;
            partDescTexts[i].setLayoutData(gridData);
            partDescTexts[i].setFont(font);

            // 2nd column: type combo box
            partTypeCombos[i] = new Combo(sixParts, SWT.READ_ONLY);
            GridData typeGridData = new GridData(GridData.FILL_HORIZONTAL);
            typeGridData.widthHint = 150;
            partTypeCombos[i].setLayoutData(typeGridData);

            // 3rd column: unit combo box
            partUnitCombos[i] = new Combo(sixParts, SWT.READ_ONLY);
            GridData unitGridData = new GridData(GridData.FILL_HORIZONTAL);
            unitGridData.widthHint = 100;
            partUnitCombos[i].setLayoutData(unitGridData);

            // 4th column: value
            partValueTexts[i] = new Text(sixParts, SWT.SINGLE | SWT.WRAP | SWT.BORDER);
            GridData valGridData = new GridData(GridData.FILL_HORIZONTAL);
            valGridData.widthHint = 50;
            partValueTexts[i].setLayoutData(valGridData);
            partValueTexts[i].setFont(font);
        }

        initPage();

        // only after initializing data, we add the modify listeners to avoid calling it during init
        scenarioText.addModifyListener(modifyListener);
        scenarioTypeCombo.addModifyListener(modifyListener);
        for (int i = 0; i < 6; i++) {
            partDescTexts[i].addModifyListener(modifyListener);
            partTypeCombos[i].addModifyListener(modifyListener);
            partUnitCombos[i].addModifyListener(modifyListener);
            partValueTexts[i].addModifyListener(modifyListener);
        }
        setDirty(false);
        return parentComposite;
    }

    /**
     * Create the button area at the bottom of the dialog.
     * 
     * @param parent parent Composite to where the widgest will be added.
     */
    protected void createButtonsForButtonBar(Composite parent) {
        Button helpButton = createButton(parent, IDialogConstants.HELP_ID,
                                         IDialogConstants.HELP_LABEL, false);

        helpButton.addSelectionListener(new SelectionListener() {

            // help button pressed
            public void widgetSelected(SelectionEvent se) {
                WorkbenchHelp.displayHelp("SEI.ArchE.UI.scenarioDlgBox");
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        //
        // SAVE BUTTON
        //
        Button saveButton = createButton(parent, IDialogConstants.NO_ID, "Save", false);
        saveButton.addSelectionListener(new SelectionListener() {

            // save button pressed
            public void widgetSelected(SelectionEvent se) {
                if (isDirty() && saveScenario(getUserInputs())) {
                    refreshScenario();
                    parentComposite.update();
                    setDirty(false);
                }
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        //
        // CLOSE BUTTON
        //
        Button closeButton = createButton(parent, IDialogConstants.CLOSE_ID,
                                          IDialogConstants.CLOSE_LABEL, true);
        closeButton.addSelectionListener(new SelectionListener() {

            // close button pressed
            public void widgetSelected(SelectionEvent se) {
                close();
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        //
        // NEW BUTTON
        // 
        Button newButton = createButton(parent, IDialogConstants.NO_ID, "New", false);
        newButton.addSelectionListener(new SelectionListener() {

            // new button pressed
            public void widgetSelected(SelectionEvent se) {
                if (isDirty()) {
                    MessageDialog newMsgDialog = new MessageDialog(getShell(), "Close", null,
                            "Scenario has been modified. Save changes?", MessageDialog.QUESTION,
                            new String[] { "Yes", "No", "Cancel"}, 0);
                    int retCode = newMsgDialog.open();
                    if (retCode == 2) {
                        // canceled operation: don't save and don't reset
                        return;
                    }
                    if (retCode == 0) {
                        // yes, please save before resetting
                        if (!saveScenario(getUserInputs())) {
                            // oops, a problem occured when saving: don't reset
                            return;
                        }
                    }

                }
                scenarioVO = null;
                initPage();
                parentComposite.update();
                setDirty(false);
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        // 
        // CANCEL BUTTON (standard cancel button: call close() by default, no listener needed)
        // 
        Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
                                           IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * User is trying to close the dialog box. Check if there's unsaved data and let the user choose
     * to save before closing (or cancel close operation).
     * 
     * @see org.eclipse.jface.window.Window#close()
     */
    public boolean close() {
        if (isDirty()) {
            MessageDialog closeMsgDialog = new MessageDialog(getShell(), "Close", null,
                    "Scenario has been modified. Save changes?", MessageDialog.QUESTION,
                    new String[] { "Yes", "No", "Cancel"}, 0);
            int retCode = closeMsgDialog.open();
            if (retCode == 2) {
                // canceled close: don't save and don't close window
                return false;
            }
            if (retCode == 0) {
                //yes, please save before closing
                if (!saveScenario(getUserInputs())) {
                    // oops, a problem occurred when saving: don't close
                    return false;
                }
            }
        }
        return super.close();
    }

    /**
     * Configure the context menu (popup menu) to be lazily populated on each pop-up.
     * 
     * @param table table that will have the popup menu.
     */
    private void createContextMenu(Text text) {
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });

        Menu menu = menuMgr.createContextMenu(text);
        text.setMenu(menu);
    }

    /**
     * Add menu actions to context menu of marked text in the scenario description text box.
     * 
     * @param menu IMenuManager object to which the separator is added.
     */
    protected void fillContextMenu(IMenuManager menu) {
        menu.add(addAsStimulusAction);
        menu.add(addAsSourceOfStimulusAction);
        menu.add(addAsArtifactAction);
        menu.add(addAsEnvironmentAction);
        menu.add(addAsResponseAction);
        menu.add(addAsResponseMeasureAction);
        Separator separator = new Separator();
        menu.add(separator);
        menu.add(copyAction);
        menu.add(cutAction);
        menu.add(pasteAction);

        // if the user didn't enter anything in the description textbox, then disable all
        // menu items.
        boolean isEnabled = false;
        isEnabled = (scenarioText.getText().equals("") ? false : true);

        // if no item is selected, gray out popup menu options
        if (isEnabled) {
            isEnabled = scenarioText.getSelectionText().length() == 0 ? false : true;
        }
        addAsStimulusAction.setEnabled(isEnabled);
        addAsSourceOfStimulusAction.setEnabled(isEnabled);
        addAsArtifactAction.setEnabled(isEnabled);
        addAsEnvironmentAction.setEnabled(isEnabled);
        addAsResponseAction.setEnabled(isEnabled);
        addAsResponseMeasureAction.setEnabled(isEnabled);
        copyAction.setEnabled(isEnabled);
        cutAction.setEnabled(isEnabled);
        pasteAction.setEnabled(isEnabled);
    }

    /**
     * Returns String array with 26 elements corresponding to user input for scenario description,
     * scenario type and text/type/unit/value of each of the 6 parts
     * 
     * @return String[]
     * @see edu.cmu.sei.arche.corebridge.interactions.ScenarioInteractions
     */
    private String[] getUserInputs() {
        String[] scenarioInputs = new String[27];
        scenarioInputs[0] = scenarioText.getText() != null ? scenarioText.getText() : "";
        ScenarioTypeVO scenarioType = (ScenarioTypeVO) activeScenarioTypes.get(getScenarioIDFromName(scenarioTypeCombo.getText()));
        scenarioInputs[1] = (scenarioType != null ? scenarioType.getId() : "");
        for (int i = 0; i < 6; i++) {
            // Description text
            String descText = partDescTexts[i].getText();

            // type
            String typeId = null;
            int selection = partTypeCombos[i].getSelectionIndex();
            if (selection != -1) {
                typeId = (String) partTypeIds[i].get(selection);
            } else {
                typeId = "";
            }

            // unit
            String unitId = null;
            selection = partUnitCombos[i].getSelectionIndex();
            if (selection != -1) {
                unitId = (String) partUnitIds[i].get(selection);
            } else {
                unitId = "";
            }
            // Value
            String valueText = partValueTexts[i].getText();

            // The 4 fields for each part are stored as 4 separate entries in the scenarioInputs
            // array. They have to be stored in a specific position defined in the constructor of
            // ScenarioInteractions. So, we translate the partType to this index here, so that
            // the partType constants can change freely later.
            int indexOfPart = ArchEFacade.INDEX_OF_PART_IN_SCENARIO_ARRAY[i];
            // finally, store the values in the array
            scenarioInputs[indexOfPart] = descText != null ? descText : "";
            scenarioInputs[indexOfPart + 1] = typeId;
            scenarioInputs[indexOfPart + 2] = unitId;
            scenarioInputs[indexOfPart + 3] = valueText != null ? valueText : "";
        }
        scenarioInputs[26] = scenarioType.getRFID();
        return scenarioInputs;
    }

    /**
     * Locate newly added or edited scenario VO in memory (has to locate unequivocally based on data
     * on screen). Then refresh scenario dialog box with located VO.
     */
    private void refreshScenario() {
        String desc = scenarioText.getText();
        List scenarioVOs = new ArrayList();
        for (Iterator it = project.getScenarios().iterator(); it.hasNext();) {
            ScenarioVO aScenario = (ScenarioVO) it.next();
            if (aScenario.getDescription().equals(desc)) {
                scenarioVOs.add(aScenario);
            }
        }
        int nofMatches = scenarioVOs.size();
        if (nofMatches == 0) {
            MessageDialog.openError(getShell(), "Error",
                                    "Scenario could not be stored in the fact base");
            return;
        }
        if (nofMatches == 1) {
            // As expected! Description is unique
            this.scenarioVO = (ScenarioVO) scenarioVOs.get(0);
        } else {
            // More than one scenario found with the same description.
            // Has to check other fields to try to identify uniquevocally which one is a match
            identifyUniqueScenarioVO(scenarioVOs);
        }
        initPage();
    }

    /**
     * There is more than one scenario in the specified list. They all have the same description,
     * which matches the description on screen. We have to identify among them, which one is the one
     * on screen, based on information we have on screen.
     * 
     * @return
     */
    private void identifyUniqueScenarioVO(List scenarioVOs) {
        if (scenarioTypeCombo.getSelectionIndex() != -1) {
            // there's a scenario type selected. Let's try to identify the vo by scenario type
            ScenarioTypeVO scenarioType = (ScenarioTypeVO) activeScenarioTypes
                    .get(getScenarioIDFromName(scenarioTypeCombo.getText()));

            for (Iterator it = scenarioVOs.iterator(); it.hasNext();) {
                ScenarioVO aScenario = (ScenarioVO) it.next();
                if (!scenarioType.equals(aScenario.getScenarioType())) {
                    scenarioVOs.remove(aScenario);
                }
            }
            int nofMatches = scenarioVOs.size();
            if (nofMatches == 0) {
                MessageDialog.openError(getShell(), "Error",
                                        "Scenario could not be stored propertly in the fact base");
                return;
            }
            if (nofMatches == 1) {
                // Isolated a unique vo :^)
                this.scenarioVO = (ScenarioVO) scenarioVOs.get(0);
                return;
            }
        }
        // There's still more than one scenario in the collection.
        // Let's check six parts to to try to identify uniquevocally which one is the match
        for (int i = 0; i < 6; i++) {
            // try to identify by the text (1st column) and value (4th column) of the ith part.
            String txt = partDescTexts[i].getText();
            String val = partValueTexts[i].getText();
            if (!txt.equals("") || !val.equals("")) {
                // on screen, the ith part has a text or a value. Let's check against parts in VO
                for (Iterator it = scenarioVOs.iterator(); it.hasNext();) {
                    ScenarioVO aScenario = (ScenarioVO) it.next();
                    ScenarioPartVO parts[] = aScenario.getParts();
                    boolean found = false;
                    if (parts != null) {
                        for (int j = 0; j < parts.length; j++) {
                            if (parts[j] != null && parts[j].getPartType() == i) {
                                if ((txt.equals("") || txt.equals(parts[j].getText()))
                                        && (val.equals("") || val.equals(parts[j].getValue()))) {
                                    // text and value on screen match text and value in the VO
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!found) {
                        // this scenario doesn't match text or value on screen
                        scenarioVOs.remove(aScenario);
                    }
                }
                int nofMatches = scenarioVOs.size();
                if (nofMatches == 0) {
                    MessageDialog
                            .openError(getShell(), "Error",
                                       "Scenario could not be stored propertly in the fact base");
                    return;
                }
                if (nofMatches == 1) {
                    // Isolated a unique vo :^)
                    this.scenarioVO = (ScenarioVO) scenarioVOs.get(0);
                    return;
                }
            }
        }
        MessageDialog.openError(getShell(), "Error",
                                "Could not identify a scenario uniquevocally and hence could not "
                                        + "refresh the data on screen. \n\nThe dialog box will be "
                                        + "closed as a precaution, but the data was saved.");
        super.close();
        return;
    }

    /**
     * Validate input and return whether this is saved without errors or not.
     * 
     * @return <code>true</code> if successful, and <code>false</code> if description is empty
     */
    private boolean saveScenario(String[] scenarioInputs) {

        String desc = scenarioText.getText();
        if ((desc.trim().length() == 0)) {
            MessageDialog.openError(getShell(), "Error", "Scenario description cannot be empty!");
            scenarioText.setFocus();
            return false;
        }
        int nofSameDesc = 0;
        for (Iterator it = project.getScenarios().iterator(); it.hasNext();) {
            ScenarioVO vo = (ScenarioVO) it.next();
            if (vo != scenarioVO && vo.getDescription().equals(desc)) {
                nofSameDesc++;
            }
        }
        if (nofSameDesc > 0) {
            MessageDialog
                    .openError(getShell(), "Error",
                               "There is already a scenario with the same description. Please change it.");
            scenarioText.setFocus();
            return false;
        }
        if (scenarioVO == null) {
            facade.addScenario(scenarioInputs);
        } else {
            facade.editScenario(scenarioInputs, scenarioVO.getFactId());
        }
        return true;
    }

    /**
     * Get suggested values from archE and display all values on screen When we get the response
     * from core, we only set the value on the proper widget if the value is different, otherwise
     * the modify listener would mark it as dirty even if it didn't change.
     */
    private void archEThink(String[] scenarioInputs) {

        String[] thinkResult = facade.thinkScenario(scenarioInputs);

        // text description
        if (thinkResult[0] != null && !thinkResult[0].equals("nil")
                && !thinkResult[0].equals(scenarioText.getText())) {
            scenarioText.setText(thinkResult[0]);
        }

        // scenario type
        if (thinkResult[1] != null && !thinkResult[1].equals("nil")) {
            String items[] = scenarioTypeCombo.getItems();
            for (int i = 0; i < items.length; i++) {
                ScenarioTypeVO scenarioType = (ScenarioTypeVO) activeScenarioTypes.get(getScenarioIDFromName(items[i]));
                if (scenarioType != null && scenarioType.getId().equals(thinkResult[1])
                        && scenarioTypeCombo.getSelectionIndex() != i) {
                    scenarioTypeCombo.select(i);
                    break;
                }
            }
        }
        // loop over six parts
        for (int i = 0; i < 6; i++) {
            // The 4 fields for each part are stored as 4 separate entries in the scenarioInputs
            // array. They have to be stored in a specific position defined in the constructor of
            // ScenarioInteractions. So, we translate the partType to this index here, so that
            // the partType constants can change freely later.
            int indexOfPart = ArchEFacade.INDEX_OF_PART_IN_SCENARIO_ARRAY[i];
            String descText = thinkResult[indexOfPart];
            String typeId = thinkResult[indexOfPart + 1];
            String unitId = thinkResult[indexOfPart + 2];
            String valueText = thinkResult[indexOfPart + 3];
            if (descText != null && !descText.equals("nil")
                    && !descText.equals(partDescTexts[i].getText())) {
                partDescTexts[i].setText(descText);
            }
            if (typeId != null && !typeId.equals("nil")) {
                for (int j = 0; j < partTypeIds[i].size(); j++) {
                    if (partTypeIds[i].get(j).equals(typeId)
                            && partTypeCombos[i].getSelectionIndex() != j) {
                        partTypeCombos[i].select(j);
                        break;
                    }
                }
            }
            if (unitId != null && !unitId.equals("nil")) {
                for (int j = 0; j < partUnitIds[i].size(); j++) {
                    if (partUnitIds[i].get(j).equals(unitId)
                            && partUnitCombos[i].getSelectionIndex() != j) {
                        partUnitCombos[i].select(j);
                        break;
                    }
                }
            }
            if (valueText != null && !valueText.equals("nil")
                    && !valueText.equals(partValueTexts[i].getText())) {
                partValueTexts[i].setText(valueText);
            }
        }
    }

    /**
     * Initialize dialog box
     */
    private void initPage() {
        resetCombos();
        resetTexts();
        if (scenarioVO != null) {
            // editing a scenario
            String desc = scenarioVO.getDescription();
            if (!desc.equals(scenarioText.getText())) {
                scenarioText.setText(desc);
            }
            
            ScenarioTypeVO scenarioType = null;
            if (scenarioVO != null) {
                scenarioType = scenarioVO.getScenarioType();
                if(scenarioType == null){
                	scenarioType = (ScenarioTypeVO) activeScenarioTypes.get(scenarioVO.getQuality());            	
                	scenarioVO.setScenarioType(scenarioType);
                }                
            }            
            fillSixPartsCombos(scenarioType);
            setSixPartsWithValuesInScenarioVO();
        } else {
            // new scenario
            scenarioText.setText("");
            fillSixPartsCombos(null);
            scenarioTypeCombo.deselectAll();
        }
    }

    /**
     * Remove the contents of all six-part combo boxes (2nd column - type, and 3rd column - unit),
     * leaving them with one single item ("").
     */
    private void resetCombos() {
        for (int i = 0; i < 6; i++) {
            // type
            partTypeCombos[i].removeAll();
            partTypeCombos[i].add("");
            partTypeCombos[i].deselectAll();
            partTypeIds[i] = new ArrayList();
            partTypeIds[i].add(0, "");
            // unit
            partUnitCombos[i].removeAll();
            partUnitCombos[i].add("");
            partUnitCombos[i].deselectAll();
            partUnitIds[i] = new ArrayList();
            partUnitIds[i].add(0, "");
        }
    }

    /**
     * Reset to blank the contents of all six-part text boxes (1st column - text, and 4th column -
     * value)
     */
    private void resetTexts() {
        for (int i = 0; i < 6; i++) {
            partDescTexts[i].setText("");
            partValueTexts[i].setText("");
        }
    }

    /**
     * Getter method for dirty flag.
     * 
     * @return
     */
    private boolean isDirty() {
        return dirty;
    }

    /**
     * Setter method for dirty flag.
     * 
     * @param dirty
     */
    private void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * Populate the widgets with the default value of each six part element of the specified
     * ScenarioTypeVO. If a given widget has already a value (or a selection in the case of a
     * Combo), it remains untouched. This method assumes the combos for type and unit are
     * pre-populated.
     * 
     * @param scenarioType cannot be null
     */
    private void setSixPartsWithDefaults(ScenarioTypeVO scenarioType) {
        ScenarioPartMetadataVO parts[] = scenarioType.getPartsMetadata();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] != null) {
                int partType = parts[i].getPartType();
                // 1st column: text
                if (partDescTexts[partType].getText().equals("")) {
                    String txt = parts[i].getDefaultText();
                    partDescTexts[partType].setText(txt != null ? txt : "");
                }
                // 2nd column: type
                if (partTypeCombos[partType].getSelectionIndex() == -1) {
                    PartOptionsVO defaultOption = parts[i].getDefaultType();
                    if (defaultOption != null) {
                        String optionId = defaultOption.getId();
                        boolean found = false;
                        for (int j = 0; j < partTypeIds[partType].size(); j++) {
                            if (partTypeIds[partType].get(j).equals(optionId)) {
                                partTypeCombos[partType].select(j);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            throw new IllegalStateException(
                                    "Type combo of scenario part was not initialized propertly");
                        }
                    }
                }
                // 3rd column: unit
                if (partUnitCombos[partType].getSelectionIndex() == -1) {
                    PartOptionsVO defaultOption = parts[i].getDefaultUnit();
                    if (defaultOption != null) {
                        String optionId = defaultOption.getId();
                        boolean found = false;
                        for (int j = 0; j < partUnitIds[partType].size(); j++) {
                            if (partUnitIds[partType].get(j).equals(optionId)) {
                                partUnitCombos[partType].select(j);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            throw new IllegalStateException(
                                    "Unit combo of scenario part was not initialized propertly");
                        }
                    }
                }
                // 4th column: value
                if (partValueTexts[partType].getText().equals("")) {
                    String val = parts[i].getDefaultValue();
                    partValueTexts[partType].setText(val != null ? val : "");
                }
            }
        }
    }

    /**
     * Populate the widgets with the values in the current scenario VO object. Should only be called
     * when scenarioVO is NOT NULL. This method assumes the combos for type and unit are
     * pre-populated. Note we only set the value of the widgets if it's changing to avoid
     * unnecessary overhead and calling setDirty(true).
     */
    private void setSixPartsWithValuesInScenarioVO() {
        ScenarioPartVO parts[] = scenarioVO.getParts();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] != null) {
                int partType = parts[i].getPartType();
                // 1st column: text
                String txt = parts[i].getText();
                if (txt == null) {
                    txt = "";
                }
                if (!txt.equals(partDescTexts[partType])) {
                    partDescTexts[partType].setText(txt);
                }
                // 2nd column: type
                String optionId = parts[i].getTypeId();
                boolean found = false;
                for (int j = 0; j < partTypeIds[partType].size(); j++) {
                    if (partTypeIds[partType].get(j).equals(optionId)) {
                        if (partTypeCombos[partType].getSelectionIndex() != j) {
                            partTypeCombos[partType].select(j);
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    String msg = "The available reasoning frameworks do not support type '"
                            + optionId
                            + "', which is used in one of the six parts of this scenario.";
                    MessageDialog.openError(getShell(), "Error", msg);
                    throw new IllegalStateException(msg);
                }
                // 3rd column: unit
                optionId = parts[i].getUnitId();
                found = false;
                for (int j = 0; j < partUnitIds[partType].size(); j++) {
                    if (partUnitIds[partType].get(j).equals(optionId)) {
                        if (partUnitCombos[partType].getSelectionIndex() != j) {
                            partUnitCombos[partType].select(j);
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    String msg = "The available reasoning frameworks do not support unit '"
                            + optionId
                            + "', which is used in one of the six parts of this scenario.";
                    MessageDialog.openError(getShell(), "Error", msg);
                    throw new IllegalStateException(msg);
                }
                // 4th column: value
                String val = parts[i].getValue();
                if (val == null) {
                    val = "";
                }
                if (!val.equals(partValueTexts[partType])) {
                    partValueTexts[partType].setText(val);
                }
            }
        }
    }

    /**
     * Add the options defined for the given ScenarioTypeVO to the combo boxes of type and unit of
     * the six parts. If scenarioType is null, fill combos with options of ALL existing scenario
     * types using a recursive call.
     * 
     * @param scenarioType
     */
    private void fillSixPartsCombos(ScenarioTypeVO scenarioType) {
        if (scenarioType == null) {
            for (Iterator it = activeScenarioTypes.values().iterator(); it.hasNext();) {
                ScenarioTypeVO vo = (ScenarioTypeVO) it.next();
                fillSixPartsCombos(vo); // recursive call
            }
        } else {
            ScenarioPartMetadataVO parts[] = scenarioType.getPartsMetadata();
            for (int i = 0; i < parts.length; i++) {
                if (parts[i] != null) {
                    int partType = parts[i].getPartType();
                    // type combo box
                    for (Iterator it = parts[i].getTypes().iterator(); it.hasNext();) {
                        PartOptionsVO option = (PartOptionsVO) it.next();
                        partTypeCombos[partType].add(option.getTextToUser());
                        int itemCount = partTypeCombos[partType].getItemCount();
                        partTypeIds[partType].add(itemCount - 1, option.getId());
                    }
                    // unit combo box
                    for (Iterator it = parts[i].getUnits().iterator(); it.hasNext();) {
                        PartOptionsVO option = (PartOptionsVO) it.next();
                        partUnitCombos[partType].add(option.getTextToUser());
                        int itemCount = partUnitCombos[partType].getItemCount();
                        partUnitIds[partType].add(itemCount - 1, option.getId());
                    }
                }
            }
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the stimulus text in
     * the six parts.
     */
    private class AddAsStimulusAction extends Action {

        private AddAsStimulusAction() {
            setText("Add as &stimulus");
        }

        public void run() {
            // Copy the marked text to the Stimulus text
            partDescTexts[ScenarioPartMetadataVO.STIMULUS].setText(scenarioText.getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the source of
     * stimulus text in the six parts.
     */
    private class AddAsSourceOfStimulusAction extends Action {

        private AddAsSourceOfStimulusAction() {
            setText("Add as s&ource of stimulus");
        }

        public void run() {
            // Copy the marked text to the Source of stimulus text
            partDescTexts[ScenarioPartMetadataVO.SOURCE_OF_STIMULUS].setText(scenarioText
                    .getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the artifact text in
     * the six parts.
     */
    private class AddAsArtifactAction extends Action {

        private AddAsArtifactAction() {
            setText("Add as &artifact");
        }

        public void run() {
            // Copy the marked text to the Artifact text
            partDescTexts[ScenarioPartMetadataVO.ARTIFACT].setText(scenarioText.getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the environment text
     * in the six parts.
     */
    private class AddAsEnvironmentAction extends Action {

        private AddAsEnvironmentAction() {
            setText("Add as &environment");
        }

        public void run() {
            // Copy the marked text to the Environment text
            partDescTexts[ScenarioPartMetadataVO.ENVIRONMENT].setText(scenarioText
                    .getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the response text in
     * the six parts.
     */
    private class AddAsResponseAction extends Action {

        private AddAsResponseAction() {
            setText("Add as &response");
        }

        public void run() {
            // Copy the marked text to the Response text
            partDescTexts[ScenarioPartMetadataVO.RESPONSE].setText(scenarioText.getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: copies the marked text to the response measure
     * text in the six parts.
     */
    private class AddAsResponseMeasureAction extends Action {

        private AddAsResponseMeasureAction() {
            setText("Add as response &measure");
        }

        public void run() {
            // Copy the marked text to the Response measure text
            partDescTexts[ScenarioPartMetadataVO.RESPONSE_MEASURE].setText(scenarioText
                    .getSelectionText());
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: standard copy (to clipboard)
     */
    private class CopyAction extends Action {

        private CopyAction() {
            setText("&Copy");
        }

        public void run() {
            scenarioText.copy();
            return;
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: standard cut (to clipboard)
     */
    private class CutAction extends Action {

        private CutAction() {
            setText("Cu&t");
        }

        public void run() {
            scenarioText.cut();
        }
    }

    /***********************************************************************************************
     * Action of popup menu of description text box: standard paste (from clipboard)
     */
    private class PasteAction extends Action {

        private PasteAction() {
            setText("&Paste");
        }

        public void run() {
            scenarioText.paste();
        }
    }
}