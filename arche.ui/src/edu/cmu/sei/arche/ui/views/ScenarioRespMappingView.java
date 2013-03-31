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
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
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
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
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
import edu.cmu.sei.arche.ui.dialog.ScenarioRespMappingDialog;
import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;
import edu.cmu.sei.arche.vo.ScenarioResponsibilityMapVO;
import edu.cmu.sei.arche.vo.ScenarioVO;

/**
 * Table viewer for Scenario Responsibility Mapping.
 * 
 * @author Myung-joo Ko
 */
public class ScenarioRespMappingView extends ViewPart implements IPropertyChangeListener {

    private Action                 newScenarioRespAction;

    private Action                 editScenarioRespAction;

    private Action                 delScenarioRespAction;

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The text of the search */
    public Text                    searchText;

    /** The table of responsibilities */
    private Table                  table;

    /** Set the table column property names */
    private final String           LOGO_COLUMN           = "Logo";

    private final String           SCENARIO_COLUMN       = "Scenario";

    private final String           RESPONSIBILITY_COLUMN = "Responsibility";

    /** Set column names */
    private String[]               columnNames;

    /** The sorter of scenario and responsibility mapping* */
    private ScenarioRespSorter     sorter;

    private Composite              parent;

    /** Set of Scenarios and mapped Responsibilities * */
    private HashSet                scenarioResps;

    /** Question parser to retrieve question text (replacing tags with dynamic data) */
    private QuestionParser         questionParser;

    /** The project VO for this instance of the function responsibility mapping view */
    private ProjectVO              project;

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

        // Initialize relevant member variables of this class
        initPage();

        // Create layout
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 5;
        parent.setLayout(layout);

        // Dynamic search label
        Label searchLabel = new Label(parent, SWT.RIGHT | SWT.WRAP);
        searchLabel.setText("Scenario or responsibility contains:");
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 300;
        searchLabel.setLayoutData(gdSearchLabel);

        // Dynamic search textbox
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
        GridData gdSearchText = new GridData();
        gdSearchText.widthHint = 200;
        searchText.setLayoutData(gdSearchText);

