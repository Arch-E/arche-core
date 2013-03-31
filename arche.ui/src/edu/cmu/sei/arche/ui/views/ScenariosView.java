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

package edu.cmu.sei.arche.ui.views;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.ui.dialog.ScenarioDialog;
import edu.cmu.sei.arche.vo.AnalysisResultVO;
import edu.cmu.sei.arche.vo.PartOptionsVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.ScenarioPartMetadataVO;
import edu.cmu.sei.arche.vo.ScenarioPartVO;
import edu.cmu.sei.arche.vo.ScenarioTypeVO;
import edu.cmu.sei.arche.vo.ScenarioVO;

/**
 * View for Scenarios.
 * 
 * @author Neel Mullick
 */
public class ScenariosView extends ViewPart implements IPropertyChangeListener {

    private Action                 delScenarioAction;

    private Action                 mappedResponsibilitiesAction;

    private Action                 newScenarioAction;

    private Action                 editScenarioAction;

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The search text box */
    private Text                   searchText;

    /** The table of scenarios */
    private Table                  table;

    /** Set the table column property names */
    private final String           MARKER_COLUMN        = "Marker";

    private final String           STATUS_MARKER_COLUMN = "StatusMarker";

    private final String           TRAFFIC_LIGHT_COLUMN = "TrafficLight";

    private final String           DESCRIPTION_COLUMN   = "Description";

    private final String           TYPE_COLUMN          = "ScenarioType";

    private final String           STIMULUS_COLUMN      = "Stimulus";

    private final String           STIMULUS_TYPE_COLUMN = "StimulusType";

    private final String           SOURCE_COLUMN        = "Source";

    private final String           ARTIFACT_COLUMN      = "Artifact";

    private final String           ENVIRONMENT_COLUMN   = "Environment";

    private final String           RESPONSE_COLUMN      = "Response";

    private final String           MEASURE_COLUMN       = "Measure";

    private final String           VALUE_COLUMN         = "Value";

    private final String           TT_NOT_ANALYZED      = "The scenario has not been analyzed.";

    private final String           TT_SATISFIED         = "The scenario is satisfied.";

    private final String           TT_NOT_SATISFIED     = "The scenario is not satisfied.";

    private final String           NO_CHANGE            = "The change had no impact on this scenario.";

    private final String           POSITIVE_IMPACT      = "The last change had a positive impact on this scenario.";

    private final String           NEGATIVE_IMPACT      = "The last change had a negative impact on this scenario.";
    /** Array of column names */
    private String[]               columnNames;

    /** The sorter of scenarios */
    private ScenariosSorter        sorter;

    /** The project VO for this instance of the scenario view */
    private ProjectVO              project;

    /** The HashSet that is to be the input for the Scenarios view */
    HashSet                        scenarios;

    /** Arraylist of scenario parts */
    private ArrayList              scenarioParts;

    /** Hashtable of all PartOptions (to display the Stimulus Type */
    private Hashtable              allPartOptions = null;

    /** The parent control for the scenarios view */
    private Composite              parent;

    /** Question parser to retrieve question text (replacing tags with dynamic data) */
    private QuestionParser         questionParser;

    
    /**
     * Maps scenario type id String --> ScenarioTypeVO. Contains only scenario types of active RFs
     */
    private Hashtable			   activeScenarioTypes = null;
    
    /**
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {

        this.parent = parent;

        // Initialize the question Parser.
        questionParser = ArchEUIPlugin.getDefault().getQuestionParser();

        // Put the name of the columns in an array. In this view, the column names don't vary,
        // so they can be set once. In views that have parameters, populateColumnNames have to
        // be called each time we don setInputAndRefresh cause the number of cols vary.
        populateColumnNames();

        //Function to initialize relevant member variables of this class
        initPage();

        // Create layout
        GridLayout layout = new GridLayout(3, false);
        parent.setLayout(layout);

        //creating the dynamic search label
        Label searchLabel = new Label(parent, SWT.RIGHT);
        searchLabel.setText("Description contains:");
        // Dynamic search label
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 200;
        searchLabel.setLayoutData(gdSearchLabel);

        //creating the dynamic search text box and associating the listener to it
        searchText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        searchText.addListener(SWT.Modify, new Listener() {

            public void handleEvent(Event e) {
                //System.out.println(e.widget + "Filtering...");
                tableViewer.refresh();
            }
        });
        // When double-click in the dynamic search text box,
        // select all text in the box.
        searchText.addListener(SWT.MouseDoubleClick, new Listener() {

            public void handleEvent(Event event) {
                searchText.selectAll();
            }
        });

        // Dynamic search textbox
        GridData gdSearchText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gdSearchText.widthHint = 200;
        searchText.setLayoutData(gdSearchText);

        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        // Table
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        // Create the table Columns
        createTableColumns();

        //create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new ScenariosContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new ScenarioFilter()); //calls refresh

        createContextMenu();

        delScenarioAction = new DeleteScenarioAction();
        mappedResponsibilitiesAction = new MappedResponsibilitiesAction();
        newScenarioAction = new NewScenarioAction();
        editScenarioAction = new EditScenarioAction();
        ArchEUIPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    }

    /**
     * Function to initialize member variables that will be used by the view
     */
    public void initPage() {
        // If the search Text box is created, then clear the content
        if (searchText != null) {
            searchText.setText("");
        }

        //      retrieve the ProjectVO from the workbench
        project = ArchEUIPlugin.getDefault().getProjectVo();
    }

    /**
     * Function to populate the column names
     */
    private void populateColumnNames() {
        columnNames = new String[13];
        int index = 0;
        columnNames[index++] = MARKER_COLUMN;
        columnNames[index++] = STATUS_MARKER_COLUMN;
        columnNames[index++] = TRAFFIC_LIGHT_COLUMN;
        columnNames[index++] = DESCRIPTION_COLUMN;
        columnNames[index++] = TYPE_COLUMN;
        columnNames[index++] = STIMULUS_COLUMN;
        columnNames[index++] = STIMULUS_TYPE_COLUMN;
        columnNames[index++] = SOURCE_COLUMN;
        columnNames[index++] = ARTIFACT_COLUMN;
        columnNames[index++] = ENVIRONMENT_COLUMN;
        columnNames[index++] = RESPONSE_COLUMN;
        columnNames[index++] = MEASURE_COLUMN;
        columnNames[index++] = VALUE_COLUMN;
    }

