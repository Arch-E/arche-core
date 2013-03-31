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
import edu.cmu.sei.arche.ui.dialog.FunctionDialog;
import edu.cmu.sei.arche.vo.FunctionVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;

/**
 * View for Functions.
 * 
 * @author Neel Mullick, Hyunwoo Kim
 */
public class FunctionsView extends ViewPart implements IPropertyChangeListener {

    private Action                 newFunctionAction;

    private Action                 editFunctionAction;

    private Action                 delFunctionAction;

    private Action                 mappedResponsibilitiesAction;

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;

    /** The search text box */
    private Text                   searchText;

    /** The table of functions */
    private Table                  table;

    /** Set the table column property names */
    private final String           MARKER_COLUMN      = "Marker";

    private final String           ID_COLUMN          = "Id";

    private final String           DESCRIPTION_COLUMN = "Description";

    /** Array of column names */
    private String[]               columnNames;

    /** List of column names */
    private List                   listColumnNames;

    /** The sorter of functions */
    private FunctionsSorter        sorter;

    /** The project VO for this instance of the Functions view */
    private ProjectVO              project;

    /** The HashSet that is to be the input for the Functions view */
    HashSet                        functions;

    /** The parent control for the functions view */
    private Composite              parent;

    /**
     * Question parser to retrieve question text (replacing tags with dynamic data)
     */
    private QuestionParser         questionParser;

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
        Label searchLabel = new Label(parent, SWT.RIGHT | SWT.WRAP);
        searchLabel.setText("Description contains:");
        // Dynamic search label
        GridData gdSearchLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdSearchLabel.widthHint = 200;
        searchLabel.setLayoutData(gdSearchLabel);

        //creating the dynamic search text box and associating the listener to
        // it
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

        //create the table
        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        // Table
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        // Create the table columns
        createTableColumns();

        //create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new FunctionsContentProvider());
        refreshTableViewer();
        tableViewer.addFilter(new FunctionFilter()); //calls refresh

        createContextMenu();

        newFunctionAction = new NewFunctionAction();
        editFunctionAction = new EditFunctionAction();
        delFunctionAction = new DeleteFunctionAction();
        mappedResponsibilitiesAction = new MappedResponsibilitiesAction();
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

