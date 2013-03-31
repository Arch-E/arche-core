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

package edu.cmu.sei.arche.export;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.export.ExportDesign;

/**
 * ExportToRose Represents the design created by ArchE in semantics (like UML) using XMI that is
 * readable by the Rational Rose
 * 
 * @author Myung-joo Ko
 */
public class ExportToRose implements ExportDesign {

    private Document    doc;

    private Element     componentView;

    private Element     componentViewPackage;

    private Element     logicalView;

    private Element     logicalViewPackage;

    private Element     xmi;

    private String      fileNameForUML;

    private String      FILENAMEFORXML  = "ArchEFacts.xml";

    private String      FILENAMEFORXSLT = "ArchEDesignToUML.xsl";

    private String      path;

    /** The ArchEFacade Object for the active project */
    private ArchEFacade archeFacade;

    /**
     * Constructor
     */
    public ExportToRose(ArchEFacade archEFacade) {

        super();
        this.archeFacade = archEFacade;
    }

    /**
     * Create XMI file for exporting design of ArchE, which will be read by the Rational Rose
     * 
     * @param fileName
     * @see edu.cmu.sei.arche.export.ExportDesign#createArchEUML(java.lang.String)
     */
    public void createArchEUML(String fileName) {
        boolean isSuccessful = false;
        this.fileNameForUML = fileName;

        createFactsXML();
        translateToUML();
    }

    /**
     * Creates XML file which contains all the data from facts in the Core
     * 
     * @see edu.cmu.sei.arche.export.ExportDesign#readArchEDesign()
     */
    public void createFactsXML() {
        archeFacade.writeFacts(FILENAMEFORXML);
    }

    /**
     * Performs an XSLT transformation, sending the results to XMI file.
     * 
     * @see edu.cmu.sei.arche.export.ExportDesign#translateToUML()
     */
    public void translateToUML() {

        String path = archeFacade.getBaseLocation();
        File xmlFile = new File(path + FILENAMEFORXML);
        File xsltFile = new File(path + FILENAMEFORXSLT);

        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

        // the factory pattern supports different XSLT processors
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer trans = null;
        try {
            trans = transFact.newTransformer(xsltSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        try {
            trans.transform(xmlSource, new StreamResult(new File(fileNameForUML)));
        } catch (TransformerException e1) {
            e1.printStackTrace();
            throw new RuntimeException(e1.getMessage(), e1);
        }
    }
}