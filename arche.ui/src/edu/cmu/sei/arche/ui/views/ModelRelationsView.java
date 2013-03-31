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
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogSettings;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.vo.ModelElementVO;
import edu.cmu.sei.arche.vo.ModelRelationTypeVO;
import edu.cmu.sei.arche.vo.ModelRelationVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;

/**
 * View for Model Relations.
 * 
 * @author Neel Mullick
 */
public class ModelRelationsView extends ViewPart implements IPropertyChangeListener {

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The search text box */
    private Text                   searchText;

    /** The table of model relations */
    private Table                  table;

    /** Set the table column property names */
    private final String           MARKER_COLUMN      = "Marker";

    private final String           LHS_ELEMENT_COLUMN = "Element";

    private final String           RELATION_COLUMN    = "Relation";

    private final String           RHS_ELEMENT_COLUMN = "Element";

    /** Array of column names */
    private String[]               columnNames;

    /** The sorter of model relations */
    private ModelRelationsSorter   sorter;

    /** The project VO for this instance of the model relations view */
    private ProjectVO              project;

    /**
     * Maps: int fact id --> ModelElementVO object
     * <p>
     * Store all model elements. It allows a quick lookup to retrieve the names of both the left
     * hand side and right hand side elements of model relations.
     */
    Hashtable                      allModelElements;

    /** Arraylist of all parameter names that are valid for active RFs */
    private ArrayList              paramNames;

    /** The parent control for the model relations view */
    private Composite              parent;

    /** Question parser to retrieve question text (replacing tags with dynamic data) */
    private QuestionParser         questionParser;

    /** Invalid element error message */
    private static final String    INVALID_ELEMENT    = "Not a valid model element";