        // Create the table
        table = new Table(parent, SWT.SINGLE | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        // Create the table Columns
        creatTableColumns();

        // create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new ScenarioRespContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new ScenarioRespFilter()); // calls refresh

        createContextMenu(table);
        newScenarioRespAction = new NewScenarioRespAction();
        editScenarioRespAction = new EditScenarioRespAction();
        // Adding an open listener lets the user call edit by double clicking a row.
        tableViewer.addOpenListener(new IOpenListener() {

            public void open(OpenEvent event) {
                editScenarioRespAction.run();
            }
        });
        delScenarioRespAction = new DelScenarioRespAction();
        ArchEUIPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
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
    private void createContextMenu(Table table) {
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
     */
    private void fillContextMenu(IMenuManager menu) {
        menu.add(newScenarioRespAction);
        menu.add(editScenarioRespAction);
        menu.add(delScenarioRespAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end"));

        // if projectVO is null, then disable menu items.
        boolean isEnabled = (project == null ? false : true);
        newScenarioRespAction.setEnabled(isEnabled);
        // if no item is selected, gray out 'delete' and 'edit' options
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        delScenarioRespAction.setEnabled(isEnabled);
        editScenarioRespAction.setEnabled(isEnabled);
    }

    /**
     * Returns a Set with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     * 
     * @return
     */
    private Set getScenarioResps() {
        Set maps = null;
        if (project != null) {
            maps = project.getScenarioResps();
        }
        if (maps == null) {
            maps = new HashSet();
            maps.add("Please open or create a project.");
        }
        return maps;
    }

    /**
     * Populate column names
     */
    private void populateColumnNames() {
        columnNames = new String[3];
        columnNames[0] = LOGO_COLUMN;
        columnNames[1] = SCENARIO_COLUMN;
        columnNames[2] = RESPONSIBILITY_COLUMN;
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
            ScenarioRespLabelProvider labelProvider = (ScenarioRespLabelProvider) tableViewer
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
     * Create table columns
     */
    private void creatTableColumns() {

        // This class handles selections of the column headers. Selection of the column header will
        // cause resorting of the shown items using that column's sorter. Repeated selection of the
        // header will toggle sorting order (ascending versus descending).
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

        // 2st column with Scenarios
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);

        // 3rd column with Responsibilities
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
    }

    /**
     * Method createTableViewer.
     */
    private void refreshTableViewer() {
        tableViewer.setInput(getScenarioResps());
        if (!(tableViewer.getLabelProvider() instanceof ScenarioRespLabelProvider)) {
            // The view is being created

            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new ScenarioRespLabelProvider()); // calls refresh

            // THE setColumnProperties STATEMENT IS INSIDE THIS IF BECAUSE THE COLUMNS DON'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, setColumnProperties IS CALLED EVERYTIME.
            tableViewer.setColumnProperties(columnNames);

            // THE CREATION OF THE SORTER IS INSIDE THIS IF BECAUSE THE SORTER DOESN'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, SORTER HAS TO BE RECREATED EVERYTIME.
            sorter = new ScenarioRespSorter(); // instantiate and reset sorter
            tableViewer.setSorter(sorter); // calls refresh because sorter changed
        } else {
            // The view is being refreshed by calling setInputAndRefresh
            ((ScenarioRespSorter) tableViewer.getSorter()).resetState();
            tableViewer.refresh();
        }
    }

    /**
     * Return the column names in a collection
     * 
     * @return List containing column names
     */
    public java.util.List getColumnNames() {
        return Arrays.asList(columnNames);
    }

    /**
     * @return Returns the tableViewer.
     */
    public ColoredCellTableViewer getTableViewer() {
        return tableViewer;
    }

    /**
     * Initialize member variables that will be used by the view
     */
    public void initPage() {
        // If the search Text box is created, then clear the content
        if (searchText != null) {
            searchText.setText("");
        }

        project = ArchEUIPlugin.getDefault().getProjectVo();
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
                    // Assuming table is single select, set the selection as if
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
                            throw new RuntimeException("The questions view could not be open", e);
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
                    rect = item.getBounds(0);
                    Object itemObject = item.getData("question");

                    //Only when mouse is over the logo column and has a question
                    if (itemObject != null && rect.contains(pt)) {
                        if (tip != null && !tip.isDisposed())
                            tip.dispose();
                        tip = new Shell(shell, SWT.ON_TOP);
                        tip.setLayout(new FillLayout());
                        label = new Label(tip, SWT.WRAP);
                        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        label.setData("_TABLEITEM", item);

                        QuestionToUserVO question = (QuestionToUserVO) itemObject;
                        String tipText = "";
                        tipText = questionParser.getQuestionText(question);
                        label.setText(" " + tipText);
                        //TODO: The size of the tooltip box should be made adaptable to the text
                        // later.
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

    /***********************************************************************************************
     * NewResponsibilityAction INNER CLASS
     */
    private class NewScenarioRespAction extends Action {

        private NewScenarioRespAction() {
            setText("New Mapping");
        }

        public void run() {
            ScenarioRespMappingDialog dialog = new ScenarioRespMappingDialog(parent.getShell(),
                    null);
            dialog.open();
        }

    }

    /***********************************************************************************************
     * EditResponsibilityAction INNER CLASS
     */
    private class EditScenarioRespAction extends Action {

        private EditScenarioRespAction() {
            setText("Edit");
        }

        public void run() {
            ScenarioResponsibilityMapVO vo = (ScenarioResponsibilityMapVO) (tableViewer.getTable()
                    .getSelection()[0].getData());
            ScenarioRespMappingDialog dialog = new ScenarioRespMappingDialog(parent.getShell(), vo);
            dialog.open();
        }
    }

    /***********************************************************************************************
     * DelScenarioRespAction INNER CLASS
     */
    private class DelScenarioRespAction extends Action {

        private DelScenarioRespAction() {
            setText("Delete");
        }

        /**
         * Delete the selected Scenario and Responsibility Mapping upon user confirmation
         */
        public void run() {
            boolean confirmed = MessageDialog
                    .openQuestion(parent.getShell(), "Confirm Delete",
                                  "Are you sure you want to delete the selected scenario-responsibility mapping?");
            if (confirmed) {
                Item[] sel = table.getSelection();
                // Delete all selected scenario and responsibility mapping
                for (int i = 0; i < sel.length; i++) {
                    int factId = ((ScenarioResponsibilityMapVO) sel[i].getData()).getFactId();
                    ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
                    facade.deleteScFnRespoRelation(factId);
                    getTableViewer().refresh();
                }
                tableViewer.refresh();
            }
        }
    }

    /***********************************************************************************************
     * Label provider for the Scenario Responsibility Mapping table view. NB: IColorProvider cannot
     * be used for color-coding the cells because it only allows setting the color of the entire
     * row. Therefore, instead, we pass the Table object to the constructor and set the color cell
     * using TableItems from Table.
     */
    private class ScenarioRespLabelProvider extends LabelProvider implements ITableLabelProvider,
            ICellColorProvider {

        /** Color to paint cells with data created by ArchE. */
        private Color srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /**
         * Auto-generated by the wizard
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
            // nothing to do
        }

        /**
         * Auto-generated by the wizard
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
            // nothing to do
        }

        /**
         * Auto-generated by the wizard
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * Auto-generated by the wizard
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
            // nothing to do
        }

        /**
         * Returns the correct icon for columns that show icons. Doesn't do anything for columns
         * that show text only.
         * 
         * @param element ResponsibilityVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed for that ResponsibilityVO in that given column or
         *         null if the column does not show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            if (columnIndex != 0) {
                return null;
            }
            if (!(element instanceof ScenarioResponsibilityMapVO)) {
                return (Image) MarkerUtil.getImage("warning");
            }
            ScenarioResponsibilityMapVO scenarioRespMap = (ScenarioResponsibilityMapVO) element;
            // If is the first column -logo
            ArrayList questions = (ArrayList) project.getQuestions();
            QuestionToUserVO question = null;
            int scenarioRespMapFactId = scenarioRespMap.getFactId();
            Iterator itq = questions.iterator();
            int[] temp;
            boolean hasQuestion = false;
            boolean hasProblem = false;

            //TODO: add problems checker here (for later when problems view is implemented in
            // subsequent versions).
            if (hasProblem) {
                return (Image) MarkerUtil.getImage("problem");
            }

            // Question

            // Iterate questionVO list to get the affected factids,
            // check if the respoFactId is in them.
            loop1: while (itq.hasNext()) {
                question = (QuestionToUserVO) itq.next();
                temp = question.getAffectedFacts();
                int temp1[] = new int[temp.length + 1];
                int length = temp.length;
                System.arraycopy(temp, 0, temp1, 0, length);
                temp = temp1;
                temp[length] = question.getParentFactId();

                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] == scenarioRespMapFactId) {
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
            } else {
                return null;
            }
        }

        /**
         * Returns the text to be shown for a given ResponsibilityVO object and the specified
         * column. Doesn't do anything for columns that show icons only.
         * 
         * @param element ResponsibilityVO object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that ResponsibilityVO in that given column or null if
         *         the column shows an icon only.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {

            // if the ScenarioResponsibilityMapVO list is null then display the warning.
            if (!(element instanceof ScenarioResponsibilityMapVO)) {
                return (columnIndex == 1 ? element.toString() : "");
            }

            // If the ScenarioResponsibilityMapVO list is not null.
            int scenarioFactId, respoFactId;

            String columnHead = null;
            String paramValue = "";
            ScenarioVO scenario;
            String result = "";

            ScenarioResponsibilityMapVO scenarioRespMap = (ScenarioResponsibilityMapVO) element;
            scenarioFactId = scenarioRespMap.getScenarioFactId();
            respoFactId = scenarioRespMap.getResponsibilityFactId();

            switch (columnIndex) {
            case 1:
                for (Iterator it = project.getScenarios().iterator(); it.hasNext();) {
                    ScenarioVO scenarioId = (ScenarioVO) it.next();
                    if (scenarioId.getFactId() == scenarioFactId) {
                        result = scenarioId.getDescription();
                    }
                }
                break;
            case 2:
                for (Enumeration e = (Enumeration) project.getResponsibilities().elements(); e
                        .hasMoreElements();) {
                    ResponsibilityVO respFactId = (ResponsibilityVO) e.nextElement();
                    if (respFactId.getFactId() == respoFactId) {
                        result = respFactId.getName();
                    }
                }
            }

            return result;
        }

        /**
         * Returns the foreground color for a given ResponsibilityVO object and the specified
         * column. If ArchE created the data, the color for the corresponding cell is ??? Otherwise,
         * the color is the default (black).
         * 
         * @param element ScenarioResponsibilityMapVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for that ScenarioResponsibilityMapVO in that given column or null if
         *         the default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            if (element instanceof ScenarioResponsibilityMapVO) {
                ScenarioResponsibilityMapVO scenarioRespMap = (ScenarioResponsibilityMapVO) element;
                if (scenarioRespMap.getSource() == CoreFact.ARCHE_CORE) {
                    return srcIsArchEColor;
                }
            }
            return null;
        }

        /**
         * Returns the background color for a given ResponsibilityVO object and the specified
         * column. We're not using different background colors in ArchE, so this method returns null
         * so the cell has default background (white)
         * 
         * @param element ScenarioResponsibilityMapVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for the backgoround of that ScenarioResponsibilityMapVO in that
         *         given column or null if the default color is to be used.
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

    /***********************************************************************************************
     * This is the responsibility's sorter.
     */
    private class ScenarioRespSorter extends ViewerSorter {

        private int[]    priorities;

        private int[]    directions;

        final static int ASCENDING          = 1;

        final static int DEFAULT_DIRECTION  = 0;

        final static int DESCENDING         = -1;

        final static int SCENARIO           = 1;

        final static int RESPONSIBILITY     = 2;

        int[]            DEFAULT_PRIORITIES = new int[columnNames.length];

        int[]            DEFAULT_DIRECTIONS = new int[columnNames.length];

        /**
         * Creates a new sorter.
         */
        public ScenarioRespSorter() {
            DEFAULT_DIRECTIONS[0] = DEFAULT_DIRECTIONS[1] = ASCENDING;
            for (int i = 2; i < columnNames.length; i++) {
                DEFAULT_DIRECTIONS[i] = DESCENDING;
            }
            for (int i = 0; i < columnNames.length - 1; i++) {
                DEFAULT_PRIORITIES[i] = i + 1;
            }
            resetState();
        }

        /**
         * Compares two markers, sorting first by the main column of this sorter, then by subsequent
         * columns, depending on the column sort order.
         */
        public int compare(Viewer viewer, Object e1, Object e2) {
            if ((e1 instanceof ScenarioResponsibilityMapVO)
                    && (e1 instanceof ScenarioResponsibilityMapVO)) {
                ScenarioResponsibilityMapVO r1 = (ScenarioResponsibilityMapVO) e1;
                ScenarioResponsibilityMapVO r2 = (ScenarioResponsibilityMapVO) e2;
                return compareColumnValue(r1, r2, 0);
            }
            return 0;
        }

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
            directions[priority] = DEFAULT_DIRECTIONS[priority];
        }

        public int getTopPriority() {
            return priorities[0];
        }

        public int[] getPriorities() {
            return priorities;
        }

        public void setTopPriorityDirection(int direction) {
            if (direction == DEFAULT_DIRECTION) {
                directions[priorities[0]] = DEFAULT_DIRECTIONS[priorities[0]];
            } else if (direction == ASCENDING || direction == DESCENDING) {
                directions[priorities[0]] = direction;
            }
        }

        public int getTopPriorityDirection() {
            return directions[priorities[0]];
        }

        public void reverseTopPriority() {
            directions[priorities[0]] *= -1;
        }

        public void resetState() {
            priorities = new int[DEFAULT_PRIORITIES.length];
            System.arraycopy(DEFAULT_PRIORITIES, 0, priorities, 0, priorities.length);
            directions = new int[DEFAULT_DIRECTIONS.length];
            System.arraycopy(DEFAULT_DIRECTIONS, 0, directions, 0, directions.length);
        }

        /**
         * Compares two elements, based only on the value of the specified column.
         */
        private int compareColumnValue(ScenarioResponsibilityMapVO s1,
                ScenarioResponsibilityMapVO s2, int depth) {
            if (depth >= priorities.length) {
                return 0;
            }
            int columnIndex = priorities[depth];
            int direction = directions[columnIndex];
            int result = 0;
            String parenetScenario = "";
            String childScenario = "";

            if (columnIndex == 0) {
                return result * direction;
            }

            if (columnIndex == 1) { //Scenario Column

                ScenarioVO scenario;
                for (Iterator i = project.getScenarios().iterator(); i.hasNext();) {
                    scenario = (ScenarioVO) i.next();
                    if (scenario.getFactId() == s1.getScenarioFactId()) {
                        parenetScenario = scenario.getDescription();
                    }
                }

                for (Iterator i = project.getScenarios().iterator(); i.hasNext();) {
                    scenario = (ScenarioVO) i.next();
                    if (scenario.getFactId() == s2.getScenarioFactId()) {
                        childScenario = scenario.getDescription();
                    }
                }

                result = collator.compare(parenetScenario, childScenario);

            } else if (columnIndex == 2) { //Responsibility Column

                ResponsibilityVO resp = (ResponsibilityVO) project.getResponsibilities()
                        .get(new Integer(s1.getResponsibilityFactId()));
                String parentRespo = resp.getName();

                resp = (ResponsibilityVO) project.getResponsibilities()
                        .get(new Integer(s2.getResponsibilityFactId()));
                String childRespo = resp.getName();

                result = collator.compare(parentRespo, childRespo);
            }
            if (result == 0) {
                return compareColumnValue(s1, s2, depth + 1);
            }
            return result * direction;
        }

        public void saveState(IDialogSettings settings) {
            if (settings == null) {
                return;
            }
            for (int i = 0; i < directions.length; i++) {
                settings.put("direction" + i, directions[i]);//$NON-NLS-1$
                settings.put("priority" + i, priorities[i]);//$NON-NLS-1$
            }
        }

        public void restoreState(IDialogSettings settings) {
            if (settings == null) {
                return;
            }
            try {
                for (int i = 0; i < priorities.length; i++) {
                    directions[i] = settings.getInt("direction" + i);//$NON-NLS-1$
                    priorities[i] = settings.getInt("priority" + i);//$NON-NLS-1$
                }
            } catch (NumberFormatException e) {
                resetState();
            }
        }
    }

    /***********************************************************************************************
     * This is the filter of the ScenarioRespMapping View.
     */
    private class ScenarioRespFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            String scenarioDesc = "";
            String respDesc = "";

            String searchString = searchText.getText().trim().toLowerCase();
            if (searchString.equals("")) {
                return true;
            } else {
                //retrieve source scenarioVO
                ScenarioResponsibilityMapVO scenarioRespMap = (ScenarioResponsibilityMapVO) element;
                int scenarioFactId = scenarioRespMap.getScenarioFactId();

                //retrieve scenario description using retrieved scenarioVO
                for (Iterator i = project.getScenarios().iterator(); i.hasNext();) {
                    ScenarioVO targetScenario = (ScenarioVO) i.next();
                    if (targetScenario.getFactId() == scenarioFactId) {
                        scenarioDesc = targetScenario.getDescription().toLowerCase();
                    }
                }

                ResponsibilityVO resp = (ResponsibilityVO) project.getResponsibilities()
                        .get(
                             new Integer(((ScenarioResponsibilityMapVO) element)
                                     .getResponsibilityFactId()));
                respDesc = resp.getName().toLowerCase();

                if (scenarioDesc.indexOf(searchString) != -1
                        || respDesc.indexOf(searchString) != -1) {
                    return true;
                }
                return false;
            }
        }
    }

    /***********************************************************************************************
     * Content provider for the scenario responsibilities mapping table view.
     * 
     * @author Myung-joo Ko
     */
    private class ScenarioRespContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        /**
         * Registers itself as observer of ScenarioResponsibilityMapVO objects and others.
         */
        public ScenarioRespContentProvider() {
            //System.out.println("[ScenarioRespMappingContentProvider] constructor");
        }

        /**
         * Returns the elements to display in the viewer When the input of the viewer is set to
         * <code>inputElement</code>.
         * 
         * @return array of objects to be displayed (each element corresponds to a row)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((HashSet) inputElement).toArray();
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * Called to notify this content provider that the viewer's input has been switched to a
         * different element. The input may be switched if the user opens another project (?)
         * Register this content provider as observers of Responsibility VO objects.
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            //System.out.println("[ScenarioRespContentProvider] inputChanged");
            this.viewer = (TableViewer) viewer;
        }
    }

}

