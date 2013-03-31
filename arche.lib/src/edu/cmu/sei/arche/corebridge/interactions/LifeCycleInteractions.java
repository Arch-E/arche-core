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

package edu.cmu.sei.arche.corebridge.interactions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

import jess.JessEvent;
import jess.JessException;
import jess.Rete;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.corebridge.listener.CoreListener;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;

/**
 * To undertake all life cycle mangement interactions with the ArchE Core related to a project being
 * loaded (opened), saved, closed (and saved by default) and check pointed (equivalent to a binary
 * save of the entire fact base in the Core) in the user interface.
 * 
 * @author Neel Mullick
 */

public class LifeCycleInteractions extends Interactions {

    /** The path of the ArchE Core main CLP files. */
    private static String engineStartupPath = "core/";

    /** The file that starts up the ArchE Core. */
    private static String engineStartupFile = "LoadArchECore.clp";
    /** The file that finishes up loading the ArchE Core. */
    private static String engineFinishupFile = "FinishLoadingArchECore.clp";

    public static String  baseLocation;
    
    private static CoreListener corelistener = null;

    /**
     * Initializes the Rete object and batch loads the project related file. Also add the listener
     * that listens for events in the ArchE Core of type = JessEvent.FACT
     * 
     * @param pathProject full path of the project
     * @param ui represents the UI layer that is currently displaying the data of the factbase that
     *            will accessed by this facade instance. The reference to the UI is needed further
     *            ahead by the VO objects so that they know what UI to call to flag views to
     *            refresh.
     * @param project The ProjectVO object is the entry point to access all the facts in memory.
     * @param designElementTypes List of design element type definitions
     * @param designRelationTypes List of design relation type definitions
     * @return engineArchE The Rete object that will be maintained locally by the ArchEFacade and
     *         used by other interactions with the ArchE Core.
     * @throws ArchEException in case it's not possible to initialize Jess
     */
    public void startupRete(Rete engineArchE, IPath pathProject, IRefreshableUI ui, ProjectVO project,
            List designElementTypes, List designRelationTypes, List trees) throws ArchEException {
        try {
        	
            // This is needed to load a jdbc driver for hsqldb.      	
	          try {
	              Class.forName("org.hsqldb.jdbcDriver");
	          } catch (java.lang.ClassNotFoundException e) {
	              System.out.println("JDBC Driver not found!");
	          }
            
            // This is needed to use the logging function of xmlBlaster properly.
            // SystemClassLoader is internally used to load log handler "org.xmlBlaster.util.log.XbNotifyHandler".
        	//   (eg.) sun.misc.Launcher$AppClassLoader
            URL newURL = null; 
			try {     	   
				URL url = FileLocator.find(Platform.getBundle("SEI.ArchE.Lib"), new Path("/"), null);
				String installPathName = FileLocator.resolve(url).getPath();
                newURL = new URL("file", "", installPathName + "lib/xmlBlaster/xmlBlaster.jar");
            	ClassPathHacker.addURL(newURL);
			} catch (MalformedURLException e1){
				e1.printStackTrace();
			} catch (IOException e1){
				e1.printStackTrace();
			}                             
           
            // Make the ArchE Jess engine use this ArchE Lib Eclipse plugin's classloader
            // so that, in the Jess context, the engine can use classes in the classpath
            // of the plugin by having the classloader load them.
            // As an example of Jess,
            // (import org.xmlBlaster.util.Global)
            // (bind ?glob (new Global))
            //
            // If Jess library exists outside of this ArchE Lib plugin (i.e. Jess is in EclipseHome\plugins\gov.sandia.jess_7.0.0)
            // the following statement is needed to let Jess know where Jess userfunctions are defined, i.e, core/classes for ArchE Lib project.
            // This is done by changing Jess' default class loader to this plugin's class loader.
            // Also, the plugin's classpath must include the path core/classes by modifying the Runtime section in plugin.xml
            //
            engineArchE.setClassLoader(this.getClass().getClassLoader());
                                    
            //Reinitialize the engine
            engineArchE.clear();

            engineArchE.eval("(defglobal ?*dir* = \"" + baseLocation + engineStartupPath
                    + "\" )");

            // Load the ArchE Core startup file--loads all generic clp files
            engineArchE.eval("(batch (str-cat ?*dir* \"" + engineStartupFile + "\"))");

            // Load the RF-specific clp files of all available RFs.
            for (Enumeration e = project.getActiveRFs().keys(); e.hasMoreElements();) {
                ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
                // Regardless of whether the RF is active or not at the moment,
                // we load its clp files into the core
                String installPathName = rf.getInstallPathName();
                String clpFile = rf.getClpFile();
                if (installPathName != null && clpFile != null) {
                    engineArchE.eval("(defglobal ?*dir* = \"" + installPathName + "\" )");
                    engineArchE.eval("(batch (str-cat ?*dir* \"" + clpFile + "\"))");
                }
            }

            if(corelistener == null){
            	corelistener = new CoreListener(ui, project, designElementTypes,
                        designRelationTypes, trees);
                /* Adding the listener to listen to Facts asserted / retracted in the ArchE Core */
                engineArchE.addJessListener(corelistener);
            }
            else{
            	corelistener.reset(ui, project, designElementTypes,
                        designRelationTypes, trees);
            }

            /* Set the event mask to receive notifications for facts */
            engineArchE.setEventMask(engineArchE.getEventMask() | JessEvent.FACT);

            
            /* Initialize global variables used in Jess */
            engineArchE.eval("(reset)");
            engineArchE.eval("(defglobal ?*dir* = \"" + baseLocation + engineStartupPath + "\" )");            
            engineArchE.eval("(defglobal ?*projectfile* = \"" + pathProject.lastSegment() + "\" )");           

            // Load the ArchE Core startup file--loads all generic clp files
            engineArchE.eval("(batch (str-cat ?*dir* \"" + engineFinishupFile + "\"))");

            // Diconnect the ArchE core from XmlBlaster
            engineArchE.eval("(E_Disconnect)");
            // Connect the ArchE core to XmlBlaster to interact with external reasoning frameworks
            engineArchE.eval("(E_Connect)");            
            // Diable the ArchE core to automatically detect an external reasoning framework
            engineArchE.eval("(E_AutoDetect disable)");
            // Enable the ArchE core to automatically detect an external reasoning framework when it's available
            engineArchE.eval("(E_AutoDetect enable)");           	
            
            //call the method in the superclass to execute the commands in the Core that are common
            // to all interactions with the Core.
           	runCore(engineArchE);
        } catch (JessException je) {
        	je.printStackTrace();
            throw new ArchEException(
                    "It was not possible to initialize ArchE Core for the project "
                            + project.getName() + ".", je);
        }
    }