    /**
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        this.parent = parent;

        // Initialize the question Parser.
        questionParser = ArchEUIPlugin.getDefault().getQuestionParser();

        //Function to initialize relevant member variables of this class
        initPage();

        Label searchLabel = new Label(parent, SWT.RIGHT);
        searchLabel.setText("Elements contain:");

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

        //create the table
        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Create the table Columns
        createTableColumns();

        //create tool tips for the table
        createTableToolTips();

        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new ModelRelationsContentProvider());
        // Create and setup the TableViewer
        refreshTableViewer();
        tableViewer.addFilter(new ModelRelationsFilter());

        // Create layout
        GridLayout layout = new GridLayout(4, false);
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
        gdTable.horizontalSpan = 4;
        table.setLayoutData(gdTable);
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

        //retrieve the ProjectVO from the workbench
        project = ArchEUIPlugin.getDefault().getProjectVo();

        //Getting the parameter names that are valid for all active reasoning frameworks
        initParamNames();

        //populate the columnNames for the view
        populateColumnNames();

    }

    /**
     * Populate the ArrayList of parameter names that are valid for all active RFs or null if no
     * project is open.
     */
    private void initParamNames() {
        paramNames = new ArrayList(0);

        // Check if projectVO is null, then do nothing and return null.
        if (project != null) {
            ArrayList modelRelationTypes = new ArrayList();
            for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
                // If the reasoning framework is disabled, continue next loop.
                if (!((Boolean) project.getActiveRFs().get(rf)).booleanValue()) {
                    continue;
                }
                //add all valid model Relation types (for active RFs) to the modelTypes ArrayList
                modelRelationTypes.addAll(rf.getModelRelationTypes());
            }

            //recurse through all valid model Relation types for all active RFs
            for (Iterator it = modelRelationTypes.iterator(); it.hasNext();) {
                ModelRelationTypeVO modelRelationType = (ModelRelationTypeVO) it.next();
                //recurse through all parameter slot names for valid model Relation types for all
                // active
                // RFs
                for (int i = 0; i < modelRelationType.getParamSlotNames().length; i++) {
                    //since a parameter name should be displayed only once in the view only unique
                    // entries need be stored in this data structure
                    if (!(paramNames.contains(modelRelationType.getParamSlotNames()[i]))) {
                        if (modelRelationType.getParamSlotNames()[i] != null
                                && modelRelationType.getParamSlotNames()[i].trim() != "") {
                            paramNames.add(modelRelationType.getParamSlotNames()[i]);
                        }
                    }
                }
            }
            //to display the columns in alphabetical order
            Collections.sort(paramNames);
        }
    }

    /**
     * Function to retrieve the model elements from the project
     */
    private void initModelElements() {
        // retrieve all model elements
        allModelElements = new Hashtable();
        for (Iterator it = project.getModelElements().iterator(); it.hasNext();) {
            ModelElementVO modelElement = (ModelElementVO) it.next();
            allModelElements.put(new Integer(modelElement.getFactId()), modelElement);
        }
    }

    /**
     * Function to populate the column names
     */
    private void populateColumnNames() {
        //size of the columnNames array should be enough to accommodate the Marker, name and type
        // columns and the valid parameters for all active RFs
        int size = 4 + (paramNames != null ? paramNames.size() : 0);
        columnNames = new String[size];
        int index = 0;
        columnNames[index++] = MARKER_COLUMN;
        columnNames[index++] = LHS_ELEMENT_COLUMN;
        columnNames[index++] = RELATION_COLUMN;
        columnNames[index++] = RHS_ELEMENT_COLUMN;
        //the parameter names
        for (int i = 0; i < paramNames.toArray().length; i++) {
            columnNames[index++] = paramNames.get(i).toString();
        }
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
     * A property in the ArchE user preferences has changed. Here we pay heed to the property that
     * defines the color for information whose source is ArchE.
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals("src_ArchE_color")) {
            Color newColor = createColor(Display.getDefault(), "src_ArchE_color");
            ModelRelationsLabelProvider labelProvider = (ModelRelationsLabelProvider) tableViewer
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
     * Create table Columns
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

        //Clear all columns first;
        for (int i = table.getColumnCount() - 1; i >= 0; i--) {
            table.getColumn(i).dispose();
        }

        int columnIndex = 0;
        // 1st column with Marker
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 2nd column with the lhs element
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(200);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        //3rd column with relation
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(200);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 4th column with the lhs element
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(200);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // Create columns for parameters
        for (int i = 4; i < columnNames.length; i++) {
            column = new TableColumn(table, SWT.NONE, columnIndex++);
            column.setText(columnNames[i]);

//            column.setWidth(columnNames[i].length() * MarkerUtil.PIXELS_OF_CHAR);
            column.setWidth(80);
            column.setAlignment(SWT.LEFT);
            column.addSelectionListener(headerListener);
        }
    }

    /**
     * Method refreshTableViewer.
     */
    private void refreshTableViewer() {
        //setting the input to the tableViewer
        tableViewer.setInput(getActiveModelRelations());

        if (!(tableViewer.getLabelProvider() instanceof ModelRelationsLabelProvider)) {
            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new ModelRelationsLabelProvider()); // calls refresh
        }

        tableViewer.setColumnProperties(columnNames);
        sorter = new ModelRelationsSorter();
        tableViewer.setSorter(sorter);
    }

    /**
     * @return Returns the tableViewer.
     */
    public ColoredCellTableViewer getViewer() {
        return tableViewer;
    }

    /**
     * Returns a Set with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     * 
     * @return
     */
    private Set getActiveModelRelations() {
        Set activeModelRelations = null;
        if (project != null) {
            initModelElements();
            activeModelRelations = project.getActiveModelRelations();
        }
        if (activeModelRelations == null) {
            activeModelRelations = new HashSet();
            activeModelRelations.add("Please open or create a project.");
        }
        return activeModelRelations;
    }

    /**
     * Called when this view now points to a different project. It changes the viewer input based on
     * the currently open project and calls refresh on the viewer.
     */
    public void setInputAndRefresh() {

        initPage();

        //Recreate table columns
        createTableColumns();
        refreshTableViewer();
        table.redraw();
    }

    /***********************************************************************************************
     * Label provider for the Model Relations table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     */
    private class ModelRelationsLabelProvider extends LabelProvider implements ITableLabelProvider,
            ICellColorProvider {

        /** Color to paint cells with data created by ArchE. */
        private Color srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /**
         * Auto-generated by the wizard.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
        }

        /**
         * Auto-generated by the wizard.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * Auto-generated by the wizard.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * Auto-generated by the wizard.
         * 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
        }

        /**
         * Returns the correct icon for columns that show icons. Doesn't do anything for columns
         * that show text only.
         * 
         * @param element Object that if correct should be correctly cast to a ModelRelationVO
         *            object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed in the given column or null if the column does not
         *         show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            // If project is not open
            if (!(element instanceof ModelRelationVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            // If is the first column -Marker
            if (columnIndex == 0) {
                ModelRelationVO modRelation = (ModelRelationVO) element;
                ArrayList questions = (ArrayList) project.getQuestions();
                QuestionToUserVO question = null;
                int modRelationFactId = modRelation.getFactId();
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
                // check if the modRelationFactId is in them.
                loop1: while (itq.hasNext()) {
                    question = (QuestionToUserVO) itq.next();
                    temp = question.getAffectedFacts();

                    int temp1[] = new int[temp.length + 1];
                    int length = temp.length;
                    System.arraycopy(temp, 0, temp1, 0, length);
                    temp = temp1;
                    temp[length] = question.getParentFactId();

                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] == modRelationFactId) {
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
         * Returns the text to be shown for a given ModelRelationVO object and the specified column.
         * Doesn't do anything for columns that show icons only.
         * 
         * @param element Object that if correct should be correctly cast to a ModelRelationVO
         *            object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that Object in the given column or the error message
         *         if the ProjectVO was null.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {

            // if the element is not an instance of the ModelRelationVO.
            if (!(element instanceof ModelRelationVO)) {
                return columnIndex == 1 ? element.toString() : "";
            }

            ModelRelationVO modRel = (ModelRelationVO) element;
            String columnHead = null;
            switch (columnIndex) {
            case 0:
                // Marker
                return "";
            case 1:
                // lhs element
                Integer lhsFactId = new Integer(modRel.getLhsFactId());
                ModelElementVO mLVO = (ModelElementVO) allModelElements.get(lhsFactId);
                String lhsName = mLVO == null ? INVALID_ELEMENT : mLVO.getName();
                return (lhsName != null ? lhsName : "");
            case 2:
                // relation
                // The relation is the name of the fact without the module name prefix and without
                // the
                // "::"
                String type = modRel.getFactType()
                        .substring(modRel.getFactType().lastIndexOf(":") + 1);
                return (type != null ? type : "");
            case 3:
                // rhs element
                Integer rhsFactId = new Integer(modRel.getRhsFactId());
                ModelElementVO mRVO = (ModelElementVO) allModelElements.get(rhsFactId);
                String rhsName = mRVO == null ? INVALID_ELEMENT : mRVO.getName();
                return (rhsName != null ? rhsName : "");
            default:
                // parameters
                columnHead = table.getColumn(columnIndex).getText();
                String paramVal = modRel.getParameterValueByName(columnHead);
                return (paramVal != null ? paramVal : "");
            }
        }

        /**
         * Returns the foreground color for a given ModelRelationVO object and the specified column.
         * If ArchE created the data, the color for the corresponding cell is as defined in the
         * project preferences. Otherwise, the color is the default (black). In this view, all
         * ModelRelationVOs are created by ArchE.
         * 
         * @param element Object that if correct should be correctly case to a ModelRelationVO
         *            object
         * @param columnIndex zero-based index of the column
         * @return Color object in which the contents are to be displayed for the element in the
         *         given column or or null if the default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            return (element instanceof ModelRelationVO) ? srcIsArchEColor : null;
        }

        /**
         * Returns the background color for a given ModelRelationVO object and the specified column.
         * We're not using different background colors in ArchE, so this method returns null so the
         * cell has default background (white)
         * 
         * @param element Object that if correct should be correctly cast to a ModelRelationVO
         *            object
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

    /***********************************************************************************************
     * This is the sorter for the ModelRelationsView.
     */
    private class ModelRelationsSorter extends ViewerSorter {

        final static int ASCENDING         = 1;

        final static int DEFAULT_DIRECTION = 0;

        final static int DESCENDING        = -1;

        final static int LHSELEMENT        = 1;

        final static int RELATION          = 2;

        final static int RHSELEMENT        = 3;

        private int[]    priorities;

        private int[]    directions;

        int[]            defaultPriorities = new int[columnNames.length];

        int[]            defaultDirections = new int[columnNames.length];

        /**
         * Creates a new sorter.
         */
        public ModelRelationsSorter() {
            defaultDirections[0] = ASCENDING;
            defaultDirections[1] = ASCENDING;

            for (int i = 2; i < columnNames.length; i++) {
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
            if ((e1 instanceof ModelRelationVO) && (e2 instanceof ModelRelationVO)) {
                ModelRelationVO m1 = (ModelRelationVO) e1;
                ModelRelationVO m2 = (ModelRelationVO) e2;
                return compareColumnValue(m1, m2, 0);
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
        private int compareColumnValue(ModelRelationVO m1, ModelRelationVO m2, int depth) {
            if (depth >= priorities.length) {
                return 0;
            }
            int columnIndex = priorities[depth];
            int direction = directions[columnIndex];
            String columnHead = null;
            int result = 0;
            switch (columnIndex) {
            case LHSELEMENT:
                /* LHS ELEMENT */
                Integer m1LhsFactId = new Integer(m1.getLhsFactId());
                Integer m2LhsFactId = new Integer(m2.getLhsFactId());
                ModelElementVO mL1VO = (ModelElementVO) allModelElements.get(m1LhsFactId);
                ModelElementVO mL2VO = (ModelElementVO) allModelElements.get(m2LhsFactId);
                String m1Lhs = mL1VO == null ? INVALID_ELEMENT : mL1VO.getName();
                String m2Lhs = mL2VO == null ? INVALID_ELEMENT : mL2VO.getName();
                result = collator.compare(m1Lhs, m2Lhs);
                if (result == 0) {
                    return compareColumnValue(m1, m2, depth + 1);
                }
                return result * direction;
            case RELATION:
                /* Relation */
                String m1Type = m1.getFactType().substring(m1.getFactType().lastIndexOf(":") + 1);
                String m2Type = m2.getFactType().substring(m2.getFactType().lastIndexOf(":") + 1);

                m1Type = m1Type == null ? "" : m1Type;
                m2Type = m2Type == null ? "" : m2Type;

                result = collator.compare(m1Type, m2Type);
                if (result == 0) {
                    return compareColumnValue(m1, m2, depth + 1);
                }
                return result * direction;
            case RHSELEMENT:
                /* RHS ELEMENT */
                Integer m1RhsFactId = new Integer(m1.getRhsFactId());
                Integer m2RhsFactId = new Integer(m2.getRhsFactId());
                ModelElementVO mR1VO = (ModelElementVO) allModelElements.get(m1RhsFactId);
                ModelElementVO mR2VO = (ModelElementVO) allModelElements.get(m2RhsFactId);
                String m1Rhs = mR1VO == null ? INVALID_ELEMENT : mR1VO.getName();
                String m2Rhs = mR2VO == null ? INVALID_ELEMENT : mR2VO.getName();
                result = collator.compare(m1Rhs, m2Rhs);
                if (result == 0) {
                    return compareColumnValue(m1, m2, depth + 1);
                }
                return result * direction;
            default:
                //TODO implement ordering for params based on data type for now the ordering is
                // rather simplistic - just uses stringValue of parameter values for sorting
                /* parameters */
                if (columnIndex >= table.getColumnCount()) {
                    // This situation can occur when we deactivate an RF and the number of columns
                    // decrease. Then, in refreshTableViewer we setInput on the table viewer before
                    // recreating the sorter, and set input internally calls refresh and hence
                    // sorter, but the old sorter may be pointing to a col that's not ther anymore.
                    return 0;
                }
                columnHead = table.getColumn(columnIndex).getText();
                String m1ParamValue = m1.getParameterValueByName(columnHead);
                String m2ParamValue = m2.getParameterValueByName(columnHead);

                m1ParamValue = m1ParamValue == null ? "" : m1ParamValue;
                m2ParamValue = m2ParamValue == null ? "" : m2ParamValue;

                result = collator.compare(m1ParamValue, m2ParamValue);
                if (result == 0) {
                    return compareColumnValue(m1, m2, depth + 1);
                }
                return result * direction;
            }
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

    /***********************************************************************************************
     * This is the filter of the ModelRelationsView.
     */
    private class ModelRelationsFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instance of ModelRelationVO then return
            if (!(element instanceof ModelRelationVO)) {
                return true;
            }

            String searchString = searchText.getText().trim().toLowerCase();
            if (searchString.equals("")) {
                return true;
            } else {
                Integer modLhsFactId = new Integer(((ModelRelationVO) element).getLhsFactId());
                String lhsModelElement = INVALID_ELEMENT.toLowerCase();
                if (allModelElements.get(modLhsFactId) instanceof ModelElementVO) {
                    lhsModelElement = ((ModelElementVO) allModelElements.get(modLhsFactId))
                            .getName().toLowerCase();
                }

                Integer modRhsFactId = new Integer(((ModelRelationVO) element).getRhsFactId());
                String rhsModelElement = INVALID_ELEMENT.toLowerCase();
                if (allModelElements.get(modLhsFactId) instanceof ModelElementVO) {
                    rhsModelElement = ((ModelElementVO) allModelElements.get(modRhsFactId))
                            .getName().toLowerCase();
                }

                if (lhsModelElement.indexOf(searchString) != -1
                        || rhsModelElement.indexOf(searchString) != -1) {
                    return true;
                }
                return false;
            }
        }
    }

    /***********************************************************************************************
     * Content provider for the ModelRelationsview.
     */
    private class ModelRelationsContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        public ModelRelationsContentProvider() {
            //System.out.println("[ModelRelationsContentProvider] constructor");
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
            //System.out.println("[ModelRelationsContentProvider] inputChanged");
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