    /**
     * Get ArchE facade.
     */
    private ArchEFacade getArchEFacade() {
        return ArchEUIPlugin.getDefault().getArchEFacade();
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        tableViewer.getControl().setFocus();
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    public void dispose() {
        ArchEUIPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
        super.dispose();
    }

    /**
     * Configure the context menu to be lazily populated on each pop-up.
     */
    private void createContextMenu() {
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });
        Menu menu = menuMgr.createContextMenu(table);
        table.setMenu(menu);
        // Be sure to register it so that other plug-ins can add actions.
        getSite().registerContextMenu(menuMgr, getSite().getSelectionProvider());
    }

    /**
     * Add the options to the popup menu
     * 
     * @param menu
     */
    private void fillContextMenu(IMenuManager menu) {
        menu.add(newScenarioAction);
        menu.add(editScenarioAction);
        menu.add(delScenarioAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(mappedResponsibilitiesAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$

        // if projectVO is null, then dissable menu items.
        boolean isEnabled = false;
        isEnabled = (project == null ? false : true);
        newScenarioAction.setEnabled(isEnabled);
        // if no item is selected, gray out 'delete' option
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        delScenarioAction.setEnabled(isEnabled);
        editScenarioAction.setEnabled(isEnabled);
        mappedResponsibilitiesAction.setEnabled(isEnabled);
    }

    /**
     * A property in the ArchE user preferences has changed. Here we pay heed to the property that
     * defines the color for information whose source is ArchE.
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals("src_ArchE_color")) {
            Color newColor = createColor(Display.getDefault(), "src_ArchE_color");
            ScenariosLabelProvider labelProvider = (ScenariosLabelProvider) tableViewer
                    .getLabelProvider();
            labelProvider.setSrcIsArchEColor(newColor);
            tableViewer.refresh();
        }
    }

    /**
     * Utility method that returns a color instance based on data from a preference field.
     */
    private Color createColor(Display display, String preference) {
        RGB rgb = PreferenceConverter.getColor(ArchEUIPlugin.getDefault().getPreferenceStore(),
                                               preference);
        return new Color(display, rgb);
    }

    /**
     * NewScenarioAction INNER CLASS
     */
    private class NewScenarioAction extends Action {

        private NewScenarioAction() {
            setText("New scenario");
        }

        public void run() {

            newScen();
        }

        /**
         * Add a Scenario
         */
        private void newScen() {

            //System.out.println("[NewScenarioAction] run");
            ScenarioDialog dialog = new ScenarioDialog(parent.getShell(), null);
            dialog.open();
        }
    }

    /**
     * EditScenarioAction INNER CLASS
     */
    private class EditScenarioAction extends Action {

        private EditScenarioAction() {
            setText("Edit");
        }

        public void run() {

            editScen();
        }

        /**
         * Edit a Scenario
         */
        private void editScen() {
            //System.out.println("[EditScenarioAction] run");
            ScenarioVO scenario = (ScenarioVO) (tableViewer.getTable().getSelection()[0].getData());
            ScenarioDialog dialog = new ScenarioDialog(parent.getShell(), scenario);
            dialog.open();
        }
    }

    /**
     * DeleteScenarioAction INNER CLASS
     */
    private class DeleteScenarioAction extends Action {

        private DeleteScenarioAction() {
            setText("Delete");
        }

        public void run() {
            delScen();
        }

        /**
         * Delete a Scenario
         */
        private void delScen() {

            MessageDialog closeMsgDialog = new MessageDialog(parent.getShell(), "Delete", null,
                    "Are you sure you want to delete the selected scenario?",
                    MessageDialog.QUESTION, new String[] { "Yes", "No"}, 1);
            closeMsgDialog.open();
            if (closeMsgDialog.getReturnCode() == 0) {
                Item[] sel = table.getSelection();
                int factId;
                //Delete all selected Scenarios
                for (int i = 0; i < sel.length; i++) {
                    factId = ((ScenarioVO) sel[i].getData()).getFactId();
                    getArchEFacade().deleteScenario(factId);
                }
            }
        }
    }

    /**
     * MappedResponsibilitiesAction INNER CLASS
     */
    private class MappedResponsibilitiesAction extends Action {

        private MappedResponsibilitiesAction() {
            setText("Mapped responsibilities");
        }

        public void run() {
            mappedResps();
        }

        /**
         * Displays the responsibilities mapped to the selected scenario in the
         * ScenarioRespMappingView.
         */
        private void mappedResps() {
            Item[] sel = table.getSelection();

            String scenarioStr = ((ScenarioVO) sel[0].getData()).getDescription();

            IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                    .getActiveWorkbenchWindow();
            try {
                IWorkbenchPage page = window.getActivePage();
                ScenarioRespMappingView view = (ScenarioRespMappingView) page
                        .showView("SEI.ArchE.UI.ScenarioRespMappingView");
                //              Search mappings of the selected scenario with responsibilities
                view.searchText.setText(scenarioStr);
            } catch (Exception e) {

            }
        }
    }

    /**
     * Create table Columns
     */
    private void createTableColumns() {

        //This class handles selections of the column headers. Selection of the column header will
        //cause resorting of the shown elements using that column's sorter. Repeated selection of
        //the header will toggle sorting order (ascending versus descending).
        SelectionListener headerListener = new SelectionAdapter() {

            /**
             * Handles the case of user selecting the header area.
             * <p>
             * If the column has not been selected previously, it will set the sorter of that column
             * to be the current sorter. Repeated presses on the same column header will toggle
             * sorting order (ascending/descending).
             */
            public void widgetSelected(SelectionEvent e) {
                // column selected - need to sort
                int column = table.indexOf((TableColumn) e.widget);
                if(column > 0){
	                if (column == sorter.getTopPriority()) {
	                    sorter.reverseTopPriority();
	                } else {
	                    sorter.setTopPriority(column);
	                }
                	tableViewer.refresh();
            	}
            }
        };

        //Clear all columns first;
        for (int i = table.getColumnCount() - 1; i >= 0; i--) {
            table.getColumn(i).dispose();
        }

        int columnIndex = 0;
        // 1st column with marker
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 2nd column with status marker
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 3rd column with traffic light
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 4th column with description
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 5th column with scenariotype
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 6th column with stimulus
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 7th column with stimulus type
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 8th column with source
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 9th column with artifact
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 10th column with environment
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 11th column with response
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 12th column with measure
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 13th column with values
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(80);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);

    }

    /**
     * This view is being created or refreshed after a facade operation or open/close project
     * operation. This method updates all aspects of the tableViewer that can vary in such cases,
     * including setInput.
     */
    private void refreshTableViewer() {
        tableViewer.setInput(getScenarios());
        if (!(tableViewer.getLabelProvider() instanceof ScenariosLabelProvider)) {
            // The view is being created

            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new ScenariosLabelProvider()); // calls refresh

            // THE setColumnProperties STATEMENT IS INSIDE THIS IF BECAUSE THE COLUMNS DON'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, setColumnProperties IS CALLED EVERYTIME.
            tableViewer.setColumnProperties(columnNames);

            // THE CREATION OF THE SORTER IS INSIDE THIS IF BECAUSE THE SORTER DOESN'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, SORTER HAS TO BE RECREATED EVERYTIME.
            sorter = new ScenariosSorter(); // instantiate and reset sorter
            tableViewer.setSorter(sorter); // calls refresh because sorter changed
        } else {
            // The view is being refreshed by calling setInputAndRefresh
            ((ScenariosSorter) tableViewer.getSorter()).resetState();
            tableViewer.refresh();
        }
    }

    /**
     * Returns a Set with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     * 
     * @return
     */
    private Set getScenarios() {
        Set scens = null;
        if (project != null) {
            scens = project.getScenarios();
            
        }
        if (scens == null) {
            scens = new HashSet();
            scens.add("Please open or create a project.");
        } else {
            if(allPartOptions == null)
            	allPartOptions = new Hashtable();
            else
            	allPartOptions.clear();
            
            if(activeScenarioTypes == null)
            	activeScenarioTypes = new Hashtable();
            else
            	activeScenarioTypes.clear();
            
            for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
                if (((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
                    for (Iterator it = rf.getScenarioTypes().iterator(); it.hasNext();) {
                        ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
                        activeScenarioTypes.put(scenarioType.getId(), scenarioType);
                    }
                }                
                for (Iterator it = rf.getScenarioTypes().iterator(); it.hasNext();) {
                    ScenarioTypeVO scenarioType = (ScenarioTypeVO) it.next();
                    for (int i = 0; i < scenarioType.getPartsMetadata().length; i++) {
                        ScenarioPartMetadataVO scenarioPartMetaData = (ScenarioPartMetadataVO) scenarioType
                                .getPartsMetadata()[i];
                        if (scenarioPartMetaData != null
                                && scenarioPartMetaData.getPartType() == ScenarioPartMetadataVO.STIMULUS) {
                            allPartOptions.put(scenarioType, scenarioPartMetaData.getTypes());
                        }
                    }
                }
            }
            
            // Refresh scenariotypes
            Iterator iter = scens.iterator();
            while(iter.hasNext()){
            	ScenarioVO scenario = (ScenarioVO)iter.next();
            	ScenarioTypeVO scenarioType = scenario.getScenarioType();
            	if(scenarioType == null){
                	scenarioType = (ScenarioTypeVO) activeScenarioTypes.get(scenario.getQuality());    
                	scenario.setScenarioType(scenarioType);        		
            	}
            	if(!activeScenarioTypes.containsKey(scenario.getQuality()))
            		scenario.setScenarioType(null);
            }                
        }
         
        return scens;
    }

    /**
     * @return Returns the tableViewer.
     */
    public ColoredCellTableViewer getViewer() {
        return tableViewer;
    }

    /**
     * Called when this view now points to a different project. It changes the viewer input based on
     * the currently open project and calls refresh on the viewer.
     */
    public void setInputAndRefresh() {

        initPage();

        refreshTableViewer();
        table.redraw();

    }

    /**
     * Label provider for the Scenarios table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     * 
     * @author Neel Mullick
     */
    class ScenariosLabelProvider extends LabelProvider implements ITableLabelProvider,
            ICellColorProvider {

        /** Color to paint cells with data created by ArchE. */
        private Color srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
        }

        /**
         * Returns the correct icon for columns that show icons. Doesn't do anything for columns
         * that show text only.
         * 
         * @param element Object that if correct should be correctly case to a ScenarioVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed in the given column or null if the column does not
         *         show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            // If project is not open
            if (!(element instanceof ScenarioVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            // If is the first column -Marker
            if (columnIndex == 0) {
                ScenarioVO s = (ScenarioVO) element;
                ArrayList questions = (ArrayList) project.getQuestions();
                QuestionToUserVO question = null;
                int sFactId = s.getFactId();
                Iterator itq = questions.iterator();
                int[] temp;
                boolean hasQuestion = false;
                boolean hasProblem = false;

                //TODO: add problems checker here (for later when problems view is implemented in
                // subsequent versions).
                if (hasProblem) {

                    return (Image) MarkerUtil.getImage("problem");
                }

                // Iterate questionVO list to get the affected factids,
                // check if the sFactId is in them.
                loop1: while (itq.hasNext()) {
                    question = (QuestionToUserVO) itq.next();
                    temp = question.getAffectedFacts();

                    int temp1[] = new int[temp.length + 1];
                    int length = temp.length;
                    System.arraycopy(temp, 0, temp1, 0, length);
                    temp = temp1;
                    temp[length] = question.getParentFactId();

                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] == sFactId) {
                            hasQuestion = true;

                            // find the table item widget matched with this data element
                            // save the reference of question to this table item
                            TableItem[] children = table.getItems();
                            for (int j = 0; j < children.length; j++) {
                                TableItem item = children[j];
                                Object data = item.getData();
                                if (data == element) {
                                    item.setData("question", question);
                                    break;
                                }
                            }
                            break loop1;
                        }
                    }
                }

                if (hasQuestion) {
                    return (Image) MarkerUtil.getImage("question");
                }

            }

            if (columnIndex == 1) {
                ScenarioVO s = (ScenarioVO) element;
                TableItem[] children = table.getItems();
                for (int j = 0; j < children.length; j++) {
                    TableItem item = children[j];
                    Object data = item.getData();
                    if (data == element) {
                        item.setData("status", new Integer(s.getStatus()));
                        if (s.getStatus() != ScenarioVO.NOT_ANALYZED) {
                            String values = "\n";
                            for (Iterator it = s.getAnalysisResults().iterator(); it.hasNext();) {
                                AnalysisResultVO vo = (AnalysisResultVO) it.next();
                                values += "value = " + vo.getValue() + "\n";
                            }
                            item.setData("values", new String(values));
                        }
                    }
                }
                switch (s.getStatus()) {
                case ScenarioVO.NOT_ANALYZED:
                    return (Image) MarkerUtil.getImage("notanalyzed");
                case ScenarioVO.FULLY_SATISFIED:
                    return (Image) MarkerUtil.getImage("satisfied");
                case ScenarioVO.NOT_SATISFIED:
                    return (Image) MarkerUtil.getImage("notsatisfied");
                //Reserved for later versions of ArchE.
                //case ScenarioVO.PARTIALLY_SATISFIED:
                //    return (Image) MarkerUtil.getImage("partiallysatisfied");
                }
            }
            
            if (columnIndex == 2) {
                ScenarioVO s = (ScenarioVO) element;
                TableItem[] children = table.getItems();
         
                for (int j = 0; j < children.length; j++) {
                    TableItem item = children[j];
                    Object data = item.getData();
                    if (data == element) {
                            String values = "\n";
                           
                            if (s.getStatus() != ScenarioVO.NOT_ANALYZED) {
	                            for (Iterator it = s.getAnalysisResults().iterator(); it.hasNext();) {
	                                AnalysisResultVO vo = (AnalysisResultVO) it.next();
//	                                if(s.getPreviousValue().equals("0"))
//	                                {
//	                                	s.setPreviousValue(vo.getPreviousValue());
//	//                                	System.out.println("Before: " + vo.getValue() + " after " + vo.getPreviousValue());
//	                                }
	                                
	                                if(!s.getPreviousValue().equals(vo.getPreviousValue()))
	                                {
	                                	s.setPreviousValue(vo.getPreviousValue());
	//                                	System.out.println("Before: " + vo.getValue() + " after " + vo.getPreviousValue());
	                                }
	                                
	                                float currentValue = new Float(vo.getValue() == null ? "0" : vo.getValue()).floatValue();
	                                float previousValue = new Float(vo.getPreviousValue() == null ? "0" : vo.getPreviousValue()).floatValue();
	
	                                float result = currentValue / previousValue;                                
	
	                                if(currentValue == previousValue) // For the case previousValuse == 0
	                                	result = 1;
	                                
	                                float result1 = result;
	                                values += "Current value = " + currentValue + "\n";
	                                values += "Previous value = " + previousValue + "\n";
	                                if(result1 > 1)
	                                {
	                                	result1 = (result1 - 1) * 100; 
	                                    values += "Negative change percentage = " + result1 + "%\n";
	                                }
	                                else if(result1 < 1)
	                                {
	                                	result1 = (1 - result1) * 100; 
	                                	values += "Positive change percentage = " + result1 + "%\n";
	                                }
	                                else if(result1 == 1){
	                                	result1 = (result1 - 1); 
	                                	values += "Percent change = " + result1 + "%\n";
	                                }
	//                                System.out.println(values);
	                                item.setData("values1", new String(values));
	                                
	                        		
	                               if(((result > .99) &&  (result < 1.01))){
	                           	        item.setData("traffic_light", new Integer(0)); 
	                                    return (Image) MarkerUtil.getImage("partiallysatisfiedChange");
	                               } 
	                               if(result > 1.01 ){
	                            	   item.setData("traffic_light", new Integer(1));
	                                   return (Image) MarkerUtil.getImage("notsatisfiedChange");
	                               } 
	                               if(result < .99){
	                            	   item.setData("traffic_light", new Integer(2));
	                                   return (Image) MarkerUtil.getImage("satisfiedChange");
	                               }
	                               
	                            }
                            //item.setData("values", new String(values));                       
                            }
                            else{
                            	// For unanalyzed scenario
                       	        item.setData("traffic_light", new Integer(3)); 
                            }
                    }
                }
            }
            return null;
        }

        /**
         * Returns the text to be shown for a given ScenarioVO object and the specified column.
         * Doesn't do anything for columns that show icons only.
         * 
         * @param element Object that if correct should be correctly case to a ScenarioVO object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that Object in the given column or the error message
         *         if the ProjectVO was null.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof ScenarioVO) {
                ScenarioVO scen = (ScenarioVO) element;
                switch (columnIndex) {
                case 3:
                    String desc = scen.getDescription();
                    return (desc != null ? desc : "");
                case 4:
                    String name = null;
                    ScenarioTypeVO scenType = scen.getScenarioType();
                    if (scenType != null) {
                        name = scen.getScenarioType().getName();
                    }
                    return (name != null ? name : "");
                case 5:
                    String text = null;
                    ScenarioPartVO part = scen.getParts()[0];
                    if (part != null) {
                        text = part.getText();
                    }
                    return (text != null ? text : "");
                case 6:
                    String textStimulusType = null;
                    ScenarioPartVO partStimulus = scen.getParts()[0];
                    ScenarioTypeVO type = scen.getScenarioType();

                    if (partStimulus != null && type != null) {
                        if (!(partStimulus.getTypeId() == null || partStimulus.getTypeId().trim()
                                .equalsIgnoreCase(""))) {
                            if (allPartOptions.get(type) != null) {
                                List listOptions = (List) allPartOptions.get(type);
                                for (Iterator iOptions = listOptions.iterator(); iOptions.hasNext();) {
                                    PartOptionsVO partOption = (PartOptionsVO) iOptions.next();
                                    if (partOption != null
                                            && partOption.getId()
                                                    .equalsIgnoreCase(partStimulus.getTypeId())) {
                                        textStimulusType = partOption.getTextToUser();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    return (textStimulusType != null ? textStimulusType : "");
                case 7:
                    String text1 = null;
                    ScenarioPartVO part1 = scen.getParts()[1];
                    if (part1 != null) {
                        text1 = part1.getText();
                    }
                    return (text1 != null ? text1 : "");
                case 8:
                    String text2 = null;
                    ScenarioPartVO part2 = scen.getParts()[2];
                    if (part2 != null) {
                        text2 = part2.getText();
                    }
                    return (text2 != null ? text2 : "");
                case 9:
                    String text3 = null;
                    ScenarioPartVO part3 = scen.getParts()[3];
                    if (part3 != null) {
                        text3 = part3.getText();
                    }
                    return (text3 != null ? text3 : "");
                case 10:
                    String text4 = null;
                    ScenarioPartVO part4 = scen.getParts()[4];
                    if (part4 != null) {
                        text4 = part4.getText();
                    }
                    return (text4 != null ? text4 : "");
                case 11:
                    String text5 = null;
                    ScenarioPartVO part5 = scen.getParts()[5];
                    if (part5 != null) {
                        text5 = part5.getText();
                    }
                    return (text5 != null ? text5 : "");
                case 12:
                    String text5Value = null;
                    ScenarioPartVO part5Value = scen.getParts()[5];
                    if (part5Value != null) {
                        text5Value = part5Value.getValue();
                    }
                    return (text5Value != null ? text5Value : "");

                default:
                    return "";
                }
            } else {
                /* Element is a String with an error message */
                if (columnIndex == 2) {
                    return element.toString();
                }
                return "";
            }
        }

        /**
         * Returns the foreground color for a given ScenarioVO object and the specified column. If
         * ArchE created the data, the color for the corresponding cell is as defined in the project
         * preferences. Otherwise, the color is the default (black).
         * 
         * @param element Object that if correct should be correctly case to a ScenarioVO object
         * @param columnIndex zero-based index of the column
         * @return Color object in which the contents are to be displayed for the element in the
         *         given column or or null if the default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            return null;
        }

        /**
         * Returns the background color for a given ScenarioVO object and the specified column.
         * We're not using different background colors in ArchE, so this method returns null so the
         * cell has default background (white)
         * 
         * @param element Object that if correct should be correctly case to a ScenarioVO object
         * @param columnIndex zero-based index of the column
         * @return Color object that is to be the background of the contents for the element in the
         *         given column or or null if the default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
         */
        public Color getBackground(Object element, int columnIndex) {
            return null;
        }

        /**
         * @return Returns the srcIsArchEColor.
         */
        public Color getSrcIsArchEColor() {
            return srcIsArchEColor;
        }

        /**
         * @param srcIsArchEColor The srcIsArchEColor to set.
         */
        public void setSrcIsArchEColor(Color srcIsArchEColor) {
            this.srcIsArchEColor.dispose();
            this.srcIsArchEColor = srcIsArchEColor;
        }
    }

    /**
     * This is the sorter for the Scenarios View.
     */
    class ScenariosSorter extends ViewerSorter {

        final static int ASCENDING         = 1;

        final static int DEFAULT_DIRECTION = 0;

        final static int DESCENDING        = -1;

        final static int SCENARIO_STATUS   = 1;

        final static int SCENARIO_CHANGE   = 2;
        
        final static int DESCRIPTION       = 3;

        final static int TYPE              = 4;

        final static int STIMULUS          = 5;

        final static int STIMULUSTYPE      = 6;

        final static int SOURCE            = 7;

        final static int ARTIFACT          = 8;

        final static int ENVIRONMENT       = 9;

        final static int RESPONSE          = 10;

        final static int MEASURE           = 11;

        final static int VALUE             = 12;

        private int[]    priorities;

        private int[]    directions;

        int[]            defaultPriorities = new int[columnNames.length];

        int[]            defaultDirections = new int[columnNames.length];

        /**
         * Creates a new sorter.
         */
        public ScenariosSorter() {
            defaultDirections[0] = ASCENDING;
            defaultDirections[1] = ASCENDING;
            defaultDirections[2] = ASCENDING;
            for (int i = 3; i < columnNames.length; i++) {
                defaultDirections[i] = DESCENDING;
            }
            for (int i = 0; i < columnNames.length - 1; i++) {
                defaultPriorities[i] = i + 1;
            }
            resetState();
        }

        /**
         * Compares two markers, sorting first by the main column of this sorter, then by subsequent
         * columns, depending on the column sort order.
         */
        public int compare(Viewer viewer, Object e1, Object e2) {
            if ((e1 instanceof ScenarioVO) && (e2 instanceof ScenarioVO)) {
                ScenarioVO s1 = (ScenarioVO) e1;
                ScenarioVO s2 = (ScenarioVO) e2;
                return compareColumnValue(s1, s2, 0);
            }
            return 0;
        }

        /**
         * Set the column to sort
         * 
         * @param priority
         */
        public void setTopPriority(int priority) {
            if (priority < 0 || priority >= priorities.length) {
                return;
            }
            int index = -1;
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == priority) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                resetState();
                return;
            }
            //shift the array
            for (int i = index; i > 0; i--) {
                priorities[i] = priorities[i - 1];
            }
            priorities[0] = priority;
            directions[priority] = defaultDirections[priority];
        }

        /**
         * Get the current top priority
         * 
         * @return
         */
        public int getTopPriority() {
            return priorities[0];
        }

        /**
         * Get the current top priority
         * 
         * @return
         */
        public int[] getPriorities() {
            return priorities;
        }

        /**
         * Set priority direction
         * 
         * @param direction
         */
        public void setTopPriorityDirection(int direction) {
            if (direction == DEFAULT_DIRECTION) {
                directions[priorities[0]] = defaultDirections[priorities[0]];
            } else if (direction == ASCENDING || direction == DESCENDING) {
                directions[priorities[0]] = direction;
            }
        }

        /**
         * Get top priority direction
         * 
         * @return
         */
        public int getTopPriorityDirection() {
            return directions[priorities[0]];
        }

        /**
         * Reverse the priority
         */
        public void reverseTopPriority() {
            directions[priorities[0]] *= -1;
        }

        /**
         * Reset state.
         */
        public void resetState() {
            priorities = new int[defaultPriorities.length];
            System.arraycopy(defaultPriorities, 0, priorities, 0, priorities.length);
            directions = new int[defaultDirections.length];
            System.arraycopy(defaultDirections, 0, directions, 0, directions.length);
        }

        /**
         * Compares two markers, based only on the value of the specified column.
         */
        private int compareColumnValue(ScenarioVO s1, ScenarioVO s2, int depth) {
            if (depth >= priorities.length) {
                return 0;
            }
            int columnIndex = priorities[depth];
            int direction = directions[columnIndex];
            int result = 0;
            switch (columnIndex) {
            case SCENARIO_STATUS:
                /* scenario status */
                result = compareFloatValue(String.valueOf(s1.getStatus()), String.valueOf(s2
                        .getStatus()));
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case SCENARIO_CHANGE:
                /* scenario change */
        		if(s1.getStatus() != ScenarioVO.NOT_ANALYZED && s2.getStatus() != ScenarioVO.NOT_ANALYZED){
	            	try {
		            	double d1 = Double.parseDouble(s1.getCurrentValue()) - Double.parseDouble(s1.getPreviousValue());
		            	double d2 = Double.parseDouble(s2.getCurrentValue()) - Double.parseDouble(s2.getPreviousValue());
		                result = Double.compare(d1,d2);
		                if (result == 0) {
		                    return compareColumnValue(s1, s2, depth + 1);
		                }
		                return result * direction;            	
	            	} catch (NumberFormatException e){
	                    return compareColumnValue(s1, s2, depth + 1);
	            	}
        		}
        		else if(s1.getStatus() != ScenarioVO.NOT_ANALYZED && s2.getStatus() == ScenarioVO.NOT_ANALYZED){
        			result = 1;
	                return result * direction;            	
        		}
        		else if(s1.getStatus() == ScenarioVO.NOT_ANALYZED && s2.getStatus() != ScenarioVO.NOT_ANALYZED){
        			result = -1;
	                return result * direction;            	
        		}
        		else if(s1.getStatus() == ScenarioVO.NOT_ANALYZED && s2.getStatus() == ScenarioVO.NOT_ANALYZED){
        			result = 0;
	                return result * direction;            	
        		}
            case DESCRIPTION:
                /* description */
                result = collator.compare(s1.getDescription(), s2.getDescription());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case TYPE:
                /* type */
                result = collator.compare(s1.getScenarioType().getName(), s2.getScenarioType()
                        .getName());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case STIMULUS:
                /* stimulus */
                result = collator.compare(s1.getParts()[0].getText(), s2.getParts()[0].getText());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case STIMULUSTYPE:
                /* stimulus type */
                String s1StimulusType = null;
                String s2StimulusType = null;
                ScenarioPartVO s1PartStimulus = s1.getParts()[0];
                ScenarioPartVO s2PartStimulus = s2.getParts()[0];
                ScenarioTypeVO s1Type = s1.getScenarioType();
                ScenarioTypeVO s2Type = s2.getScenarioType();

                if (s1PartStimulus != null && s1Type != null) {
                    if (!(s1PartStimulus.getTypeId() == null || s1PartStimulus.getTypeId().trim()
                            .equalsIgnoreCase(""))) {
                        if (allPartOptions.get(s1Type) != null) {
                            List listOptions = (List) allPartOptions.get(s1Type);
                            for (Iterator iOptions = listOptions.iterator(); iOptions.hasNext();) {
                                PartOptionsVO partOption = (PartOptionsVO) iOptions.next();
                                if (partOption != null
                                        && partOption.getId()
                                                .equalsIgnoreCase(s1PartStimulus.getTypeId())) {
                                    s1StimulusType = partOption.getTextToUser();
                                    break;
                                }
                            }
                        }
                    }
                }
                s1StimulusType = (s1StimulusType != null) ? s1StimulusType : "";

                if (s2PartStimulus != null && s2Type != null) {
                    if (!(s2PartStimulus.getTypeId() == null || s2PartStimulus.getTypeId().trim()
                            .equalsIgnoreCase(""))) {
                        if (allPartOptions.get(s2Type) != null) {
                            List listOptions = (List) allPartOptions.get(s2Type);
                            for (Iterator iOptions = listOptions.iterator(); iOptions.hasNext();) {
                                PartOptionsVO partOption = (PartOptionsVO) iOptions.next();
                                if (partOption != null
                                        && partOption.getId()
                                                .equalsIgnoreCase(s2PartStimulus.getTypeId())) {
                                    s2StimulusType = partOption.getTextToUser();
                                    break;
                                }
                            }
                        }
                    }
                }
                s2StimulusType = (s2StimulusType != null) ? s2StimulusType : "";

                result = collator.compare(s1StimulusType, s2StimulusType);
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case SOURCE:
                /* source */
                result = collator.compare(s1.getParts()[1].getText(), s2.getParts()[1].getText());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case ARTIFACT:
                /* artifact */
                result = collator.compare(s1.getParts()[2].getText(), s2.getParts()[2].getText());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case ENVIRONMENT:
                /* environment */
                result = collator.compare(s1.getParts()[3].getText(), s2.getParts()[3].getText());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case RESPONSE:
                /* response */
                result = collator.compare(s1.getParts()[4].getText(), s2.getParts()[4].getText());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case MEASURE:
                /* measure */
                result = collator.compare(s1.getParts()[5].getText(), s2.getParts()[5].getText());
//                result = compareFloatValue(s1.getParts()[5].getValue(), s2.getParts()[5].getValue());
                if (result == 0) {
                    return compareColumnValue(s1, s2, depth + 1);
                }
                return result * direction;
            case VALUE:
                /* value */
            	try {
	            	double d1 = Double.parseDouble(s1.getParts()[5].getValue());
	            	double d2 = Double.parseDouble(s2.getParts()[5].getValue());
	                result = Double.compare(d1,d2);
	                if (result == 0) {
	                    return compareColumnValue(s1, s2, depth + 1);
	                }
	                return result * direction;            	
            	} catch (NumberFormatException e){
                    return compareColumnValue(s1, s2, depth + 1);
            	}            	
//                result = collator.compare(s1.getParts()[5].getValue(), s2.getParts()[5].getValue());
//                if (result == 0) {
//                    return compareColumnValue(s1, s2, depth + 1);
//                }
//                return result * direction;
            default:
                return result * direction;
            }
        }

        /**
         * Compares the Integer Datatype of parameters of two scenario response measure.
         */
        private int compareFloatValue(String m1, String m2) {
            float result = 0;
            result = new Float(m1 == null ? "0" : m1).floatValue()
                    - new Float(m2 == null ? "0" : m2).floatValue();
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            }
            return 0;
        }

        /**
         * Save the state of the view.
         * 
         * @param settings
         */
        public void saveState(IDialogSettings settings) {
            if (settings == null) {
                return;
            }
            for (int i = 0; i < directions.length; i++) {
                settings.put("direction" + i, directions[i]);
                settings.put("priority" + i, priorities[i]);
            }
        }

        /**
         * Restore the state of the view.
         * 
         * @param settings
         */
        public void restoreState(IDialogSettings settings) {
            if (settings == null) {
                return;
            }
            try {
                for (int i = 0; i < priorities.length; i++) {
                    directions[i] = settings.getInt("direction" + i);
                    priorities[i] = settings.getInt("priority" + i);
                }
            } catch (NumberFormatException e) {
                resetState();
            }
        }
    }

    /**
     * This is the filter of the Scenarios View.
     */
    public class ScenarioFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instance of ScenarioVO then return
            if (!(element instanceof ScenarioVO)) {
                return true;
            }

            String searchString = searchText.getText().trim().toLowerCase();

            if (searchString.equals("")) {
                return true;
            } else {
                String desc = ((ScenarioVO) element).getDescription().toLowerCase();
                return desc.indexOf(searchString) == -1 ? false : true;
            }
        }
    }

    /**
     * Content provider for the scenarios table view.
     * 
     * @author Neel Mullick
     */
    class ScenariosContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        public ScenariosContentProvider() {
            //System.out.println("[ScenarioContextProvider] constructor");
        }

        /**
         * Returns the elements to display in the viewer When the input of the viewer is set to
         * <code>inputElement</code>.
         * 
         * @return array of objects to be displayed (each element corresponds to a row)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((Set) inputElement).toArray();
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * Called to notify this content provider that the viewer's input has been switched to a
         * different element. The input may be switched if the user opens another project.
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            //System.out.println("[ScenarioContextProvider] inputChanged");
            this.viewer = (TableViewer) viewer;
        }
    }

    /**
     * Create Tool tip listens for the LOGO column, response to doubleclick event to switch to
     * QuestionsView.
     */
    private void createTableToolTips() {
        final Display display = this.parent.getDisplay();
        final Shell shell = this.parent.getShell();
        // Disable native tooltip
        table.setToolTipText("");
        // Implement a tooltip
        final Listener labelListener = new Listener() {

            public void handleEvent(Event event) {
                Label label = (Label) event.widget;
                Shell shell = label.getShell();
                switch (event.type) {
                case SWT.MouseDown:
                    Event e = new Event();
                    e.item = (TableItem) label.getData("_TABLEITEM");
                    // Assuming table is single select, set the selection as
                    // if
                    // the mouse down event went through to the table
                    table.setSelection(new TableItem[] { (TableItem) e.item});
                    table.notifyListeners(SWT.Selection, e);
                // fall through
                case SWT.MouseExit:
                    shell.dispose();
                    break;
                }
            }
        };

        Listener tableListener = new Listener() {

            Shell tip   = null;

            Label label = null;

            public void handleEvent(Event event) {

                switch (event.type) {
                case SWT.Dispose:
                case SWT.KeyDown:
                case SWT.MouseMove:

                    if (tip == null) {
                        break;
                    }
                    tip.dispose();
                    tip = null;
                    label = null;
                    break;

                case SWT.MouseDoubleClick:

                    Point pt = new Point(event.x, event.y);
                    TableItem item = table.getItem(pt);
                    // Ensure the item is not null
                    if (item == null) {
                        break;
                    }

                    Rectangle rect = item.getBounds(0);
                    Rectangle rectAnalysis = item.getBounds(1);
                    if (!(rect.contains(pt) || rectAnalysis.contains(pt))) {
                        // open edit dialogbox when click neither in the first column nor in the
                        // second column
                        editScenarioAction.run();
                        break;
                    }
                    // Only when mouse is over the logo column and has a question

                    if (item.getData("question") != null && rect.contains(pt)) {

                        IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                                .getActiveWorkbenchWindow();
                        try {
                            IWorkbenchPage page = window.getActivePage();
                            QuestionsView view = (QuestionsView) page
                                    .showView("SEI.ArchE.UI.QuestionsView");
                            // Search relationships which match the responsibility name
                            QuestionToUserVO question = (QuestionToUserVO) item.getData("question");
                            String tipText = "";
                            tipText = questionParser.getQuestionText(question);
                            TableViewer questionViewer = view.getTableViewer();
                            int totalQuestions = questionViewer.getTable().getItemCount();

                            for (int i = 0; i < totalQuestions; i++) {
                                int itemFactId = ((QuestionToUserVO) questionViewer.getElementAt(i))
                                        .getFactId();
                                if (question.getFactId() == itemFactId) {
                                    questionViewer.getTable().select(i);
                                }
                            }
                        } catch (PartInitException e) {
                            throw new RuntimeException("QuestionsView could not be opened.", e);
                        }
                    }
                    break;

                case SWT.MouseHover:

                    pt = new Point(event.x, event.y);
                    item = table.getItem(pt);
                    // Ensure the item is not null
                    if (item == null) {
                        break;
                    }

                    // Only when mouse is over the logo column and has a question
                    Object itemObject = item.getData("question");
                    rect = item.getBounds(0);

                    if (itemObject != null && rect.contains(pt)) {
                        if (tip != null && !tip.isDisposed()) {
                            tip.dispose();
                        }
                        tip = new Shell(shell, SWT.ON_TOP);
                        tip.setLayout(new FillLayout());
                        label = new Label(tip, SWT.WRAP);
                        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        label.setData("_TABLEITEM", item);

                        QuestionToUserVO question = (QuestionToUserVO) itemObject;
                        String tipText = questionParser.getQuestionText(question);
                        label.setText(" " + tipText);
                        label.setSize(200, 80);

                        label.addListener(SWT.MouseExit, labelListener);
                        label.addListener(SWT.MouseDown, labelListener);
                        Point size = tip.computeSize(300, 70);

                        pt = table.toDisplay(pt.x + 15, pt.y + 10);
                        tip.setBounds(pt.x, pt.y, size.x, size.y);
                        tip.setVisible(true);
                    }

                    // Only when mouse is over the scenario status column
                    Object itemObjectAnalysis = item.getData("status");
                    rectAnalysis = item.getBounds(1);

                    if (itemObjectAnalysis != null && rectAnalysis.contains(pt)) {
                        if (tip != null && !tip.isDisposed()) {
                            tip.dispose();
                        }
                        tip = new Shell(shell, SWT.ON_TOP);
                        tip.setLayout(new FillLayout());
                        label = new Label(tip, SWT.WRAP);
                        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        label.setData("_TABLEITEM", item);

                        int status = ((Integer) itemObjectAnalysis).intValue();
                        String tipTextAnalysis = "";
                        switch (status) {
                        case ScenarioVO.NOT_ANALYZED:
                            tipTextAnalysis = TT_NOT_ANALYZED;
                            break;
                        case ScenarioVO.FULLY_SATISFIED:
                            Object itemObjectValuesFS = item.getData("values");
                            if (itemObjectValuesFS != null) {
                                tipTextAnalysis = TT_SATISFIED
                                        + ((String) itemObjectValuesFS).toString();
                            } else {
                                tipTextAnalysis = TT_SATISFIED;
                            }
                            break;
                        case ScenarioVO.NOT_SATISFIED:
                            Object itemObjectValuesNS = item.getData("values");
                            if (itemObjectValuesNS != null) {
                                tipTextAnalysis = TT_NOT_SATISFIED
                                        + ((String) itemObjectValuesNS).toString();
                            } else {
                                tipTextAnalysis = TT_NOT_SATISFIED;
                            }
                            break;
                        }

                        label.setText(" " + tipTextAnalysis);
                        label.setSize(200, 80);

                        label.addListener(SWT.MouseExit, labelListener);
                        label.addListener(SWT.MouseDown, labelListener);
                        Point size = tip.computeSize(300, 70);

                        pt = table.toDisplay(pt.x + 15, pt.y + 10);
                        tip.setBounds(pt.x, pt.y, size.x, size.y);
                        tip.setVisible(true);
                    }
                    
                    // Only when mouse is over the scenario status column
                    Object itemObjectAnalysis1 = item.getData("traffic_light");
                    rectAnalysis = item.getBounds(2);

                    if (itemObjectAnalysis1 != null && rectAnalysis.contains(pt)) {
                        if (tip != null && !tip.isDisposed()) {
                            tip.dispose();
                        }
                        tip = new Shell(shell, SWT.ON_TOP);
                        tip.setLayout(new FillLayout());
                        label = new Label(tip, SWT.WRAP);
                        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        label.setData("_TABLEITEM", item);

                        int traffic_light = ((Integer) itemObjectAnalysis1).intValue();
                        String tipTextAnalysis = "";
                        switch (traffic_light) {
                        case 0:
                            Object itemObjectValuesNC = item.getData("values1");
                            if (itemObjectValuesNC != null) {
                                tipTextAnalysis =  NO_CHANGE
                                        + ((String) itemObjectValuesNC).toString();
                            } else {
                                tipTextAnalysis = NO_CHANGE;
                            }
                            break;
                        case 1:
                            Object itemObjectValuesFS = item.getData("values1");
                            if (itemObjectValuesFS != null) {
                                tipTextAnalysis =  NEGATIVE_IMPACT
                                        + ((String) itemObjectValuesFS).toString();
                            } else {
                                tipTextAnalysis = NEGATIVE_IMPACT;
                            }
                            break;
                        case 2:
                            Object itemObjectValuesNS = item.getData("values1");
                            if (itemObjectValuesNS != null) {
                                tipTextAnalysis =  POSITIVE_IMPACT
                                        + ((String) itemObjectValuesNS).toString();
                            } else {
                                tipTextAnalysis =  POSITIVE_IMPACT;
                            }
                            break;
                        case 3:
                            tipTextAnalysis =  TT_NOT_ANALYZED;
                            break;
                        }

                        label.setText(" " + tipTextAnalysis);
                        label.setSize(200, 80);

                        label.addListener(SWT.MouseExit, labelListener);
                        label.addListener(SWT.MouseDown, labelListener);
                        Point size = tip.computeSize(300, 70);

                        pt = table.toDisplay(pt.x + 15, pt.y + 10);
                        tip.setBounds(pt.x, pt.y, size.x, size.y);
                        tip.setVisible(true);
                    } 
                    
                    
                    
                    
                }
            }

        };
        table.addListener(SWT.Dispose, tableListener);
        table.addListener(SWT.KeyDown, tableListener);
        table.addListener(SWT.MouseMove, tableListener);
        table.addListener(SWT.MouseHover, tableListener);
        table.addListener(SWT.MouseDoubleClick, tableListener);

    }

}