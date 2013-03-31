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

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.vo.EvaluatedTacticsVO;
import edu.cmu.sei.arche.vo.EvaluationResultVO;
import edu.cmu.sei.arche.vo.ProjectVO;

/**
 * View for Evaluation Results.
 * 
 * @author Ricardo Vazquez, Hyunwoo Kim
 */

public class EvaluationResultsView extends ViewPart implements IPropertyChangeListener {

   

    /** viewer object used by this view */
    private ColoredCellTableViewer tableViewer;
    
    /** button that changes the view to utility or change **/
    private Button                 button;
    
    /** The table of scenarios */
    private Table                  table;
     
    private final String           SCENARIOS            = "SCENARIOS";
    
    /** Array of column names */
    private String[]               columnNames;

    /** The sorter of scenarios */
    //private EvaluationResultSorter        sorter;

    /** The project VO for this instance of the scenario view */
    private ProjectVO              project;

    /** The HashSet that is to be the input for the Scenarios view */
    HashSet                        scenarios;

    
    /** Arraylist of scenario parts */
   // private ArrayList              scenarioParts;


    /** The parent control for the scenarios view */
    private Composite              parent;

   
    /** Attribute the indicates if the images displayed in the view are utility or change**/
    private boolean isUtility;
    
    /**
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        this.parent = parent;

        //Function to initialize relevant member variables of this class
        initPage();


        // Put the name of the columns in an array. In this view, the column names don't vary,
        // so they can be set once. In views that have parameters, populateColumnNames have to
        // be called each time we don setInputAndRefresh cause the number of cols vary.
        populateColumnNames();
        

        // Create layout
        GridLayout layout = new GridLayout(3, false);
        parent.setLayout(layout);
        
        button = new Button(parent, SWT.PUSH);
        

        button.setText("EvaluationResults");
        button.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event e) {

            	if(isUtility)
            		isUtility = false;
            	else
            		isUtility = true;
            	
                tableViewer.refresh();
                table.redraw();
            }
        });
        
//        // Dynamic search textbox
//        GridData gdSearchText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
//        gdSearchText.widthHint = 200;
        //searchText.setLayoutData(gdSearchText);

        table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        // Table
        GridData gdTable = new GridData(GridData.FILL_BOTH);
        gdTable.horizontalSpan = 3;
        table.setLayoutData(gdTable);

        table.addListener(SWT.PaintItem, new Listener() {
        	public void handleEvent(Event event) {
        		/* center column 1 vertically */
        		if (event.index != 0) {
            		TableItem item = (TableItem)event.item;
            		Image img = item.getImage(event.index);
            		if(img != null){
	            		Rectangle rect = item.getImageBounds(event.index);
	            		TableColumn col = table.getColumn(event.index);
	            		int xOffset = Math.max(0, (col.getWidth()-rect.width)/2);
	            		event.gc.drawImage(img, event.x + xOffset, event.y);
            		}
       			}
        	}
        });     
        
     	table.addListener(SWT.EraseItem, new Listener() {
     		public void handleEvent(Event event) {
        		if (event.index != 0) {
        			event.detail &= ~SWT.FOREGROUND;
        		}
     		}
     	});

        // Create the table Columns
        createTableColumns();

        //create tool tips for the table
        createTableToolTips();

        // Create and setup the TableViewer
        tableViewer = new ColoredCellTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new EvaluationResultsContentProvider());

        refreshTableViewer();
        //tableViewer.addFilter(new ScenarioFilter()); //calls refresh

        createContextMenu();

        ArchEUIPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    }

    /**
     * Function to initialize member variables that will be used by the view
     */
    public void initPage() {
        //Initialize the view in the utility option
    	this.isUtility = true;
    	
    	// If the search Text box is created, then clear the content
        // if (searchText != null) {
        //     searchText.setText("");
        // }

        //      retrieve the ProjectVO from the workbench
        project = ArchEUIPlugin.getDefault().getProjectVo();

    }
    
    
    /**
     * Function to populate the column names
     */
    private void populateColumnNames() {
    	    	
    	EvaluatedTacticsVO [] labels = null;
    	
    	ArrayList names = sortnGetTactics();
    	
    	if(names != null){
    		labels = new EvaluatedTacticsVO[names.size()];
	    	for(int i = 0; i < names.size(); i++){
	    		labels[i] = (EvaluatedTacticsVO)names.get(i);
	    	}
        	//get the number of tactics applied to the project and add one for the first column "Scenarios"
            columnNames = new String[labels.length + 1];
    	}else{
    		columnNames = new String[1];
    	}
        int index = 0;
        
        // Assign the name to the columns
        // The first colum's name always is "Scenarios"
        
        columnNames[index] =  SCENARIOS;
        if(labels != null){
	        for(index = 1; index <= labels.length; index++){
	        	columnNames[index] = "Tactic " + Integer.toString(labels[index - 1].getRelevance());
	        }
        }
    }
    
    /**
     * Function that sumarizes and sorts the tactics used in each scenario
     *  @return tactics, a String array with the sorted name of tactics
     */
    private ArrayList sortnGetTactics(){
    	
    	ArrayList sourceNames = new ArrayList();
    	
    	// review if the project has evaluation results
    	if(project != null && project.getEvaluationResult() != null && !project.getEvaluationResult().isEmpty()){
    		Iterator it = project.getEvaluationResult().iterator();
    		while(it != null && it.hasNext()){
    			EvaluationResultVO scenario = ((EvaluationResultVO)it.next());
    			Object[] arrayEvaluatedTactics = scenario.getTactics();
    			for (int count =0; count < arrayEvaluatedTactics.length; count++){
    				EvaluatedTacticsVO eTactics = (EvaluatedTacticsVO)arrayEvaluatedTactics[count];
	    			if(!this.contains(sourceNames, eTactics)){
	    				sourceNames.add(scenario.getTactics()[count]);
	    			}
    			}
    		}
    		Collections.sort(sourceNames);
    		return sourceNames;
    	}else
    		return null;
    }
    
    private boolean contains(ArrayList origin, Object search){
    	Iterator it = origin.iterator();
    	boolean end = false;
    	while(it!= null && it.hasNext() && !end){
    		Object element = it.next();
    		if(element instanceof EvaluatedTacticsVO && 
    				((EvaluatedTacticsVO) element).getTacticId().equals(((EvaluatedTacticsVO)search).getTacticId())){
    			end = true;
    		}
    	}
    	return end;
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
            TacticsLabelProvider labelProvider = (TacticsLabelProvider) tableViewer
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
            public void widgetSelected(SelectionEvent e){
                // column selected - need to sort
//                int column = table.indexOf((TableColumn) e.widget);
//                if (column == sorter.getTopPriority()) {
//                    sorter.reverseTopPriority();
//                } else {
//                    sorter.setTopPriority(column);
//                }
//                tableViewer.refresh();
            }
        };

        //Clear all columns first;
        for (int i = table.getColumnCount() - 1; i >= 0; i--) {
            table.getColumn(i).dispose();
        }

        int columnIndex = 0;
        
        
        // 1st column with marker
        TableColumn column = new TableColumn(table, SWT.NONE, columnIndex);
        column.setText(columnNames[columnIndex]);
        column.setWidth(300);
        column.setAlignment(SWT.LEFT);