    /**
     * Shuts down the Rete object and saves the persistent facts to the specified file.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param pathProject full path of the project
     * @param shutDownStatus The integer value which is used to tell Life Cycle interactions the
     *            mode to shut down the Core in. 0 will shut down the core after saving the
     *            persistent fact base. 1 will save the persistent fact base and not shut down the
     *            Core. 2 will shut down the core without saving the persistent fact base.
     */
    public void shutdownRete(Rete engineArchE, IPath pathProject, int shutDownStatus) {
        try {
            switch (shutDownStatus) {
            case 0:
//                persistFactBase(engineArchE, fileProject);
                // Diable the ArchE core to automatically detect an external reasoning framework
                engineArchE.eval("(E_AutoDetect disable)");
                // Diconnect the ArchE core from XmlBlaster
                engineArchE.eval("(E_Disconnect)");
            	
                /* halt the engine */
                engineArchE.halt();
                break;
            case 1:
                persistFactBase(engineArchE, pathProject);
                break;
            case 2:
                // Diable the ArchE core to automatically detect an external reasoning framework
                engineArchE.eval("(E_AutoDetect disable)");
                // Diconnect the ArchE core from XmlBlaster
                engineArchE.eval("(E_Disconnect)");

                /* halt the engine */
                engineArchE.halt();
                break;
            }
        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Call core to save the fact base to a file.
     * 
     * @param engineArchE
     * @param pathProject
     * @throws JessException
     */
    private void persistFactBase(Rete engineArchE, IPath pathProject) throws JessException {    	

    	// set focus to the Planner module in ArchE Core
        engineArchE.eval("(focus Planner)");

        // assert the fact to save the persistent facts in the database
        engineArchE.eval("(assert (MAIN::T_SaveFactBase \"" + pathProject.lastSegment() + "\" ))");

        // allow the engine to run to actually persist the file
        engineArchE.run();
        
    }
    
    /**
     * Call core to export the fact base to a SQL file.
     * 
     * @param engineArchE
     * @param pathProject
     * @param sqlFilePath
     * @throws JessException
     */
    public void exportFactBaseToSQL(Rete engineArchE, IPath pathProject, String sqlFilePath) throws JessException {    	

    	// set focus to the Planner module in ArchE Core
        engineArchE.eval("(focus Planner)");

        // assert the fact to export the persistent facts to a SQL file
        engineArchE.eval("(assert (MAIN::T_ExportFactBase \"" + pathProject.lastSegment() + "\" \"" + sqlFilePath + "\" ))");

        // allow the engine to run to actually persist the file
        engineArchE.run();
        
    }	
    
    /**
     * Call core to export the fact base to a SQL file without running ArchE Engine
     * 
     * @param engineArchE
     * @param pathProject
     * @param sqlFilePath
     * @throws JessException
     */
    public void exportFactBaseToSQLWithoutJessRun(Rete engineArchE, IPath pathProject, String sqlFilePath) throws JessException {    	

    	engineArchE.eval("(ExportFactSetToSQL \"Project\" \"" + pathProject.lastSegment() + "\" \"project\" \"" + sqlFilePath + "\" )");        
    }

	public void restoreFromDB(Rete engineArchE) throws JessException{
    	
    //  (RestoreFactSet "Design" "Architecture1")
	//  engineArchE.eval("(assert (MAIN::T_RestoreFactBase \"Project\" ?*projectfile* TRUE TRUE))");
    	engineArchE.eval("(assert (MAIN::T_RestoreFactBase \"Design\" \"Architecture1\" TRUE TRUE))");
    	
        // setting the focus to the Planer module in the ArchE Core.
    	engineArchE.eval("(focus Planner)");

        // assert the fact to allow the Planner module to make another round of analysis
    	engineArchE.eval("(assert (Planner::T_MakeAnotherRound))");

        // allow the engine to run
    	engineArchE.run();		
	}	

}