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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Provides a WizardPage for the user to create a architectural view by setting up
 * the VersionName and RootFactId.
 * 
 * @author Hyunwoo Kim
 */
public class DesignViewPropertiesPage extends WizardPage {

    /**
     * The container or control comprising the actual layout of this page.
     */	
	private DesignViewPropertiesComposite container;	
    
    /**
     * Creates this wizard page
     */	
	public DesignViewPropertiesPage(String pageName) {
		super(pageName);		
		setTitle("AView Properties");
		setDescription("The version name and root factid must be specified.");		
        setPageComplete(false);
	}	

	/**
     * Creates a control for this page using ProjectPropertiesComposite class
     */
	public void createControl(Composite parent) {

		container = new DesignViewPropertiesComposite(parent, SWT.NULL);
		container.setPage(this);
        container.setFont(parent.getFont());
        initializeDialogUnits(parent);
		
		setControl(container);
				
        setPageComplete(validatePage());
        
        // Show description on opening
        setErrorMessage(null);
        setMessage(null);
	}
	
    /**
     * Returns whether this page's controls currently all contain valid 
     * values.
     *
     * @return <code>true</code> if all controls are valid, and
     *   <code>false</code> if at least one is invalid
     */
    protected boolean validatePage() {
        
        String versionName = container.getVersionName();
        if (versionName.equals("")) { 
    		setErrorMessage(null);
            setMessage("The version name must be specified.");
            return false;
        }

        String rootFactId = container.getRootFactId();

        if (rootFactId.equals("")) { 
            setErrorMessage(null);
            setMessage("The root factid must be specified.");
            return false;
        }
        
        if (container.isViewSelected() == false) {
            setErrorMessage(null);
            setMessage("One architectural view must be selected.");
            return false;    
        }
        
        setErrorMessage(null);
        setMessage(null);
        return true;
    }	
    
    public String getVersionName(){
    	return container.getVersionName();
    }
	
    public String getRootFactId(){
    	return container.getRootFactId();    	
    }

    public String getSelectedViewType(){
    	return container.getSelectedViewType();    	
    }
    
    public String getSelectedLayoutType(){
    	return container.getSelectedLayoutType();    	
    }
}
