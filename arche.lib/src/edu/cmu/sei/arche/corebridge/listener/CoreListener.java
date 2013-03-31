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

package edu.cmu.sei.arche.corebridge.listener;

import java.util.List;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.vo.ProjectVO;
import jess.Fact;
import jess.Funcall;
import jess.JessEvent;
import jess.JessException;
import jess.JessListener;
import jess.Rete;
import jess.Userfunction;

/**
 * The CoreListener implements the JessListener interface and allows the Core Bridge to receive
 * notifications of events of interest in the ArchE Core.
 * 
 * @author Neel Mullick
 */

public class CoreListener implements JessListener {

    /** Constant to indicate that a FACT was Asserted. */
    public static final int ASSERT  = 0;

    /** Constant to indicate that a FACT was Modified. */
    public static final int MODIFY  = 1;

    /** Constant to indicate that a FACT was Retracted. */
    public static final int RETRACT = 2;

    /** There's one CoreListener instance per project and one associated VOUpdate instance. */
    private VOUpdate        voUpdate;
    
    /**
     * @param ui represents the UI layer that is currently displaying the data of the factbase that
     *            this object is listening to. The reference to the UI is needed further ahead by
     *            the VO objects so that they know what UI to call to flag views to refresh.
     * @param project The ProjectVO object is the entry point to access all the facts in memory.
     *            VOUpdate will later on need this reference so that when a new fact is
     *            assertedasserted/modified/retracted, we can add/change/remove the corresponding VO
     *            to this project.
     * @param designElementTypes List of design element type definitions
     * @param designRelationTypes List of design relation type definitions
     */
    public CoreListener(IRefreshableUI ui, ProjectVO project, List designElementTypes,
            List designRelationTypes, List trees) {
        this.voUpdate = new VOUpdate(ui, project, designElementTypes, designRelationTypes, trees);
    }
    
    public void reset(IRefreshableUI ui,ProjectVO project, List designElementTypes,
            List designRelationTypes, List trees){
    	this.voUpdate.setRefreshableUI(ui);
    	this.voUpdate.setProjectVO(project);
    	this.voUpdate.reset(designElementTypes,designRelationTypes,trees);
    	
    }
    /**
     * Receives event notification from the ArchE Core.
     * 
     * @param jEvent The JessEvent that occured in the ArchE Core.
     * @see jess.JessListener#eventHappened(jess.JessEvent)
     * @throws je JessException
     */
    public void eventHappened(JessEvent jEvent) throws JessException {

        /* The type of the JessEvent */
        int type = jEvent.getType();

        // The local Rete object used to get the GlobalContext required to manipulate the jess value
        // object with which the event was associated.
        Rete engineArchE = null;

        try {
            /* Retrieve the Rete object for which the event notification was received. */
            engineArchE = (Rete) jEvent.getSource();

            switch (type) {
            case JessEvent.FACT:
                /* Passing the Fact object to update the locally maintained value objects */
                voUpdate.parseFactAndUpdateVO((Fact) jEvent.getObject(), engineArchE
                        .getGlobalContext(), ASSERT);
                break;

            case JessEvent.FACT | JessEvent.REMOVED:
                /* Passing the Fact object to update the locally maintained value objects */
                voUpdate.parseFactAndUpdateVO((Fact) jEvent.getObject(), engineArchE
                        .getGlobalContext(), RETRACT);
                break;

            case JessEvent.FACT | JessEvent.MODIFIED:
                /* Passing the Fact object to update the locally maintained value objects */
//            	Fact tf = (Fact) jEvent.getObject();
//            	System.out.println("Modified Fact: " + tf.toString());
                voUpdate.parseFactAndUpdateVO((Fact) jEvent.getObject(), engineArchE
                        .getGlobalContext(), MODIFY);
                break;
//            case JessEvent.USERFUNCTION_RETURNED:
//            	String tmp = jEvent.toString();
//            	Funcall fc = (Funcall)jEvent.getObject();        
//            	Userfunction uf = fc.getUserfunction(engineArchE);
//            	String name = uf.getName();
//            	int i=0;
//                break;
            }
        } catch (JessException je) {
            throw je;
        }
    }

}