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
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

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
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
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
import edu.cmu.sei.arche.ui.dialog.ResponsibilityDialog;
import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;

/**
 * Table viewer for Responsibilities.
 * 
 * @author Henry Chen
 */
public class ResponsibilitiesView extends ViewPart implements IPropertyChangeListener {

    private Action                 newResponsiblityAction;

    private Action                 editResponsiblityAction;

    private Action                 delResponsiblityAction;

    private Action                 mappedScenariosAction;

    private Action                 mappedFunctionsAction;

    private Action                 relationshipsAction;

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The text of the search */
    private Text                   searchText           = null;

    /** The table of responsibilities */
    private Table                  table                = null;

    /** Set the table column property names */
    private final String           LOGO_COLUMN          = "Logo";

    private final String           NAME_COLUMN   = "Name";

    /** Set column names */
    private String[]               columnNames;

    /** Column name datatype from ParameterTypeVO */
    //private int[] columnDataType;
    /** The table of responsibilities * */
    private ResponsibilitiesSorter sorter;

    /** The parent containing this view */
    private Composite              parent;

    private ProjectVO              projectVO;

    /** Parameter types */
    private ArrayList              paramTypes;

    /**
     * Question parser to retrieve question text (replacing tags with dynamic data)
     */
    private QuestionParser         questionParser;

    /** Define the number of columns which are not changed during runtime. */
    private final static int       STATIC_COLUMN_NUMBER = 2;

    private final static String[]  BOOLEAN_OPTIONS      = { "TRUE", "FALSE"};

    /**
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        this.parent = parent;
        initPage();
        // Initialize the question Parser.
        questionParser = ArchEUIPlugin.getDefault().getQuestionParser();

        Label searchLabel = new Label(parent, SWT.RIGHT);
        searchLabel.setText("Name contains:");
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

        // Create the table
        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        // Create table columns
        creatTableColumns();
        //create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new ResponsibilitiesContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new ResponsibilityFilter());

        // Create layout
        GridLayout layout = new GridLayout(3, false);
        layout.verticalSpacing = 5;
        parent.setLayout(layout);
        // Dynamic search label
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 200;
        searchLabel.setLayoutData(gdSearchLabel);
        // Dynamic search textbox
        GridData gdSearchText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gdSearchText.widthHint = 200;
        searchText.setLayoutData(gdSearchText);

        // Table
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);
        createContextMenu();
        newResponsiblityAction = new NewResponsibilityAction();
        editResponsiblityAction = new EditResponsibilityAction();

        delResponsiblityAction = new DeleteResponsibilityAction();
        mappedScenariosAction = new MappedScenariosAction();
        mappedFunctionsAction = new MappedFunctionsAction();
        relationshipsAction = new RelationshipsAction();
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

    private void fillContextMenu(IMenuManager menu) {
        menu.add(newResponsiblityAction);
        menu.add(editResponsiblityAction);
        menu.add(delResponsiblityAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(mappedScenariosAction);
        menu.add(mappedFunctionsAction);
        menu.add(relationshipsAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$
        // if projectVO is null, then dissable menu items.
        boolean isEnabled = false;
        isEnabled = (projectVO == null ? false : true);
        newResponsiblityAction.setEnabled(isEnabled);
        // if no item is selected, gray out 'delete' option
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        editResponsiblityAction.setEnabled(isEnabled);
        delResponsiblityAction.setEnabled(isEnabled);
        mappedScenariosAction.setEnabled(isEnabled);
        mappedFunctionsAction.setEnabled(isEnabled);
        relationshipsAction.setEnabled(isEnabled);
    }

    /**
     * @return hashtable of responsibilities or null if no project is open.
     */
    private Hashtable getResponsibilities() {
        Hashtable resps = null;
        if (projectVO != null) {
            resps = projectVO.getResponsibilities();
        }
        if (resps == null) {
            resps = new Hashtable();
            resps.put(new Integer(-999), "Please open or create a project.");
        }
        return resps;
    }

    private void initProjectVO() {
        projectVO = ArchEUIPlugin.getDefault().getProjectVo();
    }

