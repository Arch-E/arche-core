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

package edu.cmu.sei.arche;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.xml.sax.SAXException;

import edu.cmu.sei.arche.rfconfig.ConfigReader;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;

/**
 * Locate all RF plugins and load them.
 * 
 * @author Paulo Merson
 */
public class RFLoader {

    /**
     * Call the RF config reader for each RF plugin that is available. The set of available RF data
     * is then stored in availableRFs
     * 
     * @param store preference store that indicates if each RF is active or not according to user
     *            preferences. If null, RFs will be set as active in the hashtable.
     * @return Hashtable (ReasoningFrameworkVO --> Boolean) with all RFs loaded from all RF plugins
     *         found, The value is true if that RF is active (user preference)
     */
    public static Hashtable loadReasoningFrameworks(IPreferenceStore store) throws SAXException,
            IOException, ArchEException {
        Hashtable availableRFs = new Hashtable();
        IPluginRegistry registry = Platform.getPluginRegistry();
        IExtensionPoint rfExtensionPoint = registry
                .getExtensionPoint("SEI.ArchE.UI.reasoningFramework");
        IExtension[] extensions = rfExtensionPoint.getExtensions();
        ArrayList results = new ArrayList();
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            IPluginDescriptor rfPlugin = extensions[i].getDeclaringPluginDescriptor();
            URL url = rfPlugin.getInstallURL();
            String installPathName = Platform.resolve(url).getPath();
            ReasoningFrameworkVO rfVO = null;
            String clpFile = null;
            for (int j = 0; j < elements.length; j++) {
                if (elements[j].getName().equals("rfconfigFile")) {
                    String fileName = installPathName + elements[j].getAttribute("fileName");
                    //System.out.println("[RFLoader] rf config filename: " + fileName);
                    rfVO = ConfigReader.parse(fileName);
                    Boolean active = new Boolean(true);
                    if (store != null) {
                        if (store.contains(rfVO.getId())) {
                            active = new Boolean(store.getBoolean(rfVO.getId()));
                        } else {
                            store.setDefault(rfVO.getId(), true);
                        }
                    }
                    availableRFs.put(rfVO, active);
                } else if (elements[j].getName().equals("clpFile")) {
                    clpFile = elements[j].getAttribute("fileName");
                    //System.out.println("[RFLoader] clpFile: " + clpFile);
                }
            }
            if (rfVO == null) {
                throw new IllegalStateException("The RF configuration for "
                        + rfPlugin.getUniqueIdentifier() + " could not be loaded.");
            }
            rfVO.setInstallPathName(installPathName);
            rfVO.setClpFile(clpFile);
        }
        return availableRFs;
    }

}