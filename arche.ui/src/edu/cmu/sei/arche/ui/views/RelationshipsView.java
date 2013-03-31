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
import java.util.HashSet;
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
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
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
import edu.cmu.sei.arche.ui.dialog.RelationshipDialog;
import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.OperandsVO;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.RelationshipVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;

/**
 * View part for Relationships.
 * 
 * @author Henry Chen
 */
public class RelationshipsView extends ViewPart implements IPropertyChangeListener {

    private Action                 newRelationshipAction;

    private Action                 editRelationshipAction;

    private Action                 delRelationshipAction;

    /** table viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The text of the search */
    public Text                    searchText;

    /** The table of relationship view */
    private Table                  table;

    /** Set the table column property names */
    private static final String    RESPONSIBILITY_LH_COLUMN = "Parent responsibility";

    private static final String    RESPONSIBILITY_RH_COLUMN = "Child responsibility";

    private static final String    RELATIONSHIP_COLUMN      = "Relationship";

    private static final String    PARAM_NAME_COLUMN        = "Parameter Name";

    private static final String    PARAM_VALUE_COLUMN       = "Parameter Value";

    /** Set column names */
    private String[]               columnNames;

    /** The sorter of relationships * */
    private RelationshipsSorter    sorter;

    /** The parent containing this view */
    private Composite              parent;

    private ProjectVO              projectVO;

    /** Parameter types */
    private ArrayList              paramTypes;

    /** Question parser to retrieve question text (replacing tags with dynamic data) */
    private QuestionParser         questionParser;

    /** Define the number of columns which are not changed during runtime. */
    private final static int       STATIC_COLUMN_NUMBER     = 4;

    private final static String[]  BOOLEAN_OPTIONS          = { "True", "False"};

    /** Store the width of the first 4 columns */
    private final static int[]     columnWidth              = { 20, 160, 80, 160};

    /**
     * This is the first method called when a view is loaded
     * 
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        this.parent = parent;
        this.parent.setLayout(new GridLayout());

        // Initialize the question Parser.
        questionParser = ArchEUIPlugin.getDefault().getQuestionParser();

        //Do initialze
        initPage();
        //        sorter = new RelationshipsSorter();
        Composite topBar = new Composite(parent, SWT.NULL);

        // Create layout for top bar
        GridLayout topBarLayout = new GridLayout(2, false);
        topBarLayout.marginHeight = 0;
        topBarLayout.marginWidth = 0;
        topBarLayout.verticalSpacing = 5;
        topBar.setLayout(topBarLayout);

        // Label of dynamic filter
        Label searchLabel = new Label(topBar, SWT.RIGHT | SWT.WRAP);
        searchLabel.setText("Responsibilities or relationship contains:");
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 300;
        searchLabel.setLayoutData(gdSearchLabel);

        // Text box of dynamic filter
        searchText = new Text(topBar, SWT.SINGLE | SWT.BORDER);
        searchText.addListener(SWT.Modify, new Listener() {

            public void handleEvent(Event e) {
                //System.out.println(e.widget + "Filtering...");
                tableViewer.refresh();
            }
        });

        // When double-click in the dynamic search text box, select all text in the box.
        searchText.addListener(SWT.MouseDoubleClick, new Listener() {

            public void handleEvent(Event event) {
                searchText.selectAll();
            }
        });

        GridData gdSearchText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gdSearchText.widthHint = 200;
        searchText.setLayoutData(gdSearchText);

        // Create the table columns
        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        // Create the table columns
        createTableColumns();
        // create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new RelationshipsContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new RelationshipsFilter()); // calls refresh

        createContextMenu();
        newRelationshipAction = new NewRelationshipAction();
        editRelationshipAction = new EditRelationshipAction();
        delRelationshipAction = new DeleteRelationshipAction();

        ArchEUIPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    }

    /**
     * setFocus
     * 
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
        menu.add(newRelationshipAction);
        menu.add(editRelationshipAction);
        menu.add(delRelationshipAction);

        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$

        // if projectVO is null, then dissable menu items.
        boolean isEnabled = false;
        isEnabled = (projectVO == null ? false : true);
        newRelationshipAction.setEnabled(isEnabled);
        // if no item is selected, gray out 'delete' and 'edit' option
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        delRelationshipAction.setEnabled(isEnabled);
        editRelationshipAction.setEnabled(isEnabled);

    }

    /**
     * Returns a Set with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     * 
     * @return
     */
    private Set getRelationships() {
        Set rels = null;
        if (projectVO != null) {
            // Get only relationships of activated RFs
            rels = projectVO.getActiveRelationships();
        }
        if (rels == null) {
            rels = new HashSet();
            rels.add("Please open or create a project.");
        }
        return rels;
    }