    /**
     * Populate ArrayList of parameter types or null if no project is open.
     */
    private void initParamTypes() {
        ArrayList result = new ArrayList();
        // Check if projectVO is null, then do nothing and return null.
        if (projectVO != null) {
            for (Enumeration e = projectVO.getActiveRFs().keys(); e.hasMoreElements();) {
                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
                // If the reasoning framwork is disabled, continue next loop.
                if (!((Boolean) projectVO.getActiveRFs().get(rf)).booleanValue()) {
                    continue;
                }
                result.addAll(rf.getRespParamTypes());
            }
        }
        // Sort the parameter types
        Collections.sort(result, new Comparator() {

            public int compare(Object arg0, Object arg1) {
                return ((ParameterTypeVO) arg0).getName().compareTo(
                                                                    ((ParameterTypeVO) arg1)
                                                                            .getName());
            }
        });
        paramTypes = result;
    }

    /**
     * Get Parameter typeid (which is recognized by the core) given the parameter name.
     * 
     * @return Returns parameter typeid
     */
    private String getParamID(String paramName) {
        if (projectVO == null) {
            return null;
        }
        ArrayList result = new ArrayList();
        for (Enumeration e = projectVO.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            // If the reasoning framwork is disabled, continue next loop.
            if (!((Boolean) projectVO.getActiveRFs().get(rf)).booleanValue()) {
                continue;
            }
            Iterator rpit = rf.getRespParamTypes().iterator();
            ParameterTypeVO paramType;
            while (rpit.hasNext()) {
                paramType = (ParameterTypeVO) rpit.next();
                if (paramType.getName().equals(paramName)) {
                    return paramType.getId();
                }
            }
        }
        return null;
    }

