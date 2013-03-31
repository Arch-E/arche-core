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

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 *
 * @author Hyunwoo Kim
 */
public class DesignViewPlugin extends AbstractUIPlugin {

	//The shared instance.
	private static DesignViewPlugin plugin;

    /**
     * The plugin ID.
     */
    public static final String ARCHE_DESIGNVIEW = "SEI.ArchE.DesignView"; 
    public static final int ROOT_FACT_TYPE_MODULE = 0;
    public static final int ROOT_FACT_TYPE_MODULE_INTERFACE = 1;    
    
	
	/**
	 * The constructor.
	 */
	public DesignViewPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static DesignViewPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("GuiViews", path);
	}	
	
    /**
     * Logs the given throwable to the platform log, indicating the class and
     * method from where it is being logged (this is not necessarily where it
     * occurred).
     * 
     * This convenience method is for internal use by the IDE Workbench only and
     * must not be called outside the IDE Workbench.
     * 
     * @param clazz
     *            The calling class.
     * @param methodName
     *            The calling method name.
     * @param t
     *            The throwable from where the problem actually occurred.
     */
    public static void log(Class clazz, String methodName, Throwable t) {
        String msg = MessageFormat.format("Exception in {0}.{1}: {2}", //$NON-NLS-1$
                new Object[] { clazz.getName(), methodName, t });
        log(msg, t);
    }
    
    /**
     * Logs the given message and throwable to the platform log.
     * 
     * If you have a status object in hand call log(String, IStatus) instead.
     * 
     * This convenience method is for internal use by the IDE Workbench only and
     * must not be called outside the IDE Workbench.
     * 
     * @param message
     *            A high level UI message describing when the problem happened.
     * @param t
     *            The throwable from where the problem actually occurred.
     */
    public static void log(String message, Throwable t) {
        IStatus status = newStatus(IStatus.ERROR, message, t);
        log(message, status);
    }    

    /**
     * Logs the given message and status to the platform log.
     * 
     * This convenience method is for internal use by the IDE Workbench only and
     * must not be called outside the IDE Workbench.
     * 
     * @param message
     *            A high level UI message describing when the problem happened.
     *            May be <code>null</code>.
     * @param status
     *            The status describing the problem. Must not be null.
     */
    public static void log(String message, IStatus status) {

        //1FTUHE0: ITPCORE:ALL - API - Status & logging - loss of semantic info

        if (message != null) {
            getDefault().getLog().log(
                    newStatus(IStatus.ERROR, message, null));
        }

        getDefault().getLog().log(status);
    }
    
    /**
     * This method must not be called outside the workbench.
     *
     * Utility method for creating status.
     */
    public static IStatus newStatus(int severity, String message,
            Throwable exception) {

        String statusMessage = message;
        if (message == null || message.trim().length() == 0) {
            if (exception.getMessage() == null) {
				statusMessage = exception.toString();
			} else {
				statusMessage = exception.getMessage();
			}
        }

        return new Status(severity, DesignViewPlugin.ARCHE_DESIGNVIEW, severity,
                statusMessage, exception);
    }

}