        listColumnNames = Arrays.asList(columnNames);

    }

    /**
     * Function to populate the column names
     */
    private void populateColumnNames() {
        columnNames = new String[3];
        int index = 0;
        columnNames[index++] = MARKER_COLUMN;
        columnNames[index++] = ID_COLUMN;
        columnNames[index++] = DESCRIPTION_COLUMN;
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
        // Register the context menu so that other plug-ins can add actions.
        getSite().registerContextMenu(menuMgr, getSite().getSelectionProvider());
    }

    /**
     * Add the options to the popup menu
     * 
     * @param menu
     */
    private void fillContextMenu(IMenuManager menu) {
        menu.add(newFunctionAction);
        menu.add(editFunctionAction);
        menu.add(delFunctionAction);
        menu.add(mappedResponsibilitiesAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$

        Object[] menuItems = menu.getItems();
        // if projectVO is null, then disable menu items.
        boolean isEnabled = false;

        isEnabled = (project == null ? false : true);

        newFunctionAction.setEnabled(isEnabled);

        // if no item is selected, gray out 'delete' and 'mapped
        // responsibilities' option
        if (isEnabled) {
            isEnabled = table.getSelection().length == 0 ? false : true;
        }
        editFunctionAction.setEnabled(isEnabled);
        delFunctionAction.setEnabled(isEnabled);
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
            FunctionsLabelProvider labelProvider = (FunctionsLabelProvider) tableViewer
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
     * Used in JUnit tests
     * 
     * @return Returns the delFunctionAction.
     */
    public Action getDelFunctionAction() {
        return delFunctionAction;
    }

    /**
     * Used in JUnit tests
     * 
     * @return Returns the searchText.
     */
    public Text getSearchText() {
        return searchText;
    }

    /***********************************************************************************************
     * NewFunctionAction INNER CLASS
     */
    private class NewFunctionAction extends Action {

        private NewFunctionAction() {
            setText("New function");
        }

        public void run() {
            FunctionDialog dialog = new FunctionDialog(parent.getShell(), null);
            dialog.open();
        }

    }

    /***********************************************************************************************
     * EditFunctionAction INNER CLASS
     */
    private class EditFunctionAction extends Action {

        private EditFunctionAction() {
            setText("Edit");
        }

        public void run() {
            FunctionVO vo = (FunctionVO) (tableViewer.getTable().getSelection()[0].getData());
            FunctionDialog dialog = new FunctionDialog(parent.getShell(), vo);
            dialog.open();
        }
    }

    /***********************************************************************************************
     * DeleteFunctionAction INNER CLASS
     */
    private class DeleteFunctionAction extends Action {

        private DeleteFunctionAction() {
            setText("Delete");
        }

        /**
         * Delete a Function
         */
        public void run() {
            MessageDialog closeMsgDialog = new MessageDialog(parent.getShell(), "Delete", null,
                    "Are you sure you want to delete the selected function?",
                    MessageDialog.QUESTION, new String[] { "Yes", "No"}, 1);
            closeMsgDialog.open();
            if (closeMsgDialog.getReturnCode() == 0) {
                Item[] sel = table.getSelection();
                int factId;
                //Delete all selected Functions
                for (int i = 0; i < sel.length; i++) {
                    factId = ((FunctionVO) sel[i].getData()).getFactId();
                    getArchEFacade().deleteFunction(factId);
                }
            }
        }
    }

    /***********************************************************************************************
     * MappedResponsibilitiesAction INNER CLASS
     */
    private class MappedResponsibilitiesAction extends Action {

        private MappedResponsibilitiesAction() {
            setText("Mapped responsibilities");
        }

        /**
         * Displays the responsibilities mapped to the selected function in the
         * FunctionRespMappingView.
         */
        public void run() {
            Item[] sel = table.getSelection();

            String funcStr = ((FunctionVO) sel[0].getData()).getDescription();

            IWorkbenchWindow window = ArchEUIPlugin.getDefault().getWorkbench()
                    .getActiveWorkbenchWindow();
            try {
                IWorkbenchPage page = window.getActivePage();
                FunctionRespMappingView view = (FunctionRespMappingView) page
                        .showView("SEI.ArchE.UI.FunctionRespMappingView");
                // Search mappings of the selected function with
                // responsibilities
                view.searchText.setText(funcStr);
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
        // 1st column with Marker
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex++);
        column.setWidth(20);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 2nd column with id
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex++]);
        column.setWidth(50);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
        // 3rd column with description
        column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(headerListener);
    }

    /**
     * This view is being created or refreshed after a facade operation or open/close project
     * operation. This method updates all aspects of the tableViewer that can vary in such cases,
     * including setInput.
     */
    private void refreshTableViewer() {

        tableViewer.setInput(getFunctions());
        if (!(tableViewer.getLabelProvider() instanceof FunctionsLabelProvider)) {
            // The view is being created

            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new FunctionsLabelProvider()); // calls refresh

            // THE setColumnProperties STATEMENT IS INSIDE THIS IF BECAUSE THE COLUMNS DON'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, setColumnProperties IS CALLED EVERYTIME.
            tableViewer.setColumnProperties(columnNames);

            // THE CREATION OF THE SORTER IS INSIDE THIS IF BECAUSE THE SORTER DOESN'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, SORTER HAS TO BE RECREATED EVERYTIME.
            sorter = new FunctionsSorter(); // instantiate and reset sorter
            tableViewer.setSorter(sorter); // calls refresh because sorter changed
        } else {
            // The view is being refreshed by calling setInputAndRefresh
            ((FunctionsSorter) tableViewer.getSorter()).resetState();
            tableViewer.refresh();
        }
    }

    /**
     * Returns a Set with the data that is used as the input for the table viewer. If no project is
     * open, it will have just a String with the msg asking user to open project.
     * 
     * @return
     */
    private Set getFunctions() {
        Set funcs = null;
        if (project != null) {
            funcs = project.getFunctions();
        }
        if (funcs == null) {
            funcs = new HashSet();
            funcs.add("Please open or create a project.");
        }
        return funcs;
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

    /***********************************************************************************************
     * Label provider for the Functions table view. NB: IColorProvider cannot be used for
     * color-coding the cells because it only allows setting the color of the entire row. Therefore,
     * instead, we pass the Table object to the constructor and set the color cell using TableItems
     * from Table.
     */
    private class FunctionsLabelProvider extends LabelProvider implements ITableLabelProvider,
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
         * @param element Object that if correct should be correctly case to a FunctionVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed in the given column or null if the column does not
         *         show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            // If project is not open
            if (!(element instanceof FunctionVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            // If is the first column -Marker
            if (columnIndex == 0) {
                FunctionVO func = (FunctionVO) element;
                ArrayList questions = (ArrayList) project.getQuestions();
                QuestionToUserVO question = null;
                int funcFactId = func.getFactId();
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
                        if (temp[i] == funcFactId) {
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
         * Returns the text to be shown for a given FunctionVO object and the specified column.
         * Doesn't do anything for columns that show icons only.
         * 
         * @param element Object that if correct should be correctly case to a FunctionVO object
         * @param columnIndex zero-based index of the column
         * @return String to be displayed for that Object in the given column or the error message
         *         if the ProjectVO was null.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {

            // if the FunctionVO is null then display the warning.
            if (!(element instanceof FunctionVO)) {
                return columnIndex == 2 ? element.toString() : "";
            }

            FunctionVO func = (FunctionVO) element;
            switch (columnIndex) {
            case 0:
                return "";
            case 1:
                String id = func.getId();
                return (id != null ? id : "");
            case 2:
                String desc = func.getDescription();
                return (desc != null ? desc : "");
            }
            return "";
        }

        /**
         * Returns the foreground color for a given FunctionVO object and the specified column. If
         * ArchE created the data, the color for the corresponding cell is as defined in the project
         * preferences. Otherwise, the color is the default (black).
         * 
         * @param element Object that if correct should be correctly case to a FunctionVO object
         * @param columnIndex zero-based index of the column
         * @return Color object in which the contents are to be displayed for the element in the
         *         given column or or null if the default color is to be used.
         * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
         */
        public Color getForeground(Object element, int columnIndex) {
            return null;
        }

        /**
         * Returns the background color for a given FunctionVO object and the specified column.
         * We're not using different background colors in ArchE, so this method returns null so the
         * cell has default background (white)
         * 
         * @param element Object that if correct should be correctly case to a FunctionVO object
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
     * This is the sorter for the Functions View.
     */
    private class FunctionsSorter extends ViewerSorter {

        final static int ASCENDING         = 1;

        final static int DEFAULT_DIRECTION = 0;

        final static int DESCENDING        = -1;

        final static int ID                = 1;

        final static int DESCRIPTION       = 2;

        private int[]    priorities;

        private int[]    directions;

        int[]            defaultPriorities = new int[columnNames.length];

        int[]            defaultDirections = new int[columnNames.length];

        /**
         * Creates a new sorter.
         */
        public FunctionsSorter() {
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
            if ((e1 instanceof FunctionVO) && (e2 instanceof FunctionVO)) {
                FunctionVO f1 = (FunctionVO) e1;
                FunctionVO f2 = (FunctionVO) e2;
                return compareColumnValue(f1, f2, 0);
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
        private int compareColumnValue(FunctionVO f1, FunctionVO f2, int depth) {
            if (depth >= priorities.length) {
                return 0;
            }
            int columnIndex = priorities[depth];

            // Do not sort the Marker column
            if (columnIndex == 0) {
                return 0;
            }
            int direction = directions[columnIndex];
            int result = 0;

            switch (columnIndex) {
            case ID:
                /* id */
//                result = collator.compare(f1.getId(), f2.getId());
            	try{
	            	double d1 = Double.parseDouble(f1.getId());
	            	double d2 = Double.parseDouble(f2.getId());
	            	result = Double.compare(d1, d2);
	                if (result == 0) {
	                    return compareColumnValue(f1, f2, depth + 1);
	                }
	                return result * direction;
            	} catch(NumberFormatException e){
            		return 0;
            	}
            case DESCRIPTION:
                /* description */
                result = collator.compare(f1.getDescription(), f2.getDescription());
                if (result == 0) {
                    return compareColumnValue(f1, f2, depth + 1);
                }
                return result * direction;
            default:
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
     * This is the filter of the FunctionsView.
     */
    private class FunctionFilter extends ViewerFilter {

        public boolean select(Viewer viewer, Object parent, Object element) {
            // if the element is not an instance of FunctionVO then return
            if (!(element instanceof FunctionVO)) {
                return true;
            }

            String searchString = searchText.getText().trim().toLowerCase();

            if (searchString.equals("")) {
                return true;
            } else {
                String desc = ((FunctionVO) element).getDescription().toLowerCase();
                return desc.indexOf(searchString) == -1 ? false : true;
            }
        }
    }

    /***********************************************************************************************
     * Content provider for the functions view.
     */
    private class FunctionsContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer;

        public FunctionsContentProvider() {
            //System.out.println("[FunctionContextProvider] constructor");
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
            //System.out.println("[FunctionContextProvider] inputChanged");
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
                    if (!rect.contains(pt)) {
                        // open edit dialogbox when not click on the first column
                        editFunctionAction.run();
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

                    // Only when mouse is over the logo column and has a
                    // question
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