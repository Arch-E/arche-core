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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.graphics.Image;

import edu.cmu.sei.arche.ArchEUIPlugin;

/**
 * @author Henry Chen
 */
public class MarkerUtil {

    public final static int      PIXELS_OF_CHAR = 8;

    private static Map           imageDescriptors;

    private static ImageRegistry imageRegistry  = new ImageRegistry();

    static {
        createImageDescriptors();
    }

    /**
     * Creates an image descriptor for the image file referred to by the given relative path
     * (relative to the icons directory for the plug-in).
     */
    static ImageDescriptor createImageDescriptor(String relativePath) {
        String iconPath = "icons/full/";
        try {
            URL URL_BASIC = ArchEUIPlugin.getDefault().getDescriptor().getInstallURL();
            URL url = new URL(URL_BASIC, iconPath + relativePath);
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
            Assert.isTrue(false);
            return null;
        }
    }

    /**
     * Creates the map of image descriptors.
     */
    private static void createImageDescriptors() {

        String OBJ = "";

        imageDescriptors = new HashMap(20);
        imageDescriptors.put("error", createImageDescriptor(OBJ + "error.gif"));
        imageDescriptors.put("warning", createImageDescriptor(OBJ + "warning.gif"));
        imageDescriptors.put("info", createImageDescriptor(OBJ + "info.gif"));

        imageDescriptors.put("hprio", createImageDescriptor(OBJ + "hprio.gif"));
        imageDescriptors.put("notanalyzed", createImageDescriptor(OBJ + "notanalyzed.gif"));
        imageDescriptors.put("partiallysatisfied", createImageDescriptor(OBJ
                + "partiallysatisfied.gif"));
        imageDescriptors.put("satisfied", createImageDescriptor(OBJ + "satisfied.gif"));
        imageDescriptors.put("notsatisfied", createImageDescriptor(OBJ + "notsatisfied.gif"));

        //Question and problem
        imageDescriptors.put("question", createImageDescriptor(OBJ + "question.gif"));
        imageDescriptors.put("problem", createImageDescriptor(OBJ + "problem.gif"));
        
        
        //View of EvaluatedREsults
        imageDescriptors.put("partiallysatisfiedChange", createImageDescriptor(OBJ
                + "trianglepartiallysatisfied.gif"));
        imageDescriptors.put("satisfiedChange", createImageDescriptor(OBJ + "trianglesatisfied.gif"));
        imageDescriptors.put("notsatisfiedChange", createImageDescriptor(OBJ + "trianglenotsatisfied.gif"));

    }

    /**
     * Returns the image that should be used to visually represent the marker, based on its type and
     * priority.
     */
    public static Image getImage(IMarker marker) {
        if (isMarkerType(marker, IMarker.PROBLEM)) {
            switch (getSeverity(marker)) {
            case IMarker.SEVERITY_ERROR:
                return getImage("error");
            case IMarker.SEVERITY_WARNING:
                return getImage("warn");
            case IMarker.SEVERITY_INFO:
                return getImage("info");
            }
        } else if (isMarkerType(marker, IMarker.TASK)) {
            return getImage("task");
        }
        return null;
    }

    /**
     * Returns whether the given marker is of the given type (either directly or indirectly).
     */
    public static boolean isMarkerType(IMarker marker, String type) {
        try {
            return marker.isSubtypeOf(type);
        } catch (CoreException e) {
            return false;
        }
    }

    /**
     * Returns the image with the given key, or <code>null</code> if not found.
     */
    public static Image getImage(String key) {
        Image image = (Image) imageRegistry.get(key);
        if (image == null) {
            ImageDescriptor desc = getImageDescriptor(key);
            if (desc != null) {
                image = desc.createImage(false);
                if (image == null) {
                    System.err.println("MarkerUtil: Error creating image for " + key);
                }
                imageRegistry.put(key, image);
            }
        }
        return image;
    }

    /**
     * Returns the image descriptor with the given key, or <code>null</code> if not found.
     */
    private static ImageDescriptor getImageDescriptor(String key) {
        ImageDescriptor desc = (ImageDescriptor) imageDescriptors.get(key);
        if (desc == null) {
            System.err.println("MarkerUtil: No image descriptor for " + key);
        }
        return desc;
    }

    /**
     * Returns the severity of the given marker. Default is SEVERITY_WARNING.
     */
    public static int getSeverity(IMarker marker) {
        return marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
    }

}