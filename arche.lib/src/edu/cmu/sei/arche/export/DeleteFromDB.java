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

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jess.RU;
import jess.Value;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * DeleteFromDB remove all the persisted facts of a project in the ArchE database.
 * The operation is simple. It truncates all the tables in the DBMS.
 * 
 * @author Hyunwoo Kim
 */
public class DeleteFromDB {
    
    /**
     * <code>dbconfig</code> This object contains the JDBC configuration.
     */
    private JDBCConfig dbconfig = null;
    
    /**
     * <code>deleteop</code> This object is the wrapper class that hides all the
     * JDBC operation.
     */
    private DeleteOperation deleteop = null;
    
    private static final String PROPERTYFILE_NAME = "properties.xml";
	
    /**
     * Constructor
     */
    public DeleteFromDB() {    	
    }
    /**
     * Import the ArchE project(design) from a SQL file which is readable by MySQL
     * 
     * @param filePath
     */
    public void delete(String projectName) {
    	        
        // initialize JDBC configuration object
        if (dbconfig == null) {
            dbconfig = new JDBCConfig();
            // load JDBC configuration and a set of FactType
            if (loadProperties() == false) {
                return;
            }
        }  
                
        // create JDBC connection
        deleteop = DeleteOperation.getInstance();
        if (!deleteop.createConnection(dbconfig) ){
            return;
        }
	            
        // truncate the facts tables in the DBMS
        if (!deleteop.clearFactsRelatedToVersion(projectName)) {
            return;
        }        
        
        return;      
    }

    /**
     * Read the JDBC configuration and Factset information from the properties.xml file.
     *
     * @param factSetName
     * @return true if the properties are loaded successfully
     */
    private boolean loadProperties() {
        XMLReader xmlReader = null;
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            spfactory.setValidating(false);
            
            // get the SAX paser and its xml reader
            SAXParser saxParser = spfactory.newSAXParser();
            xmlReader = saxParser.getXMLReader();

            xmlReader.setContentHandler(new PropertyLoader(dbconfig));
            
			URL libPluginURL = FileLocator.find(Platform.getBundle("SEI.ArchE.Lib"), new Path("/"), null);
			String path = FileLocator.resolve(libPluginURL).getPath() + "core/classes/edu/cmu/sei/arche/core/" + PROPERTYFILE_NAME;            
//			String path = FileLocator.resolve(libPluginURL).getPath() + PROPERTYFILE_NAME;                        
            InputSource source = new InputSource(path);
            xmlReader.parse(source);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}