//        column.addSelectionListener(headerListener);
        
        //Logic to assign the name of the tactics in each column
        
    	ArrayList names = sortnGetTactics();
        if(names != null){
	        for(columnIndex = 1; columnIndex < names.size() + 1; columnIndex++){
	            column = new TableColumn(table, SWT.NONE, columnIndex);
	           if(columnNames[columnIndex] == null)
	            {
	        	   columnNames[columnIndex] = "Tactic 0";
	            }
	            column.setText(columnNames[columnIndex]);
	            column.setWidth(100 /*60*/); // column.setWidth(100)? -- Andres
	            column.setAlignment(SWT.CENTER);
	            column.setResizable(false); // column.setResizable(true)? -- Andres
//	            column.addSelectionListener(headerListener);
	        }
        }
    }

    /**
     * This view is being created or refreshed after a facade operation or open/close project
     * operation. This method updates all aspects of the tableViewer that can vary in such cases,
     * including setInput.
     */
    private void refreshTableViewer() {
    	
		tableViewer.setInput(getScenarios());
        
        if (!(tableViewer.getLabelProvider() instanceof TacticsLabelProvider)) {
            // The view is being created

            // just need to instantiante our label provider and set it in table viewer once
            tableViewer.setLabelProvider(new TacticsLabelProvider()); // calls refresh

            // THE setColumnProperties STATEMENT IS INSIDE THIS IF BECAUSE THE COLUMNS DON'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, setColumnProperties IS CALLED EVERYTIME.
            tableViewer.setColumnProperties(columnNames);

            // THE CREATION OF THE SORTER IS INSIDE THIS IF BECAUSE THE SORTER DOESN'T VARY
            // FOR THIS VIEW. IN VIEWS THAT HAVE PARAMS, SORTER HAS TO BE RECREATED EVERYTIME.
            //sorter = new EvaluationResultSorter(); // instantiate and reset sorter
            //tableViewer.setSorter(sorter); // calls refresh because soarter changed
        } else {
            // The view is being refreshed by calling setInputAndRefresh
            //((EvaluationResultSorter) tableViewer.getSorter()).resetState();
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
            scens = project.getEvaluationResult();
        }
        if (scens == null) {
            scens = new HashSet();
            scens.add("Please open or create a project.");
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
    	
    	boolean tempUtility = this.isUtility;
        initPage();
        this.isUtility = tempUtility;
        
        populateColumnNames();
        //This code is used to display the scenarios and the tactics in the result view
        if(project != null ){
	        Iterator it = this.project.getEvaluationResult().iterator();
	    	while(it != null && it.hasNext()){
	    		EvaluationResultVO vo = (EvaluationResultVO) it.next();
	    		Object[]enums = vo.getTactics();
	    		for( int i = 0; i < enums.length;i++){
	    			EvaluatedTacticsVO tactic = (EvaluatedTacticsVO)enums[i];
//	    			System.out.println(vo.getScenarioId() + " || "  + vo.getScenarioName() + "|| Tactic ID: " + tactic.getTacticId() + " ||relevnce: " + tactic.getRelevance() + "Utility :" + tactic.getUtility());
	    		}
	    	}
        }
        createTableColumns();
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
    class TacticsLabelProvider extends LabelProvider implements ITableLabelProvider,
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
         * @param element Object that if correct should be correctly case to a EvaluatedTacticsVO object
         * @param columnIndex zero-based index of the column
         * @return Image object to be displayed in the given column or null if the column does not
         *         show icons.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            
        	// If project is not open
            if (!(element instanceof EvaluationResultVO)) {
                return columnIndex == 0 ? (Image) MarkerUtil.getImage("warning") : null;
            }

            // If is the first column -Marker
            if (columnIndex == 0) {
            	EvaluationResultVO e = (EvaluationResultVO) element;
               
                boolean hasProblem = false;
                
                // subsequent versions).
                if (hasProblem) {
                    return (Image) MarkerUtil.getImage("problem");
                }  
                TableItem[] children = table.getItems();
                for (int j = 0; j < children.length; j++) {
                    TableItem item = children[j];
                    Object data = item.getData();
                    if (data == element) {
                    	
                        item.setText(e.getScenarioName());
                    }
                }
            }

            if (columnIndex >= 1 ) {
            	EvaluationResultVO e = (EvaluationResultVO) element;
            	EvaluatedTacticsVO[] tactics = new EvaluatedTacticsVO[e.getTactics().length];
            	Object [] oTactics = e.getTactics();
            	
            	if(oTactics!= null){
            		for(int count=0; count < oTactics.length; count++){
            			tactics[count] = (EvaluatedTacticsVO)oTactics[count];
            		}
            	}
            	
            	//Assign the image that will be displayed in the cell, if  
            	// isUtility then it will display the utility of the tectic in the scenari
            	// otherwhise it'll get the image of the change applied in the tactic
        		int relevance = Integer.parseInt(columnNames[columnIndex].replaceFirst("Tactic ",""));           	
            	for(int count = 0; count < tactics.length; count++){            		
            		if( relevance == tactics[count].getRelevance()){
            			if(isUtility){
    	                	if(tactics[count] != null){
    		                	double colorNum = (tactics[count].getUtility());
    		                	ColorSeeker color = new ColorSeeker(colorNum);
    		                	return (Image)MarkerUtil.getImage(color.getColorUtility());
    	                	}	
    	                }else{
    	                	if(tactics[count] != null){
    		                	double colorNum = (tactics[count].getChange());
    		                	ColorSeeker color = new ColorSeeker(colorNum);
    		                	return (Image)MarkerUtil.getImage(color.getColorChange());
    	                	}
    	                }
            		}
            	}
                return (Image)MarkerUtil.getImage("info"); 

                
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
            String name = "";
        	if (element instanceof EvaluationResultVO && columnIndex == 0) {
            	EvaluationResultVO scen = (EvaluationResultVO) element;
                
            	return (scen.getScenarioName() != null ? scen.getScenarioName() : "");

            } else {
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
        
        private class ColorSeeker{
        	private double value;
        	public ColorSeeker(){
        		this.value = 0;
        	}
        	public ColorSeeker(double in_Value){
        		value = in_Value;
        	}
        	
        	/**
        	 * determines the color to be displayed in the view when the 
        	 * option to review is Utility
        	 * @return color to be displyed in the view
        	 */
        	public String getColorUtility(){
        		if(value >= 1 ){
        			return "satisfied";
        		} else if( value <= 0 ){
        			return "notsatisfied";
        		} else {	
        			return "partiallysatisfied";
        		}
        	}
        	/**
        	 * determines the color to be displayed in the view when the 
        	 * option to review is Improved
        	 * @return color to be displyed in the view
        	 */
        	public String getColorChange(){
        		if(value <= -1 ){
        			return "satisfiedChange";
        		} else if( value > -1 && value <= 0){
        			return "partiallysatisfiedChange";
        		} else if(value > 0){	
        			return "notsatisfiedChange";
        		}
        		return "notsatisfiedChange";
        	}
        }
    }
  
    /**
     * Content provider for the scenarios table view.
     * 
     * @author Neel Mullick
     */
    class EvaluationResultsContentProvider implements IStructuredContentProvider {

        /** associated viewer */
        private TableViewer viewer; 

        public EvaluationResultsContentProvider() {
//            System.out.println("[EvaluationResultsContentProvider] constructor");
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

                //case SWT.MouseDoubleClick:
                		 

                case SWT.MouseHover:

                	Point pt = new Point(event.x, event.y);
                	TableItem item = table.getItem(pt);
                	 
                      // Ensure the item is not null
                      if (item == null) {
                          break;
                      }
                  
                    String tipTextAnalysis = "";
                    // Only when mouse is over the tactics values
                    if(columnNames.length > 1){
	                    for( int count = 1; count < columnNames.length; count++){
	                    	Rectangle rectAnalysis = item.getBounds(count);
		                   
		                    if (rectAnalysis.contains(pt)) {
		                    	Object  evaluatedResult = item.getData();
		                    	if(evaluatedResult != null){
		                    		Hashtable tacticsTable = ((EvaluationResultVO)evaluatedResult).getTactics(true);
		                    		ArrayList sortedTactics = new ArrayList();
		                    		for(Enumeration enums = tacticsTable.elements();enums.hasMoreElements();){
		                    			sortedTactics.add(enums.nextElement());
		                    		}
		                    		Collections.sort(sortedTactics);
		                    		Object [] sortedTacticsArray = new EvaluatedTacticsVO[sortedTactics.size()];
		                    		sortedTacticsArray = sortedTactics.toArray();
		                    		// different tool tip information for utility and
		                    		// change.
	                    			for(int tacPos = 0; tacPos < sortedTacticsArray.length; tacPos++){
	                    				if(isUtility){
	                    					if( columnNames[count].indexOf(Integer.toString(((EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getRelevance()))!=-1){
				                    			tipTextAnalysis = "\n Tactic: " + ( (EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getTacticDesc();
				                    			tipTextAnalysis = tipTextAnalysis  + "\n" + " Utility: "
			                                    + (Double.toString( ((EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getUtility()));
				                    		}	
			                    		}else{
	                    					if( columnNames[count].indexOf(Integer.toString(((EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getRelevance()))!=-1){
				                    			tipTextAnalysis = "\n Tactic: " + ((EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getTacticDesc();
				                    			tipTextAnalysis = tipTextAnalysis  + "\n" + " Change: "
			                                    + (Double.toString( ((EvaluatedTacticsVO)sortedTacticsArray[tacPos]).getChange())); // + "\n Current Measure: ";
				                    			
				                    		}
			                    		}
	                    			}
			                    		
		                    		if(tipTextAnalysis.equals("")){	
		                    			tipTextAnalysis = "No tactic applied to the scenario";
		                    		}
		                    	}
		                    	
		                    	if (tip != null && !tip.isDisposed()) {
		                            tip.dispose();
		                        }
		                    	
		                        tip = new Shell(shell, SWT.ON_TOP);
		                        tip.setLayout(new FillLayout());
		                        label = new Label(tip, SWT.WRAP);
		                        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		                        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		                        label.setData("_TABLEITEM", item);
			                    label.setText(" " + tipTextAnalysis);
			                    label.setSize(400, 55);
			                    label.addListener(SWT.MouseExit, labelListener);
			                    label.addListener(SWT.MouseDown, labelListener);
			                    
			                    Point size = tip.computeSize(400, 55);

			                    pt = table.toDisplay(pt.x + 15, pt.y + 10);
			                    tip.setBounds(pt.x, pt.y, size.x, size.y);
			                    tip.setVisible(true);
		                    }
	                	}
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
