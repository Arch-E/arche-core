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

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/*  
 * $Id: PropertyLoader.java,v 1.2 2006/02/24 22:39:01 fb Exp $
 * 
 * Copyright (c) 2005, Software Engineering Institute and Robert Bosch Corp.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html  
 */

/** 
 * This class load the properties information from the XML file.
 * 
 * @author Jinhee Lee
 * @version $Revision: 1.2 $
 */

public class PropertyLoader extends DefaultHandler {
    
    private JDBCConfig config = null;
    
    private int curElementType = -1;
    
    private static final int ELEMENT_SQL_URL = 1;
    private static final int ELEMENT_SQL_USER = 2;
    private static final int ELEMENT_SQL_PWD = 3;
    private static final int ELEMENT_SQL_DRIVER = 4;
    
    /**
     * constructor
     *
     *  @param factSetFilter : the name of factset to be persisted 
     *  @param setFactSet : the set collection to maintain the list of FactType to be persisted
     *  @param config : the JDBC configuration 
     */
    public PropertyLoader(JDBCConfig config) {
        super();
        this.config = config;
    }
        
    public void startElement( String uri, String localName, String qName,
		      Attributes attribs ) {
        if (qName.equals("SQL_URL")) {
            curElementType = ELEMENT_SQL_URL;
        } else if (qName.equals("SQL_User")) {
            curElementType = ELEMENT_SQL_USER;
        } else if (qName.equals("SQL_Pwd")) {
            curElementType = ELEMENT_SQL_PWD;
        } else if (qName.equals("SQL_Driver")) {
            curElementType = ELEMENT_SQL_DRIVER;
        }
        //System.out.println("StartElement: " + qName);
    }
    
    public void characters( char[] data, int start, int length ) {
        switch ( curElementType ) {
        case ELEMENT_SQL_PWD:
            config.setSQL_Pwd(new String(data, start, length));
            break;
        case ELEMENT_SQL_USER:
            config.setSQL_User(new String(data, start, length));
            break;
        case ELEMENT_SQL_URL:
            config.setSQL_URL(new String(data, start, length));
            break;
        case ELEMENT_SQL_DRIVER:
            config.setSQL_Driver(new String(data, start, length));
            break;
        }
    } 
    
    public void endElement( String uri, String localName, String qName ) {
        curElementType = -1;
    }
}