    /**
     * Populate column names from ParametreTypeVO to Array columnNames.
     */
    private void populateColumnNames() {
        int size = STATIC_COLUMN_NUMBER + (paramTypes != null ? paramTypes.size() : 0);
        columnNames = new String[size];
        int index = 0;
        columnNames[index++] = LOGO_COLUMN;
        columnNames[index++] = NAME_COLUMN;
        ParameterTypeVO pt;
        if (paramTypes != null) {
            Iterator it = paramTypes.iterator();
            while (it.hasNext()) {
                pt = (ParameterTypeVO) it.next();
                columnNames[index] = pt.getName();
                index++;
            }
        }
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
            ResponsibilitiesLabelProvider labelProvider = (ResponsibilitiesLabelProvider) tableViewer
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
     * NewResponsibilityAction INNER CLASS
     */
    private class NewResponsibilityAction extends Action {

        private NewResponsibilityAction() {
            setText("New responsibility");
        }

        public void run() {
            ResponsibilityDialog dialog = new ResponsibilityDialog(parent.getShell(), null);
            dialog.open();
        }

    }

    /***********************************************************************************************
     * EditResponsibilityAction INNER CLASS
     */
    private class EditResponsibilityAction extends Action {

        private EditResponsibilityAction() {
            setText("Edit");
        }

        public void run() {
            ResponsibilityVO vo = (ResponsibilityVO) (tableViewer.getTable().getSelection()[0]
                    .getData());
            ResponsibilityDialog dialog = new ResponsibilityDialog(parent.getShell(), vo);
            dialog.open();
        }
    }

    /***********************************************************************************************
     * DeleteResponsibilityAction INNER CLASS
     **********************************************************************************************/
    private class DeleteResponsibilityAction extends Action {

        private DeleteResponsibilityAction() {
            setText("Delete");
        }

        public void run() {
            //table
            delResp();
        }

        /**
         * Delete a Responsibility
         */
        private void delResp() {
            MessageDialog closeMsgDialog = new MessageDialog(parent.getShell(), "Delete", null,
                    "Are you sure you want to delete the selected responsiblity?",
                    MessageDialog.QUESTION, new String[] { "Yes", "No"}, 1);
            closeMsgDialog.open();
            if (closeMsgDialog.getReturnCode() == 0) {
                Item[] sel = table.getSelection();
                int factId;
                //Delete all selected Responsibilities
                for (int i = 0; i < sel.length; i++) {
                    factId = ((ResponsibilityVO) sel[i].getData()).getFactId();
                    getArchEFacade().deleteResponsibility(factId);
                }
            }
        }
    }

    /***********************************************************************************************
     * MappedScenariosAction INNER CLASS
     **********************************************************************************************/
    private class MappedScenariosAction extends Action {

        private MappedScenariosAction() {
            setText("Mapped scenarios");
        }

        public void run() {
            mapScenarios();
        }

        /**
         * Map Scenarios
         */
        private void mapScenarios() {
            Item[] sel = table.getSelection();
            String respoStr = ((ResponsibilityVO) sel[0].getData()).getName();
            IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                    .getActiveWorkbenchWindow();
            try {
                IWorkbenchPage page = window.getActivePage();
                ScenarioRespMappingView view = (ScenarioRespMappingView) page
                        .showView("SEI.ArchE.UI.ScenarioRespMappingView");
                // Search relationships which match the responsibility name
                view.searchText.setText(respoStr);
            } catch (Exception e) {
            }
        }
    }

    /***********************************************************************************************
     * MappedFunctionAction INNER CLASS
     **********************************************************************************************/
    private class MappedFunctionsAction extends Action {

        private MappedFunctionsAction() {
            setText("Mapped functions");
        }

        public void run() {
            mapFunctions();
        }

        /**
         * Add a new Responsibility
         */
        private void mapFunctions() {
            Item[] sel = table.getSelection();
            String respoStr = ((ResponsibilityVO) sel[0].getData()).getName();
            IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                    .getActiveWorkbenchWindow();
            try {
                IWorkbenchPage page = window.getActivePage();
                FunctionRespMappingView view = (FunctionRespMappingView) page
                        .showView("SEI.ArchE.UI.FunctionRespMappingView");
                // Search relationships which match the responsibility name
                view.searchText.setText(respoStr);
            } catch (Exception e) {
            }
        }
    }

    /***********************************************************************************************
     * RelationshipsAction INNER CLASS
     **********************************************************************************************/
    private class RelationshipsAction extends Action {

        private RelationshipsAction() {
            setText("Relationships");
        }

        public void run() {
            mapRelationships();
        }

        /**
         * mapRelationships
         */
        private void mapRelationships() {
            Item[] sel = table.getSelection();
            String respoStr = ((ResponsibilityVO) sel[0].getData()).getName();
            IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                    .getActiveWorkbenchWindow();
            try {
                IWorkbenchPage page = window.getActivePage();
                RelationshipsView view = (RelationshipsView) page
                        .showView("SEI.ArchE.UI.RelationshipsView");
                // Search relationships which match the responsibility name
                view.searchText.setText(respoStr);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Clear and create table columns This is necessary when close/open a new project
     */
    private void creatTableColumns() {
        // This class handles selections of the column headers. Selection of the column header will
        // cause resorting of the shown tasks using that column's sorter. Repeated selection of the
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
        // 1st column with logo
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);

        // 2nd column with description
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setText(columnNames[1]);

        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);

        // Create columns for parameters
        for (int i = STATIC_COLUMN_NUMBER; i < columnNames.length; i++) {
            column = new TableColumn(table, SWT.NONE, columnIndex++);
            column.setText(columnNames[i]);

            column.setWidth(140);
//            column.setWidth(columnNames[i].length() * MarkerUtil.PIXELS_OF_CHAR);
            column.setAlignment(SWT.LEFT);
            column.addSelectionListener(headerListener);
        }

    }

    /**
     * Method refreshTableViewer.
     */
    private void refreshTableViewer() {
        tableViewer.setInput(getResponsibilities());

        if (!(tableViewer.getLabelProvider() instanceof ResponsibilitiesLabelProvider)) {
            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new ResponsibilitiesLabelProvider()); // calls refresh
        }

        tableViewer.setColumnProperties(columnNames);

        // Create the cell editors
        CellEditor[] editors = new CellEditor[columnNames.length];
        TextCellEditor textEditor;
        // Only initilize combobox when projectVO is not null.
        if (projectVO != null) {
            // Column1 : Do nothing
            // Column2 : Description
            // Assign editors to parameter columns based on the data type.
            for (int i = STATIC_COLUMN_NUMBER; i < columnNames.length; i++) {
                int dt = ((ParameterTypeVO) paramTypes.get(i - STATIC_COLUMN_NUMBER)).getDataType();
                switch (dt) {
                // For Boolean, we assign ComboBoxCellEditors.
                case ParameterTypeVO.BOOLEAN:

                    editors[i] = new ComboBoxCellEditor(table, BOOLEAN_OPTIONS, SWT.CENTER
                            | SWT.READ_ONLY);
                    break;
                // For Double and String, we assign TextCellEditors.
                case ParameterTypeVO.DOUBLE:
                case ParameterTypeVO.INTEGER:
                case ParameterTypeVO.STRING:
                    editors[i] = new TextCellEditor(table);
                    break;
                }
            }
        }
        // Assign the cell editors to the viewer
        tableViewer.setCellEditors(editors);
        // Set the cell modifier for the viewer
        tableViewer.setCellModifier(new ResponsiblitiesCellModifier(this));
        sorter = new ResponsibilitiesSorter();
        tableViewer.setSorter(sorter);

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
     * Called when this view now points to a different project. It changes the viewer input based on
     * the currently open project and calls refresh on the viewer.
     */
    public void setInputAndRefresh() {
        initPage();
        //Re-creat table columns
        creatTableColumns();

        refreshTableViewer();
        table.redraw();
    }

    /***********************************************************************************************
     * Label provider for the Responsibilities table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     */
    private class ResponsibilitiesLabelProvider extends LabelProvider implements
            ITableLabelProvider, ICellColorProvider {

        /** Color to paint cells with data created by ArchE. */
        private Color srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /**
         * Auto-generated by the wizard--not sure if it'll have any purpose in ArchE views.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
            // nothing to do
        }

        /**
         * Auto-generated by the wizard--not sure if it'll have any purpose in ArchE views.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
            //        nothing to do
        }

        /**
         * Auto-generated by the wizard--not sure if it'll have any purpose in ArchE views.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * Auto-generated by the wizard--not sure if it'll have any purpose in ArchE views.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
            //        nothing to do
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
            // If project is not open
            if (!(element instanceof ResponsibilityVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            ResponsibilityVO resp = (ResponsibilityVO) element;
            // If is the first column -logo
            if (columnIndex == 0) {
                ArrayList questions = (ArrayList) projectVO.getQuestions();
                QuestionToUserVO question = null;
                int respoFactId = resp.getFactId();
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
                        if (temp[i] == respoFactId) {
                            hasQuestion = true;

                            // find the table item widget matched with this data
                            // element
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

            return null;
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
            // if the relationshipVO list is null then display the warning.
            if (!(element instanceof ResponsibilityVO)) {
                return columnIndex == 1 ? element.toString() : "";
            }
            ResponsibilityVO resp = (ResponsibilityVO) element;
            String columnHead = null;
            String paramValue = "";
            switch (columnIndex) {
            case 0:
                // logo
                return "";
            case 1:
                // name
                return resp.getName();
            default:
                // parameters
                columnHead = table.getColumn(columnIndex).getText();
                paramValue = resp.getParameterValueByName(columnHead);
                int dt = ((ParameterTypeVO) paramTypes.get(columnIndex - STATIC_COLUMN_NUMBER))
                        .getDataType();
                switch (dt) {
                case ParameterTypeVO.BOOLEAN:
                case ParameterTypeVO.DOUBLE:
                case ParameterTypeVO.INTEGER:
                case ParameterTypeVO.STRING:
                    return paramValue == null ? "" : paramValue;
                }
            }
            return null;
        }

        /**
         * Returns the foreground color for a given ResponsibilityVO object and the specified
         * column. If ArchE created the data, the color for the corresponding cell is ??? Otherwise,
         * the color is the default (black).
         * 
         * @param element ResponsibilityVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for that ResponsibilityVO in that given column or null if the
         *         default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            if (!(element instanceof ResponsibilityVO)) {
                return null;
            }
            ResponsibilityVO respo = (ResponsibilityVO) element;
            switch (columnIndex) {
            case 0:
                break;
            case 1:
                // name
                if (respo.getSource() == CoreFact.ARCHE_CORE) {
                    return srcIsArchEColor;
                }
                break;
            default:
                // parameters
                String columnHead = table.getColumn(columnIndex).getText();
                ParameterVO param = respo.getParameterByName(columnHead);
                if (param == null) {
                    break;
                }
                int source = param.getSource();
                if (source == CoreFact.ARCHE_CORE) {
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
         * @param element ResponsibilityVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for the backgoround of that ResponsibilityVO in that given column or
         *         null if the default color is to be used.
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
     * cell modifier for this view
     */
    private class ResponsiblitiesCellModifier implements ICellModifier {

        private ResponsibilitiesView view;

        private String[]             columnNames;

        /** Save the cell value before modification * */
        private Object               valueBeforeModify;

        /** String list in comboBox for Boolean value */

        /**
         * Constructor
         * 
         * @param ResponsibilitiesView an instance of a ResponsibilitiesView
         */
        public ResponsiblitiesCellModifier(ResponsibilitiesView view) {
            super();
            this.view = view;
        }

        /**
         * @param element
         * @param property
         * @return @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
         *         java.lang.String)
         */
        public boolean canModify(Object element, String property) {
            return true;
        }

        /**
         * @param element
         * @param property
         * @return @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
         *         java.lang.String)
         */
        public Object getValue(Object element, String property) {
            // Find the index of the column
            int columnIndex = view.getColumnNames().indexOf(property);
            Object result = "";
            ResponsibilityVO respo = (ResponsibilityVO) element;
            String columnHead = null;

            switch (columnIndex) {
            case 0:
                // LOGO_COLUMN
                result = "";
                break;
            case 1:
                // NAME_COLUMN
                result = respo.getName();
                break;
            default:
                // Parameters

                columnHead = table.getColumn(columnIndex).getText();
                result = respo.getParameterValueByName(columnHead);
                result = result == null ? "" : result;

                int dt = ((ParameterTypeVO) paramTypes.get(columnIndex - STATIC_COLUMN_NUMBER))
                        .getDataType();
                switch (dt) {
                case ParameterTypeVO.BOOLEAN:
                    // Boolean: ComboBox
                    // If the value is not set, return unselected.
                    if (((String) result).equals("")) {
                        result = new Integer(-1);
                        break;
                    }
                    int i = BOOLEAN_OPTIONS.length - 1;
                    while (!((String) result).equalsIgnoreCase(BOOLEAN_OPTIONS[i]) && i > 0) {
                        --i;
                    }
                    result = new Integer(i);
                    break;

                case ParameterTypeVO.DOUBLE:
                case ParameterTypeVO.INTEGER:
                case ParameterTypeVO.STRING:
                }

                // If result is null, the cell won't be editable, so force
                // it to be an empty string.

                break;
            }
            valueBeforeModify = result;
            return result;
        }

        /**
         * @param element
         * @param property
         * @param value
         * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
         *      java.lang.Object)
         */
        public void modify(Object element, String property, Object value) {
            // If the value does not change, then quit.
            if (valueBeforeModify.equals(value)) {
                return;
            }
            // Find the index of the column
            int columnIndex = view.getColumnNames().indexOf(property);
            if (element instanceof Item) {
                element = ((Item) element).getData();
            }
            ResponsibilityVO respo = (ResponsibilityVO) element;

            switch (columnIndex) {
            case 1:
                // NAME_COLUMN
                getArchEFacade().editResponsibility(respo, value.toString(), null);
                break;
            default:
                String newValue = value.toString();

                // Modify Parameters
                // Datatype Check
                // Check if input value is a number when datatype is DOUBLE
                // Parepare the new value
                ParameterTypeVO pt = ((ParameterTypeVO) paramTypes.get(columnIndex
                        - STATIC_COLUMN_NUMBER));

                switch (pt.getDataType()) {
                case ParameterTypeVO.BOOLEAN:
                    int selIndex = ((Integer) value).intValue();
                    newValue = BOOLEAN_OPTIONS[selIndex];
                    break;
                case ParameterTypeVO.INTEGER:
                    // datatype check only for DOUBLE
                    try {
                        Integer.parseInt(value.toString());
                    } catch (NumberFormatException e) {
                        //System.out.println("not a number");
                        MessageDialog.openWarning(parent.getShell(), "Error",
                                                  "\n\tPlease input a INTEGER value.");
                        return;
                    }
                    break;
                case ParameterTypeVO.DOUBLE:
                    // datatype check only for DOUBLE
                    try {
                        Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        //System.out.println("not a number");
                        MessageDialog.openWarning(parent.getShell(), "Error",
                                                  "\n\tPlease input a DOUBLE value.");
                        return;
                    }
                    break;
                case ParameterTypeVO.STRING:
                    break;
                }

                //If the value is not set, auto-set it to default value.
                if (value.toString().trim().equals("")) {
                    newValue = pt.getDefaultValue();
                }

                // Get parameter ID (Used by core)
                String paramID = getParamID(property);

                if (paramID != null) {
                    // assert value
                    if (respo.getParameterByName(property) == null) {
                        getArchEFacade().addResponsbilityParameter(paramID, respo.getFactId(),
                                                                   newValue, pt.getDataType());
                    } else {
                        getArchEFacade()
                                .editResponsbilityParameter(respo.getParameterByName(property),
                                                            respo.getFactId(), respo.getFactId(),
                                                            newValue, pt.getDataType());
                    }
                }
                break;
            }
            view.getTableViewer().refresh();
        }
    }

    /***********************************************************************************************
     * This is the responsibility's sorter.
     */
    private class ResponsibilitiesSorter extends ViewerSorter {

        private int[]    priorities;

        private int[]    directions;

        final static int ASCENDING          = 1;

        final static int DEFAULT_DIRECTION  = 0;

        final static int DESCENDING         = -1;

        final static int DESCRIPTION        = 1;

        int[]            DEFAULT_PRIORITIES = new int[columnNames.length];

        int[]            DEFAULT_DIRECTIONS = new int[columnNames.length];

        /**
         * Creates a new task sorter.
         */
        public ResponsibilitiesSorter() {
            DEFAULT_DIRECTIONS[0] = DEFAULT_DIRECTIONS[1] = ASCENDING;
            for (int i = STATIC_COLUMN_NUMBER; i < columnNames.length; i++) {
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
            if (!(e1 instanceof ResponsibilityVO) || !(e1 instanceof ResponsibilityVO)) {
                return 0;
            }
            ResponsibilityVO r1 = (ResponsibilityVO) e1;
            ResponsibilityVO r2 = (ResponsibilityVO) e2;
            return compareColumnValue(r1, r2, 0);
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
            if (direction == DEFAULT_DIRECTION)
                directions[priorities[0]] = DEFAULT_DIRECTIONS[priorities[0]];
            else if (direction == ASCENDING || direction == DESCENDING)
                directions[priorities[0]] = direction;
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
         * Compares two markers, based only on the value of the specified column. Method declared on
         * ViewerSorter.
         */
        private int compareColumnValue(ResponsibilityVO r1, ResponsibilityVO r2, int depth) {
            if (depth >= priorities.length)
                return 0;
            int columnIndex = priorities[depth];
            // Do not sort the logo column
            if (columnIndex == 0)
                return 0;

            int direction = directions[columnIndex];
            int result = 0;
            switch (columnIndex) {
            case DESCRIPTION: {
                /* description */
                result = collator.compare(r1.getName(), r2.getName());
                if (result == 0)
                    return compareColumnValue(r1, r2, depth + 1);
                return result * direction;
            }
            default: {
                if (columnIndex >= table.getColumnCount()) {
                    // This situation can occur when we deactivate an RF and the number of columns
                    // decrease. Then, in refreshTableViewer we setInput on the table viewer before
                    // recreating the sorter, and set input internally calls refresh and hence
                    // sorter, but the old sorter may be pointing to a col that's not ther anymore.
                    return 0;
                }
                // Sort according to the datatype of the parameter
                int dt = ((ParameterTypeVO) paramTypes.get(columnIndex - STATIC_COLUMN_NUMBER))
                        .getDataType();
                switch (dt) {
                case ParameterTypeVO.DOUBLE:
                case ParameterTypeVO.INTEGER:
                    result = compareDoubleValue(r1, r2, columnIndex);
                    if (result == 0) {
                        return compareColumnValue(r1, r2, depth + 1);
                    }
                    break;
                    
                case ParameterTypeVO.STRING:
                    result = collator.compare(r1.getName(), r2.getName());
                    if (result == 0) {
                        return compareColumnValue(r1, r2, depth + 1);
                    }
                    break;

                case ParameterTypeVO.BOOLEAN:
                    result = compareBooleanValue(r1, r2, columnIndex);
                    if (result == 0) {
                        return compareColumnValue(r1, r2, depth + 1);

                    }
                    break;
                }

                return result * direction;
            }
            }
        }

        /**
         * Compares the Double Datatype of parameters of two responsibilities.
         */
        private int compareDoubleValue(ResponsibilityVO r1, ResponsibilityVO r2, int columnIndex) {
            double result = 0.0;
            String r1ParamValue = r1.getParameterValueByName(columnNames[columnIndex]);
            String r2ParamValue = r2.getParameterValueByName(columnNames[columnIndex]);
            result = Double.parseDouble(r1ParamValue == null ? "0" : r1ParamValue)
                    - Double.parseDouble(r2ParamValue == null ? "0" : r2ParamValue);
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            }
            return 0;
        }

        /**
         * Compares the Boolean Datatype of parameters of two responsibilities.
         */
        private int compareBooleanValue(ResponsibilityVO r1, ResponsibilityVO r2, int columnIndex) {

            String r1ParamValue = r1.getParameterValueByName(columnNames[columnIndex]);
            String r2ParamValue = r2.getParameterValueByName(columnNames[columnIndex]);
            r1ParamValue = r1ParamValue == null ? "" : r1ParamValue.trim();
            r2ParamValue = r2ParamValue == null ? "" : r2ParamValue.trim();
            return collator.compare(r1ParamValue, r2ParamValue);
        }

        /**
         * @param settings
         */
        public void saveState(IDialogSettings settings) {
            if (settings == null)
                return;
            for (int i = 0; i < directions.length; i++) {
                settings.put("direction" + i, directions[i]);//$NON-NLS-1$
                settings.put("priority" + i, priorities[i]);//$NON-NLS-1$
            }
        }

        public void restoreState(IDialogSettings settings) {
            if (settings == null)
                return;
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
     * This is the filter of the ReponsibilitiesView.
     */
    private class ResponsibilityFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instant of RelationshipVO then return
            if (!(element instanceof ResponsibilityVO)) {
                return true;
            }
            String searchString = searchText.getText().trim().toLowerCase();
            if (searchString.equals("")) {
                return true;
            } else {
                String desc = ((ResponsibilityVO) element).getName().toLowerCase();
                return desc.indexOf(searchString) == -1 ? false : true;
            }
        }
    }

    /**
     * Get ArchE facade.
     */
    private ArchEFacade getArchEFacade() {
        return ArchEUIPlugin.getDefault().getArchEFacade();
        //		return getProjectVO().getArcheFacade();
    }

    /***********************************************************************************************
     * Content provider for the responsibilities table view.
     */
    private class ResponsibilitiesContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        /**
         * Registers itself as observer of ResponsibilityVO objects and others.
         */
        public ResponsibilitiesContentProvider() {
            //System.out.println("[ResponsibilityContentProvider] constructor");
        }

        /**
         * Returns the elements to display in the viewer When the input of the viewer is set to
         * <code>inputElement</code>.
         * 
         * @return array of objects to be displayed (each element corresponds to a row)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((Hashtable) inputElement).values().toArray();
            //return ((Set) inputElement).toArray();
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
            //System.out.println("[ResponsibilityContentProvider] inputChanged");
            this.viewer = (TableViewer) viewer;
        }
    }

    /**
     * Do initialization for the page
     */
    private void initPage() {

        // If the search Text box is created, then clear the content
        if (searchText != null) {
            searchText.setText("");
        }

        initProjectVO();
        initParamTypes();
        populateColumnNames();

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
                    if (!rect.contains(pt)) {
                        // open edit dialogbox when not click on the first column
                        editResponsiblityAction.run();
                        break;
                    }

                    // Only when mouse is over the logo column and has a
                    // question

                    if (item.getData("question") != null && rect.contains(pt)) {
                        IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                                .getActiveWorkbenchWindow();
                        try {
                            IWorkbenchPage page = window.getActivePage();
                            QuestionsView view = (QuestionsView) page
                                    .showView("SEI.ArchE.UI.QuestionsView");
                            // Search relationships which match the
                            // responsibility name
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
                    // Only when mouse is over the logo column and has a
                    // question

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