    /**
     * Call a set of initializations
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
     * Get projectVo from ArchEUIPlugin
     */
    private void initProjectVO() {

        projectVO = ArchEUIPlugin.getDefault().getProjectVo();
    }

    /**
     * Populate the Parameter types from active RFs.
     */
    private void initParamTypes() {
        paramTypes = new ArrayList();
        Iterator itrt;
        Iterator itop;
        RelationshipTypeVO relType;
        OperandsVO operand;

        // Check if projectVO is null, then do nothing and return null.
        if (projectVO == null) {
            return;
        }

        for (Enumeration e = projectVO.getActiveRFs().keys(); e.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            // If the reasoning framwork is disabled, continue next loop.
            if (!((Boolean) projectVO.getActiveRFs().get(rf)).booleanValue()) {
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
                        // add parameter types bound with each relationship types
                        paramTypes.addAll(relType.getParamTypes());
                        // Goto the next relType
                        break;
                    }
                }
            }
        }

    }

    /**
     * Populate column names from ParametreTypeVO to Array columnNames.
     */
    private void populateColumnNames() {

        columnNames = new String[4 + paramTypes.size() * 2];
        int index = 0;
        columnNames[index++] = "";
        columnNames[index++] = RESPONSIBILITY_LH_COLUMN;
        columnNames[index++] = RELATIONSHIP_COLUMN;
        columnNames[index++] = RESPONSIBILITY_RH_COLUMN;
        Iterator it = paramTypes.iterator();
        int paramIndex = 1;
        while (it.hasNext()) {
            it.next();
            // Column names cannot duplicate, or the column index cannot be
            // properly returned.
            columnNames[index++] = "Parameter" + (paramIndex) + "Name";
            columnNames[index++] = "Parameter" + (paramIndex) + "Value";
            paramIndex++;
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
            RelationshipsLabelProvider labelProvider = (RelationshipsLabelProvider) tableViewer
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

    /***********************************************************************************************
     * NewRelationshipAction INNER CLASS
     */
    private class NewRelationshipAction extends Action {

        private NewRelationshipAction() {
            setText("New relationship");
        }

        public void run() {
            RelationshipDialog dialog = new RelationshipDialog(parent.getShell(), null);
            dialog.open();
        }
    }

    /***********************************************************************************************
     * EditScenarioRespAction INNER CLASS
     */
    private class EditRelationshipAction extends Action {

        private EditRelationshipAction() {
            setText("Edit");
        }

        public void run() {
            RelationshipVO vo = (RelationshipVO) (tableViewer.getTable().getSelection()[0]
                    .getData());
            RelationshipDialog dialog = new RelationshipDialog(parent.getShell(), vo);
            dialog.open();
        }
    }

    /***********************************************************************************************
     * DeleteRelationship INNER CLASS
     */
    private class DeleteRelationshipAction extends Action {

        private DeleteRelationshipAction() {
            setText("Delete");
        }

        public void run() {
            delRel();
        }

        /**
         * Delete a Relationship
         */
        private void delRel() {
            MessageDialog closeMsgDialog = new MessageDialog(parent.getShell(), "Delete", null,
                    "Are you sure you want to delete the selected relationship?",
                    MessageDialog.QUESTION, new String[] { "Yes", "No"}, 1);
            closeMsgDialog.open();

            if (closeMsgDialog.getReturnCode() == 0) {

                Item[] sel = table.getSelection();
                int factId;
                //Delete all selected relationships
                for (int i = 0; i < sel.length; i++) {
                    factId = ((RelationshipVO) sel[i].getData()).getFactId();

                    getArchEFacade().deleteRespoRelation(factId);
                }
            }
        }
    }

    /**
     * Clear and create table columns This is necessary when close/open a new project
     */
    private void createTableColumns() {

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

        /**
         * This ResizeListener is used to keep the size user change the column to. To prevent the
         * lost of user changes to the column size after refreshing the table.
         */

        ControlListener resizeListener = new ControlListener() {

            public void controlMoved(ControlEvent e) {

            }

            public void controlResized(ControlEvent e) {
                TableColumn column = (TableColumn) e.widget;
                String columnHead = column.getText();
                int i = 0;
                while (i < STATIC_COLUMN_NUMBER) {
                    if (columnNames[i].equals(columnHead)) {
                        columnWidth[i] = column.getWidth();
                    }
                    i++;
                }
            }
        };

        //Clear all columns first;
        for (int i = table.getColumnCount() - 1; i >= 0; i--) {
            table.getColumn(i).dispose();
        }

        int columnIndex = 0;

        // 1st column with logo
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setWidth(columnWidth[0]);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        column.addControlListener(resizeListener);

        // 2st column with Responsibility left hand side
        column = new TableColumn(table, SWT.NONE, ++columnIndex);
        column.setText(columnNames[columnIndex]);
        column.setWidth(columnWidth[1]);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        column.addControlListener(resizeListener);

        // 3nd column with relationships
        column = new TableColumn(table, SWT.NONE, ++columnIndex);
        column.setText(columnNames[columnIndex]);
        column.setWidth(columnWidth[2]);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        column.addControlListener(resizeListener);

        // 4nd column with Responsibility right hand side
        column = new TableColumn(table, SWT.NONE, ++columnIndex);
        column.setText(columnNames[columnIndex]);
        column.setWidth(160);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        column.addControlListener(resizeListener);

        // Create columns for parameters
        while (++columnIndex < columnNames.length) {
            column = new TableColumn(table, SWT.NONE, columnIndex);
            //column.setText(columnNames[columnIndex]);
            if (columnIndex % 2 == 0) {
                column.setText("Parameter");
            } else {
                column.setText("Value");
            }
            column.setWidth(100);
            column.setAlignment(SWT.LEFT);
            column.addSelectionListener(headerListener);
        }
    }

    /**
     * This view is being created or refreshed after a facade operation or open/close project
     * operation. This method updates all aspects of the tableViewer that can vary in such cases,
     * including setInput.
     */
    private void refreshTableViewer() {
        //        tableViewer.setContentProvider(new RelationshipsContentProvider());
        tableViewer.setInput(getRelationships());
        if (!(tableViewer.getLabelProvider() instanceof RelationshipsLabelProvider)) {
            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new RelationshipsLabelProvider()); // calls refresh
        }
        tableViewer.setColumnProperties(columnNames);

        // Create the cell editors
        CellEditor[] editors = new CellEditor[columnNames.length];
        TextCellEditor textEditor;
        ComboBoxCellEditor cmbEditor;

        // Only initilize combobox when projectVO is not null.
        if (projectVO != null) {
            // Columns : parameters
            String[] items;
            for (int i = 4; i < columnNames.length; i++) {
                switch (i % 2) {
                case 1:
                    // Parameter values
                    // Here only *TEMPORALLY* set a editor.
                    // Since the parameters are dynamically displayed,
                    // the editor should be assigned in the RelationshipsCellModifier.getValue().
                    editors[i] = new TextCellEditor(table);
                    break;
                case 0:
                    //Parameter names
                    // Should initialize with a String[]
                    items = new String[1];
                    items[0] = "";
                    editors[i] = new ComboBoxCellEditor(table, items, SWT.CENTER | SWT.READ_ONLY);
                    break;
                }
            }
        }
        // Assign the cell editors to the viewer
        tableViewer.setCellEditors(editors);

        // Set the cell modifier for the viewer
        tableViewer.setCellModifier(new RelationshipsCellModifier(this));
        sorter = new RelationshipsSorter();
        tableViewer.setSorter(sorter); // calls refresh because sorter changed
    }

    /**
     * Return the column names in a collection
     * 
     * @return List containing column names
     */
    public List getColumnNames() {
        return Arrays.asList(columnNames);
    }

    /**
     * @return Returns the tableViewer.
     */
    public ColoredCellTableViewer getTableViewer() {
        return tableViewer;
    }

    /***********************************************************************************************
     * Label provider for the Relationships table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     */
    private class RelationshipsLabelProvider extends LabelProvider implements ITableLabelProvider,
            ICellColorProvider {

        /** Color to paint cells with data created by ArchE. */
        private Color         srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /** For displaying parameter names and values * */
        private ParameterVO[] params;

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
            // nothing to do
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
            // nothing to do
        }

        /**
         * Returns the correct icon for columns that show icons. Doesn't do anything for columns
         * that show text only.
         * 
         * @param element RelationshipVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed for that RelationshipVO in that given column or null
         *         if the column does not show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {

            // If project is not open
            if (!(element instanceof RelationshipVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            RelationshipVO rel = (RelationshipVO) element;
            // If is the first column -logo
            if (columnIndex == 0) {
                ArrayList questions = (ArrayList) projectVO.getQuestions();
                QuestionToUserVO question = null;
                int relFactId = rel.getFactId();
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
                        if (temp[i] == relFactId) {
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

            return null;
        }

        /**
         * Returns the text to be shown for a given RelationshipVO object and the specified column.
         * Doesn't do anything for columns that show icons only.
         * 
         * @param element RelationshipVO object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that RelationshipVO in that given column or null if
         *         the column shows an icon only.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {

            // if the relationshipVO list is null then display the warning.
            if (!(element instanceof RelationshipVO)) {

                return columnIndex == 1 ? element.toString() : "";

            }

            // If the relationshipVO list is not null.
            RelationshipVO rel = (RelationshipVO) element;
            ResponsibilityVO respo;
            String columnHead = null;
            String paramValue = "";
            String result = "";

            if (columnIndex < 4) {
                switch (columnIndex) {
                case 1:
                    //Display parent responsibility combobox
                    Integer parentId = new Integer(rel.getParentFactId());
                    if (parentId.intValue() != 0) {

                        respo = (ResponsibilityVO) projectVO.getResponsibilities().get(parentId);
                        result = respo==null?"":respo.getName();
                    } else {
                        // if the relationship is being created
                        result = "";
                    }

                    break;
                case 2:
                    //Display relationship combobox
                    result = rel.getType().getName();
                    break;
                case 3:
                    //Display child resposibility combobox
                    Integer childId = new Integer(rel.getChildFactId());
                    if (childId.intValue() != 0) {
                        respo = (ResponsibilityVO) projectVO.getResponsibilities().get(childId);
                        result = respo==null?"":respo.getName();

                    } else {
                        result = "";
                    }
                    break;
                }

            } else {
                // Parsing parameter names and values
                int paramNumber = rel.getParameters().size();
                int paramIndex = (columnIndex - 4) / 2;

                // If this column of parameter has value then,
                if (paramNumber > paramIndex) {
                    // For performance concern, only initial the parameterVOs
                    // when fill the first column of parameter name.
                    if (columnIndex == 4) {
                        ArrayList paramArrayList = (ArrayList) rel.getParameters();

                        Collections.sort(paramArrayList, new Comparator() {

                            public int compare(Object arg0, Object arg1) {
                                return ((ParameterVO) arg0).getParameterType().getName()
                                        .compareTo(
                                                   ((ParameterVO) arg1).getParameterType()
                                                           .getName());
                            }
                        });
                        params = (ParameterVO[]) paramArrayList.toArray(new ParameterVO[0]);
                    }

                    switch (columnIndex % 2) {
                    case 1:
                        // setting parameter value
                        result = params[paramIndex].getValue();
                        break;
                    case 0:
                        // setting parameter name
                        result = params[paramIndex].getParameterType().getName();
                        break;
                    }

                }

            }
            return result;
        }

        /**
         * Returns the foreground color for a given RelationshipVO object and the specified column.
         * If ArchE created the data, the color for the corresponding cell is green Otherwise, the
         * color is the default (black).
         * 
         * @param element RelationshipVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for that RelationshipVO in that given column or null if the default
         *         color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {

            if (!(element instanceof RelationshipVO)) {
                return null;
            }

            RelationshipVO rel = (RelationshipVO) element;
            if (rel.getSource() == CoreFact.ARCHE_CORE && columnIndex == 2) {
                return srcIsArchEColor;
            }

            // paint the parameter value column
            if (columnIndex > 3 && columnIndex % 2 == 1) {
                int paramIndex = (columnIndex - 4) / 2;
                List params = rel.getParameters();
                if (paramIndex < params.size()
                        && ((ParameterVO) params.get(paramIndex)).getSource() == CoreFact.ARCHE_CORE) {
                    return srcIsArchEColor;
                }
            }

            return null;
        }

        /**
         * Returns the background color for a given RelationshipVO object and the specified column.
         * We're not using different background colors in ArchE, so this method returns null so the
         * cell has default background (white)
         * 
         * @param element RelationshipVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for the backgoround of that RelationshipVO in that given column or
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
     * Cell modifier for this view.
     * 
     * @see org.eclipse.jface.viewers.ICellModifier
     */
    private class RelationshipsCellModifier implements ICellModifier {

        private RelationshipsView view;

        /** Save the cell value before modification * */
        private Object            valueBeforeModify;

        /** Initial owner slot value of the parameter (before change)* */
        private int               slotI;

        /**
         * This array holds the list of parameters which will be listed in the parameter name
         * combobox This has to be a member variable, since getValue() and modify() will share it
         */
        private String[]          relParamTypes;

        /**
         * Constructor
         * 
         * @param RelationshipsView an instance of a RelationshipsView
         */
        public RelationshipsCellModifier(RelationshipsView view) {
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
            //if the element is not an instant of RelationshipVO then return
            //            if (!(element instanceof RelationshipVO)) {
            //                return null;
            //            }
            // Find the index of the column

            int columnIndex = view.getColumnNames().indexOf(property);
            Object result = "";

            RelationshipVO rel = (RelationshipVO) element;

            String columnHead = null;
            String paramValue = "";
            String stringValue;
            int i;
            if (columnIndex < 4) {
                // The first 4 columns is readonly.
                return null;

            } else {

                // Parsing parameter names and values
                int paramNumber = rel.getParameters().size();
                int paramIndex = (columnIndex - STATIC_COLUMN_NUMBER) / 2;
                ParameterVO[] params;

                // Everytime, parameterVO[] need to be initialized
                params = ((ParameterVO[]) (rel.getParameters()).toArray(new ParameterVO[0]));

                switch1: switch (columnIndex % 2) {
                case 1:
                    // get parameter value
                    if (paramNumber > paramIndex) {

                        // If this column of parameter has value then,
                        result = params[paramIndex].getValue();

                        // Remember the initial owner slot id, for purpos of modification
                        slotI = params[paramIndex].getOwnerFactId();
                    } else {
                        //                      If no value is set, disabled the celleditor.
                        result = "";
                    }
                    break;
                case 0:
                    // get parameter name

                    // When parameter name cell is focused, a dropdown list will show up
                    // that contains parameter names(types) belong to this relationship type
                    // The celleditor thus has to be reconstructed.

                    // Also, the parameter value cell need to be assigned an editor based
                    // on the data type defined.

                    relParamTypes = new String[rel.getType().getParamTypes().size() + 1];
                    // The first item is blank to indicate this parameter is not set.
                    relParamTypes[0] = "";

                    for (int j = 1; j < relParamTypes.length; j++) {
                        relParamTypes[j] = ((ParameterTypeVO) (rel.getType().getParamTypes()
                                .get(j - 1))).getName();

                    }

                    // Assign editors/values only when there exists parameter types
                    if (relParamTypes.length != 0) {
                        ComboBoxCellEditor cmbEditor = (ComboBoxCellEditor) tableViewer
                                .getCellEditors()[columnIndex];
                        cmbEditor.setItems(relParamTypes);

                        if (paramNumber <= paramIndex) {
                            // if the parameter is not set yet, return UNSELECTED
                            result = new Integer(0);
                        } else {
                            // If this column of parameter has value then,
                            stringValue = params[paramIndex].getParameterType().getName();
                            i = relParamTypes.length - 1;
                            while (!stringValue.equals(relParamTypes[i]) && i > 0) {
                                --i;
                            }
                            result = new Integer(i);

                        }

                    }

                    break;
                }
            }

            valueBeforeModify = result;
            //System.out.println("[RelationshipsView] valuebefore:" + valueBeforeModify + " "
            //        + property);
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
            // if the element is not an instant of RelationshipVO then return
            //            if (!(element instanceof RelationshipVO)) {
            //                return;
            //            }

            //If the value does not change, then quit.

            //System.out.println("[RelationshipsView] valueafter:" + value + " " + property);
            if (valueBeforeModify.equals(value)) {
                return;
            }
            // Find the index of the column
            int columnIndex = view.getColumnNames().indexOf(property);
            if (element instanceof Item) {
                element = ((Item) element).getData();
            }

            RelationshipVO rel = (RelationshipVO) element;

            if (columnIndex >= 4) {
                //The first 4 columns is readonly;

                // Parsing parameter names and values
                int paramNumber = rel.getParameters().size();
                int paramIndex = (columnIndex - STATIC_COLUMN_NUMBER) / 2;
                ParameterVO[] params;

                // Everytime, parameterVO[] need to be initialized
                params = ((ParameterVO[]) (rel.getParameters()).toArray(new ParameterVO[0]));
                switch (columnIndex % 2) {

                case 1:
                    //Parameter Values
                    int dt = 0;
                    // Set parameter value
                    if (paramNumber > paramIndex) {

                        String newValue = value.toString();
                        ParameterVO param = (ParameterVO) params[paramIndex];
                        String defaultValue = param.getParameterType().getDefaultValue();

                        dt = param.getParameterType().getDataType();

                        if (newValue.trim().equals("")) {
                            if (!MessageDialog.openConfirm(parent.getShell(), "Warning",
                                                           "\n\tDo you want to use the default value \""
                                                                   + defaultValue + "\"?")) {
                                return;
                            } else {
                                newValue = defaultValue;
                            }
                        } else {
                            switch (dt) {
                            case ParameterTypeVO.BOOLEAN:
                                int selIndex = ((Integer) value).intValue();
                                newValue = newValue.toLowerCase();

                                if (!newValue.equals("true") && !newValue.equals("false")) {
                                    MessageDialog
                                            .openWarning(parent.getShell(), "Error",
                                                         "\n\tPlease input a BOOLEAN value (true/false).");
                                    return;
                                }

                                break;
                            case ParameterTypeVO.INTEGER:
                                // datatype check only for DOUBLE
                                try {
                                    Integer.parseInt(newValue);
                                } catch (NumberFormatException e) {
                                    //System.out.println("[RelationshipsView] not a number");
                                    MessageDialog.openWarning(parent.getShell(), "Error",
                                                              "\n\tPlease input a INTEGER value.");
                                    return;
                                }
                                break;
                            case ParameterTypeVO.DOUBLE:
                                // datatype check only for DOUBLE
                                try {
                                    Double.parseDouble(newValue);
                                } catch (NumberFormatException e) {
                                    //System.out.println("[RelationshipsView] not a number");
                                    MessageDialog.openWarning(parent.getShell(), "Error",
                                                              "\n\tPlease input a DOUBLE value.");
                                    return;
                                }
                                break;
                            case ParameterTypeVO.STRING:
                                break;
                            }

                        }

                        // If this parameter already has a value then modify it.
                        getArchEFacade().editRespoRelationParameter(param, slotI, slotI,
                                                                    newValue.toString(), dt);
                        view.getTableViewer().refresh();
                    } else {
                        // If the parameter name is not selected yet, do nothing, return.
                        return;
                    }
                    break;

                case 0:
                    //Parameter Names
                    // Get parameter name

                    // When parameter name cell is focused, a dropdown list will show up
                    // that contains parameter names(types) belong to this relationship type
                    // The celleditor thus has to be reconstructed.

                    if (paramNumber > paramIndex) {
                        // If this column of parameter has value, 1. restract it, 2. add the new
                        // parameter

                        // First restract it
                        ParameterVO oldParam = (ParameterVO) params[paramIndex];
                        getArchEFacade().deleteRespoRelationParameter(oldParam.getFactId());

                        // If the new selection is not blank,add a new parameter with value of
                        // default value
                        if (((Integer) value).intValue() != 0) {
                            int newParamIndex = ((Integer) value).intValue();
                            String newParamName = relParamTypes[newParamIndex];

                            ParameterTypeVO paramType = getParamTypeByName(newParamName);
                            getArchEFacade().addRespoRelationParameter(paramType.getId(),
                                                                       rel.getFactId(),
                                                                       paramType.getDefaultValue(),
                                                                       paramType.getDataType());
                        }

                        view.getTableViewer().refresh();
                        return;

                    } else {
                        int newParamIndex = ((Integer) value).intValue();
                        ParameterTypeVO paramType = getParamTypeByName(relParamTypes[newParamIndex]);
                        String id = paramType.getId();

                        // If the same parameter already exists, do not assert, pop a dialog box.

                        for (int i = 0; i < params.length; i++) {
                            if (params[i].getFactType().equals(id)) {
                                MessageDialog.openWarning(parent.getShell(), "Error",
                                                          "\n\tParameter already exists.");
                                //                                MessageDialog closeMsgDialog = new
                                // MessageDialog(parent.getShell(),
                                //                                        "Error", null, "Parameter already exists.",
                                //                                        MessageDialog.QUESTION, new String[] { "Yes"}, 1);
                                //                                closeMsgDialog.open();
                                return;
                            }
                        }

                        // if the parameter is not set yet, assert this parameter with the new value
                        // ""

                        getArchEFacade().addRespoRelationParameter(id, rel.getFactId(),
                                                                   paramType.getDefaultValue(),
                                                                   paramType.getDataType());
                        view.getTableViewer().refresh();

                    }

                    break;
                }

            }

        }
    }

    /***********************************************************************************************
     * This is the sorter for this view
     */
    private class RelationshipsSorter extends ViewerSorter {

        private int[]    priorities;

        private int[]    directions;

        final static int ASCENDING          = 1;

        final static int DEFAULT_DIRECTION  = 0;

        final static int DESCENDING         = -1;

        final static int DESCRIPTION        = 1;

        int[]            default_Directions = new int[columnNames.length];

        int[]            default_Priorities = new int[columnNames.length];

        /**
         * Creates a new relationship sorter.
         */
        public RelationshipsSorter() {
            default_Directions[0] = ASCENDING;
            default_Directions[1] = ASCENDING;
            default_Directions[2] = ASCENDING;
            default_Directions[3] = ASCENDING;
            for (int i = 4; i < columnNames.length; i++) {
                default_Directions[i] = ASCENDING;
            }
            // Initialize sorting priority,

            for (int i = 0; i < columnNames.length - 1; i++) {
                default_Priorities[i] = i + 1;
            }
            // set column[0](logo)'s to be last sorted.
            default_Priorities[columnNames.length - 1] = 0;
            resetState();
        }

        /**
         * Compares two markers, sorting first by the main column of this sorter, then by subsequent
         * columns, depending on the column sort order.
         */
        public int compare(Viewer viewer, Object e1, Object e2) {

            if (!(e1 instanceof RelationshipVO) || !(e1 instanceof RelationshipVO)) {
                return 0;
            }

            RelationshipVO r1 = (RelationshipVO) e1;
            RelationshipVO r2 = (RelationshipVO) e2;
            return compareColumnValue(r1, r2, 0);
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
            directions[priority] = default_Directions[priority];
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
         * Get the priorities list
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
                directions[priorities[0]] = default_Directions[priorities[0]];
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
            priorities = new int[default_Priorities.length];
            System.arraycopy(default_Priorities, 0, priorities, 0, priorities.length);
            directions = new int[default_Directions.length];
            System.arraycopy(default_Directions, 0, directions, 0, directions.length);
        }

        /**
         * Compares two markers, based only on the value of the specified column.
         */
        private int compareColumnValue(RelationshipVO r1, RelationshipVO r2, int depth) {

            if (r1==null && r2==null){
                return 0;
            }else if (r1==null){
                return -1;
            }else if (r2==null){
                return 1;
            }
            
            if (depth >= priorities.length) {
                return 0;
            }

            int columnIndex = priorities[depth];
            int direction = directions[columnIndex];
            int result = 0;
            ResponsibilityVO respo1 = null;
            ResponsibilityVO respo2 = null;
            
            if (columnIndex < STATIC_COLUMN_NUMBER) {
                switch (columnIndex) {
                case 1:
                    // Sort child responsibility column
                    respo1 = (ResponsibilityVO) projectVO.getResponsibilities()
                            .get(new Integer(r1.getParentFactId()));

                    respo2 = (ResponsibilityVO) projectVO.getResponsibilities()
                            .get(new Integer(r2.getParentFactId()));
                    if (respo1!= null && respo2!=null){
                        result = collator.compare(respo1.getName(), respo2.getName());    
                    }
                    break;

                case 3:
                    // Sort child responsibility column
                    respo1 = (ResponsibilityVO) projectVO.getResponsibilities()
                            .get(new Integer(r1.getChildFactId()));

                    respo2 = (ResponsibilityVO) projectVO.getResponsibilities()
                            .get(new Integer(r2.getChildFactId()));
                    if (respo1!= null && respo2!=null){
                        result = collator.compare(respo1.getName(), respo2.getName());
                    }
                    break;
                case 2:
                    // Sort relationships
                    String relation1 = ((RelationshipTypeVO) r1.getType()).getName();
                    String relation2 = ((RelationshipTypeVO) r2.getType()).getName();
                    
                    result = collator.compare(relation1, relation2);
                    
                    break;
                }

            } else {
                // Sort others
                if (columnIndex >= table.getColumnCount()) {
                    // This situation can occur when we deactivate an RF and the number of columns
                    // decrease. Then, in refreshTableViewer we setInput on the table viewer before
                    // recreating the sorter, and set input internally calls refresh and hence
                    // sorter, but the old sorter may be pointing to a col that's not ther anymore.
                    return 0;
                }
                int s = (columnIndex - STATIC_COLUMN_NUMBER);
                int paramIndex = s / 2;
                List params1 = r1.getParameters();
                List params2 = r2.getParameters();
                int size1 = params1.size() - paramIndex;
                int size2 = params2.size() - paramIndex;

                if (size1 <= 0 || size2 <= 0) {
                    result = size1 > size2 ? 1 : (size1 == size2 ? 0 : -1);
                } else {
                    switch (s % 2) {
                    case 0:
                        // For parameter name columns

                        String type1 = ((ParameterVO) params1.get(paramIndex)).getParameterType()
                                .getName();
                        String type2 = ((ParameterVO) params2.get(paramIndex)).getParameterType()
                                .getName();
                        result = collator.compare(type1, type2);

                        break;
                    case 1:
                        // For parameter value columns, sort as double.

                        result = compareDoubleValue((ParameterVO) params1.get(paramIndex),
                                                    (ParameterVO) params2.get(paramIndex),
                                                    columnIndex);
                        break;
                    }

                }

            }

            if (result == 0) {
                return compareColumnValue(r1, r2, depth + 1);
            }
            return result * direction;

        }

        /**
         * Compares the Double Datatype of parameters of two responsibilities.
         */
        private int compareDoubleValue(ParameterVO r1, ParameterVO r2, int columnIndex) {
            double result = 0;
            String r1ParamValue = r1.getValue();
            String r2ParamValue = r2.getValue();
            result = new Double(r1ParamValue == null ? "0" : r1ParamValue).doubleValue()
                    - new Float(r2ParamValue == null ? "0" : r2ParamValue).doubleValue();
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            }
            return 0;
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
     * This is the filter of the RelationshipsView.
     */
    private class RelationshipsFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instant of RelationshipVO then return
            if (!(element instanceof RelationshipVO)) {
                return true;
            }

            String searchString = searchText.getText().trim().toLowerCase();
            if (searchString.equals("")) {
                return true;
            } else {
                ResponsibilityVO respo = (ResponsibilityVO) projectVO.getResponsibilities()
                        .get(new Integer(((RelationshipVO) element).getParentFactId()));
                String parentRespo = respo.getName().toLowerCase();

                respo = (ResponsibilityVO) projectVO.getResponsibilities()
                        .get(new Integer(((RelationshipVO) element).getChildFactId()));
                String childRespo = respo.getName().toLowerCase();

                String relation = ((RelationshipTypeVO) ((RelationshipVO) element).getType())
                        .getName().toLowerCase();
                if (parentRespo.indexOf(searchString) != -1
                        || childRespo.indexOf(searchString) != -1
                        || relation.indexOf(searchString) != -1) {
                    return true;
                }
                return false;
            }
        }
    }

    /**
     * Get ArchE facade.
     */
    private ArchEFacade getArchEFacade() {
        return ArchEUIPlugin.getDefault().getArchEFacade();

    }

    /***********************************************************************************************
     * Content provider for the relationships table view.
     */
    private class RelationshipsContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        /**
         * Content Provider.
         */
        public RelationshipsContentProvider() {
            //System.out.println("[RelationshipsContentProvider] constructor");
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
         * different element. The input may be switched if the user opens another project (?)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            //System.out.println("[RelationshipsContentProvider] inputChanged");
            this.viewer = (TableViewer) viewer;
        }
    }

    /**
     * Called by ArchEUIPlugin when a project is opened/closed or after a facade operation that
     * required refershing this view. It update member variables and changes the viewer based on the
     * currently opened project.
     */
    public void setInputAndRefresh() {
        initPage();
        //Recreat table columns
        createTableColumns();

        // Create and setup the TableViewer
        refreshTableViewer();
        table.redraw();
    }

    /**
     * Get Parameter types from active RFs.
     * 
     * @return Returns ArrayList of parameter types or null if no project is open.
     */
    private ParameterTypeVO getParamTypeByName(String paramTypeName) {
        Iterator itType = paramTypes.iterator();
        ParameterTypeVO paramType;
        while (itType.hasNext()) {
            paramType = (ParameterTypeVO) itType.next();
            if (paramType.getName().equals(paramTypeName)) {
                return paramType;
            }
        }
        return null;
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
                        editRelationshipAction.run();
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

    private void addParamCellEditors() {
    }

}