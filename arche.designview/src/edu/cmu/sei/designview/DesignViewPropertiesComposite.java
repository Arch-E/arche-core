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

package edu.cmu.sei.designview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.objectstyle.cayenne.access.DataContext;
import org.objectstyle.cayenne.query.SQLTemplate;

import edu.cmu.sei.ORM.model.DesignModule;
import edu.cmu.sei.ORM.model.Versions;
import edu.cmu.sei.designview.layouts.DependencyViewLayout;
import edu.cmu.sei.designview.layouts.ViewLayout;

/**
 * Implements the GUI layout for the user to set up the VersionName 
 * and RootFactId. It creats widgets such as Text and Button and 
 * arrange them to create a layout for the user. Also it implements
 * event listners for user-initiated events, say, editing text.
 * 
 * @author Hyunwoo Kim
 */
public class DesignViewPropertiesComposite extends Composite {

    /**
     * The parent page that includes this composite
     */		
	private DesignViewPropertiesPage parentPage;
	
	/** Data context for ArchE database */
	private static DataContext context; 	
		
    /**
     * constants
     */		
    private static final int SIZING_TEXT_FIELD_WIDTH = 50;

	private Label versionNameLabel = null;

	private Combo versionNameCombo = null;

	private Label rootFactIdLabel = null;

	private Combo rootFactIdCombo = null;
	
    /**
     * The lestener to handle the modify event
     */			
	private ModifyListener fieldModifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			if(e.getSource().equals(versionNameCombo))			
				computeRootFactIdCombo();
            boolean valid = parentPage.validatePage();
            parentPage.setPageComplete(valid);
        }
    };
    
    /**
     * The adapter to handle the selection event
     */			
	private SelectionAdapter radioSelectionAdapter = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			if(e.getSource().equals(dependencyRadioButton))
				dependencyCombo.setEnabled(true);
			else
				dependencyCombo.setEnabled(false);
            boolean valid = parentPage.validatePage();
            parentPage.setPageComplete(valid);
        }
    };

	private Group viewsGroup = null;

	private Label decompositionLabel = null;

	private Button decompositionRadioButton = null;
	
	private Label dependencyLabel = null;

	private Button dependencyRadioButton = null;
	
	private Combo dependencyCombo = null;

	private Label concurrencyLabel = null;

	private Button concurrencyRadioButton = null;
	
	
	public DesignViewPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		context = DataContext.createDataContext();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new org.eclipse.swt.graphics.Point(405,361));
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 15;
		gridLayout.numColumns = 3; 
		this.setLayout(gridLayout);
		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Font font = this.getFont();		        
        versionNameLabel = new Label(this, SWT.NONE);
        versionNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        versionNameLabel.setFont(font);                        
        versionNameLabel.setText("Version Name: ");

        
        versionNameCombo = new Combo(this, SWT.READ_ONLY);
        GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);		
        gridData1.widthHint = SIZING_TEXT_FIELD_WIDTH;        
        gridData1.horizontalSpan = 2;        
        versionNameCombo.setLayoutData(gridData1);              
        versionNameCombo.setFont(font);                
        initializeVersionNameCombo();
        versionNameCombo.addModifyListener(fieldModifyListener);
        
//        versionNameField = new Text(this, SWT.BORDER);
//        GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);		
//        gridData1.widthHint = SIZING_TEXT_FIELD_WIDTH;        
//        gridData1.horizontalSpan = 2;        
//        versionNameField.setLayoutData(gridData1);              
//        versionNameField.setFont(font);                
//        versionNameField.addModifyListener(fieldModifyListener);

        rootFactIdLabel = new Label(this, SWT.NONE);
        rootFactIdLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        rootFactIdLabel.setFont(font);                        
        rootFactIdLabel.setText("Root Fact ID: ");

        rootFactIdCombo = new Combo(this, SWT.READ_ONLY);
        GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);		
        gridData2.widthHint = SIZING_TEXT_FIELD_WIDTH;        
        gridData2.horizontalSpan = 2;        
        rootFactIdCombo.setLayoutData(gridData2);              
        rootFactIdCombo.setFont(font);                
        rootFactIdCombo.addModifyListener(fieldModifyListener);
        
