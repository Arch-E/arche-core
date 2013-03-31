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
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogSettings;
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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.ui.dialog.QuestionToUserDialog;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;

/**
 * Questions table view.
 * 
 * @author Henry Chen, Hyunwoo Kim
 */
public class QuestionsView extends ViewPart implements IPropertyChangeListener {

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The table of Questions */
    private Table                  table                    = null;

    /** The text of the search */
    public Text                    searchText               = null;

    /** Set the table column property names */
    private final String           LOGO_COLUMN              = "Logo";
    
    private final String           PRIORITY_COLUMN              = "Priority";

    private final String           QUESTION_TYPE_COLUMN     = "Question type";

    private final String           QUESTION_CATEGORY_COLUMN = "Question category";

    private final String           QUESTION_TEXT_COLUMN     = "Question text";

    /** Set column names */
    private String[]               columnNames;

    /** The sorter for the table of Questions */
    private QuestionsSorter        sorter;

    /** The parent containing this view */
    private Composite              parent;

    private ProjectVO              projectVO;

    /** Question parser to replace the tags in the source question text */
    private QuestionParser         questionParser;

    /**
     * Action activated when the user double clicks a line or select Open from the popup menu
     */
    private OpenQuestionAction     openQuestionAction;

    /**
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        this.parent = parent;

        // Initialize the question Parser.
        questionParser = ArchEUIPlugin.getDefault().getQuestionParser();

        populateColumnNames();

        // Put the name of the columns in an array. In this view, the column names don't vary,
        // so they can be set once. In views that have parameters, populateColumnNames have to
        // be called each time we don setInputAndRefresh cause the number of cols vary.
        initPage();

        // Create layout
        GridLayout layout = new GridLayout(3, false);
        layout.verticalSpacing = 5;
        parent.setLayout(layout);

        //        sorter = new QuestionsSorter();

        // Dynamic search label
        Label searchLabel = new Label(parent, SWT.RIGHT);
        searchLabel.setText("Question contains:");
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 200;
        searchLabel.setLayoutData(gdSearchLabel);

        //creating the dynamic search text box and associating the listener to it
        searchText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        GridData gdSearchText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gdSearchText.widthHint = 200;
        searchText.setLayoutData(gdSearchText);
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
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        // Create table columns
        createTableColumns();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new QuestionsContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new QuestionsFilter()); // calls refresh
        createContextMenu();

        openQuestionAction = new OpenQuestionAction();
        tableViewer.addOpenListener(new IOpenListener() {

            public void open(OpenEvent event) {
                openQuestionAction.run();
            }
        });

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

    /**
     * Add the options to the popup menu
     * 
     * @param menu
     */
    private void fillContextMenu(IMenuManager menu) {
        menu.add(openQuestionAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$
        // if projectVO is null, then dissable menu items.
        boolean isEnabled = false;
        isEnabled = (projectVO == null ? false : true);
        // if no item is selected, gray out 'open' option
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        openQuestionAction.setEnabled(isEnabled);
    }

    /**
     * Returns a List with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     */
    private List getQuestions() {
        List questions = null;
        if (projectVO != null) {
            questions = projectVO.getQuestions();
        }
        if (questions == null) {
            questions = new ArrayList();
            questions.add("Please open or create a project.");
        }
        return questions;
    }

    /**
     * Populate column names
     */
    private void populateColumnNames() {
        columnNames = new String[5];
        columnNames[0] = LOGO_COLUMN;
        columnNames[1] = PRIORITY_COLUMN;
        columnNames[2] = QUESTION_TYPE_COLUMN;
        columnNames[3] = QUESTION_CATEGORY_COLUMN;
        columnNames[4] = QUESTION_TEXT_COLUMN;
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
            QuestionsLabelProvider labelProvider = (QuestionsLabelProvider) tableViewer
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
     * Clear and create table columns.
     */
    private void createTableColumns() {
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
	                if (column == sorter.getTopPriority())
	                    sorter.reverseTopPriority();
	                else {
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
        // 2nd column with logo
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setText(columnNames[1]);
        column.setWidth(50);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 3rd column with question type
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setText(columnNames[2]);
        column.setWidth(150);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 4th column with question category
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setText(columnNames[3]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 5th column with question category
        column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setText(columnNames[4]);
        column.setWidth(500);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
    }

    /**
     * This view is being created or refreshed after a facade operation or open/close project
     * operation. This method updates all aspects of the tableViewer that can vary in such cases,
     * including setInput.
     */
    private void refreshTableViewer() {
        tableViewer.setInput(getQuestions());
        if (!(tableViewer.getLabelProvider() instanceof QuestionsLabelProvider)) {
            // The view is being created

            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new QuestionsLabelProvider(table)); // calls refresh

            // THE setColumnProperties STATEMENT IS INSIDE THIS IF BECAUSE THE COLUMNS DON'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, setColumnProperties IS CALLED EVERYTIME.
            tableViewer.setColumnProperties(columnNames);

            // THE CREATION OF THE SORTER IS INSIDE THIS IF BECAUSE THE SORTER DOESN'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, SORTER HAS TO BE RECREATED EVERYTIME.
            sorter = new QuestionsSorter();
            tableViewer.setSorter(sorter);
        } else {
            // The view is being refreshed by calling setInputAndRefresh
            ((QuestionsSorter) tableViewer.getSorter()).resetState();
            tableViewer.refresh();
        }
    }

    /**
     * @return Returns the tableViewer.
     */
    public ColoredCellTableViewer getTableViewer() {
        return tableViewer;
    }

    /**
     * Called by ArchEUIPlugin when a project is opened/closed or after a facade operation that
     * required refershing this view. It update member variables and changes the viewer based on the
     * currently opened project.
     */
    public void setInputAndRefresh() {
        initPage();
        refreshTableViewer();
        tableViewer.refresh();
        table.redraw();
    }

    /***********************************************************************************************
     * Label provider for the Questions table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     * 
     * @author Henry Chen
     */
    private class QuestionsLabelProvider extends LabelProvider implements ITableLabelProvider,
            ICellColorProvider {

        /** table widget associated to this provider */
        private Table table;

        /** Color to paint cells with data created by ArchE. */
        private Color srcIsArchEColor = createColor(Display.getDefault(), "src_ArchE_color");

        /**
         * Constructor just stores the table reference.
         * 
         * @param table created inQuestionosView and used to set color of individual cells
         */
        public QuestionsLabelProvider(Table table) {
            super();
            this.table = table;
        }

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
         * @param element QuestionToUserVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed for that QuestionToUserVO in that given column or
         *         null if the column does not show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            // If project is not open
            if (!(element instanceof QuestionToUserVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }
            if (columnIndex == 0) {
                return (Image) MarkerUtil.getImage("question");
            }
            return null;
        }

        /**
         * Returns the text to be shown for a given QuestionToUserVO object and the specified
         * column. Doesn't do anything for columns that show icons only.
         * 
         * @param element QuestionToUserVO object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that QuestionToUserVO in that given column or null if
         *         the column shows an icon only.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            // if the questionToUserVO list is null then display the warning.
            if (!(element instanceof QuestionToUserVO)) {
                return columnIndex == 1 ? element.toString() : "";
            }
            QuestionToUserVO question = (QuestionToUserVO) element;
            String columnHead = null;
            String result = "";
            switch (columnIndex) {
            case 0:
                // LOGO_COLUMN
                result = "";
                break;
            case 1:
                // PRIORITY_COLUMN
                result = question.getPriority();
                break;    
            case 2:
                // QUESTION_TYPE_COLUMN
                result = question.getQuestionId();
                break;
            case 3:
                // QUESTION_CATETORY_COLUMN
                result = questionParser.getQuestionCategory(question);

                break;
            case 4:
                // QUESTION_TEXT
                result = questionParser.getQuestionText(question);
                break;
            }
            return result;
        }

        /**
         * Returns the foreground color for a given QuestionToUserVO object and the specified
         * column. If ArchE created the data, the color for the corresponding cell is ??? Otherwise,
         * the color is the default (black).
         * 
         * @param element QuestionToUserVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for that QuestionToUserVO in that given column or null if the
         *         default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            if (!(element instanceof QuestionToUserVO)) {
                return null;
            }
            return null;
        }

        /**
         * Returns the background color for a given QuestionToUserVO object and the specified
         * column. We're not using different background colors in ArchE, so this method returns null
         * so the cell has default background (white)
         * 
         * @param element QuestionToUserVO object
         * @param columnIndex zero-based index of the column
         * @return Color object for the backgoround of that QuestionToUserVO in that given column or
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
     * This is the Question's sorter.
     */
    private class QuestionsSorter extends ViewerSorter {

        private int[]    priorities;

        private int[]    directions;

        final static int ASCENDING          = 1;

        final static int DEFAULT_DIRECTION  = 0;

        final static int DESCENDING         = -1;

        final static int DESCRIPTION        = 1;

        int[]            DEFAULT_PRIORITIES = new int[columnNames.length];

        int[]            DEFAULT_DIRECTIONS = new int[columnNames.length];

        /**
         * Creates a new sorter.
         */
        public QuestionsSorter() {
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
            if (!(e1 instanceof QuestionToUserVO) || !(e1 instanceof QuestionToUserVO)) {
                return 0;
            }
            QuestionToUserVO r1 = (QuestionToUserVO) e1;
            QuestionToUserVO r2 = (QuestionToUserVO) e2;
            return compareColumnValue(r1, r2, 0);
        }

        public void setTopPriority(int priority) {
            if (priority < 0 || priority >= priorities.length)
                return;
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
         * Compares two markers, based only on the value of the specified column.
         */
        private int compareColumnValue(QuestionToUserVO r1, QuestionToUserVO r2, int depth) {
            if (depth >= priorities.length)
                return 0;
            int columnIndex = priorities[depth];
            int direction = directions[columnIndex];
            int result=0;
            if (columnIndex != 0) {
            	switch(columnIndex){
            	case 1:
            		double d1, d2;
                	try{
    	            	d1 = Double.parseDouble(r1.getPriority());
                	} catch(NumberFormatException e){
                		return -1;
                	}    
                	try{                	
    	            	d2 = Double.parseDouble(r2.getPriority());
                	} catch(NumberFormatException e){
                		return 1;
                	}    
	            	result = Double.compare(d1, d2);
	                if (result == 0) {
	                    return compareColumnValue(r1, r2, depth + 1);
	                }
	                return result * direction;
            	case 2:
	                // Question type
	                result = collator.compare(r1.getQuestionId(), r2.getQuestionId());
	                if (result == 0)
	                    return compareColumnValue(r1, r2, depth + 1);
	                return result * direction;
            	case 3:
            		// QUESTION_CATETORY_COLUMN
            		String qc1 = questionParser.getQuestionCategory(r1);
            		String qc2 = questionParser.getQuestionCategory(r2);
	                result = collator.compare(qc1, qc2);
	                if (result == 0)
	                    return compareColumnValue(r1, r2, depth + 1);
	                return result * direction;
            	case 4:
            		// QUESTION_TEXT
            		String qt1 = questionParser.getQuestionText(r1);
            		String qt2 = questionParser.getQuestionText(r2);
	                result = collator.compare(qt1, qt2);
	                if (result == 0)
	                    return compareColumnValue(r1, r2, depth + 1);
	                return result * direction;
	            default:
	                return result * direction;	            	
            	}            	
            }
            return 0;
        }

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
     * Content provider for the Questions table view.
     * 
     * @author Henry Chen
     */
    private class QuestionsContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        /**
         * Registers itself as observer of QuestionToUserVO objects and others.
         */
        public QuestionsContentProvider() {
            //System.out.println("[QuestionsContentProvider] constructor");
        }

        /**
         * Returns the elements to display in the viewer When the input of the viewer is set to
         * <code>inputElement</code>.
         * 
         * @return array of objects to be displayed (each element corresponds to a row)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((ArrayList) inputElement).toArray();
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
         * Register this content provider as observers of Question VO objects.
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            //System.out.println("[QuestionsContentProvider] inputChanged");
            this.viewer = (TableViewer) viewer;
        }
    }

    /***********************************************************************************************
     * Inner class that is the action to open the dialog box of a question
     */
    private class OpenQuestionAction extends Action {

        private OpenQuestionAction() {
            setText("Open");
        }

        /**
         * Open the QuestionToUser dialog box displaying the selected question.
         * 
         * @see org.eclipse.jface.action.IAction#run()
         */
        public void run() {
            if (projectVO != null) {
                QuestionToUserVO question = (QuestionToUserVO) (tableViewer.getTable()
                        .getSelection()[0].getData());
                QuestionToUserDialog dlg = new QuestionToUserDialog(parent.getShell(), question);
                dlg.open();
            }

        }
    }

    /***********************************************************************************************
     * This is the filter of the QuestionsView.
     */
    private class QuestionsFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instant of RelationshipVO then return
            if (!(element instanceof QuestionToUserVO)) {
                return true;
            }
            String searchString = searchText.getText().trim().toLowerCase();
            if (searchString.equals("")) {
                return true;
            } else {
                String desc = questionParser.getQuestionText((QuestionToUserVO) element)
                        .toLowerCase();
                return desc.indexOf(searchString) == -1 ? false : true;
            }
        }
    }

    /**
     * Initialize member variables that will be used by the view
     */
    private void initPage() {
        // If the search Text box is created, then clear the content
        if (searchText != null) {
            searchText.setText("");
        }
        // Retrieve the current ProjectVO (null if no proj is open) from ArchEUIPlugin
        projectVO = ArchEUIPlugin.getDefault().getProjectVo();
    }

}