//        rootFactIdField = new Text(this, SWT.BORDER);
//        GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);		
//        gridData2.widthHint = SIZING_TEXT_FIELD_WIDTH;        
//        gridData2.horizontalSpan = 2;        
//        rootFactIdField.setLayoutData(gridData2);              
//        rootFactIdField.setFont(font);                
//        rootFactIdField.addModifyListener(fieldModifyListener);
        
        createViewsGroup();        
	}


	private void initializeVersionNameCombo() {
		SQLTemplate versionQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("VersionNamesQuery");
		List result= (List)context.performQuery(versionQuery);
		
		for(int i=0; i<result.size(); i++ ){
			Versions versionData = (Versions)result.get(i);	
			versionNameCombo.add(versionData.getVersionName(),i);			
		}		
	}

	private void computeRootFactIdCombo() {
		SQLTemplate moduleQuery = (SQLTemplate) context.getEntityResolver().lookupQuery("ModuleFactIDsQuery");
		Map params = new HashMap();
		String versionNameParam = "'" + versionNameCombo.getItem(versionNameCombo.getSelectionIndex()) + "'";
		params.put("VersionName",versionNameParam);
		SQLTemplate parameterizedModuleQuery = moduleQuery.queryWithParameters(params);

		List result= (List)context.performQuery(parameterizedModuleQuery);

		rootFactIdCombo.removeAll(); 
		for(int i=0; i<result.size(); i++ ){
			DesignModule dmData = (DesignModule)result.get(i);	
			rootFactIdCombo.add(dmData.getFactid(),i);			
		}				
	}

	public void setPage(DesignViewPropertiesPage page) {
		this.parentPage = page;
	}

	public String getVersionName() {
		if(versionNameCombo.getSelectionIndex() != -1)
			return versionNameCombo.getItem(versionNameCombo.getSelectionIndex()).trim();
		return "";
//		return versionNameField.getText().trim();
	}

	public String getRootFactId() {
		if(rootFactIdCombo.getSelectionIndex() != -1)
			return rootFactIdCombo.getItem(rootFactIdCombo.getSelectionIndex()).trim();
		return "";
//		return rootFactIdField.getText().trim();
	}

	public boolean isViewSelected() {
		return decompositionRadioButton.getSelection() 
				|| dependencyRadioButton.getSelection() 
				|| concurrencyRadioButton.getSelection(); 
	}


	/**
	 * This method initializes viewsGroup	
	 *
	 */
	private void createViewsGroup() {
		Font font = this.getFont();		        
		viewsGroup = new Group(this, SWT.NONE);
		viewsGroup.setFont(font);
		viewsGroup.setText("Architectural View");
        final GridLayout gridLayout = new GridLayout();        
        gridLayout.numColumns = 3;
        viewsGroup.setLayout(gridLayout);		
        final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        viewsGroup.setLayoutData(gridData);
        
        decompositionLabel = new Label(viewsGroup, SWT.NONE);
        decompositionLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        decompositionLabel.setFont(font);                        
        decompositionLabel.setText("\t\tDecomposition View ");        
        decompositionRadioButton = new Button(viewsGroup, SWT.RADIO);
        decompositionRadioButton.addSelectionListener(radioSelectionAdapter);
        Label emptyLabel1 = new Label(viewsGroup, SWT.NONE);  // placeholder
        
        dependencyLabel = new Label(viewsGroup, SWT.NONE);
        dependencyLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        dependencyLabel.setFont(font);                        
        dependencyLabel.setText("\t\tDependency View ");
        dependencyRadioButton = new Button(viewsGroup, SWT.RADIO);
        dependencyRadioButton.addSelectionListener(radioSelectionAdapter);
        dependencyCombo = new Combo(viewsGroup, SWT.READ_ONLY);
        dependencyCombo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        dependencyCombo.setFont(font);                        
        dependencyCombo.setVisibleItemCount(3);
        dependencyCombo.setToolTipText(" Algorithms for graph layout");
        dependencyCombo.add(DependencyViewLayout.FR_LAYOUT,0);
        dependencyCombo.add(DependencyViewLayout.KK_LAYOUT,1);
        dependencyCombo.add(DependencyViewLayout.CIRCLE_LAYOUT,2);
        dependencyCombo.select(0);
        dependencyCombo.setEnabled(false);
        
        concurrencyLabel = new Label(viewsGroup, SWT.NONE);
        concurrencyLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));        
        concurrencyLabel.setFont(font);                        
        concurrencyLabel.setText("\t\tConcurrency View ");
        concurrencyRadioButton = new Button(viewsGroup, SWT.RADIO);        
        concurrencyRadioButton.addSelectionListener(radioSelectionAdapter);
        concurrencyRadioButton.setEnabled(false);
        Label emptyLabel2 = new Label(viewsGroup, SWT.NONE); // placeholder
	}



	public String getSelectedViewType() {
		if(decompositionRadioButton.getSelection())
			return ViewLayout.DECOMPOSITION_VIEW;
		else if(dependencyRadioButton.getSelection())
			return ViewLayout.DEPENDENCY_VIEW;
		else if(concurrencyRadioButton.getSelection())
			return ViewLayout.CONCURRENCY_VIEW;
		return null;
	}



	public String getSelectedLayoutType() {
		if (dependencyCombo.getSelectionIndex() != -1)
			return dependencyCombo.getItem(dependencyCombo.getSelectionIndex());
		return "";
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
